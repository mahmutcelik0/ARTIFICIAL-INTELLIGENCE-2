package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Solution {
    private int repeatedSameTimeCount = 0; // Generation da iyileşme olmadığında
    private double startTime = 0;
    private double endTime = 0;
    private int generationCount = 0;

    public void solve() throws CloneNotSupportedException {
        startTime = System.nanoTime();

        Generation oldGeneration = new Generation();
        oldGeneration.fillGenerationRandomly();

        Generation nextGeneration = null;
        boolean stopCriteria = false;

        int number = 1;
        do{
            nextGeneration = new Generation();
            number++;

            oldGeneration.makeElitism(nextGeneration);
            while (nextGeneration.getChromosomesOfGeneration().size() < Constants.CHROMOSOMECOUNT.getNumber()) {
                List<Chromosome> temp = oldGeneration.tournamentSelection();
                Generation finalNextGeneration = nextGeneration;
                oldGeneration.crossOverTwoChromosome((Chromosome) temp.get(0).clone(), (Chromosome) temp.get(1).clone()).forEach(e -> {
                    if (finalNextGeneration.getChromosomesOfGeneration().size() != Constants.CHROMOSOMECOUNT.getNumber()) {
                        finalNextGeneration.getChromosomesOfGeneration().put(e, FitnessCalculator.fitnessValueCalculation(e.getGeneOfChromosome()));
                    }
                });
            }

            nextGeneration.mutation();

            nextGeneration.getChromosomesOfGeneration().entrySet().forEach(this::calculateFitnessFunctionValues);

            stopCriteria = stopCriteriaCalculation(oldGeneration,nextGeneration);
            oldGeneration.setChromosomesOfGeneration(nextGeneration.getChromosomesOfGeneration());

            nextGeneration.printTheBestChromosome(number);
        }while (!stopCriteria);
        endTime = System.nanoTime();

        generationCount = number;

        System.out.println("FINAL EXECUTED TOUR: "+ number);
    }

    //Fitness function değerlerinin güncellenmesini sağlayan metod
    //Gelen entry nin chromosome unun genleri passwordClass a yollanır ve sonuç gelen entry nin chromosome unun value suna yazılır
    public void calculateFitnessFunctionValues(Map.Entry<Chromosome,Integer> entry){
        entry.setValue(FitnessCalculator.fitnessValueCalculation(entry.getKey().getGeneOfChromosome()));
    }

    public boolean stopCriteriaCalculation(Generation oldGeneration, Generation newGeneration){
        int oldGenerationBest = Collections.max(oldGeneration.getChromosomesOfGeneration().entrySet(),Map.Entry.comparingByValue()).getValue();
        int newGenerationBest = Collections.max(newGeneration.getChromosomesOfGeneration().entrySet(),Map.Entry.comparingByValue()).getValue();

        if(oldGenerationBest >= newGenerationBest){
            repeatedSameTimeCount++;
        }else {
            repeatedSameTimeCount = 0;
        }

        return repeatedSameTimeCount == Constants.STOPCOUNT.getNumber(); // 50 defa iyileşmemişse return true olur yani dur, diğer türlü false
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public int getGenerationCount() {
        return generationCount;
    }
}
