package Model;

/**
 * Class for Part Object.
 * Supplied class: Part.java
 */
public abstract class Part {
    /**
     * Part ID value to differentiate between parts
     */
    private int id;

    /**
     * Part Name value
     */
    private String name;

    /**
     * Part Price value
     */
    private double price;

    /**
     * Part current stock amount
     */
    private int stock;

    /**
     * Part minimum stock amount
     */
    private int min;

    /**
     * Part maximum stock amount
     */
    private int max;

    /**
     * Part Constructor, creates part object with given parameters
     * @param id id of part
     * @param name name of part
     * @param price price of part
     * @param stock stock of part
     * @param min min stock of part
     * @param max max stock of part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Get ID. Returns ID of this part object.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**Set ID. Sets this part's ID value as passed in parameter.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Get name. Returns name of part.
     * @return name the name of the part
     */
    public String getName() {
        return name;
    }

    /**Set name. Sets name of part as passed in parameter.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Get price. Returns price of part.
     * @return price the price of part
     */
    public double getPrice() {
        return price;
    }

    /**Set price. Sets this parts price value to passed in price value.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Get stock. Gets stock value of this part.
     * @return stock number of units of this part in inventory
     */
    public int getStock() {
        return stock;
    }

    /**Set stock. Sets stock value of part to passed in stock value.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Get min. Returns minimum stock value of part.
     * @return min the minimum value this parts stock should be
     */
    public int getMin() {
        return min;
    }

    /**Set min. Sets the minimum value this parts stock should be.
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**Get max. Returns max value this parts stock should be.
     * @return max the max value this parts stock should be
     */
    public int getMax() {
        return max;
    }

    /**Set max. Sets maximum value this stock should be from passed in parameter.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}
