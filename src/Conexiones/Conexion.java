/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import Procedimientos.ActualizarAntec;
import Procedimientos.ActualizarDoctor;
import Procedimientos.ActualizarPaciente;
import Procedimientos.ActualizarReceptor;
import Procedimientos.Antecedentes;
import Procedimientos.BorrarDoctor;
import Procedimientos.BorrarHospital;
import Procedimientos.BusquedaDoctor;
import Procedimientos.BusquedaHistorial;
import Procedimientos.BusquedaHospital;
import Procedimientos.BusquedaPaciente;
import Procedimientos.CambioDoctor;
import Procedimientos.GuardarDoctor;
import Procedimientos.GuardarHospital;
import Procedimientos.GuardarPaciente;
import Procedimientos.HistorialAtencion;
import Procedimientos.Login;
import Procedimientos.DetalleMedQui;
import Procedimientos.DetallePadecimiento;
import Procedimientos.MedicoQuirurgico;
import Procedimientos.NuevoMedQui;
import Procedimientos.Paciente;
import Procedimientos.Padecimiento;
import Procedimientos.RegistrarCita;
import Procedimientos.idPaciente;
import Procedimientos.idPulsera;
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


public class Conexion {
    static Connection _con; //Variable para conectar tipo connection
    public String _driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // driver de Sql Server
    public String _url = "jdbc:sqlserver://localhost:14330;databaseName=VITADATA";    //url para conectar a la DB local
    static ResultSet _rs, _rs2;
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
    
    public int conectarUsuario(Login dts){
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Doctor where _usuario = '" + dts.getUser() + "' "
                    + "and _contraseña = '" + dts.getPass() + "' "
                    + "and _estado = 'Activo' ";
            
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);                        
                                    
            _con.close();
            _rs.close();            

            return 1;            
                        
            }catch (Exception e){
                    System.out.println(e);
                    return 0;
            }                
    }
    
    public Connection closeConexion(){
        try {                        
            _con.close();
            _rs.close();                        
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
//Inicia Vista Secretaria
    
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
    
    public DefaultComboBoxModel getvalues5(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Padecimiento";
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
    
    public DefaultComboBoxModel getvalues6(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Paciente P inner join Doctor D on P._idDoctor = D._idDoctor where D._usuario = user";
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
    
    public DefaultComboBoxModel getvalues7(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "select * from MedicoQuirurgico";
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                _mod.addElement(_rs.getString("_tipoOperacion"));
            }
            _con.close();
            _rs.close();
            }catch (Exception e){
                    System.out.println(e);
            }
        return _mod;
        }
    
    public DefaultComboBoxModel getvalues8(){
        DefaultComboBoxModel _mod = new DefaultComboBoxModel();
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "Select P._nombre from Paciente P inner join PacienteDoctor PD " +
                    "on P._idPaciente = PD._idPaciente inner join Doctor D " +
                        "on PD._idDoctor = D._idDoctor " +
                            "where D._usuario = USER and P._estado = 'Activo'";
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
    
    public void mostrarPacientes(JComboBox<Paciente> cbPaciente1){
        try {            
            _con = this.getConexion(_user, _pass);
            _sql = "Select * from Paciente where _estado = 'Activo' ";
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                cbPaciente1.addItem(
                        new Paciente(
                            _rs.getString("_nombre"),
                            _rs.getInt("_idPaciente")                        
                        )
                );
            }
            _con.close();
            _rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void mostrarPacientes2(JComboBox<Paciente> cbBuscarPaciente){
        try {            
            _con = this.getConexion(_user, _pass);
            _sql = "Select P._nombre, P._idPaciente from Paciente P inner join Doctor D " +
                        "on P._idDoctor = D._idDoctor " +
                            "where D._usuario = USER and P._estado = 'Activo'";
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            while(_rs.next()){
                cbBuscarPaciente.addItem(
                        new Paciente(
                            _rs.getString("_nombre"),
                            _rs.getInt("_idPaciente")                        
                        )
                );
            }
            _con.close();
            _rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
            
            
            _csta.executeUpdate();            
            
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
            
            
            _csta.executeUpdate();            
            
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
    
//Fin de Vista Secretaria
    
//------------------------------------------------------------------------------------------------------
//Inicio de Vista de Doctor
    
    //Inicia parte de Pacientes
    public void insertarPaciente(GuardarPaciente dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdRegistrarPaciente(?,?,?,?,?,?,?,?)}");
            
            _csta.setString(1, dts.getNombre());
            _csta.setInt(2, dts.getEdad());
            _csta.setString(3, dts.getNacimiento());            
            _csta.setFloat(4, dts.getPeso());
            _csta.setFloat(5,dts.getEstatura());
            _csta.setString(6, dts.getNomTutor());
            _csta.setString(7, dts.getTel());            
            _csta.setString(8, dts.getPadecimiento());
            
            _csta.executeUpdate();
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insertarAnt(Antecedentes dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdAntecedentes(?,?,?)}");
            
            _csta.setString(1, dts.getAnt1());            
            _csta.setString(2, dts.getAnt2());            
            _csta.setString(3, dts.getAnt3());            
            
            _csta.executeUpdate();
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    public void insertarDetalle(DetalleMedQui dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdDetalleAnt(?,?)}");
            
            _csta.setString(1, dts.getFecha());            
            _csta.setString(2, dts.getOperacion());                              
            
            _csta.executeUpdate();
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insertarOpe(NuevoMedQui dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdAntMedQui(?)}");
                         
            _csta.setString(1, dts.getOpe());                              
            
            _csta.executeUpdate();
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }    
    
    public DefaultTableModel verPacientes2(){
        DefaultTableModel _mod;
        String [] titulos = {"idPaciente", "Nombre", "Edad", "Fecha de Nacimiento","IMC"};
        
        String [] registro = new String [5];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            _sql = "Select _idPaciente, P._nombre, _edad, _fechaNacimiento, _IMC from Paciente P inner join Doctor D"
                    + " on P._idDoctor = D._idDoctor where _usuario = USER";
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("_idPaciente");       
                registro [1] = _rs.getString("_nombre");
                registro [2] = _rs.getString("_edad");
                registro [3] = _rs.getString("_fechaNacimiento");
                registro [4] = _rs.getString("_IMC");                                                                         
                
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
    
    public String nom;
    public String paciente(idPulsera dts){
        try{
            _con = this.getConexion(_user, _pass);
            _sql = "select _nombre from Paciente where _idPulsera = " + dts.getIdPulsera();
                        
            _st = _con.createStatement();            
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                nom = _rs.getString("_nombre");
            }            
            
            _con.close();
            _rs.close();
            
            return nom;                        
        }catch(Exception e){
            System.out.println(e);
            return null;
        }        
    }
    
    public void actualizarPaciente(ActualizarPaciente dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdActualizarPaciente(?,?,?,?,?,?)}");
            
            _csta.setInt(1, dts.getId());
            _csta.setString(2, dts.getNombre());
            _csta.setInt(3, dts.getEdad());
            _csta.setString(4, dts.getFec());            
            _csta.setFloat(5, dts.getPeso());
            _csta.setFloat(6, dts.getEstatura());
            
            
            _csta.executeUpdate();
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void borrarPaciente1(idPaciente dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdBorrarPac1(?)}");
            
            _csta.setInt(1, dts.getIdPac());                             
            
            _csta.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El Paciente ya está Inactivo ", "Error",2);         
        } 
    }
    
    public void borrarPaciente2(idPaciente dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdBorrarPac2(?)}");
            
            _csta.setInt(1, dts.getIdPac());                                                      
            
            _csta.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El Paciente ya está Activo ", "Error",2);     
        } 
    }
    
    public DefaultTableModel busquedaPaciente(BusquedaPaciente dts){
        DefaultTableModel _mod;
        String [] titulos = {"idPaciente", "Nombre", "Edad", "Fecha de Nacimiento","IMC"};
        
        String [] registro = new String [5];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            if (!dts.getNombre().equals("")){
                    _sql = "Select _idPaciente, _nombre, _edad, _fechaNacimiento, _IMC "
                            + "from Paciente where _nombre like concat('%', '" + dts.getNombre() + "' ,'%')" ;
            }else{
                if (dts.getEdad() != 0){
                    _sql = "Select _idPaciente, _nombre, _edad, _fechaNacimiento, _IMC "
                            + "from Paciente where _edad =  " + dts.getEdad() ;
                }else{
                    if (dts.getMes() != 0 && dts.getAño() != 0){
                            _sql = "Select _idPaciente, _nombre, _edad, _fechaNacimiento, _IMC "
                            + "from Paciente where Month(_fechaNacimiento) = " + dts.getMes() +" and Year(_fechaNacimiento) = " + dts.getAño();
                      }else{
                           if (dts.getAño() != 0){
                                 _sql = "Select _idPaciente, _nombre, _edad, _fechaNacimiento, _IMC "
                                 + "from Paciente where Year(_fechaNacimiento) = " + dts.getAño();
                                 System.out.println(dts.getAño());
                          }else{
                               if (!dts.getFecha().equals("")){
                                _sql = "Select _idPaciente, _nombre, _edad, _fechaNacimiento, _IMC "
                                 + "from Paciente where _fechaNacimiento = '" + dts.getFecha() + "'";
                          }
                           }
                      }
                }
            }            
            
            _st = _con.createStatement();            
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("_idPaciente");       
                registro [1] = _rs.getString("_nombre");
                registro [2] = _rs.getString("_edad");
                registro [3] = _rs.getString("_fechaNacimiento");
                registro [4] = _rs.getString("_IMC");                 
                
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
    
    //Inicia parte de Información de Paciente
    public ArrayList<String> mostrarReceptor(idPaciente dts){
        ArrayList <String> registro  = new ArrayList<String>();
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarReceptor(?)}");

            _csta.setInt(1, dts.getIdPac());                        
            
            _rs = _csta.executeQuery();          
            
            while(_rs.next()){
                registro.add(0,_rs.getString("_nombre")); 
                registro.add(1, _rs.getString("_numTelefono"));                              
            }
            
            _con.close();
            _rs.close();                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return registro;
    }
    
    public void actReceptor(ActualizarReceptor dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdActualizarReceptor(?,?,?)}");
            
            _csta.setInt(1, dts.getIdPac());                             
            _csta.setString(2, dts.getNombre());            
            _csta.setString(3, dts.getTel());
            
           _csta.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar", "Error",2);         
        } 
    }
    
    public ArrayList<String> mostrarDoc(idPaciente dts){
        ArrayList <String> registro  = new ArrayList<String>();
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarDoctor(?)}");

            _csta.setInt(1, dts.getIdPac());                        
            
            _rs = _csta.executeQuery();          
            
            while(_rs.next()){
                registro.add(0,_rs.getString("Doctor")); 
                registro.add(1, _rs.getString("_telefono"));                              
                registro.add(2, _rs.getString("_usuario"));  
                registro.add(3, _rs.getString("_nombre"));  
            }
            
            _con.close();
            _rs.close();                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return registro;
    }
    
    public ArrayList<String> mostrarAnt(idPaciente dts){
        ArrayList <String> registro  = new ArrayList<String>();
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarAntecedentes(?)}");

            _csta.setInt(1, dts.getIdPac());                        
            
            _rs = _csta.executeQuery();          
            
            while(_rs.next()){
                registro.add(0,_rs.getString("_persPatologicos")); 
                registro.add(1, _rs.getString("_familiares"));                              
                registro.add(2, _rs.getString("_noPatologicos"));  
                registro.add(3, _rs.getString("_tipoOperacion"));  
                registro.add(4, _rs.getString("_fechaOperacion"));  
            }
            
            _con.close();
            _rs.close();                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return registro;
    }
    
    public ArrayList<String> mostrarPulsera(idPaciente dts){
        ArrayList <String> registro  = new ArrayList<String>();
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdPulseraPac(?)}");

            _csta.setInt(1, dts.getIdPac());                        
            
            _rs = _csta.executeQuery();          
            
            while(_rs.next()){
                registro.add(0,_rs.getString("Numero de Serie")); 
            }
            
            _con.close();
            _rs.close();                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return registro;
    }    
    
    public void actAntec(ActualizarAntec dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdActualizarAntecedentes(?,?,?,?)}");
            
            _csta.setInt(1, dts.getIdPac());                             
            _csta.setString(2, dts.getAnt1());            
            _csta.setString(3, dts.getAnt2());
            _csta.setString(4, dts.getAnt3());
            
           _csta.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar ", "Error",2);         
        } 
    }
    
    public void agregarMQ(MedicoQuirurgico dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdAgregarMQ(?,?,?)}");
            
            _csta.setInt(1, dts.getIdPac());                             
            _csta.setString(2, dts.getOpe());            
            _csta.setString(3, dts.getFecha());            
            
           _csta.executeUpdate();                        
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo Agregar  el tipo de operación" + e, "Error",2);         
        } 
    }
    //Termina parte de Información de Paciente
    
    //Inicia parte de Monitoreo de Paciente
    public DefaultTableModel verMonitoreo(){
        DefaultTableModel _mod;
        String [] titulos = {"Pulsera", "Temperatura", "Oxígeno en la Sangre", "Frecuencia Cardíaca","Fecha y Hora", "Alerta"};
        
        String [] registro = new String [6];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            
            _sql = "select * from Datos";
            
            _st = _con.createStatement();
            _rs = _st.executeQuery(_sql);
            
            while(_rs.next()){
                registro [0] = _rs.getString("_idPulsera");
                registro [1] = _rs.getString("_temperatura");
                registro [2] = _rs.getString("_oxigenoSangre");
                registro [3] = _rs.getString("_frecuenciaCardiaca");
                registro [4] = _rs.getString("_fechaHora");                                                
                registro [5] = _rs.getString("_estado");                                             
                
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
    
    public DefaultTableModel BuscarMonitoreo(idPaciente dts){
        DefaultTableModel _mod;
        String [] titulos = {"Pulsera", "Temperatura", "Oxígeno en la Sangre", "Frecuencia Cardíaca","Fecha y Hora", "Alerta"};
        
        String [] registro = new String [6];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarDatos(?)}");

            _csta.setInt(1, dts.getIdPac());                        
            
            _rs = _csta.executeQuery();  
            
            while(_rs.next()){
                registro [0] = _rs.getString("_idPulsera");
                registro [1] = _rs.getString("_temperatura");
                registro [2] = _rs.getString("_oxigenoSangre");
                registro [3] = _rs.getString("_frecuenciaCardiaca");
                registro [4] = _rs.getString("_fechaHora");                                                
                registro [5] = _rs.getString("_estado");                                                
                
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
    
    
    //Termina parte de Monitoreo de Paciente
    
    //Inicia parte de Historial de Atención
    public DefaultTableModel mosHistorial(){
        DefaultTableModel _mod;
        String [] titulos = {"Paciente", "Fecha de atención"};
        
        String [] registro = new String [2];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta=_con.prepareCall("{call procdMostrarHistorialDoc}");
            
            _rs = _csta.executeQuery();  
            
            while(_rs.next()){
                registro [0] = _rs.getString("Nombre");                
                registro [1] = _rs.getString("FechaAtencion");                
                
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
    
    public DefaultTableModel busHistorialDoc(BusquedaHistorial dts){
        DefaultTableModel _mod;
        String [] titulos = {"Paciente", "Fecha de atención"};
        
        String [] registro = new String [2];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {            
            _con = this.getConexion(_user, _pass);
            
            if (!dts.getNombre().equals("")){
                _sql = "select Nombre, FechaAtencion "
                        + "from HistorialAtencion HA inner join Doctor D on D._nombre = HA.Doctor "
                            + "where _usuario = USER "                    
                                + "and HA.Nombre = '" + dts.getNombre() + "'";
            }else{                
                    if (dts.getMes() != 0 && dts.getAño() != 0){
                            _sql = "select Nombre, FechaAtencion "
                                 + "from HistorialAtencion HA inner join Doctor D on D._nombre = HA.Doctor "
                                    + "where _usuario = USER and Month(FechaAtencion) = " 
                                        + dts.getMes() +" and Year(FechaAtencion) = " + dts.getAño();
                      }else{
                           if (dts.getAño() != 0){
                                 _sql = "select Nombre, FechaAtencion "
                                     + "from HistorialAtencion HA inner join Doctor D on D._nombre = HA.Doctor "
                                        + "where _usuario = USER and Year(FechaAtencion) = " + dts.getAño();                                 
                          }else{
                               if (!dts.getFecha().equals("")){
                                    _sql = "select Nombre, FechaAtencion "
                                         + "from HistorialAtencion HA inner join Doctor D on D._nombre = HA.Doctor "
                                            + "where _usuario = USER and FechaAtencion = '" + dts.getFecha() + "'";
                              }                           
                      }
                }
            }            
            
            _st = _con.createStatement();            
            _rs = _st.executeQuery(_sql);
                                                
            while(_rs.next()){
                registro [0] = _rs.getString("Nombre");                
                registro [1] = _rs.getString("FechaAtencion");                
                
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
    
    //Inicia parte de Padecimiento
    public void insertarPad(Padecimiento dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdInsertarPad(?)}");
            
            _csta.setString(1, dts.getPad());                                                     
            
           int _res = _csta.executeUpdate();
            System.out.println(_res);
            JOptionPane.showMessageDialog(null, "Se ha Agregado correctamente", "Completado",1);                                                            
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo Agregar", "Error",2);         
        } 
    }
    
    public void insertarDetPad(DetallePadecimiento dts){
        try{
            _con = this.getConexion(_user, _pass);
            CallableStatement _csta = _con.prepareCall("{call procdPadecimientos(?,?)}");
            
            _csta.setString(1, dts.getPaciente());                                                     
            _csta.setString(2, dts.getPadecimiento());
            
           _csta.executeUpdate();           
            JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);                                                            
            
            _con.close();
            _rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar" + e, "Error",2);         
        } 
    }
    
    public DefaultTableModel Padecimiento(idPaciente dts){
        DefaultTableModel _mod;
        String [] titulos = {"Padecimiento"};
        
        String [] registro = new String [1];
        
        _mod = new DefaultTableModel(null,titulos);
        
        try {            
            _con = this.getConexion(_user, _pass);
            
            
                _sql = "select Pad._nombre " +
                    "from Paciente P inner join PacientePadecimiento PP " +
                        "on P._idPaciente = PP._idPaciente inner join Padecimiento Pad " +
                            "on PP._idPadecimiento = Pad._idPadecimiento where P._idPaciente = " + dts.getIdPac() ;
                        
            _st = _con.createStatement();            
            _rs = _st.executeQuery(_sql);
                                                
            while(_rs.next()){
                registro [0] = _rs.getString("_nombre");                                
                
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
    //Termina parte de Padecimiento
//Fin de Vista Doctor
    }

