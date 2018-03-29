package com.example.lenin.mymaterialdesign.features.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lenin.mymaterialdesign.R;
import com.example.lenin.mymaterialdesign.features.instructivemotion.InstructiveMotionActivity;
import com.example.lenin.mymaterialdesign.features.transitions.TransitionActivityA;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

@SuppressWarnings("WeakerAccess")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    @Override
    protected void onResume(){
        super.onResume();
        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onPause(){
        super.onPause();
        navigationView.setNavigationItemSelectedListener(null);
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int ItemId = item.getItemId();

        if (ItemId == R.id.nav_activity_enter_exit) {
            Intent intent = new Intent(getApplicationContext(), TransitionActivityA.class);
            Bundle bundle = makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);

        }
        else if(ItemId == R.id.nav_activity_instructive_motion){
            Intent intent = new Intent(getApplicationContext(), InstructiveMotionActivity.class);
            Bundle bundle = makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
