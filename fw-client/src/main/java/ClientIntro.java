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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientIntro implements Initializable {
	
	public Client client;
	public HashMap<String,Scene> scenes;
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private VBox centerBox;
	
	@FXML
	private Text title;
	
	@FXML
	private Text subTitle;
	
	@FXML 
	private HBox bottomBox;
	
	@FXML
	private Button connectButton;
	
	@FXML
	private Button rulesButton;
	
	@FXML
	private Button exitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
	
	/*
	 * Constructor, passes SceneMap to this class
	 */
	public ClientIntro(HashMap<String,Scene> sceneMap) {
		scenes = sceneMap;
	}
	
	/*
	 * Connect to server 
	 */
	public void connectMethod(ActionEvent e) throws IOException {
		/*client = new Client(data -> {
			Platform.runLater(() -> {
				System.out.println(data.toString());
			});
		});
		client.start();*/
		
		//Change Scene
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/CategoryScene.fxml"));/* Exception */
		Scene scene = new Scene(root);
		//scene.getStylesheets().add("/styles/CategoryScene.css");
		stage.setScene(scene);
		stage.show();
		
	}

}
