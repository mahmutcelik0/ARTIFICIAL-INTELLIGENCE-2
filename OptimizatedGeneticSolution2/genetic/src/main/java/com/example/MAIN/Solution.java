package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.Constants;
import com.example.CONSTANT.DataSet;
import com.example.PLOTTING.AverageAndBestPlot;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Solution {
    private int repeatedSameTimeCount = 0; // Generation da iyileşme olmadığında artar
    private double startTime = 0;
    private double endTime = 0;
    private int generationCount = 0;

    private static AverageAndBestPlot plot = new AverageAndBestPlot();

    /**
     * Yapılan adımlar:
     * 1- İlk generation un random şekilde oluşturulması
     * 2- Yeni generation a eklenmesi için önceki generation dan elitism ile chromosome seçilmesi (Şans faktörü de bulunmakta)
     * 3- Selection yapılması - Tournament selection yöntemi kullanıldı
     * 4- Seçilen kromozomların crossover a uğrayıp yeni generation a eklenmesi
     * 5- Mutasyon yapılması - 2 yöntem bulunuyor. Two opt mutation ve normal mutation (Şans faktörü bulunmakta)
     * 6- Yeni generationdaki chromosome ların fitness değerlerinin hesaplanması
     * 7- Stop criteria kontrolü - Belirli generation da sonuçlarda iyileşme olmazsa döngü durar
     * 8- Elde edilen verilerle grafiklerin çizdirilmesi
     * */
    public void solve(boolean drawPlot) throws CloneNotSupportedException {
        startTime = System.nanoTime();

        Generation oldGeneration = new Generation();
        oldGeneration.fillGenerationRandomly();
//        oldGeneration.addNewChromosome(greedyApproach());

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
                    if (finalNextGeneration.getChromosomesOfGeneration().size() != Constants.CHROMOSOMECOUNT.getNumber() ) {
                        if(oldGeneration.nonZeroExist() && FitnessCalculator.fitnessValueCalculation(e.getGeneOfChromosome()) !=0){
                            finalNextGeneration.getChromosomesOfGeneration().put(e, FitnessCalculator.fitnessValueCalculation(e.getGeneOfChromosome()));
                        }else {
                            finalNextGeneration.getChromosomesOfGeneration().put(e, FitnessCalculator.fitnessValueCalculation(e.getGeneOfChromosome()));
                        }
                    }
                });
            }

            nextGeneration.mutation();

            nextGeneration.getChromosomesOfGeneration().entrySet().forEach(this::calculateFitnessFunctionValues);

            stopCriteria = stopCriteriaCalculation(oldGeneration,nextGeneration);

            plot.addAllData(oldGeneration.calculateTheBestChromosome(), oldGeneration.meanOfFitnessValues(),oldGeneration.calculateTheWorsChromosome(), number);

            oldGeneration.setChromosomesOfGeneration(nextGeneration.getChromosomesOfGeneration());

            nextGeneration.printTheBestChromosome(number);
        }while (!stopCriteria);
        endTime = System.nanoTime();

        generationCount = number;

        System.out.println("FINAL EXECUTED TOUR: "+ number);

        if(drawPlot){
            plot.drawTheChart();
        }else{
            DataSet.saveForNeural(nextGeneration.getTheBestChromosomeGene());
        }
    }


    //Fitness function değerlerinin güncellenmesini sağlayan metod
    //Gelen entry nin chromosome unun genleri passwordClass a yollanır ve sonuç gelen entry nin chromosome unun value suna yazılır
    public void calculateFitnessFunctionValues(Map.Entry<Chromosome,Integer> entry){
        entry.setValue(FitnessCalculator.fitnessValueCalculation(entry.getKey().getGeneOfChromosome()));
    }

    //Durma kontrolünün yapıldığı yer
    public boolean stopCriteriaCalculation(Generation oldGeneration, Generation newGeneration){
        int oldGenerationBest = Collections.max(oldGeneration.getChromosomesOfGeneration().entrySet(),Map.Entry.comparingByValue()).getValue();
        int newGenerationBest = Collections.max(newGeneration.getChromosomesOfGeneration().entrySet(),Map.Entry.comparingByValue()).getValue();

        if(oldGenerationBest >= newGenerationBest){
            repeatedSameTimeCount++;
        }else {
            repeatedSameTimeCount = 0;
        }

        return repeatedSameTimeCount == Constants.STOPCOUNT.getNumber(); // Belirli tekrarda iyileşmemişse return true olur yani dur, diğer türlü false
    }


    public Chromosome greedyApproach(){
        DataSet.calculateTheValueToWeightRatio();
        Chromosome chromosome = new Chromosome();
        chromosome.setAllGenesToZero();
        int index = 0 ;
        DataSet.getValueToWeightRatios().entrySet().forEach(e->{
            chromosome.setSelectedGeneToOne(e.getKey());
            if(FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome()) == 0){
                chromosome.setSelectedGeneToZero(e.getKey());

            }
        });
        return chromosome;
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
