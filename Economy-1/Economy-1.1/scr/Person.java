import java.util.*;
public class Person {
    private static ArrayList<Person> people = new ArrayList<Person>(); //list of all people, dead & alive

    private int age; //age in days (3 cycles = 1 day)
    private int isAlive; //if alive, isAlive == 1; if dead, isAlive == 0
    private int money; //wallet
    private String name; //name
    private int id; // ID, equal to population + 1
    private Inventory inventory; //Holds items
    
    private Brain brain; //makes decisions
    
    public Person(String name) {
        
        
        this.age = 0;
        this.isAlive = 1;
        this.money = 0;
        this.name = name;
        this.id = people.size();
        this.inventory = new Inventory();
        
        this.brain = new Brain(this);
        
        people.add(this);
        
    }
    //GETTERS & SETTERS --------------------------------------------
    public int getAge() {
        return this.age;
    }
    
    public int isAlive() {
        return isAlive;
    }
    
    public int getMoney() {
        return this.money;
    }
    public void changeMoney(int amount) {
        this.money += amount;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    public void printInventory() {
        System.out.print(this.name + " has " + inventory);
    }
    
    public Brain getBrain(){
        return this.brain;
    }
    
    public void die() {
        this.isAlive = 0;

    }
    //-------------------------------------------------------------
    
    
    
    
    public void consume(int itemID) {
        if(itemID == 0) {
            this.inventory.addWood(-1);
        } else if(itemID == 1) {
            this.inventory.addMetal(-1);
        }else if(itemID == 2) {
            this.inventory.addGlass(-1);
        }
        brain.addSkill(itemID);
    }
    
    
    //STAGES ------------------------------------------------------------------
    public void stageOne() {//consumption stage
        if(brain.whatToConsume() != -1) {//person dies if they have nothing to consume
            this.consume(brain.whatToConsume());
        } else {
            this.die();
        }
    }
    public void stageTwo() {//selling stage
        int itemToSell = brain.whatToSell();
        int priceToSell = brain.howToPrice(itemToSell);
        Market.sell(this, itemToSell, priceToSell);
    }
    public void stageThree() {//buying stage
        int itemToBuy = brain.whatToBuy();
        int maxPrice = brain.setMaxPrice(itemToBuy);
        Market.buy(this, itemToBuy, maxPrice);
        age++;
    }
    //-------------------------------------------------------------------------
    
    
    
    
    
    public String toString() {
        String personAsString = name + " is " + age + " days old and has $" + money + ". " + this.name + " has " + inventory + ".";
        return personAsString;
    }
    
    //STATIC METHODS---------------------------------------------------
    
    public static void createPerson(String name) {
        Person person = new Person(name);
    }
    public static Person getPerson(int id) {
        return Person.people.get(id);
    }
    public static Person getPerson(String name) {
        for(int i = 0; i < Person.people.size(); i++) {
            if(name.equals(Person.people.get(i).getName())) {
                return Person.people.get(i);
            }
        }
        return null;
    }
    
    public static ArrayList<Person> getPeople() {
        return Person.people;
    }
    public static void printPeople() {
        System.out.println("-----------------------------------------");
        for(int i = 0; i < Person.people.size(); i++) {
            if(Person.people.get(i).isAlive() == 1) {
                System.out.println(Person.people.get(i));
                System.out.println("-----------------------------------------");
            }
        }
    }
    public static void logPeople() {
        String people = "";
        people += "\n-----------------------------------------";
        for(int i = 0; i < Person.people.size(); i++) {
            if(Person.people.get(i).isAlive() == 1) {
                people += "\n" + Person.people.get(i);
                people += "\n-----------------------------------------";
            }
        }
        Economy.log(people);
    }
}
