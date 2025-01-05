package com.uwindsor.comp8390.asynchronous.schedulller;

import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 06/04/20:4:59 PM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
@Component
public class AutomatedSchedullingService {

    private XSynchronizeService xSynchronizeService;

  //note: This time is in milliseconds


    @Autowired
    public AutomatedSchedullingService(XSynchronizeService xSynchronizeService){
        this.xSynchronizeService = xSynchronizeService;
    }

   @Scheduled(fixedRate = 1200000) //temporarily set to 20 minutes
    public void ScheduledTask() throws IOException, ModelNotFoundException {

        xSynchronizeService.synchronizeDepartment();
        xSynchronizeService.synchronizeEmployee();
        xSynchronizeService.synchronizeProject();
        xSynchronizeService.synchronizeWorksOn();

    }
}
