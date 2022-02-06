import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		if (data.equals("continue"))
		{
			return;
		}
		else
		{
			logs.add(data);
			serverLogs.setItems(logs);
		}
	}
	
}