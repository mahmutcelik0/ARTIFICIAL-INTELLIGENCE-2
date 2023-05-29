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
    private List<Double> bestValuesOfGenerations;
    private List<Double> averageValuesOfGenerations;
    private List<Integer> generationCountList;

    public AverageAndBestPlot() {
        this.bestValuesOfGenerations = new ArrayList<>();
        this.averageValuesOfGenerations = new ArrayList<>();
        this.generationCountList = new ArrayList<>();
    }

    public void addAllData(Double bestValue, Double avgValue, Integer generationCount){
        addBestValue(bestValue);
        addAverageValue(avgValue);
        addGenerationCount(generationCount);
    }

    public void drawTheChart(){
        XYDataset xyDataset = creationOfXYDataSet();
        JFreeChart chart = creationOfChart(xyDataset);

        ChartFrame chartFrame = new ChartFrame("GENETIC ALGORITHMS",chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }

    private XYDataset creationOfXYDataSet(){
        XYSeries bestValueSeries = new XYSeries("Best Values");
        XYSeries averageValueSeries = new XYSeries("Average Values");

        for(int x = 0 ; x < bestValuesOfGenerations.size() ; x++){
            bestValueSeries.add(generationCountList.get(x) , bestValuesOfGenerations.get(x));
            averageValueSeries.add(generationCountList.get(x) , averageValuesOfGenerations.get(x));
        }

        XYSeriesCollection xydataset = new XYSeriesCollection();
        xydataset.addSeries(bestValueSeries);
        xydataset.addSeries(averageValueSeries);

        return xydataset;
    }

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

    private void addBestValue(Double bestValue){
        bestValuesOfGenerations.add(bestValue);
    }

    private void addAverageValue(Double avgValue){
        averageValuesOfGenerations.add(avgValue);
    }

    private void addGenerationCount(Integer generationCount){
        generationCountList.add(generationCount);
    }
}
