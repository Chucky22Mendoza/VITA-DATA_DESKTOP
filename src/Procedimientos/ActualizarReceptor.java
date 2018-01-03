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
public class ActualizarReceptor {
    private int _idPac;
    private String _nombre;
    private String _tel;

    public ActualizarReceptor() {
    }

    public ActualizarReceptor(int _idPac, String _nombre, String _tel) {
        this._idPac = _idPac;
        this._nombre = _nombre;
        this._tel = _tel;
    }

    public int getIdPac() {
        return _idPac;
    }

    public void setIdPac(int _idPac) {
        this._idPac = _idPac;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getTel() {
        return _tel;
    }

    public void setTel(String _tel) {
        this._tel = _tel;
    }
    
    
}
