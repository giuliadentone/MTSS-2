////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImplementation {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        List<EItem> keyboardList = new ArrayList<EItem>();
        List<EItem> processorList = new ArrayList<EItem>();
        List<EItem> motherboardList = new ArrayList<EItem>();
        List<EItem> mouseList = new ArrayList<EItem>();

        // divide in liste di Processor, Motherboard, Mouse, Keyboard
        for (int i = 0; i < itemsOrdered.size(); i++) {
            switch (itemsOrdered.get(i).getTipo()) {
                case PROCESSOR:
                    processorList.add(itemsOrdered.get(i));
                    break;
                case MOTHERBOARD:
                    motherboardList.add(itemsOrdered.get(i));
                    break;
                case MOUSE:
                    mouseList.add(itemsOrdered.get(i));
                    break;
                case KEYBOARD:
                    keyboardList.add(itemsOrdered.get(i));
                    break;
            }
        }

        return getProcessorsPrice(processorList) +
                getMouseAndKeyboardPrice(keyboardList, mouseList) +
                getMotherboardPrice(motherboardList);
    }

    // il processore meno caro costa metà se sono più di 5 processori
    double getProcessorsPrice(List<EItem> processorList) {
        if (processorList.size() == 0) {
            return 0.0;
        }
        double toReturn = 0.0;
        EItem prezzoMin = processorList.get(0);
        if (processorList.size() > 5) {
            // trova minimo
            int i = 0, pos = 0;
            for (EItem x : processorList) {
                if (x.getPrezzo() <= prezzoMin.getPrezzo()) {
                    prezzoMin = x;
                    pos = i;// posizione del meno caro
                }
                i++;
            }
            processorList.get(pos).sconto(0.5);// applica lo sconto al pezzo
        }

        for (EItem x : processorList) {
            toReturn += x.getPrezzo();// somma tutto
        }

        return toReturn;
    }

    double getMousePrice(List<EItem> mouseList) {
        if (mouseList.size() == 0) {
            return 0.0;
        }
        double toReturn = 0.0;
        EItem priceMin = mouseList.get(0);
        if (mouseList.size() > 10) {
            // trova minimo
            int i = 0, pos = 0;
            for (EItem x : mouseList) {
                if (x.getPrezzo() < priceMin.getPrezzo()) {
                    priceMin = x;
                    pos = i;
                }
                i++;
            }
            mouseList.get(pos).sconto(1.0);
        }
        for (EItem x : mouseList) {
            toReturn += x.getPrezzo(); // somma tutto
        }

        return toReturn;
    }

    double getKeyboardPrice(List<EItem> keyboardList) {
        if (keyboardList.size() == 0) {
            return 0.0;
        }
        double toReturn = 0.0;
        for (EItem x : keyboardList) {
            toReturn += x.getPrezzo(); // somma tutto
        }

        return toReturn;
    }

    double getMotherboardPrice(List<EItem> motherboardList) {
        if (motherboardList.size() == 0) {
            return 0.0;
        }
        double toReturn = 0.0;
        for (EItem x : motherboardList) {
            toReturn += x.getPrezzo();// somma tutto
        }

        return toReturn;
    }

    // se mouse e tastiere sono dello stesso numero viene regalato il meno caro
    double getMouseAndKeyboardPrice(List<EItem> keyboardList, List<EItem> mouseList) {
        if (keyboardList.size() == 0 && mouseList.size() == 0) {
            return 0.0;
        }
        double toReturn = 0.0;

        if (keyboardList.size() == mouseList.size()) {
            EItem priceMin = mouseList.get(0);
            for (EItem x : mouseList) {
                if (x.getPrezzo() < priceMin.getPrezzo() && x.getPrezzo() != 0) {
                    priceMin = x;
                }
                toReturn += x.getPrezzo();// somma tutti i prezzi dei mouse

            }
            for (EItem x : keyboardList) {
                if (x.getPrezzo() < priceMin.getPrezzo() && x.getPrezzo() != 0) {
                    priceMin = x;
                }
                toReturn += x.getPrezzo();// somma tutti i prezzi delle tastiere
            }
            toReturn -= priceMin.getPrezzo();
            return toReturn;
        } else {
            return getMousePrice(mouseList) + getKeyboardPrice(keyboardList);
        }

    }
}