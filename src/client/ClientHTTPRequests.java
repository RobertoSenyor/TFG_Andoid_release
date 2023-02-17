import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class ClientHTTPRequests {

    /**
     * Функция возвращает true\false если пользователь с этим именем (_Username)
     * существует или нет соответственно
     * @return boolean
     * @params String _Username - имя пользователя
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#занято-ли-имя">GitHubURL</a>
     */
    public static boolean sendGetRequest_isExistUsername(String _Username) {

        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/exist_username?username=" + _Username;

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());
            System.out.println("Response: " + response.toString());

            // извлечение данных по ключу, здесь ключ "result"
            return new JSONObject(response.toString()).get("result").toString().equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendGetRequest_isExistUsername: Ошибка проверки существования пользователя с заданным именем;");
            return false;
        }
    }

    /**
     * Функция получает токен при успешной регистрации пользователя
     * @return String
     * @params <p>String _Username - имя пользователя</p>
     * <p>String _SteamURL - ссылка на Staem-аккаунт</p>
     * <p>String _Password - пароль для регистрации</p>
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#регистрация">GitHubURL</a>
     */
    public static String sendPostRequest_RegistrationUser(String _Username, String _SteamURL, String _Password) {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/register";

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("username", _Username);
            jsonObject.put("steam_url", _SteamURL);
            jsonObject.put("password", _Password);

            String post_data = jsonObject.toString();

            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // для отправки в формате JSON
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            // заставляет прочитать сообщение в формате JSON
            httpURLConnection.setRequestProperty("Accept", "application/json");
            // использование соединения для отправки данных
            httpURLConnection.setDoOutput(true);

            // создание и отправка тела запроса
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = post_data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());
            System.out.println("Response : " + response.toString());

            return new JSONObject(response.toString()).get("token").toString();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendPostRequest_RegistrationUser: Ошибка регистрации пользователя;");
            return null;
        }
    }

    /**
     * Функция получает токен при успешной авторизации пользователя
     * @return String
     * @params <p>String _Username - имя пользователя</p>
     * <p>String _Password - пароль для регистрации</p>
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#логин">GitHubURL</a>
     */
    public static String sendPostRequest_LoginUser(String _Username, String _Password) {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/login";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", _Username);
            jsonObject.put("password", _Password);

            String post_data = jsonObject.toString();

            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            // для отправки в формате JSON
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            // заставляет прочитать сообщение в формате JSON
            httpURLConnection.setRequestProperty("Accept", "application/json");
            // использование соединения для отправки данных
            httpURLConnection.setDoOutput(true);

            // создание и отправка тела запроса
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = post_data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());
            System.out.println("Response : " + response.toString());

            return new JSONObject(response.toString()).get("token").toString();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendPostRequest_LoginUser: Ошибка автороизации пользователя;");
            return null;
        }
    }

    /**
     * Функция получает токен при успешном выходе пользователя из аккаунта
     * @return boolean
     * @params <p>String _Token - токен сессии</p>
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#выход">GitHubURL</a>
     */
    public static boolean sendPostRequest_LogoutUser(String _Token) {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/logout?token=" + _Token;

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());

            return httpURLConnection.getResponseCode() <= 299 &&  httpURLConnection.getResponseCode() >= 200 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendPostRequest_LogoutUser: Ошибка выхода пользователя из аккаунта;");
            return false;
        }
    }

    /**
     * Функция возвращает true\false если ссылка на профиль в Steam
     * существует или нет соответственно
     * @params String _SteamURL - ссылка на профиль в Steam
     * @return boolean
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#занятали-ссылка-на-профиль">GitHubURL</a>
     */
    public static boolean sendGetRequest_isExistSteamURL(String _SteamURL) {

        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/exist_steamurl?steamurl=" + _SteamURL;

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());
            System.out.println("Response: " + response.toString());

            // извлечение данных по ключу, здесь ключ "result"
            return new JSONObject(response.toString()).get("result").toString().equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendGetRequest_isExistSteamURL: Ошибка проверки существования Steam-аккаунта;");
            return false;
        }
    }

    /**
     * Функция получает ответ от сервера при успешном получении токена и информации рода "о себе"
     * @return boolean
     * @params <p>String _About - информации рода "о себе"</p>
     * <p>String _Token - токен сессии</p>
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#обновеление-информации-о-профиле">GitHubURL</a>
     */
    public static boolean sendPostRequest_UpdateUserInfo(String _About, String _Token)
    {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/update_info?token=" + _Token;

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("about", _About);

            String post_data = jsonObject.toString();

            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            // для отправки в формате JSON
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            // заставляет прочитать сообщение в формате JSON
            httpURLConnection.setRequestProperty("Accept", "application/json");
            // использование соединения для отправки данных
            httpURLConnection.setDoOutput(true);

            // создание и отправка тела запроса
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = post_data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());

            return httpURLConnection.getResponseCode() <= 299 &&  httpURLConnection.getResponseCode() >= 200 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendPostRequest_UpdateUserInfo: Ошибка обновления данных пользователя;");
            return false;
        }
    }

    /**
     * Функция получает уникальный идентификатор пользователя и токен сессии
     * и возвращает данные об аккаунте в формате JSON
     * @params <p>Integer _UserID - уникальный идентификатор</p>
     * <p>String _Token - токен сессии</p>
     * @return JSONObject
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#информация-о-профиле">GitHubURL</a>
     * @see ClientHTTPRequests#sendGetRequest_GetUserInfo(String)
     */
    public static JSONObject sendGetRequest_GetUserInfo(Integer _UserID, String _Token)
    {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/get_info?user_id=" + _UserID.toString() + "&token=" + _Token;

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
            System.out.println("Response code: " + httpURLConnection.getResponseCode());
            System.out.println("Response: " + response.toString());

            // извлечение данных по ключу, здесь ключ "result"
            return new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendGetRequest_isExistUsername: Ошибка проверки существования пользователя с заданным именем;");
            return new JSONObject();
        }
    }

    /**
     * Функция получает токен сессии
     * и возвращает данные об аккаунте в формате JSON
     * @params <p>String _Token - токен сессии</p>
     * @return JSONObject
     * @see <a href="https://github.com/RobertoSenyor/TFG_Documentation/blob/main/API.md#информация-о-профиле">GitHubURL</a>
     * @see ClientHTTPRequests#sendGetRequest_GetUserInfo(Integer, String)
     */
    public static JSONObject sendGetRequest_GetUserInfo(String _Token)
    {
        // TODO - поменять URL
        String urlRequest = "http://127.0.0.1:5000/User/get_info?token=" + _Token;

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            String line = "";
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            // TODO - данный вывод можно убрать
//            System.out.println("Response code: " + httpURLConnection.getResponseCode());
//            System.out.println("Response: " + response.toString());

            // извлечение данных по ключу, здесь ключ "result"
            return new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("sendGetRequest_isExistUsername: Ошибка проверки существования пользователя с заданным именем;");
            return new JSONObject();
        }
    }
}
