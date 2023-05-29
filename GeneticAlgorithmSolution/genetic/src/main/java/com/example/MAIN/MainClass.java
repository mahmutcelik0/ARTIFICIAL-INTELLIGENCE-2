package com.example.MAIN;

import com.example.CALCULATION.FitnessCalculator;
import com.example.CONSTANT.DataSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainClass {
    public static void main(String[] args) throws CloneNotSupportedException {
//        DataSet.fillData(2); // DON'T REMOVE IT
//        Solution solution = new Solution();
//        solution.solve();
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


class GeneticAlgorithmGraph {

    public static void main(String[] args) {
        // Create sample data for generation values and generation counts
        double[] bestValues = {10, 20, 30, 40, 50};
        double[] averageValues = {15, 25, 35, 45, 55};
        int[] generationCounts = {1, 2, 3, 4, 5};

        // Create the dataset
        XYDataset dataset = createDataset(generationCounts, bestValues, averageValues);

        // Create the chart
        JFreeChart chart = createChart(dataset);

        // Display the chart in a frame
        ChartFrame frame = new ChartFrame("Genetic Algorithm", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static XYDataset createDataset(int[] generationCounts, double[] bestValues, double[] averageValues) {
        XYSeries bestSeries = new XYSeries("Best Values");
        XYSeries averageSeries = new XYSeries("Average Values");

        // Add data points to the series
        for (int i = 0; i < bestValues.length; i++) {
            bestSeries.add(generationCounts[i], bestValues[i]);
            averageSeries.add(generationCounts[i], averageValues[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(bestSeries);
        dataset.addSeries(averageSeries);

        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Genetic Algorithm",     // Chart title
                "Generation Count",      // X-axis label
                "Generation Value",      // Y-axis label
                dataset,                 // Dataset
                PlotOrientation.VERTICAL,
                true,                    // Show legend
                true,                    // Use tooltips
                false                    // Generate URLs
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setAutoRangeIncludesZero(false);

        return chart;
    }
}

