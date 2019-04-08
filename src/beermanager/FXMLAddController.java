package beermanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void addBeer(){
        //add excpetion handling for incorrect input type
        int rating = 0;
        if(Integer.parseInt(tfRating.getText()) > 5){
            rating = 5;
        }else if(Integer.parseInt(tfRating.getText()) < 0){
            rating = 0;
        }else{
            rating = Integer.parseInt(tfRating.getText());
        }
        
        try{
            beerData.createBeer(new Beer(tfName.getText(),Double.parseDouble(tfArate.getText()),Double.parseDouble(tfPrice.getText()),rating,Integer.parseInt(tfYear.getText()),tfMan.getText(),tfCountry.getText()));
        }catch(Exception e){
            lblError.setText("Error: "+e);
        }
        
    }

    @FXML
    private void btnConfirmHandler(ActionEvent event) {
        addBeer();
        try{
            mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(mainInterface);
    }

    @FXML
    private void btnCancelHandler(ActionEvent event) {
        try{
            mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(mainInterface);
    }
    
}
