package infoboxNames;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProgramParser
{
	private static long startTime = System.currentTimeMillis();
	
	public static void main(String[] args) throws IOException{
		

		
		//different input files
		File xmlInput = new File("C:/Temp/sample_input_enwiki-latest-pages-articles3.xml-p000025001p000055000");
		//File xmlInput = new File("C:/Temp/sample_input.xml");
		//File xmlInput = new File("C:/Temp/sample_input_testovaci.xml");
		//File xmlInput = new File("C:/Temp/sample_input_testovaci-slovakia.xml");
		//File xmlInput = new File("C:/Temp/sample_input_WW2.xml");
		//File xmlInput = new File("C:/Temp/enwiki-latest-pages-articles3.xml-p000025001p000055000"); // 392 MB
		//File xmlInput = new File("C:/Temp/enwiki-latest-pages-articles26.xml-p026625004p029624976"); // 2.8 GB
		//File xmlInput = new File("C:/Temp/enwiki-latest-pages-articles27.xml-p029625017p044065755"); // 14 GB
		//File xmlInput = new File("C:/School - ING/3. Semester/VINF/Data - Articles/enwiki-latest-pages-articles.xml"); // complete wikipedia 48 GB 

		//output files
		File xmlOutputName = new File("C:/Temp/infobox_output.txt");
		//File xmlOutputInformation = new File("C:/Temp/sample_output_Information.xml");
		
		//parser for xml file
		ArticleXmlParser parser = new ArticleXmlParser();
		
		System.out.println("Parsing Wikipedia file...");
		//filling list of articles
		StringBuilder articles = parser.parseXml(new FileInputStream(xmlInput));
		
		
		FileWriter fw = new FileWriter(xmlOutputName);
		//FileWriter fw2 = new FileWriter(xmlOutputInformation);
		
		//writing infobox names from articles list to file		
		fw.write("");
		/*
		for (Article article: articles){
			fw.append(article.getTitle() + "\t" + article.getInfoboxType() + "\n");
			System.out.println(article.getTitle() + "\t" + article.getInfoboxType());
		}
		*/
		
		long parsingTime = System.currentTimeMillis();		
		System.out.println("Time: " + (parsingTime - startTime)/1000 + " seconds");
		
		System.out.println("Writing result to file...");
		fw.append(articles.toString());
		fw.close();
		
		System.out.println("Writing result to console...");
		System.out.println(articles.toString());

		
		//writing infobox information from articles list to file
		/*
		System.out.println("\n");
		fw2.write("");
		for (Article article: articles){
			if(!article.getInfoboxProperty().isEmpty()){
				fw2.append(article.getTitle() + "\t" + article.getInfoboxProperty() + "\n");
				System.out.println(article.getTitle() + "\t" + article.getInfoboxProperty());
			}
		}
		
		fw2.close();
		*/
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time: " + (endTime - startTime)/1000 + " seconds");
	}

}
