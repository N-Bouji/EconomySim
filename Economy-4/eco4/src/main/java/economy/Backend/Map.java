package economy.Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Map {



    // height terrain cut-offs:

    

 
    private final Property[][] properties;
    private final int size;

    public Map(int size) {
        this.properties = new Property[size][size];
        this.size = size;
    }


    /**
     * 
     * @return VBox containing HBoxes containing Rectangle representations of all properties on this map
     * 
     */
    public VBox getMapContainer(int pixles) {

        HBox[] rows = new HBox[size];

        VBox cols = new VBox();

        int tileSize = (int)(pixles / size);

        Property.initTileImages(tileSize);

        for (int y = 0; y < size; y++) {

            rows[y] = new HBox();
            for (int x = 0; x < size; x++) {
                Button propertyTile = Utility.newImageButton(Property.getTileImage(properties[x][y].getFlatness()), tileSize, tileSize);
                final int curX = x;
                final int curY = y;
                propertyTile.setOnAction((ActionEvent event) -> {
                    
                    Property curProperty = properties[curX][curY];
                    System.out.println("Flatness: " + curProperty.getFlatness() + ", Resources: " + curProperty.getResourceDensity());

                });
                
                rows[y].getChildren().addAll(propertyTile);
            }
            cols.getChildren().add(rows[y]);
        }

        return cols;
    }

    /**
     * Generates a text representation of the map.
     * 
     * Format:
     * :Map[
     * {mapSize}
     * ownerID, flattness, resourceDensity~ownerID, flattness, resourceDensity
     * 
     * It is expected that mapSize will be equal to the (number of tildas - 1)
     * Each map should have 'size' lines representing them
     * 
     */
    public String getMapSavePacket() {
        String map = ":MAP [\n" + "{" + size + "}\n";

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size - 1; x++) {
                map += properties[x][y].getPropertyAsString() + "~";
            }
            map += properties[size - 1][y].getPropertyAsString();
            map += "\n";
        }
        map += "]";

        return map;
    }

    public static Map generateNewMap(int size, int noiseType) {

        Map nuMap = new Map(size);
        
        int[][] resourceDensity = Noise.generate2DNoiseMap(1, size); // still need to make a noise type for this

        int[][] flattness = Noise.generate2DNoiseMap(noiseType, size);

        for (int y = 0; y < size; y++) {

            for (int x = 0; x < size; x++) {
                nuMap.properties[x][y] = new Property(flattness[x][y], resourceDensity[x][y]);
            }
        }


        return nuMap;
    }

    public static Map getMapFromFile(File saveFile) {

        Map loadedMap = null;

         try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));

            String line = reader.readLine();
            while (!line.equals(":MAP [")) {
                
                line = reader.readLine();
            }
            line = reader.readLine();
            
            int mapSize = Integer.parseInt(line.substring(1, (line.length() - 1)));

            loadedMap = new Map(mapSize);

            for (int y = 0; y < mapSize; y++) {

                line = reader.readLine();
                String[] row = line.split("~");

                for (int x = 0; x < row.length; x++) {
                    loadedMap.properties[x][y] = Property.propertyFromString(row[x]);
                }
            }
            reader.close();


            

        } catch (IOException ex) {

        }
    
        
        return loadedMap;
    } 

    
}