package com.example.pgbarbershop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
         //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //          .setAction("Action", null).show();
           // }
     //   });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav,new Home()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.nav,new Home()).addToBackStack(null).commit();
        } else if (id == R.id.nav_loc) {
            String latitude = "-8.1322141";
            String longitude = "-34.938227";
            String label = "PG Barber";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);
        } else if (id == R.id.nav_serv) {
            fragmentManager.beginTransaction().replace(R.id.nav,new Sevicos()).addToBackStack(null).commit();
        } else if (id == R.id.nav_prof) {
            fragmentManager.beginTransaction().replace(R.id.nav,new Profissionais()).addToBackStack(null).commit();
        } else if (id == R.id.nav_agendar) {
            fragmentManager.beginTransaction().replace(R.id.nav,new Agendar()).addToBackStack(null).commit();
        } else if (id == R.id.nav_meuhor) {
            fragmentManager.beginTransaction().replace(R.id.nav,new Home()).addToBackStack(null).commit();
        } else if (id == R.id.nav_meusdados) {
        } else if (id == R.id.nav_facebook) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/PGBShop/"));
                startActivity(intent);
            } catch(Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/PGBShop/")));
            }
        } else if (id == R.id.nav_instagram) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/pgbarbers"));
                startActivity(intent);
            } catch(Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/pgbarbers")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
