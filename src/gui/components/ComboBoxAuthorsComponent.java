/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import entity.Author;
import facade.AuthorFacade;
import gui.GuiApp;
import gui.components.renderers.ListAuthorsCellRenderer;
import gui.components.renderers.ListBooksCellRenderer;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class ComboBoxAuthorsComponent extends JPanel{
    private JLabel title;
    private JComboBox<Author> comboBox;
    
    /**
     * Список книг библиотеки с заголовком
     * @param text текст в JLabel
     * @param left ширина JLabel
     * @param heightPanel высота панели компонента
     * @param widthEditor ширина JList
     */
    public ComboBoxAuthorsComponent(String text, int left, int heightPanel, int widthEditor) {
        initComponents(text, left, heightPanel,widthEditor);
    }

       
    private void initComponents(String text, int left, int heightPanel,int widthEditor) {
       
       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//       this.setBorder(BorderFactory.createLineBorder(Color.yellow));
       this.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,heightPanel));
       this.setMinimumSize(this.getPreferredSize());
       this.setMaximumSize(this.getPreferredSize());
       title = new JLabel(text);
       title.setPreferredSize(new Dimension(left,heightPanel));
       title.setMinimumSize(title.getPreferredSize());
       title.setMaximumSize(title.getPreferredSize());
       title.setHorizontalAlignment(JLabel.RIGHT);
       comboBox = new JComboBox<>();
//       title.setAlignmentY(TOP_ALIGNMENT);
       title.setFont(new Font("Tahoma",0,12));
       this.add(title);
       this.add(Box.createRigidArea(new Dimension(5,0)));
       comboBox.setModel(getComboBoxModel());
       comboBox.setRenderer(new ListAuthorsCellRenderer());
       comboBox.setSelectedIndex(-1);
       comboBox.setPreferredSize(new Dimension(widthEditor,heightPanel));
       comboBox.setMinimumSize(comboBox.getPreferredSize());
       comboBox.setMaximumSize(comboBox.getPreferredSize());
       this.add(comboBox);
    }

    public ComboBoxModel<Author> getComboBoxModel() {
        AuthorFacade authorFacade = new AuthorFacade(Author.class);
        List<Author> authors = authorFacade.findAll();
        
        DefaultComboBoxModel<Author> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Author author : authors) {
            defaultComboBoxModel.addElement(author);
        }
        return defaultComboBoxModel;
    }

    public JComboBox<Author> getComboBox() {
        return comboBox;
    }

    
    
}