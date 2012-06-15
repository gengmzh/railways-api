/**
 * 
 */
package org.railways.api.train;

import java.util.List;
import java.util.Map;

import org.railways.api.Request;
import org.railways.api.Response;

/**
 * 列车查询
 * 
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class TrainClient {

	private int connTimeout = 5000;
	private int readTimeout = 10000;
	private TrainParser parser;

	public TrainClient(int connTimeout, int readTimeout) {
		this.connTimeout = connTimeout;
		this.readTimeout = readTimeout;
		parser = new TrainParser();
	}

	public List<Train> request(TrainForm form, Map<String, String> headers) throws Exception {
		if (form == null) {
			throw new IllegalArgumentException("form is required");
		}
		// request
		String url = "http://dynamic.12306.cn/TrainQuery/iframeTrainInfoByStation.jsp";
		Request request = new Request(url).setTimeout(connTimeout, readTimeout).setHeader(headers);
		Response response = request.doPost(form.toParameters());
		// parse
		String content = response.getContentAsString();
		response.close();
		System.out.println("train: " + content);
		return parser.parse(content);
	}

}
