package com.example.CALCULATION;

import com.example.CONSTANT.DataSet;

public class FitnessCalculator {
    public static int fitnessValueCalculation(int[] geneValues){
        int totalWeight = 0;
        int totalValue = 0 ;
        for (int x = 0; x < geneValues.length; x++){
            if(geneValues[x] == 1){
                totalValue+= DataSet.getValues().get(x);
                totalWeight+= DataSet.getWeights().get(x);
            }
        }
        if (totalWeight >= DataSet.getKnapsackWeight()){
            return 0;
        }
        return totalValue;
    }
}