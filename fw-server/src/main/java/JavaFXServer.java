import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXServer extends Application {

	//public static ServerIntro serverIntro;
	public static HashMap<String,Scene> sceneMap;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Fortune Wheel Server");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/ServerIntro.fxml"));
		Scene scene = new Scene(root, 500,500);
		scene.getStylesheets().add("/styles/ServerIntro.css");
		//buildSceneMap();
		//primaryStage.setScene(sceneMap.get("serverIntro"));
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
	
	/*
	 * Build sceneMap and pass it to each *.fxml controller
	 *
	public void buildSceneMap() throws IOException{
		//init sceneMap
		sceneMap = new HashMap<String,Scene>();
		// init Scenes
		Scene serverIntro = buildScene("/FXML/ServerIntro.fxml","/styles/ServerIntro.css");
		//serverIntro = new ServerIntro(sceneMap);
		// populate sceneMap 
		
		//serverIntro = new ServerIntro(sceneMap);
		sceneMap.put("serverIntro", serverIntro);
	}*/
	
	/*
	 * Given the filenames of an .fxml and a .css, return a populated Scene
	 *
	public Scene buildScene(String fxml, String css) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(css);
		
		return scene;
	}*/
}
