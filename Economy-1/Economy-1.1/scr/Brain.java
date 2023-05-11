import java.util.*;
public class Brain {
//the brain is an object used by the class Person to make decisions    
    
    private static final int SKILL_EFFECT = 1; // 1 - 100
    
    private Person person;
    
    private int woodSkill; 
    private int metalSkill;
    private int glassSkill;
    
    public Brain(Person person) {
        this.person = person;
    }
    
    //increase the skill level for a certain item
    public void addSkill(int itemID) {
        if(itemID == Economy.WOOD_ID) {
            woodSkill++;
        } else if(itemID == Economy.METAL_ID) {
            metalSkill++;
        } else if(itemID == Economy.GLASS_ID) {
            glassSkill++;
        } else {
            //System.out.println("ERROR: ITEM DOESNT EXIST");
        }
    }
    //return the skil level of a certain item
    public double getSkillMult(int itemID) {
        if(itemID == 0) {
            return woodSkill/(101 - SKILL_EFFECT);
        } else if(itemID == 1) {
            return metalSkill/(101 - SKILL_EFFECT);
        } else if(itemID == 2){
            return glassSkill/(101 - SKILL_EFFECT);
        } else {
            return -1;
        }
    }
    //Logic to decide what item to consume
    public int whatToConsume() {//chooses to consume the item with the highest quantity
        int highest = 0;
        int highestID = -1;
        ArrayList<Integer> inventory = person.getInventory().getInventory();
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) > highest) {
                highest = inventory.get(i);
                highestID = i;
            }
        }
        if(highest > 0) {
            return highestID;
        } else {
            return -1;
        }
    }
    
    
    //logic to decide what item to sell
    public int whatToSell() {
        int sellWood = 0;
        int sellMetal = 0;
        int sellGlass = 0;
        
        int woodNum = person.getInventory().getInventory().get(0);
        int metalNum = person.getInventory().getInventory().get(1);
        int glassNum = person.getInventory().getInventory().get(2);
        
        if((woodSkill >= metalSkill) && (woodSkill >= glassSkill)) {
            sellWood++;
        } else if((metalSkill >= woodSkill) && (metalSkill >= glassSkill)) {
            sellMetal++;
        } else {
            sellGlass++;
        }
        
        if((Market.getAveragePrice(0) >= Market.getAveragePrice(1) && (Market.getAveragePrice(0) >= Market.getAveragePrice(2)))) {
            sellWood++;
        } else if((Market.getAveragePrice(1) >= Market.getAveragePrice(0) && (Market.getAveragePrice(1) >= Market.getAveragePrice(2)))) {
            sellMetal++;
        } else {
            sellGlass++;
        }
        
        if((woodNum >= metalNum) && (woodNum >= glassNum)) {
            sellWood++;
        } else if((metalNum >= woodNum) && (metalNum >= glassNum)) {
            sellMetal++;
        } else {
            sellGlass++;
        }
        
        int random = (int)(Math.random() * 3);
        
        if(sellWood == 3 && woodNum > 0) {
            return 0;
        }
        if(sellMetal == 3 && metalNum > 0) {
            return 1;
        }
        if(sellGlass == 3 && glassNum > 0) {
            return 2;
        }
        if(sellWood == 2) {
            if(sellMetal == 1) {
                if(random > 0 && woodNum > 0) {
                    return 0;
                } else {
                    return 1;
                }
            } else {//sellGlass == 1
                if(random > 0 && woodNum > 0) {
                    return 0;
                } else {
                    return 2;
                }
            }
        }
        if(sellMetal == 2) {
            if(sellWood == 1) {
                if(random > 0 && metalNum > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else {//sellMetal == 1
                if(random > 0 && metalNum > 0) {
                    return 1;
                } else {
                    return 2;
                }
            }
        } 
        if(sellGlass == 2) {
            if(sellWood == 1) {
                if(random > 0 && glassNum > 0) {
                    return 2;
                } else {
                    return 0;
                }
            } else {//sellMetal == 1
                if(random > 0 && glassNum > 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }
    
    //logic to decide what to sell a certian item for
    public int howToPrice(int itemID) {
        int basePrice = Market.getAveragePrice(itemID);
        double discount = 0;
        
        int woodNum = person.getInventory().getInventory().get(0);
        int metalNum = person.getInventory().getInventory().get(1);
        int glassNum = person.getInventory().getInventory().get(2);
        
        if((woodNum >= metalNum) && (woodNum >= glassNum)) {
            if(itemID == 0) {
                discount += .05;
            }
        } else if((metalNum >= woodNum) && (metalNum >= glassNum)) {
            if(itemID == 1) {
                discount += .05;
            }
        } else {
            if(itemID == 2) {
                discount += .05;
            }
        }
        discount += this.getSkillMult(itemID);
        
        int finalPrice = (int)(basePrice - (basePrice * discount));
        int random = (int)(Math.random() * 6) - 3;
        
        return finalPrice + random;
        
        
        
    }
    
    //logic to decide what to buy
    public int whatToBuy() {
        int woodNum = person.getInventory().getInventory().get(0);
        int metalNum = person.getInventory().getInventory().get(1);
        int glassNum = person.getInventory().getInventory().get(2);
        
        if(woodNum <= 0) {
            return 0;
        }
        if(metalNum <= 0) {
            return 1;
        }
        if(glassNum <= 0) {
            return 2;
        }
        
        int buyWood = 0;
        int buyMetal = 0;
        int buyGlass = 0;
        
        if((woodNum >= metalNum) && (woodNum >= glassNum)) {
            buyWood++;
        } else if((metalNum >= woodNum) && (metalNum >= glassNum)) {
            buyMetal++;
        } else {
            buyGlass++;
        }
        
        if((woodSkill >= metalSkill) && (woodSkill >= glassSkill)) {
            buyWood++;
        } else if((metalSkill >= woodSkill) && (metalSkill >= glassSkill)) {
            buyMetal++;
        } else {
            buyGlass++;
        }
        
        if(buyWood > buyMetal && buyWood > buyGlass) {
            return 0;
        }
        if(buyMetal > buyWood && buyMetal > buyGlass) {
            return 1;
        }
        if(buyGlass > buyWood && buyGlass > buyMetal) {
            return 2;
        }
        if(buyWood == 0) {
            int random = (int)Math.random();
            if(random > 0){
                return 1;
            } else {
                return 2;
            }
        }
        if(buyMetal == 0) {
            int random = (int)Math.random();
            if(random > 0){
                return 0;
            } else {
                return 2;
            }
        }
        if(buyGlass == 0) {
            int random = (int)Math.random();
            if(random > 0){
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }
    //logic to decide the most a person would pay for an item
    public int setMaxPrice(int itemID) {
        int basePrice = Market.getAveragePrice(itemID);
        double discount = 0;
        
        int woodNum = person.getInventory().getInventory().get(0);
        int metalNum = person.getInventory().getInventory().get(1);
        int glassNum = person.getInventory().getInventory().get(2);
        
        if((woodNum >= metalNum) && (woodNum >= glassNum)) {
            if(itemID == 0) {
                discount += .05;
            }
        } else if((metalNum >= woodNum) && (metalNum >= glassNum)) {
            if(itemID == 1) {
                discount += .05;
            }
        } else {
            if(itemID == 2) {
                discount += .05;
            }
        }
        
        int random = (int)(Math.random() * 6) - 3;
        discount -= this.getSkillMult(itemID);
        
        int finalPrice = (int)(basePrice - (basePrice * discount)) + 5 + random;
        if(person.getMoney() - finalPrice <= 0) {
            finalPrice = 0;
        }
        return finalPrice;
    }
}
