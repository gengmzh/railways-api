package org.railways.api.train;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import junit.framework.TestCase;

import org.railways.api.IdentifyingClient;
import org.railways.api.Response;
import org.railways.api.StationType;
import org.railways.api.TrainType;
import org.railways.api.train.Train;
import org.railways.api.train.TrainClient;
import org.railways.api.train.TrainForm;

public class TrainClientBaseTest extends TestCase {

	IdentifyingClient identifyingClient;
	TrainClient trainClient;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		identifyingClient = new IdentifyingClient(5000, 10000);
		trainClient = new TrainClient(5000, 10000);
	}

	public void test_request() throws Exception {
		TrainForm form = new TrainForm();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		form.setDate(cal.getTime()).setDeparture("北京").setDestination("合肥").setStationType(StationType.ALL)
				.setTrainType(TrainType.ALL);
		// random code
		Response image = identifyingClient.request();
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
		List<Train> trains = trainClient.request(form, headers);

		System.out.println();
		for (Train t : trains) {
			System.out.println(t);
		}

	}

}
