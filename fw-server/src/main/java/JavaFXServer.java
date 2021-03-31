import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXServer extends Application {

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

}
