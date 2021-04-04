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


public class ClientGameScene implements Initializable {
	
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
	private TextField sendCharBox;
	
	@FXML
	private HBox menuBox;
	
	@FXML
	private Button newGameButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Text guessesText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
	
	public void newGameMethod(ActionEvent e) throws IOException {
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
