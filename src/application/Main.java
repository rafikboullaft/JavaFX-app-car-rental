package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	Stage stagemain;
	   public void start(Stage stagemain) throws Exception {
	     Parent root = FXMLLoader.load(getClass().getResource("/application/interface.fxml"));
	     Scene scene = new Scene(root);
	      //stage.setMaximized(true);	
	     stagemain.setScene(scene);
	     stagemain.setTitle("locatin voitures");
	     stagemain.show();
	    
	}	
	public static void main(String[] args)
	{
	    launch(args);
	}


}
