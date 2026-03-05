package ui;

import model.Investment;
import validator.InputValidator;
import validator.InvestmentValidators;

import java.util.Scanner;


public class ConsoleInvestmentInputReader implements InvestmentInputReader {

    /**
     * Scanner instance used to read user input from the console.
     * This scanner is injected through the constructor and is used throughout
     * the class to read investment data including names, capital amounts,
     * interest rates, and investment periods. The scanner reads from standard
     * input and is expected to remain open for the lifetime of this reader instance.
     */
    private final Scanner scanner;
    /**
     * Validator responsible for validating the initial capital input for investments.
     * This validator ensures that capital values entered by users meet the required
     * business rules and constraints before being used to create Investment objects.
     * The validator is instantiated as a CapitalValidator and used during the
     * investment data collection process to verify that capital amounts are valid.
     */
    private final InputValidator<Double> capitalValidator;
    /**
     * Validator used to verify that the annual interest rate input meets
     * all required business rules and constraints before being accepted.
     * This validator is applied when reading the interest rate value from
     * user input to ensure it falls within acceptable bounds for investment
     * calculations. The validation is performed by the RateValidator implementation,
     * which typically ensures the rate is positive and within reasonable limits
     * for compound interest calculations.
     */
    private final InputValidator<Double> rateValidator;
    /**
     * Validator responsible for ensuring that the number of investment years entered by the user
     * meets the required criteria. This validator is used to validate integer input representing
     * the investment period duration before it is used in compound interest calculations.
     * Validation rules are defined by the InvestmentValidators.YearsValidator implementation.
     */
    private final InputValidator<Integer> yearsValidator;

    /**
     * Constructs a ConsoleInvestmentInputReader with the specified Scanner and initializes
     * the validators for capital, interest rate, and investment years.
     * The validators are configured to ensure that capital is positive, interest rate is within
     * valid bounds, and years are non-negative.
     *
     * @param scanner the Scanner instance to read user input from the console
     */
    public ConsoleInvestmentInputReader(Scanner scanner) {
        this.scanner = scanner;
        this.capitalValidator = new InvestmentValidators.CapitalValidator();
        this.rateValidator = new InvestmentValidators.RateValidator();
        this.yearsValidator = new InvestmentValidators.YearsValidator();
    }

    /**
     * Reads investment information from the console by prompting the user for
     * the investment name, initial capital, and annual interest rate.
     * <p>
     * This method displays prompts in Spanish and collects user input for each
     * investment parameter. The name is read as a trimmed string, while the
     * capital and rate values are validated using their respective validators
     * to ensure they meet business rules before creating the Investment object.
     *
     * @return a new Investment instance built from the user-provided input values
     */
    @Override
    public Investment readInvestment() {
        System.out.print("  Nombre de la inversion        : ");
        String name = scanner.nextLine().trim();

        double capital = readValidDouble(
                "  Capital inicial (m)           : $ ",
                capitalValidator);

        double rate = readValidDouble(
                "  Tasa de interes anual (X)     : ",
                rateValidator);

        return new Investment.Builder()
                .name(name)
                .initialCapital(capital)
                .annualRate(rate)
                .build();
    }

    /**
     * Reads and validates the number of years for an investment from the console.
     * Prompts the user to enter a non-negative integer value representing the investment period.
     * The input is validated using the years validator to ensure it meets the required constraints.
     * If the input is invalid, the user is prompted to re-enter the value until a valid input is provided.
     *
     * @return the validated number of years as a non-negative integer
     */
    @Override
    public int readYears() {
        return readValidInt(
                "  Anos de inversion (n >= 0)    : ",
                yearsValidator);
    }

    /**
     * Reads and validates a double value from user input.
     * Continuously prompts the user until a valid double value is entered that passes validation.
     * Displays error messages for invalid number formats or validation failures.
     *
     * @param prompt    the message to display when prompting for input
     * @param validator the validator to verify the entered double value meets specific criteria
     * @return the validated double value entered by the user
     */
    private double readValidDouble(String prompt, InputValidator<Double> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                validator.validate(value);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    /**
     * Reads and validates an integer value from user input.
     * Continuously prompts the user until a valid integer that passes validation is provided.
     * Handles invalid number formats and validation errors by displaying appropriate error messages.
     *
     * @param prompt    the message to display when prompting for input
     * @param validator the validator to check if the parsed integer meets required constraints
     * @return the validated integer value entered by the user
     */
    private int readValidInt(String prompt, InputValidator<Integer> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                validator.validate(value);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Ingrese un numero entero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }
}
