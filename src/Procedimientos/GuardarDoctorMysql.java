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
public class GuardarDoctorMysql {
    private String _nombre;
    private String _ced;
    private String _usuario;
    private String _contraseña;

    public GuardarDoctorMysql(String _nombre, String _ced, String _usuario, String _contraseña) {
        this._nombre = _nombre;
        this._ced = _ced;
        this._usuario = _usuario;
        this._contraseña = _contraseña;
    }

    public GuardarDoctorMysql() {
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getCed() {
        return _ced;
    }

    public void setCed(String _ced) {
        this._ced = _ced;
    }

    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String getContraseña() {
        return _contraseña;
    }

    public void setContraseña(String _contraseña) {
        this._contraseña = _contraseña;
    }
    
    
}
