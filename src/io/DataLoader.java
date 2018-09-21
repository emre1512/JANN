package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
	
	public enum Seperator { COMMA, SPACE, TAB }
		
	public static List<float[]> loadData(String path, Seperator seperator){
		
		String sep;
		
		if(seperator == Seperator.COMMA){ sep = ","; }
		else if(seperator == Seperator.COMMA) {sep = " ";}
		else { sep = "\t"; }
		
		BufferedReader br = null;
		
	    List<float[]> rows = new ArrayList<>();
	    
	    try {

	        String sCurrentLine;
	        br = new BufferedReader(new FileReader(path));
	        
	        while ((sCurrentLine = br.readLine()) != null) {
	        	
	            String[] arg = sCurrentLine.split(sep);	          
	            
	            float[] row = new float[arg.length];
	            
	            for(int j = 0; j < arg.length; j++){
	            	row[j] = Float.valueOf(arg[j]);
	            }
	            
	            rows.add(row);    
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null)br.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    return rows;
	}

}
