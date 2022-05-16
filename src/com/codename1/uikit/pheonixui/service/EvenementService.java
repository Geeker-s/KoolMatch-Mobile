package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.model.Recette;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EvenementService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Evenement> evenements = new ArrayList<>();
    private static EvenementService instance;

    private EvenementService() {
        request = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null)
            instance = new EvenementService();
        return instance;
    }

    public boolean addEvenement(Evenement evenement){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Adding your new event ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/addeventM";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("nomEvent",evenement.getNomEvent());
        request.addArgument("ddEvent",evenement.getDdEvent().toString());
        request.addArgument("dfEvent",evenement.getDfEvent().toString());
        request.addArgument("themeEvent",evenement.getThemeEvent());
        request.addArgument("adresseEvent",evenement.getAdresseEvent());
        request.addArgument("telephone",String.valueOf(evenement.getTelephone()));


        System.out.println(request.getRequestBody());

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                System.out.println("error 1 here");
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        return resultOK;
    }

    public ArrayList<Evenement> getEvenements(){
        System.out.println("In Get Events Method");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/listeventmobile";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetEvenements::ResponseListener");
                evenements = parseEvenement(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return evenements;
    }

    private ArrayList<Evenement> parseEvenement(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException, ParseException {
        System.out.println("In Parse Event Method");
        evenements = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> evenementListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) evenementListJson.get("root");
        for (Map<String,Object> obj : list){

            float evenementIdF = Float.parseFloat(obj.get("idEvent").toString());
            int evenementId = Integer.parseInt(String.valueOf((int) evenementIdF));
            String nomEvent = obj.get("nomEvent").toString();
            Date ddEvent = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("ddEvent").toString());
            Date dfEvent = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dfEvent").toString());
            String themeEvent = obj.get("themeEvent").toString();
            String telephone = obj.get("telephone").toString();
            String archive = obj.get("archive").toString();
            String adresseEvent = obj.get("adresseEvent").toString();


            Evenement evenement = new Evenement();
            evenement.setArchive(archive);
            evenement.setDdEvent(ddEvent);
            evenement.setDfEvent(dfEvent);
            evenement.setIdEvent(evenementId);
            evenement.setNomEvent(nomEvent);
            evenement.setThemeEvent(themeEvent);
            evenement.setTelephone(telephone);
            evenement.setAdresseEvent(adresseEvent);
            evenements.add(evenement);
        }


        return evenements;
    }

    public boolean deleteEvenement(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your Event ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/deleventM/"+id;
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
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

    public boolean updateEvenement(Evenement evenement) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Recipe ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/updateeventM";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("idEvent",String.valueOf(evenement.getIdEvent()));
        request.addArgument("nomEvent",evenement.getNomEvent());
        request.addArgument("ddEvent",evenement.getDdEvent().toString());
        request.addArgument("dfEvent",evenement.getDfEvent().toString());
        request.addArgument("themeEvent",evenement.getThemeEvent());
        request.addArgument("adresseEvent",evenement.getAdresseEvent());
        request.addArgument("telephone",String.valueOf(evenement.getTelephone()));


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
