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
public class GuardarPaciente {
    private String _nombre;
    private int _edad;
    private float _peso;
    private float _estatura;
    private String _nacimiento;
    private String _nomTutor;
    private String _tel;
    private String _padecimiento;        

    public GuardarPaciente() {
    }

    public GuardarPaciente(String _nombre, int _edad, float _peso, float _estatura, String _nacimiento, String _nomTutor, String _tel, String _padecimiento) {
        this._nombre = _nombre;
        this._edad = _edad;
        this._peso = _peso;
        this._estatura = _estatura;
        this._nacimiento = _nacimiento;
        this._nomTutor = _nomTutor;
        this._tel = _tel;
        this._padecimiento = _padecimiento;        
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

    public String getNacimiento() {
        return _nacimiento;
    }

    public void setNacimiento(String _nacimiento) {
        this._nacimiento = _nacimiento;
    }

    public String getNomTutor() {
        return _nomTutor;
    }

    public void setNomTutor(String _nomTutor) {
        this._nomTutor = _nomTutor;
    }

    public String getTel() {
        return _tel;
    }

    public void setTel(String _tel) {
        this._tel = _tel;
    }

    public String getPadecimiento() {
        return _padecimiento;
    }

    public void setPadecimiento(String _padecimiento) {
        this._padecimiento = _padecimiento;
    }    
}
