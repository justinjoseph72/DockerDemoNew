package com.jusitn.app.demodeploydocker.controller;


import com.yoti.api.client.ActivityDetails;
import com.yoti.api.client.HumanProfile;
import com.yoti.api.client.ProfileException;
import com.yoti.api.client.YotiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloWorld {

    private final YotiClient yotiClient;

    @Autowired
    public HelloWorld(final YotiClient yotiClient){
        this.yotiClient = yotiClient;
    }

    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloWorld(@RequestParam(value = "token") String token) throws IOException{
        logger.info("the token in {}", token);
        ActivityDetails activityDetails;
        HumanProfile profile;
        try{
            activityDetails = yotiClient.getActivityDetails(token);
            profile = activityDetails.getUserProfile();
            logger.info(" Name {} email {}",profile.getGivenNames(),profile.getEmailAddress());
        }
        catch (final ProfileException profileException) {
            logger.info("Could not get profile", profileException);
            return "error";
        }

        return "Hello World!";
    }




}
