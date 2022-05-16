package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.model.Recette;
import com.codename1.uikit.pheonixui.service.EvenementService;
import com.codename1.uikit.pheonixui.service.RecetteService;

public class AddRecetteForm extends BaseForm {
    public AddRecetteForm() {
        this(Resources.getGlobalResources());
    }

    public AddRecetteForm(Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField("","Name");
        TextField textField_categorie = new TextField("","Categorie");
        TextField textField_description = new TextField("","Description");
        TextField textField_duree = new TextField("","Duree",2,TextArea.NUMERIC);
        TextField textField_photo = new TextField("","Photo");

        addAll(textField_name,textField_categorie,textField_description,textField_duree,textField_photo);
        Button mb = new Button("Submit");
        add(mb);
        mb.addActionListener(evt -> {
            Recette recette = new Recette();
            recette.setNomRecette(textField_name.getText());
            recette.setCategorieRecette(textField_categorie.getText());
            recette.setDescriptionRecette(textField_description.getText());
            recette.setDureeRecette(textField_duree.getText());
            recette.setPhotoRecette(textField_photo.getText());

            if(RecetteService.getInstance().addRecette(recette))
                Dialog.show("Success","Recipe Added","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("AddEventForm");
        setName("AddEventForms");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
