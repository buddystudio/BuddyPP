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
public class BDDefineVariableWindowModel 
{
    //ObservableList<String> pinList = FXCollections.observableArrayList("A0","A1","A2");
    public ObservableList<String> typeList = FXCollections.observableArrayList();
    
    public BDDefineVariableWindowModel()
    {
        typeList.add("int");
        typeList.add("unsigned int");
        typeList.add("float");
        typeList.add("double");
        typeList.add("long");
        typeList.add("unsigned long");
        typeList.add("short");
        typeList.add("boolean");
        typeList.add("char");
        typeList.add("unsigned char");
        typeList.add("byte");
        typeList.add("word");
        typeList.add("string");
        typeList.add("String");
        //typeList.add("array");
        //typeList.add("void"); 
    }
}
