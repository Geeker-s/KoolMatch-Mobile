/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Interaction;
import com.codename1.uikit.pheonixui.model.user;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wassimromdhane
 */
public class ServiceInteraction {

    public ConnectionRequest req;
    private static ServiceInteraction instance;
    public boolean resultOK;
    public ArrayList<Interaction> reactions;
    public ArrayList<user> canBeMatched;

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

        String url = Statics.BASE_URL + "/json/addInteraction/new/";
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

    public ArrayList<user> afficherMatches(user connectedUser) {

        connectedUser.setId_user(1);
        String url = Statics.BASE_URL + "/json/algorithme/" + Integer.toString(connectedUser.getId_user());
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                canBeMatched = parsecanBeMatched(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return canBeMatched;
    }

    public ArrayList<user> parsecanBeMatched(String jsonText) {
        try {
            canBeMatched = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> coinsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) coinsListJson.get("root");
            for (Map<String, Object> obj : list) {
                user c = new user();

                float id = Float.parseFloat(obj.get("idUser").toString());
                c.setId_user((int) id);


                c.setNom_user(obj.get("nomUser").toString());
                c.setPhoto_user(obj.get("photoUser").toString());
                c.setDescription_user(obj.get("descriptionUser").toString());
                c.setAdresse_user(obj.get("adresseUser").toString());

                canBeMatched.add(c);
            }

        } catch (IOException ex) {

        }
        return canBeMatched;
    }

}
