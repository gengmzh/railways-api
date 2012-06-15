/**
 * 
 */
package org.railways.api.ticket;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.railways.api.IdentifyingClient;
import org.railways.api.Response;
import org.railways.api.StationType;
import org.railways.api.TrainType;
import org.railways.api.ticket.ICTCodeClient;
import org.railways.api.ticket.IctCode;
import org.railways.api.ticket.Ticket;
import org.railways.api.ticket.TicketClient;
import org.railways.api.ticket.TicketForm;

/**
 * @since 2012-6-6
 * @author gmz
 * 
 */
public class TicketClientBaseTest extends TestCase {

	ICTCodeClient ictCodeClient;
	IdentifyingClient imageClient;
	TicketClient ticketClient;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ictCodeClient = new ICTCodeClient(5000, 10000);
		imageClient = new IdentifyingClient(5000, 10000);
		ticketClient = new TicketClient(5000, 10000);
	}

	public void test_query() throws Exception {
		TicketForm form = new TicketForm();
		// ict
		IctCode ic = ictCodeClient.request();
		Assert.assertNotNull(ic);
		form.setIctCode(ic);
		// others
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		form.setDate(cal.getTime()).setDeparture("北京").setDestination("合肥")
				.setStationType(StationType.ALL).setTrainType(TrainType.ALL)
				.setSearchType(TicketForm.SearchType.DEFAULT);
		// random code
		Response image = imageClient.request();
		String img = "d:\\image.jpg";
		FileOutputStream out = new FileOutputStream(img);
		int b = -1;
		while ((b = image.getContent().read()) > -1) {
			out.write(b);
		}
		image.getContent().close();
		out.flush();
		out.close();
		System.out.println("random image saved as " + img);

		System.out.println("please input the random code: ");
		Scanner scanner = new Scanner(System.in);
		String line = null;
		while ((line = scanner.nextLine()) == null || line.isEmpty()) {
		}
		form.setRandomCode(line);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", image.getCookie());
		System.out.println("cookie=>" + headers);
		System.out.println("parameters=>" + form.toParameters());
		List<Ticket> tickets = ticketClient.request(form, headers);

		System.out.println();
		for (Ticket t : tickets) {
			System.out.println(t);
		}
	}

}
