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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ClientRules implements Initializable {
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private Text title;
	
	@FXML
	private VBox rulesBox;
	
	@FXML
	private Text rulesText;
	
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
