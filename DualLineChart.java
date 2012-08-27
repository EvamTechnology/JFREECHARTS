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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

public class DualLineChart {
	private static CategoryDataset createDataset1()
	{   
		  // row keys...   
        String series1 = "Fans";   
         // column keys...   
        String category1 = "JUN 27";   
        String category2 = "JUN 29";   
        String category3 = "JUL 02";   
        String category4 = "JUL 05";   
        String category5 = "JUL 08";
        String category6 = "JUL 11";
        String category7 = "JUL 14";
        String category8 = "JUL 17";
        String category9 = "JUL 20";
        String category10 = "JUL 23";
        String category11 = "JUL 26";
       
        // create the dataset...   
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   
   
        dataset.addValue(574, series1, category1);   
        dataset.addValue(928, series1, category2);   
        dataset.addValue(898, series1, category3);   
        dataset.addValue(550, series1, category4); 
        dataset.addValue(153, series1, category5);   
        dataset.addValue(304, series1, category6);   
        dataset.addValue(789, series1, category7);   
        dataset.addValue(346, series1, category8);
        dataset.addValue(401, series1, category9);
        dataset.addValue(375, series1, category10);
        dataset.addValue(524, series1, category11);
        return dataset;   
           
    }  

	private static CategoryDataset createDataset2()
	{
		String s = "conversations(as  % of fans";
		String category1 = "JUN 27";   
        String category2 = "JUN 29";   
        String category3 = "JUL 02";   
        String category4 = "JUL 05";   
        String category5 = "JUL 08";
        String category6 = "JUL 11";
        String category7 = "JUL 14";
        String category8 = "JUL 17";
        String category9 = "JUL 20";
        String category10 = "JUL 23";
        String category11 = "JUL 26";
   DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(15D, s, category1);
		defaultcategorydataset.addValue(24D, s, category2);
		defaultcategorydataset.addValue(31D, s, category3);
		defaultcategorydataset.addValue(25D, s, category4);
		defaultcategorydataset.addValue(56D, s, category5);
		defaultcategorydataset.addValue(37D, s, category6);
		defaultcategorydataset.addValue(77D, s, category7);
		defaultcategorydataset.addValue(18D, s, category8);
		defaultcategorydataset.addValue(18D, s, category9);
		defaultcategorydataset.addValue(18D, s, category10);
		defaultcategorydataset.addValue(18D, s, category11);
		return defaultcategorydataset;
	}

	private static JFreeChart createChart()
	{
		JFreeChart jfreechart = ChartFactory.createLineChart("Dual LineChart ", "", "Fans", createDataset1(), PlotOrientation.VERTICAL, false, true, false);		
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setBackgroundPaint(new Color(255, 255, 255));
		CategoryDataset categorydataset = createDataset2();
		categoryplot.setDataset(1, categorydataset);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		 	NumberAxis numberaxis = new NumberAxis("Number taking  about(as % of fans)");
		categoryplot.setRangeAxis(1, numberaxis);
		LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
		lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		categoryplot.setRenderer(1, lineandshaperenderer);
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
		legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
		legendtitle.setFrame(new BlockBorder());
		LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
		legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
		legendtitle1.setFrame(new BlockBorder());
		BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
		blockcontainer.add(legendtitle, RectangleEdge.LEFT);
		blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
		blockcontainer.add(new EmptyBlock(2000D, 0.0D));
		CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
		compositetitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(compositetitle);
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
	            int width =550;
	            int height = 600;
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
	        (new DualLineChart()).create(
	                new FileOutputStream(
	                        new File("D://Models//DualLineChart.pdf")));
	      
	}	

}
