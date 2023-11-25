package com.ds.mySQLLib;

import javax.swing.*;

public class ShowExceptions {
    public void show(Exception e){
        JOptionPane.showMessageDialog(null, e.toString(), "Exception has occurred", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        System.err.println("Something went wrong");
    }
}
