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
	private Text moviesText;
	
	@FXML
	private Text placesText;
	
	@FXML
	private HBox menuBox;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button exitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
}
