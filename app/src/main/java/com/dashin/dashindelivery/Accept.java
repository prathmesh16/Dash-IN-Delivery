package com.dashin.dashindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Accept extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);
        setMyToolbar();
    }
    void setMyToolbar()
    {
        myToolbar =findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
