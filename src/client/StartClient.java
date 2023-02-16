import org.json.JSONArray;

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
//        ClientHTTPRequests.sendGetRequest_isExistSteamURL("https://steamcommunity.com/id/knight_murloc/");
        ClientHTTPRequests.sendPostRequest_UpdateUserInfo("Hello, it's me!", "1234567890aasdfghjkl");

    }
}

