package ui;

import model.CompoundInterestRecord;
import model.CompoundInterestReport;
import model.Investment;

public class ConsoleCompoundInterestReporter implements CompoundInterestReporter {

    private static final String LINE =
            "=================================================================";
    private static final String ROW_SEP =
            "+------+-------------------+-------------------+-------------------+";

    /**
     * Displays a complete compound interest report to the console.
     * This method coordinates the output of the report by sequentially displaying
     * the header, investment parameters, detailed annual capitalization table,
     * and final summary statistics.
     *
     * @param report the compound interest report containing investment data,
     *               annual records, and calculated totals to be displayed
     */
    @Override
    public void displayReport(CompoundInterestReport report) {
        displayHeader(report.getInvestment());
        displayParameters(report);
        displayTable(report);
        displaySummary(report);
    }


    /**
     * Displays the header section of the compound interest report to the console.
     * Prints a separator line, the report title, the investment name, and another separator line.
     *
     * @param inv the Investment object whose name will be displayed in the header
     */
    private void displayHeader(Investment inv) {
        System.out.println("\n" + LINE);
        System.out.println("   REPORTE DE CAPITALIZACION COMPUESTA ANUAL");
        System.out.println("   Inversion: " + inv.getName());
        System.out.println(LINE);
    }

    /**
     * Displays the investment parameters section of the compound interest report.
     * This method outputs the key parameters used in the compound interest calculation,
     * including the initial capital, annual interest rate, decimal factor, growth factor,
     * and the number of projected years. All values are formatted and displayed to the console
     * in Spanish language format under a "PARAMETROS" header.
     *
     * @param report the compound interest report containing the investment data and projection parameters to display
     */
    private void displayParameters(CompoundInterestReport report) {
        Investment inv = report.getInvestment();
        double i = inv.getAnnualRate() / 100.0;
        System.out.println("\n  PARAMETROS");
        System.out.println("  ----------");
        System.out.printf("  Capital inicial  (m): %s%n", currency(inv.getInitialCapital()));
        System.out.printf("  Tasa anual       (X): %.2f%%%n", inv.getAnnualRate());
        System.out.printf("  Factor decimal   (i): %.4f%n", i);
        System.out.printf("  Factor crecimiento  : %.4f   (1 + i)%n", inv.getGrowthFactor());
        System.out.printf("  Anos proyectados (n): %d%n", report.getYears());
    }

    /**
     * Displays a formatted table showing the annual capitalization details of the investment.
     * The table includes columns for year, opening capital, interest earned, and closing capital.
     * Each row represents a compound interest record for a specific year from the report.
     *
     * @param report the compound interest report containing the records to display in the table
     */
    private void displayTable(CompoundInterestReport report) {
        System.out.println("\n  TABLA DE CAPITALIZACION ANUAL");
        System.out.println("  " + ROW_SEP);
        System.out.printf("  | %-4s | %-17s | %-17s | %-17s |%n",
                "Ano", "Capital Inicial", "Interes Ganado", "Capital Final");
        System.out.println("  " + ROW_SEP);

        for (CompoundInterestRecord record : report.getRecords()) {
            System.out.printf("  | %-4d | %-17s | %-17s | %-17s |%n",
                    record.getYear(),
                    currency(record.getOpeningCapital()),
                    currency(record.getInterestEarned()),
                    currency(record.getClosingCapital()));
        }

        System.out.println("  " + ROW_SEP);
    }

    /**
     * Displays the final summary section of the compound interest report.
     * Prints the total accumulated amount, total interest earned, and overall growth percentage.
     * The summary is formatted with currency values and percentage representation,
     * providing a clear overview of the investment performance.
     *
     * @param report the CompoundInterestReport containing the calculated investment data to be summarized
     */
    private void displaySummary(CompoundInterestReport report) {
        System.out.println("\n  RESUMEN FINAL");
        System.out.println("  -------------");
        System.out.printf("  Monto total acumulado C(n) : %s%n", currency(report.getFinalAmount()));
        System.out.printf("  Total intereses ganados    : %s%n", currency(report.getTotalInterestEarned()));
        System.out.printf("  Crecimiento total          : %.2f%%%n", report.getGrowthPercentage());
        System.out.println("\n" + LINE + "\n");
    }


    /**
     * Formats a monetary amount as a currency string with thousands separators and two decimal places.
     * The resulting string is right-aligned in a field of 12 characters width, prefixed with a dollar sign.
     *
     * @param amount the monetary amount to format
     * @return a formatted currency string in the format "$x,xxx,xxx.xx" with right alignment
     */
    private String currency(double amount) {
        return String.format("$%,12.2f", amount);
    }
}
