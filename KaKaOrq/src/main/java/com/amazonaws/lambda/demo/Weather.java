package com.amazonaws.lambda.demo;



import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.json.simple.JSONObject;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Weather {
	public static JSONObject weather() throws SAXException, IOException, ParserConfigurationException
	{
		String result="---------------------\n";
		
		JSONObject jsMes = new JSONObject();
	    JSONObject jsAns = new JSONObject();
	    
		String urlStr = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4511358000"; 
		String buffer ="";
		WebReader wr = new WebReader();
		buffer = wr.ReadUrl(urlStr);
		//System.out.println(buffer);

		InputSource is = new InputSource(new StringReader(buffer));
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        
     // xpath 생성
        XPath xpath = XPathFactory.newInstance().newXPath();
         
  
        // NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
        NodeList hours = null;
        NodeList temps = null;
        NodeList wfs = null;
        NodeList pop = null;
        
		try {
			
			hours = (NodeList)xpath.evaluate("//body//data/hour", document, XPathConstants.NODESET);
			temps = (NodeList)xpath.evaluate("//body//data/temp", document, XPathConstants.NODESET);
			wfs = (NodeList)xpath.evaluate("//body//data/wfKor", document, XPathConstants.NODESET);
			pop = (NodeList)xpath.evaluate("//body//data/pop", document, XPathConstants.NODESET);
			 for( int idx=0; idx<5; idx++ ) 
			 {
				 result+=  hours.item(idx).getTextContent() + "시\n" +
						 "온도 : " + temps.item(idx).getTextContent()+ "도\n" +
						 "날씨 : " + wfs.item(idx).getTextContent() + "\n" +
						 "강수확률 : " + pop.item(idx).getTextContent() +"%\n---------------------\n";	 
			 }
				
            
		} 
		catch (XPathExpressionException e) {
		
			e.printStackTrace();
		}
		
		jsAns.put("text",result);
   	    jsMes.put("message",jsAns);
   	    jsMes.put("keyboard",Menu.menu("메인메뉴"));
   	    
		return jsMes;
		
	}

}
