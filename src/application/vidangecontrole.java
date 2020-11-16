package application;

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

import com.jfoenix.controls.JFXButton;

import DBConnection.DBHundler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class vidangecontrole implements Initializable{
	@FXML
    private BarChart<?, ?> vidangeBar;
	@FXML
    private BarChart<?, ?> assuranceBar;
	@FXML
	private Label carneedV;
	@FXML
	private Label carneedV2;
	 @FXML
	    private JFXButton btmisajour;
	
	private Connection connection2;
	private DBHundler handler2;
	private PreparedStatement pst2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		int thismonth =Calendar.getInstance().get(Calendar.MONTH);
		thismonth=thismonth+1;
		int periode;
		int monthvisite;
		
		handler2=new DBHundler();
		connection2 = handler2.getConnection();
        String SQL="SELECT * from cars";
        
        try {
        	
        	
        			
			pst2 = connection2.prepareStatement(SQL);
			ResultSet rs;
			rs = pst2.executeQuery();
			XYChart.Series barc = new XYChart.Series<>();
			XYChart.Series barb = new XYChart.Series<>();
			String str="";
			String strassurance="";
			while (rs.next()) {
				String MarMod=rs.getString("marque");
				String Mod=rs.getString("modele");
				Calendar cal = Calendar.getInstance();
				//12/19
				String lastCrawlDate = rs.getString("visite");
				try {
					Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(lastCrawlDate);
					cal.setTime(utilDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 monthvisite = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				//int year = cal.get(Calendar.YEAR);
				 periode=thismonth-monthvisite;
				
				Double kilo=Double.parseDouble(rs.getString("kilometrage"));
				if (kilo>10000) {
					
					str+=MarMod+"_"+Mod+"  \n";
				}
				barc.getData().add( new XYChart.Data(MarMod, kilo));
				if (periode>0) {
					barb.getData().add( new XYChart.Data(MarMod,periode));
				}
				if (periode<0) {
					periode=(12+thismonth)-monthvisite;
					barb.getData().add( new XYChart.Data(MarMod,periode));
				}
				if (periode>3) {
					strassurance+=MarMod+"_"+Mod+" Assurance le "+day+"  \n";
				}
	
			}
			vidangeBar.getData().addAll(barc);
			assuranceBar.getData().addAll(barb);
			carneedV.setText(str);
			carneedV2.setText(strassurance);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}
	public void  misajour() {
		
		handler2=new DBHundler();
		connection2 = handler2.getConnection();
        String SQL="UPDATE `cars` SET `kilometrage` = '0' WHERE `cars`.`kilometrage` > 10000";
        try {
			pst2 = connection2.prepareStatement(SQL);
			 pst2.executeUpdate();
			}catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        
		
	}
	/*
public void  misajourassurance() {
		
		handler2=new DBHundler();
		connection2 = handler2.getConnection();
        String SQL="UPDATE `cars` SET `visite` = "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR)+" WHERE `cars`.`visite` > "+10000;
        try {
			pst2 = connection2.prepareStatement(SQL);
			 pst2.executeUpdate();
			}catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
	}*/

}
