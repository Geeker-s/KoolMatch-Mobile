/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author wassimromdhane
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this;
        setTitle("KoolMatch");
        setLayout(BoxLayout.y());
        Button btnInteraction = new Button("Swipe Now!");
        btnInteraction.addActionListener(e -> new InteractionForm(current).show());
        add(btnInteraction);
    }

}
