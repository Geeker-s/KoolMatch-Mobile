package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Restaurant;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantService instance;

    private RestaurantService() {
        request = new ConnectionRequest();
    }

    public static RestaurantService getInstance() {
        if (instance == null)
            instance = new RestaurantService();
        return instance;
    }

    public boolean addRestaurant(Restaurant restaurant){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Adding your new restaurant ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/resto/ajouter/resto";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("nomRestaurant",restaurant.getNomRestaurant());
        request.addArgument("adresseRestaurant",restaurant.getAdresseRestaurant());
        request.addArgument("telephoneRestaurant",String.valueOf(restaurant.getTelephoneRestaurant()));
        request.addArgument("sitewebRestaurant",restaurant.getSitewebRestaurant());
        request.addArgument("specialiteRestaurant",restaurant.getSpecialiteRestaurant());
        request.addArgument("imageStructureResturant",restaurant.getImageStructureResturant());
        request.addArgument("idGerant",String.valueOf(restaurant.getIdGerant()));
        request.addArgument("image",restaurant.getImage());
        request.addArgument("nbPlaceresto",String.valueOf(restaurant.getNbPlaceresto()));
        request.addArgument("description",restaurant.getDescription());
        request.addArgument("lien",restaurant.getLien());

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

    public ArrayList<Restaurant> getRestaurants(){
        System.out.println("In Get Restaurants Method");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/afficher/RESTO";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetRestaurants::ResponseListener");
                restaurants = parseRestaurant(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return restaurants;
    }

    private ArrayList<Restaurant> parseRestaurant(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException {
        System.out.println("In Parse Restaurant Method");
        restaurants = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> restaurantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) restaurantListJson.get("root");
        for (Map<String,Object> obj : list){

            float restaurantIdF = Float.parseFloat(obj.get("idRestaurant").toString());
            int restaurantId = Integer.parseInt(String.valueOf((int) restaurantIdF));
            String nomRestaurant = obj.get("nomRestaurant").toString();
            String adresseRestaurant = obj.get("adresseRestaurant").toString();
            String sitewebRestaurant = obj.get("sitewebRestaurant").toString();
            String specialiteRestaurant = obj.get("specialiteRestaurant").toString();
            String image = obj.get("image").toString();
            String description = obj.get("description").toString();
            String lien = obj.get("lien").toString();
            String  telephoneRestaurant = obj.get("telephoneRestaurant").toString();
            String idGerant = obj.get("idGerant").toString();
            String archive = obj.get("archive").toString();
            String nbPlaceresto = obj.get("nbPlaceresto").toString();

            Restaurant restaurant = new Restaurant();
            restaurant.setIdRestaurant(restaurantId);
            restaurant.setNomRestaurant(nomRestaurant);
            restaurant.setAdresseRestaurant(adresseRestaurant);
            restaurant.setSitewebRestaurant(sitewebRestaurant);
            restaurant.setSpecialiteRestaurant(specialiteRestaurant);
            restaurant.setImage(image);
            restaurant.setDescription(description);
            restaurant.setLien(lien);
            restaurant.setTelephoneRestaurant(telephoneRestaurant);
            restaurant.setIdGerant(idGerant);
            restaurant.setArchive(archive);
            restaurant.setNbPlaceresto(nbPlaceresto);
            restaurants.add(restaurant);
        }


        return restaurants;
    }

    public boolean deleteRestaurant(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your restaurant ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/delete/resto/"+id;
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

    public boolean updateRestaurant(Restaurant restaurant) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Restaurant ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/modifier/resto/"+restaurant.getIdRestaurant();
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);



        request.addArgument("nomRestaurant",restaurant.getNomRestaurant());
        request.addArgument("adresseRestaurant",restaurant.getAdresseRestaurant());
        request.addArgument("telephoneRestaurant",String.valueOf(restaurant.getTelephoneRestaurant()));
        request.addArgument("sitewebRestaurant",restaurant.getSitewebRestaurant());
        request.addArgument("specialiteRestaurant",restaurant.getSpecialiteRestaurant());
        request.addArgument("imageStructureResturant",restaurant.getImageStructureResturant());
        request.addArgument("idGerant",String.valueOf(restaurant.getIdGerant()));
        request.addArgument("image",restaurant.getImage());
        request.addArgument("nbPlaceresto",String.valueOf(restaurant.getNbPlaceresto()));
        request.addArgument("description",restaurant.getDescription());
        request.addArgument("lien",restaurant.getLien());


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
