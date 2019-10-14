/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.control;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Asiento;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.AsientoSala;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Boleto;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.Funcion;

/**
 *
 * @author jcpenya
 */
@ExtendWith(MockitoExtension.class)
public class AsientoBeanTest {

    public AsientoBeanTest() {
    }

    @Test
    public void testGetCarnet() {
        System.out.println("getCarnet");
        AsientoBean cut = new AsientoBean();
        assertNotNull(cut.getCarnet());
    }

    @Test
    public void testFindDisponiblesByFuncion() throws Exception {
        System.out.println("findDisponiblesByFuncion");
        AsientoBean cut = new AsientoBean();
        List<Asiento> result = cut.findDisponiblesByFuncion(null);
        assertTrue(result.isEmpty());
        List<Asiento> esperado = new ArrayList<>();
        esperado.add(new Asiento(1));
        esperado.add(new Asiento(2));
        esperado.add(new Asiento(3));
        List<Asiento> vendidos = new ArrayList<>();
        vendidos.add(new Asiento(4));
        vendidos.add(new Asiento(5));
        List<Asiento> listaTodosAsientos = new ArrayList<>(esperado);
        List<Boleto> listaEmitidos = new ArrayList<>();
        for (int i = 0; i < vendidos.size(); i++) {
            Boleto b = new Boleto(i);
            b.setIdAsiento(new AsientoSala(vendidos.get(i).getIdAsiento()));
            b.getIdAsiento().setAsiento(vendidos.get(i));
            listaEmitidos.add(b);
        }
        listaTodosAsientos.addAll(vendidos);
        Integer idFuncion = 1;
        Funcion funcion1 = new Funcion(idFuncion);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Query mockQSalaFindByIdSala = Mockito.mock(Query.class);
        BoletoBean mockBB = Mockito.mock(BoletoBean.class);
        Mockito.when(mockBB.findBoletosByIdFuncion(idFuncion, 0, 10000)).thenReturn(listaEmitidos);
        Mockito.when(mockQSalaFindByIdSala.getResultList()).thenReturn(listaTodosAsientos);
        Mockito.when(mockEM.createNamedQuery("Sala.findByIdSala")).thenReturn(mockQSalaFindByIdSala);
        FuncionBean mockFB = Mockito.mock(FuncionBean.class);
        Mockito.when(mockFB.findById(idFuncion)).thenReturn(funcion1);
        cut.em = mockEM;
        assertThrows(DataAccessException.class, () -> {
            cut.findDisponiblesByFuncion(idFuncion);
        });
        cut.funcionBean = mockFB;
        cut.boletoBean = mockBB;
        result = cut.findDisponiblesByFuncion(idFuncion);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertIterableEquals(esperado, result);
    }

}
