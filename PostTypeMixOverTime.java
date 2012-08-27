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

public class PostTypeMixOverTime {
	private static CategoryDataset createDataset1()
	{   
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
   
        dataset.addValue(574, series1, category1);   
        dataset.addValue(928, series1, category2);   
        dataset.addValue(898, series1, category3);   
        dataset.addValue(550, series1, category4); 
        dataset.addValue(153, series1, category5);   
        dataset.addValue(304, series1, category6);   
        dataset.addValue(789, series1, category7);   
        dataset.addValue(346, series1, category8);  
        dataset.addValue(678, series2, category1);   
        dataset.addValue(980, series2, category2);   
        dataset.addValue(653, series2, category3);   
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
        dataset.addValue(198, series4, category6);   
        dataset.addValue(272, series4, category7);   
        dataset.addValue(773, series4, category8);   
         
        return dataset;   
           
    }  

	private static CategoryDataset createDataset2()
	{
		String s = "Total Engagement";
		String s1 = "3/1/12";
		String s2 = "3/2/12";
		String s3 = "3/3/12";
		String s4 = "3/4/12";
		String s5 = "3/5/12";
		String s6 = "3/6/12";
		String s7 = "3/7/12";
		String s8 = "3/8/12";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(15D, s, s1);
		defaultcategorydataset.addValue(24D, s, s2);
		defaultcategorydataset.addValue(31D, s, s3);
		defaultcategorydataset.addValue(25D, s, s4);
		defaultcategorydataset.addValue(56D, s, s5);
		defaultcategorydataset.addValue(37D, s, s6);
		defaultcategorydataset.addValue(77D, s, s7);
		defaultcategorydataset.addValue(18D, s, s8);
		return defaultcategorydataset;
	}

	private static JFreeChart createChart()
	{
		JFreeChart jfreechart = ChartFactory.createStackedBarChart("Post Type Mix Over Time", "", "# of Posts by Type", createDataset1(), PlotOrientation.VERTICAL, false, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setBackgroundPaint(new Color(238, 238, 255));
		CategoryDataset categorydataset = createDataset2();
		categoryplot.setDataset(1, categorydataset);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		 StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();   
	        stackedbarrenderer.setDrawBarOutline(false);   
	        stackedbarrenderer.setItemLabelsVisible(true);   
	        stackedbarrenderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator()); 
	        stackedbarrenderer.setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator());   
	        stackedbarrenderer.setSeriesItemLabelGenerator(2, new StandardCategoryItemLabelGenerator());
	        stackedbarrenderer.setSeriesItemLabelGenerator(3, new StandardCategoryItemLabelGenerator());
		NumberAxis numberaxis = new NumberAxis("Likes+Shares+Comments");
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
	            int width = 400;
	            int height = 400;
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
	        (new PostTypeMixOverTime()).create(
	                new FileOutputStream(
	                        new File("D://Models//PostTypeMixOverTime.pdf")));
	      
	}	
}
