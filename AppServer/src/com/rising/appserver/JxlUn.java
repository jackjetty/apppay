package com.rising.appserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rising.appserver.common.CommonUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class JxlUn{
	public static void main(String[] args) throws BiffException, IOException{
		
		//D:\冉思\冉思统计\外呼电话.xls
		 //创建一个list 用来存储读取的内容
	    List list = new ArrayList();
	    Workbook rwb = null;
	    Cell cell = null;
	   
	    //创建输入流
	    InputStream stream = new FileInputStream("D:\\冉思\\冉思统计\\外呼电话.xls");
	   
	    //获取Excel文件对象
	    rwb = Workbook.getWorkbook(stream);
	   
	    //获取文件的指定工作表 默认的第一个
	    Sheet sheet = rwb.getSheet(0); 
	    String phoneNumber;
	  
	    //行数(表头的目录不需要，从1开始)
	    for(int i=0; i<sheet.getRows(); i++){
	    
	     //创建一个数组 用来存储每一列的值
	     String[] str = new String[sheet.getColumns()];
	    
	      
	    
	      //获取第i行，第j列的值
	      cell = sheet.getCell(0,i);  
	      phoneNumber=CommonUtil.trim(cell.getContents());
	      if(!phoneNumber.equals(""))
	      System.out.println("insert into rs_temp(phoneNumber) values('"+phoneNumber+"')"); 
	     
	    
	    }
	    
	}
}