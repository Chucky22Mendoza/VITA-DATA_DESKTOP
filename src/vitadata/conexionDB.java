package vitadata;

import java.sql.*;



public class conexionDB {
    private Connection _cn = null;
    Statement _st;
    private String _user = "root";
    private String _pass = "";
    private String _host = "localhost";
    private String _nombreDB = "vita-data";
    
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
}
