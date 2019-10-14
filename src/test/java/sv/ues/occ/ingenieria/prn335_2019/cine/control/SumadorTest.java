/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jcpenya
 */
public class SumadorTest {

    public SumadorTest() {
    }

    @Test
    public void testSumar() {
        System.out.println("sumar");
        Integer sumando1 = 1;
        Integer sumando2 = 2;
        Sumador instance = new Sumador();
        Integer expResult = 3;
        Integer result = instance.sumar(sumando1, sumando2);
        assertEquals(expResult, result);
    }

}
