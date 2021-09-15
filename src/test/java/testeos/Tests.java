package testeos;

import sugeribles.Atraccion;
import Enumeradores.ENUMTIPO;
import sistema.Sistema;
import sugeribles.promociones.PromocionPorcentaje;
import usuarios.Usuario;
import org.junit.Test;
import sugeribles.promociones.IPromocion;
import sugeribles.promociones.PromocionAbsoluta;
import sugeribles.promociones.PromocionAxB;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testConstructor(){
        Atraccion atraccion = new Atraccion("asd",2,3,4, ENUMTIPO.PAISAJE);
        System.out.println(atraccion);
        assertNotNull(atraccion);
    }

    @Test
    public void testUsuariosCSV() throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario(41456213, ENUMTIPO.PAISAJE, 7, 10));
        usuarioList.add(new Usuario(41456214, ENUMTIPO.ADVENTURA, 9, 15));
        usuarioList.add(new Usuario(41456215, ENUMTIPO.PAISAJE, 100, 123));
        usuarioList.add(new Usuario(35712346, ENUMTIPO.DEGUSTACION, 8, 15));
        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
        assertEquals(usuarioList, sistema.getUsuarios());
    }

    @Test
    public void testAtraccionesCSV() throws Exception {
        List<Atraccion> atraccionList = new ArrayList<>();
        atraccionList.add(new Atraccion("Atraccion prueba 1", 12, 3, 5, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 2", 23, 8, 3, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 3", 5, 6, 63, ENUMTIPO.ADVENTURA));
        atraccionList.add(new Atraccion("Atraccion prueba 4", 12, 1, 6, ENUMTIPO.ADVENTURA));
        atraccionList.add(new Atraccion("Atraccion prueba 5", 7, 4, 32, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 6", 5, 4, 7, ENUMTIPO.DEGUSTACION));
        atraccionList.add(new Atraccion("Atraccion prueba 7", 10, 4, 7, ENUMTIPO.DEGUSTACION));

        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
        assertEquals(atraccionList, sistema.getAtracciones());
    }

    @Test
    public void testPromocionesCSV() throws Exception {
        List<Atraccion> atraccionList = new ArrayList<>();
        atraccionList.add(new Atraccion("Atraccion prueba 1", 12, 3, 5, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 2", 23, 8, 3, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 3", 5, 6, 63, ENUMTIPO.ADVENTURA));
        atraccionList.add(new Atraccion("Atraccion prueba 4", 12, 1, 6, ENUMTIPO.ADVENTURA));
        atraccionList.add(new Atraccion("Atraccion prueba 5", 7, 4, 32, ENUMTIPO.PAISAJE));
        atraccionList.add(new Atraccion("Atraccion prueba 6", 5, 4, 7, ENUMTIPO.DEGUSTACION));
        atraccionList.add(new Atraccion("Atraccion prueba 7", 10, 4, 7, ENUMTIPO.DEGUSTACION));

        List<IPromocion> promociones = new ArrayList<>();
        promociones.add(new PromocionAbsoluta(atraccionList.get(2), atraccionList.get(3), 6));
        promociones.add(new PromocionAxB(atraccionList.get(0), atraccionList.get(1), atraccionList.get(4)));
        promociones.add(new PromocionPorcentaje(atraccionList.get(5), atraccionList.get(6), 50));
        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
        assertEquals(promociones, sistema.getPromociones());
        sistema.exportarUsuarios();
    }

    @Test
    public void testSistema() throws Exception {
        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
        sistema.getUsuarios().get(0).getAtracciones().forEach(System.out::println);
        assertEquals(1, sistema.getUsuarios().get(0).getDineroDisponible());
        assertEquals(3.0, sistema.getUsuarios().get(0).getTiempoDisponible(), 0);
    }

    @Test
    public void testExportar() throws Exception {
        Sistema sistema = new Sistema("src/main/resources/usuarios.csv", "src/main/resources/atracciones.csv", "src/main/resources/promociones.csv");
    }
}
