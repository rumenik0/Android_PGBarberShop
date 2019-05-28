package com.example.pgbarbershop.database;

import com.example.pgbarbershop.model.Servicos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ServicosDB {
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private ChildEventListener child;

    public void insert (Servicos s){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference().child("servicos");
        ref.push().setValue(s);
    }
}
