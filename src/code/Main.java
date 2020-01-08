package code;

import code.entity.Employe;
import code.scene.CScene;
import code.scene.Chooser;
import code.utils.Erreur;
import code.utils.Parametres;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
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
                Parametres.DEST = src.getParent() + "\\";
            }
        });

        btnValider.setOnAction(event -> {
            if (src == null) {
                Erreur.create("Il faut choisir un fichier.");
            }

            else {
                try {
                    this.valider(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                    Erreur.create(e.getMessage());
                }
            }
        });

        stage.setTitle("Evaluations");
        stage.setScene(new CScene(pane));
        stage.show();
    }

    private void valider(@NotNull Stage stage) throws IOException {

        FileInputStream fis = new FileInputStream(new File(src.getAbsolutePath()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        Traitement traitement = new Traitement(wb);
        XCL xcl = new XCL(traitement.getEmployes());
        xcl.create();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(Parametres.DEST + Parametres.FILENAME));

        StringBuilder builder = new StringBuilder();
        for (Employe employe: traitement.getEmployes().values()){
            if (employe.isErreur())
                builder.append(employe.toString()).append("\n");
        }

        if (builder.toString().isEmpty())
            this.terminer(stage);

        else Erreur.create("Les employés suivants ne sont pas sur la même ligne dans toutes les feuilles :\n" + builder.toString());
    }

    private void terminer(@NotNull Stage stage){

        Label label = new Label("Evaluations terminées. Ouverture du fichier...");
        Button btn = new Button("Terminer");
        btn.setOnAction(event -> stage.close());

        VBox box = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, btn);

        BorderPane pane = new BorderPane();
        pane.setCenter(box);

        Scene scene = new CScene(pane, 800, 166);

        Stage newWindow = new Stage();
        newWindow.setTitle("Evaluations terminées");
        newWindow.setScene(scene);
        newWindow.show();
    }
}
