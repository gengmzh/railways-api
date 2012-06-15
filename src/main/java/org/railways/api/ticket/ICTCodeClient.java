/**
 * 
 */
package org.railways.api.ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.railways.api.Request;
import org.railways.api.Response;

/**
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class ICTCodeClient {

	private static final Pattern pattern = Pattern
			.compile("<input type=\"hidden\" id=\"\\w{0,4}\" name=\"\\w{0,4}\" value=\"\\w{0,4}\"/>");

	private int connTimeout = 5000;
	private int readTimeout = 10000;

	public ICTCodeClient(int connTimeout, int readTimeout) {
		this.connTimeout = connTimeout;
		this.readTimeout = readTimeout;
	}

	public IctCode request() throws Exception {
		// request
		String url = "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Referer", null);
		Request request = new Request(url).setHeader(headers).setTimeout(connTimeout, readTimeout);
		Response response = request.doGet();
		// parse
		String html = response.getContentAsString();
		response.close();
		Matcher matcher = pattern.matcher(html.toString());
		if (matcher.find()) {
			String group = matcher.group();
			int si = group.indexOf("id=") + 4, ei = group.indexOf("name=") - 2;
			String key = group.substring(si, ei);
			si = group.indexOf("value=") + 7;
			ei = group.lastIndexOf('"');
			String value = group.substring(si, ei);
			return new IctCode(key, value);
		}
		return null;
	}

}
