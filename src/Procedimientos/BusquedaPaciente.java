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
public class BusquedaPaciente {
    private String _nombre;
    private int _edad;
    private int _mes;
    private int _año;
    private String _fecha;

    public BusquedaPaciente(String _nombre, int _edad, int _mes, int _año, String _fecha) {
        this._nombre = _nombre;
        this._edad = _edad;
        this._mes = _mes;
        this._año = _año;
        this._fecha = _fecha;
    }

    public BusquedaPaciente() {
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int getEdad() {
        return _edad;
    }

    public void setEdad(int _edad) {
        this._edad = _edad;
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

    public String getFecha() {
        return _fecha;
    }

    public void setFecha(String _fecha) {
        this._fecha = _fecha;
    }

    
    
    
}
