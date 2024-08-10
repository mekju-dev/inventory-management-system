package Model;

/**
 * Class for outsourced parts.
 */
public class Outsourced extends Part {

    /**
     * String value representing the company name that makes the outsourced part
     */
    private String companyName;

    /**
     * Outsourced part constructor creates a part and also includes the company name that makes the part
     * @param id id value of part
     * @param name name of part
     * @param price price of part
     * @param stock stock of part
     * @param min min stock of part
     * @param max max stock of part
     * @param companyName company name that makes part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Set company name.
     * Accepts given String and sets it as this Outsourced part's company name value.
     * @param companyName name of company that makes this part
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**Get company name.
     * Returns this Outsourced part's company name.
     * @return this.companyName
     */
    public String getCompanyName(){
        return this.companyName;
    }
}

