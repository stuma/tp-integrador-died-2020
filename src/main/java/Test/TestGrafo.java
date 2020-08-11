package Test;

import Model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import Service.*;
import static org.junit.Assert.*;

public class TestGrafo {



    @Test
    @BeforeClass
    public void crearGrafoTest() throws ElementoNoEncontradoException {
        GrafoService grafoService= new GrafoService();

        assertTrue(grafoService.gfInit().getClass().isInstance(Grafo));


    }

}
