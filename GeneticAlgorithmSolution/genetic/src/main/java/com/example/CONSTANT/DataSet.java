package com.example.CONSTANT;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataSet {
    //Veri setinde 3 grup veri olduğu için value,weight,knapsackweight şeklinde bunları gerekli veri yapılarında sakladık
    private static List<Integer> values = new ArrayList<>();
    private static List<Integer> weights = new ArrayList<>();
    private static Integer knapsackWeight = 0;

    //DATA SETLER ICERISINDE BIRDEN FAZLA DATA SERISI OLACAK VERILEN SAYIYA GORE DATASETTEN ILGILI GRUP DOLDURULACAK - 0 dan başlayacak grup

    public static void fillData(String dataSetName, Integer whichDataSet) {
        //Proje içerisinde dataset in bulunduğu path e erişim sağlıyoruz
        String rootPath = "src\\main\\java\\DATASETS\\" + dataSetName + ".txt";

        //Hangi data set in olduğu gelen bilgiler arasında. 1 tane txt dosyasında 50 tane veri seti varsa bunun 25. sini okuyabiliriz mesela veya sırayla hepsini okuyup çözen bir yapıya da kolaylıkla çevirebiliriz
        try {
            File file = new File(rootPath);
            Scanner reader = new Scanner(file);
            for (int x = 0; x < whichDataSet; x++) { //Okumak istediğimiz data set e geçmemizi sağlar
                for (int y = 0; y < 3; y++) {
                    if (reader.hasNextLine()) {
                        reader.nextLine();
                    }
                }
            }

            for (int a = 0; a < 3; a++) {
                if (reader.hasNextLine()) { // Okumak istediğimiz data setteki bilgileri alır ve ilgili elemana ekler
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

            throw new RuntimeException("ENTER THE CORRECT NUMBER IN MAINCLASS" + e);
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
