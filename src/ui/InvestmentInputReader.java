package ui;

import model.Investment;

public interface InvestmentInputReader {
    Investment readInvestment();

    int readYears();
}
