/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sfalli
 */
public class FXMLDocumentController implements Initializable {
    static Customer inUse;
    
    @FXML
    private Label label0;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    
    @FXML
    private Label incorrectmsg;
    @FXML
    private TextField user;
    @FXML 
    private PasswordField pass;
    
    @FXML
    private Label mstatus;
    @FXML
    private TextField newuser;
    @FXML 
    private PasswordField newpass;
    @FXML
    private TextField deluser;
    
    @FXML
    private Label level;
    @FXML
    private Label balance;
    @FXML
    private Label cstatus;
    @FXML
    private TextField ldeposit;
    @FXML
    private TextField lwithdraw;
    @FXML
    private TextField lpurchase;

    
    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        if (Project.getManager().login(user.getText(),pass.getText())==-1){
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
            Scene mscene = new Scene(root);
            stage.setScene(mscene);
            stage.show();
            Stage nStage = (Stage) label0.getScene().getWindow();
            nStage.close();
        }
        else if (Project.getManager().login(user.getText(),pass.getText())>= 0){
            inUse=(Customer)Project.getManager().getCustomers().get(Project.getManager().login(user.getText(), pass.getText()));
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
            Scene cscene = new Scene(root);
            stage.setScene(cscene);
            stage.show();
            Stage nStage = (Stage) label0.getScene().getWindow();
            nStage.close();
        }
        else {
            incorrectmsg.setText("Username/password incorrect.");
        }

    }
    
    @FXML
    private void addButton(ActionEvent event) throws IOException {
        if(Project.getManager().addCustomer(newuser.getText(), newpass.getText())){
            mstatus.setText("Added User successfully!");
        }
        else{
            mstatus.setText("Failed to add User.");
        }
    }
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException {
        if(Project.getManager().deleteCustomer(deluser.getText())){
            mstatus.setText("Deleted successfully!");
        }
        else{
            mstatus.setText("Failed to delete.");
        }
    }
    
    @FXML
    private void mlogout (ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mscene = new Scene(root);
        stage.setScene(mscene);
        stage.show();
        Stage nStage = (Stage) label1.getScene().getWindow();
        nStage.close();
    }
    
    @FXML
    private void depositButton(ActionEvent event) throws IOException{
        inUse.deposit(Double.parseDouble(ldeposit.getText()));
        //cstatus.setText(" ");
        cstatus.setText("Deposited!");
        balance.setText(""+inUse.getAccount().getMoney());
        level.setText(""+inUse.getLevel());
    }
    
    @FXML
    private void withdrawButton(ActionEvent event) throws IOException{
        if(inUse.withdraw(Double.parseDouble(lwithdraw.getText()))){
            inUse.withdraw(Double.parseDouble(lwithdraw.getText()));
            //cstatus.setText(" ");
            cstatus.setText("Insufficient funds.");
            String b = Double.toString(inUse.getAccount().getMoney());
            balance.setText(b);
            String l = (inUse.getLevel().toString());
            level.setText(l);
        }
        else{
            //cstatus.setText(" ");
            cstatus.setText("Withdrawal Successful!");
            String b = Double.toString(inUse.getAccount().getMoney());
            balance.setText(b);
            String l = (inUse.getLevel().toString());
            level.setText(l);
        }
    }
    
    @FXML
    private void purchaseButton (ActionEvent event) throws IOException{
        if(inUse.onlinePurchase(Double.parseDouble(lpurchase.getText()))){
            inUse.onlinePurchase(Double.parseDouble(lpurchase.getText()));
            //cstatus.setText(" ");
            cstatus.setText("Error");
            String b = Double.toString(inUse.getAccount().getMoney());
            balance.setText(b);
            String l = (inUse.getLevel().toString());
            level.setText(l);
        }
        else{
            //cstatus.setText(" ");
            cstatus.setText("Purchased!");
            String b = Double.toString(inUse.getAccount().getMoney());
            balance.setText(b);
            String l = (inUse.getLevel().toString());
            level.setText(l);
        }
    }
    
    @FXML
    private void clogout (ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mscene = new Scene(root);
        stage.setScene(mscene);
        stage.show();
        Stage nStage = (Stage) label2.getScene().getWindow();
        nStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
