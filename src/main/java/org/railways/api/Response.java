/**
 * 
 */
package org.railways.api;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 12306响应
 * 
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class Response implements Closeable {

	private Map<String, List<String>> headers;
	private InputStream content;

	public Response(Map<String, List<String>> headers, InputStream content) throws Exception {
		this.headers = headers;
		this.content = content;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public List<String> getHeader(String key) {
		if (headers.containsKey(key)) {
			return headers.get(key);
		}
		if (key != null) {
			String k = key.toLowerCase();
			if (headers.containsKey(k)) {
				return headers.get(k);
			}
			k = key.toUpperCase();
			if (headers.containsKey(k)) {
				return headers.get(k);
			}
		}
		return Collections.emptyList();
	}

	public Map<String, String> getCookies() {
		Map<String, String> result = new HashMap<String, String>();
		List<String> ckl = getHeader("Set-Cookie");
		if (ckl != null && !ckl.isEmpty()) {
			for (String ck : ckl) {
				if (ck == null) {
					continue;
				}
				String[] cookie = ck.split(";")[0].split("=");
				if (cookie.length > 1) {
					result.put(cookie[0], cookie[1]);
				}
			}
		}
		return result;
	}

	public String getCookie() {
		Map<String, String> cookies = getCookies();
		StringBuffer buf = new StringBuffer();
		for (String key : cookies.keySet()) {
			if (buf.length() > 0) {
				buf.append("; ");
			}
			buf.append(key).append("=").append(cookies.get(key));
		}
		return buf.toString();
	}

	// public String getContentEncoding() {
	// List<String> encoding = headers.get("Content-Encoding");
	// return encoding.isEmpty() ? null : encoding.get(0);
	// }

	public InputStream getContent() {
		return content;
	}

	// public InputStream getInputStream() throws Exception {
	// if ("gzip".equalsIgnoreCase(getContentEncoding())) {
	// return new GZIPInputStream(response);
	// }
	// return response;
	// }

	public String getContentAsString() throws Exception {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int b = -1;
		while ((b = content.read()) > -1) {
			buf.write(b);
		}
		buf.close();
		return buf.toString();
	}

	public void close() throws java.io.IOException {
		if (content != null) {
			try {
				content.close();
			} catch (Exception e) {
				// ignore
			} finally {
				content = null;
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

}
