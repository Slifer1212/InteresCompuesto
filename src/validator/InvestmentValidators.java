package validator;

public final class InvestmentValidators {

    private InvestmentValidators() { /* Utility class */ }

    public static class CapitalValidator implements validator.InputValidator<Double> {
        @Override
        public void validate(Double value) {
            if (value == null || value <= 0) {
                throw new IllegalArgumentException(
                        "El capital inicial debe ser mayor a cero. Recibido: " + value);
            }
        }
    }

    public static class RateValidator implements validator.InputValidator<Double> {
        private static final double MIN = 0.01;
        private static final double MAX = 999.99;

        @Override
        public void validate(Double rate) {
            if (rate == null || rate < MIN || rate > MAX) {
                throw new IllegalArgumentException(String.format(
                        "La tasa de interes debe estar entre %.2f%% y %.2f%%. Recibida: %.2f%%",
                        MIN, MAX, rate));
            }
        }
    }


    public static class YearsValidator implements validator.InputValidator<Integer> {
        @Override
        public void validate(Integer years) {
            if (years == null || years < 0) {
                throw new IllegalArgumentException(
                        "Los anos de inversion no pueden ser negativos. Recibidos: " + years);
            }
        }
    }
}
