package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PanelTags extends javax.swing.JPanel {

    //  If you want add more event create it as list
    private EventTags event;

    public void addEventTags(EventTags event) {
        this.event = event;
        ((Item) getComponent(getComponentCount() - 1)).setEventTags(event);
    }

    public PanelTags() {
        initComponents();
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT));
        Item input = new Item("");
        input.addEventKey(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                event.onKeyType(input, input.getText(), ke);
            }

        });
        input.addEventForInput(new EventInput() {
            @Override
            public void addItem(String text) {
                Item item = new Item(text);
                item.addEventMouse();
                item.setEventTags(event);
                item.addEventRemove(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (event.isRemoveAble(item, item.getText())) {
                            remove(item);
                            refresh();
                            //  event remove
                            event.onItemRemove(item, item.getText());
                        }
                    }
                });
                item.addEventKey(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent ke) {
                        //    event key
                        event.onKeyType(item, item.getText(), ke);
                    }
                });
                add(item, getComponentCount() - 1);
                //  add item to first index
                //  event add
                event.onAddItem(item, item.getText());
                refresh();
            }
        });
        add(input);
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    public List<String> getAllItem() {
        List<String> list = new ArrayList<>();
        for (Component com : getComponents()) {
            Item item = (Item) com;
            if (!item.isInput()) {
                list.add(item.getText());
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        //  set focus to input
        //  input at last index
        ((Item) getComponent(getComponentCount() - 1)).grabFocus();
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
