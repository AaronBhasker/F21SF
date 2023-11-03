package test;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * The Manager class handles the main functionality of the company management application.
 * It reads company data from a file, allows user interaction to display specific company details,
 * writes a formatted report to a file, and handles the user login process.
 */

public class Manager {
    private CompanyList companyList;
    private Scanner scanner;

    /**
     * Constructor initializes a new Manager with an empty CompanyList and a new Scanner for input.
     */
    
    public Manager() {
        this.companyList = new CompanyList();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads company details from a specified file and adds them to the company list.
     * 
     * @param filename the name of the file to read from
     */
    
    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");

                
                int companyNumber = Integer.parseInt(details[0].trim());
                String companyName = details[1].trim();
                String productOrService = details[2].trim();
                int ranking = Integer.parseInt(details[3].trim());
                String country = details[4].trim();
                String[] priceStrings = details[5].split(";");
                double[] sharePrices = new double[priceStrings.length];
                for (int i = 0; i < priceStrings.length; i++) {
                    sharePrices[i] = Double.parseDouble(priceStrings[i].trim());
                }

                ABCompany company = new ABCompany(companyNumber, companyName, productOrService, ranking, country, sharePrices);
                companyList.addCompany(company);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes formatted company details to a specified file.
     * 
     * @param filename the name of the file to write to
     */
    
    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            // Assuming that the maximum width for each column is known or calculated
            int numWidth = 10;  // Width for company number
            int nameWidth = 26; // Width for company name
            int serviceWidth = 19; // Width for product/service
            int rankWidth = 7;  // Width for ranking
            int countryWidth = 15; // Width for country
            int priceWidth = 10; // Width for each price

            // Write header with borders
            String formatString = "| %-" + numWidth + "s | %-" + nameWidth + "s | %-" + serviceWidth + "s | %-" +
                    rankWidth + "s | %-" + countryWidth + "s | %-" + priceWidth + "s |%n";

            writer.printf(formatString,
                    "Number", "Name", "Service", "Rank", "Country", "Prices");

            // Print a line to separate the header from the data
            writer.println("+" + "-".repeat(numWidth + 2)
                    + "+" + "-".repeat(nameWidth + 2)
                    + "+" + "-".repeat(serviceWidth + 2)
                    + "+" + "-".repeat(rankWidth + 2)
                    + "+" + "-".repeat(countryWidth + 2)
                    + "+" + "-".repeat(priceWidth + 2) + "+");

            for (ABCompany company : companyList.getCompanies()) {
                String priceString = formatPrices(company.getSharePrices(), priceWidth - 2); // Subtract 2 for the border spacing

                writer.printf(formatString,
                        company.getCompanyNumber(),
                        company.getCompanyName(),
                        company.getProductOrService(),
                        company.getRanking(),
                        company.getCountry(),
                        priceString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats an array of share prices into a string for writing to a file.
     * 
     * @param prices array of share prices to format
     * @param width  the width allocated for each price in the formatted string
     * @return a formatted string of share prices
     */
    
    private String formatPrices(double[] prices, int width) {
        StringBuilder sb = new StringBuilder();
        for (double price : prices) {
            sb.append(String.format("%-" + width + ".2f", price));
        }
        return sb.toString();
    }
    
    /**
     * Generates a detailed report with company information and statistics, appending it to a given file.
     * 
     * @param filename the name of the file to append the report to
     */
    
    public void generateReport(String filename) {
        writeToFile(filename);
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) { // Append mode
        ABCompany highestPriceCompany = companyList.getCompanyWithHighestSharePrice();
        double averageSharePrice = companyList.getAverageSharePrice();
        double minSharePrice = companyList.getMinSharePrice();
        double maxSharePrice = companyList.getMaxSharePrice();
        long companiesProducingClothes = companyList.countCompaniesProducing("clothes");
        Map<Double, Long> sharePriceFrequency = companyList.getSharePriceFrequency();

  
        if (highestPriceCompany != null) {
            writer.println("\nCompany with the Highest Share Price:");
            writer.println(highestPriceCompany.getFullDetails());
            writer.println();
        }

        writer.println("Summary Statistics:");
        writer.printf("Average Share Price: %.2f%n", averageSharePrice);
        writer.printf("Minimum Share Price: %.2f%n", minSharePrice);
        writer.printf("Maximum Share Price: %.2f%n", maxSharePrice);
        writer.printf("Number of Companies Producing Clothes: %d%n", companiesProducingClothes);


        writer.println("Share Price Frequency:");
        sharePriceFrequency.forEach((price, frequency) ->
            writer.printf("Price: %.2f, Frequency: %d%n", price, frequency));

        writer.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    /**
     * Displays short details for a company identified by its company number.
     * 
     * @param companyNumber the number of the company to display information for
     */
}
    public void displayShortDetailsForCompany(int companyNumber) {
        ABCompany company = companyList.getCompanyByNumber(companyNumber);
        if (company != null) {
            System.out.println("CN: " + company.getCompanyNumber());
            System.out.println("Company Name: " + company.getCompanyName());
            System.out.println("Product/Service: " + company.getProductOrService());
            System.out.println("Ranking: " + company.getRanking());
            System.out.println("Average share Prices: " + companyList.getAverageSharePrice());
        } else {
            System.out.println("Company with number " + companyNumber + " not found.");
        }
    }
    
    /**
     * Handles the user login process by asking for a password and authenticating it.
     * 
     * @return true if the user is successfully authenticated, false otherwise
     */
    private boolean login() {
        System.out.print("Please enter your password: ");
        String enteredPassword = scanner.nextLine();

       
        return Security.authenticate(enteredPassword);
    }
    /**
     * Runs the main flow of the company manager application, handling login, user input,
     * and report generation.
     */
    public void run() {
        System.out.println("Welcome to the Company Manager!");

        // Attempt to log in
        if (login()) {
            System.out.println("Login successful.");

            // Now ask for a company number
            System.out.print("Enter a company number: ");
            String input = scanner.nextLine();

            try {
                int companyNumber = Integer.parseInt(input);
                displayShortDetailsForCompany(companyNumber);
               
                writeToFile("C:\\Users\\aaron\\OneDrive\\Desktop\\output.txt");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

          
            generateReport("C:\\Users\\aaron\\OneDrive\\Desktop\\output.txt");
        } else {
            System.out.println("Login failed. Exiting the program.");
        }
    }

    /**
     * The entry point of the application. This method reads company details from a file and starts the application.
     * 
     * @param args command-line arguments (not used in this application)
     */

    public static void main(String[] args) {
        Manager manager = new Manager();

        // Reads company details from the file
        manager.readFromFile("C:\\Users\\aaron\\OneDrive\\Desktop\\input.txt");

        // The run method handles the entire program flow including login
        manager.run();
    }
}