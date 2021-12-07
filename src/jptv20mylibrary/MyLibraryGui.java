 /*
 * To change this license header, choose License Headers in Project Properties.
 * To ch   nge this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20mylibrary;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mycomponents.ButtonComponent;
import mycomponents.CaptionComponent;
import mycomponents.ComboBoxComponent;
import mycomponents.EditFieldComponent;

/**
 *
 * @author Melnikov
 */
public class MyLibraryGui extends JFrame{

    public MyLibraryGui() {
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(540, 320));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JPanel captionPanel = new CaptionComponent("Добавление новой книги", this.getWidth(), 90);
        getContentPane().add(captionPanel);
        JPanel titlePanel = new EditFieldComponent("Название книги: ",this.getWidth(), 31,250);
        getContentPane().add(titlePanel);
        JPanel comboBoxAuthors = new ComboBoxComponent("Авторы книги: ", this.getWidth(),31,250);
        getContentPane().add(comboBoxAuthors);
        JPanel publishedYearPanel = new EditFieldComponent("Год издания книги: ",this.getWidth(),31,70);
        getContentPane().add(publishedYearPanel);
        JPanel quantityPanel = new EditFieldComponent("Количество экземпляров: ",this.getWidth(),31,50);
        getContentPane().add(quantityPanel);
        JPanel buttonAddBook = new ButtonComponent("Добавить книгу", this.getWidth(),40,150,160);
        getContentPane().add(buttonAddBook);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyLibraryGui().setVisible(true);
            }
        });
    }

}