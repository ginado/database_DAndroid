package com.example.lab5;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class DataBActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DataB db = new DataB(this);
        
        /**
         * CRUD Operations
         * */
        // Inserare angajati
        Log.d("Insert: ", "Inserting ..");
        db.addAngajat(new Angajati("Laura Balaura ", "USO"));
        db.addAngajat(new Angajati("Vlad Bogdan ","SD"));
        db.addAngajat(new Angajati("Stefan Alexandru", "PR"));
        db.addAngajat(new Angajati("Mihai Andrei", "Gaina"));
       
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Angajati> angajat = db.getTotiAngajatii();       
 
        for (Angajati an : angajat) {
            String log = "Id: "+ an.getID() + " ,Nume: " + an.getNume() +
            		" ,Departament: " + an.getDepartament();
        Log.d("Nume: ", log);
    }
}
  
}
