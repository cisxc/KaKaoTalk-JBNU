package com.amazonaws.lambda.demo;



import com.amazonaws.services.lambda.runtime.Context;

import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;

import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.xml.sax.SAXException;

public class message implements RequestHandler<JSONObject, JSONObject> {

    @Override
    public JSONObject handleRequest(JSONObject input, Context context) {
    	JSONObject js = new JSONObject();
        String answer = input.get("content").toString();
        Restaurant restaurant =new Restaurant();
        //내보내야하는 JSON이 2단계여서 객체도 2개가 필요합니다.
        JSONObject jsMes = new JSONObject();
        JSONObject jsMes2 = new JSONObject();
        JSONObject jsAns = new JSONObject();
        JSONObject jsBtn = new JSONObject();
        JSONArray jsArr = new JSONArray();
        //응답 부분
        try 
        {
	        if(answer.contains("공지사항"))
	        {
	        	jsAns.put("text", "원하시는 공지를 눌러주세요.\n"
	        			+ "최근 5개의 글을 가져옵니다.");
	        	jsMes.put("message",jsAns);
	        	jsMes.put("keyboard",Menu.menu("공지사항"));
	        }
	        else if(answer.contains("메인메뉴"))
	        {
	        	jsAns.put("text","메인메뉴로 돌아갑니다.");
		     	jsMes.put("message",jsAns);
		     	jsMes.put("keyboard",Menu.menu("메인메뉴"));
	        }
	        else if(answer.contains("학식 정보"))
	        {
	        	jsAns.put("text","원하시는 학식정보를 클릭해주세요.");
		     	jsMes.put("message",jsAns);
		     	jsMes.put("keyboard",Menu.menu("학식 정보"));
	        }
	        else if(answer.contains("소식통"))
	        {
					jsMes=Notice.notice(1);
	        }
	        else if(answer.contains("학사공지"))
	        {
					jsMes=Notice.notice(2);	     
	        }
	        else if(answer.contains("일반공지 & 세미나"))
	        {
					jsMes=Notice.notice(3);
	        }
	        else if(answer.contains("취업공지"))
	        {
	        	jsMes=Notice.notice(4);	 
	        }
	        else if(answer.contains("날씨"))
	        {	     
	        	jsMes=Weather.weather();
	        }
	        else if(answer.contains("교수님"))
	        {
	        	jsMes=Professor.professor(answer);
	        }
	        else if(answer.contains("과사무실 정보"))
	        {
	        	jsAns.put("text","주소 \n54896 전북 전주시 덕진구 백제대로 567 전북대학교 공대7호관 224호 컴퓨터공학부 사무실 \n\n" +
	        					 "연락처 \nTel. 063-270-4233, 3431\n" + 
	        					 "Fax. 063-270-2394");
		     	jsMes.put("message",jsAns);
		     	jsMes.put("keyboard",Menu.menu("메인메뉴"));
		     	
	        }
	        else if(answer.contains("진수원"))
	        {
	        	jsMes=restaurant.restaurant(0);
	        }
	        else if(answer.contains("의대"))
	        {
	        	jsMes=restaurant.restaurant(1);
	        }
	        else if(answer.contains("학생회관"))
	        {
	        	jsMes=restaurant.restaurant(2);
	        }
	        else if(answer.contains("후생관"))
	        {
	        	jsMes=restaurant.restaurant(3);
	        }
	        else if(answer.contains("정담원"))
	        {
	        	jsMes=restaurant.restaurant(4);
	        }
	        else if(answer.contains("참빛관"))
	        {
	        	jsMes=restaurant.restaurant(5);
	        }
	        else if(answer.contains("기존관"))
	        {
	        	jsMes=restaurant.restaurant(6);
	        }
	        else if(answer.contains("유용한 사이트"))
	        {
	        	jsAns.put("text","유용한 사이트 모음입니다.");
		     	jsMes.put("message",jsAns);
		     	jsMes.put("keyboard",Menu.menu("유용한 사이트"));
	        }
	        else if(answer.contains("JBNUHUB 다운로드"))
	        {
	        	jsMes=Utilsite.utilsite(0);
	        }
	        else if(answer.contains("Litmus Site"))
	        {
	        	jsMes=Utilsite.utilsite(1);
	        }
	        else if(answer.contains("CTL2 Site"))
	        {
	        	jsMes=Utilsite.utilsite(2);
	        }
	        else if(answer.contains("Beakjoon Site"))
	        {
	        	jsMes=Utilsite.utilsite(3);
	        }
	        else if(answer.contains("동아리 정보"))
	        {
	        	jsAns.put("text","컴퓨터공학과의 동아리들 소개합니다");
		     	jsMes.put("message",jsAns);
		     	jsMes.put("keyboard",Menu.menu("동아리 정보"));
	        } else if(answer.contains("ALPS"))
	        {
		     	jsMes = Club.club(0);
	        } else if(answer.contains("HIT-IT"))
	        {
	        	jsMes = Club.club(1);
	        } else if(answer.contains("Invisible Shield"))
	        {
	        	jsMes = Club.club(2);
	        } else if(answer.contains("WHO"))
	        {
	        	jsMes = Club.club(3);
	        }
	        else
	        {
	     	   jsAns.put("text","잘못된입력입니다.");
	     	   jsMes.put("message",jsAns);
	     	   jsMes.put("keyboard",Menu.menu("메인메뉴"));
	        }
        }
        catch (SAXException | ParserConfigurationException | IOException e ) {
			e.printStackTrace();
		}
        
        return jsMes;
    }

}
