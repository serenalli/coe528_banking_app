/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.util.*;
import java.io.*;

/**
 *
 * @author sfalli
 */
public class Manager {
    private ArrayList<Customer> customers;
    private final String user = "admin";
    private final String pass = "admin";
    private final String role = "manager";
    
    public Manager() {
        this.customers = new ArrayList<Customer>();
    }
    
    public String getUsername() {
        return this.user;
    }
    
    public String getPassword() {
        return this.pass;
    }
    
    public ArrayList getCustomers() {
        return this.customers;
    }
    
    public boolean addCustomer(String user, String pass) throws IOException{
        File file = new File(user + ".txt");
        if(file.createNewFile()) {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(user + "\n" + pass + "\nCustomer\n100.00\nSilver");
            out.close();
            Customer c = new Customer(file);
            this.customers.add(c);
            return true;
            
        }
        else{
            System.out.println("The username " + user + " has been taken. Please pick another username.");
            return false;
        }
    }
    
    public boolean deleteCustomer(String user) throws IOException{
        for(int i = 0; i < this.customers.size(); i++) {
            if(this.customers.get(i).getAccount().getUsername().equals(user)) {
                System.out.println(i);
                if(this.customers.get(i).getAccount().getFile().delete())
                    System.out.println("Customer File deleted.");
                this.customers.remove(this.customers.get(i));
                return true;
            }
        }
        System.out.println("There's no file to be deleted.");
        return false;
    }
    
    public int login(String user, String pass) throws IOException {
        if(this.user.equals(user)&&this.pass.equals(pass)){
            return -1;
        }
        for (int i=0; i<customers.size();i++){
            if(this.customers.get(i).getAccount().getUsername().equals(user)&&this.customers.get(i).getAccount().getPassword().equals(pass)){
                return i;
            }
        }
        return -2;
    }
}