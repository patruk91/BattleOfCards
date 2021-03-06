package com.codecool.controller;

import com.codecool.editor.AddToXML;
import com.codecool.comparator.CollectCardDataController;
import com.codecool.gameelement.Card;
import com.codecool.reader.Reader;
import com.codecool.viewer.Viewer;

public class EditorController {
    boolean exitEditor;
    Reader reader;
    Viewer viewer;

    public EditorController(Reader reader, Viewer viewer) {
        exitEditor = false;
        this.reader = reader;
        this.viewer = viewer;
        controllEditorOptions();
    }

    private void controllEditorOptions() {
        while(!exitEditor) {
            viewer.clearScreen();
            viewer.printXmlEditorMenu();
            viewer.printQuestion("Chose option");
            int option = reader.getNumberInRange(0, 3);
            switch (option) {
                case 1:
                    CollectCardDataController collectData = new CollectCardDataController(reader, viewer);
                    Card card = collectData.getCardData();
                    AddToXML addToXML = new AddToXML();
                    addToXML.addCar(card);
                    break;
                case 2:
//                    editCar(car);
                    break;
                case 3:
//                    removeCar(car);
                    break;
                case 0:
                    exitEditor = true;
                    break;
            }
        }
    }
}
