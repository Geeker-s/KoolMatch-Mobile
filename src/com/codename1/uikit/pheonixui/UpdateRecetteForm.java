package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Recette;
import com.codename1.uikit.pheonixui.service.EvenementService;
import com.codename1.uikit.pheonixui.service.RecetteService;

public class UpdateRecetteForm extends BaseForm {
    public UpdateRecetteForm(Recette recette) {
        this(Resources.getGlobalResources(),recette);
    }

    public UpdateRecetteForm(Resources resourceObjectInstance, Recette recette) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField(recette.getNomRecette(),"Name");
        TextField textField_categorie = new TextField(recette.getCategorieRecette(),"Categorie");
        TextField textField_description = new TextField(recette.getDescriptionRecette(),"Description");
        TextField textField_duree = new TextField(recette.getDureeRecette(),"Duree",2,TextArea.NUMERIC);
        TextField textField_photo = new TextField(recette.getPhotoRecette(),"Photo");

        addAll(textField_name,textField_categorie,textField_description,textField_duree,textField_photo);
        Button mb = new Button("Submit");
        add(mb);

        Button db = new Button("Delete");
        add(db);

        db.addActionListener(evt -> {
            boolean bool = RecetteService.getInstance().deleteRecette(recette.getIdRecette());
            if (bool){
                Dialog.show("Success","Recipe has been deleted","OK",null);
            }
        });

        mb.addActionListener(evt -> {
            recette.setNomRecette(textField_name.getText());
            recette.setCategorieRecette(textField_categorie.getText());
            recette.setDescriptionRecette(textField_description.getText());
            recette.setDureeRecette(textField_duree.getText());
            recette.setPhotoRecette(textField_photo.getText());

            if(RecetteService.getInstance().updateRecette(recette))
                Dialog.show("Success","Recipe Updated","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Update Recipe");
        setName("Update Recipe");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
