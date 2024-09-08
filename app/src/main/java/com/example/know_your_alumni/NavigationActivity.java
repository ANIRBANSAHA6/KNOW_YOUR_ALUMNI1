package com.example.know_your_alumni;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.Open_Drawer, R.string.Close_Drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                int id = item.getItemId(); // Retrieve the ID of the selected menu item

                // Check which menu item was selected
                if (id == R.id.optnotes) {
                    fragment = new NotesFragment();
                } else if (id == R.id.home) {
                    fragment = new DashboardFragment();
                } else if (id == R.id.About_us) {
                    Toast.makeText(NavigationActivity.this, "This is About Us", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.Settings) {
                    Toast.makeText(NavigationActivity.this, "This is Settings", Toast.LENGTH_SHORT).show();
                } else {
                    fragment = new DashboardFragment(); // Default fragment
                }

                if (fragment != null) {
                    loadFragment(fragment);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });

        // Load default fragment on start
        if (savedInstanceState == null) {
            loadFragment(new DashboardFragment()); // Load HomeFragment by default
            navigationView.setCheckedItem(R.id.home); // Highlight the Home item
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the existing fragment with the new one
        fragmentTransaction.replace(R.id.container1, fragment);
        fragmentTransaction.addToBackStack(null); // Optional: Add this transaction to the back stack
        fragmentTransaction.commit();
    }
}
