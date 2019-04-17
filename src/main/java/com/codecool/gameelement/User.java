package com.codecool.gameelement;

public abstract class User {
    String name;
    Pile pile;

    public User(String name) {
        this.name = name;
        pile = new Pile();
    }

    public String getName() {
        return name;
    }

    public Pile getPile() {
        return pile;
    }
}
