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
public class mosPacientes {
    private String nombre;
    private int edad;
    private String nacimiento;
    private float IMC;
    private int id;

    public mosPacientes(String nombre, int edad, String nacimiento, float IMC, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacimiento = nacimiento;
        this.IMC = IMC;
        this.id = id;
    }

    public mosPacientes() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
