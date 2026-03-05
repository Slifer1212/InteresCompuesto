package model;

public final class Investment {

    /**
     * The name or identifier of this investment.
     * This field provides a human-readable label to distinguish between different investments.
     */
    private final String name;
    /**
     * The initial capital amount of the investment in monetary units.
     * This represents the starting principal that will be subject to compound interest calculations.
     * Must be greater than zero as validated by the CapitalValidator.
     */
    private final double initialCapital;
    /**
     * The annual interest rate expressed as a percentage.
     * <p>
     * This value represents the yearly rate of return on the investment.
     * For example, a value of 5.0 represents a 5% annual interest rate.
     * The rate must be positive and is used to calculate the growth factor
     * and compound interest over time.
     */
    private final double annualRate;

    /**
     * Private constructor that creates an Investment instance from a Builder.
     * Initializes all investment properties with values from the provided builder.
     * This constructor is part of the Builder pattern implementation and should only
     * be called by the Builder.build() method.
     *
     * @param builder the Builder instance containing the investment configuration values
     */
    private Investment(Builder builder) {
        this.name = builder.name;
        this.initialCapital = builder.initialCapital;
        this.annualRate = builder.annualRate;
    }

    /**
     * Returns the name of this investment.
     *
     * @return the investment name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the initial capital amount of this investment.
     *
     * @return the initial capital value in monetary units
     */
    public double getInitialCapital() {
        return initialCapital;
    }

    /**
     * Returns the annual interest rate for this investment.
     * The rate is expressed as a percentage value.
     *
     * @return the annual interest rate as a percentage
     */
    public double getAnnualRate() {
        return annualRate;
    }

    /**
     * Calculates and returns the annual growth factor for this investment.
     * The growth factor represents the multiplier applied to capital each year,
     * calculated as 1 plus the annual rate expressed as a decimal.
     * For example, a 5% annual rate yields a growth factor of 1.05.
     *
     * @return the growth factor as a decimal value (e.g., 1.05 for 5% annual rate)
     */
    public double getGrowthFactor() {
        return 1.0 + (annualRate / 100.0);
    }

    public static class Builder {
        private String name;
        private double initialCapital;
        private double annualRate;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder initialCapital(double initialCapital) {
            this.initialCapital = initialCapital;
            return this;
        }

        public Builder annualRate(double annualRate) {
            this.annualRate = annualRate;
            return this;
        }

        public Investment build() {
            return new Investment(this);
        }
    }

    /**
     * Returns a string representation of this Investment object.
     * The string includes the investment name, initial capital with two decimal places,
     * and annual rate with two decimal places followed by a percent sign.
     *
     * @return a formatted string containing the investment's name, initial capital, and annual rate
     */
    @Override
    public String toString() {
        return String.format("Investment{name='%s', capital=%.2f, rate=%.2f%%}",
                name, initialCapital, annualRate);
    }
}
