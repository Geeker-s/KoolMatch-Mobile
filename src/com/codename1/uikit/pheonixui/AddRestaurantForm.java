package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Reservation;
import com.codename1.uikit.pheonixui.model.Restaurant;
import com.codename1.uikit.pheonixui.service.ReservationService;
import com.codename1.uikit.pheonixui.service.RestaurantService;

public class AddRestaurantForm extends BaseForm {
    public AddRestaurantForm() {
        this(Resources.getGlobalResources());
    }

    public AddRestaurantForm(Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField("","Name");
        TextField textField_adresse = new TextField("","Adress");
        TextField textField_telephone= new TextField("","Phone",2,TextArea.NUMERIC);
        TextField textField_site= new TextField("","Website");
        TextField textField_specialite= new TextField("","Sepciality");
        TextField textField_imagestructure= new TextField("","Structure Image");
        TextField textField_nbplace = new TextField("","Nombre de places",2,TextArea.NUMERIC);
        TextField textField_image = new TextField("","Image");
        TextField textField_description = new TextField("","Description");
        TextField textField_lien = new TextField("","Link");
        TextField textField_gerant = new TextField("","User",2,TextArea.NUMERIC);

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        addAll(textField_name,textField_specialite,textField_adresse,textField_description,textField_nbplace,textField_gerant,textField_telephone,textField_site,textField_lien,textField_image,textField_imagestructure);
        Button mb = new Button("Submit");
        add(mb);
        mb.addActionListener(evt -> {
            Restaurant restaurant = new Restaurant();
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

            if(RestaurantService.getInstance().addRestaurant(restaurant))
                Dialog.show("Success","Reservation Added","OK",null);
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
