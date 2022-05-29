package JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static NoWarPolis.Base_Dados.admins;
import static NoWarPolis.Base_Dados.nodes;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Nowarpolis.fxml"));
        Nowarpolis<Node> nowarpolis = new Nowarpolis<>();
        loader.setController(new Controller(nowarpolis));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("NoWarPolis");
        primaryStage.setScene(scene);
        primaryStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("Nowarpolis.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Nowarpolis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
