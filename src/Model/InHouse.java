/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel
 */
public class InHouse extends Part {
    
    private int machineID;
    
    public InHouse(int id, String name, double price, int stock, 
            int min, int max, int machineID){
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    public void setMachineID(int id){
        this.machineID = id;
    }
    
    public int getMachineID(){
        return machineID;
    }
        
    
}
