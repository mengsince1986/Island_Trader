package terminalPrinter;

public class ReportPrinter {
	
	public static void printReport(String report) {
		String divider = "---------------------------------------------";
		System.out.println(divider + "\n" +
				           report + "\n" +
						   divider);
	}
	
}
