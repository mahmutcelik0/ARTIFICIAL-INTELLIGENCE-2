package com.example.CONSTANT;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataSet {
    private static List<Integer> values = new ArrayList<>();
    private static List<Integer> weights = new ArrayList<>();
    private static Integer knapsackWeight = 0;

    //DATA SETLER ICERISINDE BIRDEN FAZLA DATA SERISI OLACAK VERILEN SAYIYA GORE DATASETTEN ILGILI GRUP DOLDURULACAK - 0 dan başlayacak grup

    public static void fillData(String dataSetName, Integer whichDataSet) {
        String rootPath = "src\\main\\java\\DATASETS\\" + dataSetName+".txt";

        try {
            File file = new File(rootPath);
            Scanner reader = new Scanner(file);
            for(int x = 0 ; x < whichDataSet ; x++){
                for(int y = 0 ; y < 3 ; y++){
                    if(reader.hasNextLine()){
                        reader.nextLine();
                    }
                }
            }

            for(int a = 0 ; a < 3 ; a++){
                if(reader.hasNextLine()){
                    List<Integer> data = Arrays
                                        .stream(reader.nextLine().split(" "))
                                        .map(Integer::parseInt)
                                        .toList();
                    switch (a) {
                        case 0 -> values.addAll(data);
                        case 1 -> weights.addAll(data);
                        case 2 -> knapsackWeight = data.get(0);
                    }
                }
            }
        } catch (Exception e) {

            throw new RuntimeException("ENTER THE CORRECT NUMBER IN MAINCLASS"+e);
        }
    }

    public static List<Integer> getValues() {
        return values;
    }

    public static void setValues(List<Integer> values) {
        DataSet.values = values;
    }

    public static List<Integer> getWeights() {
        return weights;
    }

    public static void setWeights(List<Integer> weights) {
        DataSet.weights = weights;
    }

    public static Integer getKnapsackWeight() {
        return knapsackWeight;
    }

    public static void setKnapsackWeight(Integer knapsackWeight) {
        DataSet.knapsackWeight = knapsackWeight;
    }
}
