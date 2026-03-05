package config;

import model.Investment;
import model.CompoundInterestReport;
import service.CompoundInterestCalculator;
import ui.InvestmentInputReader;
import ui.CompoundInterestReporter;

public class CompoundInterestApplication {

    private final InvestmentInputReader inputReader;
    private final CompoundInterestCalculator calculator;
    private final CompoundInterestReporter reporter;

    public CompoundInterestApplication(
            InvestmentInputReader inputReader,
            CompoundInterestCalculator calculator,
            CompoundInterestReporter reporter) {

        this.inputReader = inputReader;
        this.calculator = calculator;
        this.reporter = reporter;
    }

    public void run() {
        printBanner();

        Investment investment = inputReader.readInvestment();
        int years = inputReader.readYears();

        CompoundInterestReport report = calculator.generateReport(investment, years);

        reporter.displayReport(report);
    }

    private void printBanner() {
        System.out.println("=================================================================");
        System.out.println("   MODULO DE CAPITALIZACION RECURSIVA — INTERES COMPUESTO");
        System.out.println("=================================================================");
        System.out.println("\n  Ingrese los datos de la inversion:\n");
    }
}
