package sistema;

import comparadores.ComparadorAtraccion;
import comparadores.ComparadorPromocion;
import sugeribles.Atraccion;
import enumeradores.ENUMTIPO;
import sugeribles.Sugerencia;
import sugeribles.promociones.IPromocion;
import sugeribles.promociones.PromocionAbsoluta;
import sugeribles.promociones.PromocionAxB;
import sugeribles.promociones.PromocionPorcentaje;
import usuarios.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sistema {
    private final List<Usuario> usuarios;
    private final List<Atraccion> atracciones;
    private final List<IPromocion> promociones;

    public Sistema(String directorioUsuariosCSV, String directorioAtraccionesCSV, String directorioPromocionesCSV) throws Exception {
        this.usuarios = new ArrayList<>();
        this.atracciones = new ArrayList<>();
        this.promociones = new ArrayList<>();
        agregarUsuariosCSV(new LectorCSV(directorioUsuariosCSV).getListaCSV());
        agregarAtraccionesCSV(new LectorCSV(directorioAtraccionesCSV).getListaCSV());
        agregarPromocionesCSV(new LectorCSV(directorioPromocionesCSV).getListaCSV());
        atracciones.sort(new ComparadorAtraccion());
        promociones.sort(new ComparadorPromocion());

        for (Usuario usuario : usuarios) {
            System.out.println("\n"+"----------------------------------------------------"
                    +"----------------------------------------------------"
                    +"----------------------------------------------------");
            System.out.println("Usuario DNI numero: " + usuario.getDNI());
            sugerirPromociones(usuario);
            sugerirAtracciones(usuario);
        }
    }

    public Sistema(List<Usuario> usuarios, List<Atraccion> atracciones, List<IPromocion> promociones) throws IOException {
        this.usuarios = usuarios;
        this.atracciones = atracciones;
        this.promociones = promociones;
        this.atracciones.sort(new ComparadorAtraccion());
        this.promociones.sort(new ComparadorPromocion());

        for (Usuario usuario : usuarios) {
            System.out.println("\n"+"----------------------------------------------------"
                    +"----------------------------------------------------"
                    +"----------------------------------------------------");
            System.out.println("Usuario DNI numero: " + usuario.getDNI());
            sugerirPromociones(usuario);
            sugerirAtracciones(usuario);
        }

        exportarUsuarios();
    }

    private void sugerirPromociones(Usuario user){
        List<Atraccion> listaAtracciones = new ArrayList<>();
        int valorTemporal;

        for (IPromocion promocion : promociones) {
            if(promocion.getAtraccionA().hayEspacio() && promocion.getAtraccionB().hayEspacio()) {
                int costoActual = 0;
                double tiempoActual = 0;

                if (promocion.getAtraccionA().getTipo() == user.getTipoFavorito()) {
                    if (promocion.getClass() == PromocionAbsoluta.class) {
                        if ((costoActual + (int) promocion.retornarPromocion() <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                            listaAtracciones.add(promocion.getAtraccionA());
                            listaAtracciones.add(promocion.getAtraccionB());
                            costoActual += (int) promocion.retornarPromocion();
                            user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                        }
                    }
                    if (promocion.getClass() == PromocionPorcentaje.class) {
                        valorTemporal = (int) (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - ((promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto()) * ((double)promocion.retornarPromocion() / 100)));
                        if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                            listaAtracciones.add(promocion.getAtraccionA());
                            listaAtracciones.add(promocion.getAtraccionB());
                            costoActual += valorTemporal;
                            user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                        }
                    }
                    if (promocion.getClass() == PromocionAxB.class) {
                        Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                        if(atraccionAux.hayEspacio()) {
                            valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();

                            if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                                listaAtracciones.add(promocion.getAtraccionA());
                                listaAtracciones.add(promocion.getAtraccionB());
                                listaAtracciones.add(atraccionAux);
                                costoActual += valorTemporal;
                                user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                            }
                        }
                    }
                }
            }
            listaAtracciones.clear();
        }

        //Ofrezco promociones pero que no son del mismo tipo.

        for (IPromocion promocion : promociones) {
            int costoActual = 0;
            double tiempoActual = 0;

            if (promocion.getAtraccionA().getTipo() != user.getTipoFavorito()) {
                if (promocion.getClass() == PromocionAbsoluta.class) {
                    if ((costoActual + (int) promocion.retornarPromocion() <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += (int) promocion.retornarPromocion();
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionPorcentaje.class) {
                    valorTemporal = (int) (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - ((promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto()) * ((double)promocion.retornarPromocion() / 100)));
                    if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionAxB.class) {
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
                    Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                    if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        listaAtracciones.add(atraccionAux);
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
            }
            listaAtracciones.clear();
        }
    }

    private void sugerirAtracciones (Usuario user){
        int costoActual = 0;
        double tiempoActual = 0;
        Atraccion[] atraccionesTipo = obtenerAtraccionesTipo(user.getTipoFavorito());

        for (Atraccion atraccion : atraccionesTipo) {
            if ((atraccion != null) && !user.getAtracciones().contains(atraccion) && atraccion.hayEspacio() && (costoActual + atraccion.getCosto() <= user.getDineroDisponible()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                user.recibirSugerencia(new Sugerencia(new Atraccion[]{atraccion}, null, atraccion.getCosto()));
            }
        }

        //Incluyo las atracciones que no son del mismo tipo
        for (Atraccion atraccion : atracciones) {
            if ((atraccion != null) && !user.getAtracciones().contains(atraccion) && atraccion.hayEspacio() && (costoActual + atraccion.getCosto() <= user.getDineroDisponible()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                user.recibirSugerencia(new Sugerencia(new Atraccion[]{atraccion}, null, atraccion.getCosto()));
            }
        }
    }

    public boolean agregarUsuario(Usuario user) {
        if (!usuarios.contains(user)){
            usuarios.add(user);
            System.out.println("\n"+"----------------------------------------------------"
                    +"----------------------------------------------------"
                    +"----------------------------------------------------");
            System.out.println("Usuario DNI numero: " + user.getDNI());
            sugerirPromociones(user);
            sugerirAtracciones(user);
            return true;
        }
        return false;
    }

    public boolean removerUsuario(int DNI){
        if (usuarios.contains(new Usuario(DNI, ENUMTIPO.ADVENTURA, 0, 0))) {
            Usuario aux = usuarios.remove(usuarios.indexOf(new Usuario(DNI, ENUMTIPO.ADVENTURA, 0, 0)));
            for (Atraccion atraccion : aux.getAtracciones()) {
                atraccion.liberarUnLugar();
            }
            return true;
        }
        return false;
    }

    private Atraccion[] obtenerAtraccionesTipo(ENUMTIPO tipo){
        List<Atraccion> listaAtraccion = new ArrayList<>();
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getTipo() == tipo){
                listaAtraccion.add(atraccion);
            }
        }
        return listaAtraccion.toArray(new Atraccion[0]);
    }

    public void agregarUsuariosCSV(List<String> listaCSV) throws Exception {
        Pattern patronRegex = Pattern.compile("(\\d*), ?(.*), ?(\\d*), ?(\\d*(?:.\\d*)?)");
        for (String fila : listaCSV) {
            Matcher matcher = patronRegex.matcher(fila);
            if (matcher.matches()) {
                if (1 > Integer.parseInt(matcher.group(3))) {
                    throw new Exception("El presupuesto es menor a 1 moneda.");
                }

                if (1 > Double.parseDouble(matcher.group(4))) {
                    throw new Exception("El tiempo disponible es menor a 1 hora.");
                }

                if(usuarios.contains(new Usuario(Integer.parseInt(matcher.group(1)), ENUMTIPO.valueOf(matcher.group(2).toUpperCase()), Integer.parseInt(matcher.group(3)), Double.parseDouble(matcher.group(4))))){
                    throw new Exception("El usuario ya existe.");
                }
                usuarios.add(new Usuario(Integer.parseInt(matcher.group(1)), ENUMTIPO.valueOf(matcher.group(2).toUpperCase()), Integer.parseInt(matcher.group(3)), Double.parseDouble(matcher.group(4))));
            }
        }

    }

    public void agregarAtraccionesCSV(List<String> listaCSV) throws Exception {
        Pattern patronRegex = Pattern.compile("(.*), ?(\\d*), ?(\\d*), ?(\\d*), ?(.*)");
        for (String fila : listaCSV) {
            Matcher matcher = patronRegex.matcher(fila);
            if(matcher.matches()) {
                for (Atraccion atraccion : atracciones) {
                    if (atraccion.getNombre().equals(matcher.group(1))) {
                        throw new Exception("Ya existe una atraccion con ese nombre.");
                    }
                }
                atracciones.add(new Atraccion(matcher.group(1), Integer.parseInt(matcher.group(2)), Double.parseDouble(matcher.group(3)), Integer.parseInt(matcher.group(4)), ENUMTIPO.valueOf(matcher.group(5).toUpperCase())));
            }
        }
    }

    public void agregarPromocionesCSV(List<String> listaCSV) throws Exception {
        Pattern patronRegex = Pattern.compile("(.*), ?(.*), ?(.*), ?(\\d*), ?(\\d*)");
        for (String fila : listaCSV) {
            Matcher matcher = patronRegex.matcher(fila);
            if (matcher.matches()) {
                Atraccion atraccionA = null;
                Atraccion atraccionB = null;
                if (!matcher.group(4).equals("0")) {
                    for (Atraccion atraccionTemp : atracciones) {
                        if (matcher.group(1).equals(atraccionTemp.getNombre())) {
                            atraccionA = atraccionTemp;
                        }
                        if (matcher.group(2).equals(atraccionTemp.getNombre())) {
                            atraccionB = atraccionTemp;
                        }
                        if (atraccionA != null && atraccionB != null) {
                            break;
                        }
                    }
                    if (atraccionA == null || atraccionB == null) {
                        throw new Exception("Una de las atracciones brindadas en el CSV no existe.");
                    }
                    promociones.add(new PromocionAbsoluta(atraccionA, atraccionB, Integer.parseInt(matcher.group(4))));
                } else {
                    if (!matcher.group(5).equals("0")) {
                        for (Atraccion atraccionTemp : atracciones) {
                            if (matcher.group(1).equals(atraccionTemp.getNombre())) {
                                atraccionA = atraccionTemp;
                            }
                            if (matcher.group(2).equals(atraccionTemp.getNombre())) {
                                atraccionB = atraccionTemp;
                            }
                            if (atraccionA != null && atraccionB != null) {
                                break;
                            }
                        }
                        if (atraccionA == null || atraccionB == null) {
                            throw new Exception("Una de las atracciones brindadas en el CSV no existe");
                        }
                        promociones.add(new PromocionPorcentaje(atraccionA, atraccionB, Integer.parseInt(matcher.group(5))));
                    } else {
                        Atraccion atraccionC = null;
                        for (Atraccion atraccionTemp : atracciones) {
                            if (matcher.group(1).equals(atraccionTemp.getNombre())) {
                                atraccionA = atraccionTemp;
                            }
                            if (matcher.group(2).equals(atraccionTemp.getNombre())) {
                                atraccionB = atraccionTemp;
                            }
                            if (matcher.group(3).equals(atraccionTemp.getNombre())) {
                                atraccionC = atraccionTemp;
                            }
                            if (atraccionA != null && atraccionB != null && atraccionC != null) {
                                break;
                            }
                        }
                        if (atraccionA == null || atraccionB == null || atraccionC == null) {
                            throw new Exception("Una de las atracciones brindadas en el CSV no existe");
                        }
                        promociones.add(new PromocionAxB(atraccionA, atraccionB, atraccionC));
                    }
                }
            }
        }
    }

    public void exportarUsuarios() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("usuariosExportados.txt"));
        for (Usuario usuario : usuarios) {
            double tiempoOcupado = 0;
            for (Atraccion atraccion : usuario.getAtracciones()) {
                tiempoOcupado += atraccion.getTiempo();
            }
            printWriter.println("El usuario DNI: " + usuario.getDNI() + ", le gustan las atracciones del tipo de " + usuario.getTipoFavorito().toString() + ", ingresó con "
                                + usuario.getDineroInicial() + " monedas y " + usuario.getTiempoInicial() + " horas inicialmente, se retiró con " + usuario.getDineroDisponible() + " monedas disponibles y " + usuario.getTiempoDisponible()
                                + " horas disponibles. Gastó " + usuario.getCostoTotal() + " monedas y estuvo " + tiempoOcupado + " horas en atracciones.");
        }
        printWriter.close();
    }

    public static void main(String[] args) throws Exception {
        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
        boolean salir = false;
        do{
            Scanner scannerInt = new Scanner(System.in);
            int eleccion;
            do {
                System.out.println("\n"
                        + "Intoduzca una de las opciones a continuacion para continuar."
                        + "1- Agregar un usuario."
                        + "2- Remover un usuario."
                        + "3- Exportar los usuarios."
                        + "4- Salir del sistema.");

                eleccion = scannerInt.nextInt();
            }while (eleccion < 1 || eleccion > 4);

            switch (eleccion){
                case 1: {
                    Scanner scannerString = new Scanner(System.in);
                    int tempDNI;
                    int tempMonedas;
                    double tempTiempo;

                    do{
                        System.out.print("Ingrese el DNI: ");
                        tempDNI = scannerInt.nextInt();
                    }while (tempDNI < 0);

                    if (sistema.getUsuarios().contains(new Usuario(tempDNI, ENUMTIPO.ADVENTURA, 0 ,0))){
                        System.out.println("Ya existe un usuario con este DNI");
                        break;
                    }

                    do {
                        System.out.print("\nIngrese las monedas del usuario: ");
                        tempMonedas = scannerInt.nextInt();
                    }while (tempMonedas < 0);

                    do{
                        System.out.print("\nIngrese el tiempo disponible del usuario: ");
                        tempTiempo = scannerInt.nextDouble();
                    }while(tempTiempo < 0);

                    String tempTipo;
                    do {
                        System.out.print("\nIngrese el tipo favorito del usuario (Adventura, Degustacion o Paisaje.): ");
                        tempTipo = scannerString.nextLine();
                    }while (!tempTipo.equalsIgnoreCase("Adventura") && !tempTipo.equalsIgnoreCase("Degustacion") && !tempTipo.equalsIgnoreCase("Paisaje"));

                    sistema.agregarUsuario(new Usuario(tempDNI, ENUMTIPO.valueOf(tempTipo.toUpperCase()), tempMonedas, tempTiempo));
                    break;
                }

                case 2: {
                    System.out.print("Ingrese el DNI del usuario a remover: ");
                    if (sistema.removerUsuario(scannerInt.nextInt())){
                        System.out.println("\nUsuario removido con exito.");
                    }else{
                        System.out.println("\nEl usuario con ese DNI no existe.");
                    }
                    break;
                }

                case 3: {
                    sistema.exportarUsuarios();
                    System.out.println("Usuarios exportados.");
                    break;
                }

                case 4: {
                    salir = true;
                    break;
                }
            }
        }while(!salir);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Atraccion> getAtracciones() {
        return atracciones;
    }

    public List<IPromocion> getPromociones() {
        return promociones;
    }
}
