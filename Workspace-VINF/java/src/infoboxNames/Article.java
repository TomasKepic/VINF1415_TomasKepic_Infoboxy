package infoboxNames;

public class Article {

	public Article() {
		infoboxType = "";
		infoboxProperty = "";
	}
	private String title;
	
	private StringBuilder text = new StringBuilder();
	
	private String infoboxType;
	
	private String infoboxProperty;
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getText(){
		return text.toString();
	}
	
	/*public void setText(String text){
		this.text.append("");
	}*/
	
	public void appendText(String text){
		this.text.append(text);
	}
	
	public void removeText(){
		//this.text.delete(0, text.length());
		this.text = new StringBuilder();
	}
	
	
	public void insertInfoboxType(String infoboxType){
		
		if(infoboxType.isEmpty())
			infoboxType = "noname";
		
		if(this.infoboxType.equals(""))
		{
			this.infoboxType = "infobox = " + infoboxType;
		}
		else
		{
			this.infoboxType = this.infoboxType + "\n" + this.title + "\t" + "infobox = " + infoboxType;
		}

	}
	
	public void setInfoboxType(String infoboxType){
		this.infoboxType = infoboxType;
	}
	
	public String getInfoboxType(){
		return infoboxType;
	}
	
	public void insertInfoboxProperty(String infoboxProperty){
		if(infoboxProperty.isEmpty())
			infoboxProperty = "noname";
		
		if(this.infoboxProperty.equals(""))
		{
			this.infoboxProperty = infoboxProperty;
		}
		else
		{
			this.infoboxProperty = this.infoboxProperty + "\n" + this.title + "\t" + "genre = " + infoboxProperty;
		}
	}
	
	public void setInfoboxProperty(String infoboxProperty){
		this.infoboxProperty = infoboxProperty;
	}
	
	public String getInfoboxProperty(){
		return infoboxProperty;
	}
	/*
	@Override
	public String toString(){
		 return this.title + ": " + this.infoboxType + "\n";
	}*/
}
