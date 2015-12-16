import java.io.*;


import org.apache.jena.vocabulary.*;

import org.apache.jena.rdf.model.*;;

public class CreateRDF {

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


