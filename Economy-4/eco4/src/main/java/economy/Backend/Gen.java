package economy.Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Gen {
    
    private String name;
    private int age;
    private boolean isMan;
    private int money;
    private int idNum;



    private static final int INITIAL_MONEY_BASE = 10000;
    private static final int INITIAL_MONEY_VARIANCE = 9000;

    public static ArrayList<Gen> allGens = new ArrayList<>();

    public Gen() {

        this.age = 18;

        Random random = new Random();
        this.isMan = random.nextBoolean();

        this.name = NameGenerator.newName(isMan);

        this.money = 0;

        idNum = allGens.size();

        allGens.add(this);

    }

    public Gen(String name, int age, boolean isMan, int money, int idNum) {
        this.name = name;
        this.age = age;
        this.isMan = isMan;
        this.money = money;
        this.idNum = idNum;

        allGens.add(this);
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
    public boolean isMan() {
        return isMan;
    }

    public int getMoney() {
        return this.money;
    }
    public void addMoney(int amount) {
        this.money += amount;
    }

    public int getIdNum() {
        return idNum;
    }


    public static void generateInitialPopulation(int numGens) {
        allGens.clear();
        for (int i = 0; i < numGens; i++) {
            Gen nuGen = new Gen();
            int startingMoney = INITIAL_MONEY_BASE + (int)(Math.random() * INITIAL_MONEY_VARIANCE * 2) - INITIAL_MONEY_VARIANCE;
            nuGen.addMoney(startingMoney);
        }
    }

    public static void initalizeGensFromFile(File file) {
        allGens.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while (!reader.readLine().equals(":GENS [")) {
            }

            line = reader.readLine();
            
            while (!line.equals("]")) { 
                
                if (line.substring(line.length() - 2).equals(", ")) {
                    line = line.substring(3, line.length() - 3);
                } else {
                    line = line.substring(3, line.length() - 1);
                }

                genFromString(line); //Modify genFromString when new attributes are added

                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
        }
    }

    public static String getGenSavePacket() {
        String genSavePacket = ":GENS [\n";
        for (int i = 0; i < allGens.size() - 1; i++) {
            genSavePacket += "  {" + allGens.get(i) + "}, \n";
        }
        genSavePacket += "  {" + allGens.get(allGens.size() - 1) + "}\n]\n";

        return genSavePacket;
    }

    public static Gen genFromID(int ID) {
        return allGens.get(ID);
    }

    public static Gen genFromString(String genString) {

        String[] desc = genString.split(", ");

        String name = desc[0];

        int age = Integer.parseInt(desc[1]);

        boolean isMan = Boolean.parseBoolean(desc[2].strip());

        int money = Integer.parseInt(desc[3]);

        int idNum = Integer.parseInt(desc[4]);
        

        return new Gen(name, age, isMan, money, idNum);

    }

    @Override
    public String toString() {
        String genStr = this.name + ", " + this.age + ", " + this.isMan + ", " + this.money + ", " + this.idNum;

        return genStr;
    }
}
