package org.railways.api.train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.railways.api.TrainNumber;

/**
 * @author gmz
 * @time 2012-6-7
 */
public class TrainParser {

	private static final Pattern pattern = Pattern.compile("parent\\.mygrid\\.addRow\\([\\S\\s]+?\\);");

	public TrainParser() {
	}

	public List<Train> parse(String content) throws Exception {
		List<Train> tl = new ArrayList<Train>();
		Map<TrainNumber, Train> map = new HashMap<TrainNumber, Train>();
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			Train t = parseRow(matcher.group());
			if (t != null) {
				if (map.containsKey(t.getTrainNumber())) {
					Train p = map.get(t.getTrainNumber());
					if (!p.getDeparture().equals(t.getDeparture())) {
						p.setDeparture(p.getDeparture() + "/" + t.getDeparture());
					}
					if (!p.getDestination().equals(t.getDestination())) {
						p.setDestination(p.getDestination() + "/" + t.getDestination());
					}
				} else {
					tl.add(t);
					map.put(t.getTrainNumber(), t);
				}
			}
		}
		map.clear();
		for (int i = 0; i < tl.size(); i++) {
			tl.get(i).setSeq(i + 1);
		}
		return tl;
	}

	Train parseRow(String row) {
		int si = row.indexOf('"'), ei = row.lastIndexOf('"');
		if (si == -1 || si == ei) {
			return null;
		}
		String[] ts = row.substring(si + 1, ei).split(",");
		if (ts.length < 20) {
			return null;
		}
		Train t = new Train();
		t.setSeq(Integer.parseInt(ts[0]));
		// 车次
		String tn = ts[1].substring(0, ts[1].indexOf('^'));
		String depart = ts[7].substring(0, ts[7].indexOf('^'));
		String dest = ts[8].substring(0, ts[8].indexOf('^'));
		t.setTrainNumber(new TrainNumber(tn, depart, dest));
		// 发站到站
		t.setDeparture(ts[2].substring(0, ts[2].indexOf('^')).trim());
		t.setDestination(ts[3].substring(0, ts[3].indexOf('^')).trim());
		// 时间
		t.setStartTime(ts[4]).setEndTime(ts[5]).setDuration(ts[6]);
		// 等级
		t.setType(ts[9]).setAirConditioner(ts[10]);
		// 价格
		t.setBusinessSeat(ts[11]).setPrincipalSeat(ts[12]).setFirstSeat(ts[13]).setSecondSeat(ts[14]);
		t.setSeniorSoftSleeper(ts[15]).setSoftSleeper(ts[16]).setHardSleeper(ts[17]);
		t.setSoftSeat(ts[18]).setHardSeat(ts[19]);
		return t;
	}

}
