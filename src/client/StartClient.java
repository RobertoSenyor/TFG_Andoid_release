import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartClient {
    public static void main(String[] args) {

//        ClientHTTPRequests.sendGetRequest_isExistUsername("knight_murloc");
//        ClientHTTPRequests.sendPostRequest_RegistrationUser("Roberto", "URL", "Password");
//        ClientHTTPRequests.sendPostRequest_LoginUser("knight_murloc", "123");
//        ClientHTTPRequests.sendPostRequest_LogoutUser("1234567890aasdfghjkl");
        ClientHTTPRequests.sendGetRequest_isExistSteamURL("https://steamcommunity.com/id/knight_murloc/");
//        ClientHTTPRequests.sendPostRequest_UpdateUserInfo("Hello, it's me!", "1234567890aasdfghjkl");

        JSONArray jsonArray = ClientHTTPRequests.sendGetRequest_GetUserInfo("1234567890aasdfghjkl").getJSONArray("game_list");

        for (int i=0; i < jsonArray.length(); i++)
        {
            System.out.println("gameName: " + jsonArray.getJSONObject(i).getString("name"));
            System.out.println("playTime: " + jsonArray.getJSONObject(i).getInt("playtime"));
            System.out.println("gameURL: " + jsonArray.getJSONObject(i).getString("cover_url"));
            System.out.println("+====================================================+");
        }

        ClientHTTPRequests.sendGetRequest_GetUserInfo(123,"1234567890aasdfghjkl");

        JSONArray jsonArray2 = ClientHTTPRequests.sendGetRequest_GetInfoList("1234567890aasdfghjkl");

        for (int i = 0; i < jsonArray2.length(); i++)
        {
            System.out.println("userID: " + jsonArray2.getJSONObject(i).getInt("user_id"));
            System.out.println("userName: " + jsonArray2.getJSONObject(i).getString("username"));
            System.out.println("avatarURL: " + jsonArray2.getJSONObject(i).getString("avatar_url"));
            System.out.println("about: " + jsonArray2.getJSONObject(i).getString("about"));
            System.out.println("match_game: " + jsonArray2.getJSONObject(i).getString("match_game"));
            System.out.println("+=============================================+");
        }
    }
}

