package fr.Stock;

import java.util.HashMap;

public class Stock {

    public HashMap<String, Integer> stockList;

    public Stock() {
        stockList = new HashMap<>();
    }

    public void addToStock(String name, int nbItem) {
        if (stockList.containsKey(name)) {
            int actualStock = stockList.get(name);
            actualStock = actualStock + nbItem;
            stockList.put(name, actualStock);
        } else {
            stockList.put(name, nbItem);
        }
    }
}
