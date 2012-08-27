package com.evam.jfreechart;

import java.awt.Color;
import java.awt.Font;
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class AvgReplyTime {
    private static DefaultPieDataset createDataset()
   {
           DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
           defaultpiedataset.setValue("Positive(70)", new Double(70));
           defaultpiedataset.setValue("Nagative(30)", new Double(30));
           return defaultpiedataset;
   }

   private static JFreeChart createChart()
   {
           JFreeChart jfreechart = ChartFactory.createPieChart("Average Reply Time", createDataset(), true, true, false);
           PiePlot pieplot = (PiePlot) jfreechart.getPlot();
           pieplot.setLabelFont(new Font("SansSerif", 0, 12));
           pieplot.setNoDataMessage("No data available");
           pieplot.setCircular(true);
           pieplot.setLabelGap(0.02D);
           pieplot.setExplodePercent("Section D", 0.5D);
           jfreechart.addSubtitle(new TextTitle("Ratio of Positive and Nagative "));
          
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
       (new AvgReplyTime()).create(
               new FileOutputStream(
                       new File("D://Models//AvgReplyTime.pdf")));
     
}
   }
