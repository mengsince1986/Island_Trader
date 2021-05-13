package terminalPrinter;

public class ReportPrinter {
	
	public static void printReport(String report) {
		String upperDivider = "-------------------Report---------------------";
		String lowerDivider = "----------------------------------------------";
		System.out.println(upperDivider + "\n" +
						   "\n" +
				           report + "\n" +
				           "\n" +
						   lowerDivider);
	}
	
}
