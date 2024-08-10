package Model;

/**
 * Class for parts made InHouse rather than Outsourced.
 */
public class InHouse extends Part {

        private int machineId;

    /**
     * InHouse part constructor. Creates a part object with an added machine ID value to create an InHouse Part
     * @param id identification
     * @param name name of part
     * @param price price of part
     * @param stock stock of part
     * @param min min stock of part
     * @param max max stock of part
     * @param machineId machine ID of part
     */
        public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
            super(id, name, price, stock, min, max);
            this.machineId = machineId;
        }

    /**Sets this InHouse parts machine ID value.
     * Accepts given machine ID value and sets it to this InHouse object.
     * @param machineId machine ID value of this InHouse part
     */
    public void setMachineId(int machineId){
            this.machineId = machineId;
        }

    /**Returns machine ID.
     * Returns machine ID value from this InHouse object
     * @return machineId
     */
    public int getMachineId(){
            return machineId;
        }

    }


