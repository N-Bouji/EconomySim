package economy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import economy.Backend.FileHandler;
import economy.Backend.Map;
import economy.Backend.Simulation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Stage stage;

    private static Scene mainMenuScene;


    private static int screenWidth;

    @Override
    public void start(Stage primaryStage) throws IOException {

        screenWidth = 700;


        stage = primaryStage;

        Scene mainMenu = initMainMenu();
        mainMenuScene = mainMenu;
       
        primaryStage.setScene(mainMenuScene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Economy");
        primaryStage.show();

        
    }

    public static Scene getGameScene() {
        
        VBox gameScreenContainer = new VBox();


        Map currentMap = Simulation.getCurSim().getMap();
        
        

        Button mainMenuButton = new Button("Main Menu");

        mainMenuButton.setOnAction((ActionEvent e) -> {
            stage.setScene(initMainMenu());
        });

        Button saveGame = new Button("Save Game");

        gameScreenContainer.getChildren().addAll(currentMap.getMapContainer(screenWidth), mainMenuButton);

        Scene gameScene = new Scene(gameScreenContainer);

        return gameScene;
    }



    public static Scene initMainMenu() {
        VBox mainMenuShell = new VBox();

        Button loadSimulation = new Button("Load Simulation");

        loadSimulation.setOnAction((ActionEvent event) -> {

            stage.setScene(initSaveMenu());
        });
        mainMenuShell.getChildren().add(loadSimulation);

        Button newSimulation = new Button("New Simulation");
        newSimulation.setOnAction((ActionEvent event) -> {
            stage.setScene(initNewSimulation());
        });
        mainMenuShell.getChildren().add(newSimulation);


        Button settings = new Button("Settings");
        mainMenuShell.getChildren().add(settings);
        
        Button quit = new Button("Quit");
        quit.setOnAction((ActionEvent event) -> {
            stage.close();
        });

        mainMenuShell.getChildren().add(quit);

        return new Scene(mainMenuShell, screenWidth, screenWidth);
    }



    private static int mapSize = 150;
    private static int newMapNoiseType = 9;
    /**
     * 
     * @return Scene containing map select screen
     */
    public static Scene getMapSelectorScene() {

        VBox mapSelectCont = new VBox();


        if (Simulation.getCurSim().getMap() == null) {

            Simulation.getCurSim().setMap(Map.generateNewMap(mapSize, newMapNoiseType));
        }

        VBox mapContainer = Simulation.getCurSim().getMap().getMapContainer(screenWidth);

        TextField mapSizeField = new TextField();
        Text mapSizeText = new Text("Map Size: ");

        HBox mapSizeContainer = new HBox(mapSizeText, mapSizeField);

        TextField noiseTypeField = new TextField();
        Text noiseTypeText = new Text("Noise Type: ");

        HBox noiseTypeContainer = new HBox(noiseTypeText, noiseTypeField);

        // BUTTONS
        Button newMapButton = new Button("New Map");
        newMapButton.setOnAction((ActionEvent nuEvent) -> {

            try {
                mapSize = Integer.parseInt(mapSizeField.getText());
            } catch (Exception e) {
                
            }

            try {
                newMapNoiseType = Integer.parseInt(noiseTypeField.getText());
            } catch (Exception e) {

            }

            Simulation.getCurSim().setMap(Map.generateNewMap(mapSize, newMapNoiseType));

            stage.setScene(getMapSelectorScene());
        });

        Button chooseMapButton = new Button("Choose Map");
        chooseMapButton.setOnAction((ActionEvent nuEvent) -> {
            Simulation.getCurSim().makeSimFile();
            FileHandler.saveCurrent(Simulation.getCurSim());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction((ActionEvent goBack) -> {
            stage.setScene(initNewSimulation());
        });

        // End of buttons

        

        mapSelectCont.getChildren().addAll(mapContainer, newMapButton, chooseMapButton, mapSizeContainer, noiseTypeContainer, backButton);
        Scene mapSelectScene = new Scene(mapSelectCont);

        return mapSelectScene;
    }

    public static Scene initSaveMenu() {

        FileHandler.fileInit();

        VBox saveMenuShell = new VBox();

        ArrayList<String> saveNames = FileHandler.saveNames;

        File[] saveFiles = FileHandler.saveFiles;

        ArrayList<Button> loadSaveButtons = new ArrayList<>();

        for (int i = 0; i < saveNames.size(); i++) {
            Button sb = new Button("Load: " + saveNames.get(i));

            sb.setOnAction((ActionEvent event) -> {

                String curSaveName = sb.getText().substring(6);
                FileHandler.loadSave(FileHandler.getSaveFile(curSaveName)); // Uses the buttons text to find the right save file               

                stage.setScene(getGameScene());
            });

            loadSaveButtons.add(sb);
            saveMenuShell.getChildren().add(sb);
        }

        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            stage.setScene(mainMenuScene);

        });

        saveMenuShell.getChildren().add(back);

        return new Scene(saveMenuShell, screenWidth, screenWidth);
    }

    
    /**
     * 
     * @return scene containing new sim select screen
     */
    public static Scene initNewSimulation() {

        TextField saveNameInput = new TextField();
        Text saveNameText = new Text("Save Name: ");

        

        HBox saveNameContainer = new HBox(saveNameText, saveNameInput);

        TextField initialPop = new TextField();
        Text initialPopulationText = new Text("Initial Population: ");

        HBox initalPopContainer = new HBox(initialPopulationText, initialPop);

        Button mapSelect = new Button("Generate Map");

        mapSelect.setOnAction((ActionEvent event) -> {
            
            int p = 10;
            try {
                p = Integer.parseInt(initialPop.getText());
            } catch (Exception e) {

            }

            if (p < 1) {
                p = 1;
            }
            if (p > 10000) {
                p = 500;
            }

            Simulation.setCurSim(new Simulation(FileHandler.checkSaveName(saveNameInput.getText()), p, mapSize, newMapNoiseType));
            stage.setScene(getMapSelectorScene());
        });


        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            stage.setScene(mainMenuScene);
        });

        VBox newSimShell = new VBox(saveNameContainer, initalPopContainer);
        

        newSimShell.getChildren().add(mapSelect);
        newSimShell.getChildren().add(back);

        return new Scene(newSimShell, screenWidth, screenWidth);

    }

    public static void main(String[] args) {
        Simulation.init();
        launch();
        System.out.println("OK");

    }

}