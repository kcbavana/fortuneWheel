import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerIntro implements Initializable {
	
	public static Server server;
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private VBox centerBox;
	
	@FXML
	private Text title;
	
	@FXML
	private Text subTitle;
	
	@FXML
	private TextField portField;
	
	@FXML 
	private HBox bottomBox;
	
	@FXML
	private Button connectButton;
	
	@FXML
	private Button exitButton;
	
	public static String portNumber;
	private String portNumb = "5555";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
	
	public String checkDefault(String checkStr) {
		if(checkStr.equals("Enter port (default: 5555)")) {
			return portNumb;
		}
		else if(checkStr.equals(" ")) {
			return portNumb;
		}
		else {
			return checkStr;
		}
	}
	
	public String updateServerLogIntro(String data) {
		// Strip Client Info 
		// Server logs
		if(data.startsWith("l"))
		{
			return data.substring(1);
		}
		// New word
		else if(data.charAt(0) == 'w') {
			return "Word Size is: " + data.substring(1);
		}
		// endGame check
		else if (data.charAt(0) == 'e')
		{
			String[] result = data.substring(1).split(":");
			switch(result[0])
			{
				case "win": return "Correctly Guessed Word:" + result[1];
				case "loss": return "Loss:" + result[1];
				case "wingame": return "Win Game!:" + result[1];
				case "losegame": return "Lose Game:" + result[1];
				default:	return "continue";
						
			}
		}
		// process Guess
		else if(data.charAt(0) == 'g')
		{
			String[] clientInfo = data.split(":");
			String guess = data.substring(1);
			// loss
			if(guess.contains("-0"))
			{
				return "continue";
			}
			else if(guess.contains("-"))
				return "Incorrect Guess:" + clientInfo[1];
			else
			{
				return "Correct Guess:" + clientInfo[1];
			/*
			else if(Integer.valueOf(guess) >= 0) {
				return "Correct Guess";
			}
			else if(Integer.valueOf(guess) < 0) {
				return "Incorrect Guess";*/
			}
		}
		return data;
	}

	/*
	 * Connect Button initializes a Server object listening on the 
	 * specified port
	 */
	public void connectMethod(ActionEvent e) throws IOException {
		portNumber = portField.getText();
		portNumber = checkDefault(portNumber);
		// Get info on current Scene/Window
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		//Scene scene = node.getScene();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ServerScene.fxml"));
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add("/styles/ServerScene.css");
		stage.setScene(scene);
		ServerScene controller = loader.getController();
		
		server = new Server(data -> {
			Platform.runLater(() -> {
				controller.updateServerLogs(updateServerLogIntro(data.toString()));
			});
		});
		
		stage.show();		
	}
	
	/*
	 * Given ActionEvent and Strings to .fxml and .css files, change the current Scene
	 */
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
	
	public void exitMethod(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		stage.close();
		Platform.exit();
        System.exit(0);
	}
}
