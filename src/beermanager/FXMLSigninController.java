package beermanager;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLSigninController implements Initializable {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private Button btnConfirmCreate;
    @FXML
    private Label lblAccess;
       
    Parent mainInterface;
    
    UserDatabase userDatabase = new UserDatabase();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnLoginHandler(ActionEvent event) {
        if(userDatabase.checkUser(tfUsername.getText(),tfPassword.getText())){
            lblAccess.setText("Access Granted");
            try{
                mainInterface = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
            }catch(IOException e){
                System.out.println("Error: "+e);
            }
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(mainInterface);
        }else
            lblAccess.setText("Access Denied");
    }

    @FXML
    private void btnCreateAccountHandler(ActionEvent event) {
        tfUsername.setPromptText("Enter a new username");
        tfPassword.setPromptText("Enter a new password");
        btnConfirmCreate.setDisable(false);
        btnConfirmCreate.setOpacity(1.0);
        btnLogin.setDisable(true);
        btnLogin.setOpacity(0);
        btnCreateAccount.setDisable(true);
        btnCreateAccount.setOpacity(0);
    }

    @FXML
    private void btnConfirmCreateHandler(ActionEvent event) {
        User user = new User(tfUsername.getText(),tfPassword.getText());
        userDatabase.createUser(user);
        btnConfirmCreate.setOpacity(0);
        btnConfirmCreate.setDisable(true);
        tfUsername.setPromptText("Username");
        tfPassword.setPromptText("Password");
        tfUsername.setText("");
        tfPassword.setText("");
        btnLogin.setDisable(false);
        btnLogin.setOpacity(1.0);
        btnCreateAccount.setDisable(false);
        btnCreateAccount.setOpacity(1.0);
        
    }
    
}
