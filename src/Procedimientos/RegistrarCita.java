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
public class RegistrarCita {
    private String _paciente;
    private String _doctor;
    private String _fecha;

    public RegistrarCita() {
    }

    public RegistrarCita(String _paciente, String _doctor, String _fecha) {
        this._paciente = _paciente;
        this._doctor = _doctor;
        this._fecha = _fecha;
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
    
    
}
