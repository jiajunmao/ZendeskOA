package com.jiajunmao.processors;

import com.jiajunmao.exceptions.NonUniqueException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Requester {

    public static JSONObject parse(String json_) {
        JSONObject object = new JSONObject(json_);
        return object;
    }

    public static String getUsernameById(String id) {
        try {
            JSONObject resp = parse(request("https://zccjiajunmao.zendesk.com/api/v2/users/search.json?query=user:" + id, "GET", null)).getJSONArray("users").getJSONObject(0);
            return resp.getString("name");
        } catch (IOException e) {
            System.out.printf("ERROR: %s", e.getMessage() );
            return null;
        }
    }

    public static JSONObject getTicketById(String id) throws NonUniqueException {
        try {
            JSONArray array = parse(request("https://zccjiajunmao.zendesk.com/api/v2/search.json?query=" + id, "GET", null)).getJSONArray("results");
            if (array.length() / 3 > 1) {
                throw new NonUniqueException("Ticket with id " + id + " is non-unique!");
            } else if (array.length() == 0) {
                return null;
            } else {
                return array.getJSONObject(0);
            }
        } catch (IOException e) {
            System.out.printf("ERROR: %s", e.getMessage() );
            return null;
        }
    }

    public static String request(String url_, String method, String payload) throws IOException {
        URL url = new URL(url_);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String auth = System.getenv("ZENDESK_USERNAME") + "/token:" + System.getenv("ZENDESK_PASSWD");
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
        conn.setRequestProperty("Authorization", basicAuth);
        conn.setRequestMethod(method);

        if (payload != null) {
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(payload);
            writer.flush();
        }

        BufferedInputStream input = new BufferedInputStream(conn.getInputStream());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        for (int i = input.read(); i != -1; i = input.read()) {
            output.write((byte) i);
        }

        return output.toString(StandardCharsets.UTF_8);
    }
}
