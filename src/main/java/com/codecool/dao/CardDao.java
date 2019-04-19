package com.codecool.dao;

import com.codecool.gameelement.Card;

import java.util.List;

public interface CardDao {
    List<Card> getAllCards();

    void addCar(Card card);
}
