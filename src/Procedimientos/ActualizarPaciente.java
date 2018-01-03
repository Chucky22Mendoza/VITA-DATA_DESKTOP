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
public class ActualizarPaciente {
    private int _id;
    private String _nombre;
    private int _edad;
    private float _peso;
    private float _estatura;
    private String _fec;

    public ActualizarPaciente() {
    }

    public ActualizarPaciente(int _id, String _nombre, int _edad, float _peso, float _estatura, String _fec) {
        this._id = _id;
        this._nombre = _nombre;
        this._edad = _edad;
        this._peso = _peso;
        this._estatura = _estatura;
        this._fec = _fec;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
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

    public float getPeso() {
        return _peso;
    }

    public void setPeso(float _peso) {
        this._peso = _peso;
    }

    public float getEstatura() {
        return _estatura;
    }

    public void setEstatura(float _estatura) {
        this._estatura = _estatura;
    }

    public String getFec() {
        return _fec;
    }

    public void setFec(String _fec) {
        this._fec = _fec;
    }

    
    
}
