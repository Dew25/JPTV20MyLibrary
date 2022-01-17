/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.director;

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
public class DirectorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameBookComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    public DirectorComponent() {
        initComponents();
    }

    private void initComponents() {
        JTabbedPane readerTabbed = new JTabbedPane();
        readerTabbed.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,GuiApp.HEIGHT_WINDOW));
        readerTabbed.setMinimumSize(readerTabbed.getPreferredSize());
        readerTabbed.setMaximumSize(readerTabbed.getPreferredSize());
        this.add(readerTabbed);
        readerTabbed.addTab("Добавить читателя", new AddReaderComponent());
        readerTabbed.addTab("Редактировать читателя", new EditReaderComponent());
        readerTabbed.addTab("Изменить роль", new AddReaderComponent());
    }

    public InfoComponent getInfoComponent() {
        return infoComponent;
    }
       
    
    
}
