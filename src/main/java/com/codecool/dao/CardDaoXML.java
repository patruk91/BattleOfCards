package com.codecool.dao;

import com.codecool.gameelement.Card;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDaoXML implements CardDao {
    private List<Card> allCards = new ArrayList<>();
    private Document doc;

    @Override
    public List<Card> getAllCards() {
        return allCards;
    }

    private void loadXMLDocument(String xmlPath) {
        try {
            File fileToRead = new File(xmlPath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(fileToRead);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            ex.printStackTrace();
        }
    }
}
