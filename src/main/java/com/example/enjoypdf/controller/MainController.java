package com.example.enjoypdf.controller;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.example.enjoypdf.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {
	

@FXML
private Button clearButton, mergeButton, selectButton1, selectButton2, selectButton3;

@FXML
private TextField link1, link2, link3; 

@FXML
private Label errorLabel;


	//merge https://coderlessons.com/tutorials/java-tekhnologii/vyuchit-pdfbox/pdfbox-obedinenie-neskolkikh-dokumentov-pdf
	
	
	public void clearAction(){
		link1.setText("");
		link2.setText("");
		link3.setText("");
		errorLabel.setVisible(false);
	}
	
	public void selectAction1(){
	
		Stage primaryStage = (Stage) selectButton1.getScene().getWindow();
		final FileChooser fileChooser1= new FileChooser();
		FileChooser.ExtensionFilter xlsxfilter = new FileChooser.ExtensionFilter("pdf files(*.pdf)", "*.pdf");
		fileChooser1.getExtensionFilters().add(xlsxfilter);
		 File file = fileChooser1.showOpenDialog(primaryStage);
	     if (file != null) {
	    	 link1.setText(file.getPath()); 
	     }
	}
	
	public void selectAction2(){
		Stage primaryStage = (Stage) selectButton2.getScene().getWindow();
		final FileChooser fileChooser2= new FileChooser();
		FileChooser.ExtensionFilter xlsxfilter = new FileChooser.ExtensionFilter("pdf files(*.pdf)", "*.pdf");
		fileChooser2.getExtensionFilters().add(xlsxfilter);
		 File file = fileChooser2.showOpenDialog(primaryStage);
	     if (file != null) {
	    	 link2.setText(file.getPath()); 
	     }
	}
	
	public void selectAction3(){
		Stage primaryStage = (Stage) selectButton3.getScene().getWindow();
		final DirectoryChooser directoryChooser = new DirectoryChooser();
	
		File file = directoryChooser.showDialog(primaryStage);
	     if (file != null) {
	    	 link3.setText(file.getPath()); 
	     }
	}
	
	
	public void mergeAction() throws IOException{
	
		if(link1.getText().equals("")){
			errorLabel.setText("Ошибка. Не указан первая ссылка");
			errorLabel.setVisible(true);
			return;
		}
		
		if(link2.getText().equals("")){
			errorLabel.setText("Ошибка. Не указана вторая ссылка");
			errorLabel.setVisible(true);
			return;
		}
		
		if(link3.getText().equals("")){
			errorLabel.setText("Ошибка. Не указано место сохранения");
			errorLabel.setVisible(true);
			return;
		}
		
		File file1 = new File(link1.getText()); 
		File file2 = new File(link2.getText()); 
		
	    String  newfile = link3.getText() + "\\merged.pdf";
		
		PDFMergerUtility PDFmerger = new PDFMergerUtility(); 
		PDFmerger.setDestinationFileName(newfile);
		
		PDFmerger.addSource(file1);
		PDFmerger.addSource(file2);
		
		PDFmerger.mergeDocuments();
	
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Результат");
	
	    alert.setHeaderText(null);
	    alert.setContentText("Файл успешно создан \n " + newfile);
	
	    alert.showAndWait();
		
	}
	
	public void settingsAction() throws IOException {
			App.status=1;
			
			String fxmlFile = "/fxml/settings.fxml";
	        FXMLLoader loader = new FXMLLoader();
	        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
	        App.mainStage.setTitle("Объединение PDF - Настройки");
	        App.mainStage.setScene(new Scene(root));
	        App.mainStage.setResizable(false);

	        App.mainStage.show();
	}
	

}
