package com.example.jukeboxapi.controller;

import com.example.jukeboxapi.model.JukeBox;
import com.example.jukeboxapi.service.JukeboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class JukeboxController {

    @Autowired
    private JukeboxService jukeboxService;

    //to fetch required juke boxes with the given settingId
    @GetMapping(path= "/fetchJukeboxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JukeBox> retrieveJukeBoxes(@RequestParam(required = true) String settingId,
                                           @RequestParam(required = false) String modelName,
                                           @RequestParam(required = false) Integer offset,
                                           @RequestParam(required = false) Integer pageLimit) throws Exception {

        List<JukeBox> fetchAvailableJukeboxes = jukeboxService.
                fetchAvailableListOfJukeboxes(settingId, modelName, offset, pageLimit);

        return fetchAvailableJukeboxes;
    }

}
