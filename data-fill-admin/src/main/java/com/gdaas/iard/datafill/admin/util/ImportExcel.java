package com.gdaas.iard.datafill.admin.util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.gdaas.iard.datafill.admin.repo.dao.entity.TaskEntity;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;




@Component
public class ImportExcel {

    public static ImportExcel importExcel;
    
    @PostConstruct
    public void init() {
    	importExcel = this;
    } 

	public Map<String,Object> imputExcelTask(MultipartFile file) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setLenient(false);
		DecimalFormat nf = new DecimalFormat("0.##");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		if( !file.getOriginalFilename().endsWith(".xlsx")) {
			HSSFWorkbook workbook;
	    	HSSFSheet sheet;
	    	Map<String, Object> content = new HashMap<String, Object>();
	    	workbook=new HSSFWorkbook(getStream(file));// 创建Excel2003文件对象
	    	sheet = workbook.getSheetAt(0);
			//取出所有行
			for (int i = 2; i <= 300; i++) {
				Map<String,Object> map = new HashMap<>();
				TaskEntity task = new TaskEntity();
				Map<String,String> release = new HashMap<>();
 	     		if (sheet.getRow(i) == null) {// 此行如果为空，不处理
	     			task=null;
	     			break;//////////////////////////////////////////////////stop uploading flag
	     		}
	     		HSSFRow row = sheet.getRow(i);
	     		//取出所有列
	     		for(int j = 0;j<=10;j++){
	     			HSSFCell cell_s = row.getCell(j); 
	     			String cellStr ="";
			 		    if (cell_s == null||cell_s.equals("")||cell_s.equals("")||cell_s.equals(null)) {// 单元格为空设置cellStr为空串  
			 		    	cellStr="";
			 		    	
			 		    } else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理  
			 		        cellStr = String.valueOf(cell_s.getBooleanCellValue());
			 		    }  else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {// 对公式生成值的处理  
			 		        try {  
			 		            cellStr = String.valueOf(cell_s.getNumericCellValue());
			 		        } catch (IllegalStateException e) {  
			 		            cellStr = String.valueOf(cell_s.getRichStringCellValue());    	
			 		        }  
			 		    }else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理  
			 		        cellStr =nf.format(cell_s.getNumericCellValue());
			 		    } else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_ERROR) {// 对错误数据的处理  
			 		    	task=null;
			 		    	content.put("error", "第"+(i+1)+"行数据有问题请检查!");
			 		    	//////////////////////////////////////////////////stop uploading flag
			 		    } else {// 其余按照字符串处理  
			 		        cellStr = cell_s.getStringCellValue();
			 		      
			 		        if(cellStr==null||cellStr.trim().equals("")){
			 		        	cellStr = "";
			 		        }
			 		    }
			 		    cellStr = cellStr.trim();
			 		   if(j==0){
				 			 try {
				 				task.setTaskName(cellStr);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第1列单元格出错\n");
							}
			 		   }else if(j==1){
			 			  try {
			 				  	task.setTaskText(cellStr);
							} catch (Exception e) {
								e.printStackTrace();
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第2列单元格出错\n");
							}
			 		   }else if(j==2){
							try {
                                String[] tt = cellStr.split("\\.");
                                System.out.println(tt[0]);
                                System.out.println("0."+tt[1]);
                                Date date = getDate(Integer.parseInt(tt[0]));
                                double dou = Double.parseDouble("0."+tt[1]);
                                Date strDate = getTime(date,dou);
							    task.setStartime(strDate);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第3列单元格出错\n");
							}
			 		   }else if(j==3){
							try{
                                String[] tt = cellStr.split("\\.");
                                System.out.println(tt[0]);
                                System.out.println("0."+tt[1]);
                                Date date = getDate(Integer.parseInt(tt[0]));
                                double dou = Double.parseDouble("0."+tt[1]);
                                Date strDate = getTime(date,dou);
								task.setEndtime(strDate);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第4列单元格出错\n");
							}
					   }else if(j==4){
						   try{
							   task.setTaskDate(getDate(Integer.parseInt(cellStr)));
						   } catch (Exception e) {
							   content.put("error", "数据:"+getDate(Integer.parseInt(cellStr))+";"+"第"+(i+1)+"行第5列单元格出错\n");
						   }
					   }else if(j==5){
						   try{
						   		//支行(不包含自贸区)
							   release.put("subbranch",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第6列单元格出错\n");
						   }
					   }else if(j==6){
						   try{
							   //自贸区
							   release.put("fta",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第7列单元格出错\n");
						   }
					   }else if(j==7){
						   try{
							   //网点(不包含社区)
							   release.put("dot",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第8列单元格出错\n");
						   }
					   }else if(j==8){
						   try{
							   //社区
							   release.put("community",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第9列单元格出错\n");
						   }
					   }else if(j==9){
						   try{
						   		//指定机构
							   release.put("type",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第10列单元格出错\n");
						   }
					   }else if(j==10){
						   try{
							   //机构编号(以|分隔)
							   release.put("number",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第11列单元格出错\n");
						   }
					   }
	     			}
				map.put("Task",task);
				System.out.println("releasemap:"+release);
	     		map.put("release",release);
	     		list.add(map);
	         }
			content.put("ok",list);
			return content;
		} else{
			XSSFWorkbook workbook;
	    	XSSFSheet sheet;
	    	Map<String, Object> content = new HashMap<String, Object>();
	    	workbook=new XSSFWorkbook(getStream(file));// 创建Excel2003文件对象
	    	sheet = workbook.getSheetAt(0);
			//取出所有行
	    	for (int i = 2; i <= 300; i++) {
				Map<String,Object> map = new HashMap<>();
				TaskEntity task = new TaskEntity();
				Map<String,String> release = new HashMap<>();
	     		if (sheet.getRow(i) == null) {// 此行如果为空，不处理 
	     			task=null;
	     			break;
	     		}
	     		XSSFRow row = sheet.getRow(i);
	     		//取出所有列
	     		for(int j = 0;j<=10;j++){
	     			XSSFCell cell_s = row.getCell(j); 
	     			String cellStr ="";
			 		    if (cell_s == null||cell_s.equals("")||cell_s.equals("")||cell_s.equals(null)) {// 单元格为空设置cellStr为空串  
			 		    	cellStr="";
			 		    	
			 		    } else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理  
			 		        cellStr = String.valueOf(cell_s.getBooleanCellValue());
			 		    }  else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {// 对公式生成值的处理  
			 		        try {  
			 		            cellStr = String.valueOf(cell_s.getNumericCellValue());
			 		        } catch (IllegalStateException e) {  
			 		            cellStr = String.valueOf(cell_s.getRichStringCellValue());    	
			 		        }  
			 		    }else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理  
			 		        cellStr =nf.format(cell_s.getNumericCellValue());
			 		    } else if (cell_s.getCellType() == XSSFCell.CELL_TYPE_ERROR) {// 对错误数据的处理  
			 		    	task=null;
			 		    	content.put("error", "第"+(i+1)+"行数据有问题请检查!");
			 		    	//////////////////////////////////////////////////stop uploading flag
			 		    } else {// 其余按照字符串处理  
			 		        cellStr = cell_s.getStringCellValue();
			 		      
			 		        if(cellStr==null||cellStr.equals("")){
			 		        	cellStr = "";
			 		        	
			 		        	
			 		        }
			 		    } 
			 		   cellStr = cellStr.trim();
			 		    	
			 		   if(j==0){
				 			 try {
								 task.setTaskName(cellStr);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第1列单元格出错\n");
							}
			 		   }else if(j==1){
			 			  try {
							  task.setTaskText(cellStr);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第2列单元格出错\n");
							}
			 		   }else if(j==2){
			 			  try {
                              String[] tt = cellStr.split("\\.");
                              System.out.println(tt[0]);
                              System.out.println("0."+tt[1]);
                              Date date = getDate(Integer.parseInt(tt[0]));
                              double dou = Double.parseDouble("0."+tt[1]);
                              Date strDate = getTime(date,dou);
							  task.setStartime(strDate);
							} catch (Exception e) {
								content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第3列单元格出错\n");
							}
			 		   }else if(j==3){
						   try{
                               String[] tt = cellStr.split("\\.");
                               System.out.println(tt[0]);
                               System.out.println("0."+tt[1]);
                               Date date = getDate(Integer.parseInt(tt[0]));
                               double dou = Double.parseDouble("0."+tt[1]);
                               Date strDate = getTime(date,dou);
							   task.setEndtime(strDate);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第4列单元格出错\n");
						   }
					   }else if(j==4){
						   try{
							   task.setTaskDate(getDate(Integer.parseInt(cellStr)));
						   } catch (Exception e) {
							   content.put("error", "数据:"+getDate(Integer.parseInt(cellStr))+";"+"第"+(i+1)+"行第5列单元格出错\n");
						   }
					   }else if(j==5){
						   try{
							   //支行(不包含自贸区)
							   release.put("Subbranch",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第6列单元格出错\n");
						   }
					   }else if(j==6){
						   try{
							   //自贸区
							   release.put("fta",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第7列单元格出错\n");
						   }
					   }else if(j==7){
						   try{
							   //网点(不包含社区)
							   release.put("dot",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第8列单元格出错\n");
						   }
					   }else if(j==8){
						   try{
							   //社区
							   release.put("Community",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第9列单元格出错\n");
						   }
					   }else if(j==9){
						   try{
							   //指定机构
							   release.put("type",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第10列单元格出错\n");
						   }
					   }else if(j==10){
						   try{
							   //机构编号(以|分隔)
							   release.put("number",cellStr);
						   } catch (Exception e) {
							   content.put("error", "数据:"+cellStr+";"+"第"+(i+1)+"行第11列单元格出错\n");
						   }
					   }
				}
				map.put("Task",task);
				map.put("release",release);
				list.add(map);
	         }
			content.put("ok",list);
			return content;
		}
	}

	public static File getFile(MultipartFile file){	
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
		DiskFileItem fi = (DiskFileItem)cf.getFileItem();
		File f = fi.getStoreLocation();
		return f;
	}
	
	public static InputStream getStream(MultipartFile file) throws IOException{
		InputStream is = new ByteArrayInputStream(file.getBytes());
		return is;
	}
	
	public String sishewuru (String str){
		if(str == null || "".equalsIgnoreCase(str)){
			return "";
		}else{
			DecimalFormat df2 =new DecimalFormat("0.00");
			String str2 =df2.format(new BigDecimal(str));
			return str2;
		}
		
	}

    public static Date getDate(int days) {

        Calendar c = Calendar.getInstance();

        c.set(1900, 0, 1);

        c.add(Calendar.DATE, days - 2);

        return c.getTime();

    }

    public static Date getTime(Date date, double ditNumber) {

        Calendar c = Calendar.getInstance();

        int mills = (int) (Math.round(ditNumber * 24 * 3600));

        int hour = mills / 3600;

        int minute = (mills - hour * 3600) / 60;

        int second = mills - hour * 3600 - minute * 60;

        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, hour);

        c.set(Calendar.MINUTE, minute);

        c.set(Calendar.SECOND, second);

        return c.getTime();

    }
}
