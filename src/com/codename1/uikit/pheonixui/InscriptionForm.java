/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.service.ServiceUtilisateur;

/**
 *
 * @author Boughnimi
 */
public class InscriptionForm extends BaseForm {

    int interet;

    public InscriptionForm(Resources res) {
        initGuiBuilderComponents(Resources.getGlobalResources());
        setLayout(new BorderLayout());
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prénom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de Passe", 20, TextField.PASSWORD);
        TextField numerotelephone  = new TextField("", "Numéro Téléphone", 20, TextField.ANY);
        TextField ddn  = new TextField("", "Date de Naissance", 20, TextField.ANY);
        TextField sexUser  = new TextField("", "Sexe", 20, TextField.ANY);
        Picker picker1 = new Picker();
        picker1.setType(Display.PICKER_TYPE_STRINGS);
        picker1.setStrings("Moins de fois","3 fois","plus de 3 fois");
        picker1.setSelectedString("Combien de fois mangez-vous par jour ? ");


        Picker picker2 = new Picker();
        picker2.setType(Display.PICKER_TYPE_STRINGS);
        picker2.setStrings("sucré","salé","les 2");
        picker2.setSelectedString("Que préférez vous ? ");
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE);
        Picker picker3 = new Picker();
        picker3.setType(Display.PICKER_TYPE_STRINGS);
        picker3.setStrings("A la maison","Manger dans un restaurant","commandez votre repas");
        picker3.setSelectedString("Que préférez vous ? ");

        Picker picker4 = new Picker();
        picker4.setType(Display.PICKER_TYPE_STRINGS);
        picker4.setStrings("Nourriture Saine","Fast Food","les 2");
        picker4.setSelectedString("Que préférez vous ? ");

        Picker picker5 = new Picker();
        picker5.setType(Display.PICKER_TYPE_STRINGS);
        picker5.setStrings("Légume","Viande","Poissons");
        picker5.setSelectedString("Que préférez vous ? ");

        Button next = new Button("Inscription");
        Button cnx = new Button("Connexion");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        numerotelephone.setSingleLineTextArea(false);
        ddn.setSingleLineTextArea(false);
        sexUser.setSingleLineTextArea(false);



        gui_Container_1.addAll(nom,prenom,email,password,numerotelephone,sexUser,dateTimePicker,picker1,picker2,picker3,picker4,picker5,next,cnx,alreadHaveAnAccount);
        add(CENTER,gui_Container_1);
        next.requestFocus();
        cnx.addActionListener(evt -> {
            new SignInForm().show();
        });
        next.addActionListener((e) -> {
            interet = picker1.getSelectedStringIndex() + picker2.getSelectedStringIndex()  + picker3.getSelectedStringIndex()
                    + picker4.getSelectedStringIndex() + picker4.getSelectedStringIndex() + picker5.getSelectedStringIndex();
            ServiceUtilisateur.getInstance().signup(nom, prenom, email, password, numerotelephone,interet,sexUser,dateTimePicker, res);
            Dialog.show("Success","compte créer","OK",null);
            new SignInForm().show();
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
    }// </editor-fold>

    //-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

}
