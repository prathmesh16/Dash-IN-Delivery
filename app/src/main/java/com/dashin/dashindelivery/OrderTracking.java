package com.dashin.dashindelivery;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderTracking extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CheckBox c1,c2,c3;
    CardView card1,card2,card3;
    CollectionReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);
        c1=findViewById(R.id.checkBox);
        c2=findViewById(R.id.checkBox2);
        c3=findViewById(R.id.checkBox3);

        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);

        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);

        ref=db.collection("customer/+919422224222/my-orders/ORDER100007671/order-loc");


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c1.isChecked())
                    return;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                ref.document("stage1").update("status","C");

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTracking.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c2.isChecked())
                    return;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                if (ContextCompat.checkSelfPermission(OrderTracking.this,
                                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    if (ActivityCompat.shouldShowRequestPermissionRationale(OrderTracking.this,
                                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                                        ActivityCompat.requestPermissions(OrderTracking.this,
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                                    } else {
                                        ActivityCompat.requestPermissions(OrderTracking.this,
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                                    }
                                }
                                else
                                {
                                    ref.document("stage2").update("status","C");
                                    startService(new Intent(OrderTracking.this,ServiceLocation.class));
                                }

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTracking.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c3.isChecked())
                    return;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                ref.document("stage3").update("status","C");
                                stopService(new Intent(OrderTracking.this,ServiceLocation.class));
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTracking.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        ref.document("stage1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    JSONObject obj= new JSONObject(snapshot.getData());
                    try {
                        if (obj.getString("status").equals("A") || obj.getString("status").equals("IA"))
                        {
                            //c1.setEnabled(true);
                            c1.setChecked(false);
                        }
                        else {
                            c1.setChecked(true);
                            c1.setEnabled(false);
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        ref.document("stage2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    JSONObject obj= new JSONObject(snapshot.getData());
                    try {
                        if (obj.getString("status").equals("A") || obj.getString("status").equals("IA"))
                        {
                            // c2.setEnabled(true);
                            c2.setChecked(false);
                        }
                        else {
                            c2.setChecked(true);
                            //c2.setEnabled(false);
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        ref.document("stage3").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    JSONObject obj= new JSONObject(snapshot.getData());
                    try {
                        if (obj.getString("status").equals("A") || obj.getString("status").equals("IA"))
                        {
                            //c3.setEnabled(true);
                            c3.setChecked(false);
                        }
                        else {
                            c3.setChecked(true);
                            c3.setEnabled(false);
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(OrderTracking.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(OrderTracking.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        ref.document("stage2").update("status","C");
                        startService(new Intent(OrderTracking.this,ServiceLocation.class));
                    }
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}


