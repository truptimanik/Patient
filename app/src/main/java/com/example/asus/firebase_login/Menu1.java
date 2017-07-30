package com.example.asus.firebase_login;

/**
 * Created by ASUS on 16-07-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Menu1 extends Fragment{

    private TextView TextViewname,TextViewage,TextViewweight,TextViewcondition,TextViewallergy;

private DatabaseReference databaseReference;
   private FirebaseAuth auth;

    String name,age,weight,condition,allergy;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     //   Toast.makeText(getContext(),"this is working",Toast.LENGTH_LONG).show();
        View v = inflater.inflate(R.layout.fragment_pat_history,container,false);
        Bundle b = this.getArguments();
        String userid = b.getString("USERID");


        auth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PATIENT").child(userid).child("PATIENT HISTORY");


        TextViewname = (TextView)v.findViewById(R.id.editTextname);
        TextViewage = (TextView) v.findViewById(R.id.editTextage);
        TextViewweight = (TextView) v.findViewById(R.id.editTextweight);
        TextViewcondition = (TextView) v.findViewById(R.id.editTextcondition);
        TextViewallergy = (TextView) v.findViewById(R.id.editTextallergy);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                if (dataSnapshot.exists())
                {
                Map <String, String> map = (Map)dataSnapshot.getValue(genericTypeIndicator);




                    name = map.get("name");
                    age = map.get("age");
                    weight = map.get("weight");
                    condition = map.get("condition");
                    allergy = map.get("allergy");
                    TextViewname.setText(name);
                    TextViewage.setText(age);
                    TextViewweight.setText(weight);
                    TextViewcondition.setText(condition);
                    TextViewallergy.setText(allergy);


                    System.out.println("++++++++++++++++++++++++++++++++++++");
                    Log.v("E_VALUE", "Name" + name);
                    Log.v("E_VALUE", "Age" + age);
                    Log.v("E_VALUE", "Weight" + weight);
                    Log.v("E_VALUE", "Condition" + condition);
                    Log.v("E_VALUE", "Allergy" + allergy);
                    System.out.println("++++++++++++++++++++++++++++++++++++");
                }

                else {    Toast.makeText(getContext(), "Patient History is Blank yet!:", Toast.LENGTH_SHORT).show();}


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


      //  Toast.makeText(getContext(), "Patient History 0f:"+userid, Toast.LENGTH_SHORT).show();
        return v;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



   //   Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();

        //you can set the title for your toolbar here for different fragments different titles
     //   getActivity().setTitle("Menu 1");



    }


}
