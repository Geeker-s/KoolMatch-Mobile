package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Commande;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class CartService {
    private static CartService obj = new CartService();
    private  ArrayList<Product> products;
    public CartService()
    {
        products = new ArrayList<>();
    }
    public static CartService getInstance()
    {
        return obj;
    }


    public void clearCart(){
        products.clear();

    }
    public ArrayList<Product> getProducts(){
        return this.products;
    }
    public void removeFromCart(Product product){
        products.remove(product);
    }
    boolean resultOK;
    private ArrayList<Commande> commandes = new ArrayList<>();
    public void AddToCart(Product product){
        product.setQuantToBuy(1);
        products.add(product);
        String url = Statics.BASE_URL+"/api/panier/add";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("product",product.getId());
        request.addArgument("user","1");


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

    }

    public ArrayList<Commande> getCommands(){
        System.out.println("In Get Events Method");
        System.out.println("Got Id");
        ConnectionRequest request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/api/commande/show/1";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetEvents::ResponseListener");
                commandes = parseEvent(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return commandes;
    }

    private ArrayList<Commande> parseEvent(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException, ParseException {
        System.out.println("In Parse Event Method");
        commandes = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
        for (Map<String,Object> obj : list){

            float comIdF = Float.parseFloat(obj.get("id").toString());
            String comId = String.valueOf((int) comIdF);

            String date = obj.get("adresse").toString();
            String ref = obj.get("etat").toString();
            Commande commande = new Commande();

            commande.setId(comId);
            commande.setRef(ref);
            commande.setDate(comId);
            commande.setTotal(date);
            commandes.add(commande);
        }


        return commandes;
    }
    public boolean pay(String address) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Sending ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/commande/add";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        request.addArgument("adresse", address);
        request.addArgument("user", "1");
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        clearCart();
        return resultOK;
    }
    public boolean addToDataBaseCart(Product product) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Sending ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/commande/add";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        request.addArgument("product", product.getId());
        request.addArgument("user", "1");
        request.addArgument("quantity", "1");
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

    public void updateCommands(int selectedStringIndex, Object o) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Sending ...");
        status.setShowProgressIndicator(true);
        status.show();
        System.out.println(o);
        String url = Statics.BASE_URL+"/api/commande/update/"+ o.toString();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        System.out.println(o);
        request.addArgument("commande",o.toString()); // TODO: a changer dans l'integration
        request.addArgument("etat", String.valueOf(selectedStringIndex));

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
    }


    public void updateCartItemQuantity(String quantity,String id) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Sending ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/panier/update/"+ id;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        request.addArgument("quantity", String.valueOf(quantity));

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
    }
}
