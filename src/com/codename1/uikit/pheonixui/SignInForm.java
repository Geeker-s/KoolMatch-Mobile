/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.service.ServiceUtilisateur;

/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");


        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de Passe", 20, TextField.PASSWORD);
        Button cnx = new Button("Connexion");
        Button creer = new Button("Créer compte");

        creer.addActionListener(e -> new InscriptionForm(Resources.getGlobalResources()).show());
        creer.setUIID("link");
        Label DontHaveAnAccount = new Label("Don't have an account?");


        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);


        //mp oublié
        Button  mp = new Button("oublier mot de passe?","CenterLabel");

        Container content = BoxLayout.encloseY(

                new Label("Connexion", "LogoLabel"),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                cnx,
                FlowLayout.encloseCenter(DontHaveAnAccount , creer),mp

        );

        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        cnx.requestFocus();

        cnx.addActionListener(e->
        {
            if (!email.getText().isEmpty() && !password.getText().isEmpty())
            ServiceUtilisateur.getInstance().signin(email.getText(),password.getText(),resourceObjectInstance);
        });

        //Mp oublie event
        mp.addActionListener((e) -> {

//            new ActivateForm(Resources.getGlobalResources()).show();


        });

    }



    //-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
//        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
//        gui_Container_1.setScrollableY(true);
//        gui_Container_1.setName("Container_1");
//        gui_Container_1.addComponent(gui_Label_1);
//        gui_Container_1.addComponent(gui_Component_Group_1);
//        gui_Component_Group_1.setName("Component_Group_1");
//        gui_Component_Group_1.addComponent(gui_Text_Field_2);
//        gui_Component_Group_1.addComponent(gui_Text_Field_1);
//        gui_Text_Field_2.setText("TextField");
//        gui_Text_Field_2.setName("Text_Field_2");
//        gui_Text_Field_1.setText("TextField");
//        gui_Text_Field_1.setName("Text_Field_1");
//        gui_Container_1.addComponent(gui_Button_2);
//        gui_Container_1.addComponent(gui_Button_3);
//        gui_Label_1.setUIID("CenterLabel");
//        gui_Label_1.setName("Label_1");
//        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
//        gui_Component_Group_1.setName("Component_Group_1");
//        gui_Button_2.setText("Sign In");
//        gui_Button_2.setName("Button_2");
//        gui_Button_3.setText("Forgot Your Password");
//        gui_Button_3.setUIID("CenterLabelSmall");
//        gui_Button_3.setName("Button_3");
//        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
//        gui_Container_1.setScrollableY(true);
//        gui_Container_1.setName("Container_1");
//        gui_Button_1.setText("Create New Account");
//        gui_Button_1.setUIID("CenterLabel");
//        gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {

    }

}
