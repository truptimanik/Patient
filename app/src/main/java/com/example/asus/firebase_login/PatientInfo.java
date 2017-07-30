package com.example.asus.firebase_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class PatientInfo extends AppCompatActivity implements View.OnClickListener{

    private Button ok;
    private EditText editTextName;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        firebaseAuth= FirebaseAuth.getInstance();
        editTextName=(EditText)findViewById(R.id.editTextPtName);
        ok=(Button) findViewById(R.id.OK);
      ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==ok)
        {
            String name = editTextName.getText().toString().trim();
            firebaseAuth= FirebaseAuth.getInstance();

            patientLabel patientLabel = new patientLabel(name);
            FirebaseUser user = firebaseAuth.getCurrentUser();   //auth.getCurrentUser();
            String id = databaseReference.push().getKey();
       //     databaseReference.child(user.getUid()).setValue(patientLabel);
            startActivity(new Intent(getApplicationContext(),Patient.class));
        }

    }
}
