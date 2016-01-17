# SSW-TweetProject
----Folders-----

Input-NetworkAnalysis: Stores the output files, in xsl or csv format.

RDF-Querying Examples: Some example source codes we worked on previously, but did not include in our project.

Reports: Includes the final report and progress presentation.

SSWProject:

  It takes three input files:
  
    trump.txt: each line includes a tweet formatted as, username \t tweet \t location \t language 
    
    slang.txt: includes our slang word dictionary
    
    stop-list.txt: includes a list of stop words
    
  It creates 10 output files under the "output" directory:
  
    locationSlangFreq.txt: location \t slang_word \t frequency,  shows the freq of a slang used in that location
    
    locationSlangFreq.txt: duplicate of the previous
    
    slangConsCoccurFreq.txt: slang_word \t slang_word \t frequency, shows the frequency of two consecutive cooccur. words in a tweet
    
    slangConsCoccurFreqws.txt: same as previous, only with stop words
    
    slangCooccurFreq.txt: slang_word \t slang_word \t frequency, shows the frequency of two  cooccur. words in a tweet
    
    slangCooccurFreqws.txt: same as previous, with stop words
    
    slangFreq.txt: slang \t frequency, total frequency of slang in whole data set
    
    slangFreqws.txt: duplicate of  the previous
    
    userSlangFreq.txt: user \t slang_word \t frequency,  shows the freq of a slang used by that user
    
    userSlangFreqws.txt: duplicate of the previous
