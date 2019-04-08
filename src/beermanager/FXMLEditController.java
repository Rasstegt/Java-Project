package beermanager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private MainInterfaceController main; 
    
    Beer beer;
    
    BeerDatabase beerData = new BeerDatabase();
    @FXML
    private ImageView iv;
    @FXML
    private Button btnImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }

    @FXML
    private void btnConfirmHandler(ActionEvent event) throws IOException {
        beerData.editFile(beer, tfName.getText(), Double.parseDouble(tfArate.getText()), Double.parseDouble(tfPrice.getText()), Integer.parseInt(tfRating.getText()), Integer.parseInt(tfYear.getText()) , tfMan.getText(), tfCountry.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainInterface.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
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
    }
    
    public void setMain(MainInterfaceController main){
        this.main = main;
    }

    @FXML
    private void btnImageHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        configureFileChooser(fileChooser);
        Image ndsa = new Image(file.toString());
        iv.setImage(ndsa);
        
    }
       
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))); 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }
}
