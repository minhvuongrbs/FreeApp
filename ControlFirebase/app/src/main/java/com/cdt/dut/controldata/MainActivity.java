package com.cdt.dut.controldata;

import android.graphics.Color;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button led1,led2,led3,led4;
    private Switch mswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        initComponent();
        getData();
        setData();
        getData();
    }

    private void initComponent() {
        led1=findViewById(R.id.led1);
        led2=findViewById(R.id.led2);
        led3=findViewById(R.id.led3);
        led4=findViewById(R.id.led4);
        mswitch=findViewById(R.id.switch1);
    }

    private void getData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                DatabaseReference cambien1=states.child("CAM_BIEN_1");
//                DatabaseReference cambien2=states.child("CAM_BIEN_2");
//                DatabaseReference cambien3=states.child("CAM_BIEN_3");
//                DatabaseReference cambien4=states.child("CAM_BIEN_4");

                //led1
                if (dataSnapshot.child("states").child("CAM_BIEN_1").getValue().toString().equalsIgnoreCase("true")){
                    led1.setText("led1 on");
                }else
                    led1.setText("led1 off");

                //led2
                if (dataSnapshot.child("states").child("CAM_BIEN_2").getValue().toString().equalsIgnoreCase("true")){
                    led2.setText("led2 on");
                }else
                    led2.setText("led2 off");

                //led3
                if (dataSnapshot.child("states").child("CAM_BIEN_3").getValue().toString().equalsIgnoreCase("true")){
                    led3.setText("led3 on");
                }else
                    led3.setText("led3 off");

                //led4
                if (dataSnapshot.child("states").child("CAM_BIEN_4").getValue().toString().equalsIgnoreCase("true")){
                    led4.setText("led4 on");
                }else
                    led4.setText("led4 off");

                //switch
                if (dataSnapshot.child("states").child("autoupdate").getValue().toString().equalsIgnoreCase("true")){
                    mswitch.setChecked(true);
                }else
                    mswitch.setChecked(false);
                }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setData() {
        mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    Toast.makeText(MainActivity.this,"Bật chế độ tự động",Toast.LENGTH_SHORT).show();
                    mDatabase.child("states").child("autoupdate").setValue("true");
                }else{
                    Toast.makeText(MainActivity.this,"Tắt chế độ tự động",Toast.LENGTH_SHORT).show();
                    mDatabase.child("states").child("autoupdate").setValue("false");
                }
            }
        });
        led1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mswitch.isChecked()==true){
                    if (led1.getText().toString().equalsIgnoreCase("led1 on")){
                        led1.setText("led1 off");
                        mDatabase.child("states").child("CAM_BIEN_1").setValue("false");
                    }else{
                        led1.setText("led1 on");
                        mDatabase.child("states").child("CAM_BIEN_1").setValue("false");
                    }

                }
            }
        });
        //led2
        led2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mswitch.isChecked()==true){
                    if (led2.getText().toString().equalsIgnoreCase("led2 on")){
                        led2.setText("led2 off");
                        mDatabase.child("states").child("CAM_BIEN_2").setValue("false");
                    }else
                    {
                        led2.setText("led2 on");
                        mDatabase.child("states").child("CAM_BIEN_2").setValue("false");
                    }
                }
            }
        });
        //led3
        led3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mswitch.isChecked()==true){
                    if (led3.getText().toString().equalsIgnoreCase("led3 on")){
                        led3.setText("led3 off");
                        mDatabase.child("states").child("CAM_BIEN_3").setValue("false");
                    }else
                    {
                        led3.setText("led3 on");
                        mDatabase.child("states").child("CAM_BIEN_3").setValue("false");
                    }
                }
            }
        });
        //led4
        led4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mswitch.isChecked()==true){
                    if (led4.getText().toString().equalsIgnoreCase("led4 on")){
                        led4.setText("led4 off");
                        mDatabase.child("states").child("CAM_BIEN_4").setValue("false");
                    }else
                    {
                        led4.setText("led4 on");
                        mDatabase.child("states").child("CAM_BIEN_4").setValue("false");
                    }
                }
            }
        });



    }
}
