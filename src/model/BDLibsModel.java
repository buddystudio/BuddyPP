/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author gsh
 */
public class BDLibsModel 
{
    public String libName = "";
    public ObservableList<String> libsList = FXCollections.observableArrayList();
    
    public BDLibsModel(String name)
    {
        libName = name;
    }
}
