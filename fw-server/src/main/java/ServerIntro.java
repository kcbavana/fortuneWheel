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
	public static HashMap<String,Scene> scenes;
	
	/*
	 * Constructor, called before @FXML fields are populated
	 *
	public ServerIntro(HashMap<String,Scene> sceneMap) {
		scenes = sceneMap;
	}*/
	
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
	 * Get SceneMap
	 */
	
	/*
	 * Connect Button initializes a Server object listening on the 
	 * specified port
	 */
	public void connectMethod(ActionEvent e) throws IOException {
		/*server = new Server(data -> {
			Platform.runLater(() -> {
				System.out.println(data.toString());
			});
		});
		*/
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		//stage.setScene(scenes.get("serverScene"));
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ServerScene.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/ServerScene.css");
		stage.setScene(scene);
		stage.show();
	}
}
