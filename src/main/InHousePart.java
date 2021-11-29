package main;

/**
 * This class extends the provided Part.java class.
 * Allows for the creation and accessing of In House Part Objects
 *
 * @author Alberto Sosa
 */

public class InHousePart extends Part {
    private int machineId;

    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setMachineId(machineId);
    }

    /**
     * @param machineId sets the machine id
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * @return the machine id
     */
    public int getMachineId(){
        return machineId;
    }
}
