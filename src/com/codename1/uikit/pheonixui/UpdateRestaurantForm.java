package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Restaurant;
import com.codename1.uikit.pheonixui.service.EvenementService;
import com.codename1.uikit.pheonixui.service.RestaurantService;

public class UpdateRestaurantForm extends BaseForm {
    public UpdateRestaurantForm(Restaurant restaurant) {
        this(Resources.getGlobalResources(),restaurant);
    }

    public UpdateRestaurantForm(Resources resourceObjectInstance,Restaurant restaurant) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField(restaurant.getNomRestaurant(),"Name");
        TextField textField_adresse = new TextField(restaurant.getAdresseRestaurant(),"Adress");
        TextField textField_telephone= new TextField(restaurant.getTelephoneRestaurant(),"Phone",2,TextArea.NUMERIC);
        TextField textField_site= new TextField(restaurant.getSitewebRestaurant(),"Website");
        TextField textField_specialite= new TextField(restaurant.getSpecialiteRestaurant(),"Sepciality");
        TextField textField_imagestructure= new TextField(restaurant.getImageStructureResturant(),"Structure Image");
        TextField textField_nbplace = new TextField(restaurant.getNbPlaceresto(),"Nombre de places",2,TextArea.NUMERIC);
        TextField textField_image = new TextField(restaurant.getImage(),"Image");
        TextField textField_description = new TextField(restaurant.getDescription(),"Description");
        TextField textField_lien = new TextField(restaurant.getLien(),"Link");
        TextField textField_gerant = new TextField(restaurant.getIdGerant(),"User",2,TextArea.NUMERIC);

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        addAll(textField_name,textField_specialite,textField_adresse,textField_description,textField_nbplace,textField_gerant,textField_telephone,textField_site,textField_lien,textField_image,textField_imagestructure);
        Button mb = new Button("Submit");
        add(mb);

        Button db = new Button("Delete");
        add(db);

        db.addActionListener(evt -> {
            boolean bool = RestaurantService.getInstance().deleteRestaurant(restaurant.getIdRestaurant());
            if (bool){
                Dialog.show("Success","Restaurant has been deleted","OK",null);
            }
        });


        mb.addActionListener(evt -> {
            restaurant.setNomRestaurant(textField_name.getText());
            restaurant.setSpecialiteRestaurant(textField_specialite.getText());
            restaurant.setAdresseRestaurant(textField_adresse.getText());
            restaurant.setDescription(textField_description.getText());
            restaurant.setNbPlaceresto(textField_nbplace.getText());
            restaurant.setIdGerant(textField_gerant.getText());
            restaurant.setTelephoneRestaurant(textField_telephone.getText());
            restaurant.setSitewebRestaurant(textField_site.getText());
            restaurant.setLien(textField_lien.getText());
            restaurant.setImage(textField_image.getText());
            restaurant.setImageStructureResturant(textField_imagestructure.getText());

            if(RestaurantService.getInstance().updateRestaurant(restaurant))
                Dialog.show("Success","Reservation Updated","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Update Restaurant");
        setName("Update Restaurant");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
