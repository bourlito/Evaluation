package code.scene;

import code.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CScene extends Scene {

    public CScene(Parent root) {
        super(root, 800, 500);
        this.getStylesheets().add(Main.STYLE);
    }

    public CScene(Parent root, double width, double height) {
        super(root, width, height);
        this.getStylesheets().add(Main.STYLE);
    }
}
