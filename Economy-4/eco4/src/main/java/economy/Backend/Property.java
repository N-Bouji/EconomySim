package economy.Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Property {
    
    private int flattness; //measure of buildability (0 - 100)
    private int resourceDensity; // (0 - 100)

    private int baseValue;

    private String propertyImageAddress;
    

    private Gen owner = null;

    public Property(int flattness, int resourceDensity) {
        this.flattness = flattness;
        this.resourceDensity = resourceDensity;
        this.baseValue = flattness * resourceDensity * 10;
    }

    public Property(int flattness, int resourceDensity, Gen owner) {
        this.flattness = flattness;
        this.resourceDensity = resourceDensity;
        this.baseValue = flattness * resourceDensity * 10;

        this.owner = owner;
    }

    public int getFlatness() {
        return this.flattness;
    }

    public int getResourceDensity() {
        return this.resourceDensity;
    }

    public int getAppraisal() {
        int estimatedValue = baseValue;

        estimatedValue += (int)((Math.random() - .5) * (estimatedValue / 10));

        return estimatedValue;
    }

    public void setOwner(Gen nuOwner) {
        this.owner = nuOwner;
    }
    public Gen getOwner() {
        return this.owner;
    }


    /**
     * Format: [genIDNum, flattness, resourceDensity]
     * 
     * @return string representation of the property
    */
    public String getPropertyAsString() {
        String property = "";

        if (owner != null) {
            property += owner.getIdNum() + ", ";
        } else {
            property += "-1, ";
        }

        property += flattness + ", " + resourceDensity;

        return property;
    }

    public static Property propertyFromString(String asString) {

        String[] propArr = asString.split(", ");

        int ownerID = Integer.parseInt(propArr[0]);

        int flattness = Integer.parseInt(propArr[1]);

        int resourceDensity = Integer.parseInt(propArr[2]);


        if (ownerID == -1) {
            return new Property(flattness, resourceDensity);
        } else {
            return new Property(flattness, resourceDensity, Gen.genFromID(ownerID));
        }
    }

    /**
     * Water Deep - #002e47
     * Water Mid - #03355c
     * Water Shallow - #043e6c
     * 
     * Wet Sand - #605c20
     * Dry Sand - #7c761c
     * 
     * Dense Forest - #1e3b04
     * Sparce Forest - #1b460e
     * 
     * Low Plains - #246131
     * Mid Plains - #205c2e
     * High Plains - #276536
     * 
     * 
     */


    private static final int DEEP_WATER = 11; // deep water is 0 - 10
    private static final int MID_WATER = 15; // mid water is 11 - 14
    private static final int SHALLOW_WATER = 18; // shallow water is 15 - 17
    private static final int WET_SAND = 22; // wet sand is 18 - 21
    private static final int DRY_SAND = 25; // dry sand is 22 - 24;
    private static final int DENSE_FOREST = 35; // dense forest is 25 - 34
    private static final int SPARCE_FOREST = 45; // sparce forest is 35 - 49
    private static final int LOW_PLAINS = 55; // low plains is 45 - 54
    private static final int MID_PLAINS = 65; // mid plains is 55 - 64
    private static final int HIGH_PLAINS = 75; // high plains is 65 - 74
    private static final int MOUNT_BASE = 82; // mountain base is 75 - 81
    private static final int MOUNT_MID_BASE = 88; // mountain mid base is 82 - 87
    private static final int MOUNT_MID_PEAK = 94; // mountain mid peak is 88 - 93
    private static final int MOUNT_PEAK = 100; // mountain peak is 94 - 100
    
    
    public static Image getTileImage(int flattness) {

        if (flattness < DEEP_WATER) {
            return deepWaterImage;
        } else if (flattness < MID_WATER) {
            return midWaterImage;
        } else if (flattness < SHALLOW_WATER) {
            return shallowWaterImage;
        } else if (flattness < WET_SAND) {
            return wetSandImage;
        } else if (flattness < DRY_SAND) {
            return drySandImage;
        } else if (flattness < DENSE_FOREST) {
            return denseForestImage;
        } else if (flattness < SPARCE_FOREST) {
            return sparceForestImage;
        } else if (flattness < LOW_PLAINS) {
            return lowPlainsImage;
        } else if (flattness < MID_PLAINS) {
            return midPlainsImage;
        } else if (flattness < HIGH_PLAINS) {
            return highPlainsImage;
        } else if (flattness < MOUNT_BASE) {
            return baseMountainImage;
        } else if (flattness < MOUNT_MID_BASE) {
            return midBaseMountainImage;
        } else if (flattness < MOUNT_MID_PEAK) {
            return midPeakMountainImage;
        } else { // MOUNTAIN_PEAK
            return peakMountainImage;
        }
    }

    public static Image deepWaterImage;
    public static Image midWaterImage;
    public static Image shallowWaterImage;
    public static Image wetSandImage;
    public static Image drySandImage;
    public static Image denseForestImage;
    public static Image sparceForestImage;
    public static Image lowPlainsImage;
    public static Image midPlainsImage;
    public static Image highPlainsImage;
    public static Image baseMountainImage;
    public static Image midBaseMountainImage;
    public static Image midPeakMountainImage;
    public static Image peakMountainImage;

    public static void initTileImages(int size) {
        String imageAddress = "src/main/resources/Tile_Images/";
        try {
            deepWaterImage = new Image (new FileInputStream(imageAddress + "WaterDeep.png"), size, size, false, false);
            midWaterImage = new Image (new FileInputStream(imageAddress + "WaterMid.png"), size, size, false, false);
            shallowWaterImage = new Image (new FileInputStream(imageAddress + "WaterShallow.png"), size, size, false, false);

            wetSandImage = new Image (new FileInputStream(imageAddress + "SandWet.png"), size, size, false, false);
            drySandImage = new Image (new FileInputStream(imageAddress + "SandDry.png"), size, size, false, false);

            denseForestImage = new Image (new FileInputStream(imageAddress + "ForestDense.png"), size, size, false, false);
            sparceForestImage = new Image (new FileInputStream(imageAddress + "ForestSparce.png"), size, size, false, false);

            lowPlainsImage = new Image (new FileInputStream(imageAddress + "PlainsLow.png"), size, size, false, false);
            midPlainsImage = new Image (new FileInputStream(imageAddress + "PlainsMid.png"), size, size, false, false);
            highPlainsImage = new Image (new FileInputStream(imageAddress + "PlainsHigh.png"), size, size, false, false);

            baseMountainImage = new Image (new FileInputStream(imageAddress + "MountainBase.png"), size, size, false, false);
            midBaseMountainImage = new Image (new FileInputStream(imageAddress + "MountainMidBase.png"), size, size, false, false);
            midPeakMountainImage = new Image (new FileInputStream(imageAddress + "MountainMidPeak.png"), size, size, false, false);
            peakMountainImage = new Image (new FileInputStream(imageAddress + "MountainPeak.png"), size, size, false, false);
        } catch (FileNotFoundException e) {
            System.out.println("One or more of the tilePictures are gone or corrupt.");
        }
    }
    
}
