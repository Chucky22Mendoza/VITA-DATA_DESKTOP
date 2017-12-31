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
public class BusquedaHospital {
    private String _nombre;
    private String _ciudad;
    private String _estado;
    private String _cp;

    public BusquedaHospital() {
    }

    public BusquedaHospital(String _nombre, String _ciudad, String _estado, String _cp) {
        this._nombre = _nombre;
        this._ciudad = _ciudad;
        this._estado = _estado;
        this._cp = _cp;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getCiudad() {
        return _ciudad;
    }

    public void setCiudad(String _ciudad) {
        this._ciudad = _ciudad;
    }

    public String getEstado() {
        return _estado;
    }

    public void setEstado(String _estado) {
        this._estado = _estado;
    }

    public String getCp() {
        return _cp;
    }

    public void setCp(String _cp) {
        this._cp = _cp;
    }
    
}
