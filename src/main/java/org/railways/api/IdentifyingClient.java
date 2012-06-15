/**
 * 
 */
package org.railways.api;

import java.util.HashMap;
import java.util.Map;


/**
 * 验证码图片获取
 * 
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class IdentifyingClient {

	private int connTimeout = 5000;
	private int readTimeout = 10000;

	public IdentifyingClient(int connTimeout, int readTimeout) {
		this.connTimeout = connTimeout;
		this.readTimeout = readTimeout;
	}

	public Response request() throws Exception {
		String url = "http://dynamic.12306.cn/TrainQuery/passCodeAction.do?rand=rrand?" + Math.random();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5");
		headers.put("Referer", "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp");
		Request request = new Request(url).setHeader(headers).setTimeout(connTimeout, readTimeout);
		return request.doGet();
	}

}
