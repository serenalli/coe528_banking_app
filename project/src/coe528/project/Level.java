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
public abstract class Level {
    
    public abstract double getFee();
    
    public abstract void changeLevel(Customer c) throws IOException;
    
    public abstract String toString();
}
