package com.example.enjoypdf;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Hello world!
 *
 */
public class App extends Application {

	public static Scene mainScene;
	public static Stage mainStage;

	public static Integer status;

	public static void main(String[] args) throws IOException {
		System.out.println("Enjoy PDF!");

		launch(args);
	}

	public static Stage getStage() {
		return mainStage;
	}

	public static Scene getScene() {
		return mainScene;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.mainStage = stage;
		this.status = 0;

		String fxmlFile = "/fxml/index.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
		mainStage.setTitle("Объединение PDF");
		mainScene = new Scene(root);
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);

		mainStage.show();

		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				if (status == 1) {
					event.consume();

					App.mainStage.setTitle("Объединение PDF");
					App.mainStage.setScene(App.mainScene);
					App.mainStage.show();
					App.status = 0;

				}
				if (status == 2) {
					event.consume();

					App.mainStage.setTitle("Объединение PDF");
					App.mainStage.setScene(App.mainScene);
					App.mainStage.show();
					App.status = 0;

				}

			}

		});
	}
}
