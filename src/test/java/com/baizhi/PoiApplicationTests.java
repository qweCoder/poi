package com.baizhi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest(classes = PoiApplication.class)
@RunWith(SpringRunner.class)
public class PoiApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("asd");
    }

    //poi导出excel
    @Test
    public void poiOut() {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        user1.setId("id1").setName("name1").setAge(1).setBirthday(new Date());
        user2.setId("id2").setName("name2").setAge(2).setBirthday(new Date());
        users.add(user1);
        users.add(user2);

        //=创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //=创建工作表
        HSSFSheet sheet = workbook.createSheet("mySheet");
        sheet.setColumnWidth(3, 15 * 256);
        //=使标题居中,设置标题字体
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setBold(true);
        font.setFontName("幼圆");
        font.setItalic(true);
        cellStyle1.setFont(font);
        //=创建标题行、创建单元格对象
        HSSFRow row = sheet.createRow(0);
        String[] titles = {"主键", "姓名", "年龄", "出生日期"};
        HSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cellStyle1);
        }
        //处理日期格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
        //=创建数据行
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 1);
            User user = users.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getAge());
            cell = row.createCell(3);
            cell.setCellValue(user.getBirthday());
            cell.setCellStyle(cellStyle);
        }
        //=写出到文件
        try {
            workbook.write(new File("E:\\baizhiThirdStage\\source/poiMake.xls"));
            //=关闭工作簿
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIn() throws FileNotFoundException {
        FileInputStream is = null;
        try {
            File file = new File("E:\\baizhiThirdStage\\source/poiMake.xls");
            is = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet mySheet = workbook.getSheet("mySheet");
            HSSFRow row = null;
            int count = mySheet.getLastRowNum();
            System.out.println("count = " + count);
            for (int i = 0; i < count; i++) {
                row = mySheet.getRow(i + 1);
                System.out.println(row.getCell(0).getStringCellValue() + ","
                        + row.getCell(1).getStringCellValue() + ","
                        + row.getCell(2).getNumericCellValue() + ","
                        + row.getCell(3).getDateCellValue()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
