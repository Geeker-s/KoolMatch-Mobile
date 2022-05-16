/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.pheonixui;


import com.codename1.ui.*;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;




public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        getToolbar().addCommandToSideMenu("Swipe Now!", null, e -> new InteractionForm().show());
        getToolbar().addCommandToSideMenu("Events", null, e -> new EventsForm().show());
        getToolbar().addCommandToSideMenu("Conversations", null, e -> new ProductsForm().show());
        getToolbar().addCommandToSideMenu("Restaurants", null, e -> new RestaurantForm().show());
        getToolbar().addCommandToSideMenu("Reservations", null, e -> new ReservationForm().show());
        getToolbar().addCommandToSideMenu("Recipes", null, e -> new RecipesForm().show());
        getToolbar().addCommandToSideMenu("BackOffice", null, e -> new BackOfficeWindow().show());




        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected boolean isCurrentInbox() {
        return false;
    }
    protected boolean isCurrentTrending() {
        return false;
    }
    protected boolean isCurrentCalendar() {
        return false;
    }
    protected boolean isCurrentStats() {
        return false;
    }
}
