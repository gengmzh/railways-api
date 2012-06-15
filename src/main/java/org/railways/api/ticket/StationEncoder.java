/**
 * 
 */
package org.railways.api.ticket;

/**
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class StationEncoder {

	public static final String encode(String str) {
		return encode(str, "liusheng");
	}

	public static final String encode(String str, String pwd) {
		String prand = "";
		for (int i = 0; i < pwd.length(); i++) {
			prand += (int) pwd.charAt(i);
		}
		int sPos = prand.length() / 5;
		int mult = Integer.valueOf(prand.charAt(sPos) + "" + prand.charAt(sPos * 2) + "" + prand.charAt(sPos * 3) + ""
				+ prand.charAt(sPos * 4) + "" + prand.charAt(sPos * 5));
		long incr = (long) Math.ceil(pwd.length() / 2.00d);
		long modu = (long) (Math.pow(2, 31) - 1);

		long salt = Math.round(Math.random() * 1000000000) % 100000000;
		prand += salt;
		long lprand = (mult * prand.length() + incr) % modu;
		int enc_chr = 0;
		String enc_str = "";

		for (int i = 0; i < str.length(); i++) {
			enc_chr = (int) (((long) str.charAt(i)) ^ (long) Math.floor((((double) lprand / modu) * 255)));

			if (enc_chr < 16) {
				enc_str += "0" + Long.toHexString(enc_chr);
			} else
				enc_str += Long.toHexString(enc_chr);

			lprand = (mult * lprand + incr) % modu;
		}

		String ssalt = Long.toHexString(salt);

		while (ssalt.length() < 8)
			ssalt = "0" + ssalt;
		enc_str += ssalt;

		return enc_str;
	}

}
