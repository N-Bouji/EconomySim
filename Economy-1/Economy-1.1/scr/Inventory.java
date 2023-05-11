
import java.util.*;
public class Inventory {
//inventory kepps track of a person's items and has inventory related tools
    private ArrayList<Integer> inventory;
    
    public Inventory() {
        this.inventory = new ArrayList<Integer>(Arrays.asList(0, 0, 0)); // wood, metal, glass
    }
    
    public static String getItemName(int itemID) {
        if(itemID == 0) {
            return "wood";
        }
        if(itemID == 1) {
            return "metal";
        }
        if(itemID == 2) {
            return "glass";
        }
        return "invalid";
    }
    
    public int getWood() {
        return inventory.get(0);
    }
    public void addWood(int amount) {
        inventory.set(0, inventory.get(0) + amount);
    }
    
    public int getMetal() {
        return inventory.get(1);
    }
    public void addMetal(int amount) {
        inventory.set(1, inventory.get(1) + amount);
    }
    public int getGlass() {
        return inventory.get(2);
    }
    public void addGlass(int amount) {
        inventory.set(2, inventory.get(2) + amount);
    }
    
    public void addItem(String item, int amount) {
        if(item.equals("wood")){
            this.addWood(amount);
            
        } else if(item.equals("metal")) {
            this.addMetal(amount);
            
        }else if(item.equals("glass")) {
            this.addGlass(amount);
            
        } else {
            //System.out.print("ERROR: THE ITEM TRYING TO BE ADDED DOES NOT EXIST");
        }
    }
    public void addItem(int itemID, int amount) {
        if(itemID == 0){
            this.addWood(amount);
            
        } else if(itemID == 1) {
            this.addMetal(amount);
            
        }else if(itemID == 2) {
            this.addGlass(amount);
            
        } else {
            //System.out.print("ERROR: THE ITEM TRYING TO BE ADDED DOES NOT EXIST");
        }
    }
    
    public int getItem(String item) {
        if(item.equals("wood")){
            return this.getWood();
            
        } else if(item.equals("metal")) {
            return this.getMetal();
            
        }else if(item.equals("glass")) {
            return this.getGlass();
            
        } else {
            System.out.print("ERROR70: THE ITEM DOES NOT EXIST");
            return -1;
        }
    }
    public int getItem(int itemID) {
        if(itemID == 0){
            return this.getWood();
            
        } else if(itemID == 1) {
            return this.getMetal();
            
        }else if(itemID == 2) {
            return this.getGlass();
            
        } else {
            //System.out.print("ERROR85: THE ITEM DOES NOT EXIST");
            return -1;
        }
    }
    
    public ArrayList<Integer> getInventory() {
        return inventory;
    }
    

    
    public String toString(){
        
        return "Wood: " + inventory.get(0) + ", Metal: " + inventory.get(1) + ", Glass: " + inventory.get(2);
    }
}
