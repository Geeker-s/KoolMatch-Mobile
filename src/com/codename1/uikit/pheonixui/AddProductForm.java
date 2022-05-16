package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.service.ProductService;

public class AddProductForm extends com.codename1.ui.Form {
    public AddProductForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public AddProductForm(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        TextField textField_name = new TextField("","Name");
        TextField textField_price = new TextField("","Price");
        TextField textField_design = new TextField("","Description");
        TextField textField_quant = new TextField("","Quantité");


        Picker stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("Category 1 ","Category 2 ","Category 3 ","Category 4 ","Category 5 ");

        addAll(textField_name,textField_price,textField_design,textField_quant,stringPicker);
        Button mb = new Button("Submit");
        add(mb);
        mb.addActionListener(evt -> {
            Product product = new Product();
            product.setName(textField_name.getText());
            product.setPrice(textField_price.getText());
            product.setDesignation(textField_design.getText());
            product.setQuant(textField_quant.getText());
            product.setCat(stringPicker.getSelectedString());
            if(ProductService.getInstance().addProduct(product))
                Dialog.show("Success","Product Added","OK",null);
        });
        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("AddProductForm");
        setName("AddProductForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
