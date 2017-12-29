/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import Procedimientos.ActualizarDoctor;
import Procedimientos.BorrarDoctor;
import Procedimientos.BusquedaDoctor;
import Procedimientos.GuardarDoctor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mendo
 */
public class Conexion {
    static Connection _con; //Variable para conectar tipo connection
    public String _driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // driver de Sql Server
    public String _url = "jdbc:sqlserver://localhost:14330;databaseName=VITADATA";    //url para conectar a la DB local
    static ResultSet _rs;
    static PreparedStatement _ps;    
    static Statement _st;
    static String _user, _pass, _sql;
    //Método para conectar con 2 variables de entrada que son usuario y contraseña
    public Connection getConexion(String _usuario, String _contraseña){        
        try{
            _user = _usuario;
            _pass = _contraseña;            
            Class.forName(_driver); //Mandar a llamar el driver             
            _con=DriverManager.getConnection(_url,_user,_pass); //Hacer la conexión dependiendo del usuario ingresado en la interfz
            System.out.println("Conexión realizada");//debug 3            
        }catch (Exception e){
            System.out.println("Error de conexión: " + e);
        }     
        return _con ;        
    }
    
    public void insertarDoctor(GuardarDoctor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdRegistrarDoctor(?,?,?,?,?,?)}");
            
            _csta.setString(1, dts.getCed());
            _csta.setString(2, dts.getNombre());            
            _csta.setString(3, dts.getTelefono());
            _csta.setString(4,dts.getHospital());
            _csta.setString(5, dts.getUsuario());
            _csta.setString(6, dts.getContraseña());
            
            int _res = _csta.executeUpdate();
            System.out.println("debug");
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void actualizarDoctor(ActualizarDoctor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdActDoc(?,?,?,?)}");
            
            _csta.setString(1, dts.getCed());                        
            _csta.setString(2, dts.getNombre());            
            _csta.setString(3, dts.getTelefono());
            _csta.setString(4, dts.getHospital());            
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void borrarDoctor(BorrarDoctor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdBorrarDoc(?)}");
            
            _csta.setString(1, dts.getCed());                             
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El doctor ya está Inactivo", "Error",1);         
        } 
    }
    
    public void borrarDoctor2(BorrarDoctor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdBorrarDoc2(?)}");
            
            _csta.setString(1, dts.getCed());                             
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El doctor ya está Activo", "Error",1);     
        } 
    }
    
    public DefaultTableModel MostrarDoctor(){
        DefaultTableModel _mod;
        String [] titulos = {"Cedula", "Doctor", "Telefono","Hospital", "Usuario"};
        
        String [] registro = new String [5];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdVerDoctores}");
            _rs = _csta.executeQuery();
            
            while(_rs.next()){
                registro [0] = _rs.getString("_cedula");
                registro [1] = _rs.getString("_nombre");
                registro [2] = _rs.getString("_telefono");
                registro [3] = _rs.getString("_hospital");
                registro [4] = _rs.getString("_usuario");
                
                _mod.addRow(registro);
            }
            _con.close();
            _rs.close();
            return _mod;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel BusquedaDoctor(BusquedaDoctor dts){
        DefaultTableModel _mod;
        String [] titulos = {"Cedula", "Doctor", "Telefono","Hospital", "Usuario"};
        
        String [] registro = new String [5];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            if (!dts.getCedula().equals("")){
                    _sql = "select _cedula, D._nombre, D._telefono, _hospital = I._nombre, _usuario "
                    + "from Doctor D inner join Institucion I on D._hospital = I._idIns "
                            + "where D._cedula like concat('%','" + dts.getCedula()+"','%') ";
            }else{
                if (!dts.getHospital().equals("")){
                    _sql = "select _cedula, D._nombre, D._telefono, _hospital = I._nombre, _usuario "
                    + "from Doctor D inner join Institucion I on D._hospital = I._idIns where I._nombre like concat('%','" + dts.getHospital()+"','%') "; ;
            }else{
                    if (!dts.getNombre().equals("")){
                           _sql = "select _cedula, D._nombre, D._telefono, _hospital = I._nombre, _usuario "
                           + "from Doctor D inner join Institucion I on D._hospital = I._idIns where D._nombre like concat('%','" + dts.getNombre()+"','%') "; ;
                   }
                }
            }            
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("_cedula");
                registro [1] = _rs.getString("_nombre");
                registro [2] = _rs.getString("_telefono");
                registro [3] = _rs.getString("_hospital");
                registro [4] = _rs.getString("_usuario");
                
                _mod.addRow(registro);
            }
            _con.close();
            _rs.close();
            return _mod;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public DefaultComboBoxModel getvalues1(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Institucion";
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                _mod.addElement(_rs.getString("_nombre"));
            }
            _con.close();
            _rs.close();
            }catch (Exception e){
                    System.out.println(e);
            }
        return _mod;
        }
    public DefaultComboBoxModel getvalues2(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
             _sql = "Select * from Paciente";
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                _mod.addElement(_rs.getString("_nombre"));
            }
            _con.close();
            _rs.close();
            }catch (Exception e){
                    System.out.println(e);
            }
        return _mod;
        }
    public DefaultComboBoxModel getvalues3(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Doctor where _estado = 'Activo'";
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                _mod.addElement(_rs.getString("_nombre"));
            }
            _con.close();
            _rs.close();
            }catch (Exception e){
                    System.out.println(e);
            }
        return _mod;
        }
    }

