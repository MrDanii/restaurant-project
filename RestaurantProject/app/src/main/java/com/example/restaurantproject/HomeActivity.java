package com.example.restaurantproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private long tiempoBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PresentacionFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(tiempoBackButton + 2000 > System.currentTimeMillis()){
                super.onBackPressed();
            }else{
                Toast.makeText(this, "Presiona de nuevo para volver a la página principal",
                        Toast.LENGTH_SHORT).show();
            }
            tiempoBackButton = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_alimento:
                fTransaction.replace(R.id.fragment_container, new AlimentoFragment()).commit();
                break;
            case R.id.nav_empleado:
                //Dependiendo del tipo de usuario, se le mostrara un fragment distinto
                boolean esMesero = true;
                if(esMesero){
                    fTransaction.replace(R.id.fragment_container, new MeseroFragment()).commit();
                }else{
                    fTransaction.replace(R.id.fragment_container, new EmpleadoFragment()).commit();
                }
                break;
            case R.id.nav_mesas:
                fTransaction.replace(R.id.fragment_container, new MesaFragment()).commit();
                break;
            case R.id.nav_orden:
                fTransaction.replace(R.id.fragment_container, new OrdenFragment()).commit();
                break;
            case R.id.nav_reporte:
                fTransaction.replace(R.id.fragment_container, new ReporteFragment()).commit();
                break;
            case R.id.nav_cambiar_mesero:
                fTransaction.replace(R.id.fragment_container, new CambiarMeseroFragment()).commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_salir:
                Toast.makeText(this, ":)", Toast.LENGTH_SHORT).show();
                System.exit(1);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
