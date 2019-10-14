/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Funcion;

/**
 *
 * @author melvin
 */

@Stateless
@LocalBean
public class FuncionBean implements Serializable{
    
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public Funcion findById(Integer idFuncion) throws DataAccessException{
       if(this.em==null || idFuncion==null){
          return null; 
       }
        
        try {
        Query query =em.createQuery("Funcion.findByIdFuncion");
        query.setParameter(":idFuncion",idFuncion);
        
        List<Funcion> resultadoList=query.getResultList();
        
        if(resultadoList.isEmpty()){
            return null;
        }else{
            Funcion res =resultadoList.get(0);
            return res;
        }
        
        
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(),e);
        }
        
        
    }
    
}
