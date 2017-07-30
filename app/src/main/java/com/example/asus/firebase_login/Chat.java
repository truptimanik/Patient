package com.example.asus.firebase_login;

/**
 * Created by ASUS on 16-07-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends Fragment{


    private Button submit;
private DatabaseReference databaseReference;
   private FirebaseAuth auth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragement_menu_1,container,false);

        auth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    FirebaseUser user = auth.getCurrentUser();



                   // databaseReference.child(user.getUid()).setValue(inputPatientHistory);
                    Toast.makeText(getContext(), "Info Saved...", Toast.LENGTH_LONG).show();

                }

        });

        return v;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }


}
