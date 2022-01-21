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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.ListModel;


/**
 *
 * @author Melnikov
 */
public class EditBookComponent extends JPanel{
    public EditBookComponent editBookComponent = this;
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameBookComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private ComboBoxBooksComponent comboBoxBooksComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    
    private BookFacade bookFacade;
    private Book editBook;
    public EditBookComponent() {
        bookFacade = new BookFacade(Book.class);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Редактирование книги", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxBooksComponent = new ComboBoxBooksComponent("Книги", 240, 30, 300);
        this.add(comboBoxBooksComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameBookComponent = new EditComponent("Название книги",240, 30, 300);
        this.add(nameBookComponent);
        listAuthorsComponent = new ListAuthorsComponent("Авторы", 240, 120, 300);
        this.add(listAuthorsComponent);
        publishedYearComponent = new EditComponent("Год изания книги", 240, 30, 100);
        this.add(publishedYearComponent);
        quantityComponent = new EditComponent("Колличество экземпляров", 240, 30, 50);
        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Добавть книгу",GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book updateBook = bookFacade.find(editBook.getId());
                if(nameBookComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите название книги");
                    return;
                }
                updateBook.setBookName(nameBookComponent.getEditor().getText());

                List<Author> authorsBook = listAuthorsComponent.getList().getSelectedValuesList();
                if(authorsBook.isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Выберите авторов книги");
                    return;
                }
                updateBook.setAuthor(authorsBook);
                try {
                    updateBook.setPublishedYear(Integer.parseInt(publishedYearComponent.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите год издания книги цифрами");
                    return;
                }
                try {
                    updateBook.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                    updateBook.setCount(updateBook.getQuantity());
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите количество книг цифрами");
                    return;
                }
                BookFacade bookFacade = new BookFacade(Book.class);
                try {
                    bookFacade.edit(updateBook);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Книга успешно изменена");
                    comboBoxBooksComponent.getComboBox().setModel(comboBoxBooksComponent.getComboBoxModel());
                    comboBoxBooksComponent.getComboBox().setSelectedIndex(-1);
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Книгу изменить не удалось");
                }

            }
        });
        comboBoxBooksComponent.getComboBox().addItemListener((ItemEvent e) -> {
           JComboBox comboBox = (JComboBox) e.getSource();
           if(comboBox.getSelectedIndex() == -1){
                nameBookComponent.getEditor().setText("");
                publishedYearComponent.getEditor().setText("");
                quantityComponent.getEditor().setText("");
                listAuthorsComponent.getList().clearSelection();
           }else{
                editBook = (Book) e.getItem();
                nameBookComponent.getEditor().setText(editBook.getBookName());
                publishedYearComponent.getEditor().setText(((Integer)editBook.getPublishedYear()).toString());
                quantityComponent.getEditor().setText(((Integer)editBook.getQuantity()).toString());
                listAuthorsComponent.getList().clearSelection();
                ListModel<Author> listModel = listAuthorsComponent.getList().getModel();
                for (int i=0;i<listModel.getSize();i++) {
                    if(editBook.getAuthor().contains(listModel.getElementAt(i))){
                        listAuthorsComponent.getList().getSelectionModel().addSelectionInterval(i, i);
                    }
                }
           }
        });
}
       
    
    
}
