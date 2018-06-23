package com.amazonaws.lambda.demo;


import java.io.IOException;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Professor {
	
	public static JSONObject professor(String name)
	{
		
		JSONObject jsMes = new JSONObject();
	    JSONObject jsAns = new JSONObject();
	    String [] title = new String[16];
	    String [] stitle = new String[16];
	    if(name.equals("교수님")) name="가나다 ";
	    if(!name.contains(" ")) name+=" ";
	    name = name.substring(0,name.lastIndexOf(" "));
	    String professor=name+"\n\n";
	    int i=0;
	    boolean flag=false;
	        
	      String html = "";
	      WebReader wb = new WebReader();
	      html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_201");
	         
	      Document doc = Jsoup.parse(html);
	      Elements rows = doc.select("div.prof");
	      Elements raws;
	      
	      for(Element e : rows) {         
	         if(e.select("div.pleft").text().equals(name)) {
	            raws = e.select("div.pcontent ul li");            
	            for(Element k : raws) {            
	               title[i]=k.select("h5").text();
	               stitle[i++]=k.select("p").text();
	            }
	            flag =true;
	            break;
	         }
	         else flag =false;  	 
	         
	      }
	      
	      if(flag)
	      {
	    	  for(int idx=0 ;idx<7; idx++)
		      {
		    	  professor+=title[idx] + " : " + stitle[idx]+"\n\n";
		      }
		         
		      jsAns.put("text",professor);
		   	  jsMes.put("message",jsAns);
		   	  jsMes.put("keyboard",Menu.menu("메인메뉴"));
	    	  
	      }
	      else 
	      {
	    	  jsAns.put("text","ㅁㅁㅁ + 교수님 형식으로 입력해주세요.\n" +
	    			  	"ex) 양재동 교수님");
	    	  jsMes.put("message",jsAns);
		   	  //jsMes.put("keyboard",Menu.menu("메인메뉴"));
	    	  
	      }
	      
	      
		return jsMes;         
	      
	}
	

}
