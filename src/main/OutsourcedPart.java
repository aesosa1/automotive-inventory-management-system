package main;

/**
 * This class extends the provided Part.java class.
 * Allows for the creation and accessing of Outsourced Part Objects
 *
 * @author Alberto Sosa
 */

public class OutsourcedPart extends Part {
    private String companyName;
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.setCompanyName(companyName);
    }

    /**
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the company name
     */
    public String getCompanyName(){
        return companyName;
    }
}
