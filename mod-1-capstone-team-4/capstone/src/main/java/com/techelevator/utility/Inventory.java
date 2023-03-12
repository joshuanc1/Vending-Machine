package com.techelevator.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Inventory {


    //Parameters
    private Map<String, List<Item>> itemMap = new TreeMap<>();





    //Getter

    public Map<String, List<Item>> getItemMap() {
        return itemMap;
    }


    public Inventory() {
        mapFile();

    }

    //Methods

    //Reads inventoryList.csv and fills up itemMap accordingly
    public void mapFile() {
        File inventoryFile = new File("C:\\Users\\Student\\workspace\\pair-projects\\mod-1-capstone-team-4\\capstone\\src\\main\\java\\com\\techelevator\\utility\\inventoryList.csv");
        try (Scanner inventoryInput = new Scanner(inventoryFile)) {
            while (inventoryInput.hasNextLine()) {
                String inventoryLine = inventoryInput.nextLine();
                String[] lineDetails = inventoryLine.split(",");

                String key = lineDetails[0];
                String itemName = lineDetails[1];
                double itemPrice = Double.parseDouble(lineDetails[2]);
                String itemCategory = lineDetails[3];
                //int itemStock = Integer.parseInt(lineDetails[4]);


                Item newItem = new Item(itemName, itemPrice, itemCategory /*itemStock*/);

                List<Item> slotItems = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    slotItems.add(newItem);
                }
                itemMap.put(key, slotItems);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }



    }








