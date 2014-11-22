package infoboxNames;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ArticleXmlParser {

	public StringBuilder parseXml(InputStream in)
	{
	
		//ArrayList<Article> articles = new ArrayList<Article>();
		StringBuilder articles = new StringBuilder();
		
		try
		{
			ArticleParserHandler handler = new ArticleParserHandler();
			
			XMLReader parser = XMLReaderFactory.createXMLReader();
			
			parser.setContentHandler(handler);
			
			InputSource source = new InputSource(in);
			
			parser.parse(source);
			
			articles = handler.getArticles();
		
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return articles;
	}
}
