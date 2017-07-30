package com.example.asus.firebase_login;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ASUS on 19-07-2017.
 */

public class AdapterPill extends ArrayAdapter<inputPill> {

    private Context context;
    private int id;

    ArrayList<inputPill> array;


    public AdapterPill(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<inputPill> array) {
        super(context,resource,array);
        //super(context, resource, objects);
        this.context=context;
        this.id=resource;
        this.array=array;

    }

    public class ViewHolder {
        TextView pillname,schedule,noon,eve;;
    }




    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {


        ViewHolder holder = new ViewHolder();
        inputPill getInputPill = getItem(position);
        if(convertView==null){

    //    Toast.makeText(context, "Adapter pill", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.customlistviewpill,parent,false);
        holder.pillname = (TextView)convertView.findViewById(R.id.txt_PillName);
        holder.schedule = (TextView)convertView.findViewById(R.id.txt_M_N_E);
            holder.noon=(TextView)convertView.findViewById(R.id.txt_N);
            holder.eve=(TextView)convertView.findViewById(R.id.txt_E);
        convertView.setTag(holder);
           }
        else {
        holder = (ViewHolder)convertView.getTag();
         }



        holder.pillname.setText(getInputPill.getPillName());
        holder.schedule.setText(getInputPill.getMorning());
        holder.noon.setText(getInputPill.getNoon());
        holder.eve.setText(getInputPill.getEve());


        return convertView;








    }


//  final  inputPill inputPill=array.get(position);


     //   TextView txtpill = (TextView)convertView.findViewById(R.id.textViewPillLabel);
     //   EditText EditTextpill=(EditText)convertView.findViewById(R.id.editTextPillName);
       /* CheckBox mor=(CheckBox)convertView.findViewById(R.id.checkBoxMorning);
        CheckBox noon=(CheckBox)convertView.findViewById(R.id.checkBoxNoon);
        CheckBox eve=(CheckBox)convertView.findViewById(R.id.checkBoxEve);
        mor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                inputPill.setMorning(isChecked);
            }
        });

        noon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                inputPill.setNoon(isChecked);

            }
        });

        eve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                inputPill.setEve(isChecked);

            }
        });


        txtpill.setText(inputPill.getPillName());//
        mor.setChecked(inputPill.isMorning());
        noon.setChecked(inputPill.isNoon());
        eve.setChecked(inputPill.isEve());

*/

       // return super.getView(position, convertView, parent);

}
