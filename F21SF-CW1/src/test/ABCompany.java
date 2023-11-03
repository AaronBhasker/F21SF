package test;

import java.util.Arrays;
/**
 * ABCompany class represents a company with attributes like company number,
 * company name, product or service, ranking, country, and share prices.
 */
public class ABCompany {
    private int companyNumber;
    private String companyName;
    private String productOrService;
    private int ranking;
    private String Country;
    private double[] sharePrices;

    /**
     * Constructs an ABCompany with the specified details.
     *
     * @param companyNumber    Unique identifier for the company.
     * @param companyName      The name of the company.
     * @param productOrService The primary product or service offered by the company.
     * @param ranking          The company's ranking.
     * @param Country          The country in which the company is based.
     * @param sharePrices      An array of the company's recent share prices.
     */
    public ABCompany(int companyNumber, String companyName, String strings, int ranking, String Country, double[] sharePrices2) {
        this.companyNumber = companyNumber;
        this.companyName = companyName;
        this.productOrService = strings;
        this.ranking = ranking;
        this.Country = Country;
        this.sharePrices = sharePrices2;
    }
    /**
     * Overloaded constructor for ABCompany. Currently, it does not set any attributes
     * and only returns without any action.
     *
     * @param companyNumber    Unique identifier for the company.
     * @param companyName      The name of the company.
     * @param productOrServices The primary product or service offered by the company.
     * @param sharePrices      The company's last known share price.
     * @deprecated This constructor is not fully implemented and should not be used.
     */
    public ABCompany(int companyNumber, String companyName, String productOrServices, double sharePrices) {
    	return;
	}

    /**
     * Retrieves the company number.
     *
     * @return The company number.
     */
    
    public int getCompanyNumber() {
        return companyNumber;
    }
    /**
     * Sets the company number to the specified value.
     *
     * @param companyNumber The new company number.
     */
    public void setCompanyNumber(int companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductOrService() {
        return productOrService;
    }

    public void setProductOrService(String productOrService) {
        this.productOrService = (String) productOrService;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public double[] getSharePrices() {
        return sharePrices;
    }

    public void setSharePrices(double[] sharePrices) {
        this.sharePrices = sharePrices;
    }
    /**
     * Calculates the average share price of the company.
     *
     * @return The average of the share prices.
     */
    public double getAverageSharePrice() {
        int total = 0;
        for (double price : sharePrices) {
            total += price;
        }
        return (double) total / sharePrices.length;
    }
    /**
     * Retrieves the highest share price of the company.
     *
     * @return The maximum share price from the available data.
     */
    public double getMaxSharePrice() {
        return Arrays.stream(sharePrices).max().orElse(Double.NaN); // Assuming sharePrices is a double array
    }

    /**
     * Provides a string containing full details of the company including
     * name, country, products or services, and share price information.
     *
     * @return Detailed string of company information.
     */
    
    public String getFullDetails() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Full details for ")).append(String.format("%03d", companyNumber)).append(": \nName: ").append(companyName).append(", ").append(Country).append(". ");
        sb.append(companyName).append(" produces ").append(String.join(", ", productOrService)).append(".\nThe past five-day share price: ");
        for (int i = 0; i < sharePrices.length; i++) {
            sb.append(sharePrices[i]);
            if (i != sharePrices.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("\nTherefore, they have an average unaltered share price of ").append(getAverageSharePrice());
        return sb.toString();
    }
    /**
     * Provides a string containing short details of the company, including
     * company number and average share price.
     *
     * @return Brief company details.
     */
    public String getShortDetails() {
        StringBuffer sb = new StringBuffer();
        sb.append("Short Details for ").append(String.format("%03d", companyNumber)).append(":\n");
        sb.append("CN ").append(String.format("%03d", companyNumber));
        sb.append("(").append(companyName.charAt(0)).append(") has an average share price of ").append(getAverageSharePrice()).append("\n");
        return sb.toString();
    }}