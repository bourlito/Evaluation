package code.utils;

import code.scene.CScene;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Erreur {

    @NotNull
    public static void create(String erreur){

        BorderPane pane = new BorderPane();
        Label label = new Label(erreur);
        pane.setCenter(label);

        Scene scene = new CScene(pane, 800, 166);

        Stage newWindow = new Stage();
        newWindow.setTitle("Erreur");
        newWindow.setScene(scene);
        newWindow.show();
    }
}
