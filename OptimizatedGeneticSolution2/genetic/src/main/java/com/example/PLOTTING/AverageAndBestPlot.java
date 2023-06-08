package com.example.PLOTTING;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;

public class AverageAndBestPlot {
    //Her generation sonuçları saklanır ve döngü durduğunda çizim gerçekleşir
    private List<Double> bestValuesOfGenerations;
    private List<Double> averageValuesOfGenerations;
    private List<Double> worstValuesOfGenerations;
    private List<Integer> generationCountList;

    public AverageAndBestPlot() {
        this.bestValuesOfGenerations = new ArrayList<>();
        this.averageValuesOfGenerations = new ArrayList<>();
        this.worstValuesOfGenerations = new ArrayList<>();
        this.generationCountList = new ArrayList<>();
    }
    //Data nın eklenmesini yapar
    public void addAllData(Double bestValue, Double avgValue,Double worstValue, Integer generationCount){
        addBestValue(bestValue);
        addAverageValue(avgValue);
        addWorstValue(worstValue);
        addGenerationCount(generationCount);
    }
    //Chart ı çizdirmeyi sağlar. Elimizdeki verileri XY dataset ine çeviririz ve JFreeChart ile çizimi yaparız
    public void drawTheChart(){
        XYDataset xyDataset = creationOfXYDataSet();
        JFreeChart chart = creationOfChart(xyDataset);

        ChartFrame chartFrame = new ChartFrame("GENETIC ALGORITHMS",chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }

    //Skladığımız verilerin XYDataset ine çevirir
    private XYDataset creationOfXYDataSet(){
        XYSeries bestValueSeries = new XYSeries("Best Values");
        XYSeries averageValueSeries = new XYSeries("Average Values");
        XYSeries worstValueSeries = new XYSeries("Worst Values");


        for(int x = 0 ; x < bestValuesOfGenerations.size() ; x++){
            bestValueSeries.add(generationCountList.get(x) , bestValuesOfGenerations.get(x));
            averageValueSeries.add(generationCountList.get(x) , averageValuesOfGenerations.get(x));
            worstValueSeries.add(generationCountList.get(x),worstValuesOfGenerations.get(x));
        }

        XYSeriesCollection xydataset = new XYSeriesCollection();
        xydataset.addSeries(bestValueSeries);
        xydataset.addSeries(averageValueSeries);
        xydataset.addSeries(worstValueSeries);

        return xydataset;
    }

    //Chart ın çizdirilmesi
    private JFreeChart creationOfChart(XYDataset xyDataset){
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Genetic Algorithm",     // Chart title
                "Generation Count",      // X-axis label
                "Generation Value",      // Y-axis label
                xyDataset,                 // Dataset
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

    //Data ların eklenmesi
    private void addBestValue(Double bestValue){
        bestValuesOfGenerations.add(bestValue);
    }

    private void addAverageValue(Double avgValue){
        averageValuesOfGenerations.add(avgValue);
    }

    private void addWorstValue(Double worstValue){
        worstValuesOfGenerations.add(worstValue);
    }

    private void addGenerationCount(Integer generationCount){
        generationCountList.add(generationCount);
    }
}
