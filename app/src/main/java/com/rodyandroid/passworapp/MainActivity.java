package com.rodyandroid.passworapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rodyandroid.passworapp.Fragmentos.F_Ajustes;
import com.rodyandroid.passworapp.Fragmentos.F_Todas;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    boolean DobletoqueParaSalir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*fragmento al ejecutar la aplicacion*/
        if (savedInstanceState== null){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new F_Todas()).commit();
            navigationView.setCheckedItem(R.id.Opcion_Todas);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.Opcion_Todas){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new F_Todas()).commit();
        }

        if (id ==R.id.Opcion_Ajustes){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new F_Ajustes()).commit();
        }

        if (id==R.id.Opcion_Salir){

            Toast.makeText(this, "Cerraste Session", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (DobletoqueParaSalir){
            super.onBackPressed();
            Toast.makeText(this, "Saliste de la App", Toast.LENGTH_SHORT).show();
            return;
        }
        this.DobletoqueParaSalir = true;

        Toast.makeText(this, "Presione 2 veces para salir", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                DobletoqueParaSalir = false;
            }
        }, 2000);
        super.onBackPressed();
    }
}