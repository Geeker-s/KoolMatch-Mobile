package com.codename1.uikit.pheonixui;//package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Reservation;
import com.codename1.uikit.pheonixui.service.EvenementService;
import com.codename1.uikit.pheonixui.service.ReservationService;

public class UpdateReservationForm extends BaseForm {
    public UpdateReservationForm(Reservation reservation) {
        this(Resources.getGlobalResources(),reservation);
    }

    public UpdateReservationForm(Resources resourceObjectInstance, Reservation reservation) {
        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_adresse = new TextField(reservation.getAdresse(),"Adresse");
        TextField textField_nomResto = new TextField(reservation.getNomResto(),"nomResto");
        TextField textField_nbplace = new TextField(reservation.getNbplaceReservation(),"Nombre de places",2,TextArea.NUMERIC);
        TextField textField_image = new TextField(reservation.getImage(),"Image");
        TextField textField_user = new TextField(reservation.getIdUser(),"User",2,TextArea.NUMERIC);

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        addAll(textField_adresse,textField_nomResto,date,textField_nbplace,textField_image,textField_user);
        Button mb = new Button("Submit");
        add(mb);

        Button db = new Button("Delete");
        add(db);

        db.addActionListener(evt -> {
            boolean bool = ReservationService.getInstance().deleteReservation(reservation.getIdReservation());
            if (bool){
                Dialog.show("Success","Reservation has been deleted","OK",null);
            }
        });

        mb.addActionListener(evt -> {
            reservation.setAdresse(textField_adresse.getText());
            reservation.setNomResto(textField_nomResto.getText());
            reservation.setDateReservation(date.getDate());
            reservation.setNbplaceReservation(textField_nbplace.getText());
            reservation.setImage(textField_image.getText());
            reservation.setIdUser(textField_user.getText());

            if(ReservationService.getInstance().updateReservation(reservation))
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
        setTitle("Update Reservation");
        setName("Update Reservation");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
