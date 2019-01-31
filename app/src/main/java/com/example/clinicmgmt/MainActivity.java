package com.example.clinicmgmt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if (id == R.id.sale_detail) {
            SaleDetailsFragment saleDetailsFragment = new SaleDetailsFragment();
            displayFragment(saleDetailsFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.purchase_detail) {
            PurchaseDetailsFragment purchaseDetailsFragment= new PurchaseDetailsFragment();
            displayFragment(purchaseDetailsFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.main_stock) {
            MainStockFragment mainStockFragment = new MainStockFragment();
            displayFragment(mainStockFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.inner_stock) {
            InnerStockFragment innerStockFragment = new InnerStockFragment();
            displayFragment(innerStockFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.add_medicine) {
            AddMedicineFragment addMedicineFragment = new AddMedicineFragment();
            displayFragment(addMedicineFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.manage_medicine) {
            ManageMedicineFragment manageMedicineFragment = new ManageMedicineFragment();
            displayFragment(manageMedicineFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if(id == R.id.add_patient){
            AddPatientFragment addPatientFragment = new AddPatientFragment();
            displayFragment(addPatientFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if(id == R.id.manage_patient){
            ManagePatientFragment managePatientFragment = new ManagePatientFragment();
            displayFragment(managePatientFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        } else if(id == R.id.medicine_setting){
            SettingMedicineFragment settingMedicineFragment = new SettingMedicineFragment();
            displayFragment(settingMedicineFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        }else if(id == R.id.patient_setting){
            SettingPatientFragment settingPatientFragment = new SettingPatientFragment();
            displayFragment(settingPatientFragment);
            //Toast.makeText(this,"sale details",Toast.LENGTH_SHORT).show();

        }else if(id == R.id.add_task){
            AddTaskFragment addTaskFragment =new AddTaskFragment();
            displayFragment(addTaskFragment);
        }else if (id == R.id.view_task){
            ViewTaskFragment viewTaskFragment = new ViewTaskFragment();
            displayFragment(viewTaskFragment);
        }else if (id == R.id.add_expenses){
            AddExpensesFragment addExpensesFragment = new AddExpensesFragment();
            displayFragment(addExpensesFragment);
        }else if(id == R.id.view_expenses){
            ViewExpensesFragment viewExpensesFragment = new ViewExpensesFragment();
            displayFragment(viewExpensesFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void displayFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}
