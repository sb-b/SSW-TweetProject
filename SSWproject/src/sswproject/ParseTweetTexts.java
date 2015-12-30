package sswproject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class ParseTweetTexts {
	
	  public static Map<String, Integer> slangFreq = new LinkedHashMap<>();
	  public static Map<String, Integer> slangCooccurFreq = new LinkedHashMap<>();
	  public static Map<String, Integer> slangConsCoccurFreq = new LinkedHashMap<>();
	  public static Map<String, Integer> userSlangFreq = new LinkedHashMap<>();
	  public static Map<String, String> userLocation = new LinkedHashMap<>();
	  public static Map<String, Integer> locationSlangFreq = new LinkedHashMap<>();
	  
	  public static void checkTextsforSlangs(List<TweetObject> TO_List, Map<String, String> slangDictionary) throws IOException
	  {
		  int encount = 0;
		  String prevWord = "";
		  String nextWord = "";
		  
		  for(TweetObject toNew : TO_List)
		  {
			  if(toNew.lang.equals("en"))
			  {
				  encount ++;
				  List<String> toText = toNew.tweetWords;
				  String username = toNew.username;
				  String location = toNew.location;
				  
				  
				  for(String curWord : toText)
				  {
										  
					  if(slangDictionary.containsKey(curWord))
					  {
						  if(!slangFreq.containsKey(curWord))
						  {
							  slangFreq.put(curWord, 1);
						  }
						  else
						  {
							  int freq = slangFreq.get(curWord);
							  slangFreq.put(curWord, freq+1);
							  
						  }
						  
						  int sIndex = toText.indexOf(curWord);
						  
						  if(sIndex > 0)
						  {
							  prevWord = toText.get(sIndex -1);
							  fillConsCooccurList(curWord, prevWord,0);
						  }
						  if(sIndex < toText.size()-1)
						  {
							  nextWord = toText.get(sIndex +1);
							  fillConsCooccurList(curWord, nextWord,1);
						  }
						  
						  fillCooccurList(toText, curWord);
						  fillUserSlangList(username, curWord);
						  fillLocationSlangList(location, curWord);

					  }
					  
					   fillUserLocationList(username, location);
				  }
				  
			  }
		  }

		 System.out.println(encount);
	    printOutputs();
	  }
	  
	  
	  public static void fillCooccurList(List<String> wordList, String slang )
	  {
		 
		  for(String words : wordList)
		  {		
			  if(!words.contains("@"))
			  {
				  if(slang != words)
				  {
					  String cooccur = slang + "--" + words;

			          if(!slangCooccurFreq.containsKey(cooccur))
			          {
			        	  slangCooccurFreq.put(cooccur, 1);
			          }
			          else
			          {
			        	  int freq = slangCooccurFreq.get(cooccur);
			        	  slangCooccurFreq.put(cooccur, freq+1);
			          }
				  }
			  }
		  }
	  }
	  
	  
	  private static void fillConsCooccurList(String slang, String w1, int flag)
	  {
		  String cooccur = "";
		 if(!w1.contains("@"))
		 {
	        if(flag == 1)
			  cooccur = slang + "--" + w1;
	        else if(flag== 0)
	        	cooccur = w1 + "--" + slang;

			if(!slangConsCoccurFreq.containsKey(cooccur))
			   {
				slangConsCoccurFreq.put(cooccur, 1);
			   }
			else
			     {
			       int freq = slangConsCoccurFreq.get(cooccur);
			       slangConsCoccurFreq.put(cooccur, freq+1);
			     }	
			
		 }
		
	  }
	  
	  public static void fillUserSlangList(String user, String slang)
	  {
		 
			String cooccur = user + "--" + slang;

			if(!userSlangFreq.containsKey(cooccur))
			   {
				userSlangFreq.put(cooccur, 1);
			   }
			else
			     {
			       int freq = userSlangFreq.get(cooccur);
			       userSlangFreq.put(cooccur, freq+1);
			     }	

		
	  }
	  
	  public static void fillLocationSlangList(String loc, String slang)
	  {
		 
		  if(loc.equals(""))
			  loc = "empty_field";
		  
			String cooccur = loc + "--" + slang;

			if(!locationSlangFreq.containsKey(cooccur))
			   {
				locationSlangFreq.put(cooccur, 1);
			   }
			else
			     {
			       int freq = locationSlangFreq.get(cooccur);
			       locationSlangFreq.put(cooccur, freq+1);
			     }	
		  

		
	  }
	  
	  public static void fillUserLocationList(String user, String location)
	  {
		  if(location.equals(""))
			  location = "empty_field";
		  
			if(!userLocation.containsKey(user))
			   {
				userLocation.put(user, location);
			   }
		
	  }
	  
	  public static void writeToText(Map<String, Integer> list, String filename) throws IOException
	  {
		  
		    FileWriter fw = new FileWriter(filename);
	        PrintWriter pw = new PrintWriter(fw);
	        
	        
		  for(Entry<String, Integer>  slangs : list.entrySet() )
		  {
			  String slangWord = slangs.getKey();
			  
			
			  String [] words = slangWord.split("--");
			  int slangFreq = slangs.getValue();
			  if(words.length > 1)
			  {
				 if(!words[0].equals("") && !words[1].equals(""))
			     pw.println(words[0] + "\t" + words[1] + "\t" +slangFreq );
			  }
			
		  }
		  
		  pw.close();
	  }
	  
	
	  
	  public static void printOutputs() throws IOException
	  {
		  
		  writeToTexto(slangFreq, "slangFreq.txt");
		  writeToText(slangCooccurFreq, "slangCooccurFreq.txt");
		  writeToText(slangConsCoccurFreq, "slangConsCoccurFreq.txt");
		  writeToText(userSlangFreq, "userSlangFreq.txt");
		  writeToText(locationSlangFreq, "locationSlangFreq.txt");
		  
		  System.out.println("Analysis completed.");
		 
	 }
	  
	  public static void writeToTexto(Map<String, Integer> list, String filename) throws IOException
	  {
		  
		    FileWriter fw = new FileWriter(filename);
	        PrintWriter pw = new PrintWriter(fw);
	        
	        
		  for(Entry<String, Integer>  slangs : list.entrySet() )
		  {
			  String slangWord = slangs.getKey();		
			  String [] words = slangWord.split("-");
			  int slangFreq = slangs.getValue();
			
				  pw.println(words[0]  + "\t" +slangFreq );
		  }
		  
		  pw.close();
	  }
	  
	  

}
