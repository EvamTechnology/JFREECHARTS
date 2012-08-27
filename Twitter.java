package com.evam.jfreechart;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class Twitter {
	 private static CategoryDataset categorydataset;

		private static CategoryDataset createDataset() {   
	        
	        // row keys...   
	        String series1 = "ReTweet";   
	        String series2 = "Reply";   
	        String series3 = "Proactive Tweet"; 
	        
	        
	   
	        // column keys...   
	        String category1 = "Day1";   
	        String category2 = "Day2";   
	        String category3 = "Day3";   
	        String category4 = "Day4";   
	        String category5 = "Day5";
	        String category6 = "Day6";
	        String category7 = "Day7";
	        
	       
	        // create the dataset...   
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   
	   
	        dataset.addValue(1024, series1, category1);   
	        dataset.addValue(928, series1, category2);   
	        dataset.addValue(898, series1, category3);   
	        dataset.addValue(1100, series1, category4); 
	        dataset.addValue(1503, series1, category5);   
	        dataset.addValue(1224, series1, category6);   
	        dataset.addValue(789, series1, category7);   
	          
	        dataset.addValue(678, series2, category1);   
	        dataset.addValue(980, series2, category2);   
	        dataset.addValue(1653, series2, category3);   
	        dataset.addValue(453, series2, category4); 
	        dataset.addValue(456, series2, category5);   
	        dataset.addValue(435, series2, category6);   
	        dataset.addValue(530, series2, category7);   
	           
	        dataset.addValue(656, series3, category1);   
	        dataset.addValue(498, series3, category2);   
	        dataset.addValue(491, series3, category3);   
	        dataset.addValue(298, series3, category4); 
	        dataset.addValue(888, series3, category5);   
	        dataset.addValue(445, series3, category6);   
	        dataset.addValue(232, series3, category7);   
	           
	       return dataset;   
	           
	    }  

	private static JFreeChart createChart() {
		// create the chart...
		      // JFreeChart chart = ChartFactory.createXYLineChart("Twitter", "","" , (XYDataset) createChart(),PlotOrientation.VERTICAL, true,true,false);
		JFreeChart chart = ChartFactory.createLineChart("Twitter Report", "", "",  createDataset(), PlotOrientation.VERTICAL, false, true, false);
		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		        chart.setBackgroundPaint(Color.white);
		// get a reference to the plot for further customisation...
		        CategoryPlot plot = (CategoryPlot)chart.getPlot();
		        plot.setBackgroundPaint(Color.white);
		        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		        plot.setDomainGridlinePaint(Color.white);
		        plot.setRangeGridlinePaint(Color.white);
		        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		        renderer.setShapesVisible(true);
		        renderer.setShapesFilled(true);
		// change the auto tick unit selection to integer units only...
		        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.
		        return chart;
		    }
	 public void create(OutputStream outputStream) throws DocumentException, IOException {
	       Document document = null;
	       PdfWriter writer = null;
	        
	       try {
	           //instantiate document and writer
	           document = new Document();
	           writer = PdfWriter.getInstance(document, outputStream);
	            
	           //open document
	           document.open();
	       
	           //add image
	           int width = 500;
	           int height = 500;
	           JFreeChart chart = createChart();
	           BufferedImage bufferedImage = chart.createBufferedImage(width, height);
	           Image image = Image.getInstance(writer, bufferedImage, 1.0f);
	           document.add(image);
	            
	           //release resources
	           document.close();
	           document = null;
	            
	           writer.close();
	           writer = null;
	       } catch(DocumentException de) {
	           throw de;
	       } catch (IOException ioe) {
	           throw ioe;
	       } finally {
	           //release resources
	           if(null != document) {
	               try { document.close(); }
	               catch(Exception ex) { }
	           }
	            
	           if(null != writer) {
	               try { writer.close(); }
	               catch(Exception ex) { }
	           }
	       }
	   }

	   public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
	       (new Twitter()).create(
	               new FileOutputStream(
	                       new File("D://Models//Twitter.pdf")));
	     
	}

}
