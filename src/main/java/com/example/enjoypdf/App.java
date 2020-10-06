package com.example.enjoypdf;

import java.io.File;
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Hello world!
 *
 */
public class App extends Application {
	
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Enjoy PDF!" );
        
        launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		   String fxmlFile = "/fxml/index.fxml";
	        FXMLLoader loader = new FXMLLoader();
	        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
	        stage.setTitle("Объединение PDF");
	        stage.setScene(new Scene(root));
	        stage.setResizable(false);

	        stage.show();
	        
		
	}
}
