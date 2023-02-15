import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartClient {
    public static void main(String[] args) {

        sendGetRequest();
    }

    public static void sendGetRequest() {

        try {
            URL url = new URL("http://127.0.0.1:5000/User/exist_username?username=knight_murloc");
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
            System.out.println("Response : " + response.toString());

            JSONObject jsonObject = new JSONObject(response.toString());

            System.out.println(jsonObject.get("result").toString().equals("true") ? "nate" : "higgers");

        } catch (Exception exception) {
            System.out.println("Error in Making Get Request");
        }
    }

        public static void sendPOSTRequest() {
            try {
                String post_data = "key1=value1&key2=value2";

                URL url = new URL("https://yandex.ru");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                //adding header
                httpURLConnection.setRequestProperty("Auth", "Token");
                httpURLConnection.setRequestProperty("Data1", "Value1");
                httpURLConnection.setDoOutput(true);

                //Adding Post Data
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(post_data.getBytes());
                outputStream.flush();
                outputStream.close();


                System.out.println("Response Code " + httpURLConnection.getResponseCode());

                String line = "";
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
                System.out.println("Response : " + response.toString());


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error in Making POST Request");
            }
        }


        public static void ParseJsonResponse () {
            try {
                URL url = new URL("https://yandex.ru");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                //adding header

                String line = "";
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
                System.out.println("Response : " + response.toString());
                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    System.out.println("Title : " + jsonArray.getJSONObject(i).getString("title"));
                    System.out.println("ID : " + jsonArray.getJSONObject(i).getInt("id"));
                    System.out.println("URL : " + jsonArray.getJSONObject(i).getString("url"));
                    System.out.println("===========================================================\n");
                }

            } catch (Exception e) {
                System.out.println("Error in Making Get Request");
            }
        }
    }

