/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

/**
 *
 * @author mendo
 */
public class DBConect {
    private String _user;
    private String _password;
    
    public DBConect(){
        
    }

    public DBConect(String _user, String _password) {
        this._user = _user;
        this._password = _password;
    }

    public String getUser() {
        return _user;
    }

    public void setUser(String _user) {
        this._user = _user;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
    
    
}
