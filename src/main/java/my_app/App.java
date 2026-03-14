package my_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        var text = new Text("Olá mundo");

        var layout = new StackPane(text);

        primaryStage.setScene(new Scene(layout, 400, 300));

        primaryStage.show();
    }

    static void main(String[] args) {
        launch(args);
    }

}