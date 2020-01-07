package code.utils;

import code.entity.Employe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Traitement {

    private static List<List<List<String>>> data = new ArrayList<>();;
    private static Traitement INSTANCE = null;

    private Traitement(){}

    public static Traitement getInstance(){
       if (INSTANCE == null)
           INSTANCE = new Traitement();

       return INSTANCE;
    }

    /**
     * List<List<List<String>>>
     * /---/---/---|cellule
     * /---/---|row
     * /---|sheet
     * /wb
     * @param wb xlsx
     * @return data
     */
    @NotNull
    public List<List<List<String>>> parseEntree(@NotNull XSSFWorkbook wb){

        for (Sheet sheet: wb){
            List<List<String>> dataSheet = new ArrayList<>();

            for (Row row: sheet){

                if (row.getRowNum() < 2) continue;

                List<String> dataRow = new ArrayList<>();

                for (Cell cell: row){

                    switch (cell.getCellType()){
                        default:
                        case STRING:
                            dataRow.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            dataRow.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                    }
                }

                dataSheet.add(dataRow);
            }

            data.add(dataSheet);
        }

        return data;
    }

    public List<Employe> createEmployes(){
        List<Employe> employes = new ArrayList<>();

        return employes;
    }
}
