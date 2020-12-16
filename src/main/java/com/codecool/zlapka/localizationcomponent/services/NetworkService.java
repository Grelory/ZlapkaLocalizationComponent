package com.codecool.zlapka.localizationcomponent.services;

import com.codecool.zlapka.localizationcomponent.networking.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

@Service
public class NetworkService {

    @Autowired
    private ConnectionProvider connectionProvider;

    public String addLocalizationToUser(String localizationBondJson, String userId) {
        if (userId == null) return "{\"status\":\"400\", \"info\":\"Invalid data!\"}";
        try {
            HttpURLConnection connection = connectionProvider.ownerBondConnection(userId, "add");
            Optional<String> optionalResponse = connectionProvider
                    .sendRequest(localizationBondJson.getBytes(), connection, "POST");
            if (optionalResponse.isEmpty()) return "{ \"status\":\"500\", \"info\":\"Server error! Unable to bind!\"}";
            return optionalResponse.get();
        } catch (IOException e) {
            e.printStackTrace();
            return "{ \"status\":\"500\", \"info\":\"Server error! Unable to bind!\"}";
        }
    }

    public String deleteLocalizationFromUser(String localizationBondJson, String userId) {
        try {
            HttpURLConnection connection = connectionProvider.ownerBondConnection(userId, "delete");
            Optional<String> optionalResponse = connectionProvider
                    .sendRequest(localizationBondJson.getBytes(), connection, "DELETE");
            if (optionalResponse.isEmpty()) return "{ \"status\":\"500\", \"info\":\"Server error! Unable to delete!\"}";
            return optionalResponse.get();
        } catch (IOException e) {
            e.printStackTrace();
            return "{ \"status\":\"500\", \"info\":\"Server error! Unable to delete!\"}";
        }
    }

    public String updateLocalizationNameInUser(String localizationBondJson, String userId) {
        try {
            HttpURLConnection connection = connectionProvider.ownerBondConnection(userId, "update");
            Optional<String> optionalResponse = connectionProvider
                    .sendRequest(localizationBondJson.getBytes(), connection, "UPDATE");
            if (optionalResponse.isEmpty()) return "{ \"status\":\"500\", \"info\":\"Server error! Unable to update!\"}";
            return optionalResponse.get();
        } catch (IOException e) {
            e.printStackTrace();
            return "{ \"status\":\"500\", \"info\":\"Server error! Unable to update!\"}";
        }
    }

}
