package infoboxNames;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoboxParser {

	/*public static ArrayList parseArticles(ArrayList<Article> articles) {

		Pattern p = Pattern.compile("\\{\\{(\\s)*Infobox(\\s)(.*)");

		for(Article article: articles){
			
			Matcher m = p.matcher(article.getText());
			
			while( m.find()){
				article.setInfoboxType(m.group(3));
			}
					
			article.removeText();
		}
		
		return articles;
	}*/
	
	public static Article parseArticleInfoboxType(Article article) {

		String text = article.getText();
		
		//infobox first type {{infobox
		Pattern p1 = Pattern.compile("(?i)\\{\\{(\\s)*infobox(\\s)([^<|\\n\\}]*)");
			
		Matcher m1 = p1.matcher(article.getText());
			
		while( m1.find()){
			article.insertInfoboxType(m1.group(3).toString().replaceAll("\\s+$", ""));
		}
		
		//infobox second type infobox}}
		Pattern p2 = Pattern.compile("(?i)\\{\\{(.*)infobox(\\s)*\\}\\}");
		
		Matcher m2 = p2.matcher(article.getText());
			
		while( m2.find()){
			article.insertInfoboxType(m2.group(1).toString().replaceAll("\\s+$", ""));
		}

		return article;
	}
	
	public static Article parseArticleInfoboxProperty(Article article){

		//finding of population estimate which is part of article in infobox
		if(article.getInfoboxType().contains("country")){
			
			//Pattern p3 = Pattern.compile("(population_estimate(\\s)*=(\\s)*([0-9]|[,.])*[^<&\\n\\{])");
			Pattern p3 = Pattern.compile("(population_estimate(\\s)*=(\\s)*([0-9]|[,.]|((\\s)?million))*)");
			Matcher m3 = p3.matcher(article.getText());
				
			while( m3.find()){
				//article.insertInfoboxProperty(m3.group(1).toString().replaceAll("\\s+", " "));
				article.setInfoboxType(article.getInfoboxType() + "\n" + article.getTitle() + "\t" +  m3.group(1).toString().replaceAll("\\s+", " "));
			}
		}
		
		
		//finding of genre which is part of article in infobox
		if(article.getInfoboxType().contains("musical artist")){
			
			//Pattern p3 = Pattern.compile("(population_estimate(\\s)*=(\\s)*([0-9]|[,.])*[^<&\\n\\{])");
			//Pattern p4 = Pattern.compile("(\\|\\s*genre\\s*=.*)"); //dont contain more lines
			Pattern p4 = Pattern.compile("(?s)(\\|\\s*genre\\s*=(.+?)((\\n\\|)|(\\n\\}\\})))");			
			Matcher m4 = p4.matcher(article.getText());
		
			String genre = "";
			
			while( m4.find()){
				genre = m4.group(1).toString().replaceAll("\\s+", " ");
			}
			
			//System.out.println(genre);
			
			Pattern p5 = Pattern.compile("(\\[\\[[-\\p{L}0-9\\s\\|]*\\]\\])");
			Matcher m5 = p5.matcher(genre);
			
			while( m5.find()){
				//var genres = m5.group(1).toString().replaceAll("\\s+", " ").replaceAll("\\[|\\]", "");
				article.insertInfoboxProperty(m5.group(1).toString().replaceAll("\\s+", " ").replaceAll("\\[|\\]", ""));
			}
			
			if(!article.getInfoboxProperty().isEmpty()){
				
				//article.setInfoboxProperty("genre = " + article.getInfoboxProperty());
				article.setInfoboxType(article.getInfoboxType() + "\n" + article.getTitle() + "\tgenre = " + article.getInfoboxProperty());
			}
		}
		
		return article;
	}
	
	
}
