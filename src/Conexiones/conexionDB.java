package conexiones;

import Procedimientos.GuardarDoctorMysql;
import java.sql.*;



public class conexionDB {
    private Connection _cn = null;
    Statement _st;
    private String _user = "root";
    private String _pass = "";
    private String _host = "localhost";
    private String _nombreDB = "vitadata";
    
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance( );
            String _servidor = "jdbc:mysql://" + _host + "/" + _nombreDB;
            _cn = DriverManager.getConnection(_servidor,_user,_pass);
            return _cn;
        }catch(Exception e){
            e.printStackTrace();
            return _cn;
        }        
    }    
    
    Statement createStatement(){
        throw new UnsupportedOperationException("No soportado");
    }
    
    public Connection closeConexion(){
        try {
            _cn = this.conexion();
            _cn.close();
            _st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void insertarDoctor(GuardarDoctorMysql dts2){
        try{                        
            _cn = this.conexion();
            PreparedStatement _pps = _cn.prepareStatement("INSERT INTO doctores(Nombre, cedulaProfesional, usuario, clave) VALUES(?,?,?,?);");
            
            
            _pps.setString(1, dts2.getNombre());            
            _pps.setString(2, dts2.getCed());                        
            _pps.setString(3, dts2.getUsuario());
            _pps.setString(4, dts2.getContraseña());
            
            _pps.executeUpdate();
            
            _cn.close();            
        }catch (SQLException ex){
            System.out.println("error mysql:" + ex);
        }
    }
}


