package com.example.chat101;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUserActivity extends AppCompatActivity {

    private EditText mName, mEmail, mPassword;
    private Button mRegisterButton;
    private TextView mSignInLink;
    private FirebaseAuth mAuth;

    /**
     * Creates the new user view where you can register to Firebase for future authentication
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_activity); //ensure this XML is for the NewUserActivity

        mAuth = FirebaseAuth.getInstance();

        //initialize views
        mName = findViewById(R.id.nameEditText);
        mEmail = findViewById(R.id.emailEditText);
        mPassword = findViewById(R.id.passwordEditText);
        mRegisterButton = findViewById(R.id.registerButton);
        mSignInLink = findViewById(R.id.signInLink);

        //set the click listener for the Register button
        mRegisterButton.setOnClickListener(v -> {
            String name = mName.getText().toString();
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                //show a Toast if fields are empty
                Toast.makeText(NewUserActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                //register the user with Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                //user registration was successful
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(NewUserActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                //navigate to the SignIn screen after successful registration
                                startActivity(new Intent(NewUserActivity.this, SignupActivity.class));
                                finish(); // Close the NewUserActivity!
                            } else {
                                //registration failed
                                Toast.makeText(NewUserActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        mSignInLink.setOnClickListener(v -> {
            //navigate to the Signun screen
            startActivity(new Intent(NewUserActivity.this, SignupActivity.class));
            finish(); // Close the NewUserActivity
        });
    }
}