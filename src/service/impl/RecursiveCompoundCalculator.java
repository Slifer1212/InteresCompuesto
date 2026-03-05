package service.impl;

import model.CompoundInterestRecord;
import model.CompoundInterestReport;
import model.Investment;
import service.CompoundInterestCalculator;

import java.util.ArrayList;
import java.util.List;


public class RecursiveCompoundCalculator implements CompoundInterestCalculator {

    /**
     * Calculates the total amount of an investment at a given year using recursive compound interest.
     * This method recursively computes the investment value by applying the growth factor
     * to the previous year's amount. The base case returns the initial capital when year is 0.
     *
     * @param investment the investment containing initial capital and growth factor information
     * @param year       the year for which to calculate the amount, where 0 represents the initial year
     * @return the total amount of the investment at the specified year
     */
    @Override
    public double calculateAmount(Investment investment, int year) {
        if (year == 0) {
            return investment.getInitialCapital();
        }
        return calculateAmount(investment, year - 1) * investment.getGrowthFactor();
    }

    /**
     * Generates a comprehensive compound interest report for the given investment over the specified time period.
     * <p>
     * This method calculates the year-by-year progression of the investment, creating a record for each year
     * from year 0 (initial state) through the specified number of years. Each record includes the opening
     * capital, interest earned during that year, and closing capital. The calculation uses recursive
     * compound interest based on the investment's growth factor.
     *
     * @param investment the investment configuration containing initial capital and annual rate
     * @param years      the number of years to project the investment growth
     * @return a CompoundInterestReport containing the investment details, projection period, and a list
     * of records showing the capital progression for each year
     */
    @Override
    public CompoundInterestReport generateReport(Investment investment, int years) {
        List<CompoundInterestRecord> records = new ArrayList<>();

        for (int year = 0; year <= years; year++) {
            double closing = calculateAmount(investment, year);
            double opening = (year == 0)
                    ? investment.getInitialCapital()
                    : calculateAmount(investment, year - 1);
            double interest = closing - opening;

            records.add(new CompoundInterestRecord(year, opening, interest, closing));
        }

        return new CompoundInterestReport(investment, years, records);
    }
}
