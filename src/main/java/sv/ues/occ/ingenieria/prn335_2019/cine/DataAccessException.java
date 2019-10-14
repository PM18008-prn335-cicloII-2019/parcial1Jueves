/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine;

import java.io.Serializable;

/**
 *
 * @author jcpenya
 */
public class DataAccessException extends Exception implements Serializable {

    public DataAccessException(final String razon, final Throwable causa) {
        super(razon, causa);
    }

}
