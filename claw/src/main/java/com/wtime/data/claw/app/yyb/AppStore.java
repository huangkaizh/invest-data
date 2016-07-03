package com.wtime.data.claw.app.yyb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.kevinsawicki.http.HttpRequest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.wtime.data.common.utils.Const;
import com.wtime.data.common.utils.StringUtils;

public class AppStore {
	public static final String HOME_URL = "http://sj.qq.com/myapp/category.htm?orgame=2";
	public static final String APP_LIST_URL = "http://sj.qq.com/myapp/cate/appList.htm?orgame=1&categoryId=%s&pageSize=20&pageContext=%d";

	public static List<String> category() throws IOException {
		Document doc = Jsoup.connect(HOME_URL).timeout(Const.TIMEOUT).get();
		Elements nodes = doc.select("li[id^=cate]");
		List<String> list = new ArrayList<String>(nodes.size());
		for (Element node : nodes) {
			String id = node.id();
			if (id.contains("--"))
				continue;
			list.add(StringUtils.remove(id, "cate-"));
		}
		return list;
	}

	/**
	 * 应用列表
	 * 
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public static List<DBObject> list(String cateId, int page) throws IOException {
		String url = String.format(APP_LIST_URL, cateId, page);
		HttpRequest request = HttpRequest.get(url);
		BasicDBObject bo = BasicDBObject.parse(request.body());
		List<DBObject> list = (List<DBObject>) bo.get("obj");
		for (DBObject app : list) {
			app.put("_id", app.get("appId"));
			app.removeField("appId");
			System.out.println(app);
		}
		return list;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Mongo mongo = new Mongo("10.0.0.4");
		DBCollection dc = mongo.getDB("invest_bak").getCollection("app_yyb");
		List<String> listC = category();
		for (int i = listC.size() - 1 ; i >= 0 ; i--) {
			String s = listC.get(i);
			int page = 1;
			while (true) {
				System.out.println(s + "-" + page);
				List<DBObject> list = list(s, page);
				for(DBObject bo : list)
				try {
					dc.insert(bo);
				} catch (Exception e) {

				}
				if (list.size() == 0)
					break;
				page += list.size();
			}
			Thread.sleep(1000);
		}
	}
}
