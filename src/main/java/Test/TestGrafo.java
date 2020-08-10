package Test;

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
        grafoService.gfInit();


    }

    @Test
    public void
}
