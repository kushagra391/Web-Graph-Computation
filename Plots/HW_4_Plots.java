import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class HW_4_Plots extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Precision - Recall Curve");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Recall");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Precision - Recall Curve - Query3");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Precision Recall Curve");
        
        //populating the series with data
        
        // Query - 1
//        series.getData().add(new XYChart.Data(0.0, 1.0));
//        series.getData().add(new XYChart.Data(0.1, 0.84));
//        series.getData().add(new XYChart.Data(0.2, 0.74));
//        series.getData().add(new XYChart.Data(0.3, 0.7083));
//        series.getData().add(new XYChart.Data(0.4, 0.6909));
//        series.getData().add(new XYChart.Data(0.5, 0.6838));
//        series.getData().add(new XYChart.Data(0.6, 0.6587));
//        series.getData().add(new XYChart.Data(0.7, 0.6262));
//        series.getData().add(new XYChart.Data(0.8, 0.5817));
//        series.getData().add(new XYChart.Data(0.9, 0.4316));
//        series.getData().add(new XYChart.Data(1.0, 0.0));
        
        // Query - 2
//        series.getData().add(new XYChart.Data(0.0, 1.0));
//        series.getData().add(new XYChart.Data(0.1, 0.9545));
//        series.getData().add(new XYChart.Data(0.2, 0.9412));
//        series.getData().add(new XYChart.Data(0.3, 0.9310));
//        series.getData().add(new XYChart.Data(0.4, 0.9250));
//        series.getData().add(new XYChart.Data(0.5, 0.9205));
//        series.getData().add(new XYChart.Data(0.6, 0.9020));
//        series.getData().add(new XYChart.Data(0.7, 0.8607));
//        series.getData().add(new XYChart.Data(0.8, 0.8435));
//        series.getData().add(new XYChart.Data(0.9, 0.8000));
//        series.getData().add(new XYChart.Data(1.0, 0.0));
        
        // Query - 3
        series.getData().add(new XYChart.Data(0.0, 1.0));
        series.getData().add(new XYChart.Data(0.1, 0.65));
        series.getData().add(new XYChart.Data(0.2, 0.65));
        series.getData().add(new XYChart.Data(0.3, 0.65));
        series.getData().add(new XYChart.Data(0.4, 0.6500));
        series.getData().add(new XYChart.Data(0.5, 0.6500));
        series.getData().add(new XYChart.Data(0.6, 0.6500));
        series.getData().add(new XYChart.Data(0.7, 0.6500));
        series.getData().add(new XYChart.Data(0.8, 0.6461));
        series.getData().add(new XYChart.Data(0.9, 0.5915));
        series.getData().add(new XYChart.Data(1.0, 0.4197));
        
        
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}