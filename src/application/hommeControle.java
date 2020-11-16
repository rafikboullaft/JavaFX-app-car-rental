package application;



import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBConnection.DBHundler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class hommeControle extends Application{
	@FXML 
	AnchorPane anchorpanehomee;
	@FXML 
	AnchorPane vidangepane;
	@FXML
	AnchorPane home2;
	@FXML
	AnchorPane home3;
	@FXML
	AnchorPane home4;
	@FXML
	AnchorPane anchorpaneCommande;
	@FXML
	AnchorPane anchorpaneVoituressortee;
	@FXML
	AnchorPane anchorpaneAjouterVoiture;
	@FXML
	AnchorPane anchorpanedashbord;
	 @FXML
	    private AnchorPane Anchorpanecarshoose;
	//table voiture sortee
	    @FXML
	    private AnchorPane AnchorpanechoosecarBMV;
	    @FXML
	    private AnchorPane AnchorpanechoosecarAUDI;
	    @FXML
	    private AnchorPane AnchorpanechoosecarMERCEDESS;

	    @FXML
	    private AnchorPane AnchorpanechoosecarVW;
	   @FXML
	   TableView tablehome; 
	   
	   @FXML
	    private AnchorPane tableau;
	   @FXML
	    private AnchorPane tableau2;
	   
	   TableView table;
	   private Connection connection2;
		private DBHundler handler2;
		  private Connection connection3;
			private DBHundler handler3;
			private PreparedStatement pst3;
		private PreparedStatement pst2;
		 carstablecontrole ctc=new carstablecontrole();
		 vscontrollere ct=new vscontrollere();
		
		private Connection connection;
		private DBHundler handler;
	
		private PreparedStatement pst;
	  
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	creatPage();
    	//creatPagecommande();
    	creatPageVoiturssorte();
    	creatPageAjoutervoiture();
    	creatPagedashbord();
    	//piechartcontroller ppp=new piechartcontroller();
    	//ppp.initialize(arg0, arg1);
    	
 
   		}
	private void setNode(Node node) {
		anchorpanehomee.getChildren().clear();
		anchorpanehomee.getChildren().add((Node) node);
		
			
	}
	private void setNode2(Node node) {
		Anchorpanecarshoose.getChildren().clear();
		Anchorpanecarshoose.getChildren().add((Node) node);
		
			
	}
	
	//supprimer voiture -----walid ***********************************
	
	public void supprimerVoiture() throws SQLException
	{ 
		
		int n=ctc.getIndex();
		    	System.out.println(n);	
				
				if(n==0)
				{
					  Alert alert=new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Information");
						alert.setContentText("vous devez selectionne une ligne de tableau !");
						alert.show();
					
				}else
				{
					 Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Information");
						alert.setContentText("confiremer la supprission!");
						alert.show();
					handler2=new DBHundler();
					connection2 = handler2.getConnection();
			        String SQL="DELETE  from cars where id="+n;
			        pst2 = connection2.prepareStatement(SQL);
					pst2.executeUpdate();
                    creatPage2();
				}
				
				
		
	}
	//*************

@FXML
private TextField prenom_client;

@FXML
private TextField cin;

@FXML
private TextField nom_client;

@FXML
private DatePicker date_sortie;

@FXML
private TextField telephone;

@FXML
private DatePicker date_retour;

@FXML
private TextField nombre_jours;
	public void enregistrerReservetion()
	{
		int n=ctc.getIndex();
		if(n==0)
		{
			System.out.println("egale zero");
			
		}
		else {
		handler=new DBHundler();
		 String insert= "INSERT INTO reservation(id,id_voiture,Nom,Prenom,Telephone,Cin,Date_sortie,Date_retour,Nombre_jour)"+" VALUES(?,?,?,?,?,?,?,?,?)";
		  
		  
		  connection = handler.getConnection();
		 try {
			pst=connection.prepareStatement(insert);
			int nb=Integer.parseInt(nombre_jours.getText());
			pst.setString(1,null);
			pst.setInt(2,n);
			System.out.print("la valeur de n"+n);
	     	pst.setString(3,nom_client.getText());
			pst.setString(4,prenom_client.getText());
			pst.setString(5,telephone.getText());
			pst.setString(6,cin.getText());
			pst.setString(7,((TextField)date_sortie.getEditor()).getText());
			pst.setString(8,((TextField)date_retour.getEditor()).getText());
			pst.setInt(9,nb);
			System.out.print("reservation ok2");
		  }catch(Exception e)
			{
				
			}
			try {
			pst.executeUpdate();
			
			//***********19/12
			handler3=new DBHundler();
			 String update= "update cars set reserver=1 where id="+n;
			  connection3 = handler3.getConnection();
			  pst3=connection3.prepareStatement(update);
				pst3.executeUpdate();
				 creatPage2();
			
			//**************
			System.out.print("reservation ok");
			}catch(Exception e)
			{
				
			}	
		}
	}

	public void resrverVoiture() throws SQLException, IOException {
		
		int n=ctc.getIndex();
			if(n==0)
			{
				System.out.println("vous devez selectionne une ligne de tableau !");
				  Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Information");
					alert.setContentText("vous devez selectionne une ligne de tableau !");
					alert.show();
			}else
			{
				System.out.println(n);

				    
				
		        Stage stage=new Stage();
			    Parent root = FXMLLoader.load(getClass().getResource("/application/reserver-voitures.fxml"));
			    Scene scene = new Scene(root);
			    stage.setScene(scene);
			    stage.setTitle("Ajouter voiture");
			    stage.show();	 
		
	}
	}
		
	//********************************
public void ajoutetvoiture() {
		try {
		//	Pane pan=new Pane("ajoutervoiture");
			
			  Stage stage=new Stage();
				    Parent root = FXMLLoader.load(getClass().getResource("/application/ajouter-voitures.fxml"));

				    Scene scene = new Scene(root);

				    stage.setScene(scene);
				    stage.setTitle("Ajouter voiture");
				    stage.show();

			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	//button mes voitures
	
	
	
public void creatPage() {
	

	try {
		home2 = FXMLLoader.load(getClass().getResource("/application/voitures.fxml"));
		setNode(home2);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
public void creatPage2() {
		

	
		
		carstablecontrole ctc=new carstablecontrole();
		
		tableau.getChildren().clear();
		//tableau.setTopAnchor(bt, null);
		tableau.setTopAnchor(ctc.startt(), 10.0);
		tableau.setLeftAnchor(ctc.startt(), 40.0);
		tableau.setRightAnchor(ctc.startt(), 50.0);
		tableau.setBottomAnchor(ctc.startt(), 10.0);
		tableau.getChildren().add(ctc.startt());
		//ctc.startt().setPadding(new Insets(15,20, 10,10));
	}
//**********************

	// button commande
	public void creatPagevidange() {
		try {
			vidangepane = FXMLLoader.load(getClass().getResource("/application/vidange.fxml"));
			setNode(vidangepane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void choosecar() {
		

		try {
			Anchorpanecarshoose = FXMLLoader.load(getClass().getResource("/application/carschoose.fxml"));
			setNode(Anchorpanecarshoose);
		} catch (IOException eee) {
			// TODO Auto-generated catch block
			eee.printStackTrace();
		}
		
	}
public void choosecarBMV() {
	

	try {
		AnchorpanechoosecarBMV = FXMLLoader.load(getClass().getResource("/application/bmw.fxml"));
		setNode2(AnchorpanechoosecarBMV);
	} catch (IOException eee) {
		// TODO Auto-generated catch block
		eee.printStackTrace();
	}
	
}
public void choosecarAUDI() {
	

	try {
		AnchorpanechoosecarAUDI = FXMLLoader.load(getClass().getResource("/application/audi.fxml"));
		setNode2(AnchorpanechoosecarAUDI);
	} catch (IOException eee) {
		// TODO Auto-generated catch block
		eee.printStackTrace();
	}
	
}
public void choosecarMERCEDESS() {
	

	try {
		AnchorpanechoosecarMERCEDESS = FXMLLoader.load(getClass().getResource("/application/mercedess.fxml"));
		setNode2(AnchorpanechoosecarMERCEDESS);
	} catch (IOException eee) {
		// TODO Auto-generated catch block
		eee.printStackTrace();
	}
	
}
public void choosecarVW() {
	

	try {
		AnchorpanechoosecarVW = FXMLLoader.load(getClass().getResource("/application/vw.fxml"));
		setNode2(AnchorpanechoosecarVW );
	} catch (IOException eee) {
		// TODO Auto-generated catch block
		eee.printStackTrace();
	}
	
}
	// button voitures sortee
	
	public void creatPageVoiturssorte() {
		
		try {
			home3 = FXMLLoader.load(getClass().getResource("/application/list_resrvatiom.fxml"));
			setNode(home3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void creatPageFiltre() {
		
		try {
			home4 = FXMLLoader.load(getClass().getResource("/application/Filtrer_voitures.fxml"));
			setNode(home4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//********************************
	
public void creatPage3() {
		
		tableau2.getChildren().clear();
		//tableau.setTopAnchor(bt, null);
		tableau2.setTopAnchor(ct.startt(), 10.0);
		tableau2.setLeftAnchor(ct.startt(), 40.0);
		tableau2.setRightAnchor(ct.startt(), 50.0);
		tableau2.setBottomAnchor(ct.startt(), 10.0);
		tableau2.getChildren().add(ct.startt());
		//ctc.startt().setPadding(new Insets(15,20, 10,10));
	}
	//button ajouter voitures
	public void creatPageAjoutervoiture() {
		
		
		try {
			anchorpaneAjouterVoiture = FXMLLoader.load(getClass().getResource("/application/ajouterVoiture.fxml"));
			setNode(anchorpaneAjouterVoiture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
		
		//ctc.startt().setPadding(new Insets(15,20, 10,10));
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@FXML
	 public void seconnecter(ActionEvent eeee) throws IOException
	 {
		Node node = (Node) eeee.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
		 //singup.getScene().getWindow().hide();
		 Parent root = FXMLLoader.load(getClass().getResource("/application/logincar.fxml"));
		 Scene scene = new Scene(root);
		 Stage loginnn=new Stage();
		 loginnn.setScene(scene);
		 loginnn.setTitle("tomobiltii");
		 loginnn.show();
	 }
	
	// button dahbord
			public void creatPagedashbord() {
				try {
					anchorpanedashbord = FXMLLoader.load(getClass().getResource("/application/dashboard.fxml"));
					setNode(anchorpanedashbord);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
}

