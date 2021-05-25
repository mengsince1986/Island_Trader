package terminalPrinter;

/**
 * The ReportPrinter class is used to initialize an object to print formated
 * string report in the command line interface.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class ReportPrinter {
	
	/**
	 * This method takes a string report, formats it and and prints it to the
	 * terminal.
	 * 
	 * @param report a report String to be printed
	 */
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
