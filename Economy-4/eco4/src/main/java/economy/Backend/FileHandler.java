package economy.Backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    
    public static final File GAME_SAVES_FOLDER = new File("src/main/java/economy/Backend/Saves"); // Folder the simulation save files are expected to be in.

    public static File[] saveFiles; // Holds all of the files found in the folder "Saves"

    public static File currentSave; // Stores the last file loaded. For saving purposes.

    public static ArrayList<String> saveNames = new ArrayList<>(); // Stores all of the file names in the "Saves" folder.



    /**
     * Updates the array of files and arrayList of file names based on the contense of the GAME_SAVES_FOLDER.
     */
    public static void fileInit() {
        saveNames.clear(); // start by clearing the current list of saveNames

        try {
            
            saveFiles = GAME_SAVES_FOLDER.listFiles(); // array of files given by File class
            

            for (File saveFile : saveFiles) {
                String path = saveFile.toString();
                int startInd = path.indexOf("Saves") + 6;

                String fileName = path.substring(startInd, path.length() - 4);
                saveNames.add(fileName);
            }
            


        } catch (Exception ex) {
            System.out.print("No Saves");
        }
    }

    /**
     *  Loads a save from a formatted file.
     * 
     * @param file File to load save from.
     */
    public static void loadSave(File file) {
        currentSave = file;

        Simulation.getSimFromFile(file);
    }

    /**
     *  Makes a new save file and updates the list of save files.
     * 
     * @param fileName Name of the new save file.
     */
    public static File newSaveFile(String fileName) {
        String path = "src/main/java/economy/Backend/Saves/" + fileName + ".txt";

        File file = new File(path);
        try {
            file.createNewFile();
            FileHandler.fileInit();
        } catch (IOException e) {
            System.out.println("File not created.");
        }
        currentSave = file;
        return file;
    }


    /**
     * Saves every game object with a "getSavePacket" method.
     * 
     * Saves to the file associated with the currenent simulation instance 
     * 
     * Currently saves:
     *     Gen information
     *     Map Information
     */
    public static void saveCurrent(Simulation currentSimulation) {

        try (FileWriter writer = new FileWriter(currentSave)) {

            writer.write(Simulation.getVersionInfo());

            writer.write(Gen.getGenSavePacket());
                
            writer.write(currentSimulation.getMap().getMapSavePacket());

            writer.close();
        } catch (Exception e) {
            System.out.println("Did you mess with the file structure?");
            e.printStackTrace();
        }
        
    }


    /**
     * 
     * 
     * @param saveName Save name to be tested
     * @return A valid file name in the format of: "Save_[numSaves]"
     */
    public static String checkSaveName(String saveName) {

        boolean isBlank = false;
        if (saveName.length() == 0) {
            saveName = "New_Save";
            isBlank = true;
        }


        int numDuplicates = 0;
        for (String otherSave : saveNames) {

            if (saveName.length() <= otherSave.length()) {
                if (otherSave.substring(0, saveName.length()).equals(saveName)) {
                    numDuplicates++;
                }
            }
           
        }

        if (numDuplicates > 0) {
            
            return saveName + "_" + numDuplicates;
        } else {
            if (isBlank) {
                return "New_Save_0";
            }
        }

        return saveName;
    }

    public static File getSaveFile(String fileName) {

        for (int i = 0; i < saveNames.size(); i++) {
            if (fileName.equals(saveNames.get(i))) {
                return saveFiles[i];
            }
        }
        System.out.println(fileName + " Can't be found!");
        return null;
    }
}
