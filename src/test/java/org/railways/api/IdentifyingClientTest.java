/**
 * 
 */
package org.railways.api;

import java.io.FileOutputStream;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.railways.api.IdentifyingClient;
import org.railways.api.Response;

/**
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class IdentifyingClientTest extends TestCase {

	IdentifyingClient identifyingClient;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		identifyingClient = new IdentifyingClient(5000, 10000);
	}

	public void test_request() throws Exception {
		Response resp = identifyingClient.request();

		Assert.assertNotNull(resp);
		System.out.println(resp.getHeaders());
		// System.out.println(resp.getResponse());
		Map<String, String> cookies = resp.getCookies();
		System.out.println(cookies);
		System.out.println(resp.getCookie());
		// image
		String img = "d:\\image.jpg";
		FileOutputStream out = new FileOutputStream(img);
		int b = -1;
		while ((b = resp.getContent().read()) > -1) {
			out.write(b);
		}
		resp.getContent().close();
		out.flush();
		out.close();
		System.out.println("random image saved as " + img);
	}

}
