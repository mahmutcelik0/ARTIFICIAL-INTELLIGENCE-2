package com.example.MAIN;

import com.example.CONSTANT.Constants;

import java.util.Arrays;
import java.util.Random;

public class Chromosome {
    private int[] geneOfChromosome = new int[Constants.LENGTHOFDATASET.getNumber()];

    public Chromosome() {
        for (int x = 0; x < Constants.LENGTHOFDATASET.getNumber() ; x++){
            geneOfChromosome[x] = new Random().nextInt(0,2);
        }
    }

    public int[] getGeneOfChromosome() {
        return geneOfChromosome;
    }

    public void printChromosomeGenes(){
        System.out.println(Arrays.toString(geneOfChromosome));
    }
}
