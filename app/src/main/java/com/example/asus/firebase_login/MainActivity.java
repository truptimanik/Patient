package com.example.asus.firebase_login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth=FirebaseAuth.getInstance();



        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail= (EditText)findViewById(R.id.editTextEmail);
        editPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignin=(TextView)findViewById(R.id.TextViewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser()
    {
        final String email = editTextEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please Enter your email",Toast.LENGTH_SHORT).show();
            //stopping the excution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Password cannot be Blank",Toast.LENGTH_SHORT).show();
            //stopping the excution further
            return;
        }

        if(password.length()<6)
        {
            Toast.makeText(this,"Password needs to be 6 character long",Toast.LENGTH_SHORT).show();
            return;

        }
        //if validations are ok show progress bar
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //can start profile activity here
                        Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();

                      //  firebaseAuth= FirebaseAuth.getInstance();
                        String Type="Patient";
                        patientLabel patientLabel = new patientLabel(editTextEmail.getText().toString().trim());
                      //  FirebaseUser user = firebaseAuth.getCurrentUser();   //auth.getCurrentUser();

                        databaseReference = FirebaseDatabase.getInstance().getReference();
                       String emailid = editTextEmail.getText().toString().trim();

                        //CHECK IF THIS IS NEEDED??
                      String id = databaseReference.push().getKey();
                       databaseReference.child("PATIENT").child(escapeEmailAddress(emailid)).setValue(patientLabel);


                      startActivity(new Intent(getApplicationContext(),Patient.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Fail Try Again",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                private String escapeEmailAddress(String email) {

                    String emailid = editTextEmail.getText().toString().trim();
                    // Replace '.' (not allowed in a Firebase key) with ',' (not allowed in an email address)

                    emailid=emailid.replace('.','-');
                    email = emailid.replace('@','-');
                    return email;
                }

            });


    }

    @Override
    public void onClick(View v) {

        if(v==buttonRegister){
            registerUser();
        }

       if(v==textViewSignin){
            //open login activity
          startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
