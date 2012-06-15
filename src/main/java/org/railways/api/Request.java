/**
 * 
 */
package org.railways.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 12306请求
 * 
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class Request implements Closeable {

	private HttpURLConnection conn;

	public Request(String url) throws Exception {
		conn = (HttpURLConnection) new URL(url).openConnection();
		// header
		conn.addRequestProperty("Accept", "text/html, application/xhtml+xml, */*");
		conn.addRequestProperty("Referer", "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp");
		conn.addRequestProperty("Accept-Language", "zh-CN");
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
		conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// TODO 中文gzip内容GZIPInputStream无法解压问题待研究
		// conn.addRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.addRequestProperty("Host", "dynamic.12306.cn");
		conn.addRequestProperty("Connection", "Keep-Alive");
		conn.addRequestProperty("Cache-Control", "no-cache");
	}

	// public My12306Request setMethod(String method) throws Exception {
	// conn.setRequestMethod(method != null ? method : "GET");
	// return this;
	// }

	public Request setHeader(Map<String, String> headers) throws Exception {
		if (headers != null && !headers.isEmpty()) {
			for (String key : headers.keySet()) {
				conn.setRequestProperty(key, headers.get(key));
			}
		}
		return this;
	}

	public Request setTimeout(int connTimeout, int readTimeout) throws Exception {
		conn.setConnectTimeout(connTimeout);
		conn.setReadTimeout(readTimeout);
		return this;
	}

	public Response doGet() throws Exception {
		conn.setRequestMethod("GET");
		conn.connect();
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		InputStream ins = conn.getInputStream();
		int b = -1;
		while ((b = ins.read()) > -1) {
			buf.write(b);
		}
		ins.close();
		buf.close();
		conn.disconnect();
		Response response = new Response(conn.getHeaderFields(), new ByteArrayInputStream(buf.toByteArray()));
		return response;
	}

	public Response doPost(String parameters) throws Exception {
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.getOutputStream().write(parameters.getBytes());
		conn.connect();
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		InputStream ins = conn.getInputStream();
		int b = -1;
		while ((b = ins.read()) > -1) {
			buf.write(b);
		}
		ins.close();
		buf.close();
		conn.disconnect();
		Response response = new Response(conn.getHeaderFields(), new ByteArrayInputStream(buf.toByteArray()));
		return response;
	}

	// public My12306Response request(String url, String method, Map<String,
	// String> headers, int connTimeout,
	// int readTimeout) throws Exception {
	// if (url == null || url.isEmpty()) {
	// throw new IllegalArgumentException("url is required");
	// }
	// if (connTimeout < 0) {
	// throw new IllegalArgumentException("connTimeout is illegal");
	// }
	// if (readTimeout < 0) {
	// throw new IllegalArgumentException("readTimeout is illegal");
	// }
	// HttpURLConnection conn = (HttpURLConnection) new
	// URL(url).openConnection();
	// conn.setRequestMethod(method != null ? method : "GET");
	// // header
	// conn.addRequestProperty("Accept",
	// "text/html, application/xhtml+xml, */*");
	// conn.addRequestProperty("Referer",
	// "http://dynamic.12306.cn/TrainQuery/leftTicketByStation.jsp");
	// conn.addRequestProperty("Accept-Language", "zh-CN");
	// conn.addRequestProperty("User-Agent",
	// "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
	// conn.addRequestProperty("Content-Type",
	// "application/x-www-form-urlencoded");
	// // conn.addRequestProperty("Accept-Encoding", "gzip, deflate");
	// conn.addRequestProperty("Host", "dynamic.12306.cn");
	// conn.addRequestProperty("Connection", "Keep-Alive");
	// conn.addRequestProperty("Cache-Control", "no-cache");
	// if (headers != null && !headers.isEmpty()) {
	// for (String key : headers.keySet()) {
	// conn.setRequestProperty(key, headers.get(key));
	// }
	// }
	// conn.setConnectTimeout(connTimeout);
	// conn.setReadTimeout(readTimeout);
	// // connect
	// conn.connect();
	// // response
	// My12306Response response = new My12306Response(conn.getHeaderFields(),
	// conn.getInputStream());
	// return response;
	// }

	public void close() throws java.io.IOException {
		conn.disconnect();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

}
