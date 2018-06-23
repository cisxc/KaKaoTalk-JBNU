package com.amazonaws.lambda.demo;

import java.io.IOException;




import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Notice {	 
	public static JSONObject notice(int num) throws IOException
	{
		JSONObject jsMes = new JSONObject();
	    JSONObject jsAns = new JSONObject();
	    String [] title = new String[5];
	    String [] url = new String[5];
	    int i=0;
	    String notice="";
	    
		String html = null;
		WebReader wb = new WebReader();
		if(num ==1) html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_501");
		else if(num==2) html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_502");
		else if(num==3) html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_503");
		else if(num==4) html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_505");
	
		Document doc = Jsoup.parse(html);
		Elements rows = doc.select("div.board_list tbody tr");
		for(Element e : rows) {
			if(e.select(".notice .notice").text().equals("공지"))
			{
				if(i<5) 
				{
					title[i] = e.select(".title").text();
					url[i++] = e.select(".title a[href]").attr("abs:href");
				}					
				else
				{
					i=0;
					break;
				}
			}			
		}
		
		for(i=0; i<5 ; i++)
		{
			notice+= i+1 + ". " + title[i] + "\n"
					+url[i] + "\n\n";
		}

		jsAns.put("text",notice);		
   	    jsMes.put("message",jsAns);
   	    jsMes.put("keyboard",Menu.menu("공지사항"));
   	      	    
		return jsMes;		
	}

}
