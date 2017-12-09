/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dc.file.search.ui;

import org.apache.commons.lang.RandomStringUtils;
import org.dc.file.search.MessageUtils;
import org.dc.file.search.Peer;
import org.dc.file.search.SearchRequest;
import org.dc.file.search.SearchResult;
import org.dc.file.search.Store;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * @author rasikaperera
 */
public class DashboardForm extends javax.swing.JFrame {

    private Peer localPeer;
    private String uuid;

    class Item extends JPanel{
        JLabel l1;
        JLabel l2;

        public Item(String a, String b) {
            setLayout(new FlowLayout());
            this.l1 = new JLabel(a);
            this.l2 = new JLabel(b);
            add(l1);
            add(l2);
        }
    }

    /**
     * Creates new form Dashboard
     */
    public DashboardForm() {
        initComponents();

        String[] columnNames = {"Result"};
        Object[][] data = {
                {new TableRow("manual", 5, 0, 0, "")},
                {new TableRow("locked", 4,0, 0, "")},
                {new TableRow("manual", 0, 0, 0, "")},
                {new TableRow("locked", 0,0, 0, "")},
        };
        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return TableRow.class;
            }
        };
        jTable1.setModel(model);
        jTable1.setRowHeight(32);
        jTable1.setDefaultRenderer(TableRow.class, new RowRenderer());
        jTable1.setDefaultEditor(TableRow.class, new RowEditor());

        uuid = RandomStringUtils.randomAlphanumeric(8);
        setTitle("Dashboard :" + uuid);
        try {
            localPeer = MessageUtils.init(uuid);
        } catch (IOException e) {
            System.out.println("Error occurred while initializing peer");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Peer initialization failed!", "Error Occurred!",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    class CheckboxListItem {
        private String label;
        private boolean isSelected = false;

        public CheckboxListItem(String label) {
            this.label = label;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String toString() {
            return label;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearchKey = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSearchResult = new javax.swing.JList<>();
        btnPeersList = new javax.swing.JButton();
        btnMoviesList = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Movie Name:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lstSearchResult.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstSearchResult);

        btnPeersList.setText("Peers List");
        btnPeersList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeersListActionPerformed(evt);
            }
        });

        btnMoviesList.setText("My Movies List");
        btnMoviesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoviesListActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(btnMoviesList)
                .addGap(18, 18, 18)
                .addComponent(btnPeersList))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoviesList)
                    .addComponent(btnPeersList))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        Store store = Store.getInstance();
        String key = txtSearchKey.getText();
        SearchRequest searchRequest = new SearchRequest(Calendar.getInstance().getTimeInMillis(),
                                                        key, 2, localPeer);
        store.setMySearchRequest(searchRequest);
        store.addSearchRequest(searchRequest);
        store.setSearchResults(new ArrayList<>());
        List<String> results = Store.getInstance().findInFiles(searchRequest.getSearchKey());
        if (!results.isEmpty()) {
            SearchResult searchResult = new SearchResult(key, localPeer, 0, results);
            store.addSearchResult(searchResult);
        }
        for (Map.Entry<String, Peer> entry : Store.getInstance().getPeerMap().entrySet()) {
            Peer peer = entry.getValue();
            MessageUtils.sendUDPMessage(peer.getIp(),
                                        peer.getPort(),
                                        "SER " + localPeer.getIp() + " " + localPeer.getPort()
                                                + " \"" + key + "\" 2");
        }
        Runnable resultTask = () -> {
            List<SearchResult> searchResults = Store.getInstance().getSearchResults();
            if (searchResults != null) {
                DefaultListModel listModel = new DefaultListModel();
                listModel.addElement("Peer\t\t\t\t|Hops\t|Count\t|Files");
                for (SearchResult searchResult : searchResults) {
                    String resultStr = "";
                    Peer peer = searchResult.getPeerWithResults();
                    resultStr += peer.getIp() + ":" + peer.getPort() + "\t ";
                    resultStr +=searchResult.getHopCount() + "\t\t ";
                    resultStr +=searchResult.getResults().size() + "\t\t ";
                    String files = "";
                    for (String fileName : searchResult.getResults()) {
                        files += fileName + " ";
                    }
                    resultStr += files;
                    listModel.addElement(resultStr);
                }
                lstSearchResult.setModel(listModel);
            }
        };
        int delay = 5;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(resultTask, delay, TimeUnit.SECONDS);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        performExit();
    }//GEN-LAST:event_formWindowClosing

    private void btnPeersListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new PeersListForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMoviesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new MyMoviesListForm().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void performExit() {
        Store store = Store.getInstance();
        for (Map.Entry<String, Peer> entry : Store.getInstance().getPeerMap().entrySet()) {
            Peer peer = entry.getValue();
            MessageUtils.sendUDPMessage(peer.getIp(),
                                        peer.getPort(),
                                        "LEAVE " + localPeer.getIp() + " " + localPeer.getPort());
        }
        MessageUtils.sendTCPMessage(store.getServerIp(),
                                    store.getServerPort(),
                                    "UNREG " + localPeer.getIp() + " " + localPeer.getPort() + " " + uuid);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnPeersList;
    private javax.swing.JButton btnMoviesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JList<String> lstSearchResult;
    private javax.swing.JTextField txtSearchKey;
    // End of variables declaration//GEN-END:variables
}

class TableRow {
    public final String peerIp;
    public final int hopCount;
    public final int resultSize;
    public final String result;
    public final int rating;

    public TableRow(String peerIP, int rating, int hopCount, int resultSize, String result) {
        this.peerIp = peerIP;
        this.rating = rating;
        this.hopCount = hopCount;
        this.resultSize = resultSize;
        this.result = result;
    }
}

class RowPanel extends JPanel {
    private static String DEFAULT = "0";
    public final JLabel peerIp = new JLabel("192.168.8.1:9090");
    public final JLabel hopCount = new JLabel("0");
    public final JLabel resultSize = new JLabel("0");
    public final JLabel result = new JLabel("0");
    public final StarRater starRater = new StarRater(5, 2, 1);

    public RowPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        starRater.addStarListener(
                new StarRater.StarListener() {
                    public void handleSelection(int selection) {
                        System.out.println(selection);
                    }
                });
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.add(peerIp);
        panel.add(hopCount);
        panel.add(resultSize);
        panel.add(result);
//        add(panel);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        gridBagLayout.setConstraints(peerIp, c);
        add(peerIp);
        gridBagLayout.setConstraints(hopCount, c);
        add(hopCount);
        gridBagLayout.setConstraints(resultSize, c);
        add(resultSize);
        gridBagLayout.setConstraints(result, c);
        add(result);
        add(starRater);
    }

    public void updateValue(TableRow bt) {
        peerIp.setText(bt.peerIp);
        hopCount.setText(String.valueOf(bt.hopCount));
        resultSize.setText(String.valueOf(bt.resultSize));
        result.setText(bt.result);
        starRater.setRating(bt.rating);
    }
}

class RowRenderer extends RowPanel implements TableCellRenderer {
    public RowRenderer() {
        super();
        setName("Table.cellRenderer");
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        if (value instanceof TableRow) {
            updateValue((TableRow) value);
        }
        return this;
    }
}

class RowEditor extends RowPanel implements TableCellEditor {
    protected transient ChangeEvent changeEvent;

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        this.setBackground(table.getSelectionBackground());
        if (value instanceof TableRow) {
            updateValue((TableRow) value);
        }
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return new TableRow(peerIp.getText(), starRater.getSelection(), Integer.parseInt(hopCount.getText()), Integer.parseInt(resultSize.getText()), result.getText());
    }

    //Copied from AbstractCellEditor
    //protected EventListenerList listenerList = new EventListenerList();
    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }

    public CellEditorListener[] getCellEditorListeners() {
        return listenerList.getListeners(CellEditorListener.class);
    }

    protected void fireEditingStopped() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (Objects.isNull(changeEvent)) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CellEditorListener.class) {
                // Lazily create the event:
                if (Objects.isNull(changeEvent)) {
                    changeEvent = new ChangeEvent(this);
                }
                ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
            }
        }
    }
}