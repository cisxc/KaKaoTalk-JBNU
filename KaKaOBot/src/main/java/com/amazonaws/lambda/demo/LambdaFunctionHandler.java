package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class LambdaFunctionHandler implements RequestHandler<JSONObject, JSONObject> {

    @Override
    public JSONObject handleRequest(JSONObject input, Context context) {
        
    	//JSON 객체 생성
    	JSONObject js = new JSONObject ();
    	JSONArray jsArr = new JSONArray();
    	
    	//버튼에 들어갈 텍스트 넣어주기
    	jsArr.add("공지사항");
    	jsArr.add("교수님 정보");   	
    	jsArr.add("학식 정보");
    	jsArr.add("날씨");
    	jsArr.add("과사무실 정보");
    	jsArr.add("유용한 사이트");
    	jsArr.add("동아리 정보");
    	

    	
    	//home keyboard 설정해주기
    	js.put("type", "buttons");
    	js.put("buttons", jsArr);
    	
    	//완성된 JSON 내보내기
        return js;
    }

}
