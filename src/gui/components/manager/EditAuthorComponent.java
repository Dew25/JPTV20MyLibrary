/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.manager;

import gui.components.*;
import entity.Author;
import facade.AuthorFacade;
import gui.GuiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class EditAuthorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private ComboBoxAuthorsComponent comboBoxAuthorsComponent;
    private EditComponent nameAuthorComponent;
    private EditComponent lastNameAuthorComponent;
    private EditComponent birthYearAuthorComponent;
    private ButtonComponent buttonComponent;
    private Author editAuthor;
    public EditAuthorComponent() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Редактирование автора", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxAuthorsComponent = new ComboBoxAuthorsComponent("Авторы", 240, 30, 300);
        this.add(comboBoxAuthorsComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameAuthorComponent = new EditComponent("Имя",240, 30, 300);
        this.add(nameAuthorComponent);
        lastNameAuthorComponent = new EditComponent("Фамилия",240, 30, 300);
        this.add(lastNameAuthorComponent);
        birthYearAuthorComponent = new EditComponent("Год рождения книги", 240, 30, 100);
        this.add(birthYearAuthorComponent);
       
        buttonComponent = new ButtonComponent("Добавть автора",GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author author = new Author();
                if(nameAuthorComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите имя автора");
                    return;
                }
                author.setFirstname(nameAuthorComponent.getEditor().getText());
                
                try {
                    author.setLastname(lastNameAuthorComponent.getEditor().getText());
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите фамилию автора");
                    return;
                }
                try {
                    author.setBirthYear(Integer.parseInt(birthYearAuthorComponent.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите год цифрами");
                    return;
                }
                AuthorFacade authorFacade = new AuthorFacade(Author.class);
                try {
                    authorFacade.create(author);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Автор успешно добавлен");
                    comboBoxAuthorsComponent.getComboBox().setModel(comboBoxAuthorsComponent.getComboBoxModel());
                    comboBoxAuthorsComponent.getComboBox().setSelectedIndex(-1);
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Автора добавить не удалось");
                }

            }
    });
    comboBoxAuthorsComponent.getComboBox().addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
               if(comboBox.getSelectedIndex() == -1){
                    nameAuthorComponent.getEditor().setText("");
                    lastNameAuthorComponent.getEditor().setText("");
                    birthYearAuthorComponent.getEditor().setText("");
               }else{
                    editAuthor = (Author) e.getItem();
                    nameAuthorComponent.getEditor().setText(editAuthor.getFirstname());
                    lastNameAuthorComponent.getEditor().setText(editAuthor.getLastname());
                    birthYearAuthorComponent.getEditor().setText(((Integer)editAuthor.getBirthYear()).toString());
                    
               }
                
            }
        });
}
       
    
    
}
