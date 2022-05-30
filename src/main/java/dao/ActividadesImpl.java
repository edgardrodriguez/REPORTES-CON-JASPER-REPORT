/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ActividadModel;

/**
 *
 * @author EDGARD
 */
public class ActividadesImpl extends Conexion implements ICRUD<ActividadModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void guardar(ActividadModel obj) throws Exception {
        String sql = "insert into ACTIVIDADES (NOMACT,MONESPACT,FECACT,ESTACT) values (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombreAct());
            ps.setInt(2, obj.getMontoAct());
            ps.setString(3, formatter.format(obj.getFecha()));
            ps.setString(4, obj.getEstado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar Actividad Dao {0} ", e.getMessage());
        }
    }

    @Override
    public void modificar(ActividadModel obj) throws Exception {
        String sql = "update ACTIVIDADES set NOMACT=?,MONESPACT=?,FECACT=?,ESTACT=? where IDACT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombreAct());
            ps.setInt(2, obj.getMontoAct());
            ps.setString(3, formatter.format(obj.getFecha()));
            ps.setString(4, obj.getEstado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar Actividad Dao {0} ", e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(ActividadModel obj) throws Exception {
        String sql = "delete from ACTIVIDADES where IDACT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar Actividad Dao {0} ", e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<ActividadModel> listarTodos() throws Exception {
        List<ActividadModel> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "select *from ACTIVIDADES";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActividadModel act = new ActividadModel();
                act.setID(rs.getInt("IDACT"));
                act.setNombreAct(rs.getString("NOMACT"));
                act.setMontoAct(rs.getInt("MONESPACT"));
                act.setFecha(rs.getDate("FECACT"));
                act.setEstado(rs.getString("ESTACT"));
                listado.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar Actividad Dao {0} ", e.getMessage());
        }
        return listado;
    }

    public List<ActividadModel> listarVista() throws Exception {
        List<ActividadModel> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "select* from V_ACTIVIDADES ORDER BY IDACT DESC";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActividadModel act = new ActividadModel();
                act.setID(rs.getInt("IDACT"));
                act.setNombreAct(rs.getString("NOMACT"));
                act.setMontoAct(rs.getInt("MONESPACT"));
                act.setFecha(rs.getDate("FECACT"));
                act.setEstado(rs.getString("ESTACT"));
                act.setEstadoVist(rs.getString("ESTADO"));
                listado.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar vista Actividad Dao {0} ", e.getMessage());
        }
        return listado;
    }

    public List<ActividadModel> listaFecha() throws Exception {
        List<ActividadModel> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM V_FECHA";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActividadModel act = new ActividadModel();
                act.setFechaLista(rs.getString("FECHACO"));
                listado.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar FECHA Actividad Dao {0} ", e.getMessage());
        }
        return listado;
    }
}
