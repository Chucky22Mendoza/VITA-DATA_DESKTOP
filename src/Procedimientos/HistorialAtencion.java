/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procedimientos;

/**
 *
 * @author mendo
 */
public class HistorialAtencion {
    private String _paciente;
    private String _doctor;
    private String _fecha;
    private int _mes;
    private int _año;

    public HistorialAtencion() {
    }

    public HistorialAtencion(String _paciente, String _doctor, String _fecha, int _mes, int _año) {
        this._paciente = _paciente;
        this._doctor = _doctor;
        this._fecha = _fecha;
        this._mes = _mes;
        this._año = _año;
    }

    public String getPaciente() {
        return _paciente;
    }

    public void setPaciente(String _paciente) {
        this._paciente = _paciente;
    }

    public String getDoctor() {
        return _doctor;
    }

    public void setDoctor(String _doctor) {
        this._doctor = _doctor;
    }

    public String getFecha() {
        return _fecha;
    }

    public void setFecha(String _fecha) {
        this._fecha = _fecha;
    }

    public int getMes() {
        return _mes;
    }

    public void setMes(int _mes) {
        this._mes = _mes;
    }

    public int getAño() {
        return _año;
    }

    public void setAño(int _año) {
        this._año = _año;
    }

    
    
}
