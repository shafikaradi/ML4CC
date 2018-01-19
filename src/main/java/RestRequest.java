import java.io.File;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.codehaus.plexus.util.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;



class RestRequest{

   private String url;
   private URL urlObj;
   private HttpURLConnection con;
   private StringBuffer response;
   private JSONArray arrayOfExchanges;
   
   private void setUrlObj(String url) throws Exception{

       urlObj = new URL(url);
   }

   public RestRequest setConnection(String url){

    try{

        this.url = url;
        setUrlObj(url);
        con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        System.out.println(con.getResponseCode());

    }catch(Exception e){

        e.getCause();
    }
     return this;
   }

   public RestRequest readData(){

    
    try{

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();


    }catch(IOException ex){

        ex.getCause();

    }
      

     return this;

   }

   public RestRequest convertFromStringToArrayJson(){


       try {
           arrayOfExchanges = new JSONArray(response.toString());
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
           JsonParser jp = new JsonParser();
           JsonElement je = jp.parse(arrayOfExchanges.toString());
           System.out.println(gson.toJson(je));

       } catch (JSONException e) {
           e.printStackTrace();
       }

       return this;
   }

   public RestRequest convertFromJsonToString(){

       File ctrCSV = new File("/Users/shafikalaradi/Desktop/ML4CC/"+this.url.substring(44,50)+".csv");
       String csv = CDL.toString(arrayOfExchanges);
       try {
           FileUtils.fileWrite(ctrCSV,csv);
       } catch (IOException e) {
           e.printStackTrace();
       }

       return this;
   }

    
}