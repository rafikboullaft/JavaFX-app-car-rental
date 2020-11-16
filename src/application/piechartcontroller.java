package application;

import java.awt.geom.Arc2D.Double;
import java.lang.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import DBConnection.DBHundler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class piechartcontroller implements Initializable{

	@FXML
    private AnchorPane anchorpanedashbord;

    @FXML
    private Label Vdisponible;

    @FXML
    private Label Vreservee;

    @FXML
    private Label Nclients;

    @FXML
    private Label Venattente;

    @FXML
    private Label Rjour;

    @FXML
    private Label Rsemaine;

    @FXML
    private Label Rmois;

    @FXML
    private Label Rannee;

    @FXML
    private PieChart pchart1;

    @FXML
    private BarChart<?, ?> revenuesBars;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    //connection 
    private Connection connection2;
	private DBHundler handler2;
	private PreparedStatement pst2;
	int nbrVoitures=0;
	int nbrVreserver=0;
	double RJanvier=0,RFevrier=0,RMars=0,RAvril=0,RMai=0,RJui=0,RJuillet=0,RAout=0,RSeptembre=0,ROctobre=0,RNovembre=0,RDecembr=0;
	int thismonth;
	double thisyear;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub
	
		int thismonth =Calendar.getInstance().get(Calendar.MONTH);
		thismonth=thismonth+1;
		System.out.println("la date today .."+thismonth);
		handler2=new DBHundler();
		calculeRevenus();
		double a=nbrV(),b=nbrVReserver();
		double pa=(b/a)*100;
		double pb=((a-b)/a)*100;
		ObservableList<PieChart.Data> pieChartData =
		            FXCollections.observableArrayList(
		            new PieChart.Data("reserve",pa ),
		            new PieChart.Data("non reserve", pb));

		 pchart1.setData(pieChartData);
		 XYChart.Series barc = new XYChart.Series<>();
		 barc.getData().add( new XYChart.Data("Janvier", RJanvier));
		 barc.getData().add( new XYChart.Data("Fevrier", RFevrier));
		 barc.getData().add( new XYChart.Data("Mars",RMars));
		 barc.getData().add( new XYChart.Data("Avril",RAvril));
		 barc.getData().add( new XYChart.Data("Mai",RMai));
		 barc.getData().add( new XYChart.Data("Juin",RJui));
		 barc.getData().add( new XYChart.Data("Juillet",RJuillet));
		 barc.getData().add( new XYChart.Data("Aout", RAout));
		 barc.getData().add( new XYChart.Data("Septembre",RSeptembre));
		 barc.getData().add( new XYChart.Data("Octobre",ROctobre));
		 barc.getData().add( new XYChart.Data("Novembre", RNovembre));
		 barc.getData().add( new XYChart.Data("Decembre", RDecembr));
		 
		 revenuesBars.getData().addAll(barc);
		 System.out.println("nomres de voitures est "+(b/a)); 
		 System.out.println("nomres de voitures reserve est  "+((a-b)/a)); 
		 Vdisponible.setText("VD :"+ (int)(a-b));
		 Vreservee.setText("VR :"+ (int)b);
		 Nclients.setText("clients :"+ (int)b);
	
		 thisyear=RJanvier+RFevrier+RMars+RAvril+RMai+RJui+RJuillet+RAout+RSeptembre+ROctobre+RNovembre+RDecembr;
		 switch(thismonth) {
		  case 1:
			  Rmois.setText(RJanvier+"MAD");
		    break;
		  case 2:
			  Rmois.setText(RFevrier+"MAD");
		    break;
		  case 3:
			  Rmois.setText(RMars+"MAD");
			    break;
		  case 4:
			  Rmois.setText(RAvril+"MAD");
			    break;
		  case 5:
			  Rmois.setText(RMai+"MAD");
			    break;
		  case 6:
			  Rmois.setText(RJui+"MAD");
			    break;
		  case 7:
			  Rmois.setText(RJuillet+"MAD");
			    break;
	      case 8:
	    	  Rmois.setText(RAout+"MAD");
			    break;
			    
	      case 9:
	    	  Rmois.setText(RSeptembre+"MAD");
			    break;
	      case 10:
	    	  Rmois.setText(ROctobre+"MAD");
			    break;
			    
	      case 11:
	    	  Rmois.setText(RNovembre+"MAD");
			    break;
	       case 12:
	    	   Rmois.setText(RDecembr+"MAD");
			    break;
		  default:
		    // code block
		}
		 Rannee.setText(thisyear+"MAD");
	}
	
    public int nbrV() {
    	connection2 = handler2.getConnection();
        String SQL="SELECT * from cars";
        try {
			pst2 = connection2.prepareStatement(SQL);
			ResultSet rs;
			rs = pst2.executeQuery();
			while (rs.next()) {
				nbrVoitures++;
				
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	return nbrVoitures;
    	
    }
    public int nbrVReserver() {
    	connection2 = handler2.getConnection();
        String SQL="SELECT * from cars WHERE reserver=1";
        try {
			pst2 = connection2.prepareStatement(SQL);
			ResultSet rs;
			rs = pst2.executeQuery();
			while (rs.next()) {
				nbrVreserver++;
				
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	return nbrVreserver;
    	
    }
    
    public void calculeRevenus()
    {
    	connection2 = handler2.getConnection();
        String SQL="SELECT * from reservation";
        try {
			pst2 = connection2.prepareStatement(SQL);
			ResultSet rs;
			rs = pst2.executeQuery();
			while (rs.next()) {
				int nbj=rs.getInt("Nombre_jour");
				int idV=rs.getInt("id_voiture");
				Calendar cal = Calendar.getInstance();
				//12/19
				String lastCrawlDate = rs.getString("Date_sortie");
				try {
					Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(lastCrawlDate);
					cal.setTime(utilDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//
				//cal.setTime(rs.getDate("Date_sortie"));
				int month = cal.get(Calendar.MONTH);
				//int day = cal.get(Calendar.DAY_OF_MONTH);
				//int year = cal.get(Calendar.YEAR);
				month=month+1;
				System.out.println("month :"+month);
				//System.out.println(day);
				//System.out.println(year);
				System.out.println("id voiture :"+idV);
				System.out.println("nbr de jour :"+nbj);
				//
			
				
				String SQL3="SELECT * from cars WHERE id="+idV;
				try {
				pst2 = connection2.prepareStatement(SQL3);
				ResultSet rs3;
				rs3 = pst2.executeQuery();
				while (rs3.next()) {	
					     double Rprix=rs3.getDouble("prix");
						System.out.println(rs3.getDouble("prix"));
						switch(month) {
						  case 1:
							  RJanvier=rs.getInt("Nombre_jour")*Rprix;
						    break;
						  case 2:
							  RFevrier=rs.getInt("Nombre_jour")*Rprix;
						    break;
						  case 3:
							  RMars=rs.getInt("Nombre_jour")*Rprix;
							    break;
						  case 4:
							  RAvril=rs.getInt("Nombre_jour")*Rprix;
							    break;
						  case 5:
							  RMai=rs.getInt("Nombre_jour")*Rprix;
							    break;
						  case 6:
							  RJui=rs.getInt("Nombre_jour")*Rprix;
							    break;
						  case 7:
							  RJuillet=rs.getInt("Nombre_jour")*Rprix;
							    break;
					      case 8:
					    	  RAout=rs.getInt("Nombre_jour")*Rprix;
							    break;
							    
					      case 9:
					    	  RSeptembre=rs.getInt("Nombre_jour")*Rprix;
							    break;
					      case 10:
					    	  ROctobre=rs.getInt("Nombre_jour")*Rprix;
							    break;
							    
					      case 11:
					    	  RNovembre=rs.getInt("Nombre_jour")*Rprix;
							    break;
					       case 12:
					    	   RDecembr=rs.getInt("Nombre_jour")*Rprix;
							    break;
						  default:
						    // code block
						}
						
						System.out.println( "revenue du septembre  :"+RSeptembre);
						
				}
				}catch (SQLException e22) {
					// TODO Auto-generated catch block
					e22.printStackTrace();
					System.out.println("prix zalo");
					
				}			
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    }
    public void calculerevenumois() {
    	
    	
    }
    

}
