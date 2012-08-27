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
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class Interactions {
	  private static CategoryDataset categorydataset;


	private static CategoryDataset createDataset() {   
          
          // row keys...   
          String series1 = "Clicks/Instances";   
          String series2 = "Engagement/Instances";   
          
          // column keys...   
          String category1 = "bit.ly";   
          String category2 = "photos-h.ak.fbcdn.net";   
          String category3 = "photos-d.ak.fbcdn.net";   
          String category4 = "photos-g.ak.fbcdn.net";   
          String category5 = " photos-e.ak.fbcdn.net";
          String category6 = " photos-a.ak.fbcdn.net";
          String category7 = " photos-b.ak.fbcdn.net";
          String category8 = " photos-c.ak.fbcdn.net";
          String category9 = " photos-f.ak.fbcdn.net";
          String category10 = " on.fb.me";
          // create the dataset...   
          DefaultCategoryDataset dataset = new DefaultCategoryDataset();   
     
          dataset.addValue(1156, series1, category1);   
          dataset.addValue(538, series2, category1);  
          dataset.addValue(0, series1, category2);   
          dataset.addValue(0, series2, category2); 
          dataset.addValue(0, series1, category3);   
          dataset.addValue(90, series2, category3);
          dataset.addValue(0, series1, category4);   
          dataset.addValue(50, series2, category4); 
          dataset.addValue(0, series1, category5);   
          dataset.addValue(203, series2, category5);   
          dataset.addValue(0, series1, category6);   
          dataset.addValue(0, series2, category6);   
          dataset.addValue(0, series1, category7);   
          dataset.addValue(78, series2, category7);   
          dataset.addValue(0, series1, category8);   
          dataset.addValue(245, series2, category8);   
          dataset.addValue(0, series1, category9);   
          dataset.addValue(410, series2, category9);   
          dataset.addValue(0, series1, category10);   
          dataset.addValue(2145, series2, category10);   
          
          
          
          
           return dataset;   
             
      }  
	  private static JFreeChart createChart()
      {
              JFreeChart jfreechart = ChartFactory.createBarChart("TOP DOMAINS:INTERACTIONS", "", "", createDataset(), PlotOrientation.HORIZONTAL, true, true, false);
              //jfreechart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
              jfreechart.addSubtitle(new TextTitle(""));
              jfreechart.addSubtitle(new TextTitle(""));
             
              jfreechart.setBackgroundPaint(Color.white);
              CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
              categoryplot.setBackgroundPaint(Color.white);
              categoryplot.setRangeGridlinePaint(Color.white);
              categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
              BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
              barrenderer.setBaseItemLabelsVisible(true);
              barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
              CategoryAxis categoryaxis = categoryplot.getDomainAxis();
              categoryaxis.setCategoryMargin(0.3D);
              categoryaxis.setUpperMargin(0.02D);
              categoryaxis.setLowerMargin(0.02D);
              NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
              numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
              numberaxis.setUpperMargin(0.10000000000000001D);
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
          (new Interactions()).create(
                  new FileOutputStream(
                          new File("D://Models//Interactions.pdf")));
        
  }
}
