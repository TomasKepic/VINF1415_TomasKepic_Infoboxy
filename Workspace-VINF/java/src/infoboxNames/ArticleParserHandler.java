package infoboxNames;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ArticleParserHandler extends DefaultHandler
{
	//private ArrayList articleList = new ArrayList();
	
	private StringBuilder articleList= new StringBuilder();
	
	private Stack elementStack = new Stack();
	
	private Stack objectStack = new Stack();
	
	public void startDocument() throws SAXException
	{
		//System.out.println("start of the document: ");
	}
	
	public void endDocument() throws SAXException
	{
		//System.out.println("end of the document: ");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		this.elementStack.push(qName);
		
		if("page".equals(qName))
		{
			Article article = new Article();
			
			article.appendText("");
			
			this.objectStack.push(article);
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		this.elementStack.pop();
		
		if ("page".equals(qName))
		{
			Article object = (Article) this.objectStack.pop();
			
			//System.out.println(object.getTitle());
			
			object = InfoboxParser.parseArticleInfoboxType(object);
			
			//if we dont have any type of infobox in article we dont find properties
			if(!object.getInfoboxType().isEmpty() &&
					(object.getInfoboxType().contains("country") || object.getInfoboxType().contains("musical artist")) ){
				object = InfoboxParser.parseArticleInfoboxProperty(object);
			}
			
			object.removeText();
			
			//if we dont have any type of infobox in article we dont add article to our articleList
			if(!object.getInfoboxType().isEmpty()){
				//this.articleList.add(object);
				articleList.append(object.getTitle() + "\t" + object.getInfoboxType() + "\n");
			}

		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String value = new String(ch, start, length);
		
		if (value.length() == 0){
			return;
		}
		
		if("title".equals(currentElement()))
		{
			Article article = (Article) this.objectStack.peek();
			article.setTitle(value);
		}
		else if ("text".equals(currentElement()))
		{
			Article article = (Article) this.objectStack.peek();
			
			article.appendText(value);
			
			//article = InfoboxParser.parseArticleInfoboxType(article);

		}
	}
	
	private String currentElement()
	{
		return (String) this.elementStack.peek();
	}
	
	/*
	public ArrayList getArticles()
	{
		return articleList;
	}
	*/
	public StringBuilder getArticles()
	{
		return articleList;
	}
	
}
