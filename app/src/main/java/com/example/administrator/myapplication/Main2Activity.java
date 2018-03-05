package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;

public class Main2Activity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
      private Intent intent1;
    private DrawerLayout mDrawerLayout;
      //private Button butnum1;
    //private TextView text1;
    protected Activity mContext;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        intent1 = new Intent(this, MainActivity.class);
        //text1=findViewById(R.id.textView);
        //text1.setOnClickListener(new ButtonListener());
        init();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Button but1=(Button) view;
//            if(but1.getId()==R.id.textView){
//            startActivity(intent1);}
       }
    }
}
