package com.codecool.comparator;

import com.codecool.gameelement.Card;
import com.codecool.reader.Reader;
import com.codecool.viewer.Viewer;

public class CollectCardDataController {
    private Reader reader;
    private Viewer viewer;

    public CollectCardDataController(Reader reader, Viewer viewer) {
        this.reader = reader;
        this.viewer = viewer;
    }

    public Card getCardData() {
        String carName = reader.getCarName();
        double topSpeed = reader.getStatisticNumber("Enter top speed");
        double powerWeightRati = reader.getStatisticNumber("Enter power to weight ratio");
        double price = reader.getStatisticNumber("Enter price");
        viewer.printQuestion("Enter capacity");
        int capacity = reader.getNumberInRange(0, 999999999);
        return new Card(carName, topSpeed, powerWeightRati, price, capacity);
    }
}
