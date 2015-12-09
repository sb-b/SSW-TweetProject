/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sswproject;

import java.util.List;

/**
 *
 * @author betül
 */
public class TweetObject {
    	String username;
	String tweetText;
	List<String> tweetWords;
        String location;
        String lang;

        public TweetObject()
        {
                this.username = null;
		this.tweetText = null;
                this.tweetWords = null;
		this.location = null;
                this.lang = null;
        }
        
	public TweetObject(String uname, String text, List<String> words, String loc, String lang) 
	{
		this.username = uname;
		this.tweetText = text;
                this.tweetWords = words;
		this.location = loc;
                this.lang = lang;
		
	}
}
