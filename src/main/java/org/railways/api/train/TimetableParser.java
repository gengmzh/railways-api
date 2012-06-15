package org.railways.api.train;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.railways.api.TrainNumber;

/**
 * @author gmz
 * @time 2012-6-7
 */
public class TimetableParser {

	private static final Pattern pattern = Pattern.compile("parent\\.mygrid\\.addRow\\([\\S\\s]+?\\);");

	public TimetableParser() {
	}

	public List<Train> parse(String content) throws Exception {
		List<Train> tl = new ArrayList<Train>();
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			Train t = parseRow(matcher.group());
			if (t != null) {
				tl.add(t);
			}
		}
		return tl;
	}

	Train parseRow(String row) {
		int si = row.indexOf('"'), ei = row.lastIndexOf('"');
		if (si == -1 || si == ei) {
			return null;
		}
		String[] ts = row.substring(si + 1, ei).split(",");
		if (ts.length < 15) {
			return null;
		}
		Train t = new Train();
		t.setSeq(Integer.parseInt(ts[0]));
		// 车站
		t.setDeparture(ts[1].substring(0, ts[1].indexOf('^')));
		// 车次
		t.setTrainNumber(new TrainNumber(ts[2].trim(), "", ""));
		// 时间
		t.setStartTime(ts[3]).setEndTime(ts[4]).setDuration(ts[5]);
		// 价格
		t.setBusinessSeat(ts[6]).setPrincipalSeat(ts[7]).setFirstSeat(ts[8]).setSecondSeat(ts[9]);
		t.setSeniorSoftSleeper(ts[10]).setSoftSleeper(ts[11]).setHardSleeper(ts[12]);
		t.setSoftSeat(ts[13]).setHardSeat(ts[14]);
		return t;
	}

}
