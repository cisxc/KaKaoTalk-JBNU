package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Club {

	public static JSONObject club(int num) throws IOException {
		String result = "";
		int i = 0;
		JSONObject jsMes = new JSONObject();
		JSONObject jsAns = new JSONObject();
		String html = "";
		WebReader wb = new WebReader();
		html = wb.ReadUrl("https://cse.chonbuk.ac.kr/content_104");
		Document doc = Jsoup.parse(html);
		Element Club = doc.select("div.club").get(num);
		Elements Club2 = Club.select("ul li");

		if (num == 0)
			result += "ALPS" + "\n------------------\n";
		else if (num == 1)
			result += "HIT-IT" + "\n------------------\n";
		else if (num == 2)
			result += "I.S" + "\n------------------\n";
		else if (num == 3)
			result += "WHO" + "\n------------------\n";

		for (int j = 1; j <= 6; j++) {
			result += Club2.get(j).text() + "\n\n";
		}
		System.out.println(result);

		jsAns.put("text", result);
		jsMes.put("message", jsAns);
		jsMes.put("keyboard", Menu.menu("동아리 정보"));

		return jsMes;

	}

}
