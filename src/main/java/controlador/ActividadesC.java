/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ActividadesImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ActividadModel;
import servicios.Reporte;

/**
 *
 * @author EDGARD
 */
@Named(value = "actividadesC")
@SessionScoped
public class ActividadesC implements Serializable {

    private ActividadModel actividadModel;
    private List<ActividadModel> lstActividades;
    private List<ActividadModel> lstFecha;
    private ActividadesImpl dao;

    public ActividadesC() {

        actividadModel = new ActividadModel();
        dao = new ActividadesImpl();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(actividadModel);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar ActividadesC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(actividadModel);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ActividadesC/modificar: " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(actividadModel);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ActividadesC/eliminar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstActividades = dao.listarVista();
        } catch (Exception e) {
            System.out.println("Error en listar ActividadesC/listar: " + e.getMessage());
        }
    }

    public ActividadesImpl getDao() {
        return dao;
    }

    public void setDao(ActividadesImpl dao) {
        this.dao = dao;
    }

    public ActividadModel getActividadModel() {
        return actividadModel;
    }

    public void setActividadModel(ActividadModel actividadModel) {
        this.actividadModel = actividadModel;
    }

    public List<ActividadModel> getLstActividades() {
        return lstActividades;
    }

    public void setLstActividades(List<ActividadModel> lstActividades) {
        this.lstActividades = lstActividades;
    }

    public void reporteCuota() throws Exception {
        Reporte report = new Reporte();
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "actividad.jasper", fechSystem + "actividad.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteCuotaRango() throws Exception {
        try {
            if (actividadModel.getFechaReportEntrada() == null || actividadModel.getFechaReportSalida() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar una fecha en el reporte"));
            }
            if (actividadModel.getFechaReportEntrada() != null && actividadModel.getFechaReportSalida() != null) {
                if (actividadModel.getFechaReportEntrada().after(actividadModel.getFechaReportSalida())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha de inicio es mayor a la salida en el reporte"));
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String sts1 = dateFormat.format(actividadModel.getFechaReportEntrada());
                    String sts2 = dateFormat.format(actividadModel.getFechaReportSalida());
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date fechaActual = new Date(System.currentTimeMillis());
                    String fechSystem = dateFormat2.format(fechaActual);
                    Reporte report = new Reporte();
                    Map<String, Object> parameters = new HashMap();
                    parameters.put("Parameter1", sts1);
                    parameters.put("Parameter2", sts2);
                    report.exportarPDFGlobal(parameters, "rangoFecha.jasper", fechSystem + "rangoFecha.pdf");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public void reporteListActividad() throws Exception {

        try {
            if (actividadModel.getFechaLista() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falta rellenar la fecha en el reporte"));
            }
            if (actividadModel.getFechaLista() != null) {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fechaActual = new Date(System.currentTimeMillis());
                String fechSystem = dateFormat2.format(fechaActual);
                String sts = actividadModel.getFechaLista();
                Reporte report = new Reporte();

                Map<String, Object> parameters = new HashMap();
                parameters.put("Parameter1", sts);
                report.exportarPDFGlobal(parameters, "actividadFechaUnica.jasper", fechSystem + " fechaUnica.pdf");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    public List<ActividadModel> getLstFecha() {
        lstFecha = new ArrayList<ActividadModel>();
        try {
            lstFecha = dao.listaFecha();
        } catch (SQLException ex) {
            Logger.getLogger(ActividadesC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActividadesC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstFecha;
    }

    public void setLstFecha(List<ActividadModel> lstFecha) {
        this.lstFecha = lstFecha;
    }

}
