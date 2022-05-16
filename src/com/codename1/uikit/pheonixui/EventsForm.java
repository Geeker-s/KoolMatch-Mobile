package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.uikit.pheonixui.model.Evenement;
import com.codename1.uikit.pheonixui.service.EvenementService;

import java.util.ArrayList;

public class EventsForm extends BaseForm {
    public EventsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    private ArrayList<Evenement> events;
    private Form current = this;
    public EventsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        events = EvenementService.getInstance().getEvenements();

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Events", "Title"),
                        new Label(String.valueOf(events.size()), "InboxNumber")
                )
        );

        installSidemenu(resourceObjectInstance);

        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});

        Container destinationsList = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if(index == 0)
                    events = EvenementService.getInstance().getEvenements();

                if(index + amount > events.size()){
                    amount = events.size() - index;
                    System.out.println("hello salem");
                    if (amount <= 0) {
                        System.out.println("amount is 0");
                        return null;
                    }
                }
                Component[] more = new Component[amount];
                for(int iter = 0; iter < amount; iter++){
                    int offset = index + iter;

                    MultiButton mb = new MultiButton(events.get(offset).getNomEvent());
                    mb.setTextLine2(events.get(offset).getThemeEvent());
                    mb.setTextLine3("Date début : "+events.get(offset).getDdEvent());
                             mb.setTextLine4("\n Date fin : \n" + events.get(offset).getDfEvent());
                    System.out.println("repeat");
                    mb.setNameLine1("Label_3");
                    mb.setUIIDLine2("RedLabel");
                    mb.setUIIDLine3("SmallFontLabel");
                    mb.setIcon(resourceObjectInstance.getImage("label_round.png"));
                    Container container = new Container(new BorderLayout());
                    container.add(BorderLayout.CENTER,mb);

                    more[iter] = container;
                    mb.addActionListener(e->{
                        new UpdateEventForm(events.get(offset)).show();
                    });

                }
                return more;
            }
        };
        gui_Container_1.addComponent(BorderLayout.CENTER,destinationsList);
        add(CENTER,gui_Container_1);
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            new AddEventForm().show();
        });


    }

    //-- DON'T EDIT BELOW THIS LINE!!!
    private Container gui_Container_1 = new Container(new BorderLayout());
    private Container gui_Container_2 = new Container(new FlowLayout());
    private Label gui_Label_1 = new Label();
    private Container gui_Container_4 = new Container(new FlowLayout());
    private Label gui_Label_4 = new Label();
    private Container gui_Container_3 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Label gui_Label_3 = new Label();
    private Label gui_Label_2 = new Label();
    private TextArea gui_Text_Area_1 = new TextArea();
    private Label gui_Label_6 = new Label();
    private Container gui_Container_1_1 = new Container(new BorderLayout());
    private Container gui_Container_2_1 = new Container(new FlowLayout());
    private Label gui_Label_1_1 = new Label();
    private Container gui_Container_4_1 = new Container(new FlowLayout());
    private Label gui_Label_4_1 = new Label();
    private Container gui_Container_3_1 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Label gui_Label_3_1 = new Label();
    private Label gui_Label_2_1 = new Label();
    private TextArea gui_Text_Area_1_1 = new TextArea();
    private Label gui_Label_7 = new Label();
    private Container gui_Container_1_2 = new Container(new BorderLayout());
    private Container gui_Container_2_2 = new Container(new FlowLayout());
    private Label gui_Label_1_2 = new Label();
    private Container gui_Container_4_2 = new Container(new FlowLayout());
    private Label gui_Label_4_2 = new Label();
    private Container gui_Container_3_2 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Label gui_Label_3_2 = new Label();
    private Label gui_Label_2_2 = new Label();
    private TextArea gui_Text_Area_1_2 = new TextArea();
    private Label gui_Label_8 = new Label();
    private Container gui_Container_1_3 = new Container(new BorderLayout());
    private Container gui_Container_2_3 = new Container(new FlowLayout());
    private Label gui_Label_1_3 = new Label();
    private Container gui_Container_4_3 = new Container(new FlowLayout());
    private Label gui_Label_4_3 = new Label();
    private Container gui_Container_3_3 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Label gui_Label_3_3 = new Label();
    private Label gui_Label_2_3 = new Label();
    private TextArea gui_Text_Area_1_3 = new TextArea();
    private Label gui_Label_9 = new Label();
    private Container gui_Container_1_4 = new Container(new BorderLayout());
    private Container gui_Container_2_4 = new Container(new FlowLayout());
    private Label gui_Label_1_4 = new Label();
    private Container gui_Container_4_4 = new Container(new FlowLayout());
    private Label gui_Label_4_4 = new Label();
    private Container gui_Container_3_4 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Label gui_Label_3_4 = new Label();
    private Label gui_Label_2_4 = new Label();
    private TextArea gui_Text_Area_1_4 = new TextArea();
    private Label gui_Label_5 = new Label();


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new BorderLayout());
        //setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("InboxForm");
        setName("InboxForm");
//        addComponent(gui_Container_1);
//        gui_Container_1.setName("Container_1");
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
//        gui_Container_2.setName("Container_2");
//        gui_Container_2.addComponent(gui_Label_1);
//        gui_Label_1.setText("11:31 AM");
//        gui_Label_1.setUIID("SmallFontLabel");
//        gui_Label_1.setName("Label_1");
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
//        gui_Container_4.setName("Container_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4.addComponent(gui_Label_4);
//        gui_Label_4.setUIID("Padding2");
//        gui_Label_4.setName("Label_4");
//        gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
//        gui_Container_3.setName("Container_3");
//        gui_Container_3.addComponent(gui_Label_3);
//        gui_Container_3.addComponent(gui_Label_2);
//        gui_Container_3.addComponent(gui_Text_Area_1);
//        gui_Label_3.setText("Sheldon Murphy");
//        gui_Label_3.setName("Label_3");
//        gui_Label_2.setText("Design Updates");
//        gui_Label_2.setUIID("RedLabel");
//        gui_Label_2.setName("Label_2");
//        gui_Text_Area_1.setText("Hi Adrian, there is a new announcement for you from Oxford  Learning  Lab. Hello we completly...");
//        gui_Text_Area_1.setUIID("SmallFontLabel");
//        gui_Text_Area_1.setName("Text_Area_1");
//        gui_Text_Area_1.setColumns(100);
//        gui_Text_Area_1.setRows(2);
//        gui_Container_2.setName("Container_2");
//        gui_Container_4.setName("Container_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3.setName("Container_3");
//        addComponent(gui_Label_6);
//        addComponent(gui_Container_1_1);
//        gui_Container_1_1.setName("Container_1_1");
//        gui_Container_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_1);
//        gui_Container_2_1.setName("Container_2_1");
//        gui_Container_2_1.addComponent(gui_Label_1_1);
//        gui_Label_1_1.setText("8:23 PM");
//        gui_Label_1_1.setUIID("SmallFontLabel");
//        gui_Label_1_1.setName("Label_1_1");
//        gui_Container_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4_1);
//        gui_Container_4_1.setName("Container_4_1");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_1.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4_1.addComponent(gui_Label_4_1);
//        gui_Label_4_1.setUIID("Padding2");
//        gui_Label_4_1.setName("Label_4_1");
//        gui_Label_4_1.setIcon(resourceObjectInstance.getImage("label_round-selected.png"));
//        gui_Container_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_1);
//        gui_Container_3_1.setName("Container_3_1");
//        gui_Container_3_1.addComponent(gui_Label_3_1);
//        gui_Container_3_1.addComponent(gui_Label_2_1);
//        gui_Container_3_1.addComponent(gui_Text_Area_1_1);
//        gui_Label_3_1.setText("Massdrop");
//        gui_Label_3_1.setName("Label_3_1");
//        gui_Label_2_1.setText("We Are Just Getting Started");
//        gui_Label_2_1.setUIID("RedLabel");
//        gui_Label_2_1.setName("Label_2_1");
//        gui_Text_Area_1_1.setText("Tenkara Rod Co Teton Package Made possible by the Ultralight community...");
//        gui_Text_Area_1_1.setUIID("SmallFontLabel");
//        gui_Text_Area_1_1.setName("Text_Area_1_1");
//        gui_Text_Area_1_1.setColumns(100);
//        gui_Text_Area_1_1.setRows(2);
//        gui_Container_2_1.setName("Container_2_1");
//        gui_Container_4_1.setName("Container_4_1");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_1.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3_1.setName("Container_3_1");
//        addComponent(gui_Label_7);
//        addComponent(gui_Container_1_2);
//        gui_Container_1_2.setName("Container_1_2");
//        gui_Container_1_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_2);
//        gui_Container_2_2.setName("Container_2_2");
//        gui_Container_2_2.addComponent(gui_Label_1_2);
//        gui_Label_1_2.setText("Yesterday");
//        gui_Label_1_2.setUIID("SmallFontLabel");
//        gui_Label_1_2.setName("Label_1_2");
//        gui_Container_1_2.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4_2);
//        gui_Container_4_2.setName("Container_4_2");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_2.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4_2.addComponent(gui_Label_4_2);
//        gui_Label_4_2.setUIID("Padding2");
//        gui_Label_4_2.setName("Label_4_2");
//        gui_Label_4_2.setIcon(resourceObjectInstance.getImage("label_round.png"));
//        gui_Container_1_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_2);
//        gui_Container_3_2.setName("Container_3_2");
//        gui_Container_3_2.addComponent(gui_Label_3_2);
//        gui_Container_3_2.addComponent(gui_Label_2_2);
//        gui_Container_3_2.addComponent(gui_Text_Area_1_2);
//        gui_Label_3_2.setText("Product Hunt");
//        gui_Label_3_2.setName("Label_3_2");
//        gui_Label_2_2.setText("Our favorite GIF apps");
//        gui_Label_2_2.setUIID("RedLabel");
//        gui_Label_2_2.setName("Label_2_2");
//        gui_Text_Area_1_2.setText("We know that you spend a lot of time admiring the hard work of GIF-makers the world over. ");
//        gui_Text_Area_1_2.setUIID("SmallFontLabel");
//        gui_Text_Area_1_2.setName("Text_Area_1_2");
//        gui_Text_Area_1_2.setColumns(100);
//        gui_Text_Area_1_2.setRows(2);
//        gui_Container_2_2.setName("Container_2_2");
//        gui_Container_4_2.setName("Container_4_2");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_2.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3_2.setName("Container_3_2");
//        addComponent(gui_Label_8);
//        addComponent(gui_Container_1_3);
//        gui_Container_1_3.setName("Container_1_3");
//        gui_Container_1_3.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_3);
//        gui_Container_2_3.setName("Container_2_3");
//        gui_Container_2_3.addComponent(gui_Label_1_3);
//        gui_Label_1_3.setText("Mar 12");
//        gui_Label_1_3.setUIID("SmallFontLabel");
//        gui_Label_1_3.setName("Label_1_3");
//        gui_Container_1_3.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4_3);
//        gui_Container_4_3.setName("Container_4_3");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4_3.addComponent(gui_Label_4_3);
//        gui_Label_4_3.setUIID("Padding2");
//        gui_Label_4_3.setName("Label_4_3");
//        gui_Label_4_3.setIcon(resourceObjectInstance.getImage("label_round.png"));
//        gui_Container_1_3.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_3);
//        gui_Container_3_3.setName("Container_3_3");
//        gui_Container_3_3.addComponent(gui_Label_3_3);
//        gui_Container_3_3.addComponent(gui_Label_2_3);
//        gui_Container_3_3.addComponent(gui_Text_Area_1_3);
//        gui_Label_3_3.setText("MightyDeals");
//        gui_Label_3_3.setName("Label_3_3");
//        gui_Label_2_3.setText("Vintage Design: 600+ Retro Vector Illustrations and Objects");
//        gui_Label_2_3.setUIID("RedLabel");
//        gui_Label_2_3.setName("Label_2_3");
//        gui_Text_Area_1_3.setText("With just a little imagery, an ordinary project can transform into something extraordinary! ");
//        gui_Text_Area_1_3.setUIID("SmallFontLabel");
//        gui_Text_Area_1_3.setName("Text_Area_1_3");
//        gui_Text_Area_1_3.setColumns(100);
//        gui_Text_Area_1_3.setRows(2);
//        gui_Container_2_3.setName("Container_2_3");
//        gui_Container_4_3.setName("Container_4_3");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3_3.setName("Container_3_3");
//        addComponent(gui_Label_9);
//        addComponent(gui_Container_1_4);
//        gui_Container_1_4.setName("Container_1_4");
//        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_4);
//        gui_Container_2_4.setName("Container_2_4");
//        gui_Container_2_4.addComponent(gui_Label_1_4);
//        gui_Label_1_4.setText("Mar 08");
//        gui_Label_1_4.setUIID("SmallFontLabel");
//        gui_Label_1_4.setName("Label_1_4");
//        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4_4);
//        gui_Container_4_4.setName("Container_4_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4_4.addComponent(gui_Label_4_4);
//        gui_Label_4_4.setUIID("Padding2");
//        gui_Label_4_4.setName("Label_4_4");
//        gui_Label_4_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
//        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_4);
//        gui_Container_3_4.setName("Container_3_4");
//        gui_Container_3_4.addComponent(gui_Label_3_4);
//        gui_Container_3_4.addComponent(gui_Label_2_4);
//        gui_Container_3_4.addComponent(gui_Text_Area_1_4);
//        gui_Label_3_4.setText("Twitter");
//        gui_Label_3_4.setName("Label_3_4");
//        gui_Label_2_4.setText("Popular tweets this week");
//        gui_Label_2_4.setUIID("RedLabel");
//        gui_Label_2_4.setName("Label_2_4");
//        gui_Text_Area_1_4.setText("Hi Adrian, there is a new announcement for you from Oxford Learning  Lab. Hello we completly...");
//        gui_Text_Area_1_4.setUIID("SmallFontLabel");
//        gui_Text_Area_1_4.setName("Text_Area_1_4");
//        gui_Text_Area_1_4.setColumns(100);
//        gui_Text_Area_1_4.setRows(2);
//        gui_Container_2_4.setName("Container_2_4");
//        gui_Container_4_4.setName("Container_4_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3_4.setName("Container_3_4");
//        addComponent(gui_Label_5);
//        gui_Container_1.setName("Container_1");
//        gui_Label_6.setText("");
//        gui_Label_6.setUIID("Separator");
//        gui_Label_6.setName("Label_6");
//        gui_Container_1_1.setName("Container_1_1");
//        gui_Label_7.setText("");
//        gui_Label_7.setUIID("Separator");
//        gui_Label_7.setName("Label_7");
//        gui_Container_1_2.setName("Container_1_2");
//        gui_Label_8.setText("");
//        gui_Label_8.setUIID("Separator");
//        gui_Label_8.setName("Label_8");
//        gui_Container_1_3.setName("Container_1_3");
//        gui_Label_9.setText("");
//        gui_Label_9.setUIID("Separator");
//        gui_Label_9.setName("Label_9");
//        gui_Container_1_4.setName("Container_1_4");
//        gui_Label_5.setText("");
//        gui_Label_5.setUIID("Separator");
//        gui_Label_5.setName("Label_5");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
