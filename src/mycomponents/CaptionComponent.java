/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Melnikov
 */
public class CaptionComponent extends JPanel{
    
    private JLabel jLabelTitle;
    
    public CaptionComponent(String text,int widthPanel,int heightPanel) {
        initComponents(text,widthPanel,heightPanel);
       
    }

    private void initComponents(String text,int widthPanel,int heightPanel) {
        this.setPreferredSize(new Dimension(widthPanel,heightPanel));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        jLabelTitle = new JLabel(text);
        jLabelTitle.setPreferredSize(new Dimension(widthPanel,heightPanel));
        jLabelTitle.setMinimumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setMaximumSize(jLabelTitle.getPreferredSize());
        jLabelTitle.setHorizontalAlignment(JLabel.CENTER);
        jLabelTitle.setFont(new Font("Tahoma",1,16));
        this.add(jLabelTitle);
    }

    public JLabel getjLabelTitle() {
        return jLabelTitle;
    }
    
}
