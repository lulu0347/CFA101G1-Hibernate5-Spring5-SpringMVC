package Utils;

public class PrintUtils {

	private static String printLevel = "debug";

	public static void printWhenDebug(String className, String action, String paramName, String values) {
		if ("debug".equals(printLevel)) {
			printInfos(className, action, paramName, values);
		}
	}

	public static void alwaysPrint(String className, String action, String paramName, String values) {
		printInfos(className, action, paramName, values);
	}

	private static void printInfos(String className, String action, String paramName, String values) {
		String template = "class: %s, action: %s,paramName: %s, values: %s\n";

		System.out.printf(template, className, action, paramName, values);
	}

}
