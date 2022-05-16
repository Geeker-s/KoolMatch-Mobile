/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;


import static com.codename1.charts.util.ColorUtil.red;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.model.Interaction;
import com.codename1.uikit.pheonixui.model.user;
import com.codename1.uikit.pheonixui.service.ServiceInteraction;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author wassimromdhane
 */
public class InteractionForm extends Form {

    Resources theme;
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    public ArrayList<user> users;
    public InteractionForm() {

        setTitle("Swipe Now!");
        setLayout(BoxLayout.y());
        setScrollableY(true);

        user connectedUser = new user(1);
        Date date = new Date(1998 - 05 - 14);
        user foulen = new user(2, "foulen@esprit.tn", "1234", "Foulen", "Ben Foulen", date, "Homme", 94366666, "e72630d0111bc84aa75e064f1eb615e4.jpg", "I love Pizza, Suschi, Spagetti and YOU!", 20, 18, 50, "ESPRIT", 36.8991643, 10.1879781, 24122);

//        card(foulen,connectedUser).

        users = ServiceInteraction.getInstance().afficherMatches(connectedUser);

        for (user u : users){
            this.add(card(u,connectedUser));

        }

    }

    public SwipeableContainer card(user x, user connectedUser) {

        Container cardContainer = new Container(BoxLayout.y());
        cardContainer.setHeight(getLayoutHeight());
        Container detailsContainer = new Container(BoxLayout.y());
        Container imgContainer = new Container(new FlowLayout(CENTER, CENTER));



        String url = Statics.BASE_URL+"/uploads/images/"+x.getPhoto_user();

        try {
            enc = EncodedImage.create("/load.png");
            System.out.println("before");
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);

            System.out.println("after"+ imgs.getImageName());
            imgv = new ImageViewer();
            imgv.setImage(imgs);

        }catch (Exception e){
            try {
                imgv = new ImageViewer(Image.createImage("/load.png"));
                System.out.println("loading Catch 1 ");
            } catch (IOException ex) {
                System.out.println("error Catch1");
            }
            System.out.println("error Catch2");
        }
//        Image img = theme.getImage("toolbar-profile-pic.png");
//        ImageViewer image = new ImageViewer(img.scaled(getLayoutWidth(), getLayoutHeight() - 800));

//        ImageViewer img = new ImageViewer(theme.getImage("e72630d0111bc84aa75e064f1eb615e4.jpg").scaled(150, 150));
//        ImageViewer img = new ImageViewer(theme.getImage("/Users/wassimromdhane/Desktop/pp/KoolMatch-Website/public/uploads/images/"+x.getPhoto_user()).scaled(150, 150));
        Label id = new Label("id : " + String.valueOf(x.getId_user()));
        id.setVisible(false);
        Label nom = new Label(x.getNom_user());
        Label adresse = new Label(x.getAdresse_user());
        Label description = new Label(x.getDescription_user());
        Label age = new Label("18 ans");
        Label distance = new Label("8 kilo.");
        Button btnDetail = new Button("More informations");

             imgContainer.add(imgv);
        detailsContainer.addAll(id, nom, description, age, distance);
        cardContainer.addAll(imgContainer, detailsContainer);

        Button btnLike = new Button("Like");
        Button btnDislike = new Button("Dislike");

        Interaction react = new Interaction();
        react.setId_user1(connectedUser.getId_user());
        react.setId_user2(x.getId_user());


        btnLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                react.setType_interaction("o");

                if (ServiceInteraction.getInstance().addInteraction(react)) {
                    Dialog.show("Succes", "Interaction Ajouté", "OK", null);

                } else {
                    Dialog.show("Error", "Request Error", "OK", null);
                }
                btnLike.remove();
                btnDislike.remove();

            }

        });
        btnDislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                react.setType_interaction("x");
                if (ServiceInteraction.getInstance().addInteraction(react)) {
                    Dialog.show("Succes", "Interaction Ajouté", "OK", null);
                } else {
                    Dialog.show("Error", "Request Error", "OK", null);
                }
                btnLike.remove();
                btnDislike.remove();

            }
        });
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(btnLike, btnDislike),
                cardContainer);
    }

}
