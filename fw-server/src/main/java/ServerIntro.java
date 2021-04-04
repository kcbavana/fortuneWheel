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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
	
	/*
	 * Connect Button initializes a Server object listening on the 
	 * specified port
	 */
	public void connectMethod(ActionEvent e) throws IOException {
		
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
				controller.updateServerLogs(data.toString());
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
		Scene scene = new Scene(root);
		// Apply CSS styling
		scene.getStylesheets().add(css);
		// Change current Scene on the Stage
		stage.setScene(scene);
		stage.show();
	}
}
