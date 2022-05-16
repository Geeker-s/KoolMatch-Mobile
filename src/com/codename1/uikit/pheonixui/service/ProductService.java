package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Product> products = new ArrayList<>();
    private static ProductService instance;

    private ProductService() {
        request = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    public boolean addProduct(Product product){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Adding your new product ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/produit/add";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("lib",product.getName());
        request.addArgument("desc",product.getDesignation());
        request.addArgument("prix",product.getPrice());
        request.addArgument("quant",product.getQuant());
        request.addArgument("cat",product.getCat());


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

    public ArrayList<Product> getProducts(){
        System.out.println("In Get Products Method");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/api/produit/show-all";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetProducts::ResponseListener");
                products = parseProduct(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return products;
    }

    private ArrayList<Product> parseProduct(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException {
        System.out.println("In Parse Product Method");
        products = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> productListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) productListJson.get("root");
        for (Map<String,Object> obj : list){

            float productIdF = Float.parseFloat(obj.get("id").toString());
            String productId = String.valueOf((int) productIdF);
            String name = obj.get("lib").toString();
            String quant = obj.get("quant").toString();
            String prix = obj.get("prix").toString();
            String design = obj.get("desc").toString();
            String cat = obj.get("cat").toString();
            String imgPath = obj.get("img").toString();


            Product product = new Product();
            product.setId(productId);
            product.setName(name);
            product.setDesignation(design);
            product.setPrice(prix);
            product.setQuant(quant);
            product.setCat(cat);
            product.setImg(imgPath);
            products.add(product);
        }


        return products;
    }

    public boolean deleteProduct(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your product ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/produit/delete/"+id;
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

    public boolean updateProduct(Product product) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Product ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/produit/update/"+product.getId();
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);



        request.addArgument("lib",product.getName());
        request.addArgument("desc",product.getDesignation());
        request.addArgument("prix",product.getPrice());
        request.addArgument("quant",product.getQuant());
        request.addArgument("cat",product.getCat());


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
