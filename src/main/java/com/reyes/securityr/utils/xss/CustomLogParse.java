package com.reyes.securityr.utils.xss;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/** LOG 黑名單 */
public class CustomLogParse extends ClassicConverter {

	private final static List<String> FORGING_LIST = Arrays.asList("%0a", "%0A", "%0d", "%0D", "\r", "\n");
	
	public static String stripLogForging(String value) {
		if (value == null || (value != null && value.length() == 0)) {
			return "";
		}

		String encode = Normalizer.normalize(value, Normalizer.Form.NFC);
		for (String toReplaceStr : FORGING_LIST) {
			encode = encode.replace(toReplaceStr, "");
		}

		return encode;
	}
	
	@Override
	public String convert(ILoggingEvent event) {
		/**
		全域處理，有需要自訂規則再加上
		if (event.getLoggerName().startsWith("com.x.x")) {
		}
		**/
		return stripLogForging(event.getFormattedMessage());
	}
	
}
