package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Recette;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecetteService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Recette> recettes = new ArrayList<>();
    private static RecetteService instance;

    private RecetteService() {
        request = new ConnectionRequest();
    }

    public static RecetteService getInstance() {
        if (instance == null)
            instance = new RecetteService();
        return instance;
    }

    public boolean addRecette(Recette recette){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Adding your new recipe ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/addRecetteJ/new";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("CategorieRecette",recette.getCategorieRecette());
        request.addArgument("DescriptionRecette",recette.getDescriptionRecette());
        request.addArgument("NomRecette",recette.getNomRecette());
        request.addArgument("DureeRecette",String.valueOf(recette.getDureeRecette()));
        request.addArgument("PhotoRecette",recette.getPhotoRecette());


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

    public ArrayList<Recette> getRecettes(){
        System.out.println("In Get Recettes Method");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/AfficherJ";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetRecettes::ResponseListener");
                recettes = parseRecette(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return recettes;
    }

    private ArrayList<Recette> parseRecette(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException {
        System.out.println("In Parse Recipe Method");
        recettes = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> recetteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) recetteListJson.get("root");
        for (Map<String,Object> obj : list){

            float recetteIdF = Float.parseFloat(obj.get("idRecette").toString());
            int recetteId = Integer.parseInt(String.valueOf((int) recetteIdF));
            String categorieRecette = obj.get("categorieRecette").toString();
            String descriptionRecette = obj.get("descriptionRecette").toString();
            String nomRecette = obj.get("nomRecette").toString();
            String dureeRecette = obj.get("dureeRecette").toString();
            String photoRecette = obj.get("photoRecette").toString();


            Recette recette = new Recette();
            recette.setIdRecette(recetteId);
            recette.setNomRecette(nomRecette);
            recette.setDureeRecette(dureeRecette);
            recette.setPhotoRecette(photoRecette);
            recette.setDescriptionRecette(descriptionRecette);
            recette.setCategorieRecette(categorieRecette);
            recettes.add(recette);
        }


        return recettes;
    }

    public boolean deleteRecette(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your Recipe ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/supprimerJ/"+id;
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

    public boolean updateRecette(Recette recette) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Recipe ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/updateJ/"+recette.getIdRecette();
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);



        request.addArgument("CategorieRecette",recette.getCategorieRecette());
        request.addArgument("DescriptionRecette",recette.getDescriptionRecette());
        request.addArgument("NomRecette",recette.getNomRecette());
        request.addArgument("DureeRecette",String.valueOf(recette.getDureeRecette()));
        request.addArgument("PhotoRecette",recette.getPhotoRecette());


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
