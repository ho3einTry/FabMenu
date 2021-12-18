package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked = false;
    private FloatingActionButton addFab;
    private FloatingActionButton editFab;
    private FloatingActionButton imageFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);


        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);


        addFab = findViewById(R.id.fba_add);
        addFab.setOnClickListener(v -> {
            onAddButtonClick();
        });
        editFab = findViewById(R.id.fba_pen);
        editFab.setOnClickListener(v -> {
            Toast.makeText(this, "Edit Button Clicked", Toast.LENGTH_SHORT).show();
        });
        imageFab = findViewById(R.id.fba_image);
        imageFab.setOnClickListener(v -> {
            Toast.makeText(this, "Image Button Clicked", Toast.LENGTH_SHORT).show();
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void onAddButtonClick() {
        setVisibility(clicked);
        setAnimation(clicked);
        setClickable(clicked);
        clicked = !clicked;
    }

    private void setClickable(boolean clicked) {
        if(!clicked){
            editFab.setClickable(true);
            imageFab.setClickable(true);
        }
        else{
            editFab.setClickable(false);
            imageFab.setClickable(false);
        }
    }

    private void setAnimation(boolean clicked) {
        if (!clicked) {
            editFab.startAnimation(fromBottom);
            imageFab.startAnimation(fromBottom);
            addFab.startAnimation(rotateOpen);
        } else {
            editFab.startAnimation(toBottom);
            imageFab.startAnimation(toBottom);
            addFab.startAnimation(rotateClose);
        }
    }

    private void setVisibility(boolean clicked) {
        if (!clicked) {
            editFab.setVisibility(View.VISIBLE);
            imageFab.setVisibility(View.VISIBLE);
        } else {
            editFab.setVisibility(View.INVISIBLE);
            imageFab.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}