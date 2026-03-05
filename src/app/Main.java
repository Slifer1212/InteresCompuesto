package app;

import config.CompoundInterestApplication;
import service.CompoundInterestCalculator;
import service.impl.RecursiveCompoundCalculator;
import ui.CompoundInterestReporter;
import ui.ConsoleCompoundInterestReporter;
import ui.ConsoleInvestmentInputReader;
import ui.InvestmentInputReader;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        InvestmentInputReader reader = new ConsoleInvestmentInputReader(scanner);
        CompoundInterestCalculator calculator = new RecursiveCompoundCalculator();
        CompoundInterestReporter reporter = new ConsoleCompoundInterestReporter();

        CompoundInterestApplication app = new CompoundInterestApplication(reader, calculator, reporter);

        app.run();

        scanner.close();
    }
}
