package beermanager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonType;
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
    
    Parent mainInterface;
    BeerDatabase beerData = new BeerDatabase();
    ObservableList beerList;
    private String imageFile;
    int rating, year;
    double alcoRate, price;
    Alert alert = new Alert(Alert.AlertType.WARNING);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void addBeer(){
        
        price = Double.parseDouble(tfPrice.getText());
        alcoRate = Double.parseDouble(tfArate.getText());
        year = Integer.parseInt(tfYear.getText());
        rating = Integer.parseInt(tfRating.getText());
        
        if(rating > 5)  rating = 5;
        else if(rating < 0) rating = 0;
        
        if(year > 2019) year = 2019;
        else if(year < 0) year = 0;
        
        if(alcoRate < 0) alcoRate = 0;
        else if(alcoRate > 99) alcoRate = 99;
        
        if(price < 0) price = 0;

        beerData.createBeer(new Beer(tfName.getText(),
        Double.parseDouble(tfArate.getText()),
        Double.parseDouble(tfPrice.getText()),
        rating,
        Integer.parseInt(tfYear.getText()),
        tfMan.getText(),
        tfCountry.getText(), 
        imageFile));
           
    }
    
    @FXML
    private void btnConfirmHandler(ActionEvent event) {
        try {    
        addBeer();
        } catch(NumberFormatException e) {
        alert.setTitle("Add Record");
        alert.setHeaderText("Be careful with values my drunk friend :)");
        alert.setContentText("Don't fill out database if you are drunk"
            + " (It's okay)");
            Optional<ButtonType>result=alert.showAndWait();
            if(result.get() == null){
                try{
                mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
                } catch(IOException ex) {
                    System.out.println("Error: " + e);
                }    
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(mainInterface);
            }
        }
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
