package com.codename1.uikit.pheonixui.service;

import com.codename1.io.*;
import com.codename1.uikit.pheonixui.model.Conversation;
import com.codename1.uikit.pheonixui.model.Message;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConversationService {
    private ArrayList<Conversation> conversations = new ArrayList<>();
    boolean resultOK;
    public ArrayList<Conversation> getMessages(){
        System.out.println("In Get Events Method");
        System.out.println("Got Id");
        ConnectionRequest request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/json/conversation/"+ Preferences.get("id","1");
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetEvents::ResponseListener");
                conversations = parseEvent(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return conversations;
    }

    private ArrayList<Conversation> parseEvent(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException, ParseException {
        System.out.println("In Parse Event Method");
        conversations = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
        for (Map<String,Object> obj : list){

            float comIdF = Float.parseFloat(obj.get("id").toString());
            String comId = String.valueOf((int) comIdF);

            String msgMessage = obj.get("title").toString();
            String user = obj.get("user").toString();

            Conversation conversation = new Conversation();

            conversation.setId(comId);
            conversation.setTitle(msgMessage);
            conversation.setUser(user);
            conversations.add(conversation);
        }


        return conversations;
    }
}
