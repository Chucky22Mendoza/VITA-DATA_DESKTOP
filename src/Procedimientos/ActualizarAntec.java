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
public class ActualizarAntec {
    private int idPac;
    private String ant1;
    private String ant2;
    private String ant3;

    public ActualizarAntec() {
    }

    public ActualizarAntec(int idPac, String ant1, String ant2, String ant3) {
        this.idPac = idPac;
        this.ant1 = ant1;
        this.ant2 = ant2;
        this.ant3 = ant3;
    }

    public int getIdPac() {
        return idPac;
    }

    public void setIdPac(int idPac) {
        this.idPac = idPac;
    }

    public String getAnt1() {
        return ant1;
    }

    public void setAnt1(String ant1) {
        this.ant1 = ant1;
    }

    public String getAnt2() {
        return ant2;
    }

    public void setAnt2(String ant2) {
        this.ant2 = ant2;
    }

    public String getAnt3() {
        return ant3;
    }

    public void setAnt3(String ant3) {
        this.ant3 = ant3;
    }
    
    
}
