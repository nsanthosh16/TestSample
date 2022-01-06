package samplePackage;
import java.lang.Math.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  

public class TestSample {
	
double fnCalNegate(double lDblX,double lDblC,double lDblN,String lStrOp){
	
	double lDblYnegate =0.0;
	
	if(lStrOp.equals("+")){
		lDblYnegate = lDblC + lDblN * lDblX;
	}else if(lStrOp.equals("-")){
		lDblYnegate = lDblC - lDblN * lDblX;
	}
	
	return lDblYnegate;
}
double fnCalDeltaSquare(double lDblYNeg,double lDblY){
	double lDblDeltaSq  = 0.0;
	lDblDeltaSq = Math.sqrt(lDblYNeg - lDblY);
	
	return lDblDeltaSq;
	
}

double fnCalDeltaMean(double lDblSumOfDeltaSq,int lIntRecCnt){
	double lDblDeltaMean = 0.0;
	lDblDeltaMean = lDblSumOfDeltaSq / lIntRecCnt;
	return lDblDeltaMean;
}

public static void main(String a[]){
	CSVReader lCSVreader = null;  
	try{
		String lStrline = "";  
		String splitBy = ",";  
		TestSample lStr = new TestSample();
		double lDblYNeg = 0.0;
		double lDblMeanOfDelta = 0.0;
		double lDblSumOfDeltaSq = 0.0;
		int lIntSize = 0;
		double lDblY = 0.0;
		double lDblX = 0.0;
		Scanner lScannerRead = new Scanner(System.in);
		System.out.print("Enter a value in c op nX :: ");
		String lStrFormat= lScannerRead.nextLine(); 
		boolean isValidFormat = false;
		
		String regex = "[\\+\\-]?(\\d+(\\.\\d+)?)\\s(\\+|-)+\\s?(\\d+(\\.\\d+)?)X";
	    
	    Pattern lPattern = Pattern.compile(regex);
	    if(lPattern.matches(regex, lStrFormat)){
	    	isValidFormat = true;
	    }else{
	    	System.out.println(" Invalid format c op nX");
	    }
	    
	    if(isValidFormat){
		String[] eqVal = lStrFormat.split(" ");
	    double lDblFirstVal = Double.parseDouble(eqVal[0]);
	    String lStrOp =  eqVal[1];
	    String lStrValue = eqVal[2];
	    double lDblSecondVal = Double.parseDouble(lStrValue.replace("X", ""));
		
		System.out.println(":: " +lDblSecondVal);
		
		BufferedReader br = new BufferedReader(new FileReader("E:\\File_Name\\myFileName.csv")); 
		boolean firstLine = true;
		lIntSize = 0;
		while ((lStrline = br.readLine()) != null)
		{  
			if (firstLine) 
			   {
			      firstLine = false;
			       continue;
			   } 
		
			String[] lStrValues = lStrline.split(splitBy);    
			lDblX = Double.parseDouble(lStrValues[0]);
			lDblY = Double.parseDouble(lStrValues[1]);
		
		
			lDblYNeg = lStr.fnCalNegate(lDblX,lDblFirstVal, lDblSecondVal, "+");
			
			lDblSumOfDeltaSq = lDblSumOfDeltaSq + lStr.fnCalDeltaSquare(lDblYNeg, lDblY);
		
			lIntSize = lIntSize +1;
			
		}  
		lDblMeanOfDelta = lStr.fnCalDeltaMean(lDblSumOfDeltaSq, lIntSize);
		
		 BigDecimal lDblDeltaMean=new BigDecimal(lDblMeanOfDelta).setScale(2,RoundingMode.HALF_DOWN);
		 
		System.out.println("Mean of Delta:: "+ lDblDeltaMean);
	    }
	}
	catch(FileNotFoundException e){
		e.printStackTrace();
	}
	catch(NumberFormatException e){
		e.printStackTrace();
	}
	
	catch(Exception e){
		e.printStackTrace();
	}
	
}
}
