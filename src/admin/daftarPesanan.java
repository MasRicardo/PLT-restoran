/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import server.mysql;

/**
 *
 * @author DELL
 */
public class daftarPesanan extends javax.swing.JFrame {
    private final mysql db;
    private final Statement stm;
    private ResultSet res;
    private final HashMap<String, Integer> idOrder;
    int dragxmouse;
    int dragymouse;
    /**
     * Creates new form pesanan
     */
    public daftarPesanan() throws SQLException {
        this.setUndecorated(true);
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        setTitle("DAFTAR PESANAN DALAM ANTRIAN | DAPUR");
        setPreferredSize(new Dimension(5000, 800));
        setResizable(false);
        setLocationRelativeTo(null);

        this.db = new mysql();
        this.stm = mysql.getConnection().createStatement();
        this.idOrder = new HashMap<String, Integer>();

        this.updateOrderList();
        finishAlert.setVisible(false);
        orderItems.setVisible(false);
        daftarOrder.setEnabled(false);
        
        pack();
    }
    
    private void updateOrderList() {
        try {
            DefaultTableModel orderLists = (DefaultTableModel) daftarPesanan.getModel();
            this.res = this.stm.executeQuery("SELECT * FROM `orders` WHERE `status` = '0' ORDER BY `orderTime` ASC");
            orderLists.setRowCount(0);
            
            if(this.res.next()) {
                daftarOrder.addItem("Pilih order...");
                do {
                    String orderID = this.res.getString("orderID");
                    String orderNumber = this.res.getString("orderNumber");
                    Date orderDate = this.res.getDate("orderTime");
                    Time orderTime = this.res.getTime("orderTime");
                    
                    SimpleDateFormat dateFormater = new SimpleDateFormat("EEEE, dd MMM YYYY");
                    String ordDate = dateFormater.format(orderDate);
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                    String ordTime = timeFormatter.format(orderTime);
                    
                    String orderDateTime = ordDate +" "+ ordTime;
                    
                    orderLists.addRow(new Object[] {orderID, orderNumber, orderDateTime});
                    
                    int ordID = Integer.parseInt(orderID);
                    String item = "Order [" + orderNumber + "]";
                    daftarOrder.addItem(item);
                    this.idOrder.put(item, ordID);
                }
                while(this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userbtn1 = new javax.swing.JButton();
        menubtn = new javax.swing.JButton();
        pilihanbtn = new javax.swing.JButton();
        status = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        finishAlert = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        daftarOrder = new javax.swing.JComboBox();
        finishOrder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        daftarPesanan = new javax.swing.JTable();
        orderItems = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ordItems = new javax.swing.JTable();
        orderNumber = new javax.swing.JLabel();
        onsreen = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Pesanan");
        getContentPane().setLayout(null);

        userbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/userbtn1.png"))); // NOI18N
        userbtn1.setBorderPainted(false);
        userbtn1.setContentAreaFilled(false);
        userbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userbtn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                userbtn1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userbtn1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userbtn1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userbtn1MouseEntered(evt);
            }
        });
        getContentPane().add(userbtn1);
        userbtn1.setBounds(0, 100, 270, 68);

        menubtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/menubtn1.png"))); // NOI18N
        menubtn.setBorderPainted(false);
        menubtn.setContentAreaFilled(false);
        menubtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menubtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menubtnMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menubtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menubtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menubtnMouseEntered(evt);
            }
        });
        menubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubtnActionPerformed(evt);
            }
        });
        getContentPane().add(menubtn);
        menubtn.setBounds(0, 170, 270, 69);

        pilihanbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/pilihanbtn1.png"))); // NOI18N
        pilihanbtn.setBorderPainted(false);
        pilihanbtn.setContentAreaFilled(false);
        pilihanbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pilihanbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseEntered(evt);
            }
        });
        getContentPane().add(pilihanbtn);
        pilihanbtn.setBounds(0, 240, 270, 69);

        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/status1.png"))); // NOI18N
        status.setBorderPainted(false);
        status.setContentAreaFilled(false);
        status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                statusMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statusMouseEntered(evt);
            }
        });
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        getContentPane().add(status);
        status.setBounds(0, 300, 270, 69);

        jPanel1.setBackground(new java.awt.Color(246, 243, 255));

        jLabel2.setText("TANDAI ORDER SUDAH SELESAI");

        finishAlert.setText("{{finishAlert}}");

        jLabel3.setText("Pilih Order:");

        daftarOrder.setModel(new javax.swing.DefaultComboBoxModel());
        daftarOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarOrderActionPerformed(evt);
            }
        });

        finishOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/selesai1.png"))); // NOI18N
        finishOrder.setBorderPainted(false);
        finishOrder.setContentAreaFilled(false);
        finishOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finishOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                finishOrderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                finishOrderMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                finishOrderMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                finishOrderMouseEntered(evt);
            }
        });
        finishOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(finishAlert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(178, 178, 178))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(finishOrder)
                            .addComponent(daftarOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finishAlert, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daftarOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(finishOrder)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(710, 210, 350, 190);

        daftarPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "No. Order", "Waktu Order"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        daftarPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarPesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(daftarPesanan);
        if (daftarPesanan.getColumnModel().getColumnCount() > 0) {
            daftarPesanan.getColumnModel().getColumn(0).setMaxWidth(40);
            daftarPesanan.getColumnModel().getColumn(1).setMaxWidth(100);
            daftarPesanan.getColumnModel().getColumn(2).setMaxWidth(200);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(350, 210, 340, 190);

        orderItems.setBackground(new java.awt.Color(246, 243, 255));
        orderItems.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ordItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nama", "Jumlah", "Kategori"
            }
        ));
        jScrollPane2.setViewportView(ordItems);
        if (ordItems.getColumnModel().getColumnCount() > 0) {
            ordItems.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        orderItems.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 750, 230));

        orderNumber.setBackground(new java.awt.Color(0, 0, 0));
        orderNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderNumber.setText("Order Number");
        orderItems.add(orderNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        getContentPane().add(orderItems);
        orderItems.setBounds(310, 420, 810, 290);

        onsreen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                onsreenMouseDragged(evt);
            }
        });
        onsreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onsreenMousePressed(evt);
            }
        });
        getContentPane().add(onsreen);
        onsreen.setBounds(0, 0, 1160, 770);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/statuspesanan.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1162, 768);

        setSize(new java.awt.Dimension(1293, 801));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void finishOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishOrderActionPerformed
        try {
            Object order = daftarOrder.getSelectedItem();
            int id = this.idOrder.get((String) order);

            String update = "UPDATE `orders` SET `status` = '1' WHERE `orderID` = '" + id + "'";
            int updateOrder = this.stm.executeUpdate(update);
            if (updateOrder > 0) {
                finishAlert.setText(order + " diselesaikan dari dapur");
                finishAlert.setVisible(true);

                daftarOrder.removeItem(order);
                this.updateOrderList();

                int size = daftarOrder.getItemCount();
                if (size > 0) {
                    daftarOrder.setSelectedIndex(0);
                }
                
                orderItems.setVisible(false);
            } else {
                finishAlert.setText("Terjadi kesalahan. Harap ulangi kembali.");
                finishAlert.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_finishOrderActionPerformed

    private void daftarPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarPesananMouseClicked
        try {
            DefaultTableModel orderItem = (DefaultTableModel) daftarPesanan.getModel();
            DefaultTableModel ordItem = (DefaultTableModel) ordItems.getModel();
            ordItem.setRowCount(0);
            
            int sel = daftarPesanan.getSelectedRow();
            String ID = orderItem.getValueAt(sel, 0).toString();
            
            orderItems.setVisible(true);
            
            String getItem = "SELECT o.orderID, o.orderNumber, i.orderQuantity as qty, f.foodID, f.name, c.categoryName FROM `orders` `o` JOIN `orderItems` `i` ON i.orderID = o.orderID JOIN `foods` f ON f.foodID = i.foodID JOIN foodCategory c ON c.categoryID = f.foodCategory WHERE o.orderID = '"+ ID +"'";
            this.res = this.stm.executeQuery(getItem);
            if(this.res.next()) {
                String ordNumber = this.res.getString("orderNumber");
                do {
                    String id = this.res.getString("foodID");
                    String nama = this.res.getString("name");
                    String jumlah = this.res.getString("qty");
                    String kategori = this.res.getString("categoryName");
                
                    ordItem.addRow(new Object[] {id, nama, jumlah, kategori});
                }
                while(this.res.next());
                
                String selOrder = "Order ["+ ordNumber +"]";
                daftarOrder.setSelectedItem(selOrder);
                daftarOrder.setEnabled(true);
                orderNumber.setText("Order #"+ ordNumber);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_daftarPesananMouseClicked

    private void daftarOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daftarOrderActionPerformed

    private void userbtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtn1MousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn1.png"));
        userbtn1.setIcon(b);
    }//GEN-LAST:event_userbtn1MousePressed

    private void userbtn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtn1MouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn2.png"));
        userbtn1.setIcon(b);
    }//GEN-LAST:event_userbtn1MouseReleased

    private void userbtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtn1MouseClicked
        try {
            new datausers().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_userbtn1MouseClicked

    private void userbtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtn1MouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn1.png"));
        userbtn1.setIcon(b);
    }//GEN-LAST:event_userbtn1MouseExited

    private void userbtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtn1MouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn2.png"));
        userbtn1.setIcon(b);
    }//GEN-LAST:event_userbtn1MouseEntered

    private void menubtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn1.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMousePressed

    private void menubtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn2.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseReleased

    private void menubtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseClicked
        try {
            new menu().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menubtnMouseClicked

    private void menubtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn1.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseExited

    private void menubtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn2.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseEntered

    private void menubtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubtnActionPerformed

    }//GEN-LAST:event_menubtnActionPerformed

    private void pilihanbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn1.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMousePressed

    private void pilihanbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn2.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseReleased

    private void pilihanbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn1.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseExited

    private void pilihanbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn2.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseEntered

    private void pilihanbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseClicked
        try {
            new pilihan().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pilihanbtnMouseClicked

    private void statusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/status1.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMousePressed

    private void statusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/status2.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseReleased

    private void statusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseClicked
        try {
            new daftarPesanan().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_statusMouseClicked

    private void statusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/status1.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseExited

    private void statusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/status2.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseEntered

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void finishOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finishOrderMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/selesai2.png"));
        finishOrder.setIcon(b);  
    }//GEN-LAST:event_finishOrderMouseEntered

    private void finishOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finishOrderMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/selesai1.png"));
        finishOrder.setIcon(b);  
    }//GEN-LAST:event_finishOrderMouseExited

    private void finishOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finishOrderMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/selesai1.png"));
        finishOrder.setIcon(b);  
    }//GEN-LAST:event_finishOrderMousePressed

    private void finishOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finishOrderMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/selesai2.png"));
        finishOrder.setIcon(b);  
    }//GEN-LAST:event_finishOrderMouseReleased

    private void onsreenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onsreenMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - dragxmouse,y-dragymouse);
        System.out.println(x+","+y);
    }//GEN-LAST:event_onsreenMouseDragged

    private void onsreenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onsreenMousePressed
        dragxmouse = evt.getX();
        dragymouse = evt.getY();
    }//GEN-LAST:event_onsreenMousePressed

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
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(daftarPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new daftarPesanan().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(daftarPesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JComboBox daftarOrder;
    private javax.swing.JTable daftarPesanan;
    private javax.swing.JLabel finishAlert;
    private javax.swing.JButton finishOrder;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menubtn;
    private javax.swing.JLabel onsreen;
    private javax.swing.JTable ordItems;
    private javax.swing.JPanel orderItems;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JButton pilihanbtn;
    private javax.swing.JButton status;
    private javax.swing.JButton userbtn1;
    // End of variables declaration//GEN-END:variables
}
