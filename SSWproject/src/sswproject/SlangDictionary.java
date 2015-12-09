package sswproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;




public class SlangDictionary {
	
	
	
	public static  Map<String, String>  buildDictionary(String path) throws IOException
	{
		   Map<String, String> slangDic = new LinkedHashMap<>();
		  
		  BufferedReader reader = null;
          try {
              reader = new BufferedReader(new FileReader(path));
          }  catch (FileNotFoundException ex) {
              
          }
          String line = null;
            try {
				while ((line = reader.readLine()) != null) {
                    
					line = line.toLowerCase();
					
				    String [] tokens = line.split("\t");
				    String slang = tokens[1];
				    String actual = tokens[0];
				    
				    if(!slangDic.containsKey(slang))
				        slangDic.put(slang, actual);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           finally
           {
             reader.close();
           }
            
            return slangDic;
	}

}
