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
public class Platinum extends Level{
    private final double fee = 0;
    
    @Override
    public double getFee() {
        return this.fee;
    }
    
    @Override
    public void changeLevel(Customer c) throws IOException{
        if(c.getAccount().getMoney() < 20000) {
            Level l = new Gold();
            c.setLevel(l);
            c.getLevel().changeLevel(c);          
        }
    }
    
    @Override
    public String toString() {
        return "Platinum";
    }
}