/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui.service;

import com.codename1.io.*;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.CartForm;
import com.codename1.uikit.pheonixui.ProductsForm;
import com.codename1.uikit.pheonixui.utils.Statics;
import java.util.Map;

/**
 *
 * @author Boughnimi
 */
public class ServiceUtilisateur {
      //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
      public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
      
      
    //Signup
    public void signup(TextField nom, TextField prenom, TextField email, TextField password, TextField numerotelephone, int interet, TextField sexUser, Picker ddn , Resources res ) {
        System.out.println(ddn.getDate().toString());
        String url = Statics.BASE_URL+"/json/adduser?nomUser="+nom.getText().toString()+"&prenomUser="+prenom.getText().toString()+"&emailUser="+email.getText().toString()+
                "&passwordUser="+password.getText().toString()+"&telephoneUser="+numerotelephone.getText().toString() + "&sexeUser=" + sexUser.getText().toString()
                +"&datenaissanceUser="+ddn.getDate()+"&maxdistanceUser=50&preferredminageUser=18&preferredmaxageUser=60&adresseUser=xxx&latitude=50&longitude=50&interetUser="+interet;
        req.setUrl(url);
       //Control saisi
        if(nom.getText().equals("") && prenom.getText().equals("") && email.getText().equals("") && password.getText().equals("")&& numerotelephone.getText().equals("")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            System.out.println("data ===>"+responseData);
                    Dialog.show("Success","Success","OK",null);
        }
        );
               NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void signin(String email,String password,Resources res)
    {
         String url = Statics.BASE_URL+"/json/signin";
         req.setUrl(url);
         req.addArgument("email",email);
         req.addArgument("password",password);
         req.setPost(true);
          req.addResponseListener((e)-> {
              JSONParser j = new JSONParser();
              String json = new String(req.getResponseData()) + "";
              System.out.println(json);
              try{
                if (json.equals("failed"))
                    Dialog.show("Echec d'authentification","Email ou mot de passe incorrect","OK",null);
              
              else{
                    System.out.println("data =="+json);
                    Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(user.get("id"));
                    Preferences.set("id", (int)Float.parseFloat(String.valueOf(user.get("id"))));
                    Preferences.set("email", String.valueOf(user.get("email")));
                    System.out.println(Preferences.get("id","1"));
                    System.out.println(Preferences.get("email","1"));
                  if (user.size() > 0)
                  {
                    new ProductsForm().show();
                  }
              }
              
          }catch (Exception ex){
              ex.printStackTrace();
          }
          
          });
         NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public String getPasswordByEmail(String email, Resources rs ) {
        
          // String mp=" "; 
           String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
           req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
           req.setUrl(url);
        
           req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
           json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
}
