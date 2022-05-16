package com.codename1.uikit.pheonixui;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.uikit.pheonixui.service.ReservationService;


public class BackOfficeWindow extends BaseForm {
    public BackOfficeWindow() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public BackOfficeWindow(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new com.codename1.ui.Label("BACK OFFICE :: ADMIN PANEL", "Title"),
                        new com.codename1.ui.Label(String.valueOf(ReservationService.getInstance().getReservations().size())+" reservations", "InboxNumber")
                )
        );
        installSidemenu(resourceObjectInstance);
        Button buttonEvent = new Button("Event");
        Button buttonRecette = new Button("Recipe");
        Button buttonRestaurant = new Button("Restaurant");
        Button buttonReservation = new Button("Reservation");


        buttonEvent.addActionListener(evt -> {
            new EventsForm().show();
        });
        buttonRecette.addActionListener(evt -> {
            new RecipesForm().show();
        });
        buttonRestaurant.addActionListener(evt -> {
            new RestaurantForm().show();
        });
        buttonReservation.addActionListener(evt -> {
            new ReservationForm().show();
        });
        addAll(buttonEvent,buttonRecette,buttonRestaurant,buttonReservation);
    }
    
////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("BackOfficeWindow");
        setName("BackOfficeWindow");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
