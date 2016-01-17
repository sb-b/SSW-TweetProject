import java.io.*;


import org.apache.jena.vocabulary.*;

import org.apache.jena.rdf.model.*;;

public class CreateRDF {
/*
Jena API is used for generating RDF, which is available on https://jena.apache.org/.
Jar files in library folder should be imported into the project. 
Also, javadoc files needs to be specified in project properties in order to have program run.
/*
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			  String personURI  = "http://localhost/yagmur";
			  String givenName  = "Jon";
			  String familyName = "May";
			  String fullName = givenName+familyName;

			  Model model = ModelFactory.createDefaultModel();

			  Resource node = model.createResource(personURI)
			 .addProperty(VCARD.FN, fullName)
			 .addProperty(VCARD.N,model.createResource()
			 .addProperty(VCARD.Given, givenName)
			 .addProperty(VCARD.Family, familyName));
			  model.write(System.out);
			  }
			
	}


