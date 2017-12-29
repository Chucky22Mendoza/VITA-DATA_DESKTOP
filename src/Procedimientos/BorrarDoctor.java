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
public class BorrarDoctor {
    private String _ced ;

    public BorrarDoctor(String _ced) {
        this._ced = _ced;
    }
    
    public BorrarDoctor(){
        
    }

    public String getCed() {
        return _ced;
    }

    public void setCed(String _ced) {
        this._ced = _ced;
    }
    
}
