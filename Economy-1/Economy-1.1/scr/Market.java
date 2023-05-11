import java.util.*;
public class Market {
    /**
     * -Keeps an array of all the items that are being sold
     * 
     * 
    **/
    public static ArrayList<ArrayList> forSale = new ArrayList<ArrayList>();//Person seller, int item, int price
    
    public static ArrayList<ArrayList<Integer>> transactions = new ArrayList<ArrayList<Integer>>(); //int item, int price
    
    
    public static void clearMarket() {
        forSale.clear();
    }
    
    public static void buy(Person buyer, int itemID, int maxPrice) {
        int cheapestIndex;
        for(int i = 0; i < forSale.size(); i++) {
            ArrayList<Object> listing = forSale.get(i);
            int type = (int)listing.get(1);
            int price = (int)listing.get(2);
            if(type == itemID && (price <= maxPrice)) {
                buyer.getInventory().addItem(itemID, 1);
                
                Person seller = (Person)listing.get(0);
                seller.changeMoney((int)(price*(1 + seller.getBrain().getSkillMult(itemID))));//increases amount seller recieves by skillMult%
                seller.getInventory().addItem(itemID, -1);
                buyer.changeMoney(-(int)(price - (price * buyer.getBrain().getSkillMult(itemID))));//decreases amount buyer pays by skillMult%
                seller.getBrain().addSkill(itemID);
                transactions.add(new ArrayList<Integer>(Arrays.asList(itemID, price)));
                forSale.remove(i);
                if(Economy.SHOW_TRANSACTIONS) {
                    System.out.println(buyer.getName() + " bought " + Inventory.getItemName(itemID) + " for $" + price + ", from " + seller.getName());
                }
                break;
            }
        }
    }
    
    public static void sell(Person seller, int itemID, int price) {
        if(seller.getInventory().getItem(itemID) > 0 && (itemID != -1)) {
            ArrayList<Object> info = new ArrayList<Object>();
            info.add(seller);
            info.add(itemID);
            info.add(price);
            forSale.add(info);
            
            
        }
    }
    
    public static int getScarsity(int itemID) {
        return 1;
    }
    
    public static int getAveragePrice(int itemID) {
        int totalSpent = 0;
        int totalBought =  0;
        for(int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).get(0) == itemID) {
                totalSpent += transactions.get(i).get(1);
                totalBought++;
            }
        }
        if(totalBought > 0) {
            return (int)(totalSpent/totalBought);
        } else {
            return 10;
        }
    }
    
    public static void printMarket() {
        for(int i = 0; i < forSale.size(); i++) {
            Person seller = (Person)forSale.get(i).get(0);
            int item = (int)forSale.get(i).get(1);
            int price = (int)forSale.get(i).get(2);
            
            System.out.println(seller.getName() + " is selling " + Inventory.getItemName(item) + " for $" + price + ".");
        }
    }

    public static void logMarket() {
        String market = "\n\n----- Goods For Sale: " + forSale.size() + " -----";
        for(int i = 0; i < forSale.size(); i++) {
            Person seller = (Person)forSale.get(i).get(0);
            int item = (int)forSale.get(i).get(1);
            int price = (int)forSale.get(i).get(2);
            market += "\n" + seller.getName() + " is selling " + Inventory.getItemName(item) + " for $" + price + ".";
            
        }
        Economy.log(market);
    }
}
