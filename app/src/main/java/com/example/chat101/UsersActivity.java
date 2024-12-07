package com.example.chat101;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat101.adapters.UserAdapter;
import com.example.chat101.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> users;

    /**
     * Here we can see create the activity view which is the view inside each user chatroom - see past current messages and type out messages to send
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<>();

        // here set up the adapter with an empty list initially
        adapter = new UserAdapter(this, users, user -> {
            //handle user click (e.g., open chat)
            //you can navigate to a ChatActivity here
        });
        recyclerView.setAdapter(adapter);

        // get users from Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        users.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null) {
                                users.add(user);
                            }
                        }
                        adapter.notifyDataSetChanged(); //update RecyclerView
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle error here!!
                    }
                });
    }
}