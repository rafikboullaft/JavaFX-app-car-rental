package application;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import DBConnection.DBHundler;


public class loginContrrol implements Initializable{
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField nomtxt;

	    @FXML
	    private TextField motdepasstxt;

	    @FXML
	    private Button loginbt;

	    @FXML
	    private Button singupbt;

	    @FXML
	    private CheckBox savgarder;
	    private Connection connection;
	    private DBHundler handler;
	    private PreparedStatement pst;
		
	   
	 @Override  
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 handler=new DBHundler();
		}
	 @FXML
	 public void createSolButtonHandler(ActionEvent e ) throws IOException
	 {
	 
	        System.out.println("Pressed!");
	        connection = handler.getConnection();
	        String req="SELECT * from users where nom=? and password=?";
	        try {
				pst = connection.prepareStatement(req);
				pst.setString(1, nomtxt.getText());
				pst.setString(2, motdepasstxt.getText());
				ResultSet rs=pst.executeQuery();
				/*int count=0;
				while(rs.next()) {
					count=count+1;
				}*/
				if (!rs.next()) {

					 //logincar.getScene().getWindow().hide();
					System.out.println("error");
					 
				}
				else {
					System.out.println("connect");
					Node node = (Node) e.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close(); 
                    
					Parent homeroot = FXMLLoader.load(getClass().getResource("/application/interface.fxml"));
					 Scene scene = new Scene(homeroot);
					 Stage home=new Stage();
					 home.setScene(scene);
					 home.setTitle("Tomobilti");
					 home.show();
				}
					
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        
	             
	  }
	 @FXML
	 public void singup(ActionEvent e) throws IOException
	 {
		 Node node = (Node) e.getSource();
         Stage stage = (Stage) node.getScene().getWindow();
         //stage.setMaximized(true);
         stage.close();
		 //logincar.getScene().getWindow().hide();
		 Parent root = FXMLLoader.load(getClass().getResource("/application/singup.fxml"));
		 Scene scene = new Scene(root);
		 Stage singup=new Stage();
		 singup.setScene(scene);
		 singup.setTitle("MYcaaar");
		 singup.show();
	 }
	 


}
	    
	    
