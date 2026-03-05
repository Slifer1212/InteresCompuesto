package service;

import model.CompoundInterestReport;
import model.Investment;

public interface CompoundInterestCalculator {

    double calculateAmount(Investment investment, int year);

    CompoundInterestReport generateReport(Investment investment, int years);

}
