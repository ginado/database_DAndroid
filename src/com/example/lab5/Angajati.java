package com.example.lab5;

public class Angajati {

	  //private variables
    int _id;
    String _name;
    String _departament;
 
   public Angajati(){}
 
    
    // constructor
    public Angajati(int id, String name, String _departament){
        this._id = id;
        this._name = name;
        this._departament = _departament;
    }
 
    // constructor
    public Angajati(String name, String _departament){
        this._name = name;

        this._departament = _departament;
       
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getNume(){
        return this._name;
    }
 
    // setting name
    public void setNume(String name){
        this._name = name;
    }
    
    //get prenume
    
        
    public String getDepartament(){
        return this._departament;
    }
 
 
    public void setDepartament(String departament){
        this._departament=departament;
    }
}
