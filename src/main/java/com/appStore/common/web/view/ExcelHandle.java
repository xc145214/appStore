/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.appStore.common.web.view;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  Excel导出工具
 *
 *  @author xiachuan at 2016/7/7 19:53。
 */

public abstract class ExcelHandle {
    protected static final Logger LOG = LoggerFactory.getLogger(ExcelHandle.class);
    private static final int WIDTH = 4500;
    private static final int SHEET_INDEX = 0;
    private static final int MAX_ROWS_PER_PAGE = 30000;

    /**
     * 生成EXCEL表头。
     * @param sheet {@link Sheet}。
     * @param wb {@link XSSFWorkbook}.
     */
    public void createDetailColumn(Sheet sheet, XSSFWorkbook wb, String[] titles){
        int count = titles.length;
        //设置列宽
        for(int i=0; i<count; i++){
            sheet.setColumnWidth(i, WIDTH);
        }
        CellStyle titleStyle = wb.createCellStyle();
        XSSFFont titleFont = wb.createFont();
        //设置字体大小
        titleFont.setFontHeightInPoints((short)12);
        //粗体显示
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleStyle.setFont(titleFont);
        //左右居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //初始行
        int rowNumber = 0;
        Row titleRow = sheet.createRow(rowNumber);
        Cell[] cells = new Cell[count];
        for (int i=0; i<count; i++) {
            cells[i] = titleRow.createCell(i);
            cells[i].setCellStyle(titleStyle);
            cells[i].setCellValue(titles[i]);
        }
    }

    /**
     * 导出海量数据到EXCEL。
     *
     * @param request    {@link HttpServletRequest}。
     * @param response   {@link HttpServletResponse}。
     * @param sheetName  导出文件名称。
     * @param titles     Excel文件的表头数组。
     * @param count      需要导出的数据一共有多少条。
     * @param conditions 查询列表的条件需要的对象，可以是集合也可以是对象。
     */
    public void createExcel(HttpServletRequest request, HttpServletResponse response, String sheetName, String[] titles, int count, Object conditions)
    {
        response.setContentType("application/binary;charset=UTF-8");
        Map<String, Object> obj = null;
        ViewExcelForXSSFWorkbook viewExcel = new ViewExcelForXSSFWorkbook();
        //创建一个Excel
        XSSFWorkbook wb = new XSSFWorkbook();
        //超大数据处理
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(wb, MAX_ROWS_PER_PAGE);
        sxssfWorkbook.createSheet(sheetName);
        Sheet sheet = sxssfWorkbook.getSheetAt(SHEET_INDEX);
        createDetailColumn(sheet, wb, titles);
        int pages = 0;
        if(count%MAX_ROWS_PER_PAGE == 0)
        {
            pages = count/MAX_ROWS_PER_PAGE;
        }else
        {
            pages = (count/MAX_ROWS_PER_PAGE) + 1;
        }
        //循环写入EXCEL文件。
        writeExceL(pages, MAX_ROWS_PER_PAGE, sheet, conditions);
        try {
            viewExcel.buildExcelDocument(obj, sxssfWorkbook, request, response);
        } catch (Exception e) {
            LOG.debug("生成海量数据的Excel文件异常。" + e.getMessage());
        }
    }

    /**
     * 导出数据到EXCEL。
     *
     * @param request    {@link HttpServletRequest}。
     * @param response   {@link HttpServletResponse}。
     * @param sheetName  导出文件名称。
     * @param titles     Excel文件的表头数组。
     * @param conditions 查询列表的条件需要的对象，可以是集合也可以是对象。
     */
    public void createCommonExcel(HttpServletRequest request, HttpServletResponse response, String sheetName, String[] titles, Object conditions)
    {
        response.setContentType("application/binary;charset=UTF-8");
        Map<String, Object> obj = null;
        ViewExcelForXSSFWorkbook viewExcel = new ViewExcelForXSSFWorkbook();
        //创建一个Excel
        XSSFWorkbook wb = new XSSFWorkbook();
        //超大数据处理
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(wb);
        sxssfWorkbook.createSheet(sheetName);
        Sheet sheet = sxssfWorkbook.getSheetAt(SHEET_INDEX);
        createDetailColumn(sheet, wb, titles);
        //写入EXCEL文件。
        writeCommonExceL(sheet, conditions);
        try {
            viewExcel.buildExcelDocument(obj, sxssfWorkbook, request, response);
        } catch (Exception e) {
            LOG.debug("生成普通的Excel文件异常。" + e.getMessage());
        }
    }

    /**
     * 把数据循环写入文件。
     *
     * @param pages 一共分为多少页。
     * @param maxRowsPerPage 每页最大行数。
     * @param sheet 表单。
     * @param conditions 查询列表需要条件的对象。
     */
    public abstract void writeExceL(int pages, int maxRowsPerPage, Sheet sheet, Object conditions);

    /**
     * 把数据写入文件。
     *
     * @param sheet 表单。
     * @param conditions 查询列表需要条件的对象。
     */
    public abstract void writeCommonExceL(Sheet sheet, Object conditions);
}

