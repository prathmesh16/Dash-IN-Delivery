package com.dashin.dashindelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.dashin.dashindelivery.adapters.OrdersAdapter;
import com.dashin.dashindelivery.dataclasses.OrderData;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
NavigationView sideDrawer;
    androidx.appcompat.widget.Toolbar myToolbar;
    RecyclerView items;
    OrdersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMyToolbar();
        setLeftSideNavigationDrawer();
        setViews();
    }
    void setLeftSideNavigationDrawer()
    {
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer , myToolbar , R.string.open_drawer_layout, R.string.close_drawer_layout) ;
        drawer.addDrawerListener(toggle) ;
        toggle.syncState() ;
        sideDrawer = findViewById(R.id.navigation);
        sideDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id)
                {
                    case R.id.first:
                        Toast.makeText(getApplicationContext(),"First",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.second:
                        Toast.makeText(getApplicationContext(),"Second",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.third:
                        Toast.makeText(getApplicationContext(),"Third",Toast.LENGTH_LONG).show();
                        break;
                }
                DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
                drawer.closeDrawer(GravityCompat. START ) ;
                return true;
            }
        });
    }
    FirestoreRecyclerOptions<OrderData> makeRecyclerView(String number)
    {
        Query query = FirebaseFirestore.getInstance().collection("DELIVERIES");
        FirestoreRecyclerOptions<OrderData> options = new FirestoreRecyclerOptions
                .Builder<OrderData>()
                .setQuery(query,OrderData.class)
                .build();
        return options;
    }
    void setMyToolbar()
    {
        myToolbar =findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    void setViews()
    {
     items=findViewById(R.id.allOrdersRecyclerview);
     adapter = new OrdersAdapter(makeRecyclerView("number"));
     items.setHasFixedSize(true);
     items.setLayoutManager(new LinearLayoutManager(this));
     items.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
