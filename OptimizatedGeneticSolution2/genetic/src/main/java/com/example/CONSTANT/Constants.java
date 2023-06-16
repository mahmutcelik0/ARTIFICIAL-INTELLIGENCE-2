package com.example.CONSTANT;

//Projede kullanılan sabitler
public enum Constants {
    LENGTHOFDATASET(DataSet.getWeights().size()),
    CHROMOSOMECOUNT(20),
    ELITCHROMOCOUNT(2),
    ELITISMPERCENT(100),
    MUTATIONPERCENT(100),
    STOPCOUNT(200);


    private final int number;

    Constants(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
