package com.codecool.gameelement;

import java.util.List;

public class Card {
    private Pile containingPile;
    private double topSpeed;
    private double powerWeightRatio;
    private double price;
    private int capacity;
    private String carName;

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
        containingPile.removeLastCard();
        setContainingPile(destinationPile);
    }

    public void moveFromDealer(Pile destinationPile) {
        destinationPile.addCard(this);
        setContainingPile(destinationPile);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(String.format("Name: %s\nTop speed: %s mph | Power to weight ratio: %s bhp/ton | Price: %s USD | Capacity: %s cc",
                carName, topSpeed, capacity, powerWeightRatio, price)).toString();
    }
}
