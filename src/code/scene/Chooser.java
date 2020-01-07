package code.scene;

import code.utils.Parametres;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Chooser {
    public static void configureFileChooser(FileChooser fileChooser) {
        File folder = new File(Parametres.DOSSIER);
        if (folder.isDirectory()) {
            fileChooser.setInitialDirectory(folder);
        }

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls")
        );
    }

    public static void configureFolderChooser(DirectoryChooser directoryChooser) {
        File folder = new File(Parametres.DOSSIER);
        if (folder.isDirectory()) {
            directoryChooser.setInitialDirectory(folder);
        }
    }
}
