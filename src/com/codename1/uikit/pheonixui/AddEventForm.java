package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.service.EvenementService;

public class AddEventForm extends BaseForm {
    public AddEventForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AddEventForm(Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField("","Name");
        TextField textField_theme = new TextField("","Theme");
        TextField textField_adresse = new TextField("","Adresse");
        TextField textField_telephone = new TextField("","Telephone");


        Picker debutPicker = new Picker();
        debutPicker.setType(Display.PICKER_TYPE_DATE);

        Picker finPicker = new Picker();
        finPicker.setType(Display.PICKER_TYPE_DATE);

        addAll(textField_name,debutPicker,finPicker,textField_theme,textField_adresse,textField_telephone);
        Button mb = new Button("Submit");
        add(mb);
        mb.addActionListener(evt -> {
            Evenement evenement = new Evenement();
            evenement.setNomEvent(textField_name.getText());
            evenement.setDdEvent(debutPicker.getDate());
            evenement.setDfEvent(finPicker.getDate());
            evenement.setThemeEvent(textField_theme.getText());
            evenement.setAdresseEvent(textField_adresse.getText());
            evenement.setTelephone(textField_telephone.getText());

            if(EvenementService.getInstance().addEvenement(evenement))
                Dialog.show("Success","Event Added","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

//////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("AddEventForm");
        setName("AddEventForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
