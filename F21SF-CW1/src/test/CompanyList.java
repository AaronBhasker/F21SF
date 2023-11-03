package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompanyList {
    private List<ABCompany> companies;
    /**
     * Constructs an empty list of companies.
     */
    public CompanyList() {
        companies = new ArrayList<>();
    }
    /**
     * Adds a company to the list.
     *
     * @param company the {@code ABCompany} to be added to the list
     */
    public void addCompany(ABCompany company) {
        companies.add(company);
    }
    /**
     * Retrieves the list of companies.
     *
     * @return the list of {@code ABCompany} objects
     */
    public List<ABCompany> getCompanies() {
        return companies;
    }
    /**
     * Finds and returns the company with the highest share price.
     *
     * @return The {@code ABCompany} with the highest share price, or null if the list is empty.
     */
    public ABCompany getCompanyWithHighestSharePrice() {
        return companies.stream()
                .max((c1, c2) -> Double.compare(c1.getMaxSharePrice(), c2.getMaxSharePrice()))
                .orElse(null);
    }

    /**
     * Calculates statistical information about share prices including count, sum, min, and max.
     *
     * @return An array of doubles containing the count, sum, minimum, and maximum share price values.
     */
    public double[] getSharePriceStatistics() {
        return companies.stream()
                .flatMapToDouble(c -> Arrays.stream(c.getSharePrices()))
                .collect(() -> new double[4], // supplier
                        (a, b) -> { // accumulator
                            a[0]++; // count
                            a[1] += b; // sum
                            a[2] = a[2] == 0 ? b : Math.min(a[2], b); // min
                            a[3] = Math.max(a[3], b); // max
                        },
                        (a, b) -> { // combiner
                            a[0] += b[0];
                            a[1] += b[1];
                            a[2] = Math.min(a[2], b[2]);
                            a[3] = Math.max(a[3], b[3]);
                        });
    }
    /**
     * Computes and returns the average share price across all companies.
     *
     * @return The average share price, or {@code Double.NaN} if there are no share prices.
     */
    public double getAverageSharePrice() {
        double[] stats = getSharePriceStatistics();
        return stats[0] > 0 ? stats[1] / stats[0] : Double.NaN;
    }
    /**
     * Retrieves the minimum share price across all companies.
     *
     * @return The minimum share price value.
     */
    public double getMinSharePrice() {
        double[] stats = getSharePriceStatistics();
        return stats[2];
    }
    /**
     * Retrieves the maximum share price across all companies.
     *
     * @return The maximum share price value.
     */
    public double getMaxSharePrice() {
        double[] stats = getSharePriceStatistics();
        return stats[3];
    }
    /**
     * Counts the number of companies that produce a given product or service.
     *
     * @param productOrService The product or service to match against the companies.
     * @return The number of companies producing the specified product or service.
     */
    public long countCompaniesProducing(String productOrService) {
        return companies.stream()
                .filter(c -> c.getProductOrService().equalsIgnoreCase(productOrService))
                .count();
    }
    /**
     * Creates a frequency map of share prices, showing how many companies have each share price.
     *
     * @return A {@code Map<Double, Long>} where the key is the share price and the value is the frequency.
     */
    public Map<Double, Long> getSharePriceFrequency() {
        return companies.stream()
                .flatMapToDouble(c -> Arrays.stream(c.getSharePrices()))
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    /**
     * Retrieves a company by its company number.
     *
     * @param companyNumber The number to search for.
     * @return The {@code ABCompany} with the corresponding number, or null if not found.
     */
    public ABCompany getCompanyByNumber(int companyNumber) {
        return companies.stream()
                .filter(c -> c.getCompanyNumber() == companyNumber)
                .findFirst()
                .orElse(null);
    }
    
}
