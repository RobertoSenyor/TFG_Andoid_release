import org.json.JSONArray;

public class StartClient {
    public static void main(String[] args) {
        ClientHTTPRequests.sendPostRequest_RegistrationUser("Roberto", "URL", "Password");
        ClientHTTPRequests.sendGetRequest_isExistUsername("Roberto");
        ClientHTTPRequests.sendPostRequest_LoginUser("knight_murloc", "123");
        ClientHTTPRequests.sendPostRequest_LogoutUser("1234567890aasdfghjkl");
        ClientHTTPRequests.sendGetRequest_isExistSteamURL("https://steamcommunity.com/id/knight_murloc/");
        ClientHTTPRequests.sendPostRequest_UpdateUserInfo("Hello, it's me!", "1234567890aasdfghjkl");
        ClientHTTPRequests.sendPostRequest_SetAttitude(-1, 123, "1234567890aasdfghjkl");

        JSONArray jsonArray = ClientHTTPRequests.sendGetRequest_FindMessage(123,"test","1234567890aasdfghjkl");
        for (int i=0; i < jsonArray.length(); i++)
        {
            System.out.println("message_id: " + jsonArray.getInt(i));
            System.out.println("+====================================================+");
        }

        JSONArray jsonArray1 = ClientHTTPRequests.sendGetRequest_GetChatsList("1234567890aasdfghjkl");
        for (int i=0; i < jsonArray1.length(); i++)
        {
            System.out.println("chat_id: " + jsonArray1.getJSONObject(i).getInt("chat_id"));
            System.out.println("user_id: " + jsonArray1.getJSONObject(i).getInt("user_id"));
            System.out.println("+====================================================+");
        }
        
        JSONArray jsonArray2 = ClientHTTPRequests.sendGetRequest_GetMessagesList(123, 101, 100, true, "1234567890aasdfghjkl");
        for (int i=0; i < jsonArray2.length(); i++)
        {
            System.out.println("message_id: " + jsonArray2.getJSONObject(i).getInt("message_id"));
            System.out.println("user_id: " + jsonArray2.getJSONObject(i).getInt("user_id"));
            System.out.println("text: " + jsonArray2.getJSONObject(i).getString("text"));
            System.out.println("time: " + jsonArray2.getJSONObject(i).getInt("time"));
            System.out.println("+====================================================+");
        }
        
        JSONArray jsonArray3 = ClientHTTPRequests.sendGetRequest_GetUserInfo("1234567890aasdfghjkl").getJSONArray("game_list");
        for (int i=0; i < jsonArray3.length(); i++)
        {
            System.out.println("gameName: " + jsonArray3.getJSONObject(i).getString("name"));
            System.out.println("playTime: " + jsonArray3.getJSONObject(i).getInt("playtime"));
            System.out.println("gameURL: " + jsonArray3.getJSONObject(i).getString("cover_url"));
            System.out.println("+====================================================+");
        }

        ClientHTTPRequests.sendGetRequest_GetUserInfo(123,"1234567890aasdfghjkl");

        JSONArray jsonArray4 = ClientHTTPRequests.sendGetRequest_GetInfoList("1234567890aasdfghjkl");
        for (int i = 0; i < jsonArray4.length(); i++)
        {
            System.out.println("userID: " + jsonArray4.getJSONObject(i).getInt("user_id"));
            System.out.println("userName: " + jsonArray4.getJSONObject(i).getString("username"));
            System.out.println("avatarURL: " + jsonArray4.getJSONObject(i).getString("avatar_url"));
            System.out.println("about: " + jsonArray4.getJSONObject(i).getString("about"));
            System.out.println("match_game: " + jsonArray4.getJSONObject(i).getString("match_game"));
            System.out.println("+=============================================+");
        }

        ClientHTTPRequests.sendPostRequest_SendMessage(123, "Hello", "1234567890aasdfghjkl");
        ClientHTTPRequests.sendPostRequest_AddUserInBlackList(123,"1234567890aasdfghjkl");
        ClientHTTPRequests.sendPostRequest_DropChat(123,"1234567890aasdfghjkl");
    }
}

