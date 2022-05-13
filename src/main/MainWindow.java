package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable {

    @FXML
    private Canvas canvas;
    
    private ArrayList<Double>ejeX=new ArrayList<>();
    private ArrayList<Double>ejeY=new ArrayList<>();
    private GraphicsContext gc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
      gc = canvas.getGraphicsContext2D();
      
	  paint();
	}
	public void paint() {
        gc.setFill(Color.rgb(240,240,240));
        gc.fillRect(0, 0, canvas.getWidth(),canvas.getHeight());
        for(int i=0;i<10;i++) {
        	ejeX.add(50.0+10*i);
        	ejeY.add(50*Math.random());
        }
       double[]resX = getMinMax(ejeX);
       double minX = resX[0];
       double maxX = resX[1];
       
       double[]resY = getMinMax(ejeY);
       double minY = resY[0];
       double maxY = resY[1];
       
       double deltaPx = canvas.getWidth();
       double deltaDias = maxX-minX;
       
       double pendienteX=deltaPx/deltaDias;
       
       double interceptoX = pendienteX * minX *(-1);
       
       double deltaPy = -canvas.getHeight();
       
       double deltaAccidentes = maxY-minY;
       
       double pendienteY = deltaPy/deltaAccidentes;
       double interceptoY = pendienteY* maxY*(-1);

       gc.setFill(Color.BLUE);
       for(int i=0;i<ejeX.size();i++) {
    	   gc.fillOval(interception(pendienteX, interceptoX, ejeX.get(i)),interception(pendienteY, interceptoY, ejeY.get(i)) , 6, 6);
       }
       gc.setStroke(Color.BLUE);
       gc.setLineWidth(2);
       for(int i=0;i<ejeX.size()-1;i++) {
    	   
       //gc.moveTo(interception(ejeX.get(i),pendienteX,intercepto)+3, interception(ejeY.get(i),pendienteY,interceptoY)+3);
    	   gc.lineTo(interception(pendienteX, interceptoX, ejeX.get(i)),interception(pendienteY, interceptoY, ejeY.get(i)));
       }
       gc.stroke();
	}

	public double[] getMinMax(ArrayList<Double>arr) {
		ArrayList<Double> arr2 = new ArrayList<>();
        arr2.addAll(arr);
        Collections.sort(arr2);
        return new double[] {arr2.get(0), arr2.get(arr2.size()-1)};
	}
	 private double interception (double m, double b, double x){
	        return (m*x)+b;
	    }
	
}
