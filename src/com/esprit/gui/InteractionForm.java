/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Interaction;
import com.esprit.services.ServiceInteraction;

/**
 *
 * @author wassimromdhane
 */
public class InteractionForm extends Form {

    public InteractionForm(Form previous) {

        setTitle("Swipe Now!");
        setLayout(BoxLayout.y());

        int connectedUser = 1;
        int user = 1;
        String type = "x";
        
        TextField tfId = new TextField("", "ID du Utilisateur");
        tfId.setVisible(false);

        TextField tfName = new TextField("", "Nom du Utilisateur");

        Button btnLike = new Button("Like");
        Button btnDislike = new Button("Dislike");

        btnLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Interaction react = new Interaction();
                react.setId_user1(connectedUser);
                react.setId_user2(user);
                react.setType_interaction(type);
                
                if (ServiceInteraction.getInstance().addInteraction(react)) {
                    Dialog.show("Succes", "Interaction Ajouté", "OK", null);
                } else {
                    Dialog.show("Error", "Request Error", "OK", null);
                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        addAll(btnLike, btnDislike);

    }

}
