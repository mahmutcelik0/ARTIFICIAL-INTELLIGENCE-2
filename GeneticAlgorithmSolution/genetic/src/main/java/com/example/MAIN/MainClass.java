package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.DataSet;

public class MainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        DataSet.fillData(1); // DON'T REMOVE IT
        Solution solution = new Solution();
        solution.solve();
//        int[] myArray = {0,0,0,1,1,0,0,0,0,0};
//        System.out.println(FitnessCalculator.fitnessValueCalculation(myArray));

//        Chromosome chromosome = new Chromosome();
//        chromosome.printChromosomeGenes();

//        Generation generation = new Generation();
//        generation.fillGenerationRandomly();
//        generation.printGeneration();
//        System.out.println("----------------------");

        //ELITISM CONTROl
        //Generation nextGeneration = new Generation();
        //generation.makeElitism(nextGeneration);
        //nextGeneration.printGeneration();

        //SELECTION CONTROL
//        generation.tournamentSelection().forEach(e -> {
//            e.getKey().printChromosomeGenes();
//            System.out.println(e.getValue());
//        });
//


        //CROSSOVER CONTROL
//        Chromosome chr1 = new Chromosome();
//        Chromosome chr2 = new Chromosome();
//        System.out.println("BEFORE");
//        chr1.printChromosomeGenes();
//        chr2.printChromosomeGenes();
//        System.out.println("--------------------------------\nAFTER");
//        Generation secondGeneration = new Generation();
//        secondGeneration.crossOverTwoChromosome(chr1,chr2).forEach(Chromosome::printChromosomeGenes);

        //TWO OPT MUTATION CONTROL
//        Chromosome chr1 = new Chromosome();
//        System.out.println("BEFORE");
//        chr1.printChromosomeGenes();
//        System.out.println("FITNESS FUNCTION - "+ FitnessCalculator.fitnessValueCalculation(chr1.getGeneOfChromosome()));
//        Generation generation = new Generation();
//        generation.twoOptMutation(chr1);
//        System.out.println("AFTER");
//        chr1.printChromosomeGenes();
//        System.out.println("FITNESS FUNCTION - "+ FitnessCalculator.fitnessValueCalculation(chr1.getGeneOfChromosome()));

        //MUTATION CONTROL
//        Chromosome chr1 = new Chromosome();
//        System.out.println("BEFORE");
//        chr1.printChromosomeGenes();
//        Generation generation = new Generation();
//        System.out.println("EXECUTION");
//        generation.normalMutation(chr1);
//        System.out.println("AFTER");
//        chr1.printChromosomeGenes();

        //PRINT BEST CONTROL
//        Generation generation = new Generation();
//        generation.fillGenerationRandomly();
//        generation.printGeneration();
//        generation.printTheBestChromosome(1);

        //PRINT WORST CONTROL
//        Generation generation = new Generation();
//        generation.fillGenerationRandomly();
//        generation.printGeneration();
//        generation.printTheWorstChromosome(1);

        //PRINT MEAN CONTROL
//        Generation generation = new Generation();
//        generation.fillGenerationRandomly();
//        generation.printGeneration();
//        generation.meanOfFitnessValues(1);

        //MUTATION CONTROL AGAIN
//        Generation generation = new Generation();
//        generation.fillGenerationRandomly();
//        System.out.println("BEFORE GENERATION");
//        generation.printGeneration();
//        System.out.println("MUTATION");
//        generation.mutation();
//        generation.updateGenerationsFitnessValues();
//        System.out.println("AFTER GENERATION");
//        generation.printGeneration();


    }
}
