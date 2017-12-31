/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import Procedimientos.ActualizarDoctor;
import Procedimientos.BorrarDoctor;
import Procedimientos.BorrarHospital;
import Procedimientos.BusquedaDoctor;
import Procedimientos.BusquedaHospital;
import Procedimientos.CambioDoctor;
import Procedimientos.GuardarDoctor;
import Procedimientos.GuardarHospital;
import Procedimientos.HistorialAtencion;
import Procedimientos.RegistrarCita;
import Procedimientos.mostrarDoc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;

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
        }catch (Exception e){
            System.out.println("Error de conexión: " + e);
        }     
        return _con ;        
    }
    //Inicia parte de Registro de Doctores
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
            
            _csta.executeUpdate();
            
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
            
            System.out.println("debug");
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
            JOptionPane.showMessageDialog(null, "El doctor ya está Inactivo", "Error",2);         
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
            JOptionPane.showMessageDialog(null, "El doctor ya está Activo", "Error",2);     
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
    //Termina parte de Registro de Doctores
    
    
    //Uso de Combo box
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
    
    public DefaultComboBoxModel getvalues4(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Doctor";
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
    
    //Uso de Combo box
    
    
    //Inicio parte de registro de Instituciones
    public void insertarHospital(GuardarHospital dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdRegistrarHospital(?,?,?,?,?,?,?)}");
            
            _csta.setString(1, dts.getNombre());
            _csta.setString(2, dts.getCalle());            
            _csta.setString(3, dts.getColonia());
            _csta.setString(4,dts.getCp());
            _csta.setString(5, dts.getCiudad());
            _csta.setString(6, dts.getTelefono());
            _csta.setString(7, dts.getEstado());
            
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public DefaultTableModel MostrarHospital(){
        DefaultTableModel _mod;
        String [] titulos = {"Nombre", "Calle", "Colonia","Código Postal","Ciudad", "Estado","Teléfono"};
        
        String [] registro = new String [7];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdMostrarInstituciones}");
            _rs = _csta.executeQuery();
            
            while(_rs.next()){
                registro [0] = _rs.getString("_nombre");
                registro [1] = _rs.getString("_calle");
                registro [2] = _rs.getString("_colonia");
                registro [3] = _rs.getString("_codigoPostal");
                registro [4] = _rs.getString("_ciudad");
                registro [5] = _rs.getString("_estado");
                registro [6] = _rs.getString("_telefono");
                
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
    
    public void modificarHospital(GuardarHospital dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdModificarHospital(?,?,?,?,?,?,?)}");
            
            _csta.setString(1, dts.getNombre());
            _csta.setString(2, dts.getCalle());            
            _csta.setString(3, dts.getColonia());
            _csta.setString(4,dts.getCp());
            _csta.setString(5, dts.getCiudad());
            _csta.setString(6, dts.getTelefono());
            _csta.setString(7, dts.getEstado());
            
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void borrarHospital(BorrarHospital dts){
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdBorrarHospital(?)}");
            
            _csta.setString(1,dts.getNombre());
            
            int _res = _csta.executeUpdate();      
            System.out.println(_res);
            
            if (_res == -1){
                JOptionPane.showMessageDialog(null, "Esta Institución aún cuenta con Doctores registrados", "Error", 2);
            }
            _con.close();
            _rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Esta Institución aún cuenta con Doctores registrados", "Error", 2);
        }
    }
    
    public DefaultTableModel busquedaHospital(BusquedaHospital dts){
        DefaultTableModel _mod;
        String [] titulos = {"Nombre", "Calle", "Colonia","Código Postal", "Ciudad", "Estado", "Teléfono"};
        
        String [] registro = new String [7];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            if (!dts.getNombre().equals("")){
                    _sql = "select _nombre, _calle, _colonia, _codigoPostal, _ciudad, _estado, _telefono "
                            + "from Institucion "
                            + "where _nombre like concat('%','" + dts.getNombre()+"','%') ";
            }else{
                if (!dts.getCiudad().equals("")){
                    _sql = "select _nombre, _calle, _colonia, _codigoPostal, _ciudad, _estado, _telefono "
                            + "from Institucion "
                            + "where _ciudad like concat('%','" + dts.getCiudad()+"','%') ";
                }else{
                    if (!dts.getEstado().equals("")){
                           _sql = "select _nombre, _calle, _colonia, _codigoPostal, _ciudad, _estado, _telefono "
                            + "from Institucion "
                            + "where _estado like concat('%','" + dts.getEstado()+"','%') ";
                      }else{
                           if (!dts.getCp().equals("")){
                                _sql = "select _nombre, _calle, _colonia, _codigoPostal, _ciudad, _estado, _telefono "
                                + "from Institucion "
                                + "where _codigoPostal like concat('%','" + dts.getCp()+"','%') ";
                          }
                      }
                }
            }            
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("_nombre");
                registro [1] = _rs.getString("_calle");
                registro [2] = _rs.getString("_colonia");
                registro [3] = _rs.getString("_codigoPostal");
                registro [4] = _rs.getString("_ciudad");
                registro [5] = _rs.getString("_estado");
                registro [6] = _rs.getString("_telefono");
                
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
    
    //Termina parte de registro de Instituciones
    
    
    //Iniciar parte de registro de citas
    public void registroCita(RegistrarCita dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdRegistrarCita(?,?,?)}");
            
            _csta.setString(1, dts.getPaciente());
            _csta.setString(2, dts.getDoctor());            
            _csta.setString(3, dts.getFecha());                        
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cambioDoctor(CambioDoctor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdActualizarDoctor(?,?)}");
            
            _csta.setString(1, dts.getPac());
            _csta.setString(2, dts.getDoc());                        
            
            int _res = _csta.executeUpdate();
            System.out.println(_res);
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public ArrayList<String> mosDoctor(mostrarDoc dts){
        ArrayList <String> registro  = new ArrayList<String>();
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarDoc(?)}");

            _csta.setString(1, dts.getDoc());                        
            
            _rs = _csta.executeQuery();          
            
            while(_rs.next()){
                registro.add(0,_rs.getString("_nombre")); 
                registro.add(1, _rs.getString("_cedula")); 
                registro.add(2, _rs.getString("_telefono")); 
                registro.add(3, _rs.getString("Hospital")); 
                registro.add(4, _rs.getString("_ciudad")); 
                registro.add(5, _rs.getString("_estado")); 
                registro.add(6, _rs.getString("Telefono"));                 
            }
            
            _con.close();
            _rs.close();                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return registro;
    }
    //Termina parte de registro de citas
    
    //Inicia parte de Historial de Atención
    public DefaultTableModel HistorialAtencion(){
        DefaultTableModel _mod;
        String [] titulos = {"Paciente", "Doctor", "Fecha de Atención"};
        
        String [] registro = new String [3];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdMostrarHistorial}");
            _rs = _csta.executeQuery();
            
            while(_rs.next()){
                registro [0] = _rs.getString("Nombre");
                registro [1] = _rs.getString("Doctor");
                registro [2] = _rs.getString("FechaAtencion");                
                
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
    
    public DefaultTableModel busquedaHistorial(HistorialAtencion dts){
        DefaultTableModel _mod;
        String [] titulos = {"Paciente", "Doctor", "Fecha de Atención"};
        
        String [] registro = new String [3];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            if (!dts.getPaciente().equals("")){
                    _sql = "select Nombre, Doctor, FechaAtencion "
                            + "from HistorialAtencion "
                            + "where Nombre = '" + dts.getPaciente()+"'";
            }else{
                if (!dts.getDoctor().equals("")){
                    _sql = "select Nombre, Doctor, FechaAtencion "
                            + "from HistorialAtencion "
                            + "where Doctor = '" + dts.getDoctor()+"'";
                }else{
                    if (dts.getMes() != 0 && dts.getAño() != 0){
                           _sql = "select Nombre, Doctor, FechaAtencion "
                            + "from HistorialAtencion "
                            + "where Month(FechaAtencion) = " + dts.getMes() +" and Year(FechaAtencion) = " + dts.getAño();
                      }else{
                           if (dts.getAño() != 0){
                                _sql = "select Nombre, Doctor, FechaAtencion "
                            + "from HistorialAtencion "
                            + "where Year(FechaAtencion) = " + dts.getAño();
                          }else{
                               if (!dts.getFecha().equals("")){
                                _sql = "select Nombre, Doctor, FechaAtencion "
                            + "from HistorialAtencion "
                            + "where FechaAtencion = '" + dts.getFecha() + "'";
                          }
                           }
                      }
                }
            }            
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("Nombre");
                registro [1] = _rs.getString("Doctor");
                registro [2] = _rs.getString("FechaAtencion");                
                
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
    //Termina parte de Historial de Atención
    
    //Inicia parte de  Pacientes    
    public DefaultTableModel verPacientes(){
        DefaultTableModel _mod;
        String [] titulos = {"Paciente", "Edad", "Fecha de Nacimiento","Doctor", "Tutor", "Número de pulsera"};
        
        String [] registro = new String [6];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            _sql = "Select Paciente = P._nombre, _edad, _fechaNacimiento, Doctor = D._nombre, Tutor = R._nombre, Pul._idPulsera as 'NumeroPulsera'\n" +
                       " from  Paciente P inner join Doctor D on P._idDoctor = D._idDoctor \n" +
                       " inner join Receptor R on P._idReceptor = R._idReceptor inner join Pulsera Pul on P._idPulsera = Pul._idPulsera";
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("Paciente");
                registro [1] = _rs.getString("_edad");
                registro [2] = _rs.getString("_fechaNacimiento");
                registro [3] = _rs.getString("Doctor");
                registro [4] = _rs.getString("Tutor");
                registro [5] = _rs.getString("NumeroPulsera");
                
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
    //Termina parte de Pacientes
    }

