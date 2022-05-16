package com.codename1.uikit.pheonixui;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.uikit.pheonixui.model.Conversation;
import com.codename1.uikit.pheonixui.model.Message;
import com.codename1.uikit.pheonixui.service.MessageService;

import javax.xml.soap.Text;
import java.util.ArrayList;

import static com.codename1.charts.util.ColorUtil.BLUE;

public class CartForm extends BaseForm {
    public CartForm(Conversation conversation) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),conversation);
    }
    ArrayList<Message> messages;
    public CartForm(com.codename1.ui.util.Resources resourceObjectInstance,Conversation conversation) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Chat", "Title"),
                        new Label("0", "InboxNumber")
                )
        );
        installSidemenu(resourceObjectInstance);
        MessageService messageService = new MessageService();
        messages = messageService.getMessages();
        Container globalContainer = new Container(new com.codename1.ui.layouts.BoxLayout(BoxLayout.Y_AXIS));
        globalContainer.setScrollableY(true);
        for(Message message : messages) {
            Label label = new Label(">> " + message.getMessage());
            globalContainer.add(label);
        }
        Container textContainer = new Container(BoxLayout.x());
        TextField textField = new TextField("","Your message (top right to refresh)");
        Button button = new Button(FontImage.MATERIAL_SEND);
        textContainer.addAll(textField,button);
        add(CENTER,globalContainer);
        add(BOTTOM,textContainer);
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {
            new CartForm(conversation).show();
        });

        button.addActionListener(evt -> {
                messageService.addMessage(textField.getText(),conversation.getId());
        });
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Chat");
        setName("Chat");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
