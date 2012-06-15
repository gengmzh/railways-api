package org.railways.api.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.railways.api.TrainNumber;

/**
 * @author gmz
 * @time 2012-6-7
 */
public class TicketParser {

	private static final Pattern pattern = Pattern.compile("parent\\.mygrid\\.addRow\\([\\S\\s]+?\\);");

	public TicketParser() {
	}

	public List<Ticket> parse(String content) throws Exception {
		List<Ticket> tl = new ArrayList<Ticket>();
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			Ticket t = parseRow(matcher.group());
			if (t != null) {
				tl.add(t);
			}
		}
		return tl;
	}

	Ticket parseRow(String row) {
		int si = row.indexOf('"'), ei = row.lastIndexOf('"');
		if (si == -1 || si == ei) {
			return null;
		}
		String[] ts = row.substring(si + 1, ei).split(",");
		if (ts.length < 18) {
			return null;
		}
		Ticket t = new Ticket();
		t.setSeq(Integer.parseInt(ts[0]));
		// 车次
		String train = ts[1].substring(0, ts[1].indexOf('^'));
		si = 0;
		ei = train.indexOf('(');
		String tn = train.substring(si, ei);
		si = ei + 1;
		ei = train.indexOf("->");
		String depart = train.substring(si, ei);
		si = ei + 2;
		ei = train.indexOf(')');
		String dest = train.substring(si, ei);
		t.setTrainNumber(new TrainNumber(tn, depart, dest));
		// 发站到站
		t.setDeparture(ts[2].substring(0, ts[2].indexOf('^')));
		t.setDestination(ts[3].substring(0, ts[3].indexOf('^')));
		// 时间
		t.setStartTime(ts[4]).setEndTime(ts[5]).setDuration(ts[6]);
		// 余票
		t.setBusinessSeat(ts[7]).setPrincipalSeat(ts[8]).setFirstSeat(ts[9]).setSecondSeat(ts[10]);
		t.setSeniorSoftSleeper(ts[11]).setSoftSleeper(ts[12]).setHardSleeper(ts[13]);
		t.setSoftSeat(ts[14]).setHardSeat(ts[15]).setNoSeat(ts[16]);
		// 等级
		t.setType(ts[17]);
		return t;
	}

}
