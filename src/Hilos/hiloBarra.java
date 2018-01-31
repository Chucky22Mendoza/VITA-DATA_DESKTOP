/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Vistas.LogIn;
/**
 *
 * @author mendo
 */
public class hiloBarra extends Thread{
    public void run(){
        
        for (int i = 0; i<=100; i++){
            LogIn.barra.setValue(i);
            
            try{
                Thread.sleep(25);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
