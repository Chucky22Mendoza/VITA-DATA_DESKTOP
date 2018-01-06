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
public class DetallePadecimiento {
    private String paciente;
    private String padecimiento;

    public DetallePadecimiento() {
    }

    public DetallePadecimiento(String paciente, String padecimiento) {
        this.paciente = paciente;
        this.padecimiento = padecimiento;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }
    
    
}
