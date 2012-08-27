package com.evam.jfreechart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.evam.jfreechart.TotalEngagements;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class AverageResponseTime {
	 private static CategoryDataset createDataset;
	private static CategoryDataset createDataset() {
         String s = "First";
         String s1= "@ hilton Online";
         String s2 = "@ starwood Buzz";
         String s3 = "@ holiday Inn";
         String s4 = "@ hilton WorldWide";
         String s5 = "@spgInsider";
         String s6 = "@the BestWestern";
         String s7 = "@hyattConcierge";
         String s8 = "@choice Hotels";
         DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
         defaultcategorydataset.addValue(5.2, s, s1);
         defaultcategorydataset.addValue(0.8, s, s2);
         defaultcategorydataset.addValue(10.7, s, s3);
         defaultcategorydataset.addValue(6.5, s, s4);
         defaultcategorydataset.addValue(0.6, s, s5);
         defaultcategorydataset.addValue(34.4, s, s6);
         defaultcategorydataset.addValue(1.2, s, s7);
         defaultcategorydataset.addValue(23.8, s, s8);
         return defaultcategorydataset;
 }
	 private static JFreeChart createChart() {
		 //JFreeChart jfreechart = ChartFactory.createBarChart("Total Engagements By Post Type", "", "Likes+Comments+Shares", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		 JFreeChart jfreechart = ChartFactory.createBarChart("Average Response Time(In Hours)",
                         "", "", createDataset(), PlotOrientation.VERTICAL,
                         true, true, false);
         CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
         categoryplot.setDomainGridlinesVisible(true);
         categoryplot.setRangeCrosshairVisible(true);
         categoryplot.setRangeCrosshairPaint(Color.white);
         NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
         numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
         BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
         barrenderer.setDrawBarOutline(false);
         GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue,
                         0.0F, 0.0F, new Color(0, 0, 64));
         GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F,
                         Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
         GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red,
                         0.0F, 0.0F, new Color(64, 0, 0));
         barrenderer.setSeriesPaint(0, gradientpaint);
         barrenderer.setSeriesPaint(1, gradientpaint1);
         barrenderer.setSeriesPaint(2, gradientpaint2);
         barrenderer
                         .setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator(
                                         "Tooltip: {0}"));
         CategoryAxis categoryaxis = categoryplot.getDomainAxis();
         categoryaxis.setCategoryLabelPositions(CategoryLabelPositions
                         .createUpRotationLabelPositions(0.52359877559829882D));
         CategoryItemRenderer renderer = categoryplot.getRenderer();   
         renderer.setItemLabelGenerator(   
                 new StandardCategoryItemLabelGenerator());   
         renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);   
            
        
         return jfreechart;
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
         (new AverageResponseTime()).create(
                 new FileOutputStream(
                         new File("D://Models//AverageResponseTime.pdf")));
       
 }	
}
