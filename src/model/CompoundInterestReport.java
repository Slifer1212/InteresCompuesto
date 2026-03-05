package model;

import java.util.Collections;
import java.util.List;


public final class CompoundInterestReport {

    /**
     * The investment configuration for which this compound interest report was generated.
     * Contains the initial capital, annual interest rate, and other investment parameters
     * used to calculate the compound interest records in this report.
     */
    private final model.Investment investment;
    /**
     * The number of years over which the compound interest will be calculated.
     * This value represents the time period for the investment and must be non-negative.
     * It is used to determine the duration of the compound interest calculation and
     * the number of records that will be generated in the report.
     */
    private final int years;
    /**
     * An immutable list of compound interest records representing the year-by-year
     * progression of the investment. Each record in the list captures the financial
     * state for a specific year, including opening capital, interest earned, and
     * closing capital. The list is ordered chronologically by year.
     */
    private final List<model.CompoundInterestRecord> records;

    /**
     * Constructs a new CompoundInterestReport with the specified investment details,
     * time period, and calculation records.
     * The provided records list is wrapped in an unmodifiable list to ensure immutability
     * of the report data after construction.
     *
     * @param investment the investment details including initial capital and interest rate
     * @param years the number of years over which compound interest is calculated
     * @param records the list of compound interest records for each period in the calculation
     */
    public CompoundInterestReport(model.Investment investment, int years,
                                  List<model.CompoundInterestRecord> records) {
        this.investment = investment;
        this.years = years;
        this.records = Collections.unmodifiableList(records);
    }

    /**
     * Returns the investment details associated with this compound interest report.
     * The investment contains the initial capital, annual interest rate, and other
     * configuration parameters used to generate this report.
     *
     * @return the Investment object containing the investment configuration
     */
    public model.Investment getInvestment() {
        return investment;
    }

    /**
     * Returns the number of years over which the compound interest is calculated.
     *
     * @return the number of years for the investment period
     */
    public int getYears() {
        return years;
    }

    /**
     * Returns an unmodifiable list of compound interest records representing the year-by-year
     * breakdown of the investment's growth over the specified investment period.
     * Each record contains the opening capital, interest earned, and closing capital for
     * a particular year in the investment timeline.
     *
     * @return an unmodifiable list of CompoundInterestRecord objects, one for each year of the investment period
     */
    public List<model.CompoundInterestRecord> getRecords() {
        return records;
    }

    /**
     * Returns the final amount of the investment after all compound interest calculations.
     * This represents the closing capital at the end of the last year in the compound interest period.
     * If no records exist in the report, returns 0.
     *
     * @return the final investment amount in monetary units, or 0 if no records are present
     */
    public double getFinalAmount() {
        return records.isEmpty() ? 0 : records.get(records.size() - 1).getClosingCapital();
    }

    /**
     * Calculates and returns the total interest earned over the investment period.
     * The total interest is computed as the difference between the final amount
     * and the initial capital of the investment.
     *
     * @return the total interest earned in monetary units, or a negative value if the final amount is less than the initial capital
     */
    public double getTotalInterestEarned() {
        return getFinalAmount() - investment.getInitialCapital();
    }

    /**
     * Calculates and returns the overall growth percentage of the investment.
     * The growth percentage represents the total interest earned relative to the initial capital,
     * expressed as a percentage. This is calculated by dividing the total interest earned by
     * the initial capital and multiplying by 100.
     *
     * @return the growth percentage of the investment relative to the initial capital
     */
    public double getGrowthPercentage() {
        return (getTotalInterestEarned() / investment.getInitialCapital()) * 100.0;
    }
}
