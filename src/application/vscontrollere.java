package application;

import java.awt.Insets;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnection.DBHundler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class vscontrollere extends Application{
	@FXML
    private AnchorPane affichetable;
//TABLE VIEW AND DATA
private ObservableList<ObservableList> data;

private TableView tableview;
private Connection connection2;
private DBHundler handler2;
private PreparedStatement pst2;

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
        String SQL="SELECT * from reservation";
        //SQL FOR SELECTING ALL OF CUSTOMER
        
        //ResultSet
        pst2 = connection2.prepareStatement(SQL);
		ResultSet rs=pst2.executeQuery();

        /**
         * ********************************
         * TABLE COLUMN ADDED DYNAMICALLY *
         *********************************
         */
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
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

        /**
         * ******************************
         * Data added to ObservableList *
         *******************************
         */
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            data.add(row);

        }

        //FINALLY ADDED TO TableView
        tableview.setItems(data);
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error on Building Data");
    }
}


public TableView startt(){
	
	//Stage stage=new Stage();
    //TableView
    tableview = new TableView();
    buildData();
    
    
    // BYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY////
    tableview.setMinSize(1165, 540);
    tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    // BYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY////

    /*Main Scene
    Scene scene = new Scene(tableview);

    stage.setScene(scene);
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            Platform.exit();
            System.exit(0);
        }
    });
    
    stage.show();
    */
	return tableview;
}




}

/*
public class vscontrollere implements Initializable{
	@FXML
    private TableView<tableVSControle> tableVS;

    @FXML
    private TableColumn<tableVSControle, String> colVoiture;

    @FXML
    private TableColumn<tableVSControle, String> colNom;

    @FXML
    private TableColumn<tableVSControle, String> colDated;

    @FXML
    private TableColumn<tableVSControle, String> colPermis;
    private Connection connection2;
    private DBHundler handler2;
    private PreparedStatement pst2;
    
    //ObservableList<tableVSControle> oblist=FXCollections.observableArrayList();
    @FXML
     ObservableList<tableVSControle> oblist=FXCollections.observableArrayList();

	
	public void initialize(URL location, ResourceBundle resources) {
		handler2=new DBHundler();
		connection2 = handler2.getConnection();
        String reqq="SELECT * from clients";
       
        try {
			pst2 = connection2.prepareStatement(reqq);
			ResultSet rs=pst2.executeQuery();
			 while(rs.next()) {
				 oblist.add(new tableVSControle(rs.getString("voitures"),
						 rs.getString("nom"),
						 rs.getString("datedebut"),
						 rs.getString("permes")));
				 System.out.println(rs.getString(1));
				 System.out.println(rs.getString(2));
				 System.out.println(rs.getString(3));
				 System.out.println(rs.getString(4));
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    	colVoiture.setCellValueFactory(new PropertyValueFactory<tableVSControle, String>("voiture"));
    	colNom.setCellValueFactory(new PropertyValueFactory<tableVSControle, String>("nom"));
    	colDated.setCellValueFactory(new PropertyValueFactory<tableVSControle, String>("datedeb"));
    	colPermis.setCellValueFactory(new PropertyValueFactory<tableVSControle, String>("permis"));
    	tableVS.setItems(oblist);
    	
    	System.out.println(tableVS);
    	
		
	    
		
	}

}
*/