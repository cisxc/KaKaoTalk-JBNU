package com.amazonaws.lambda.demo;

import org.json.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Menu {
	public static JSONObject menu(String menu)
	{
		JSONObject jsMes = new JSONObject();
	    JSONObject jsAns = new JSONObject();
	    JSONArray jsArr = new JSONArray();
	    JSONObject jsBtn = new JSONObject();
	    if(menu.equals("메인메뉴"))
	    {
		    jsArr.add("공지사항");
		    jsArr.add("교수님 정보");    
		    jsArr.add("학식 정보");
		    jsArr.add("날씨");
		    jsArr.add("과사무실 정보");
		    jsArr.add("유용한 사이트");
		    jsArr.add("동아리 정보");
	    	jsBtn.put("type", "buttons");
	    	jsBtn.put("buttons", jsArr);
	    }
	    else if(menu.equals("공지사항"))
	    {
	    	jsArr.add("소식통");
        	jsArr.add("학사공지");
        	jsArr.add("일반공지 & 세미나");
        	jsArr.add("취업공지");
        	jsArr.add("메인메뉴");
        	jsBtn.put("type", "buttons");
        	jsBtn.put("buttons", jsArr);      	
	    }
	    else if(menu.equals("학식 정보"))
	    {
	    	jsArr.add("후생관");
	    	jsArr.add("진수원");
        	jsArr.add("학생회관");	
        	jsArr.add("정담원");
        	jsArr.add("의대");
        	jsArr.add("참빛관");
        	jsArr.add("기존관");
        	jsArr.add("메인메뉴");
        	jsBtn.put("type", "buttons");
        	jsBtn.put("buttons", jsArr);    
	    }
	    else if(menu.equals("유용한 사이트"))
	    {
	    	jsArr.add("JBNUHUB 다운로드");
	    	jsArr.add("Litmus Site");
	    	jsArr.add("CTL2 Site");
	    	jsArr.add("Beakjoon Site");
	    	jsArr.add("메인메뉴");
        	jsBtn.put("type", "buttons");
        	jsBtn.put("buttons", jsArr);  
	    }
	    else if(menu.equals("동아리 정보"))
	    {
	    	jsArr.add("ALPS");
	    	jsArr.add("HIT-IT");
	    	jsArr.add("Invisible Shield");
	    	jsArr.add("WHO");
	    	jsArr.add("메인메뉴");
        	jsBtn.put("type", "buttons");
        	jsBtn.put("buttons", jsArr);  
	    }
    	
		return jsBtn;
	}

}
