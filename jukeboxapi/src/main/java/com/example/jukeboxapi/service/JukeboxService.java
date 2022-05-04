package com.example.jukeboxapi.service;


import com.example.jukeboxapi.constants.Constants;
import com.example.jukeboxapi.model.JukeBox;
import com.example.jukeboxapi.model.JukeComponent;
import com.example.jukeboxapi.model.Setting;
import com.example.jukeboxapi.model.SettingResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
public class JukeboxService {

    private RestTemplate restTemplate = new RestTemplate();
    private Constants constant = new Constants();

    public List<JukeBox> fetchAvailableListOfJukeboxes(String settingId, String modelName, Integer offset,
                                                       Integer pageLimit) throws Exception {
        List<JukeBox> jukeBoxResponse = new ArrayList<>();
        //calling fetchJukeBox() method
        List<JukeBox> jukeboxes = fetchJukeBox();
        //calling fetchSettingBySettingId() method
        Setting settingFetched = fetchSettingBySettingId(settingId);

        List<String> componentNames = new ArrayList<>();
        //validating jukeboxes to not be empty and null
        if(!jukeboxes.isEmpty() && jukeboxes!=null) {
            //iterating through jukeboxes list
            for (JukeBox juke : jukeboxes) {
                //validating juke.getComponents and settingFetched.getRequires lists to not be null
                if (juke != null && juke.getComponents() != null && settingFetched != null &&
                        settingFetched.getRequires() != null) {

                    // filtering response using modelName
                    if (juke.getModel().equals(modelName)) {

                        //iterating juke.getComponents to fetch names for each juke component and adding to the
                        // componentNames arraylist
                        for (JukeComponent jukeName : juke.getComponents()) {
                            componentNames.add(jukeName.getName());
                        }
                    } else if(modelName==null){
                        for (JukeComponent jukeName : juke.getComponents()) {
                            componentNames.add(jukeName.getName());
                        }
                    }
                    //checking for the size of settingFetched.getRequires() to be lesser or equal to componentNames.size()
                    // and checking if all componentNames of that juke are present in settingFetched.getRequires() list
                    if (settingFetched.getRequires().size() <= componentNames.size() && componentNames.
                            containsAll(settingFetched.getRequires())) {
                        jukeBoxResponse.add(juke);
                    }
                    //checking for the size of settingFetched.getRequires() to be greater or equal to componentNames.size()
                    // and checking if all componentNames of that juke are present in settingFetched.getRequires() list
                    else if (settingFetched.getRequires().size() >= componentNames.size() && componentNames.
                            containsAll(settingFetched.getRequires())) {
                        jukeBoxResponse.add(juke);
                    }
                    //resetting componentNames list to empty for each juke component
                    componentNames = new ArrayList<>();
                }
            }
    }
        return jukeBoxResponse;

    }

    //to fetch setting response object using settingId
    private Setting fetchSettingBySettingId(String settingId) throws URISyntaxException {
        Setting jukeSetting =  new Setting();
        final String settingURI = constant.mockServiceUrl +"/settings";
        URI uri = new URI(settingURI);

        //Calling mocked setttings API
        SettingResponse settingResponse = restTemplate.getForObject(uri, SettingResponse.class);
        if (settingResponse != null && settingResponse.getSettings() != null) {
            //iterating through setting response list
            for (Setting setting : settingResponse.getSettings()) {
                if (settingResponse.getSettings() != null) {
                    //validating settingId from the response with given settingId
                    if (setting.getId().equals(settingId)) {
                        //saving matched setting object in jukeSetting
                        jukeSetting =  setting;
                        break;
                    } else jukeSetting = new Setting();
                    System.out.println("The entered settingId is not valid");
                }
            }
        }
        return jukeSetting;
    }

    private List<JukeBox> fetchJukeBox() {

        //Calling mocked jukeBox API
        ResponseEntity<List<JukeBox>> responses = restTemplate.exchange(
                constant.mockServiceUrl + "/jukes",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<JukeBox>>() {
                    });
        List<JukeBox> jukeBoxes = responses.getBody();
        //returning the list of jukebox from the response
        return jukeBoxes;
    }

}

