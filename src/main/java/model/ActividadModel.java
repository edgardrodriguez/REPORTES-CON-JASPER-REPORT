/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author EDGARD
 */
import java.util.Date;
public class ActividadModel {

    private int ID;
    private String nombreAct;
    private int montoAct;
    private Date fecha;
    private String estado;
    private String estadoVist;
    
    private Date  FechaReportEntrada;
    private Date  FechaReportSalida;
    private Date  FechaReportRango;
    private String fechaLista;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreAct() {
        return nombreAct;
    }

    public void setNombreAct(String nombreAct) {
        this.nombreAct = nombreAct;
    }

    public int getMontoAct() {
        return montoAct;
    }

    public void setMontoAct(int montoAct) {
        this.montoAct = montoAct;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoVist() {
        return estadoVist;
    }

    public void setEstadoVist(String estadoVist) {
        this.estadoVist = estadoVist;
    }

    public Date getFechaReportEntrada() {
        return FechaReportEntrada;
    }

    public void setFechaReportEntrada(Date FechaReportEntrada) {
        this.FechaReportEntrada = FechaReportEntrada;
    }

    public Date getFechaReportSalida() {
        return FechaReportSalida;
    }

    public void setFechaReportSalida(Date FechaReportSalida) {
        this.FechaReportSalida = FechaReportSalida;
    }

    public Date getFechaReportRango() {
        return FechaReportRango;
    }

    public void setFechaReportRango(Date FechaReportRango) {
        this.FechaReportRango = FechaReportRango;
    }

    public String getFechaLista() {
        return fechaLista;
    }

    public void setFechaLista(String fechaLista) {
        this.fechaLista = fechaLista;
    }
    
}
