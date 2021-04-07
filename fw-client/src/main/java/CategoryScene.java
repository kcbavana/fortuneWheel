import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

public class CategoryScene implements Initializable {
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private Text title;
	
	@FXML
	private HBox titleBox;

	@FXML
	private HBox categoryBox;
	
	@FXML
	private VBox animalBox;
	
	@FXML
	private VBox placesBox;
	
	@FXML
	private VBox moviesBox;
	
	@FXML
	private static Button animalsButton;
	
	@FXML
	private static Button placesButton;
	
	@FXML
	private static Button moviesButton;
	
	@FXML
	private Text animalsText;
	
	@FXML
	private Text animalsWin;
	
	@FXML
	private Text animalsLoses;
	
	@FXML
	private Text moviesText;
	
	@FXML
	private Text moviesWin;
	
	@FXML
	private Text moviesLoses;
	
	@FXML
	private Text placesText;
	
	@FXML
	private Text placesWin;
	
	@FXML
	private Text placesLoses;
	
	@FXML
	private HBox menuBox;
	
	//@FXML
	//private Button backButton;
	
	@FXML
	private Button exitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		animalsWin.setText("Wins: " + Client.animalWins);
		animalsLoses.setText("Losses: " + Client.animalLoses);
		moviesWin.setText("Wins: " + Client.moviesWins);
		moviesLoses.setText("Losses: " + Client.moviesLoses);
		placesWin.setText("Wins: " + Client.placesWins);
		placesLoses.setText("Losses: " + Client.placesLoses);
	}
	
	public void animalsMethod(ActionEvent e) throws IOException,InterruptedException {
		if (Client.animalWins == 1)
		{
			return;
		}
		Client.curCategory = "Animals";
		Client.send("Animals");
		//Thread.sleep(3000);
		
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	public void moviesMethod(ActionEvent e) throws IOException {
		if (Client.moviesWins == 1)
		{
			return;
		}
		Client.curCategory = "Movies";
		Client.send("Movies");
		// Thread.sleep(3000);
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	public void placesMethod(ActionEvent e) throws IOException {
		if (Client.placesWins == 1)
		{
			return;
		}
		Client.curCategory = "Places";
		Client.send("Places");
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	/*
	public void backMethod(ActionEvent e) throws IOException {
		changeScene(e,"/FXML/ClientIntro.fxml","/styles/ClientIntro.css");
	}*/
	
	public void exitMethod(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		stage.close();
		Platform.exit();
        System.exit(0);
	}
	
	public void changeScene(ActionEvent e, String fxml, String css) throws IOException{
		// Retrieve Stage from ActionEvent
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		// Populate root of Scene Graph from .xml and init Scene
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(root, 800, 800);
		// Apply CSS styling
		scene.getStylesheets().add(css);
		// Change current Scene on the Stage
		stage.setScene(scene);
		stage.show();
	}
	
	// Update CategoryScene after a win/loss
	//Disable completed categories
	public void updateCategoryScene()
	{
			if(Client.animalWins == 1)
			{
				animalsWin.setText("COMPLETED");
				//animalsButton.setVisible(false);
			}
			if(Client.moviesWins == 1)
			{
				moviesWin.setText("COMPLETED");
				//moviesButton.setVisible(false);
			}
			if(Client.placesWins == 1)
			{
				placesWin.setText("COMPLETED");
				//placesButton.setVisible(false);
			}

	}
	
}
