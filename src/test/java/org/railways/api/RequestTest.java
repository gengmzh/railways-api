package org.railways.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.railways.api.Request;
import org.railways.api.Response;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class RequestTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test_request() throws Exception {
		String url = "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		// headers.put("Referer", null);
		Request request = new Request(url).setHeader(headers).setTimeout(5000, 10000);
		Response response = request.doGet();
		// byte[] data = new byte[response.getResponse().available()];
		// response.getResponse().read(data);
		// System.out.println(new String(data));

		Assert.assertNotNull(response);
		System.out.println(response.getHeaders());

		InputStream input = response.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public void test_naive_request() throws Exception {
		String url = "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp";
		URLConnection conn = new URL(url).openConnection();
		// header
		conn.addRequestProperty("Accept", "text/html, application/xhtml+xml, */*");
		conn.addRequestProperty("Referer", "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp");
		conn.addRequestProperty("Accept-Language", "zh-CN");
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
		conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// conn.addRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.addRequestProperty("Host", "dynamic.12306.cn");
		conn.addRequestProperty("Connection", "Keep-Alive");
		conn.addRequestProperty("Cache-Control", "no-cache");
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(10000);
		// connect
		conn.connect();
		// response
		InputStream input = conn.getInputStream();
		// System.out.println(input.available());
		// byte[] data = new byte[input.available()];
		// input.read(data);
		// input = new GZIPInputStream(new ByteArrayInputStream(data));
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

}
