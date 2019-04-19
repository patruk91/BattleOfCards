package com.codecool.editor;

import com.codecool.dao.CardDaoXML;
import com.codecool.gameelement.Card;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AddToXML extends CardDaoXML {
    private Document doc;

    public AddToXML() {
        this.doc = getDocument();
    }

    @Override
    public void addCar(Card card) {

        Element newCar = doc.createElement("Car");
        newCar.setAttribute("name", card.getCarName() );

        Element statistics = doc.createElement("Statistics");
        Element statisticSpeed = doc.createElement("Statistic");
        Element statisticRatio = doc.createElement("Statistic");
        Element statisticPrice = doc.createElement("Statistic");
        Element statisticCapacity = doc.createElement("Statistic");



        statisticSpeed.setAttribute("id", "Speed");
        statisticSpeed.appendChild(doc.createTextNode(Double.toString(card.getTopSpeed())));

        statisticRatio.setAttribute("id", "Ratio");
        statisticRatio.appendChild(doc.createTextNode(Double.toString(card.getPowerWeightRatio())));

        statisticPrice.setAttribute("id", "Price");
        statisticPrice.appendChild(doc.createTextNode(Double.toString(card.getPrice())));

        statisticCapacity.setAttribute("id", "Capacity");
        statisticCapacity.appendChild(doc.createTextNode(Double.toString(card.getCapacity())));


        newCar.appendChild(statistics);
        statistics.appendChild(statisticSpeed);
        statistics.appendChild(statisticRatio);
        statistics.appendChild(statisticPrice);
        statistics.appendChild(statisticCapacity);
        doc.getDocumentElement().appendChild(newCar);

        saveToFile();
    }


    public void saveToFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("nncars.xml");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


}
