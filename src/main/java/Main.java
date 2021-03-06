import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String args[]) {


     //a.setConnection("https://api.hitbtc.com/api/2/public/candles/ETHBTC?period=M30").readData().convertFromStringToArrayJson().convertFromJsonToString();
       launch(args);

  }

    @Override
    public void start(Stage primaryStage) throws Exception {

      Parent root =  FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
      Scene mainScene = new Scene(root,500,150);

      primaryStage.setTitle("JSON-CSV Converter");
      primaryStage.setScene(mainScene);
      primaryStage.show();


    }
}