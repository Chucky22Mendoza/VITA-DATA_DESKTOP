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
public class GuardarHospital {
    private String _nombre;
    private String _calle;
    private String _colonia;
    private String _cp;
    private String _ciudad;
    private String _telefono;
    private String _estado;

    public GuardarHospital(String _nombre, String _calle, String _colonia, String _cp, String _ciudad, String _telefono, String _estado) {
        this._nombre = _nombre;
        this._calle = _calle;
        this._colonia = _colonia;
        this._cp = _cp;
        this._ciudad = _ciudad;
        this._telefono = _telefono;
        this._estado = _estado;
    }
    
    public GuardarHospital(){
        
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getCalle() {
        return _calle;
    }

    public void setCalle(String _calle) {
        this._calle = _calle;
    }

    public String getColonia() {
        return _colonia;
    }

    public void setColonia(String _colonia) {
        this._colonia = _colonia;
    }

    public String getCp() {
        return _cp;
    }

    public void setCp(String _cp) {
        this._cp = _cp;
    }

    public String getCiudad() {
        return _ciudad;
    }

    public void setCiudad(String _ciudad) {
        this._ciudad = _ciudad;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String getEstado() {
        return _estado;
    }

    public void setEstado(String _estado) {
        this._estado = _estado;
    }
    
    
}
