package com.amazonaws.lambda.demo;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Restaurant {

	StringBuilder day = new StringBuilder();

	String html = null;
	String html2 = null;

	public void ParsingType(int Type, Elements e, int week) {

		if (week == 0)
			day.append("일요일은 휴무입니다.");
		else if (week == 1)
			day.append("월\n\n");
		else if (week == 2)
			day.append("화\n\n");
		else if (week == 3)
			day.append("수\n\n");
		else if (week == 4)
			day.append("목\n\n");
		else if (week == 5)
			day.append("금\n\n");
		else if (week == 6)
			day.append("토요일은 휴무입니다.");

		if (Type == 0 && (week != 0 && week != 6)) {
			day.append("중식\n" + "--------\n");
			for (int idx = 0; idx < e.size(); idx++) {
				if (idx < 6) {
					if (idx == week - 1)
						day.append(e.get(idx).text() + "\n");
				} else {
					if (idx == 21) { // Flag : "석식"문구를 단 한번만 출력하게 해주는 것
						day.append("\n\n석식\n" + "--------\n");
					}

					if (week == 5)
						week = 0;

					if (idx % 5 == week)
						day.append(e.get(idx).text() + "\n");

				}
			}

		} else if (Type == 1 && week != 0) {
			if (week == 6) {
				day.setLength(0);
				day.append("토\n\n");
			}
			day.append("중식\n" + "--------\n");
			for (int idx = 0; idx < e.size(); idx++) {
				if (idx == 24) {
					day.append("\n\n석식\n" + "--------\n");
				}
				if (week == 0)
					break;
				if (idx % 6 == week - 1)
					day.append(e.get(idx).text() + "\n");

			}

		} else if (Type == 2 && (week != 0 && week != 6)) {
			day.append("중식\n" + "--------\n");
			for (int idx = 0; idx < e.size(); idx++) {
				if (idx < 6) {
					if (idx == 0) {
						day.append("특식:\n");
					}

					if (idx == week - 1)
						day.append(e.get(idx).text() + "\n");
				} else if (idx < 21) {
					if (idx == 6) {
						day.append("\n찌계/계절면:\n");

					} else if (idx == 11) {
						day.append("\n추억의도시락:\n");
					}

					if (week == 5)
						week = 0;
					if (idx % 5 == week)
						day.append(e.get(idx).text() + "\n");

				} else if (idx == 21) {

					day.append(e.get(idx).text() + "\n");

				} else if (idx > 21) {
					if (idx == 22) { // Flag : "석식"문구를 단 한번만 출력하게 해주는 것
						day.append("\n\n석식\n" + "--------\n");
					} else if (idx == 37)
						break;

					if (week == 0 && week == 6)
						break;
					else if (week == 4)
						week = 0;
					else if (week == 5)
						week = 1;

					if (idx % 5 == week + 1)
						day.append(e.get(idx).text() + "\n");
				}

			}

		} else if (Type == 3 && (week != 0 && week != 6)) {
			day.append("중식\n" + "--------\n");
			for (int idx = 0; idx < e.size(); idx++) {
				if (idx < 6) {
					if (idx == week - 1)
						day.append(e.get(idx).text() + "\n");
				} else {

					if (week == 5)
						week = 0;
					if (idx % 5 == week)
						day.append(e.get(idx).text() + "\n");

				}
			}

		} else if (Type == 4) {

			for (int idx = 0; idx < e.size(); idx++) {
				Elements m = e.get(idx).select("a");
				if (week == 0) {
					day.setLength(0);
					day.append("일\n\n");
				} else if (week == 6) {
					day.setLength(0);
					day.append("토\n\n");
				}
				if (idx == week) {
					day.append(m.get(0).text() + "\n\n" + m.get(1).text() + "\n\n" + m.get(2).text() + "\n");
					break;
				}
			}

		} else if (Type == 5 && (week != 0 && week != 6)) {

			for (int idx = 0; idx < e.size(); idx++) {
				Elements m = e.get(idx).select("a");
				if (idx == week - 1) {
					day.append(m.get(0).text() + "\n\n" + m.get(1).text() + "\n\n" + m.get(2).text() + "\n");
					break;
				}

			}

		}

	}

	public JSONObject restaurant(int num) throws IOException {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 9);
		JSONObject jsMes = new JSONObject();
		JSONObject jsAns = new JSONObject();
		// System.out.println(cal.getTime());
		// System.out.println(day.get(cal.getTime().getDay()));

		WebReader wb = new WebReader();
		if (num == 0 || num == 1 || num == 2 || num == 3 || num == 4)
			html = wb.ReadUrl("http://sobi.chonbuk.ac.kr/chonbuk/m040101");
		else if (num == 5)
			html = wb.ReadUrl("http://likehome.jbnu.ac.kr/board/bbs/board.php?dk_table=cbnu2_7_1_k");
		else if (num == 6)
			html = wb.ReadUrl("http://likehome.jbnu.ac.kr/board/bbs/board.php?dk_table=cbnu2_7_k");
		if(html.equals("err")) {
			jsAns.put("text", "서버가 불안정합니다.\n"
					+ "몇 초후에 다시 시도해주세요.");
			jsMes.put("message", jsAns);
			jsMes.put("keyboard", Menu.menu("학식 정보"));
			return jsMes;		
		}
		Document doc = Jsoup.parse(html);
		Elements rows = doc.select("table tbody");
		if (num == 0) {
			Elements jinsu = rows.get(0).select("[bgcolor=#ffffff]");
			ParsingType(0, jinsu, cal.getTime().getDay());
		} else if (num == 1) {
			Elements medic = rows.get(1).select("[bgcolor=#ffffff]");
			ParsingType(0, medic, cal.getTime().getDay());
		} else if (num == 2) {
			Elements stud = rows.get(2).select("[bgcolor=#ffffff]");
			ParsingType(1, stud, cal.getTime().getDay());
		} else if (num == 3) {
			Elements husang = rows.get(3).select("[bgcolor=#ffffff]");
			ParsingType(2, husang, cal.getTime().getDay());
		} else if (num == 4) {
			Elements jungdam = rows.get(4).select("[bgcolor=#ffffff]");
			ParsingType(3, jungdam, cal.getTime().getDay());
		} else if (num == 5) {
			Elements chambit = doc.select("div [id=box_list2] div");
			ParsingType(4, chambit, cal.getTime().getDay());
		} else if (num == 6) {
			Elements Saebit = doc.select("div [id=box_list2] div");
			ParsingType(5, Saebit, cal.getTime().getDay());
		}

		jsAns.put("text", day.toString());
		jsMes.put("message", jsAns);
		jsMes.put("keyboard", Menu.menu("학식 정보"));

		// System.out.println(jinsu.size());

		return jsMes;

	}

}
