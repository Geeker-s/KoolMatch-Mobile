/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Interaction;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author wassimromdhane
 */
public class ServiceInteraction {

    public ConnectionRequest req;
    private static ServiceInteraction instance;
    public boolean resultOK;
    public ArrayList<Interaction> reactions;

    private ServiceInteraction() {
        req = new ConnectionRequest();
    }

    public static ServiceInteraction getInstance() {
        if (instance == null) {
            instance = new ServiceInteraction();
        }
        return instance;
    }

    public boolean addInteraction(Interaction react) {
        
        String url = Statics.BASE_URL + "json/addInteraction/new/";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("connectedUser", Integer.toString(react.getId_user1()));
        req.addArgument("user", Integer.toString(react.getId_user2()));
        req.addArgument("type", react.getType_interaction());

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
