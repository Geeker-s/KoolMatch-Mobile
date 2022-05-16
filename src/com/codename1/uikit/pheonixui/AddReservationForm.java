package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Recette;
import com.codename1.uikit.pheonixui.model.Reservation;
import com.codename1.uikit.pheonixui.service.RecetteService;
import com.codename1.uikit.pheonixui.service.ReservationService;

public class AddReservationForm extends BaseForm {
    public AddReservationForm() {
        this(Resources.getGlobalResources());
    }

    public AddReservationForm(Resources resourceObjectInstance) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_adresse = new TextField("","Adresse");
        TextField textField_nomResto = new TextField("","nomResto");
        TextField textField_nbplace = new TextField("","Nombre de places",2,TextArea.NUMERIC);
        TextField textField_image = new TextField("","Image");
        TextField textField_user = new TextField("","User",2,TextArea.NUMERIC);

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        addAll(textField_adresse,textField_nomResto,date,textField_nbplace,textField_image,textField_user);
        Button mb = new Button("Submit");
        add(mb);
        mb.addActionListener(evt -> {
            Reservation reservation = new Reservation();
            reservation.setAdresse(textField_adresse.getText());
            reservation.setNomResto(textField_nomResto.getText());
            reservation.setDateReservation(date.getDate());
            reservation.setNbplaceReservation(textField_nbplace.getText());
            reservation.setImage(textField_image.getText());
            reservation.setIdUser(textField_user.getText());

            if(ReservationService.getInstance().addReservation(reservation))
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
