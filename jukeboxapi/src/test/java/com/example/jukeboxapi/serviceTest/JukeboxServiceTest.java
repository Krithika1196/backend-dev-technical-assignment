package com.example.jukeboxapi.serviceTest;

import com.example.jukeboxapi.model.JukeBox;
import com.example.jukeboxapi.model.JukeComponent;
import com.example.jukeboxapi.service.JukeboxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class JukeboxServiceTest {

    JukeboxService jukeboxService = new JukeboxService();
    @Test
    public void jukeboxSuccessServiceTest() throws Exception {
    String settingId = "515ef38b-0529-418f-a93a-7f2347fc5805";
    String modelName = "angelina";
    String name1 = "money_storage";
    String name2 = "pcb";
    List<JukeComponent> jukeComponentList = new ArrayList<>();
    JukeComponent jukeComponent = new JukeComponent();
        jukeComponent.setName(name1);
        jukeComponent.setName(name2);
        jukeComponentList.add(jukeComponent);
        List<String> requiresList = new ArrayList<>();
    String requires1 = "money_storage";
        requiresList.add(requires1);
    List<JukeBox> jukeBoxes = jukeboxService.fetchAvailableListOfJukeboxes(settingId,modelName,1,1 );
        Assert.assertNotNull(jukeBoxes);

    }
}
