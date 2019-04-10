package beermanager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class FXMLAddController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfMan;
    @FXML
    private TextField tfCountry;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfRating;
    @FXML
    private TextField tfArate;
    @FXML
    private TextField tfPrice;
    @FXML
    private Label lblTitle;
    
    Parent mainInterface;
    
    BeerDatabase beerData = new BeerDatabase();
    ObservableList beerList;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button lbtnCancel;
    @FXML
    private Label lblError;
    @FXML
    private ImageView iv;
    @FXML
    private Button btnImage;
    @FXML
    private Label imageTtl;
    
    private String imageFile;
    Pattern isInteger = Pattern.compile("\\d+");
    int rating, year;
    double alcoRate, price;
    ArrayList<String> errors = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void addBeer(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
          try {
              
        try {
           price = Double.parseDouble(tfPrice.getText());
        }catch(NumberFormatException e) {
            errors.add("It is a price, not a poem.. "
                + "(Double value for price)");
            
        }
        try {
           alcoRate = Double.parseDouble(tfArate.getText());
        }catch(NumberFormatException e) {
            errors.add("\nHave you seen an alcohol % written in String??? "
                + "(Double for Alcohol Rate)");
        }
        
        try {
           year = Integer.parseInt(tfYear.getText());
        }catch(NumberFormatException e) {
            errors.add("\nIf you were born in 2019.2 - "
                + "We are so sorry for you.. (Integer for Year)");
            
        }
        
        try {
        if(Integer.parseInt(tfRating.getText()) > 5){
            rating = 5;
        } else if(Integer.parseInt(tfRating.getText()) < 0){
            rating = 0;
        } else {
            rating = Integer.parseInt(tfRating.getText());
        } 
        
        } catch(NumberFormatException e) {
            errors.add("\nRating.. Only 0-5, sorry (Integer for Rating)");
        }
        
                Beer beer = new Beer(tfName.getText(),
                Double.parseDouble(tfArate.getText()),
                Double.parseDouble(tfPrice.getText()),
                rating,
                Integer.parseInt(tfYear.getText()),
                tfMan.getText(),tfCountry.getText());
                beer.setImage(imageFile);
                beerData.createBeer(beer);
                
        } catch(NumberFormatException e){
            
            alert.setContentText(errors.toString() + "\nFix your errors and comeback");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void btnConfirmHandler(ActionEvent event) {
        addBeer();
        try{
            mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(mainInterface);
    }

    @FXML
    private void btnCancelHandler(ActionEvent event) {
        try{
            mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        }catch(IOException e) {
            System.out.println("Error: " + e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(mainInterface);
    }

    @FXML
    private void btnImageHandler(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(imageTtl.getScene().getWindow());

        if (selectedFile != null) {
            imageFile = selectedFile.toURI().toURL().toString();
            Image image = new Image(imageFile);
            iv.setImage(image);
            imageTtl.setText("");
        } else {
            imageTtl.setText("Image file selection cancelled.");
        }
    }
    
}
