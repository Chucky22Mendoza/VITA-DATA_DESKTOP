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
public class CambioDoctor {
    private String _pac;
    private String _doc;

    public CambioDoctor() {
    }

    public CambioDoctor(String _pac, String _doc) {
        this._pac = _pac;
        this._doc = _doc;
    }

    public String getPac() {
        return _pac;
    }

    public void setPac(String _pac) {
        this._pac = _pac;
    }

    public String getDoc() {
        return _doc;
    }

    public void setDoc(String _doc) {
        this._doc = _doc;
    }
    
    
}
