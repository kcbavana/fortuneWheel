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
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class ClientGameScene implements Initializable {
	
	PauseTransition pause = new PauseTransition(Duration.seconds(2));
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private VBox titleVBox;
	
	@FXML
	private HBox titleBox;
	
	@FXML
	private Text title;
	
	@FXML
	private Text winsText;
	
	@FXML
	private Text currentCategory;
	
	@FXML
	private Text losesText;
	
	@FXML
	private VBox gameBox;
	
	@FXML
	private Text gameText;
	
	@FXML
	private HBox sendBox;
	
	@FXML
	private TextField sendCharBox;
	
	@FXML
	private Button sendButton;
	
	@FXML
	private HBox menuBox;
	
	@FXML
	private Button newGameButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Text guessesText;
	
	String initValue;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentCategory.setText("Current Category: " + Client.curCategory);
		winsText.setText("Total Wins: " + Client.totalWins);
		losesText.setText("Total Loses: " + Client.totalLoses);
		//initValue = String.valueOf(Client.guessArray);
		gameText.setText("Guess one letter");
		pause.setOnFinished(e->{
			System.out.println("This code runs");
			//gameText.setText(String.valueOf(Client.guessArray));
			gameText.setText(spaceGuessArray());
			});
		pause.play();
		guessesText.setText("6");
		
	}
	
	public void newGameMethod(ActionEvent e) throws IOException {
		// TODO: call game.newGame()
		changeScene(e,"/FXML/ClientIntro.fxml","/styles/ClientIntro.css");
	}
	
	public void guessMethod() {
		// Send guess
		// TODO: error check
		Client.currentGuess = sendCharBox.getText().charAt(0);
		Client.send(sendCharBox.getText());
		// Update Scene with updated Client info
		gameText.setText(String.valueOf(spaceGuessArray()));
		guessesText.setText(String.valueOf(Client.guesses));
		//initValue = String.valueOf(Client.guessArray);
		//gameText.setText(initValue);
		// TODO: checkForSceneChange()
	}
	
	public void exitMethod(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		stage.close();
		//TODO: close client connection
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
	
	// Add spaces between guessArray characters to display
	public String spaceGuessArray()
	{
		// create array with 2x size
		char[] display = new char[Client.guessArray.length *2];
		// add guessArray chars to every other letter in display
		for(int i = 0; i < Client.guessArray.length; i++)
		{
			display[i*2] = Client.guessArray[i];
		}
		
		return String.valueOf(display);
	}
	
}
