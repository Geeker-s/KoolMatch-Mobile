package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Message;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageService {
    private ArrayList<Message> messages = new ArrayList<>();
    boolean resultOK;
    public ArrayList<Message> getMessages(){
        System.out.println("In Get Events Method");
        System.out.println("Got Id");
        ConnectionRequest request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/message/j/s/o/n";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetEvents::ResponseListener");
                messages = parseEvent(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return messages;
    }

    private ArrayList<Message> parseEvent(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException, ParseException {
        System.out.println("In Parse Event Method");
        messages = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
        for (Map<String,Object> obj : list){

            float comIdF = Float.parseFloat(obj.get("idMessage").toString());
            String comId = String.valueOf((int) comIdF);

            String msgMessage = obj.get("msgMessage").toString();

            Message message = new Message();

            message.setId(comId);
            message.setMessage(msgMessage);
            messages.add(message);
        }


        return messages;
    }

    public boolean addMessage(String string,String id) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Sending ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/addmessage";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        request.addArgument("msgMessage", string);
        request.addArgument("idConversation", "1");

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();

        return resultOK;
    }
}
