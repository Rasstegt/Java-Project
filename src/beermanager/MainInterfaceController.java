package beermanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MainInterfaceController implements Initializable {

    @FXML
    private TableView<Beer> tbBeer;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSignout;
    @FXML
    private Label lblName;
    @FXML
    private Label lblARate;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblRating;
    @FXML
    private Label lblYear;
    @FXML
    private Label lblMan;
    @FXML
    private Label lblCountry;
    
    @FXML
    private Label lblTitle;
    
    Parent signin;
    Parent add;
    
    private FXMLEditController edit;
    
    BeerDatabase beerData = new BeerDatabase();
    
    @FXML
    private TableColumn<Beer, String> tcName;
    @FXML
    private TableColumn<Beer, Double> tcARate;
    @FXML
    private TableColumn<Beer, String> tcMan;
    @FXML
    private TableColumn<Beer, String> tcCountry;
    @FXML
    private TableColumn<Beer, Double> tcPrice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcName.setCellValueFactory(new PropertyValueFactory<Beer,String>("name"));
        tcARate.setCellValueFactory(new PropertyValueFactory<Beer,Double>("alcoRate"));
        tcMan.setCellValueFactory(new PropertyValueFactory<Beer,String>("maunfacturer"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<Beer,String>("country"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Beer,Double>("price"));
        
        tbBeer.setItems(beerData.readFromFile());
    }    

    @FXML
    private void btnAddHandler(ActionEvent event) {
        try{
            add = FXMLLoader.load(getClass().getResource("FXMLAdd.fxml"));
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(add);
    }
    
    @FXML
    private void btnEditHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLEdit.fxml"));
        Parent root = (Parent)loader.load();
        edit = loader.getController();
        edit.setMain(this);
        edit.setBeer(tbBeer.getSelectionModel().getSelectedItem());
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
        
    }

    @FXML
    private void btnDeleteHandler(ActionEvent event) {
        beerData.deleteFromFile(tbBeer.getSelectionModel().getSelectedItem());
        tbBeer.setItems(beerData.readFromFile());
    }

    @FXML
    private void btnSignoutHandler(ActionEvent event) {
        try{
            signin = FXMLLoader.load(getClass().getResource("FXMLSignin.fxml"));
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(signin);
    }
    
    @FXML
    private void searchHandler(KeyEvent event){
        ObservableList<Beer> list = beerData.readFromFile();
        FilteredList<Beer> filteredList = new FilteredList<>(list, p->true);
        tfSearch.textProperty().addListener((observable,oldValue,newValue) -> {
          filteredList.setPredicate(beer -> {
              
              if(newValue == null || newValue.isEmpty()){
                  return true;
              }
              String typed = newValue.toLowerCase();
              
              if(beer.getName().toLowerCase().indexOf(typed) != -1){
                  return true;
              }
              if(beer.getMaunfacturer().toLowerCase().indexOf(typed) != -1){
                  return true;
              }
              if(beer.getCountry().toLowerCase().indexOf(typed) != -1){
                  return true;
              }
              if(String.valueOf(beer.getPrice()).equals(typed)){
                  return true;
              }
              if(String.valueOf(beer.getAlcoRate()).equals(typed)){
                  return true;
              }
              
              return false;
          });  
          SortedList<Beer> sortedList = new SortedList<>(filteredList);
          sortedList.comparatorProperty().bind(tbBeer.comparatorProperty());
          tbBeer.setItems(sortedList);
        });  
    }

    @FXML
    private void tbBeerHandler(MouseEvent event) {
        Beer beer = tbBeer.getSelectionModel().getSelectedItem();
        lblName.setText("Name: "+beer.getName());
        lblARate.setText("Alcohol Rate: "+String.valueOf(beer.getAlcoRate()));
        lblRating.setText("Rating "+String.valueOf(beer.getRating())+"/5");
        String price = String.valueOf(beer.getPrice());
        if(price.length() < 4){
            lblPrice.setText("Price per Can: "+price+"0");
        }else{
            lblPrice.setText("Price per Can: "+price);
        }
        
        lblYear.setText("Year: "+String.valueOf(beer.getYear()));
        lblMan.setText("Manufacturer: "+beer.getMaunfacturer());
        lblCountry.setText("Country: "+beer.getCountry());
    }
    
}
