package beermanager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

public class FXMLEditController implements Initializable {

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
    private String imageFile;

    private MainInterfaceController main; 
    
    Beer beer;
    
    BeerDatabase beerData = new BeerDatabase();
    @FXML
    private ImageView iv;
    @FXML
    private Button btnImage;
    @FXML
    private Label imageTtl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }

    @FXML
    private void btnConfirmHandler(ActionEvent event) throws IOException {
        Alert alertConfEdit = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfEdit.setTitle("Delete Record");
        alertConfEdit.setHeaderText("You are trying to delete a record");
        alertConfEdit.setContentText("Are you sure you want to delete?");
        Optional<ButtonType>result=alertConfEdit.showAndWait();
        if (result.get() == ButtonType.OK){
            beerData.editFile(beer, 
                tfName.getText(), 
                Double.parseDouble(tfArate.getText()), 
                Double.parseDouble(tfPrice.getText()), 
                Integer.parseInt(tfRating.getText()), 
                Integer.parseInt(tfYear.getText()) , 
                tfMan.getText(), 
                tfCountry.getText(), imageFile);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainInterface.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
    }

    @FXML
    private void btnCancelHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainInterface.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
    
    public void setBeer(Beer beer){
        this.beer = beer;
        lblTitle.setText("Edit "+beer.getName());
        tfName.setText(beer.getName());
        tfMan.setText(beer.getMaunfacturer());
        tfCountry.setText(beer.getCountry());
        tfPrice.setText(String.valueOf(beer.getPrice()));
        tfRating.setText(String.valueOf(beer.getRating()));
        tfArate.setText(String.valueOf(beer.getAlcoRate()));
        tfYear.setText(String.valueOf(beer.getYear()));
        iv.setImage(new Image(beer.getPath()));
    }
    
    public void setMain(MainInterfaceController main){
        this.main = main;
    }

    @FXML
    private void btnImageHandler(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog
    (imageTtl.getScene().getWindow());

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
