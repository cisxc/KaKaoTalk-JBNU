package com.amazonaws.lambda.demo;

import org.json.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Utilsite {
	
	public static JSONObject utilsite(int num)
	{
		JSONObject jsMes = new JSONObject();
		JSONObject jsMes2 = new JSONObject();
	    JSONObject jsAns = new JSONObject();
	    JSONArray jsArr = new JSONArray();
	    JSONObject jsBtn = new JSONObject();
	    
	    if(num==0) {
	    	jsMes2.put("text", "JBNUHUB 다운로드 링크 입니다.");        	
        	jsAns.put("label", "JBNUHUB 다운로드");
        	jsAns.put("url", "https://play.google.com/store/apps/details?id=kr.ktech.jbnuhub");
        	jsMes2.put("message_button", jsAns);
        	jsMes.put("message", jsMes2);
	     	jsMes.put("keyboard",Menu.menu("유용한 사이트"));	    	
	    }else if(num==1) {
	    	jsMes2.put("text", "리트머스 사이트 입니다.");        	
        	jsAns.put("label", "Litmus로 이동");
        	jsAns.put("url", "http://litmus.jbnu.ac.kr/web/");
        	jsMes2.put("message_button", jsAns);
        	jsMes.put("message", jsMes2);
	     	jsMes.put("keyboard",Menu.menu("유용한 사이트"));	    	
	    }else if(num==2) {
	    	jsMes2.put("text", "CTL2 사이트 입니다.");        	
        	jsAns.put("label", "CTL2로 이동");
        	jsAns.put("url", "http://ctl2.jbnu.ac.kr");
        	jsMes2.put("message_button", jsAns);
        	jsMes.put("message", jsMes2);
	     	jsMes.put("keyboard",Menu.menu("유용한 사이트"));	
	     	
	    }else if(num==3) {
	    	jsMes2.put("text", "알고리즘을 연습할 수 있는\n"
	    			+ "백준 사이트 입니다.");        	
        	jsAns.put("label", "Beakjoon로 이동");
        	jsAns.put("url", "https://www.acmicpc.net/");
        	jsMes2.put("message_button", jsAns);
        	jsMes.put("message", jsMes2);
	     	jsMes.put("keyboard",Menu.menu("유용한 사이트"));	
	    }
		return jsMes;
	
	}

}
