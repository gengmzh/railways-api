/**
 * 
 */
package org.railways.api.ticket;

import org.railways.api.ticket.ICTCodeClient;
import org.railways.api.ticket.IctCode;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @since 2012-6-5
 * @author gmz
 * 
 */
public class ICTCodeClientTest extends TestCase {

	ICTCodeClient ictClient;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.ictClient = new ICTCodeClient(5000, 10000);
	}

	public void test_request() throws Exception {
		IctCode ict = ictClient.request();

		Assert.assertNotNull(ict);
		System.out.println(ict);
	}

}
