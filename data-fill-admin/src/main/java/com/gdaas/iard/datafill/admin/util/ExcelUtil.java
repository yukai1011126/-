package com.gdaas.iard.datafill.admin.util;

import com.gdaas.iard.datafill.admin.repo.dao.entity.OrganizationEntity;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 重定义日期格式


    public static void mbtaskExcel(HttpServletResponse response){
        OutputStream os;
        WritableWorkbook book;
        try {
            os = response.getOutputStream();
            // 设置导出文件单元格的格式
            // 字体样式
            WritableFont font5 = new WritableFont(WritableFont.TAHOMA, 18, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            Color color = Color.decode("#33365A");
            book = Workbook.createWorkbook(os);
            book.setColourRGB(Colour.ORANGE, color.getRed(), color.getGreen(), color.getBlue());
            WritableCellFormat cellFormat1 = new WritableCellFormat(font5); // 标题字体
            cellFormat1.setBackground(Colour.ORANGE);
            WritableFont font2 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD, false);
            WritableFont font3 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
            WritableFont font4 = new WritableFont(WritableFont.TAHOMA, 9, WritableFont.BOLD, false);
            WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 8, WritableFont.BOLD, false);
            // //说明字体
            WritableCellFormat cellFormat4 = new WritableCellFormat(font2); // 表格标题字体
            WritableCellFormat cellFormat5 = new WritableCellFormat(font4); // 表格标题字体
            WritableCellFormat cellFormat6 = new WritableCellFormat(font3); // 表格内容字体
            WritableCellFormat cellFormat7 = new WritableCellFormat(font6); // 表格内容字体
            // 单元格边框
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat5.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat6.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat7.setBorder(Border.ALL, BorderLineStyle.THIN);
            // 单元格背景颜色
            // 单元格对齐方式
            cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat4.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat5.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat7.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat7.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat6.setWrap(true);
            response.setContentType("application/x-excel");

            String fileName = "任务导入模板";
            fileName += df.format(System.currentTimeMillis());

            fileName += ".xls";
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
            // 第一个sheet
            WritableSheet sheet1 = book.createSheet("任务导入模板", 0);
            // sheet1.setRowView(0, 500, false); // 设置行高

            // 单元格宽
            sheet1.setColumnView(0, 20);
            sheet1.setColumnView(1, 20);
            sheet1.setColumnView(2, 30);
            sheet1.setColumnView(3, 30);
            sheet1.setColumnView(4, 30);
            sheet1.setColumnView(5, 30);
            sheet1.setColumnView(6, 30);
            sheet1.setColumnView(7, 30);
            sheet1.setColumnView(8, 30);
            sheet1.setColumnView(9, 30);
            sheet1.setColumnView(10, 30);

            sheet1.mergeCells(0, 0, 8, 0);// 跨越的行数
            // 表头标题
            sheet1.addCell(new Label(0, 0, "任务导入模板", cellFormat1));
            sheet1.addCell(new Label(0, 1, "任务名称", cellFormat4));
            sheet1.addCell(new Label(1, 1, "任务内容", cellFormat4));
            sheet1.addCell(new Label(2, 1, "任务开始时间(yyyy/MM/dd HH:mm)", cellFormat7));
            sheet1.addCell(new Label(3, 1, "任务结束时间(yyyy/MM/dd HH:mm)", cellFormat7));
            sheet1.addCell(new Label(4, 1, "任务所属日期(yyyy/MM/dd)", cellFormat4));
            sheet1.addCell(new Label(5, 1,"区域支行(不包含自贸区)",cellFormat4));
            sheet1.addCell(new Label(6, 1,"自贸区支行",cellFormat4));
            sheet1.addCell(new Label(7, 1,"二级网点(不包含社区)",cellFormat4));
            sheet1.addCell(new Label(8, 1,"社区网点",cellFormat4));
            sheet1.addCell(new Label(9, 1,"指定机构",cellFormat4));
            sheet1.addCell(new Label(10, 1,"机构编号(以|分隔)",cellFormat4));
            sheet1.addCell(new Label(10,0,"指定机构时必填",cellFormat4));
            // 关闭流
            book.write();
            book.close();

            os.close();
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 机构表模板导出
     *
     * @param
     * @param response
     */
    public static void mborganizationExcel(HttpServletResponse response) {
        OutputStream os;
        WritableWorkbook book;
        try {
            os = response.getOutputStream();
            // 设置导出文件单元格的格式
            // 字体样式
            WritableFont font5 = new WritableFont(WritableFont.TAHOMA, 18, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            Color color = Color.decode("#33365A");
            book = Workbook.createWorkbook(os);
            book.setColourRGB(Colour.ORANGE, color.getRed(), color.getGreen(), color.getBlue());
            WritableCellFormat cellFormat1 = new WritableCellFormat(font5); // 标题字体
            cellFormat1.setBackground(Colour.ORANGE);
            WritableFont font2 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD, false);
            WritableFont font3 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
            // //说明字体
            WritableCellFormat cellFormat4 = new WritableCellFormat(font2); // 表格标题字体
            WritableCellFormat cellFormat6 = new WritableCellFormat(font3); // 表格内容字体
            // 单元格边框
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat6.setBorder(Border.ALL, BorderLineStyle.THIN);
            // 单元格背景颜色
            // 单元格对齐方式
            cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat4.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat6.setWrap(true);
            response.setContentType("application/x-excel");

            String fileName = "机构导入模板";
            fileName += df.format(System.currentTimeMillis());

            fileName += ".xls";
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
            // 第一个sheet
            WritableSheet sheet1 = book.createSheet("机构导入模板", 0);
            // sheet1.setRowView(0, 500, false); // 设置行高

            // 单元格宽
            sheet1.setColumnView(0, 20);
            sheet1.setColumnView(1, 20);
            sheet1.setColumnView(2, 20);
            sheet1.setColumnView(3, 20);
            sheet1.setColumnView(4, 20);
            sheet1.setColumnView(5, 20);
            sheet1.setColumnView(6, 20);
            sheet1.setColumnView(7, 20);
            sheet1.setColumnView(8, 20);
            sheet1.setColumnView(9, 20);

            sheet1.mergeCells(0, 0, 9, 0);// 跨越的行数
            // 表头标题
            sheet1.addCell(new Label(0, 0, "机构导入模板", cellFormat1));
            sheet1.addCell(new Label(0, 1, "机构ID", cellFormat4));
            sheet1.addCell(new Label(1, 1, "机构编号", cellFormat4));
            sheet1.addCell(new Label(2, 1, "机构名称", cellFormat4));
            sheet1.addCell(new Label(3, 1, "机构等级", cellFormat4));
            sheet1.addCell(new Label(4, 1, "父级机构ID", cellFormat4));
            sheet1.addCell(new Label(5, 1, "父级机构名称", cellFormat4));
            sheet1.addCell(new Label(6, 1, "X轴坐标", cellFormat4));
            sheet1.addCell(new Label(7, 1, "Y轴坐标", cellFormat4));
            sheet1.addCell(new Label(8, 1, "创建时间", cellFormat4));
            sheet1.addCell(new Label(9, 1, "更新时间", cellFormat4));
            // 关闭流
            book.write();
            book.close();

            os.close();
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 机构表导出
     *
     * @param
     * @param response
     */
    public static void organizationExcel(List<OrganizationEntity> OrganizationList, HttpServletResponse response) {
        OutputStream os;
        WritableWorkbook book;
        try {
            os = response.getOutputStream();
            // 设置导出文件单元格的格式
            // 字体样式
            WritableFont font5 = new WritableFont(WritableFont.TAHOMA, 18, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            Color color = Color.decode("#33365A");
            book = Workbook.createWorkbook(os);
            book.setColourRGB(Colour.ORANGE, color.getRed(), color.getGreen(), color.getBlue());
            WritableCellFormat cellFormat1 = new WritableCellFormat(font5); // 标题字体
            cellFormat1.setBackground(Colour.ORANGE);
            WritableFont font2 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD, false);
            WritableFont font3 = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
            // //说明字体
            WritableCellFormat cellFormat4 = new WritableCellFormat(font2); // 表格标题字体
            WritableCellFormat cellFormat6 = new WritableCellFormat(font3); // 表格内容字体
            // 单元格边框
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat6.setBorder(Border.ALL, BorderLineStyle.THIN);
            // 单元格背景颜色
            // 单元格对齐方式
            cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat4.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat6.setWrap(true);
            response.setContentType("application/x-excel");

            String fileName = "机构表";
            fileName += df.format(System.currentTimeMillis());

            fileName += ".xls";
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
            // 第一个sheet
            WritableSheet sheet1 = book.createSheet("机构表", 0);
            // sheet1.setRowView(0, 500, false); // 设置行高

            // 单元格宽
            sheet1.setColumnView(0, 20);
            sheet1.setColumnView(1, 20);
            sheet1.setColumnView(2, 20);
            sheet1.setColumnView(3, 20);
            sheet1.setColumnView(4, 20);
            sheet1.setColumnView(5, 20);
            sheet1.setColumnView(6, 20);
            sheet1.setColumnView(7, 20);
            sheet1.setColumnView(8, 20);
            sheet1.setColumnView(9, 20);

            sheet1.mergeCells(0, 0, 9, 0);// 跨越的行数
            // 表头标题
            sheet1.addCell(new Label(0, 0, "机构表", cellFormat1));
            sheet1.addCell(new Label(0, 1, "机构ID", cellFormat4));
            sheet1.addCell(new Label(1, 1, "机构编号", cellFormat4));
            sheet1.addCell(new Label(2, 1, "机构名称", cellFormat4));
            sheet1.addCell(new Label(3, 1, "机构等级", cellFormat4));
            sheet1.addCell(new Label(4, 1, "父级机构ID", cellFormat4));
            sheet1.addCell(new Label(5, 1, "父级机构名称", cellFormat4));
            sheet1.addCell(new Label(6, 1, "X轴坐标", cellFormat4));
            sheet1.addCell(new Label(7, 1, "Y轴坐标", cellFormat4));
            sheet1.addCell(new Label(8, 1, "创建时间", cellFormat4));
            sheet1.addCell(new Label(9, 1, "更新时间", cellFormat4));
            if (OrganizationList != null) {
                for (int i = 0; i < OrganizationList.size(); i++) {
                    int row = i + 2;
                    sheet1.addCell(new Label(0, row, OrganizationList.get(i).getOrganizationId()+"", cellFormat6));
                    sheet1.addCell(new Label(1, row, OrganizationList.get(i).getOrganizationCode(), cellFormat6));
                    sheet1.addCell(new Label(2, row, OrganizationList.get(i).getOrganizationName(), cellFormat6));
                    sheet1.addCell(new Label(3, row, OrganizationList.get(i).getOrganizationLevel(), cellFormat6));
                    sheet1.addCell(new Label(4, row, OrganizationList.get(i).getPorganizationId()+"", cellFormat6));
                    sheet1.addCell(new Label(5, row, OrganizationList.get(i).getPorganizationName()+"", cellFormat6));
                    sheet1.addCell(new Label(6, row, OrganizationList.get(i).getAxisX()+"", cellFormat6));
                    sheet1.addCell(new Label(7, row, OrganizationList.get(i).getAxisY()+"", cellFormat6));
                    sheet1.addCell(new Label(8, row, OrganizationList.get(i).getCreatetime()+"", cellFormat6));
                    sheet1.addCell(new Label(9, row, OrganizationList.get(i).getUpdatetime()+"", cellFormat6));
                }
            }
            // 关闭流
            book.write();
            book.close();

            os.close();
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
