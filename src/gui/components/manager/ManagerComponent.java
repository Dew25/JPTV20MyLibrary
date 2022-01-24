/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.manager;

import gui.components.*;
import gui.GuiApp;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Melnikov
 */
public class ManagerComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameBookComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    public ManagerComponent() {
        initComponents();
    }

    private void initComponents() {
        JTabbedPane readerTabbed = new JTabbedPane();
        readerTabbed.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,GuiApp.HEIGHT_WINDOW));
        readerTabbed.setMinimumSize(readerTabbed.getPreferredSize());
        readerTabbed.setMaximumSize(readerTabbed.getPreferredSize());
        this.add(readerTabbed);
        readerTabbed.addTab("Добавить книгу", new AddBookComponent());
        readerTabbed.addTab("Редактировать книгу", new EditBookComponent());
        readerTabbed.addTab("Добавить автора", new AddAuthorComponent());
        readerTabbed.addTab("Редактировать автора", new EditAuthorComponent());
    }

    public InfoComponent getInfoComponent() {
        return infoComponent;
    }
       
    
    
}
