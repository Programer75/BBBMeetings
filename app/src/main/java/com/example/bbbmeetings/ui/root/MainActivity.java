package com.example.bbbmeetings.ui.root;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bbbmeetings.App;
import com.example.bbbmeetings.R;
import com.example.bbbmeetings.data.dto.Status;
import com.example.bbbmeetings.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_history, R.id.nav_account,R.id.nav_about)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        checkStatus();
    }

//    public void someEvent(String s){
//        Fragment fragHome = getFragmentManager().findFragmentById(R.id.nav_home);
//    }
    private void checkStatus(){
        final String TAG = "checkStatus";
        App.api.checkStatus().enqueue(new Callback<Status>(){
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.code() != 200){
                    Toast.makeText(getApplicationContext(), "Server is temporarily unavailable. Please try later.",
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.i(TAG, t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}