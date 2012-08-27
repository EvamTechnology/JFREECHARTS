package com.evam.jfreechart;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class FanPageComparison {
	private static CategoryDataset categorydataset;
	 private static CategoryDataset createDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        dataset.addValue(10, "Status", "Target");
	        dataset.addValue(13, "Link", "Target");
	        dataset.addValue(15, "Photo", "Target");
	        dataset.addValue(9, "Video", "Target");
	        dataset.addValue(19, "Other", "Target");
	        dataset.addValue(17, "Status", "Macy's");
	        dataset.addValue(18, "Link", "Macy's");
	        dataset.addValue(11, "Photo", "Macy's");
	        dataset.addValue(19, "Video", "Macy's");
	        dataset.addValue(23, "Other", "Macy's");
	        dataset.addValue(21, "Status", "Toys 'R' Us");
	        dataset.addValue(11, "Link", "Toys 'R' Us");
	        dataset.addValue(23, "Photo", "Toys 'R' Us");
	        dataset.addValue(33, "Video", "Toys 'R' Us");
	        dataset.addValue(22, "Other", "Toys 'R' Us");
	        dataset.addValue(17, "Status", "Sears");
	        dataset.addValue(11, "Link", "Sears");
	        dataset.addValue(13, "Photo", "Sears");
	        dataset.addValue(15, "Video", "Sears");
	        dataset.addValue(9, "Other", "Sears");
	        dataset.addValue(7, "Status", "JC Penney");
	        dataset.addValue(15, "Link", "JC Penney");
	        dataset.addValue(17, "Photo", "JC Penney");
	        dataset.addValue(23, "Video", "JC Penney");
	        dataset.addValue(31, "Other", "JC Penney");
	        
	        
	        return dataset;
	    }
	 private static JFreeChart createChart()   
	    {   
	        JFreeChart jfreechart = ChartFactory.createStackedBarChart("Fan Page Comparison:Brand Posts", "", "", createDataset(), PlotOrientation.VERTICAL, true, true, false);   
	        jfreechart.setBackgroundPaint(Color.white);   
	        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();   
	        categoryplot.setBackgroundPaint(Color.white);   
	        categoryplot.setRangeGridlinePaint(Color.white);   
	        StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();   
	        stackedbarrenderer.setDrawBarOutline(false);   
	        stackedbarrenderer.setItemLabelsVisible(true);   
	        stackedbarrenderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
	        stackedbarrenderer.setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator());
	        stackedbarrenderer.setSeriesItemLabelGenerator(2, new StandardCategoryItemLabelGenerator());
	        stackedbarrenderer.setSeriesItemLabelGenerator(3, new StandardCategoryItemLabelGenerator());
	        stackedbarrenderer.setSeriesItemLabelGenerator(4, new StandardCategoryItemLabelGenerator());
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
	            int height = 350;
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
	                try { writer.close();
	                }
	                catch(Exception ex) { }
	            }
	        }
	    }
	    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
	        (new FanPageComparison()).create(
	                new FileOutputStream(
	                        new File("D://Models//FanPageComparison.pdf")));
	      
	}	
}
