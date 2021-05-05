package com.example.enjoypdf.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import com.example.enjoypdf.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SeparateController {
	@FXML
	private Button clearButton, selectButton1, selectButton3, separatePdfButton, separatePngButton, backButton;

	@FXML
	private TextField link1, link3;

	@FXML
	private Label errorLabel;

	public void selectFile() {

		Stage primaryStage = (Stage) selectButton1.getScene().getWindow();
		final FileChooser fileChooser1 = new FileChooser();
		FileChooser.ExtensionFilter xlsxfilter = new FileChooser.ExtensionFilter("pdf files(*.pdf)", "*.pdf");
		fileChooser1.getExtensionFilters().add(xlsxfilter);
		File file = fileChooser1.showOpenDialog(primaryStage);
		if (file != null) {
			link1.setText(file.getPath());
		}
	}

	public void selectAction3() {
		Stage primaryStage = (Stage) selectButton3.getScene().getWindow();
		final DirectoryChooser directoryChooser = new DirectoryChooser();

		File file = directoryChooser.showDialog(primaryStage);
		if (file != null) {
			link3.setText(file.getPath());
		}
	}

	public void clearAction() {
		link1.setText("");
		link3.setText("");
		errorLabel.setVisible(false);
	}

	public void backAction() {
		App.getStage().setScene(App.getScene());
	}

	public void actionPdfButton() {
		if (link1.getText().equals("")) {
			errorLabel.setText("Ошибка. Не указан ссылка на файл");
			errorLabel.setVisible(true);
			return;
		}

		if (link3.getText().equals("")) {
			errorLabel.setText("Ошибка. Не указано место сохранения");
			errorLabel.setVisible(true);
			return;
		}

		String urlfile = link1.getText();
		String path = link3.getText();
		PDDocument document = null;
		File newfile = new File(urlfile);

		try {
			document = PDDocument.load(newfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int page = 0; page < document.getNumberOfPages(); ++page) {
			PDDocument document_single = new PDDocument();

			document_single.addPage(document.getPage(page));
			try {
				document_single.save(path + "\\" + newfile.getName() + "-" + (page + 1) + ".pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void actionPngButton() {

		if (link1.getText().equals("")) {
			errorLabel.setText("Ошибка. Не указан ссылка на файл");
			errorLabel.setVisible(true);
			return;
		}

		if (link3.getText().equals("")) {
			errorLabel.setText("Ошибка. Не указано место сохранения");
			errorLabel.setVisible(true);
			return;
		}

		String urlfile = link1.getText();
		String path = link3.getText();
		PDDocument document = null;
		File newfile = new File(urlfile);

		try {
			document = PDDocument.load(newfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		for (int page = 0; page < document.getNumberOfPages(); ++page) {
			BufferedImage bim = null;
			try {
				bim = pdfRenderer.renderImageWithDPI(page, 75, ImageType.RGB);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// suffix in filename will be used as the file format
			try {
				ImageIOUtil.writeImage(bim, path + "\\" + newfile.getName() + "-" + (page + 1) + ".png", 300);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// сделать окно с прогрессом
	}

}
