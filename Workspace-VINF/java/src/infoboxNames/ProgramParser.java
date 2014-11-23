package infoboxNames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		File xmlOutput = new File("C:/Temp/infobox_output.txt");
		//File xmlOutputInformation = new File("C:/Temp/sample_output_Information.xml");
		
		//parser for xml file
		ArticleXmlParser parser = new ArticleXmlParser();
		
		System.out.println("Parsing Wikipedia file...");
		//filling list of articles
		StringBuilder infoboxes = parser.parseXml(new FileInputStream(xmlInput));
		System.out.println("Wikipedia file parsed");		
		
		long parsingTime = System.currentTimeMillis();		
		System.out.println("Parsing time: " + (parsingTime - startTime)/1000 + " seconds");
		
		FileWriter fw = new FileWriter(xmlOutput);
		
		//writing infobox names and properties from articles list to file		
			
		System.out.println("Writing result to file...");
		fw.write("");
		fw.append(infoboxes.toString());
		fw.close();
		System.out.println("Result was written to file");		
		//System.out.println("Writing result to console...\n");
		//System.out.println(articles.toString());
		//System.out.println("Result was written to console\n");
			
		long endTime = System.currentTimeMillis();
		System.out.println("File writing time: " + (endTime - parsingTime)/1000 + " seconds\n");
		
		
		//STATISTICAL PROCESSING
		System.out.println("Creating statistics...");
		//File xmlOutputName = new File("C:/Temp/infobox_output.txt");
		//File xmlOutputName = new File("C:/Temp/sample_output_name - 48 GB - po riadkoch.xml");
		
		Statistics statistics = new Statistics();
		System.out.println("Statistics was created");
		
		System.out.println("Writing statistics to files..");
		try {
			FileReader frs = new FileReader(xmlOutput);
			BufferedReader bufferedReader = new BufferedReader(frs);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				//send line to statistic method to process line
				statistics.ProcessLine(line);
			}
			frs.close();
			statistics.WriteStatistics();
			System.out.println("Statistics was written to files..");
		} catch (IOException e) {
			e.printStackTrace();
		}		

	}

}
