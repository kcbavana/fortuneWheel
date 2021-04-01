import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXClient extends Application {

	public static HashMap<String,Scene> sceneMap;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Build sceneMap
		
		
		primaryStage.setTitle("Fortune Wheel Client");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ClientIntro.fxml"));
		Scene scene = new Scene(root, 500,500);
		scene.getStylesheets().add("/styles/ClientIntro.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Ensure proper shutdown
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
	}
	
	// public HashMap<String,Scene> buildSceneMap() {}
}