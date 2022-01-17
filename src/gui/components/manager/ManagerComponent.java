/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.manager;

import gui.components.reader.*;
import gui.components.*;
import entity.Author;
import entity.Book;
import facade.BookFacade;
import gui.GuiApp;
import static gui.GuiApp.HEIGHT_WINDOW;
import static gui.GuiApp.WIDTH_WINDOW;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
