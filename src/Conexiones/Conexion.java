/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author mendo
 */
public class Conexion {
    Connection _con; //Variable para conectar tipo connection
    public String _driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // driver de Sql Server
    public String _url = "jdbc:sqlserver://localhost:14330;databaseName=VITADATA";    //url para conectar a la DB local
    
    //Método para conectar con 2 variables de entrada que son usuario y contraseña
    public Connection getConexion(String _usuario, String _contraseña){        
        try{
            System.out.println("Conexión realizada"); //debug 1
            Class.forName(_driver); //Mandar a llamar el driver 
            System.out.println("Conexión realizada2"); //debug 2
            _con=DriverManager.getConnection(_url,_usuario,_contraseña); //Hacer la conexión dependiendo del usuario ingresado en la interfz
            System.out.println("Conexión realizada3");//debug 3
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e); //Error de conexión
        }     
        return _con ;
    }    
}
