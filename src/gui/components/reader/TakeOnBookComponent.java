/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.reader;

import gui.components.*;
import gui.GuiApp;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class TakeOnBookComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private GuestComponent guestComponent;
    private ButtonComponent buttonComponent;
   
    public TakeOnBookComponent() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        captionComponent = new CaptionComponent("Выберите книги для чтения", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,GuiApp.HEIGHT_WINDOW));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        guestComponent = new GuestComponent(200);
        this.add(guestComponent);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        buttonComponent = new ButtonComponent("Взять книги для чтения", GuiApp.WIDTH_WINDOW, 27, 100, 200);
        this.add(buttonComponent);
        
    }
       
    
    
}
