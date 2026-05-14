package economy.Backend;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Utility {



    public static Button newImageButton(Image image, int height, int width) {


        Button imageButton = new Button();

        imageButton.setMinSize(width, height);
        imageButton.setMaxSize(width, height);
        
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(imageButton.getWidth(), imageButton.getHeight(), true, true, true, false));

        Background backGround = new Background(bImage);
        imageButton.setBackground(backGround);
        
        
        
        // try {
        //     Image buttonImage = new Image(new FileInputStream(imageFilePath)); 
        //     ImageView buttonImageView = new ImageView(buttonImage);
        //     buttonImageView.setFitWidth(width);
        //     buttonImageView.setFitHeight(height);
            
        //     imageButton.setGraphic(buttonImageView);
        // } catch (FileNotFoundException e) {
        //     System.out.println("Tried getting an image that doesn't exist.");
        // }

        return imageButton;
    }



}


