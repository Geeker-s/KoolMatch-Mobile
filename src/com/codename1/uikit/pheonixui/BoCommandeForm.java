package com.codename1.uikit.pheonixui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.uikit.pheonixui.model.Commande;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.service.CartService;

import java.util.ArrayList;

public class BoCommandeForm extends BaseForm {
    public BoCommandeForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public BoCommandeForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Commandes", "Title"),
                        new Label(String.valueOf(CartService.getInstance().getCommands().size()), "InboxNumber")
                )
        );
        installSidemenu(resourceObjectInstance);
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        ArrayList<Commande> commandes = CartService.getInstance().getCommands();
        Object[][] rows = new Object[commandes.size()][];

        for(int iter = 0 ; iter < rows.length ; iter++) {
            rows[iter] = new Object[] {
                    commandes.get(iter).getDate(), commandes.get(iter).getRef(), commandes.get(iter).getTotal()
            };
        }
        TableModel model = new DefaultTableModel(new String[] {"Ref", "Etat", "Addresse"}, rows) {
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };

        Table table = new Table(model) {
            @Override
            protected Component createCell(Object value, int row, int column, boolean editable) {
                Component cell;
                if(column == 1) {
                    Picker p = new Picker();
                    p.setType(Display.PICKER_TYPE_STRINGS);
                    p.setStrings("0","1"); //A changer
                    p.setSelectedString((String)value);
                    p.setUIID("TableCell");
                    p.addActionListener((e) -> {
                        getModel().setValueAt(row, column, p.getSelectedString());
                        CartService cartService = new CartService();
                        cartService.updateCommands(p.getSelectedStringIndex(),rows[row][0]);
                    });
                    cell = p;
                } else {
                    cell = super.createCell(value, row, column, editable);
                }
                if(row > -1 && row % 2 == 0) {
                    // pinstripe effect
                    cell.getAllStyles().setBgColor(0xeeeeee);
                    cell.getAllStyles().setBgTransparency(255);
                }
                return cell;
            }

            @Override
            protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
                TableLayout.Constraint con =  super.createCellConstraint(value, row, column);
                if(row == 1 && column == 1) {
                    con.setHorizontalSpan(2);
                }
                con.setWidthPercentage(33);
                return con;
            }
        };


        add(CENTER,table);

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("BoCommandeForm");
        setName("BoCommandeForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
