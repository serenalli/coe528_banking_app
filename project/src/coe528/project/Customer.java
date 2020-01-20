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
public class Customer {
    private Level lvl;
    private Account acct;
    BufferedWriter output;
    BufferedReader input;
    
    public Customer(File file) throws IOException{
        this.acct = new Account(file);
        this.lvl = new Silver();
    }
    
    public Level getLevel() {
        return this.lvl;
    }
    
    public void setLevel(Level lvl) throws IOException{
        this.input = new BufferedReader(new FileReader(this.acct.getFile()));
        this.lvl = lvl;
        ArrayList<String> up = new ArrayList<String>();
        for(int i = 0; i < 5; i++) {
            up.add(input.readLine());
        }
        this.output = new BufferedWriter(new FileWriter(this.acct.getFile()));
        up.set(4, "" + this.lvl);
        for(int i = 0; i < 5; i++) {
            output.write(up.get(i));
            output.newLine();
        }
        input.close();
        output.close();     
    }
    
    public double getFee() {
        return this.lvl.getFee();
    }
    
    public void changeLevel() throws IOException{
        this.lvl.changeLevel(this);
    }
    
    
    public Account getAccount() {
        return this.acct;
    }
   
    public void deposit(double amount) throws IOException{
        this.acct.deposit(amount);
        this.changeLevel();     
    }
    
    public boolean withdraw(double amount) throws IOException{
        if(amount > this.acct.getMoney()) {
            System.out.println("Insufficient funds");
            return true;
        }
        this.acct.withdraw(amount);
        this.changeLevel(); 
        return false;
    }
    
    public boolean onlinePurchase(double amount) throws IOException{
        if((amount + this.getFee()) > this.acct.getMoney()) {
            System.out.println("Insufficient funds");
            return true;
        }
        else if(amount < 50) {
            System.out.println("Online purchases must be $50 or more");
            return true;
        }
        this.acct.withdraw(amount + this.getFee());
        this.changeLevel();
        return false;
    }
    
    public int login (String user, String pass) throws IOException{
        for (int i=0; i<Project.getManager().getCustomers().size();i++){
            Customer c = (Customer)Project.getManager().getCustomers().get(i);
            if(c.getAccount().getUsername().equals(user)&&c.getAccount().getPassword().equals(pass)){
                return 1;
            }
        }
        return 0;
    }
}