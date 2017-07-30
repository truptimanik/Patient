package com.example.asus.firebase_login;

/**
 * Created by ASUS on 16-07-2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class Report extends Fragment{



    private Button view;

    private ImageView imageView;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private StorageReference mStore;
    private static final int GALLERY_INTENT = 2;
    private static final int CAMERA_REQUEST_CODE = 1;

     String USERID;
    String path;
    private ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
          USERID=bundle.getString("USERID");
       // Toast.makeText(getContext(), "User id retrived:"+ USERID, Toast.LENGTH_LONG).show();


       // Toast.makeText(getContext(), "User id changed"+ USERID, Toast.LENGTH_LONG).show();


        View v = inflater.inflate(R.layout.report,container,false);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Report").child(USERID);

        view=(Button)v.findViewById(R.id.buttonViewReport);

        imageView=(ImageView)v.findViewById(R.id.imageReport);

        progressDialog = new ProgressDialog(getContext());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mStore = FirebaseStorage.getInstance().getReference();
//
                StorageReference filepath = mStore;

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};

                        if(dataSnapshot.exists()) {
                            Map<String, String> map = (Map) dataSnapshot.getValue(genericTypeIndicator);
                            path = map.get("url");
                            Picasso.with(getContext()).load(path).fit().into(imageView);
                        }

                        else

                       Toast.makeText(getContext(), "Sorry, No Reports yet!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });




        return v;

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }


}
