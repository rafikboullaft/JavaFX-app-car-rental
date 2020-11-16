package application;

import javafx.beans.property.SimpleStringProperty;

public class tableVSControle{
	private final SimpleStringProperty voiture;
	private final SimpleStringProperty permis;
	private final SimpleStringProperty nom;
	private final SimpleStringProperty datedeb;
	public tableVSControle(String voiture1,String nom1,String datedeb1,String permis1){
		this.datedeb=new SimpleStringProperty(datedeb1);
		this.nom=new SimpleStringProperty(nom1);
		this.permis=new SimpleStringProperty(permis1);
		this.voiture=new SimpleStringProperty(voiture1);
	}
	/*
	public SimpleStringProperty getnom() {
		return this.nom;
	}
	public void setnom(SimpleStringProperty nom) {
		nom.set(nom);
	}
	public SimpleStringProperty getvoiture() {
		return this.voiture;
	}
	public void setvoiture(SimpleStringProperty voiture) {
		this.voiture=voiture;
	}
	public SimpleStringProperty getdatedeb() {
		return this.datedeb;
	}
	public void setdatedeb(SimpleStringProperty db) {
		this.datedeb=db;
	}
	public SimpleStringProperty getpermis() {
		return this.permis;
	}
	public void setpermis(SimpleStringProperty prs) {
		this.permis=prs;
	}*/

}
