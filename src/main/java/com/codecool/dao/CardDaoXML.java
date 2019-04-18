package com.codecool.dao;

import com.codecool.gameelement.Card;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CardDaoXML extends CardDaoLoadXML implements CardDao {
    private List<Card> allCards = new ArrayList<>();

    public CardDaoXML() {
        loadXMLDocument("src/main/resources/cards.xml");
        parseCards(getDoc());
    }

    private void parseCards(Document document) {
        NodeList nodeListCards = document.getElementsByTagName("Cars");
        for (int i = 0; i < nodeListCards.getLength(); i++) {
            Node nodeCard = nodeListCards.item(i);
            if (nodeCard.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeCard;
                Card card = createCard(element);
                allCards.add(card);
            }
        }
    }

    private Card createCard(Element element) {
        String carName = element.getAttribute("name");
        Element carElement = (Element) element.getElementsByTagName("Statistics").item(0);
        NodeList carList = carElement.getElementsByTagName("Statistic");

        double speed = Double.parseDouble(carList.item(0).getFirstChild().getTextContent());
        double ratio = Double.parseDouble(carList.item(1).getFirstChild().getTextContent());
        double price = Double.parseDouble(carList.item(2).getFirstChild().getTextContent());
        int capacity = Integer.parseInt(carList.item(3).getFirstChild().getTextContent());

        return new Card(carName, speed, ratio, price, capacity);
    }

    @Override
    public List<Card> getAllCards() {
        return allCards;
    }


}
