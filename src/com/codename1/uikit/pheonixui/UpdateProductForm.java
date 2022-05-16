package com.codename1.uikit.pheonixui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.spinner.Picker;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.service.ProductService;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;

public class UpdateProductForm extends com.codename1.ui.Form {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    public UpdateProductForm(Product product) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),product);
    }
    
    public UpdateProductForm(com.codename1.ui.util.Resources resourceObjectInstance,Product product) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");
        String url = Statics.BASE_URL+"/photo/"+product.getImg();

        //getting image
        try {
            enc = EncodedImage.create("/load.png");
            System.out.println("before");
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);

            System.out.println("after"+ imgs.getImageName());
            imgv = new ImageViewer();
            imgv.setImage(imgs);

        }catch (Exception e){
            try {
                imgv = new ImageViewer(Image.createImage("/load.png"));
                System.out.println("loading Catch 1 ");
            } catch (IOException ex) {
                System.out.println("error Catch1");
            }
            System.out.println("error Catch2");
        }

        Button button = new Button("Upload Image");
        button.addActionListener(evt -> {

            MultipartRequest multipartRequest = new MultipartRequest();
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            multipartRequest.setUrl(Statics.BASE_URL+"/api/produit/update/"+product.getId());
            multipartRequest.setPost(true);
            String mime = "image/jpeg";
            try {
                multipartRequest.addData("image",filePath,mime);
            } catch (IOException e) {
                Dialog.show("Error",e.getMessage(),"OK",null);
            }
            multipartRequest.setFilename("file","aoo"+".jpg");

            InfiniteProgress progress = new InfiniteProgress();
            Dialog dialog = progress.showInfiniteBlocking();
            multipartRequest.setDisposeOnCompletion(dialog);
            NetworkManager.getInstance().addToQueueAndWait(multipartRequest);
            Dialog.show("Success","Image Uploaded","OK",null);
        });
        TextField textField_name = new TextField(product.getName(),"Name");
        TextField textField_price = new TextField(product.getPrice(),"Nombre Participant");
        TextField textField_design = new TextField(product.getDesignation(),"Description");
        TextField textField_quant = new TextField(product.getQuant(),"Quantité");

        add(imgv);
        add(button);
        add(textField_name);
        add(textField_price);
        add(textField_design);
        add(textField_quant);

        Button mb = new Button("Submit");
        add(mb);

        Button db = new Button("Delete");
        add(db);

        db.addActionListener(evt -> {
            boolean bool = ProductService.getInstance().deleteProduct(Integer.parseInt(product.getId()));
            if (bool){
                Dialog.show("Success","Product has been deleted","OK",null);
            }
        });

        mb.addActionListener(evt -> {
            product.setName(textField_name.getText());
            product.setDesignation(textField_design.getText());
            product.setPrice(textField_price.getText());
            product.setQuant(textField_quant.getText());
            if(ProductService.getInstance().updateProduct(product))
                Dialog.show("Success","Product Updated","OK",null);
        });


        getToolbar().addCommandToLeftBar("", mat, e -> new BackOfficeWindow().show());

    }

//-- DON'T EDIT BELOW THIS LINE!!!


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("ProductUpdateForm");
        setName("ProductUpdateForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
