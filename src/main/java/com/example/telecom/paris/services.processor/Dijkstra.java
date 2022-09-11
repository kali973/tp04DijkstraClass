package com.example.telecom.paris.services.processor;

import com.example.telecom.paris.models.Promotion;

public class Dijkstra {
    public static void main(String[] args) {

        Promotion promotion = new Promotion();

        promotion.add("Patrick", "Bellot");
        promotion.add("Cuong", "Bellot");
        promotion.add("Leila", "Bellot");
        promotion.add("Nam", "Bellot");
        promotion.selectionSort();
        promotion.printToConsole();
    }
}