package com.codecool.dao;

import com.codecool.gameelement.Card;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class CardDaoXML extends CardDaoLoadXML implements CardDao {
    private List<Card> allCards = new ArrayList<>();

    public CardDaoXML() {
        loadXMLDocument("src/main/resources/cards.xml");
        parseCards(getDoc());
    }

    private void parseCards(Document document) {

    }


    @Override
    public List<Card> getAllCards() {
        return allCards;
    }


}
