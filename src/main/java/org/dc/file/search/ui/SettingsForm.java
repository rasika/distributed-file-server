/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dc.file.search.ui;

import org.apache.commons.lang.RandomStringUtils;
import org.dc.file.search.MessageUtils;
import org.dc.file.search.Peer;
import org.dc.file.search.ResponseHandler;
import org.dc.file.search.Store;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;

/**
 * @author rasikaperera
 */
public class SettingsForm extends javax.swing.JFrame {

    /**
     * Creates new form SettingsForm
     */
    public SettingsForm() {
        initComponents();
        try {
            txtServerIP.setText(getLocalHostIP());
        } catch (ConnectException e) {
            //do nothing
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
        txtServerIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtServerPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");

        jLabel1.setText("Bootstrap Server IP:");

        txtServerIP.setText("192.168.1.2");
        txtServerIP.setName(""); // NOI18N

        jLabel2.setText("Bootstrap Server Port:");

        txtServerPort.setText("9090");
        txtServerPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerPortActionPerformed(evt);
            }
        });

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(
                                                  javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addGroup(layout.createSequentialGroup()
                                                                              .addContainerGap(
                                                                                      javax.swing.GroupLayout
                                                                                              .DEFAULT_SIZE,
                                                                                      Short.MAX_VALUE)
                                                                              .addComponent(btnConnect)
                                                                              .addPreferredGap(
                                                                                      javax.swing.LayoutStyle
                                                                                              .ComponentPlacement
                                                                                              .RELATED)
                                                                              .addComponent(btnExit))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                      layout.createSequentialGroup()
                                                                              .addGap(36, 36, 36)
                                                                              .addGroup(layout.createParallelGroup(
                                                                                      javax.swing.GroupLayout
                                                                                              .Alignment.LEADING)
                                                                                                .addComponent(jLabel1)
                                                                                                .addComponent(jLabel2))
                                                                              .addGap(37, 37, 37)
                                                                              .addGroup(layout.createParallelGroup(
                                                                                      javax.swing.GroupLayout
                                                                                              .Alignment.LEADING,
                                                                                      false)
                                                                                                .addComponent(
                                                                                                        txtServerIP,
                                                                                                        javax.swing
                                                                                                                .GroupLayout.DEFAULT_SIZE,
                                                                                                        297,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addComponent(
                                                                                                        txtServerPort))))
                                          .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                          .addGap(29, 29, 29)
                                          .addGroup(layout.createParallelGroup(
                                                  javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel1)
                                                            .addComponent(txtServerIP,
                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                          javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addGroup(layout.createParallelGroup(
                                                  javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel2)
                                                            .addComponent(txtServerPort,
                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                          javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12,
                                                           Short.MAX_VALUE)
                                          .addGroup(layout.createParallelGroup(
                                                  javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btnConnect)
                                                            .addComponent(btnExit))
                                          .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Save IP and Port
            Store store = Store.getInstance();
            store.setServerIp(txtServerIP.getText());
            store.setServerPort(Integer.parseInt(txtServerPort.getText()));

            // Register the peer
            String uuid = RandomStringUtils.randomAlphanumeric(8);

            //Response handler
            ResponseHandler responseHandler = new ResponseHandler() {
                @Override
                public void onSuccess() {
                    setVisible(false);
                    new DashboardForm().setVisible(true);
                }

                @Override
                public void onError(Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Connection Failed!",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            };

            Peer localPeer = MessageUtils.init(uuid);
            MessageUtils.sendTCPMessage(store.getServerIp(),
                                        store.getServerPort(),
                                        "REG " + localPeer.getIp() + " " + localPeer.getPort() + " " + uuid,
                                        responseHandler);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Connection Failed!", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Server IP and Port should be numeric!", "Invalid Settings!",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtServerPortActionPerformed(
            java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServerPortActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static String getLocalHostIP() throws ConnectException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            Enumeration list = NetworkInterface.getNetworkInterfaces();
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress localIp = InetAddress.getLocalHost();
            String hostAddress = localIp.getHostAddress();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interf = interfaces.nextElement();
                if (interf.isUp() && !interf.isLoopback()) {
                    List<InterfaceAddress> adrs = interf.getInterfaceAddresses();
                    for (InterfaceAddress adr : adrs) {
                        InetAddress inadr = adr.getAddress();
                        if (inadr instanceof Inet4Address) {
                            hostAddress = inadr.getHostAddress();
                        }
                    }
                }
            }
            return hostAddress;
        } catch (SocketException e) {
            throw new ConnectException("Cannot read local interfaces.");
        } catch (UnknownHostException e){
            throw new ConnectException("Cannot not find local interface address.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtServerIP;
    private javax.swing.JTextField txtServerPort;
    // End of variables declaration//GEN-END:variables
}
