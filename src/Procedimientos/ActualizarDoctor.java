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
public class ActualizarDoctor {
    private String _ced;
    private String _nombre;
    private String _telefono;
    private String _hospital;

    public ActualizarDoctor(String _ced, String _nombre, String _telefono, String _hospital) {
        this._ced = _ced;
        this._nombre = _nombre;
        this._telefono = _telefono;
        this._hospital = _hospital;
    }
    
    public ActualizarDoctor(){
        
    }

    public String getCed() {
        return _ced;
    }

    public void setCed(String _ced) {
        this._ced = _ced;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String getHospital() {
        return _hospital;
    }

    public void setHospital(String _hospital) {
        this._hospital = _hospital;
    }
    
    
}
