package com.example.root.angelhackapp.Activity.Application;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.root.angelhackapp.Activity.Adapter.SectionPageAdapter;
import com.example.root.angelhackapp.Activity.Fragments.ProfileFragment;
import com.example.root.angelhackapp.R;

public class MyProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ProfileFragment profileFragment;
    private ViewPager mViewPager;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    //static SectionPage
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
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
        profileFragment=new ProfileFragment();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_profile_info_fragment, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        SectionPageAdapter sectionPageAdapter=new SectionPageAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(sectionPageAdapter);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            int id=item.getItemId();
        if (id==R.id.nav_item_market){
            Intent intent=new Intent(MyProfileActivity.this,MarketActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_item_logout){
            Intent intent=new Intent(MyProfileActivity.this,MarketActivity.class);
            startActivity(intent);
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent=new Intent(MyProfileActivity.this,LogoutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
