/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.DBManipulation;
import java.sql.SQLException;

import view.LoginForm;

/**
 *
 * @author Nizar4790k
 */
public class Main {
    public static void main(String [] args){
        new LoginForm().setVisible(true);
        try {
            DBManipulation.testConnection();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}