package com.example.PLOTTING.BarChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainBarChart{
    public static void main(String[] args){
        readAndFillTheBarChartValues();
    }
    // RESULT - TIME tarzında saklanacak elde edilen sonuçlar.
    // Bunları okuyup chart çeşitine göre dolduracağız
    public static void readAndFillTheBarChartValues(){
        DrawBarChart resultBarChart = new DrawBarChart("RESULT");
        DrawBarChart executionTimeBarChart = new DrawBarChart("EXECUTION TIME");

        String path = "src\\main\\java\\RESULTS\\";

        List<String> filePaths = new ArrayList<>(
                Arrays.asList(
                        path+"\\bruteforce_approach_results.txt",
                        path+"\\dynamic_programming_approach_results.txt",
                        path+"\\genetic_algorithms_approach_results.txt",
                        path+"\\neural_network_approach_results.txt"
                )
        );

        filePaths.forEach(e->{
            File file = new File(e);
            try {
                Scanner reader = new Scanner(file);
                int number = 1;
                while (reader.hasNextLine()){
                    String[] values = reader.nextLine().split("-");

                    if(e.contains("bruteforce")){
                        resultBarChart.addValueToBruteforceCategory(Double.parseDouble(values[0]),Integer.toString(number));
                        executionTimeBarChart.addValueToBruteforceCategory(Double.parseDouble(values[1]),Integer.toString(number));
                    }else if(e.contains("dynamic")){
                        resultBarChart.addValueToDynamicProgCategory(Double.parseDouble(values[0]),Integer.toString(number));
                        executionTimeBarChart.addValueToDynamicProgCategory(Double.parseDouble(values[1]),Integer.toString(number));
                    }else if(e.contains("genetic")){
                        resultBarChart.addValueToGeneticAlgorithmCategory(Double.parseDouble(values[0]),Integer.toString(number));
                        executionTimeBarChart.addValueToGeneticAlgorithmCategory(Double.parseDouble(values[1]),Integer.toString(number));
                    }else{
                        resultBarChart.addValueToNeuralNetworkCategory(Double.parseDouble(values[0]),Integer.toString(number));
                        executionTimeBarChart.addValueToNeuralNetworkCategory(Double.parseDouble(values[1]),Integer.toString(number));
                    }
                    number++;
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        resultBarChart.drawTheChart();
        executionTimeBarChart.drawTheChart();
    }
}

class DrawBarChart {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private String title;

    public DrawBarChart() {
    }
    public DrawBarChart(String title){
        this.title = title;
    }
    public void drawTheChart(){
        drawTheChart(title);
    }

    //TITLE OLARAK RESULT VEYA EXECUTION TIME ALACAK
    public void drawTheChart(String title){
        String valueAxis = title.equals("RESULT")? "Results" : "Execution Times";
        JFreeChart chart = ChartFactory.createBarChart(
                title+" BAR CHART",
                "Data Sets",
                valueAxis,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Display the chart in a frame
        ChartFrame frame = new ChartFrame("Bar Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }

    //KACINCI COZUM OLDUGUNU ALACAK ve BRUTEFORCE SERISINE EKLEYECEK
    public void addValueToBruteforceCategory(Double value, String columnKey){
        addValueToDataset(value,"BRUTEFORCE",columnKey);
    }

    //KACINCI COZUM OLDUGUNU ALACAK ve DYNAMIC PROGRAMMING SERISINE EKLEYECEK
    public void addValueToDynamicProgCategory(Double value, String columnKey){
        addValueToDataset(value,"DYNAMIC PROGRAMMING",columnKey);
    }

    //KACINCI COZUM OLDUGUNU ALACAK ve GENETIC ALGORITHM SERISINE EKLEYECEK
    public void addValueToGeneticAlgorithmCategory(Double value, String columnKey){
        addValueToDataset(value,"GENETIC ALGORITHM",columnKey);
    }

    //KACINCI COZUM OLDUGUNU ALACAK ve NEURAL NETWORK SERISINE EKLEYECEK
    public void addValueToNeuralNetworkCategory(Double value, String columnKey){
        addValueToDataset(value,"NEURAL NETWORK",columnKey);
    }

    public void addValueToDataset(Double value, String rowKey, String columnKey){
        dataset.addValue(value,rowKey,columnKey);
    }
}
