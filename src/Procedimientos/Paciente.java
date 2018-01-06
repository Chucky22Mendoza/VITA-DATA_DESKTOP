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
public class Paciente {
    private String _paciente;
    private int _id;

    public Paciente() {
    }

    public Paciente(String _paciente, int _id) {
        this._paciente = _paciente;
        this._id = _id;
    }

    public String getPaciente() {
        return _paciente;
    }

    public void setPaciente(String _paciente) {
        this._paciente = _paciente;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }
    @Override
    public String toString(){
        return _paciente;
    }        
}
