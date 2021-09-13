// Alumno: Morales, Gabriel.
package sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    private List<String> listaCSV= new ArrayList<>();

    public LectorCSV(String directorioArchivo) throws IOException {
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader(directorioArchivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fila;
        if (csvReader != null) {
            while ((fila = csvReader.readLine()) != null) {
                listaCSV.add(fila);
            }
            csvReader.close();
        }
        
    }

    public List<String> getListaCSV() {
        return listaCSV;
    }

    @Override
    public String toString() {
        return listaCSV.toString();
    }
}
// Alumno: Morales, Gabriel.
