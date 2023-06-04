package com.example.MAIN;

import com.example.CONSTANT.Constants;

import java.util.Arrays;
import java.util.Random;

public class Chromosome implements Cloneable {
    private int[] geneOfChromosome = new int[Constants.LENGTHOFDATASET.getNumber()]; //Chromosome ların genleri

    public Chromosome() {
        for (int x = 0; x < Constants.LENGTHOFDATASET.getNumber(); x++) {
            geneOfChromosome[x] = new Random().nextInt(0, 2);
        }
    }

    //Çaprazlama için kullanılır
    public void setSecondPart(int[] crossGene) {
        int halfIndex = geneOfChromosome.length / 2;
        for (int x = halfIndex; x < geneOfChromosome.length; x++) {
            geneOfChromosome[x] = crossGene[x];
        }
    }

    public int[] getGeneOfChromosome() {
        return geneOfChromosome;
    }

    public void setGeneOfChromosome(int[] geneOfChromosome) {
        this.geneOfChromosome = geneOfChromosome;
    }

    public void printChromosomeGenes() {
        System.out.println(Arrays.toString(geneOfChromosome));
    }

    //DEEP CLONING YAPMAK GEREKLI - CHROMOSOME CLASS ICERISINDE PRIMITIVE VEYA NON-MUTABLE FIELD OLDUGU ICIN
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Chromosome tempChromosome = (Chromosome) super.clone();
        tempChromosome.setGeneOfChromosome(this.geneOfChromosome.clone());
        return tempChromosome;
    }


}
