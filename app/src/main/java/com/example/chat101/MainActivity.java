package com.example.chat101;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chat101.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button signInButton, signUpButton;

    /**
     * creates and directs you to the main page with 2 buttons - direct you to sign into the current user view or the new user view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(v -> {
            //navigate to SignInActivity
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });

        signUpButton.setOnClickListener(v -> {
            // navigate to sign up for the New Users
            startActivity(new Intent(MainActivity.this, NewUserActivity.class));
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle navigation up
        return super.onSupportNavigateUp();
    }
}
