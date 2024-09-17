
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class parsing
{
    public static void main(String[] args) 
    {
        String api = "980d96176457a6e65b8bc282bcadccd4";
        try 
        {
            HttpResponse<String> res = Unirest.get("https://api.themoviedb.org/3/search/person?api_key=980d96176457a6e65b8bc282bcadccd4&language=en-US&query=salman%20khan&page=1&include_adult=false").asString();
              
                    
            if(res.getStatus()==200)
            {
                String ans=res.getBody();
                JSONParser parser=new JSONParser();
                
                JSONObject mainobj=(JSONObject) parser.parse(ans);
                
                JSONArray array=(JSONArray) mainobj.get("results");
                
                
                   
                    JSONObject singleobj=(JSONObject)array.get(0);
                    String title=(String) singleobj.get("title");
                    Double rating=(Double) singleobj.get("vote_average");
                    String overview=(String) singleobj.get("overview");
                    System.out.println(title);
                    
                
                
            }
        }      
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
