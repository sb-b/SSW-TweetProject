import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


import org.apache.jena.vocabulary.*;

import org.apache.jena.rdf.model.*;;


public class ReadFile {
    public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "filePath/CreateRDF/src/slangCooccurFreq.txt";

        // This will reference one line at a time
        String line = null;
        ArrayList<String> aList = new ArrayList<String>();
        ArrayList<String> co1 = new ArrayList<String>();
        ArrayList<String> co2 = new ArrayList<String>();
        ArrayList<String> freq = new ArrayList<String>();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	aList.add(line);
        //    	 String[] arr = line.split(" ");
            //	 for ( String ss : arr) {

            //	       System.out.println(ss);
            //	  }
           // 	 System.out.println(line);
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        Object[] objDays = aList.toArray();
        
        //Second Step: convert Object array to String array
        String[] strDays = Arrays.copyOf(objDays, objDays.length, String[].class);
       
        System.out.println("ArrayList converted to String array");
  
        
        //print elements of String array
        for(int i=0; i < strDays.length; i++){
            //    System.out.println(strDays[i]);
                String[] arr = strDays[i].split(" ");
                co1.add(arr[0]);
               co2.add(arr[2]);
            //    System.out.println(co2);
        }
        
        
        
        String personURI  = "http://localhost/yagmur";
		 String fullName = "co-occurance";
        for(int i=0; i<100 ;i++)
        {
        	// co2.get(i).replaceAll("1","");
        	 
		  Model model = ModelFactory.createDefaultModel();
          
		  Resource node = model.createResource(personURI)
		 .addProperty(VCARD.FN, fullName)
		 .addProperty(VCARD.N,model.createResource()
		 .addProperty(VCARD.NAME, co2.get(i).replaceAll("[^A-Za-z]",""))
		 .addProperty(VCARD.Other, co1.get(i)));
		  model.write(System.out);
         
        }
        
    }
    
}
