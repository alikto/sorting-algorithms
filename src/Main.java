import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static double[] averageColumns(double[][] matrix) {
        int i, j;
        double[] average = new double[matrix[0].length];
        for (i = 0; i < matrix[0].length; i++) {
            double sum = 0;
            for (j = 0; j < matrix.length; j++) {
                sum += matrix[j][i];
            }
            average[i] = sum / matrix.length;
        }
        return average;
    }

    public static void print2D(double[][] arr) {
        for (double[] i : arr) {
            for (double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("********************************");
    }

    public static void main(String[] args) throws IOException {

        //x axis data
        int[] inputAxis = {256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};
        ArrayList<Integer> flowDurations;
        ArrayList<Integer> flowToCopy;

        SelectionSort sSort = new SelectionSort();
        BucketSort bSort = new BucketSort();
        QuickSort qSort = new QuickSort();

        BinarySearch bSearch = new BinarySearch();
        LinearSearch lSearch = new LinearSearch();

        Stopwatch stopwatch = new Stopwatch();


        // matrix for visualization
        double[][] search = new double[3][10];
        double[][] random = new double[3][10];
        double[][] sorted = new double[3][10];
        double[][] reverse = new double[3][10];


        // Create sample data for linear runtime
        double[][] randomQuick = new double[10][11];
        double[][] sortedQuick = new double[10][11];
        double[][] reverseQuick = new double[10][11];

        double[][] randomSelection = new double[10][11];
        double[][] sortedSelection = new double[10][11];
        double[][] reverseSelection = new double[10][11];

        double[][] randomBucket = new double[10][11];
        double[][] sortedBucket = new double[10][11];
        double[][] reverseBucket = new double[10][11];

        // search for random
        double[][] randomLinear = new double[1000][11];

        // search for sorted
        double[][] sortedBinary = new double[1000][11];
        double[][] sortedLinear = new double[1000][11];

        int position = 0;


        ArrayList<Integer> storedFlowDurations = new ArrayList<>();

        //read csv
        String line = "";
        String[] values;

        try (BufferedReader br = new BufferedReader(new FileReader("src/TrafficFlowDataset.csv"))) {
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                if (values[6].equals(" Flow Duration")) continue;
                int flowDuration = Integer.parseInt(values[6]);
                storedFlowDurations.add(flowDuration);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int inputSize : inputAxis) {
            System.out.println("Sorting input size: " + inputSize);
            //array of given size
            flowToCopy = new ArrayList<>();
            for (int i = 0; i < inputSize-1; i++) {
                flowToCopy.add(storedFlowDurations.get(i));
            }


            for (int i = 0; i<10; i++){ // 10 times sorting
                System.out.println(i);
                flowDurations = new ArrayList<>(flowToCopy);

                //SELECTION SORT------------------------------

                //random selection sort
                stopwatch = new Stopwatch();
                flowDurations = sSort.selectionSort(flowDurations, flowDurations.size());
                double time = stopwatch.elapsedTime();
                randomSelection[i][position] = time;

                //sorted selection sort
                stopwatch = new Stopwatch();
                flowDurations = sSort.selectionSort(flowDurations, flowDurations.size());
                time = stopwatch.elapsedTime();
                sortedSelection[i][position] = time;

                //reverse sorted selection sort
                Collections.reverse(flowDurations);
                stopwatch = new Stopwatch();
                flowDurations = sSort.selectionSort(flowDurations, flowDurations.size());
                time = stopwatch.elapsedTime();
                reverseSelection[i][position] = time;

                flowDurations = new ArrayList<>(flowToCopy);

                //BUCKET SORT------------------------------

                //random bucket sort
                stopwatch = new Stopwatch();
                flowDurations = bSort.bucketSort(flowDurations);
                time = stopwatch.elapsedTime();
                randomBucket[i][position] = time;

                //sorted bucket sort
                stopwatch = new Stopwatch();
                flowDurations = bSort.bucketSort(flowDurations);
                time = stopwatch.elapsedTime();
                sortedBucket[i][position] = time;

                //reverse sorted bucket sort
                Collections.reverse(flowDurations);
                stopwatch = new Stopwatch();
                flowDurations = bSort.bucketSort(flowDurations);
                time = stopwatch.elapsedTime();
                reverseBucket[i][position] = time;

                flowDurations = new ArrayList<>(flowToCopy);

                //QUICK SORT------------------------------

                //random quicksort
                stopwatch=new Stopwatch();
                flowDurations=qSort.quickSort(flowDurations, 0, flowDurations.size()-1);
                time = stopwatch.elapsedTime();
                randomQuick[i][position]=time;

                //sorted quicksort
                stopwatch=new Stopwatch();
                flowDurations=qSort.quickSort(flowDurations, 0, flowDurations.size()-1);
                time = stopwatch.elapsedTime();
                sortedQuick[i][position]=time;

                //reverse quicksort
                Collections.reverse(flowDurations);
                stopwatch=new Stopwatch();
                flowDurations=qSort.quickSort(flowDurations, 0, flowDurations.size()-1);
                time = stopwatch.elapsedTime();
                reverseQuick[i][position]=time;

            }


            for (int i = 0; i < 1000; i++) { // 1000 times searching

                flowDurations = new ArrayList<>(flowToCopy);

                //random linear search
                stopwatch = new Stopwatch();
                lSearch.linearSearch(flowDurations, 555555);
                double time = stopwatch.elapsedTimeSearch();
                randomLinear[i][position] = time;

                Collections.sort(flowDurations);

                //sorted binary search
                stopwatch = new Stopwatch();
                bSearch.binarySearch(flowDurations, 555555);
                time = stopwatch.elapsedTimeSearch();
                sortedBinary[i][position] = time;

                //sorted linear search
                stopwatch = new Stopwatch();
                lSearch.linearSearch(flowDurations, 555555);
                time = stopwatch.elapsedTimeSearch();
                sortedLinear[i][position] = time;

            }

            position++;
        }

        search[0] = Arrays.copyOfRange(averageColumns(randomLinear), 1, 11);
        search[1] = Arrays.copyOfRange(averageColumns(sortedLinear), 1, 11);
        search[2] = Arrays.copyOfRange(averageColumns(sortedBinary), 1, 11);


        // order = 0:quick - 1:bucket - 2:selection

        random[0] = Arrays.copyOfRange(averageColumns(randomQuick), 1, 11);
        random[1] = Arrays.copyOfRange(averageColumns(randomBucket), 1, 11);
        random[2] = Arrays.copyOfRange(averageColumns(randomSelection), 1, 11);

        sorted[0] = Arrays.copyOfRange(averageColumns(sortedQuick), 1, 11);
        sorted[1] = Arrays.copyOfRange(averageColumns(sortedBucket), 1, 11);
        sorted[2] = Arrays.copyOfRange(averageColumns(sortedSelection), 1, 11);

        reverse[0] = Arrays.copyOfRange(averageColumns(reverseQuick), 1, 11);
        reverse[1] = Arrays.copyOfRange(averageColumns(reverseBucket), 1, 11);
        reverse[2] = Arrays.copyOfRange(averageColumns(reverseSelection), 1, 11);

        System.out.println("Order - 0:quick - 1:bucket - 2:selection");

        System.out.println("Random Data");
        print2D(random);

        System.out.println("Sorted Data");
        print2D(sorted);

        System.out.println("Reverse Sorted Data");
        print2D(reverse);

        //Saving Charts
        showAndSaveChart("Random Data Sample", inputAxis, random);
        showAndSaveChart("Sorted Data Sample", inputAxis, sorted);
        showAndSaveChart("Reverse Sorted Data Sample", inputAxis, reverse);

        showAndSaveChartSearch("Searching Data Sample", inputAxis, search);

    }


    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Quick Sort", doubleX, yAxis[0]);
        chart.addSeries("Bucket Sort", doubleX, yAxis[1]);
        chart.addSeries("Selection Sort", doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }

    public static void showAndSaveChartSearch(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Random Linear Search", doubleX, yAxis[0]);
        chart.addSeries("Sorted Linear Search", doubleX, yAxis[1]);
        chart.addSeries("Sorted Binary Search", doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
