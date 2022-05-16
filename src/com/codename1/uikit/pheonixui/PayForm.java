package com.codename1.uikit.pheonixui;

import com.codename1.components.SignatureComponent;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.uikit.pheonixui.service.CartService;

public class PayForm extends BaseForm {
    public PayForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public PayForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Cart", "Title"),
                        new Label(String.valueOf(CartService.getInstance().getProducts().size()), "InboxNumber")
                )
        );
        installSidemenu(resourceObjectInstance);
        TextField textField_addr = new TextField("","Address");
        SignatureComponent sig = new SignatureComponent();
        sig.addActionListener((evt)-> {
            if(CartService.getInstance().pay(textField_addr.getText())){
                {
                    Dialog.show("Success","Votre commande à été passez","OK",null);
                    new ProductsForm().show();
                }
            }
        });
        Label label = new Label("Signez votre commande");
        add(label);
        add(textField_addr);
        add(sig);


    }
    
//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(CENTER));
        setTitle("PayForm");
        setName("PayForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
