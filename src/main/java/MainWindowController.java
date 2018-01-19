import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Shafiq on 1/19/18.
 */
public class MainWindowController implements Initializable{


    @FXML
    private Button convertButton;
    @FXML
    private TextField urlField;

    Thread restRequest;
    public MainWindowController(){

        RestRequest jsonData = new RestRequest();
        restRequest = new Thread(new Runnable() {
            @Override
            public void run() {
                jsonData.setConnection(urlField.getText()).readData().convertFromStringToArrayJson().convertFromJsonToString();
            }
        });

    }

    public void initialize(URL location, ResourceBundle resources) {



        convertButton.setOnAction( action -> {



            restRequest.start();

        });
    }
}
