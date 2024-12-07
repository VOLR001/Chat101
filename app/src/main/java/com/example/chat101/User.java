package com.example.chat101;


public class User {
    private String id;
    private String name;
    private String email;

    //required empty constructor for Firebase to not cause issues
    public User() {
    }

    /**
     * Creates just a single user with basic info about them - can scroll through multiple users in the activity view for Chat
     * @param id
     * @param name
     * @param email
     */
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}