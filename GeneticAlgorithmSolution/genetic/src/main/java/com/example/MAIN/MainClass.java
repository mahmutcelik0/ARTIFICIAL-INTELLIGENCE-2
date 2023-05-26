package com.example.MAIN;

import com.example.CONSTANT.DataSet;

public class MainClass {
    public static void main(String[] args) {
        DataSet.fillData(1); // DON'T REMOVE IT
//        int[] myArray = {0,0,0,1,1,0,0,0,0,0};
//        System.out.println(FitnessCalculator.fitnessValueCalculation(myArray));

//        Chromosome chromosome = new Chromosome();
//        chromosome.printChromosomeGenes();

        Generation generation = new Generation();
        generation.fillGenerationRandomly();
        generation.printGeneration();
        System.out.println("----------------------");

        //ELITISM CONTROl
        //Generation nextGeneration = new Generation();
        //generation.makeElitism(nextGeneration);
        //nextGeneration.printGeneration();

        //SELECTION CONTROL
        generation.tournamentSelection().forEach(e -> {
            e.getKey().printChromosomeGenes();
            System.out.println(e.getValue());
        });


    }
}
