import java.util.*;
import java.io.*;
public class Economy
{
    //SIMULATION SETTINGS ----------------
    public static final int DAYS_TO_SIMULATE = 100;
    
    public static final int NUMBER_OF_PEOPLE = 20;
    
    public static final int NUMBER_OF_STARTING_ITEMS = 60;
    public static final int NUMBER_OF_ITEMS_PER_DAY = 15;
    
    public static final int AMOUNT_OF_STARTING_MONEY = 500;
    
    public static final boolean SHOW_TRANSACTIONS = false;
    public static final boolean SHOW_MARKET = false;
    
    public static final int DAYS_BETWEEN_PRINT = 10;

    //------------------------------------
    
    //LOG SETTINGS -----------------------
    public static final boolean LOG_TRANSACTIONS = false;
    public static final boolean LOG_PEOPLE = true;
    public static final boolean LOG_MARKET = true;

    //------------------------------------
    
    public static int WOOD_ID = 0;
    public static int METAL_ID = 1;
    public static int GLASS_ID = 2;

    public static String saveFileName;

    public static int day = 0;
    
    public static void main(String[] args)
    {
        //Stage 1 - Citizens make what they can with what they have
        //Stage 2 - Citizens put items on the market
        //Stage 3 - Citizens buy things from the market
        
        
        Economy.birthPeople(NUMBER_OF_PEOPLE);
        
        Economy.randomlyDistributeMoney(AMOUNT_OF_STARTING_MONEY);
        randomlyDistributeItems(NUMBER_OF_STARTING_ITEMS);
        
        
        
        //System.out.println("-   DAY 1   -");
        
        //nextDay();
        
        //Person.printPeople();
        
        //System.out.println("-   DAY 2   -");
        
        //nextDay();
        
        //Person.printPeople();
        Economy.createSave("Save");
        for(int i = 0; i <= DAYS_TO_SIMULATE; i++){
            if(i % DAYS_BETWEEN_PRINT == 0) {
                System.out.println("-   Day " + day + "   -");
                System.out.println("-   GDP per CAP: " + getGDP() + "   -");
                System.out.println("-   AVG Wood Price: " + Market.getAveragePrice(0) + "   -");
                System.out.println("-   AVG Metal Price: " + Market.getAveragePrice(1) + "   -");
                System.out.println("-   AVE Glass Price: " + Market.getAveragePrice(2) + "   -");
                Person.printPeople();
            }
            if(Economy.LOG_PEOPLE) {
                Person.logPeople();
            }
            if(Economy.SHOW_MARKET) {
                nextDayPrintMarket();
            } else {
                nextDay();
            }
            
             randomlyDistributeItems(NUMBER_OF_ITEMS_PER_DAY);
            
        }
    }
    
    public Economy() {
        
    }
    
    public static void nextDay() {
        ArrayList<Person> people = Person.getPeople();
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).isAlive() == 1) {
                people.get(i).stageOne();
            }
        }
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).isAlive() == 1) {
                people.get(i).stageTwo();
            }
        }
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).isAlive() == 1) {
                people.get(i).stageThree();
            }
        }
        if(Economy.LOG_MARKET) {
            Market.logMarket();
        }
        Economy.day++;
        Market.clearMarket();
    }
    public static void nextDayPrintMarket() {
        ArrayList<Person> people = Person.getPeople();
        for(int i = 0; i < people.size(); i++){
            people.get(i).stageOne();
        }
        for(int i = 0; i < people.size(); i++){
            people.get(i).stageTwo();
        }
        Market.printMarket();
        for(int i = 0; i < people.size(); i++){
            people.get(i).stageThree();
        }
        if(Economy.LOG_MARKET) {
            Market.logMarket();
        }
        Economy.day++;
        Market.clearMarket();
    }
    
    public static void birthPerson() {
        Person.createPerson(NameGenerator.newName());
    }
    
    public static void birthPeople(int population) {
        for(int i = 0; i < population; i++) {
            Economy.birthPerson();
        }
    }
    
    public static void randomlyDistributeMoney(int amount) {
        for(int i = 0; i < amount; i++) {
            ArrayList<Person> people = Person.getPeople();
            int randomNum = (int)(Math.random() * people.size());
            people.get(randomNum).changeMoney(1);
            
        }
    }
    
    public static void randomlyDistributeItems(int amount) {
        ArrayList<Person> people = Person.getPeople();
        for(int i = 0; i < amount; i++) {
            int randomPerson = (int)(Math.random() * people.size());
            int randomItem = (int)(Math.random() * 3);
            people.get(randomPerson).getInventory().addItem(randomItem, 1);
        }
    }
    public static void randomlyDistributeItems(int amount, String item) {
        for(int i = 0; i < amount; i++) {
            int randomNum = (int)(Math.random() * Person.getPeople().size());
        }
    }
    
    public static int getGDP() {
        int gdp = 0;
        int alive = 0;
        for(int i = 0; i < Person.getPeople().size(); i++) {
            Person person = Person.getPeople().get(i);
            if(Person.getPeople().get(i).isAlive() == 1) {
                alive++;
                gdp += person.getMoney();
                
                gdp += person.getInventory().getInventory().get(0) * Market.getAveragePrice(0);
                gdp += person.getInventory().getInventory().get(1) * Market.getAveragePrice(1);
                gdp += person.getInventory().getInventory().get(2) * Market.getAveragePrice(2);
            }
        }
        
        return (int)(gdp/alive);
    }
    
    public static void createSave(String saveName) {
        
        boolean fileCreated = false;
        int tries = 0;
        //String fileSeperator = System.getProperty(File.separator);
        while (!fileCreated) {
            
            if(tries > 0) {
                Economy.saveFileName = saveName + "-" + tries + ".txt";
            } else {
                Economy.saveFileName = saveName + ".txt";
            }
            try {
                File save = new File("Economy-1.1\\res\\saves\\" + Economy.saveFileName);
                if (save.createNewFile() == false) {
                    tries += 1;
                } else {
                    fileCreated = true;
                }
            } catch (IOException e) {
                System.out.println("File couldn't be created");
                fileCreated = true;
            }
        }
    }
    public static void log(String log) {
        try {
            FileWriter myWriter = new FileWriter("Economy-1.1\\res\\saves\\" + Economy.saveFileName, true);
            myWriter.write(log);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("You must create a save first!");
        }
    }
}