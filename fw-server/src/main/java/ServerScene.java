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
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerScene implements Initializable {
	
	public static Server server;
	private ObservableList<String> logs;
	
	public ServerScene() {
		logs = FXCollections.observableArrayList();
	}
	
	@FXML
	private VBox vbox;
	
	@FXML
	private Text h1;
	
	@FXML
	private ListView<String> serverLogs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void updateServerLogs(String data) {
		// server messages are prepended with l
		//if (data.startsWith("l"))
		//{
			//logs.add(data.substring(1));
		logs.add(data);
		serverLogs.setItems(logs);
		//}
	}
	
}