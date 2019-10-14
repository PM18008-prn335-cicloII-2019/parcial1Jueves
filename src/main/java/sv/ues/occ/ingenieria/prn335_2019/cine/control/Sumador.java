/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

/**
 *
 * @author jcpenya
 */
public class Sumador {

    public Integer sumar(Integer sumando1, Integer sumando2) {
        if (sumando1 == null || sumando2 == null) {
            return null;
        }
        return sumando1 + sumando2;
    }

}
