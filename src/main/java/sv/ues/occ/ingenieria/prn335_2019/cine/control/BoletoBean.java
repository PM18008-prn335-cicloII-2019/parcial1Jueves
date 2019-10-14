/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Boleto;

/**
 *
 * @author melvin
 */

@Stateless
@LocalBean
public class BoletoBean implements Serializable{
    
    @PersistenceContext(unitName = "my_persistence_unit")
    
    EntityManager em;
    
    public List<Boleto> findBoletosByIdFuncion(Integer idFuncion, int desde,int hasta) throws DataAccessException{
        if(this.em == null || idFuncion == null){
            return Collections.emptyList();
        }
        
        try {
           Query query= em.createQuery("SELECT b FROM Boleto b JOIN Funcion f WHERE f.idFuncion = :id");
           query.setParameter(":id",idFuncion);
           
           List<Boleto> resultado=query.getResultList();
           return resultado;
           
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(),e);
        }
        
    }
    
}
