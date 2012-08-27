package com.evam.jfreechart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class AvgEngagementPOstType  {
	
	private static CategoryDataset createDataset() {   
        
        // row keys...   
        String series1 = "one";   
        String series2 = "Two";   
        String series3 = "Three"; 
        String series4 = "Four";
        String series5 = "Five";
        String series6 = "Six";
        String series7 = "Seven";
        String series8 = "Eight";
        String series9 = "Nine";
   
        // column keys...   
        String category1 = "All Post Types";   
        String category2 = "Photo";   
        String category3 = "Video";   
        String category4 = "Link";   
        String category5 = "Question";
        String category6 = "Short Status (0-140 Characters)";
        String category7 = "Medium Status (141-300 Characters)";
        String category8 = "Long Status (301-1000 Characters)";
        String category9 = "Extra Long Status (>1001 Characters)";
   
        // create the dataset...   
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   
   
        dataset.addValue(26.8, series1, category1);   
        dataset.addValue(50.7, series2, category2);   
        dataset.addValue(698.3, series3, category3);   
        dataset.addValue(394.4, series4, category4);   
        dataset.addValue(1326.8, series5, category5); 
        dataset.addValue(68.5, series6, category6);   
        dataset.addValue(0.0, series7, category7);   
        dataset.addValue(0.0, series8, category8);   
        dataset.addValue(0.0, series9, category9);   
        
   
        
           
        return dataset;   
           
    }   
private static JFreeChart createChart() {   
        
	JFreeChart jfreechart = ChartFactory.createBarChart("Average Engagement Post Type", "", "", createDataset(), PlotOrientation.HORIZONTAL, true, true, false);
    //jfreechart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
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
            int width = 550;
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
		 (new AvgEngagementPOstType()).create(
                 new FileOutputStream(
                         new File("D://Models//AvgEngagementPOstType.pdf")));
       
      
}
}

