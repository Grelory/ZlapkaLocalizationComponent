package com.codecool.zlapka.localizationcomponent.Networking;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class ConnectionProvider {

    private String userPATH = "http://5.135.20.171:8080/userAPI/users/";

    public ConnectionProvider() { }

    public HttpURLConnection ownerBondConnection(String UUID, String action) throws IOException {
        return (HttpURLConnection) new URL(userPATH + UUID + "/locations/" + action).openConnection();
    }

    public Optional<String> sendRequest(byte[] requestBody, HttpURLConnection connection, String method) {
        try {
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(requestBody.length));
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);

            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(requestBody);
            writer.flush();
            writer.close();

            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            connection.disconnect();
            return Optional.of(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
