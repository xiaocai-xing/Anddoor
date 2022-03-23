package unit;

import java.io.File;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

/**
 * @ClassName ExcelReadUtil
 * @Description TODO
 * @Author Charles0219
 * @Date 2022/3/22 15:39
 * @Version 1.0
 **/
public class ExcelReadUtil {

    public static Object[][] seach(String exclepath,String sheetnamee,int [] rows,int []cells){
        //获取对象
        Object[][] datas = null;
        try {
            //获取工作簿workbook对象
            Workbook workbook= WorkbookFactory.create(new File(exclepath));
            //获取sheet对象
            Sheet sheet = workbook.getSheet(sheetnamee);
            datas = new Object[rows.length][cells.length];
            for (int i = 0;i<rows.length;i++){
                Row row = sheet.getRow(rows[i]-1);
                for (int j = 0;j< cells.length;j++) {
                    Cell cell = row.getCell(cells[j]-1);
                    cell.setCellType(CellType.STRING);
                    String value =cell.getStringCellValue() ;
                    datas[i][j] = value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

}
