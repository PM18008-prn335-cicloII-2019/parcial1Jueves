/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Asiento;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Boleto;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Funcion;


/**
 *
 * @author melvin
 */

@Stateless
@LocalBean
public class AsientoBean implements Serializable{
    
    @PersistenceContext(unitName = "my_persistance_unit")
    EntityManager em;
    
    BoletoBean boletoBean;
    FuncionBean funcionBean;
    
    public AsientoBean(){
        boletoBean = new BoletoBean();
        funcionBean = new FuncionBean();
    }
    
    
    
    public String getCarnet(){
        return "PM18008";
    }
    
    public List<Asiento> findDisponiblesByFuncion(Integer idFuncion) throws DataAccessException{
       if( idFuncion == null){
         return Collections.emptyList();   
       }
           
       
       Funcion funcion = funcionBean.findById(idFuncion);
               
       int idSala = -1;
        
       if(funcion == null)
            throw new DataAccessException("Fallo al obtener id de la sala", new Exception());
        
       
       List<Boleto> boletos = boletoBean.findBoletosByIdFuncion(idFuncion, 0, 10000);
        List<Asiento> ocupados = new ArrayList();
        
        Asiento  selec;
        
        for(int i = 0; i < boletos.size(); i++){
            
            selec = boletos.get(i).getIdAsiento().getAsiento();            
            ocupados.add(selec);
        
        }
        
         List<Asiento> asientoList = null;
         
         Query query = em.createNamedQuery("Sala.findByIdSala");
        query.setParameter("idSala",idSala);

        asientoList = query.getResultList();
                        
        
        
        List<Asiento> resp = new ArrayList();
        
        for(int i = 0; i < asientoList.size(); i++){
            
            selec = asientoList.get(i);
            
            if(!ocupados.contains(selec))
                resp.add(selec);
        
        }
        
        return resp;
    }
    
    
}
