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
public class MedicoQuirurgico {
    private int idPac;
    private String  ope;
    private String fecha;

    public MedicoQuirurgico() {
    }

    public MedicoQuirurgico(int idPac, String ope, String fecha) {
        this.idPac = idPac;
        this.ope = ope;
        this.fecha = fecha;
    }

    public int getIdPac() {
        return idPac;
    }

    public void setIdPac(int idPac) {
        this.idPac = idPac;
    }

    public String getOpe() {
        return ope;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
