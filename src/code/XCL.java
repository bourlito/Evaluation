package code;

import code.entity.Commentaire;
import code.entity.Employe;
import code.utils.Column;
import code.utils.Parametres;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XCL {

    private XSSFWorkbook wb;
    private XSSFSheet sheet;

    public XCL(@NotNull Map<Integer, Employe> employes) {
        this.wb = new XSSFWorkbook();
        this.creerFeuille();

        for (int id: employes.keySet()){
            Employe employe = employes.get(id);
            this.remplir(employe);
        }
    }

    public void create() throws IOException {
        FileOutputStream out = new FileOutputStream(Parametres.DEST + Parametres.FILENAME);
        wb.write(out);
        wb.close();
        out.close();
    }

    private void creerFeuille(){
        sheet = wb.createSheet("Récapitulatif");
        sheet.setDefaultRowHeightInPoints(15f);
        sheet.setDefaultColumnWidth(10);

        this.creerEntete();
    }

    private void creerEntete(){
        this.creerCategories();
        this.creerLibellesNotes();
    }

    private void creerCategories(){
        Row rowCat = sheet.createRow(0);
        rowCat.setHeightInPoints(21f);

        for (int i = Column.COL_NOM; i <= Column.COL_SURNOM; i++)
            rowCat.createCell(i);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, Column.COL_NOM, Column.COL_SURNOM));

        for (int i = Column.COL_SAISIE; i <= Column.COL_REVISION; i++)
            rowCat.createCell(i);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, Column.COL_SAISIE, Column.COL_REVISION));
        rowCat.getCell(Column.COL_SAISIE).setCellValue(Column.CAT_SAVOIR_FAIRE);

        for (int i = Column.COL_AUTONOMIE; i <= Column.COL_OBJECTIFS; i++)
            rowCat.createCell(i);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, Column.COL_AUTONOMIE, Column.COL_OBJECTIFS));
        rowCat.getCell(Column.COL_AUTONOMIE).setCellValue(Column.CAT_SAVOIR_ETRE);

        for (int i = Column.COL_PONCTUALITE; i <= Column.COL_MANAGEMENT; i++)
            rowCat.createCell(i);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, Column.COL_PONCTUALITE, Column.COL_MANAGEMENT));
        rowCat.getCell(Column.COL_PONCTUALITE).setCellValue(Column.CAT_INVESTISSEMENT);

        for (Cell cell: rowCat){
            this.setStyle(cell, cell.getColumnIndex(), true);
        }
    }

    private void creerLibellesNotes(){
        Row rowStat = sheet.createRow(1);
        rowStat.setHeightInPoints(42f);

        rowStat.createCell(Column.COL_NOM).setCellValue(Column.COL_NOM_LIBELLE);
        rowStat.createCell(Column.COL_PRENOM).setCellValue(Column.COL_PRENOM_LIBELLE);
        rowStat.createCell(Column.COL_SURNOM);

        rowStat.createCell(Column.COL_SAISIE).setCellValue(Column.COL_SAISIE_LIBELLE);
        rowStat.createCell(Column.COL_LETTRAGE).setCellValue(Column.COL_LETTRAGE_LIBELLE);
        rowStat.createCell(Column.COL_AFFECTATION).setCellValue(Column.COL_AFFECTATION_LIBELLE);
        rowStat.createCell(Column.COL_SUSPENS).setCellValue(Column.COL_SUSPENS_LIBELLE);
        rowStat.createCell(Column.COL_TVA).setCellValue(Column.COL_TVA_LIBELLE);
        rowStat.createCell(Column.COL_REVISION).setCellValue(Column.COL_REVISION_LIBELLE);
        rowStat.createCell(Column.COL_AUTONOMIE).setCellValue(Column.COL_AUTONOMIE_LIBELLE);
        rowStat.createCell(Column.COL_PARTAGE).setCellValue(Column.COL_PARTAGE_LIBELLE);
        rowStat.createCell(Column.COL_DELAIS).setCellValue(Column.COL_DELAIS_LIBELLE);
        rowStat.createCell(Column.COL_OBJECTIFS).setCellValue(Column.COL_OBJECTIFS_LIBELLE);
        rowStat.createCell(Column.COL_PONCTUALITE).setCellValue(Column.COL_PONCTUALITE_LIBELLE);
        rowStat.createCell(Column.COL_INVESTISSEMENT).setCellValue(Column.COL_INVESTISSEMENT_LIBELLE);
        rowStat.createCell(Column.COL_RESPECT).setCellValue(Column.COL_RESPECT_LIBELLE);
        rowStat.createCell(Column.COL_MANAGEMENT).setCellValue(Column.COL_MANAGEMENT_LIBELLE);
        rowStat.createCell(Column.COL_COMMENTAIRE).setCellValue(Column.COL_COMMENTAIRE_LIBELLE);

        for (Cell cell: rowStat){
            this.setStyle(cell, cell.getColumnIndex(), true);
        }
    }

    private void remplir(@NotNull Employe employe){
        Row row = sheet.createRow(employe.getId());

        this.creerCell(row, Column.COL_NOM, employe.getNom());
        this.creerCell(row, Column.COL_PRENOM, employe.getPrenom());
        this.creerCell(row, Column.COL_SURNOM, employe.getSurnom());

        this.creerCell(row, Column.COL_SAISIE, employe.getSaisie().getMoyenne());
        this.creerCell(row, Column.COL_LETTRAGE, employe.getLettrage().getMoyenne());
        this.creerCell(row, Column.COL_AFFECTATION, employe.getAffectation().getMoyenne());
        this.creerCell(row, Column.COL_SUSPENS, employe.getSuspens().getMoyenne());
        this.creerCell(row, Column.COL_TVA, employe.getTva().getMoyenne());
        this.creerCell(row, Column.COL_REVISION, employe.getRevision().getMoyenne());
        this.creerCell(row, Column.COL_AUTONOMIE, employe.getAutonomie().getMoyenne());
        this.creerCell(row, Column.COL_PARTAGE, employe.getPartage().getMoyenne());
        this.creerCell(row, Column.COL_DELAIS, employe.getDelais().getMoyenne());
        this.creerCell(row, Column.COL_OBJECTIFS, employe.getObjectifs().getMoyenne());
        this.creerCell(row, Column.COL_PONCTUALITE, employe.getPonctualite().getMoyenne());
        this.creerCell(row, Column.COL_INVESTISSEMENT, employe.getInvestissement().getMoyenne());
        this.creerCell(row, Column.COL_RESPECT, employe.getRespect().getMoyenne());
        this.creerCell(row, Column.COL_MANAGEMENT, employe.getManagement().getMoyenne());

        List<Commentaire> commentaires = employe.getCommentaires();
        for (int index = 0; index < commentaires.size(); index++)
            this.creerCell(row, Column.COL_COMMENTAIRE + index, commentaires.get(index).toString());
    }

    private void creerCell(@NotNull Row row, int numCol, String value){
        Cell cell = row.createCell(numCol);
        cell.setCellValue(value);

        this.setStyle(cell, numCol, false);
    }

    private void creerCell(@NotNull Row row, int numCol, double value){
        Cell cell = row.createCell(numCol);

        if (value != -1)
            cell.setCellValue(value);

        this.setStyle(cell, numCol, false);
    }

    private void setStyle(Cell cell, int numCol, boolean title){
        DataFormat df = wb.createDataFormat();

        CellStyle styleNP = title ?
                this.getTitleStyle(IndexedColors.LIGHT_GREEN.index):
                this.getColoredStyle(IndexedColors.LIGHT_GREEN.index);
        styleNP.setDataFormat(df.getFormat("0.0"));

        CellStyle styleSF = title ?
                this.getTitleStyle(IndexedColors.PALE_BLUE.index):
                this.getColoredStyle(IndexedColors.PALE_BLUE.index);
        styleSF.setDataFormat(df.getFormat("0.0"));

        CellStyle styleSE = title ?
                this.getTitleStyle(IndexedColors.CORAL.index):
                this.getColoredStyle(IndexedColors.CORAL.index);
        styleSE.setDataFormat(df.getFormat("0.0"));

        CellStyle styleIP = title ?
                this.getTitleStyle(IndexedColors.LIGHT_CORNFLOWER_BLUE.index):
                this.getColoredStyle(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
        styleIP.setDataFormat(df.getFormat("0.0"));

        CellStyle styleCOM = title ?
                this.getTitleStyle(IndexedColors.WHITE.index):
                this.getColoredStyle(IndexedColors.WHITE.index);
        styleCOM.setDataFormat(df.getFormat("0.0"));

        if (Column.COL_NOM <= numCol && numCol <= Column.COL_SURNOM)
            cell.setCellStyle(styleNP);

        else if (Column.COL_SAISIE <= numCol && numCol <= Column.COL_REVISION)
            cell.setCellStyle(styleSF);

        else if (Column.COL_AUTONOMIE <= numCol && numCol <= Column.COL_OBJECTIFS)
            cell.setCellStyle(styleSE);

        else if (Column.COL_PONCTUALITE <= numCol && numCol <= Column.COL_MANAGEMENT)
            cell.setCellStyle(styleIP);

        else cell.setCellStyle(styleCOM);
    }

    @NotNull
    private CellStyle getColoredStyle(short colorIndex){
        CellStyle style = this.getDefaultStyle();

        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    @NotNull
    private CellStyle getTitleStyle(short colorIndex){
        CellStyle style = this.getColoredStyle(colorIndex);

        Font font = wb.createFont();
        font.setBold(true);

        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFont(font);

        return style;
    }

    @NotNull
    private CellStyle getDefaultStyle(){
        CellStyle style = wb.createCellStyle();

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        return style;
    }
}
