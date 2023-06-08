package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.Constants;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Generation {
    private Map<Chromosome, Integer> chromosomesOfGeneration = new HashMap<>();
    private Random random = new Random();

    public Generation() {
    }

    //Sabitlerdeki kromozom sayısı kadar rastgele kromozom oluşturulur ve generation a eklenir
    public void fillGenerationRandomly() {
        for (int x = 0; x < Constants.CHROMOSOMECOUNT.getNumber();  x++) { //1 tane azalttık greedyden gelen de olacak
            Chromosome tempChromosome = new Chromosome();
            chromosomesOfGeneration.put(tempChromosome, FitnessCalculator.fitnessValueCalculation(tempChromosome.getGeneOfChromosome()));
        }
    }

    //Elitism yapılmak isteniyorsa ve şans faktörü başarılı olursa, generation fitness değerlerine göre sıralanır ve kaç tane chromosome
    //Yeni generation a aktarılacaksa o kadar chromosome aktarılır
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
    /**
     * Tournament selection yönteminde generationdan rastgele 2 tane chromsome seçilir ve fitness değerleri karşılaştırılır.
     * Bunlardan daha yüksek olan kazanmış olur ve yeni generation a aktarılır. 1 tane daha chromosome seçilmesi için tekrardan bu olay gerçekleştirilir.
     * İkisinin fitness değeri eşitse ikisi de seçilir fakat ilerleyen zamanlarda bunu değiştirmeyi planlamaktayım.
     * Çözümün başarısını düşürdüğünü düşünüyorum çünkü 0 0 sonuçlarına sahip iki tane chromosome seçilmiş olma oranı ilk başlarda yüksek
     * */

    public List<Chromosome> tournamentSelection() {
        Map<Chromosome, Integer> copyOfCurrentGeneration = new HashMap<>(chromosomesOfGeneration);
        List<Chromosome> returnList = new ArrayList<>();
        while (returnList.size() <=2){
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
                returnList.add(firstPlayer.getKey());
            } else if (firstPlayer.getValue() < secondPlayer.getValue()) {
                returnList.add(secondPlayer.getKey());
            }else {
                returnList.add(firstPlayer.getKey());
                returnList.add(secondPlayer.getKey());
            }
        }
        return returnList;
    }

    /*
     * else {
     *                 if (returnList.size() == 1) {
     *                     returnList.add(firstPlayer.getKey());
     *                 } else {
     *                     returnList.add(firstPlayer.getKey());
     *                     returnList.add(secondPlayer.getKey());
     *                 }
     *                 x = 2;
     *             } sildim fakat ilk generation lar tamamen 0 dan oluştuğunda problem oluştu geri ekledim
     * */

    /**
     * 10 DATA SET
     * BEFORE
     * [0, 0, 0, 0, 1, 0, 0, 0, 1, 0]
     * [1, 0, 1, 0, 1, 0, 0, 1, 1, 1]
     * --------------------------------
     * AFTER
     * [0, 0, 0, 0, 1, 0, 0, 1, 1, 1]
     * [1, 0, 1, 0, 1, 0, 0, 0, 1, 0]
     * -------------------------------
     * 11 DATA SET
     * BEFORE
     * [1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0]
     * [1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0]
     * --------------------------------
     * AFTER
     * [1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0]
     * [1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0]
     * */
    //Seçilen iki chromosome un ortadan sonraki genlerinin çaprazlamasını yapar
    public List<Chromosome> crossOverTwoChromosome(Chromosome firstChromosome, Chromosome secondChromosome){
        int[] firstChromosomeGene = firstChromosome.getGeneOfChromosome().clone();
        firstChromosome.setSecondPart(secondChromosome.getGeneOfChromosome());
        secondChromosome.setSecondPart(firstChromosomeGene);
        return List.of(firstChromosome,secondChromosome);
    }


    //MUTATION TECHNIQUES - In future of project, we will add new techniques to improve success rate of algorithm


    //TUM GENLER SIRAYLA YER DEGISTIRIR EN IYISI SECILIR

    /**
     * BEFORE
     * [1, 0, 1, 0, 0, 1, 1, 0, 0, 1]
     * FITNESS FUNCTION - 0
     * AFTER
     * [1, 0, 0, 1, 0, 1, 0, 0, 1, 1]
     * FITNESS FUNCTION - 83
     * */

    // İki yöntem bulunuyor. En iyi chromosome two opt mutation yöntemine diğer chromosome lar normal mutation a yollanıyor
    public void mutation(){
        AtomicBoolean firstElement = new AtomicBoolean(true);
        chromosomesOfGeneration.entrySet().stream().sorted(Map.Entry.<Chromosome,Integer>comparingByValue().reversed()).forEach(e->{
            if(firstElement.get()) {
                try {
                    e.getKey().printChromosomeGenes();
                    System.out.println(e.getValue());
                    twoOptMutation(e.getKey());
                    firstElement.set(false);
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                normalMutation(e.getKey());
            }
        });
    }

    //Two opt mutation da generation daki en iyi chromosome un genleri birbirleriyle yer değiştirerek mevcuttaki genlerin alabileceği max fitness değeri bulunur
    public void twoOptMutation(Chromosome chromosome) throws CloneNotSupportedException {
        Chromosome mutatedChromosome = chromosome;
        Chromosome bestMutated = (Chromosome) mutatedChromosome.clone();

        int beforeTourResult = FitnessCalculator.fitnessValueCalculation(bestMutated.getGeneOfChromosome());
        boolean continueLoop = true;

        while(continueLoop){ //passwordClass.fitnessFunction(tempGene)> beforeTourResult -- İyileşme olduğu sürece devam
            int[] tempGene = bestMutated.getGeneOfChromosome().clone();

            //Chromsome daki gene ler dolaşılır ve yer değiştirilir
            for(int x = 0 ; x < tempGene.length-1 ; x++){
                for(int y = x+1 ; y < tempGene.length ; y++){
                    int current = tempGene[x];
                    tempGene[x] = tempGene[y];
                    tempGene[y] = current;

                    //Yeni oluşan daha iyiyse bestmutated a setlenir değilse yapılan değişiklik geri alınır
                    if(FitnessCalculator.fitnessValueCalculation(tempGene) >FitnessCalculator.fitnessValueCalculation(bestMutated.getGeneOfChromosome())){
                        bestMutated.setGeneOfChromosome(tempGene.clone());
                    }else{
                        current = tempGene[y];
                        tempGene[y] = tempGene[x];
                        tempGene[x] = current;
                    }
                }
            }
            if(!(FitnessCalculator.fitnessValueCalculation(tempGene) >beforeTourResult)){
                continueLoop = false;
            }else{
                beforeTourResult = FitnessCalculator.fitnessValueCalculation(tempGene);
            }
        }
        mutatedChromosome.setGeneOfChromosome(bestMutated.getGeneOfChromosome().clone());
    }


    /**
     * BEFORE
     * [1, 1, 1, 1, 1, 0, 1, 1, 0, 1]
     * EXECUTION
     * RANDOM INDEX IS: 4
     * RANDOM CURRENT VALUE: 1
     * RANDOM AFTER CHANGE: 0
     * AFTER
     * [1, 1, 1, 1, 0, 0, 1, 1, 0, 1]
     * BEFORE
     * [1, 0, 1, 0, 1, 1, 0, 0, 0, 1]
     * EXECUTION
     * RANDOM INDEX IS: 0
     * RANDOM CURRENT VALUE: 1
     * RANDOM AFTER CHANGE: 0
     * AFTER
     * [0, 0, 1, 0, 1, 1, 0, 0, 0, 1]
     * BEFORE
     * [1, 1, 1, 1, 0, 0, 1, 0, 1, 0]
     * EXECUTION
     * RANDOM INDEX IS: 7
     * RANDOM CURRENT VALUE: 0
     * RANDOM AFTER CHANGE: 1
     * AFTER
     * [1, 1, 1, 1, 0, 0, 1, 1, 1, 0]
     * */
    //Normal mutationda şans faktörü başarılı olursa chromosome daki rastgele bir gen 0 sa 1, 1 se 0 yapılır
    //Mutasyon sonucunda aşma olmuyorsa set lenir
    public void normalMutation(Chromosome chromosome){
        double randomPercent = random.nextDouble(0,100);
        if(randomPercent < Constants.MUTATIONPERCENT.getNumber()){
            int beginningFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            int randomGene = random.nextInt(0, Constants.LENGTHOFDATASET.getNumber());
            int[] temp = chromosome.getGeneOfChromosome().clone();
            temp[randomGene] = temp[randomGene] == 1 ? 0 : 1; //1 ise 0, 0 ise 1 yapılır

            int afterMutationFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            if(beginningFitnessValue > 0 && afterMutationFitnessValue!=0){ //AŞMA OLMUYORSA
                chromosome.setGeneOfChromosome(temp.clone());
            }

        }
    }

    public void firstConcantratedMutation(Chromosome chromosome) {
        double randomPercent = random.nextDouble(0, 100);
        if (randomPercent < firstConcantratedMutationTechnique(FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome()))) {
            int beginningFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            int randomGene = random.nextInt(0, Constants.LENGTHOFDATASET.getNumber());
            int[] temp = chromosome.getGeneOfChromosome().clone();
            temp[randomGene] = temp[randomGene] == 1 ? 0 : 1; //1 ise 0, 0 ise 1 yapılır

            int afterMutationFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            if (beginningFitnessValue > 0 && afterMutationFitnessValue != 0) { //AŞMA OLMUYORSA
                chromosome.setGeneOfChromosome(temp.clone());
            }
        }
    }

    public void secondConcantratedMutation(Chromosome chromosome) {
        double randomPercent = random.nextDouble(0, 100);
        if (randomPercent < secondConcantratedMutationTechnique(FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome())) ) {
            int beginningFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            int randomGene = random.nextInt(0, Constants.LENGTHOFDATASET.getNumber());
            int[] temp = chromosome.getGeneOfChromosome().clone();
            temp[randomGene] = temp[randomGene] == 1 ? 0 : 1; //1 ise 0, 0 ise 1 yapılır

            int afterMutationFitnessValue = FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome());

            if (beginningFitnessValue > 0 && afterMutationFitnessValue != 0) { //AŞMA OLMUYORSA
                chromosome.setGeneOfChromosome(temp.clone());
            }
        }
    }

    /*
     * Mutasyon oranı jenerasyonun fitness değerlerine göre hesaplanacak
     * 10 tane kromozom olsun ve fitness değerleri aşağıdaki gibi olsun
     * 10 150 150 200 200 200 250 250 300 300
     * (Toplamları - Kromozomun Fitnessi)/toplam*100 --> Mutasyona uğrama şansı
     * Örnek:
     * Toplamları 2010 -- Fitness i 10 olan kromozomun şansı -> (2010 - 10)/2010*100 = %99.50
     * Toplamları 2010 -- Fitness i 300 olan kromozomun şansı -> (2010 - 300)/2010*100 = %85
     * Üstteki formülde mutasyon oranı hala yüksek
     * */
    public double firstConcantratedMutationTechnique(Integer fitnessValue) {
        return (double) (sumOfGenerationsFitnessValue() - fitnessValue) /sumOfGenerationsFitnessValue()*100;
    }

    /*
     * Mutasyon oranı jenerasyonun fitness değerlerine göre hesaplanacak
     * 10 tane kromozom olsun ve fitness değerleri aşağıdaki gibi olsun
     * 10 150 150 200 200 200 250 250 300 300
     * Konsantrasyon Değeri = (Maksimum Fitness Değeri - İlgili Kromozomun Fitness Değeri) / (Maksimum Fitness Değeri - Minimum Fitness Değeri) * 100 --> Mutasyona uğrama şansı
     * Örnek:
     * Max Fitness Değeri -> 300 -- Fitness i 10 olan kromozomun şansı -> (300 - 10) / (300 - 10) * 100 = %100
     * Max Fitness Değeri -> 300 -- Fitness i 150 olan kromozomun şansı -> (300 - 150) / (300 - 10) * 100 = %51.72
     * Max Fitness Değeri -> 300 -- Fitness i 300 olan kromozomun şansı -> (300 - 300) / (300 - 10) * 100 = %0
     *
     */

    public double secondConcantratedMutationTechnique(Integer fitnessValue) {
        double temp = (calculateTheBestChromosome() - fitnessValue)/ (calculateTheBestChromosome() - calculateTheWorsChromosome())*100;
        return temp==0?100:temp;
    }

    public int sumOfGenerationsFitnessValue(){
        AtomicInteger temp = new AtomicInteger();
        chromosomesOfGeneration.values().forEach(temp::addAndGet);
        return temp.get();
    }

    public void printTheBestChromosome(int number) {
        Map.Entry<Chromosome, Integer> maxEntry =  Collections.max(chromosomesOfGeneration.entrySet(),Map.Entry.comparingByValue());
        System.out.print(number+". GENERATION'S BEST:\t");
        maxEntry.getKey().printChromosomeGenes();
        System.out.print("\t VALUE:"+maxEntry.getValue()+"\n");
    }

    //Generationdaki en iyi fitness değerine sahip chromosome un bulunması
    public Double calculateTheBestChromosome(){
        Map.Entry<Chromosome, Integer> maxEntry =  Collections.max(chromosomesOfGeneration.entrySet(),Map.Entry.comparingByValue());
        return (double) maxEntry.getValue();
    }

    public int[] getTheBestChromosomeGene(){
        Map.Entry<Chromosome, Integer> maxEntry =  Collections.max(chromosomesOfGeneration.entrySet(),Map.Entry.comparingByValue());
        return maxEntry.getKey().getGeneOfChromosome();
    }
    public Double calculateTheWorsChromosome(){
        Map.Entry<Chromosome, Integer> minEntry =  Collections.min(chromosomesOfGeneration.entrySet(),Map.Entry.comparingByValue());
        return (double) minEntry.getValue();
    }

    //Maybe will use in future
    public void printTheWorstChromosome(int number){
        Map.Entry<Chromosome, Integer> minEntry =  Collections.min(chromosomesOfGeneration.entrySet(),Map.Entry.comparingByValue());
        System.out.print(number+". GENERATION'S BEST:\t");
        minEntry.getKey().printChromosomeGenes();
        System.out.println("\t"+minEntry.getValue()+"\n");
    }

    /**
     * SUM OF GENERATION FITNESS FUNCTION VALUES: 318
     * 1. GENERATION'S MEAN OF FITNESS VALUES: 15.9
     * */
    //Generation un fitness değerlerinin ortalamasını hesaplar
    public Double meanOfFitnessValues(){
        AtomicInteger sum = new AtomicInteger();
        chromosomesOfGeneration.values().forEach(sum::addAndGet);
        return sum.get() / (double) Constants.CHROMOSOMECOUNT.getNumber();
    }

    public void printGeneration() {
        for (Map.Entry<Chromosome, Integer> chromosome : chromosomesOfGeneration.entrySet()) {
            chromosome.getKey().printChromosomeGenes();
            System.out.println(chromosome.getValue());
        }
    }

    public void updateGenerationsFitnessValues(){
        chromosomesOfGeneration.entrySet().forEach(e -> e.setValue(FitnessCalculator.fitnessValueCalculation(e.getKey().getGeneOfChromosome())));
    }

    public void addNewChromosome(Chromosome chromosome){
        chromosomesOfGeneration.put(chromosome,FitnessCalculator.fitnessValueCalculation(chromosome.getGeneOfChromosome()));
    }

    public Map<Chromosome, Integer> getChromosomesOfGeneration() {
        return chromosomesOfGeneration;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setChromosomesOfGeneration(Map<Chromosome, Integer> chromosomesOfGeneration) {
        this.chromosomesOfGeneration = chromosomesOfGeneration;
    }

    public boolean nonZeroExist() {
        return chromosomesOfGeneration.entrySet().stream().filter(e-> e.getValue()!=0).toList().size()!=0?true:false;
    }
}
