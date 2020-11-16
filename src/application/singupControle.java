package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DBConnection.DBHundler;
public class singupControle implements Initializable{
	@FXML
    private TextField nomsingup;

    @FXML
    private Button enregestrerbt;

    @FXML
    private Button seconnecterbt;

    @FXML
    private PasswordField passwordsingup;
    
    private Connection connection;
    private DBHundler handler;
   private PreparedStatement pst;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
   		// TODO Auto-generated method stub
    	handler=new DBHundler();
   		}
    


	
	public void singupAction(ActionEvent ae1 ) {
		//String nomdb=nomsingup.getText();
		//String passdb=passwordsingup.getText();
		String insert= "INSERT INTO users(nom,password)"+" VALUES(?,?)";
		connection = handler.getConnection();
		
		try {
			pst=connection.prepareStatement(insert);
			pst.setString(1,nomsingup.getText());
			pst.setString(2,passwordsingup.getText());
			
			System.out.println("passdb");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("okkkk");
		
		
	}
	@FXML
	 public void seconnecter(ActionEvent e) throws IOException
	 {
		 //singup.getScene().getWindow().hide();
		 Parent root = FXMLLoader.load(getClass().getResource("/application/logincar.fxml"));
		 Scene scene = new Scene(root);
		 Stage loginnn=new Stage();
		 loginnn.setScene(scene);
		 loginnn.setTitle("MYcaaar");
		 loginnn.show();
	 }

}
