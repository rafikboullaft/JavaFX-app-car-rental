package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import DBConnection.DBHundler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class carstablecontrole extends Application{
	
	

	private ObservableList<ObservableList> data;

	private TableView tableview;
	private Connection connection2;
	private DBHundler handler2;
	private PreparedStatement pst2;
	public static int n=0;
	
	
	
	//MAIN EXECUTOR
	public static void main(String[] args) {
	    launch(args);
	}

	//CONNECTION DATABASE
	public void buildData() {
	    
		data = FXCollections.observableArrayList();
	   
	    try {
	    	handler2=new DBHundler();
			connection2 = handler2.getConnection();
	        String SQL="SELECT * from cars";
	        pst2 = connection2.prepareStatement(SQL);
			ResultSet rs=pst2.executeQuery();

	        
	        for (int i = 0; i < rs.getMetaData().getColumnCount()-1; i++) {
	            //We are using non property style for making dynamic table
	            final int j = i;
	            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
	                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
	                    return new SimpleStringProperty(param.getValue().get(j).toString());
	                }
	            });

	            tableview.getColumns().addAll(col);
	            System.out.println("Column [" + i + "] ");
	        }

	        while (rs.next()) {
	            //Iterate Row
	            ObservableList<String> row = FXCollections.observableArrayList();
	            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                //Iterate Column
	                row.add(rs.getString(i));
	            }
	           /* File file=new File(rs.getString(rs.getMetaData().getColumnCount()));
	            try {
	                BufferedImage bufferedImage = ImageIO.read(file);
	                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	                ImageView  imageView=new  ImageView(); 
	                imageView.setImage(image);
	                
	            } catch (IOException ex) {
	                
	            }*/
		 
	            System.out.println("Row [1] added " + row);
	            data.add(row);

	        }

	        //FINALLY ADDED TO TableView
	        tableview.setItems(data);
	        
         tableview.setOnMouseClicked(e->{ 
	        	
	        	
	        	ObservableList<String> roww = FXCollections.observableArrayList();
				roww=(ObservableList<String>) tableview.getSelectionModel().getSelectedItem();
			
				n= Integer.parseInt(roww.get(0));
			System.out.println(n);
	        
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error on Building Data");
	    }
	}

  
	public TableView startt(){
		
	    tableview = new TableView();
	    buildData();
	    
	    // BYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY////

	    tableview.setMinSize(1165, 540);
	    tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	    // BYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY////


	    
		return tableview;
	}
	 
	 public int getIndex()
	 {
		 return n;
	 }
	
	


}
