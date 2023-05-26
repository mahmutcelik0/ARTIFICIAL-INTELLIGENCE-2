package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.Constants;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Generation {
    private Map<Chromosome, Integer> chromosomesOfGeneration = new HashMap<>();
    private Random random = new Random();

    public Generation() {
    }

    public void fillGenerationRandomly() {
        for (int x = 0; x < Constants.CHROMOSOMECOUNT.getNumber(); x++) {
            Chromosome tempChromosome = new Chromosome();
            chromosomesOfGeneration.put(tempChromosome, FitnessCalculator.fitnessValueCalculation(tempChromosome.getGeneOfChromosome()));
        }
    }

    public void makeElitism(Generation nextGeneration) {
        if (Constants.ELITCHROMOCOUNT.getNumber() <= 0) return;
        if (random.nextDouble(0, 100.1) < Constants.ELITISMPERCENT.getNumber()) {
            List<Map.Entry<Chromosome, Integer>> sorted = chromosomesOfGeneration.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
            for (int x = 0; x < Constants.ELITCHROMOCOUNT.getNumber(); x++) {
                nextGeneration.getChromosomesOfGeneration().put(sorted.get(sorted.size() - x - 1).getKey(), sorted.get(sorted.size() - x - 1).getValue());
            }
        }
    }

    //TOURNAMENT SELECTION WAY
    //https://en.wikipedia.org/wiki/Tournament_selection
    public List<Map.Entry<Chromosome, Integer>> tournamentSelection() {
        Map<Chromosome, Integer> copyOfCurrentGeneration = new HashMap<>(chromosomesOfGeneration);
        List<Map.Entry<Chromosome, Integer>> returnList = new ArrayList<>();
        for (int x = 0; x < 2 && returnList.size() <= 2; x++) {
            int firstRandomNumber = random.nextInt(0, copyOfCurrentGeneration.size());
            int secondRandomNumber = random.nextInt(0, copyOfCurrentGeneration.size());

            while (firstRandomNumber == secondRandomNumber) {
                secondRandomNumber = random.nextInt(0, copyOfCurrentGeneration.size());
            }

            int finalSecondRandomNumber = secondRandomNumber;

            AtomicInteger temp = new AtomicInteger(0);
            Map.Entry<Chromosome, Integer> firstPlayer = copyOfCurrentGeneration.entrySet().stream().filter(e -> firstRandomNumber == temp.getAndIncrement()).toList().get(0);
            temp.set(0);
            Map.Entry<Chromosome, Integer> secondPlayer = copyOfCurrentGeneration.entrySet().stream().filter(e -> finalSecondRandomNumber == temp.getAndIncrement()).toList().get(0);

            if (firstPlayer.getValue() > secondPlayer.getValue()) {
                returnList.add(firstPlayer);
            } else if (firstPlayer.getValue() < secondPlayer.getValue()) {
                returnList.add(secondPlayer);
            } else {
                if (returnList.size() == 1) {
                    returnList.add(firstPlayer);
                } else {
                    returnList.add(firstPlayer);
                    returnList.add(secondPlayer);
                }
                x = 2;
            }
        }
        return returnList;
    }

    //ROULET WHELL WILL IMPLEMENT SOON...


    public void printGeneration() {
        for (Map.Entry<Chromosome, Integer> chromosome : chromosomesOfGeneration.entrySet()) {
            chromosome.getKey().printChromosomeGenes();
            System.out.println(chromosome.getValue());
        }
    }

    public Map<Chromosome, Integer> getChromosomesOfGeneration() {
        return chromosomesOfGeneration;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
