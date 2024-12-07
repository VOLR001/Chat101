package com.example.chat101;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mRegisterButton;
    private TextView mSignInLink;
    private FirebaseAuth mAuth;

    /**
     * Creates login screen and shoves user into that - has loadable icon too
     * users can login and create new accounts
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin); // ensure this xml is for the signupactivity

        mAuth = FirebaseAuth.getInstance();

        // initialize views
        mEmail = findViewById(R.id.emailEditText);
        mPassword = findViewById(R.id.passwordEditText);
        mRegisterButton = findViewById(R.id.registerButton);
        mSignInLink = findViewById(R.id.signInLink);

        // set the click listener for the register button
        mRegisterButton.setOnClickListener(v -> {
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                // show a toast if fields are empty
                Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // register the user with firebase authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // user registration was successful
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                // navigate to the signin screen after successful registration
                                startActivity(new Intent(SignupActivity.this, SignupActivity.class));
                                finish(); // close the signupactivity
                            } else {
                                // registration failed
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // set the click listener for the signin link to navigate to the new user page
        mSignInLink.setOnClickListener(v -> {
            // navigate to the new user screen
            startActivity(new Intent(SignupActivity.this, NewUserActivity.class)); // use NewUserActivity here
            finish(); // close the signupactivity
        });
    }
}
