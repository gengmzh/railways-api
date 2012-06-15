/**
 * 
 */
package org.railways.api.train;

import java.util.List;
import java.util.Map;

import org.railways.api.Request;
import org.railways.api.Response;

/**
 * 列车时刻表查询
 * 
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class TimetableClient {

	private int connTimeout = 5000;
	private int readTimeout = 10000;
	private TimetableParser parser;

	public TimetableClient(int connTimeout, int readTimeout) {
		this.connTimeout = connTimeout;
		this.readTimeout = readTimeout;
		parser = new TimetableParser();
	}

	public List<Train> request(TimetableForm form, Map<String, String> headers) throws Exception {
		if (form == null) {
			throw new IllegalArgumentException("form is required");
		}
		// request
		String url = "http://dynamic.12306.cn/TrainQuery/iframeTrainPassStationByTrainCode.jsp";
		Request request = new Request(url).setTimeout(connTimeout, readTimeout).setHeader(headers);
		Response response = request.doPost(form.toParameters());
		// parse
		String content = response.getContentAsString();
		response.close();
		System.out.println("timetable: " + content);
		return parser.parse(content);
	}

}
