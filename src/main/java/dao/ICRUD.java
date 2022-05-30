/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author EDGARD
 */
import java.util.List;

public interface ICRUD<Generic> {
    
    void guardar(Generic obj) throws Exception;

    void modificar(Generic obj) throws Exception;
    
    void eliminar(Generic obj) throws Exception;

    List<Generic> listarTodos() throws Exception;

}

