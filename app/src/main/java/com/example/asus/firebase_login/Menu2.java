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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Menu2 extends Fragment {

    private EditText EditTextname, EditTextage, EditTextweight, EditTextcondition, EditTextallergy;
    private Button submit;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    AdapterPill adapter;
    ListView pilllist;
    private ArrayList<inputPill> array;
    private View view;
    private inputPill setInputPill;
   // String inter,USERID1;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //  Toast.makeText(getContext(),"this is working",Toast.LENGTH_LONG).show();


        Bundle bundle = this.getArguments();
        final String userid=bundle.getString("USERID"); //getArguments().getString("UID");
      //  Toast.makeText(getContext(), "User id retrived"+ userid, Toast.LENGTH_LONG).show();



        if(view==null) {
             view = inflater.inflate(R.layout.fragement_menu_1, container, false);
        }
        else {

            ViewGroup parent=(ViewGroup)view.getParent();
            parent.removeView(view);
        }

        //  View innerview = v.findViewById()
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        pilllist = (ListView) view.findViewById(R.id.pilllist);

        array = new ArrayList<inputPill>();



        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("PATIENT").child(userid).child("PILL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  pillList.clear();
                for(DataSnapshot pillSnapshot:dataSnapshot.getChildren()){
                    // DataSnapshot Snapshot;

                    inputPill ip =pillSnapshot.getValue(inputPill.class);
                    array.add(ip);
                }
                pilllist.setAdapter(adapter);
                adapter = new AdapterPill(getContext(),R.layout.customlistviewpill,array);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        try {
            adapter = new AdapterPill(view.getContext(), R.layout.customlistviewpill, array);
            pilllist.setAdapter(adapter);
        }
        catch (Exception e){
            System.out.println("=============="+e);
        }
     //   Toast.makeText(getContext(), "Pill display", Toast.LENGTH_SHORT).show();


      //  return  inflater.inflate(R.layout.fragement_menu_1, container, false);
        return view;
        //submit.setOnClickListener((View.OnClickListener) this);
  /*      submit.setOnClickListener(new View.OnClickListener() {
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



   //   Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();

        //you can set the title for your toolbar here for different fragments different titles
     //   getActivity().setTitle("Menu 1");

*/
        //return v;
        //}


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //  Toast.makeText(getContext(),"Works fine!",Toast.LENGTH_LONG).show();





    }
}
