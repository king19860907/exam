package com.majun.exam.excel;

import com.majun.exam.dao.OptionMapper;
import com.majun.exam.dao.QuestionMapper;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by majun on 2018/7/25.
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportDataV2 {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private OptionMapper optionMapper;

    private FormulaEvaluator evaluator;


    @Test
    public void importData() throws Exception {
        List<ExcelData> datas = readXml("src/test/resources/data2.xls");
        saveDatas(datas);
    }

    private void saveDatas(List<ExcelData> datas){
        for(ExcelData data : datas){
            QuestionDto dto = data.toQuestion();
            Question question = dto.getQuestion();
            List<Option> options = dto.getOptions();
            questionMapper.insert(question);
            options.forEach(option -> {
                optionMapper.insert(option);
            });
        }
    }

    private List<ExcelData> readXml(String path) throws Exception {
        List<ExcelData> datas = new ArrayList<>();
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        evaluator=hssfWorkbook.getCreationHelper().createFormulaEvaluator();

        // HSSFSheet 标识某一页
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        // 处理当前页，循环读取每一行
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            // HSSFRow表示行
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);

            ExcelData data = new ExcelData();
            data.setIndex(rowNum+"");
            data.setQuestion(getStringVal(hssfRow.getCell(1)));
            data.setOptionA(getStringVal(hssfRow.getCell(2)));
            data.setOptionB(getStringVal(hssfRow.getCell(3)));
            data.setOptionC(getStringVal(hssfRow.getCell(4)));
            data.setRightAnswer(getStringVal(hssfRow.getCell(5)));
            //System.out.println(data);
            datas.add(data);
        }
        return filterDatas(datas);
    }

    private List<ExcelData> filterDatas(List<ExcelData> datas){
        return datas.stream().filter(excelData -> {
            //System.out.println(excelData);
            return excelData.getRightOption() != null;
        }).collect(Collectors.toList());
    }


    /**
     * 改造poi默认的toString（）方法如下
     * @Title: getStringVal
     * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串
     *               2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
     * @param @param cell
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getStringVal(HSSFCell cell) {
        if (cell == null){
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                try{
                    CellValue cellValue = evaluator.evaluate(cell);
                    return getStringVal(cellValue);
                }catch (Exception e){
                    return null;
                }
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String result = cell.getStringCellValue();
                return result==null?null:result.trim();
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                return result==null?null:result.trim();
            default:
                return null;
        }
    }

    public String getStringVal(CellValue cell) {
        if (cell == null){
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getStringValue();
            case Cell.CELL_TYPE_NUMERIC:
                String result = String.valueOf(cell.getNumberValue());
                return result==null?null:result.trim();
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringValue();
                return result==null?null:result.trim();
            default:
                return null;
        }
    }

}
