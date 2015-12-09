package sswproject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sswproject.SlangDictionary;

public class ParseTweetTexts {
	
	  public static Map<String, Integer> slangFreq = new LinkedHashMap<>();
	  public static Map<String, Integer> slangCooccurFreq = new LinkedHashMap<>();
	  
	  
	  public static void checkTextsforSlangs(List<TweetObject> TO_List, Map<String, String> slangDictionary)
	  {
		  int encount = 0;
		  for(TweetObject toNew : TO_List)
		  {
			  if(toNew.lang.equals("en"))
			  {
				  encount ++;
				  List<String> toText = toNew.tweetWords;
				  
				  for(String curWord : toText)
				  {
					  List<String> cooccur = new ArrayList<String>();
					  
					  if(slangDictionary.containsKey(curWord))
					  {
						  if(!slangFreq.containsKey(curWord))
						  {
							  slangFreq.put(curWord, 0);
						  }
						  else
						  {
							  int freq = slangFreq.get(curWord);
							  slangFreq.put(curWord, freq+1);
							  
						  }
						  
						  cooccur.add(curWord); 
						  
					  }
					  
					  fillCooccurList(cooccur);
				  }
				  
			  }
		  }

		 System.out.println(encount);
	    printOutputs();
	  }
	  
	  
	  public static void fillCooccurList(List<String> cooccurList )
	  {
		 
		  for(String slang : cooccurList)
		  {
			  for(String slang2 : cooccurList)
			  {
				  if(slang != slang2)
				  {
					  String cooccur = slang + " - " + slang2;
					  String cooccur2 = slang2 + " - " + slang;
			          if(!slangCooccurFreq.containsKey(cooccur)&& !slangCooccurFreq.containsKey(cooccur2))
			          {
			        	  slangCooccurFreq.put(cooccur, 0);
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
	  
	  
	  public static void printOutputs()
	  {
		  for(Entry<String, Integer>  slangs : slangFreq.entrySet() )
		  {
			  String slangWord = slangs.getKey();
			  int slangFreq = slangs.getValue();
			  System.out.println(slangWord + "\t" +slangFreq );
		  }
		  
		  System.out.println("-----------------------------------");
		  
		  for(Entry<String, Integer>  slangs : slangCooccurFreq.entrySet() )
		  {
			  String slangWords = slangs.getKey();
			  int coFreq = slangs.getValue();
			  System.out.println(slangWords + "\t" +coFreq );
		  }
	  }

}
