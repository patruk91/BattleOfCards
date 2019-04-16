package com.codecool.gameelement;

public class Card {
    Pile containingPile;
    double topSpeed;
    double powerWeightRatio;
    double price;
    int capacity;
    String carName;

    public Card(String carName, double topSpeed, double powerWeightRatio, double price, int capacity) {
        this.topSpeed = topSpeed;
        this.powerWeightRatio = powerWeightRatio;
        this.price = price;
        this.capacity = capacity;
        this.carName = carName;
    }

    public Pile getContainingPile() {
        return containingPile;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public double getPowerWeightRatio() {
        return powerWeightRatio;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCarName() {
        return carName;
    }

    public void setContainingPile(Pile containingPile) {
        this.containingPile = containingPile;
    }

    public void moveToPile(Pile destinationPile) {
        destinationPile.addCard(this);
        containingPile.removeCard(this);
        setContainingPile(destinationPile);
    }
}
