package ru.rienel.shs.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Formatters {

	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

	public static String formatDateTime(Date date) {
		return dateTimeFormat.format(date);
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	public static String formatDouble(Double number) {
		return String.format(Locale.getDefault(), "%.2f", number);
	}

	public static String formatResourceMeterValue(Double number) {
		return String.format(Locale.getDefault(), "%.3f", number);
	}

	public static String formatDouble(Double number, int scale) {
		return String.format(Locale.getDefault(), "%." + scale + "f", number);
	}
}
