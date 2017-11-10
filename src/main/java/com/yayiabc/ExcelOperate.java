package com.yayiabc;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * poi导出excel
 * @author Administrator
 *
 */
public class ExcelOperate {
  public static void main(String[] args) {
      //创建工作簿
	  HSSFWorkbook workbook = new HSSFWorkbook();
	  //新建工作表
	  HSSFSheet sheet = workbook.createSheet("hello");
	  //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
	   HSSFRow row = sheet.createRow(0);
	   //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
}
}
