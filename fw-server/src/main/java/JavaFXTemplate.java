import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Fortune Wheel");
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Myfxml.fxml"));
		Scene scene = new Scene(root, 500,500);
		scene.getStylesheets().add("/styles/style1.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
