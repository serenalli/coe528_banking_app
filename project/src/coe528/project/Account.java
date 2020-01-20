/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.io.*;
import java.util.*;

/**
 *
 * @author sfalli
 */
public class Account {
    private File file;
    BufferedWriter output;
    BufferedReader input;
    private double money;
    
    public Account(File file) throws IOException {
        this.file = file;
        this.input = new BufferedReader(new FileReader(file));
        
        input.readLine();
        input.readLine();
        input.readLine();        
        this.money = Double.parseDouble(input.readLine());
        input.close();
    }
    
    public double getMoney() {
        return this.money;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void deposit(double amount) throws IOException {
        this.money += amount;
        this.updateAccount();
    }
    
    public void withdraw(double amount) throws IOException {
        this.money -= amount;
        this.updateAccount();
    }
    
    public String getUsername() throws IOException{
        this.input = new BufferedReader(new FileReader(file));
        String u = input.readLine();
        input.close();
        return u;
    }
    
    public String getPassword() throws IOException{
        this.input = new BufferedReader(new FileReader(file));
        input.readLine();
        String p = input.readLine();
        input.close();
        return p;
    }
    
    public String getRole() throws IOException{
        this.input = new BufferedReader(new FileReader(file));
        input.readLine();
        input.readLine();
        String r = input.readLine();
        input.close();
        return r;
    }
    
    
    public void updateAccount() throws IOException{
        this.input = new BufferedReader(new FileReader(file));
        ArrayList<String> up = new ArrayList<String>();
        for(int i = 0; i < 5; i++) {
            up.add(input.readLine());
        }
        this.output = new BufferedWriter(new FileWriter(file));
        up.set(3, "" + this.money);
        for(int i = 0; i < 5; i++) {
            output.write(up.get(i));
            output.newLine();
        }
        input.close();
        output.close();       
    }
}