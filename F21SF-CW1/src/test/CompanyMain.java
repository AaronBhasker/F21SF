
package test;
/**
 * The CompanyMain class serves as the entry point to the application.
 * It demonstrates the creation of ABCompany instances and outputs their details.
 */
public class CompanyMain {
	/**
     * The main method serves as the entry point for the application.
     * It creates instances of ABCompany with predefined share prices and company details,
     * and then prints out the full and short details of each company.
     *
     * @param args the command-line arguments (not used).
     */
	 public static void main(String[] args) {
	        double[] sharePrices1 = {221, 216, 214, 213, 215};
	        double[] sharePrices2 = {260, 257, 256, 255, 254};
	        double[] sharePrices3 = {211, 209, 204, 203, 201};
	        double[] sharePrices4 = {117, 113, 114, 110, 119};
	        double[] sharePrices5 = {484, 467, 466, 463, 467};
	        
	        ABCompany company1 = new ABCompany(1, "Marks & Spencer Group PLC", new String("clothes"), 1, "Country : UK", sharePrices1);
	        ABCompany company2 = new ABCompany(2, "J Sainsbury PLC", new String("groceries"), 2, "Country : UK", sharePrices2);
	        ABCompany company3 = new ABCompany(3, "Kingfisher plc", new String("Betting operators"), 3, "Country : Ireland", sharePrices3);
	        ABCompany company4 = new ABCompany(4, "Airtel Africa plc", new String("telecommunications"), 4, "Country : India", sharePrices4);
	        ABCompany company5 = new ABCompany(5, "MELROSE INDUSTRIES PLC", new String("Industrial Goods"), 5, "Country : UK", sharePrices5);
	    
	        System.out.println(company1.getFullDetails());
	        System.out.println(company1.getShortDetails());
	        System.out.println(company2.getFullDetails());
	        System.out.println(company2.getShortDetails());
	        System.out.println(company3.getFullDetails());
	        System.out.println(company3.getShortDetails());
	        System.out.println(company4.getFullDetails());
	        System.out.println(company4.getShortDetails());
	        System.out.println(company5.getFullDetails());
	        System.out.println(company5.getShortDetails());
	    }
	
}