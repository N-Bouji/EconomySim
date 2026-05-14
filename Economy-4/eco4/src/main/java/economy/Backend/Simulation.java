package economy.Backend;
import java.io.File;
import java.util.ArrayList;


public class Simulation {

    public static final String VERSION = "Economy-4.0.1";

    public static ArrayList<String> saveNames = new ArrayList<>();

    public static File[] saveFiles;

    private static Simulation currentSimulation;


    public String saveName;

    public File saveFile;

    private Map map;

    public ArrayList<Gen> gens;



    public Simulation (String saveName, int numGens, int mapSize, int noiseType) {
        this.saveName = saveName;

        Gen.generateInitialPopulation(numGens);

        this.gens = Gen.allGens;

        this.map = Map.generateNewMap(mapSize, noiseType);

    }

    public Simulation(File saveFile, Map map, ArrayList<Gen> gens) {
        this.saveFile = saveFile;
        this.map = map;
        this.gens = gens;
    }


    /**
     * Initalizes everything needed for the simulation
     */
    

    public void setMap(Map newMap) {
        this.map = newMap;
    }

    public Map getMap() {
        return this.map;
    }

    public void makeSimFile() {
        FileHandler.newSaveFile(this.saveName);
    }

    public static void setCurSim (Simulation curSim) {
        Simulation.currentSimulation = curSim;
    }

    public static Simulation getCurSim() {
        return Simulation.currentSimulation;
    }

    public static void init() {
        FileHandler.fileInit();
        
    }

    public static void getSimFromFile(File saveFile) {


        Gen.initalizeGensFromFile(saveFile);
        ArrayList<Gen> loadedGens = Gen.allGens;

        Map loadedMap = Map.getMapFromFile(saveFile);

        Simulation.setCurSim(new Simulation(saveFile, loadedMap, loadedGens));
    }

    public static String getVersionInfo() {
        String versionPacket = ":VERSION [\n";

        versionPacket += Simulation.VERSION + "\n]\n";



        return versionPacket;
    }
}
