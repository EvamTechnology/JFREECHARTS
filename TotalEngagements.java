package com.evam.jfreechart;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.UnitType;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class TotalEngagements {
	  private static CategoryDataset categorydataset;

	private static CategoryDataset createDataset() {   
        
        // row keys...   
        String series1 = "Multimedia";   
        String series2 = "Status";   
        String series3 = "Link"; 
        String series4 = "Other";
        
   
        // column keys...   
        String category1 = "3/1/12";   
        String category2 = "3/2/12";   
        String category3 = "3/3/12";   
        String category4 = "3/4/12";   
        String category5 = "3/5/12";
        String category6 = "3/6/12";
        String category7 = "3/7/12";
        String category8 = "3/8/12";
       
        // create the dataset...   
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   
   
        dataset.addValue(1024, series1, category1);   
        dataset.addValue(928, series1, category2);   
        dataset.addValue(898, series1, category3);   
        dataset.addValue(1100, series1, category4); 
        dataset.addValue(1503, series1, category5);   
        dataset.addValue(1224, series1, category6);   
        dataset.addValue(789, series1, category7);   
        dataset.addValue(346, series1, category8);  
        dataset.addValue(678, series2, category1);   
        dataset.addValue(980, series2, category2);   
        dataset.addValue(1653, series2, category3);   
        dataset.addValue(453, series2, category4); 
        dataset.addValue(456, series2, category5);   
        dataset.addValue(435, series2, category6);   
        dataset.addValue(530, series2, category7);   
        dataset.addValue(372, series2, category8);   
        dataset.addValue(656, series3, category1);   
        dataset.addValue(498, series3, category2);   
        dataset.addValue(491, series3, category3);   
        dataset.addValue(298, series3, category4); 
        dataset.addValue(888, series3, category5);   
        dataset.addValue(445, series3, category6);   
        dataset.addValue(232, series3, category7);   
        dataset.addValue(591, series3, category8);   
        dataset.addValue(909, series4, category1);   
        dataset.addValue(415, series4, category2);   
        dataset.addValue(211, series4, category3);   
        dataset.addValue(383, series4, category4); 
        dataset.addValue(333, series4, category5);   
        dataset.addValue(1298, series4, category6);   
        dataset.addValue(1332, series4, category7);   
        dataset.addValue(773, series4, category8);   
         
        return dataset;   
           
    }  
	  private static JFreeChart createChart() {
		  JFreeChart jfreechart = ChartFactory.createAreaChart("Total Engagements By Post Type", "", "Likes+Comments+Shares", createDataset(), PlotOrientation.VERTICAL, true, true, false);
          
          jfreechart.setBackgroundPaint(Color.white);
        /*TextTitle texttitle = new TextTitle("An area chart demonstration.  We use this subtitle as an example of what happens when you get a really long title or subtitle.");
          texttitle.setPosition(RectangleEdge.TOP);
          texttitle.setPadding(new RectangleInsets(UnitType.RELATIVE, 0.050000000000000003D, 0.050000000000000003D, 0.050000000000000003D, 0.050000000000000003D));
          texttitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
          jfreechart.addSubtitle(texttitle);*/
          CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
          categoryplot.setForegroundAlpha(0.5F);
          categoryplot.setDomainGridlinesVisible(true);
          CategoryAxis categoryaxis = categoryplot.getDomainAxis();
          categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
          categoryaxis.setLowerMargin(0.0D);
          categoryaxis.setUpperMargin(0.0D);
         /* categoryaxis.addCategoryLabelToolTip("Type 1", "The first type.");
          categoryaxis.addCategoryLabelToolTip("Type 2", "The second type.");
          categoryaxis.addCategoryLabelToolTip("Type 3", "The third type.");*/
          NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
          numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
          numberaxis.setLabelAngle(0.0D);
          ChartUtilities.applyCurrentTheme(jfreechart);
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
	          (new TotalEngagements()).create(
	                  new FileOutputStream(
	                          new File("D://Models//TotalEngagements.pdf")));
	        
	  }	
}
