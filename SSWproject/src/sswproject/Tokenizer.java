/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sswproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author betül
 */
public class Tokenizer {
    
    public static List<String> tokenize(String text) throws IOException
    {
        List<String> words = new ArrayList<String>();
        List<String> stopWList = SlangDictionary.stopWordList();
        
        String separator = "!?. ";
        
        StringTokenizer st = new StringTokenizer( text, separator, true );

        while ( st.hasMoreTokens() ) {
            String token = st.nextToken();
            if ( token.length() == 1 && separator.indexOf( token.charAt( 0 ) ) >= 0 ) {
                //System.out.println( "special char:" + token );
            }
            else {
                token = token.toLowerCase();
                token = token.trim();   
                token = token.replace(",", "");
                
               if(!stopWList.contains(token))
                	words.add(token);
            }

        }
        return words;
    }
}
