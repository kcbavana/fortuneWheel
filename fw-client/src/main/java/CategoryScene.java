import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CategoryScene implements Initializable {
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private Text title;

	@FXML
	private HBox categoryBox;
	
	@FXML
	private VBox animalBox;
	
	@FXML
	private VBox placesBox;
	
	@FXML
	private VBox moviesBox;
	
	@FXML
	private Button animalsButton;
	
	@FXML
	private Button placesButton;
	
	@FXML
	private Button moviesButton;
	
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
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button exitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		animalsWin.setText("Wins: " + Client.animalWins);
		animalsLoses.setText("Looses: " + Client.animalLoses);
		moviesWin.setText("Wins: " + Client.moviesWins);
		moviesLoses.setText("Looses: " + Client.moviesLoses);
		placesWin.setText("Wins: " + Client.placesWins);
		placesLoses.setText("Looses: " + Client.placesLoses);
        
	}
	
	public void animalsMethod(ActionEvent e) throws IOException {
		Client.curCategory = "Animals";
		Client.send("Animals");
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	public void moviesMethod(ActionEvent e) throws IOException {
		Client.curCategory = "Movies";
		Client.send("Movies");
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	public void placesMethod(ActionEvent e) throws IOException {
		Client.curCategory = "Places";
		Client.send("Places");
		changeScene(e,"/FXML/ClientGameScene.fxml","/styles/ClientGameScene.css");
	}
	
	public void backMethod(ActionEvent e) throws IOException {
		changeScene(e,"/FXML/ClientIntro.fxml","/styles/ClientIntro.css");
	}
	
	public void exitMethod(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		stage.close();
	}
	
	public void changeScene(ActionEvent e, String fxml, String css) throws IOException{
		// Retrieve Stage from ActionEvent
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		// Populate root of Scene Graph from .xml and init Scene
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(root, 500, 500);
		// Apply CSS styling
		scene.getStylesheets().add(css);
		// Change current Scene on the Stage
		stage.setScene(scene);
		stage.show();
	}
}
