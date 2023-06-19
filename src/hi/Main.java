package hi;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        /**Parent Root = FXMLLoader.load(getClass().getResource("sample.fxml"));
         * FOR POTENTIAL FXML USAGE
         */
        Welcome firstUI = new Welcome();
        Pane root = firstUI.getRootPane();

        primaryStage.setTitle("CropBenders v. 1.4");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
