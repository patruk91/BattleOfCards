package com.codecool.gameelement;

public abstract class User {
    private String name;
    private Pile pile;
    private boolean isUserFirst = false;

    public User(String name) {
        this.name = name;
        pile = new Pile();
        pile.setContainingUser(this);
    }

    public String getName() {
        return name;
    }

    public Pile getPile() {
        return pile;
    }

    public boolean isUserFirst() {
        return isUserFirst;
    }

    public void setUserFirst(boolean isPlayerFirst) {
        this.isUserFirst = isPlayerFirst;
    }
}
