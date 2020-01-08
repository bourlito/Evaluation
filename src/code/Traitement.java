package code;

import code.entity.Commentaire;
import code.entity.Employe;
import code.utils.Column;
import code.utils.Erreur;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Traitement {

    private Map<Sheet, Map<Row, Map<Cell, String>>> mapWb = new HashMap<>();
    private Map<Integer, Employe> employes = new HashMap<>();
    private XSSFWorkbook wb;

    private Sheet mSheet;

    public Traitement(@NotNull XSSFWorkbook wb){
        this.wb = wb;

        this.parseEntree();
        this.createEmployes();
        this.createMoyennes();
    }

    public Map<Integer, Employe> getEmployes() {
        return employes;
    }

    private void parseEntree(){

        for (Sheet sheet: wb){
            if (mSheet == null || mSheet.getLastRowNum() < sheet.getLastRowNum())
                mSheet = sheet;

            Map<Row, Map<Cell, String>> mapSheet = new HashMap<>();

            for (Row row: sheet){

                if (row.getRowNum() < 2) continue;

                Map<Cell, String> mapRow = new HashMap<>();

                for (Cell cell: row){

                    switch (cell.getCellType()){
                        case STRING:
                            mapRow.put(cell, cell.getStringCellValue());
                            break;
                        case NUMERIC: case FORMULA:
                            mapRow.put(cell, String.valueOf(cell.getNumericCellValue()));
                            break;
                    }
                }

                mapSheet.put(row, mapRow);
            }

            mapWb.put(sheet, mapSheet);
        }
    }

    private void createEmployes(){
        Map<Row, Map<Cell, String>> mapSheet = mapWb.get(mSheet);

        for (Row row: mapSheet.keySet()){

            Employe employe = new Employe(row.getRowNum(),
                    row.getCell(Column.COL_NOM).getStringCellValue(),
                    row.getCell(Column.COL_PRENOM).getStringCellValue(),
                    row.getCell(Column.COL_SURNOM).getStringCellValue());

            employes.put(row.getRowNum(), employe);
        }
    }

    private void createMoyennes(){

        for (Sheet sheet: mapWb.keySet()){
            Map<Row, Map<Cell, String>> mapSheet = mapWb.get(sheet);

            for (Row row: mapSheet.keySet()){
                Employe employe = employes.get(row.getRowNum());

                if (row.getCell(Column.COL_NOM).getStringCellValue().equalsIgnoreCase(employe.getNom()))
                    setStats(employe, mapSheet.get(row), sheet.getSheetName());
                else employe.setErreur(true);
            }
        }
    }

    private void setStats(Employe employe, @NotNull Map<Cell, String> mapRow, String evaluateur){

        for (Cell cell: mapRow.keySet()){

            double value = -1;

            try {
                value = Double.parseDouble(mapRow.get(cell));
            } catch (NumberFormatException ignored) {}

            switch (cell.getColumnIndex()){
                case Column.COL_SAISIE:
                    employe.getSaisie().add(value);
                    break;
                case Column.COL_LETTRAGE:
                    employe.getLettrage().add(value);
                    break;
                case Column.COL_AFFECTATION:
                    employe.getAffectation().add(value);
                    break;
                case Column.COL_SUSPENS:
                    employe.getSuspens().add(value);
                    break;
                case Column.COL_TVA:
                    employe.getTva().add(value);
                    break;
                case Column.COL_REVISION:
                    employe.getRevision().add(value);
                    break;
                case Column.COL_AUTONOMIE:
                    employe.getAutonomie().add(value);
                    break;
                case Column.COL_PARTAGE:
                    employe.getPartage().add(value);
                    break;
                case Column.COL_DELAIS:
                    employe.getDelais().add(value);
                    break;
                case Column.COL_OBJECTIFS:
                    employe.getObjectifs().add(value);
                    break;
                case Column.COL_PONCTUALITE:
                    employe.getPonctualite().add(value);
                    break;
                case Column.COL_INVESTISSEMENT:
                    employe.getInvestissement().add(value);
                    break;
                case Column.COL_RESPECT:
                    employe.getRespect().add(value);
                    break;
                case Column.COL_MANAGEMENT:
                    employe.getManagement().add(value);
                    break;
                case Column.COL_COMMENTAIRE:
                    if (!cell.getStringCellValue().isEmpty())
                        employe.getCommentaires().add(new Commentaire(evaluateur, cell.getStringCellValue()));
                    break;
            }
        }
    }
}
