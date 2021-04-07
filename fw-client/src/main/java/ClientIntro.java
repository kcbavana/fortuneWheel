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
	
	@FXML
	private BorderPane bPane;
	
	@FXML
	private VBox centerBox;
	
	@FXML
	private Text title;
	
	@FXML
	private VBox portField;
	
	@FXML
	private TextField portNumber;
	
	@FXML
	private TextField ipPort;
	
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
	
	public static String port;
	public static String ip;
	private String portNumb = "5555";
	private String ipNumb = "127.0.0.1";
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
        
	}
	
	public int ipDotChecker(String ip) {
		int tracker = 0;
		for(int i = 0; i<ip.length(); i++) {
			if(ip.charAt(i) == '.') {
				tracker++;
			}
		}
		return tracker;
	}
	public String checkPort(String checkStr) {
		if(checkStr.equals("Type portNumber and hit enter: ")) {
			return portNumb;
		}
		else if(checkStr.equals(" ")) {
			return portNumb;
		}
		else {
			return checkStr;
		}
	}
	
	public String checkIP(String checkStr) {
		if(checkStr.equals("Type IP Address and hit enter: ")) {
			return ipNumb;
		}
		else if(checkStr.equals(" ")) {
			return ipNumb;
		}
		else if(ipDotChecker(ip) > 3) {
			return ipNumb;
		}
		else if(ipDotChecker(ip) < 3) {
			return ipNumb;
		}
		else {
			return checkStr;
		}
	}

	/*
	 * Connect to server 
	 */
	public void connectMethod(ActionEvent e) throws IOException {
		port = portNumber.getText();
		port = checkPort(port);
		ip =  ipPort.getText();
		ip = checkIP(ip);
		System.out.println("IP test: " + ip);
		client = new Client(data -> {
			Platform.runLater(() -> {
				Client.parseServerResponse(data.toString());
			});
		});
		client.start();
		
		System.out.println("IP: " + ip);
		System.out.println("PortNumber: " + port);
		
		//Change Scene
		/*Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/CategoryScene.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/CategoryScene.css");
		stage.setScene(scene);
		stage.show();*/
		changeScene(e,"/FXML/CategoryScene.fxml","/styles/CategoryScene.css");
		
	}
	
	public void rulesMethod(ActionEvent e) throws IOException {
		changeScene(e,"/FXML/ClientRules.fxml","/styles/ClientRules.css");
	}
	
	public void exitMethod(ActionEvent e) throws IOException {
		Node node=(Node) e.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		stage.close();
		//TODO exit platform and system
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
		Scene scene = new Scene(root, 800, 800);
		// Apply CSS styling
		scene.getStylesheets().add(css);
		// Change current Scene on the Stage
		stage.setScene(scene);
		stage.show();
	}

}
