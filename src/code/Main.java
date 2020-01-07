package code;

import code.scene.CScene;
import code.scene.Chooser;
import code.utils.Erreur;
import code.utils.Traitement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    private File src;
    public static final String STYLE = Main.class
            .getResource("res/javafx.css")
            .toExternalForm();

    @Override
    public void start(@NotNull Stage stage) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        Button btnSrc = new Button("Fichier d'évaluation");
        btnSrc.setMinWidth(200);
        pane.add(btnSrc, 0, 0);

        Label lSrc = new Label();
        lSrc.setText(src != null ? src.getName() : "");
        pane.add(lSrc, 1, 0);

        Button btnValider = new Button("Valider");
        btnValider.setId("btnValider");
        btnValider.setMinWidth(200);
        pane.add(btnValider, 1, 1);

        btnSrc.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Fichier d'évaluation");
            Chooser.configureFileChooser(fileChooser);

            File src = fileChooser.showOpenDialog(stage);

            if (src != null) {
                lSrc.setText(src.getName());
                this.src = src;
            }
        });

        btnValider.setOnAction(event -> {
            if (src == null) {
                Erreur.create("Il faut choisir un fichier.");
            }

            else {
                try {
                    this.valider();
                } catch (IOException e) {
                    e.printStackTrace();
                    Erreur.create(e.getMessage());
                }
            }
        });

        stage.setTitle("Hello World");
        stage.setScene(new CScene(pane));
        stage.show();
    }

    private void valider() throws IOException {
        Traitement traitement = Traitement.getInstance();

        FileInputStream fis = new FileInputStream(new File(src.getAbsolutePath()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        System.out.println(traitement.parseEntree(wb));
    }
}
