package com.example.lab5;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataB extends SQLiteOpenHelper {
	
	// Versiune BD
    private static final int VERSIUNE_BD = 1;

    // Nume BD
    private static final String NUME_BD = "Angajati_Firma";

    // Tabela Angajati
    private static final String TABELA_ANGAJATI = "angajati";

    // coloanele tabelei Angajati
    private static final String ID_KEY = "id";
    private static final String NUME_KEY = "nume";
	private static final String DEPARTAMENT_KEY = "departament";


	public DataB(Context context) {
	    	super(context, NUME_BD, null, VERSIUNE_BD);
	    }

	    //Crearea bazei de date
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	    	String CREATE_EMPLOYERS_TABLE = "CREATE TABLE " + TABELA_ANGAJATI + "("
	                + ID_KEY + " INTEGER PRIMARY KEY, " + NUME_KEY + " TEXT,"
	              + DEPARTAMENT_KEY + " TEXT" + ")";
	        db.execSQL(CREATE_EMPLOYERS_TABLE);
	 }
	    

	    // Actualizarea bazei de date
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    	 db.execSQL("DROP TABLE IF EXISTS " + TABELA_ANGAJATI);
	    	 
	         // Create tables again
	         onCreate(db);

	    }
	    
	    /**
	     * All CRUD(Create, Read, Update, Delete) Operations
	     */
	 
	    // Adaugare angajat
	    void addAngajat(Angajati angajat) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues valori = new ContentValues();
	        valori.put(NUME_KEY, angajat.getNume()); // Nume Angajat
	        valori.put(DEPARTAMENT_KEY, angajat.getDepartament()); // Departament angajat
	 
	        // Inserare linie
	        db.insert(TABELA_ANGAJATI, null, valori);
	        db.close(); // Closing database connection
	    }
	 
	    // Returneaza angajat
	    Angajati getAngajat(int id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	 
	        Cursor cursor = db.query(TABELA_ANGAJATI, new String[] { ID_KEY,
	                NUME_KEY, DEPARTAMENT_KEY  }, ID_KEY + "=?",
	                new String[] { String.valueOf(id) }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	 
	       Angajati angajat = new Angajati(Integer.parseInt(cursor.getString(0)),
	                cursor.getString(1), cursor.getString(2));
	        // return contact
	        return angajat;
	    }
	 
	 //Toti angajatii
	    public List<Angajati> getTotiAngajatii() {
	        List<Angajati> angajatiList = new ArrayList<Angajati>();
	        
	        // querry
	        String selectQuery = "SELECT  * FROM " + TABELA_ANGAJATI;
	 
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        
	        // face loop printre linii si adauga in lista
	        if (cursor.moveToFirst()) { 
	            do {
	                Angajati angajat = new Angajati();
	                angajat.setID(Integer.parseInt(cursor.getString(0)));
	                angajat.setNume(cursor.getString(1));
	                angajat.setDepartament(cursor.getString(2));
	               
	                // Adding contact to list
	                angajatiList.add(angajat);
	            } while (cursor.moveToNext());
	        }
	 
	        // returneaza lista de angajati
	        return angajatiList;
	 
	    }
	    
	    // Update-ul unui angajat
	    public int updateAngajat(Angajati angajat) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(NUME_KEY, angajat.getNume());
	      
	        values.put(DEPARTAMENT_KEY, angajat.getDepartament());
	 
	        // update linie
	        return db.update(TABELA_ANGAJATI, values, ID_KEY + " = ?",
	                new String[] { String.valueOf(angajat.getID()) });
	    }
	    
	    
	 //Stergere angajat
	    public void stergeAngajat(Angajati angajat) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(TABELA_ANGAJATI, ID_KEY + " = ?",
	                new String[] { String.valueOf(angajat.getID()) });
	        db.close();
	    }
	    
	 // Numarul angajatilor
	    public int getAngajatiCount() {
	        String countQuery = "SELECT  * FROM " + TABELA_ANGAJATI;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        cursor.close();
	 
	        // returneaza numarul
	        return cursor.getCount();
	    }
	 
}
