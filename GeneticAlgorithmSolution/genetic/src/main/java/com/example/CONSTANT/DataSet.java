package com.example.CONSTANT;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSet {
    private static List<Integer> values = new ArrayList<>();
    private static List<Integer> weights = new ArrayList<>();
    private static Integer knapsackWeight = 0;

    public static void fillData(Integer dataSetHardness) {
        String rootPath = "src\\main\\java\\DifferentDataSetsForPlots\\" + dataSetHardness.toString();
        String valueFilePath = rootPath + "\\value_file.txt";
        String weightFilePath = rootPath + "\\weight_file.txt";
        String knapsackWeightPath = rootPath + "\\knapsack_file.txt";

        try {
            for (int x = 0; x < 3; x++) {
                File file;
                Scanner reader;
                switch (x) {
                    case 0 -> {
                        file = new File(valueFilePath);
                        reader = new Scanner(file);
                        while (reader.hasNextLine()) {
                            int nextValue = Integer.parseInt(reader.nextLine());
                            values.add(nextValue);
                        }
                    }
                    case 1 -> {
                        file = new File(weightFilePath);
                        reader = new Scanner(file);
                        while (reader.hasNextLine()) {
                            int nextValue = Integer.parseInt(reader.nextLine());
                            weights.add(nextValue);
                        }
                    }
                    case 2 -> {
                        file = new File(knapsackWeightPath);
                        reader = new Scanner(file);
                        int nextValue = Integer.parseInt(reader.nextLine());
                        setKnapsackWeight(nextValue);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
