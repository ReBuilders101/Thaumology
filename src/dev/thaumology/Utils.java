package dev.thaumology;

public final class Utils {
	
	private Utils() {}
	
	public static double DEGREE_90 = Math.PI / 2;
	public static double DEGREE_180 = Math.PI;
	public static double DEGREE_270 = (Math.PI * 3) / 2;
	public static double DEGREE_360 = Math.PI * 2;
	
	
	public static String left(String string, int length) {
		return string.substring(0, length);
	}
	
	public static String mid(String string, int beginIndex, int length) {
		return string.substring(beginIndex, beginIndex + length);
	}

	public static String right(String string, int length) {
	return string.substring(string.length() - length);
	}
}
