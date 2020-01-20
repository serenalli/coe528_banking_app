/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.io.*;


/**
 *
 * @author sfalli
 */
public class Gold extends Level {
    /*
    * OVERVIEW: This class is used to provide the purchase Fee for level Gold.
    * When the user's balance exceeds 10000 but is still less than or equal to 
    * 20000, the user's level becomes Gold. The class is immutable.
    *
    * ABSTRACTION FUNCTION: Abstraction will always return the level of the 
    * account. 
    * REP INVARIANT: Nothing is required.
    */
    private final double fee = 10;
    
    @Override
    public double getFee() {
        return this.fee;
    }
    
    @Override
    public void changeLevel(Customer c) throws IOException{
        if(c.getAccount().getMoney() >= 20000) {
            Level l = new Platinum();
            c.setLevel(l);
            c.getLevel().changeLevel(c);          
        }
        else if(c.getAccount().getMoney() < 10000) {
            Level l = new Silver();
            c.setLevel(l);
            c.getLevel().changeLevel(c); 
        }
    }
    
    @Override
    public String toString() {
        return "Gold";
    }
}