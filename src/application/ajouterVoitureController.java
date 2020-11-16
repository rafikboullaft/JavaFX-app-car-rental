package application;




import java.awt.image.BufferedImage;
//import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import DBConnection.DBHundler;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ajouterVoitureController implements Initializable{
	
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button ajouterV;

	    @FXML
	    private Button annulerV;

	    //@FXML
	   // private Button imageV;

	    @FXML
	    private TextField modeleV;

	    @FXML
	    private DatePicker visiteV;

	    @FXML
	    private TextField marqueV;

	    @FXML
	    private TextField kilometrageV;

	    @FXML
	    private TextField prixV;

	    @FXML
	    private TextField carburantV;
	    
	    @FXML
	    private ImageView imageView;
	    
	    hommeControle c=new hommeControle();
	    carstablecontrole ctc=new carstablecontrole();
  
    private Connection connection;
    private DBHundler handler;
    private PreparedStatement pst;
    private Image img;
    File file;
    FileInputStream fis;
	String path;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		handler=new DBHundler();
	}

	//****************modifier voiture
	
	public void modifier() throws SQLException
	{
		int n=ctc.getIndex();
		String rqt= "select kilometrage,prix,visite from cars where id="+n;
		connection = handler.getConnection();
		pst=connection.prepareStatement(rqt);
		ResultSet rs=pst.executeQuery();
		
		//prixV.setString(rs.getSring("prix"));
		
	}
	//affichage de fentre d'ajou..

	public void ajouterVoitureDB(ActionEvent ee) throws FileNotFoundException
	{
		
		String insert= "INSERT INTO cars(id,marque,modele,kilometrage,visite,carburant,prix,reserver,image)"+" VALUES(?,?,?,?,?,?,?,?,?)";
		connection = handler.getConnection();
	   System.out.println(path);
		try {
			//************
		
			//************
			
			double prix=Double.parseDouble(prixV.getText());
			
		
			pst=connection.prepareStatement(insert);
			pst.setString(1,null);
			pst.setString(2,marqueV.getText());
	    	pst.setString(3,modeleV.getText());
			pst.setString(4,kilometrageV.getText());
			pst.setString(5,((TextField)visiteV.getEditor()).getText());
			pst.setString(6,carburantV.getText());
			pst.setDouble(7,prix);
			pst.setString(8,"0");
			pst.setString(9,path);
			pst.executeUpdate();
			c.creatPage2();
			System.exit(0);
		   //fis=new FileInputStream(file);
		   
			//InputStream is=new FileInputStream (new File(path));
		   
		   //pst.setBinaryStream(9,(InputStream)fis);
			//pst.setBlob(9,is);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	//button annuler
	public void annuler(ActionEvent eee) {
		
	System.exit(0);
	
	System.out.println("img");
	}
	//choisire image
		public void choisirimage(ActionEvent ee) {
			
			FileChooser choose=new 	FileChooser();
			ExtensionFilter filter=new ExtensionFilter("*.IMAGE","jpg","gif","png");
			 choose.setSelectedExtensionFilter(filter);
			file=choose.showOpenDialog(null);
			path=file.getAbsolutePath();
			if(file !=null)
			{
			   //img=new Image(file.toURI().toString(),186,124,true,true);
		       // imageView=new ImageView(img);
		        
		        
				//img=new Image(file.getAbsolutePath());
		        //imageView.setPreserveRatio(true);
		    
		           
	            try {
	                BufferedImage bufferedImage = ImageIO.read(file);
	                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	                
	                imageView.setImage(image);
	            } catch (IOException ex) {
	                
	            }
		 
		    System.out.println(file.toURI().toString());
			}
		}
//****************

}

