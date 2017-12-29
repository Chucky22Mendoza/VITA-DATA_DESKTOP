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
public class BusquedaDoctor {
    private String _cedula;
    private String _nombre;
    private String _hospital;

    public BusquedaDoctor(String _cedula, String _nombre, String _hospital) {
        this._cedula = _cedula;
        this._nombre = _nombre;
        this._hospital = _hospital;
    }
    
    public BusquedaDoctor(){
        
    }

    public String getCedula() {
        return _cedula;
    }

    public void setCedula(String _cedula) {
        this._cedula = _cedula;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getHospital() {
        return _hospital;
    }

    public void setHospital(String _hospital) {
        this._hospital = _hospital;
    }
    
    
}
