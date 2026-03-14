package my_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import my_app.hotreload.HotReload;
import my_app.screens.HomeScreen;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {

    public static Stage stage;
    HotReload hotReload;
    boolean devMode = true;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        initializeScene(primaryStage);
        initHotReload(primaryStage);
    }

    public static void initializeScene(Stage stage) throws Exception {
        Scene scene = new Scene(new HomeScreen(), 850, 600);
        scene.getStylesheets().add("/styles.css");
        stage.setScene(scene);

        stage.setTitle("Coesion Effect");
        stage.setResizable(false);

        stage.show();

        System.out.println("[App] Scene re-initialized.");
    }

    private void initHotReload(Stage primaryStage){
        if(devMode){
            // Configure Exclusions (Always exclude the App entry point)
            Set<String> exclusions = new HashSet<>();
            //exclusions.add("my_app.App");
            exclusions.add("my_app.hotreload.CoesionApp");
            exclusions.add("my_app.hotreload.Reloader");

            // Initialize Hot Reload
            hotReload = new HotReload(
                    "src/main/java/my_app",     // Path to .java files
                    "target/classes",           // Path to compiled .class files
                    "src/main/resources",       // Path to resources (css/fxml)
                    "my_app.hotreload.UIReloaderImpl",    // Your Implementation Class Name
                    primaryStage,               // Context (Main Stage)
                    exclusions                  // Classes to exclude from reloading
            );
            hotReload.start();
        }
    }

    @Override
    public void stop() throws Exception {
        // opcional: encerra o HotReload watchService ao fechar a aplicação
        if (hotReload != null) hotReload.stop();
        super.stop();
    }
}