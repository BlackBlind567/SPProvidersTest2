package com.trinitybox.blackblind.spproviderstest2;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //Navigation item selected
        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_metal_spot_rate){
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "item clicked", Toast.LENGTH_SHORT).show();
                }

                else if (id == R.id.nav_ms_ingot_scrap_spot_rate){
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "second item clicked", Toast.LENGTH_SHORT).show();
                }

                else if (id == R.id.nav_pulses_spot_rate){
                    //TODO change it according feature
                    Toast.makeText(MainActivity.this, "third item clicked", Toast.LENGTH_SHORT).show();
                }

                else if (id == R.id.nav_edible_oil_spot_rate){
                    //TODO change it according to feature
                    Toast.makeText(MainActivity.this, "fourth item clicked", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.nav_share){
                    //TODO i will change
                    Toast.makeText(MainActivity.this, "share clicked", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.nav_send){
                    //TODO i will change
                    Toast.makeText(MainActivity.this, "send clicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_reload:
                Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_filter:
                Toast.makeText(MainActivity.this, "Filter", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_news:
                Toast.makeText(MainActivity.this, "News", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_contact_us:
                Toast.makeText(MainActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_about_us:
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
