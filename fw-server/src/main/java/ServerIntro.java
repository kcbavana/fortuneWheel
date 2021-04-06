import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
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
		if(checkStr.equals("Enter the port number and press connect: ")) {
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
			switch(data.substring(1))
			{
				case "win": return "Correctly Guessed Word";
				case "loss": return "Loss";
				case "wingame": return "Win Game!";
				case "losegame": return "Lose Game";
				default:	return "continue";
						
			}
		}
		// process Guess
		else if(data.charAt(0) == 'g')
		{
			String guess = data.substring(1);
			// loss
			if(guess.equals("-0"))
			{
				return "continue";
			}
			else if(Integer.valueOf(guess) >= 0) {
				return "Correct Guess";
			}
			else if(Integer.valueOf(guess) < 0) {
				return "Incorrect Guess";
			}
		}
		/*
		else if(data.equals("-0"))
		{
			return "continue";
		}
		else if(Integer.valueOf(data) >= 0) {
			return "Client Guessed it right";
		}
		
		else if(Integer.valueOf(data) >= 0 && data.length() >= 2 && data.charAt(0) != 'e') 
		{
			String retValue = "Guesses are at ";
			for(int i = 0; i<data.length(); i++) {
				retValue += data.charAt(i);
				if(i == data.length()-1) {
					break;
				}
				retValue += " and at ";
			}
			retValue += " indicies";
			return retValue;
		}
		else if(Integer.valueOf(data) < 0) {
			return "Sorry, its a wrong guess";
		}*/
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
				// replace this line with 'AddServerEvent to ServerScene Listview'
				//System.out.println(data.toString());
				controller.updateServerLogs(updateServerLogIntro(data.toString()));
			});
		});
		
		stage.show();
		
		//changeScene(e, "/FXML/ServerScene.fxml","/styles/ServerScene.css");
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
}
