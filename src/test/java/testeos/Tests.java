package testeos;

import general.Atraccion;
import general.ENUMTIPO;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testConstructor(){
        Atraccion atraccion = new Atraccion("asd",2,3,4, ENUMTIPO.PAISAJE);
        System.out.println(atraccion);
        assertNotNull(atraccion);
    }
}
