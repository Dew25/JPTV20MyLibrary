/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Melnikov
 */
public class GuiApp extends javax.swing.JFrame {
    private BookFacade bookFacade;
    private AuthorFacade authorFacade;
    private ReaderFacade readerFacade;
    private HistoryFacade historyFacade;
    private Reader reader;
    private Book book;
    
    /**
     * Creates new form GuiApp
     */
    public GuiApp() {
        init();
        initComponents();
        List<Reader> readers = readerFacade.findAll();
        if(readers.size() == 0){
            Reader reader = new Reader();
            reader.setFirstname("Ivan");
            reader.setLastname("Ivanov");
            reader.setPhone("56545656");
            readerFacade.create(reader);
            readers = readerFacade.findAll();
        }
        DefaultComboBoxModel<Reader> defaultComboBoxModel= new DefaultComboBoxModel<>();
        for (Reader r : readers) {
            defaultComboBoxModel.addElement(r);
        }
        jComboBoxReaders.setModel(defaultComboBoxModel);
        jComboBoxReaders.setRenderer(createListReadersRenderer());
        jComboBoxReaders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reader = jComboBoxReaders.getItemAt(jComboBoxReaders.getSelectedIndex());
            }
        });
        List<Book> books = bookFacade.findAll();
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for(Book book: books){
          defaultListModel.addElement(book);
        }
        jListBooks.setModel(defaultListModel);
        jListBooks.setCellRenderer(createListBooksRenderer());
        jListBooks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    book = jListBooks.getSelectedValue();
                }
            }
        });
        setLocationRelativeTo(null);
    }
    private void init(){
        bookFacade = new BookFacade(Book.class);
        authorFacade = new AuthorFacade(Author.class);
        readerFacade = new ReaderFacade(Reader.class);
        historyFacade = new HistoryFacade(History.class);
    }
    private ListCellRenderer<? super Reader> createListReadersRenderer(){
      return new DefaultListCellRenderer(){
          private final Color background = new Color(0, 100, 255, 15);
          private final Color defaultBackground = (Color) UIManager.get("List.background");
          @Override
          public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus) {
              Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
              if (component instanceof JLabel) {
                  JLabel label = (JLabel) component;
                  Reader reader = (Reader) value;
                  if(reader == null){
                    label.setText("Список читателей пуст");
                  }else{
                    label.setText(String.format("%d. %s. %s. Телефон: %s%n"
                          ,reader.getId()
                          ,reader.getFirstname()
                          ,reader.getLastname()
                          ,reader.getPhone()
                    ));
                  }
                  if (!isSelected) {
                      label.setBackground(index % 2 == 0 ? background : defaultBackground);
                  }
              }
              return component;
          }
      };
  }
  private ListCellRenderer<? super Book> createListBooksRenderer() {
    return new DefaultListCellRenderer(){
        private final Color background = new Color(0, 100, 255, 15);
        private final Color defaultBackground = (Color) UIManager.get("List.background");
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                Book book = (Book) value;
                label.setText(String.format("%d. %s. %s %d. В наличии: %d%n"
                        ,book.getId()
                        ,book.getBookName()
                        ,listStringAuthors(book.getAuthor())
                        ,book.getPublishedYear()
                        ,book.getCount()
                ));
                if (!isSelected) {
                    label.setBackground(index % 2 == 0 ? background : defaultBackground);
                }
            }
            return component;
        }
        private String listStringAuthors(List<Author> authors){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < authors.size(); i++) {
                sb.append(authors.get(i).getFirstname())
                  .append(" ")
                  .append(authors.get(i).getLastname())
                  .append(". ");
            }
            return sb.toString();
        }
    };
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTible = new javax.swing.JLabel();
        jButtonReader = new javax.swing.JButton();
        jButtonManager = new javax.swing.JButton();
        jButtonDirector = new javax.swing.JButton();
        jLabelReaders = new javax.swing.JLabel();
        jComboBoxReaders = new javax.swing.JComboBox<Reader>();
        jPanelCards = new javax.swing.JPanel();
        jPanelCardReader = new javax.swing.JPanel();
        jLabelChangeBook = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBooks = new javax.swing.JList<Book>();
        jButtonAddBook = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JPTV20 Library");
        setMaximumSize(new java.awt.Dimension(480, 290));
        setMinimumSize(new java.awt.Dimension(480, 290));
        setResizable(false);

        jLabelTible.setText("Выберите свою роль");

        jButtonReader.setText("Читатель");
        jButtonReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReaderActionPerformed(evt);
            }
        });

        jButtonManager.setText("Библиотекарь");

        jButtonDirector.setText("Директор");

        jLabelReaders.setText("Выбери себя");

        jPanelCards.setName(""); // NOI18N
        jPanelCards.setLayout(new java.awt.CardLayout(3, 3));

        jPanelCardReader.setName("cardReader"); // NOI18N

        jLabelChangeBook.setText("Выберите книгу");

        jScrollPane1.setViewportView(jListBooks);

        jButtonAddBook.setText("Нажмите, чтобы взять книгу");
        jButtonAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCardReaderLayout = new javax.swing.GroupLayout(jPanelCardReader);
        jPanelCardReader.setLayout(jPanelCardReaderLayout);
        jPanelCardReaderLayout.setHorizontalGroup(
            jPanelCardReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCardReaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCardReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(jPanelCardReaderLayout.createSequentialGroup()
                        .addGroup(jPanelCardReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCardReaderLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonAddBook))
                            .addGroup(jPanelCardReaderLayout.createSequentialGroup()
                                .addComponent(jLabelChangeBook)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanelCardReaderLayout.setVerticalGroup(
            jPanelCardReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCardReaderLayout.createSequentialGroup()
                .addComponent(jLabelChangeBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAddBook)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelCardReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabelTible))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabelReaders)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxReaders, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButtonReader)
                        .addGap(38, 38, 38)
                        .addComponent(jButtonManager)
                        .addGap(44, 44, 44)
                        .addComponent(jButtonDirector)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonReader)
                    .addComponent(jButtonManager)
                    .addComponent(jButtonDirector))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReaders)
                    .addComponent(jComboBoxReaders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelCardReader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddBookActionPerformed
        if(reader != null && book != null){
            History history = new History();
            Calendar c = new GregorianCalendar();
            history.setBook(book);
            history.setReader(reader);
            history.setGivenDate(c.getTime());
            book.setCount(book.getCount() - 1);
            bookFacade.edit(book);
            historyFacade.create(history);
        }
    }//GEN-LAST:event_jButtonAddBookActionPerformed

    private void jButtonReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReaderActionPerformed
        CardLayout card = (CardLayout) jPanelCards.getLayout();
        card.show(jPanelCards, "cardReader");
        
    }//GEN-LAST:event_jButtonReaderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddBook;
    private javax.swing.JButton jButtonDirector;
    private javax.swing.JButton jButtonManager;
    private javax.swing.JButton jButtonReader;
    private javax.swing.JComboBox<Reader> jComboBoxReaders;
    private javax.swing.JLabel jLabelChangeBook;
    private javax.swing.JLabel jLabelReaders;
    private javax.swing.JLabel jLabelTible;
    private javax.swing.JList<Book> jListBooks;
    private javax.swing.JPanel jPanelCardReader;
    private javax.swing.JPanel jPanelCards;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
