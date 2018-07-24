package com.trinitybox.blackblind.spproviderstest2.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.trinitybox.blackblind.spproviderstest2.R;
import com.trinitybox.blackblind.spproviderstest2.Objects.ShareMarket;
import com.trinitybox.blackblind.spproviderstest2.Adapters.ShareMarketListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";
    private List<ShareMarket> shareMarketList;
    private ShareMarketListAdapter shareMarketListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);

        shareMarketList = new ArrayList<>();
        shareMarketListAdapter = new ShareMarketListAdapter(shareMarketList);
        //Navigation drawer

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Navigation item selected
        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_metal_spot_rate) {
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "item clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ShareMarketActivity.class));
                } else if (id == R.id.nav_ms_ingot_scrap_spot_rate) {
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "second item clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_pulses_spot_rate) {
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "third item clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_edible_oil_spot_rate) {
                    //TODO change it according to feature
                    Toast.makeText(MainActivity.this, "fourth item clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_share) {
                    //TODO i will change
                    Toast.makeText(MainActivity.this, "share clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_send) {
                    //TODO i will change
                    Toast.makeText(MainActivity.this, "send clicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rw_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(shareMarketListAdapter);

        //Firebase
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);


        //TODO: Database name will be changed
        firebaseFirestore.collection("UserThoughts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }

                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {

                    switch (documentChange.getType()) {
                        case ADDED:
                            ShareMarket quotes = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.add(quotes);
                            shareMarketListAdapter.notifyDataSetChanged();
                            break;
                        case REMOVED:
                            ShareMarket quotes1 = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.remove(quotes1);
                            shareMarketListAdapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            ShareMarket quotes2 = documentChange.getDocument().toObject(ShareMarket.class);
                            shareMarketList.getClass();
                            shareMarketListAdapter.notifyDataSetChanged();
                            break;
                    }

                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                //TODO Set action
                Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_reload:
                //TODO Set action
                Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_filter:
                //TODO Set action
                Toast.makeText(MainActivity.this, "Filter", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_news:
                //TODO Set action
                Toast.makeText(MainActivity.this, "News", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_settings:
                //TODO Set action
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_contact_us:
                //TODO Set action
                Toast.makeText(MainActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_about_us:
                //TODO Set action
                Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                break;
        }

        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    //Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }


}
