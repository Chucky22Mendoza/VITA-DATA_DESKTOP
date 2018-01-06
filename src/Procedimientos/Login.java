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
public class Login {
    private String _user;
    private String _pass;

    public Login() {
    }

    public Login(String _user, String _pass) {
        this._user = _user;
        this._pass = _pass;
    }

    public String getUser() {
        return _user;
    }

    public void setUser(String _user) {
        this._user = _user;
    }

    public String getPass() {
        return _pass;
    }

    public void setPass(String _pass) {
        this._pass = _pass;
    }
}
