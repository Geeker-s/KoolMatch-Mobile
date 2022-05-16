package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.service.EvenementService;

public class UpdateEventForm extends BaseForm {
    public UpdateEventForm(Evenement evenement) {
        this(Resources.getGlobalResources(),evenement);
    }

    public UpdateEventForm(Resources resourceObjectInstance, Evenement evenement) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField(evenement.getNomEvent(),"Name");
        TextField textField_theme = new TextField(evenement.getThemeEvent(),"Theme");
        TextField textField_adresse = new TextField(evenement.getAdresseEvent(),"Adresse");
        TextField textField_telephone = new TextField(evenement.getTelephone(),"Telephone");


        Picker debutPicker = new Picker();
        debutPicker.setType(Display.PICKER_TYPE_DATE);
        debutPicker.setDate(evenement.getDdEvent());

        Picker finPicker = new Picker();
        finPicker.setType(Display.PICKER_TYPE_DATE);
        finPicker.setDate(evenement.getDfEvent());

        addAll(textField_name,debutPicker,finPicker,textField_theme,textField_adresse,textField_telephone);
        Button mb = new Button("Submit");
        add(mb);

        Button db = new Button("Delete");
        add(db);

        db.addActionListener(evt -> {
            boolean bool = EvenementService.getInstance().deleteEvenement(evenement.getIdEvent());
            if (bool){
                Dialog.show("Success","Event has been deleted","OK",null);
            }
        });

        mb.addActionListener(evt -> {
            evenement.setNomEvent(textField_name.getText());
            evenement.setDdEvent(debutPicker.getDate());
            evenement.setDfEvent(finPicker.getDate());
            evenement.setThemeEvent(textField_theme.getText());
            evenement.setAdresseEvent(textField_adresse.getText());
            evenement.setTelephone(textField_telephone.getText());

            if(EvenementService.getInstance().updateEvenement(evenement))
                Dialog.show("Success","Event Updated","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Update Event");
        setName("Update Event");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
