/**
 * 
 */
package org.railways.api.ticket;

import java.util.List;
import java.util.Map;

import org.railways.api.Request;
import org.railways.api.Response;

/**
 * 火车票余票查询
 * 
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class TicketClient {

	private int connTimeout = 5000;
	private int readTimeout = 10000;
	private TicketParser ticketParser;

	public TicketClient(int connTimeout, int readTimeout) {
		this.connTimeout = connTimeout;
		this.readTimeout = readTimeout;
		ticketParser = new TicketParser();
	}

	public List<Ticket> request(TicketForm form, Map<String, String> headers) throws Exception {
		if (form == null) {
			throw new IllegalArgumentException("form is required");
		}
		// request
		String url = "http://dynamic.12306.cn/TrainQuery/iframeLeftTicketByStation.jsp?" + form.toParameters();
		Request request = new Request(url).setTimeout(connTimeout, readTimeout).setHeader(headers);
		Response response = request.doPost(form.toParameters());
		// parse
		String content = response.getContentAsString();
		response.close();
		System.out.println("ticket: " + content);
		return ticketParser.parse(content);
	}

}
