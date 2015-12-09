/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sswproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sswproject.Tokenizer;


/**
 *
 * @author betül
 */
public class SSWproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Tweet Object List where each object has a username, tweet text, words of tweets, location, and language
        List<TweetObject> TO_List; 
        TO_List = new ArrayList<TweetObject>();
        SlangDictionary slangDic = new SlangDictionary();
        Map<String, String>  slangWDic = slangDic.buildDictionary("slangs.txt");
        
        
        //reading xmas.txt
        BufferedReader br = new BufferedReader(new FileReader("xmas.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            
            //all tweets are in this array
            String[] tweets = everything.split("\\[*\\]\r\n");
            
            //parsing tweets into username, text, words, location, and language
            for( int i = 0; i < tweets.length; i++)
            {
                tweets[i] = tweets[i].replace("\"","");
                String[] temp = tweets[i].split("\r\n");
                
                String uname = temp[1].substring(0, temp[1].length()-1);
                uname = uname.trim();
                String text = temp[2].substring(0, temp[2].length()-1);
                text = text.trim();
                String loc = temp[3].substring(0, temp[3].length()-1);
                loc = loc.trim();
                String lang = temp[4].trim();
               
                //tokenization
                List<String> words = Tokenizer.tokenize(text);
                
                //create a tweet object with username,tex,words, loc, and langugage areas
                TweetObject to = new TweetObject(uname, text, words, loc, lang);
                //add that obejct into Tweet object list
                TO_List.add(to);
            }
            
        } finally {
            br.close();
        }
        
        
        ParseTweetTexts.checkTextsforSlangs(TO_List, slangWDic);
     
    }
    
}
