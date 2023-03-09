/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelanggan;

import admin.menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import server.helperClass;
import server.mysql;

/**
 *
 * @author DELL
 */
public class createOrder extends javax.swing.JFrame {
    private final mysql db;
    private final Statement stm;
    private ResultSet res;
    private int lastOrderID;
    private int viewOrderID;
    private final HashMap<String, Integer> idMenu;
    private final HashMap<Integer, Integer> orderItemQty; //id, jumlah
    private final HashMap<Integer, String> orderItemName; //id, nama
    private final HashMap<Integer, Double> orderItemPrices; //id, harga
    int dragxmouse;
    int dragymouse;

    /**
     * Creates new form newOrder
     */
    public createOrder() throws SQLException {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        setTitle("BUAT ORDER BARU");
        setPreferredSize(new Dimension(5000, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        
        this.db = new mysql();
        this.stm = mysql.getConnection().createStatement();
        this.idMenu = new HashMap<String, Integer>();
        this.orderItemQty = new HashMap<Integer, Integer>();
        this.orderItemName = new HashMap<Integer, String>();
        this.orderItemPrices = new HashMap<Integer, Double>();

        String getMenu = "SELECT f.foodID, f.name, f.price, cat.categoryName FROM foods f JOIN foodcategory cat ON f.foodCategory = cat.categoryID";
        this.res = this.stm.executeQuery(getMenu);

        if (this.res.next()) {
            DefaultTableModel model = (DefaultTableModel) foodList.getModel();
            TableColumn col = new TableColumn(model.getColumnCount());
            JTable table = foodList;

            TableColumn menuColumn = table.getColumnModel().getColumn(0);

            ResultSetMetaData rsmd = this.res.getMetaData();
            int totalRow = rsmd.getColumnCount();

            int i = 0;
            do {
                int id = this.res.getInt("foodID");
                String nama = this.res.getString("name");
                String kategori = this.res.getString("categoryName");

                model.addRow(new Object[]{id, nama, kategori});
            } while (this.res.next());
        }
        jumlahAlert.setVisible(false);
        idField.setEditable(false);
        namaField.setEditable(false);
        hargaField.setEditable(false);
        totalHarga.setEditable(false);
        orderMsg.setVisible(false);
        viewOrder.setVisible(false);
        totalOrderPrice.setEditable(false);
        orderQty.setEditable(false);
        
        JTextField tf = jumlahField;
        
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent ke) {
            String value = tf.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ((int) ke.getKeyChar() == 8)) {
               tf.setEditable(true);
               jumlahAlert.setVisible(false);
               if(menu.nn > 0) {
                    jumlahAlert.setVisible(true);
                    if((int) ke.getKeyChar() != 8) {
                        jumlahAlert.setText("Oke lanjutkan ...");
                    }
                    menu.nn = 0;
               }
            } else {
               tf.setEditable(false);
               jumlahAlert.setVisible(true);
               jumlahAlert.setText("Masukkan hanya angka dan titik !!");
               menu.nn = menu.nn + 1;
            }
         }
      });
        
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JScrollPane();
        foodList = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        hargaField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jumlahField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jumlahAlert = new javax.swing.JLabel();
        totalHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addItemBtn = new javax.swing.JLabel();
        editBG = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderItemLists = new javax.swing.JTable();
        orderQty = new javax.swing.JTextField();
        totalOrderPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        orderBtn = new javax.swing.JLabel();
        resetBtn = new javax.swing.JLabel();
        viewOrder = new javax.swing.JLabel();
        bottomPanelBG = new javax.swing.JLabel();
        backLink = new javax.swing.JLabel();
        onsreen = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        orderMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foodList.setForeground(new java.awt.Color(255, 149, 0));
        foodList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        foodList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodListMouseClicked(evt);
            }
        });
        topPanel.setViewportView(foodList);

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 900, 220));

        bottomPanel.setBackground(new java.awt.Color(0, 0, 0));
        bottomPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 149, 0));
        jLabel1.setForeground(new java.awt.Color(255, 149, 0));
        jLabel1.setText("ID        :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        idField.setEditable(false);
        jPanel1.add(idField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 150, -1));

        jLabel2.setBackground(new java.awt.Color(255, 149, 0));
        jLabel2.setForeground(new java.awt.Color(255, 149, 0));
        jLabel2.setText("Nama  :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        namaField.setEditable(false);
        jPanel1.add(namaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, -1));

        hargaField.setEditable(false);
        jPanel1.add(hargaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 150, -1));

        jLabel3.setBackground(new java.awt.Color(255, 149, 0));
        jLabel3.setForeground(new java.awt.Color(255, 149, 0));
        jLabel3.setText("Harga  :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jumlahField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahFieldKeyReleased(evt);
            }
        });
        jPanel1.add(jumlahField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 150, -1));

        jLabel5.setBackground(new java.awt.Color(255, 149, 0));
        jLabel5.setForeground(new java.awt.Color(255, 149, 0));
        jLabel5.setText("Jumlah :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jumlahAlert.setText("{{jumlahAlert}}");
        jPanel1.add(jumlahAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        totalHarga.setEditable(false);
        jPanel1.add(totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 150, -1));

        jLabel6.setBackground(new java.awt.Color(255, 149, 0));
        jLabel6.setForeground(new java.awt.Color(255, 149, 0));
        jLabel6.setText("Total    :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        addItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/shop1.png"))); // NOI18N
        addItemBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addItemBtnMouseClicked(evt);
            }
        });
        jPanel1.add(addItemBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, -1));

        editBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/bb.png"))); // NOI18N
        jPanel1.add(editBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 250, 1390));

        bottomPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 360));

        orderItemLists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Harga satuan", "Jumlah", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderItemLists);
        if (orderItemLists.getColumnModel().getColumnCount() > 0) {
            orderItemLists.getColumnModel().getColumn(0).setMaxWidth(40);
            orderItemLists.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        bottomPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 690, 230));

        orderQty.setEditable(false);
        bottomPanel.add(orderQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 80, -1));

        totalOrderPrice.setEditable(false);
        bottomPanel.add(totalOrderPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 250, 160, -1));

        jLabel10.setForeground(new java.awt.Color(255, 149, 0));
        jLabel10.setText("Total :");
        bottomPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));

        orderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/tambah1.png"))); // NOI18N
        orderBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        orderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderBtnMouseClicked(evt);
            }
        });
        bottomPanel.add(orderBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 120, 50));

        resetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/refresh1.png"))); // NOI18N
        resetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBtnMouseClicked(evt);
            }
        });
        bottomPanel.add(resetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, 120, 48));

        viewOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/order.png"))); // NOI18N
        viewOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewOrderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewOrderMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewOrderMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewOrderMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewOrderMouseEntered(evt);
            }
        });
        bottomPanel.add(viewOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, -1, -1));

        bottomPanelBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/bb.png"))); // NOI18N
        bottomPanelBG.setText("jLabel1");
        bottomPanel.add(bottomPanelBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, -1));

        getContentPane().add(bottomPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 940, 320));

        backLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/backk.png"))); // NOI18N
        backLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLinkMouseClicked(evt);
            }
        });
        getContentPane().add(backLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 60));

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
        getContentPane().add(onsreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 760));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pelanggan/image/pesan.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        orderMsg.setFont(new java.awt.Font("Leelawadee", 1, 12)); // NOI18N
        orderMsg.setForeground(new java.awt.Color(255, 255, 255));
        orderMsg.setText("{{orderMsg}}");
        getContentPane().add(orderMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        setSize(new java.awt.Dimension(1280, 796));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void foodListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodListMouseClicked
        try {
            jumlahAlert.setVisible(false);
            DefaultTableModel dtm = (DefaultTableModel) foodList.getModel();
            int sel = foodList.getSelectedRow();
            String id = dtm.getValueAt(sel, 0).toString();

            this.res = this.stm.executeQuery("SELECT * FROM `foods` WHERE `foodID` = '" + id + "'");
            if (this.res.next()) {
                String nama = this.res.getString("name");
                String harga = this.res.getString("price");

                idField.setText(id);
                namaField.setText(nama);
                hargaField.setText(harga);
                jumlahField.setText("");
                totalHarga.setText("");
            }

            jumlahField.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(createOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_foodListMouseClicked

    private void jumlahFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahFieldKeyReleased
        String jumlah = jumlahField.getText();
        String harga = hargaField.getText();

        if (!jumlah.equals("") && !harga.equals("")) {
            Double j = Double.parseDouble(jumlah);
            Double h = Double.parseDouble(harga);

            Double t = (j * h);
            String total = t.toString();
            totalHarga.setText(total);
        }
    }//GEN-LAST:event_jumlahFieldKeyReleased
    
    private void clearOrderTable() {
        JTable table = orderItemLists;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        totalOrderPrice.setText("");
        orderQty.setText("");
    }
    
    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        this.clearOrderTable();
        
        this.orderItemName.clear();
        this.orderItemPrices.clear();
        this.orderItemQty.clear();        
    }//GEN-LAST:event_resetBtnMouseClicked

    private void orderBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderBtnMouseClicked
                               
        JTable table = orderItemLists;
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int totalOrder = model.getRowCount();

        if (totalOrder > 0) {
            try {
                orderMsg.setVisible(false);

                int totalQty = 0;
                for (int i = 0; i < totalOrder; i++) {
                    String tQty = model.getValueAt(i, 3).toString();
                    int totQty = Integer.parseInt(tQty);
                    totalQty += totQty;
                }

                Double totalHarga = 0.00;
                for (int i = 0; i < totalOrder; i++) {
                    String getHarga = model.getValueAt(i, 4).toString();
                    String sHarga = getHarga.substring(4, getHarga.length() - 3).replace(".", "");
                    Double totHarga = Double.parseDouble(sHarga);
                    totalHarga += totHarga;
                }

                String orderNumber = helperClass.generateOrderNumber();
                String date = helperClass.getCurrentDateTime();

                String insertOrder = "INSERT INTO `orders` (orderID, orderNumber, orderTime, orderQuantity, totalPrice, isPaid, status) VALUES (NULL, '" + orderNumber + "', '" + date + "', '" + totalQty + "', '" + totalHarga + "', 0, 0)";
                PreparedStatement pstmt = mysql.getConnection().prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
                int insert = pstmt.executeUpdate();
                int orderID;

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                    int in = 0;
                    this.viewOrderID = orderID;

                    for (int i = 0; i < totalOrder; i++) {
                        Object ID = model.getValueAt(i, 0);
                        String price = model.getValueAt(i, 2).toString();
                        Object qty = model.getValueAt(i, 3);

                        String p = price.substring(4, price.length() - 3).replace(".", "");
                        Double priceEach = Double.parseDouble(p);

                        String items = "INSERT INTO `orderItems` (ID, orderID, foodID, priceEach, orderQuantity) VALUES (NULL, '" + orderID + "', '" + ID + "', '" + priceEach + "', '" + qty + "')";
                        int addItems = this.stm.executeUpdate(items);

                        in += addItems;
                    }

                    if (in > 0) {
                        orderMsg.setText("Berhasil memasukkan data order");
                        this.clearOrderTable();
                        this.lastOrderID = orderID;
                        viewOrder.setVisible(true);
                    } else {
                        orderMsg.setText("Gagal memasukkan data order. Harap ulangi kembali");

                        String hapusOrder = "DELETE FROM `orders` WHERE `orderID` = '" + orderID + "'";
                        int hapus = this.stm.executeUpdate(hapusOrder);
                    }
                    orderMsg.setVisible(true);
                } else {
                    orderMsg.setText("Gagal memasukkan data order. Harap ulangi kembali");
                    orderMsg.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(createOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            orderMsg.setText("Tidak ada item dalam order !!!");
            orderMsg.setVisible(true);
        }
    }//GEN-LAST:event_orderBtnMouseClicked

    private void viewOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderMouseClicked
        try {
            int id = this.viewOrderID;
            viewOrder v = new viewOrder(id);
            v.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(createOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewOrderMouseClicked

    private void addItemBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addItemBtnMouseClicked
        String id = idField.getText();
        String jumlah = jumlahField.getText();
        
        if(id.equals("")) {
            jumlahAlert.setText("Pilih menu untuk ditambahkan!");
            jumlahAlert.setVisible(true);
        }
        else if (jumlah.equals("")) {
            jumlahAlert.setText("Masukkan jumlah order!");
            jumlahAlert.setVisible(true);
        } else {
            try {
                int ID = Integer.parseInt(id);
                int qty = Integer.parseInt(jumlah);

                this.res = this.stm.executeQuery("SELECT * FROM `foods` WHERE `foodID` = '" + ID + "'");
                if (this.res.next()) {
                    DefaultTableModel model = (DefaultTableModel) orderItemLists.getModel();

                    String nama = this.res.getString("name");
                    Double harga = this.res.getDouble("price");

                    this.orderItemQty.put(ID, qty);
                    this.orderItemName.put(ID, nama);
                    this.orderItemPrices.put(ID, harga);

                    Double total = (qty * harga);

                    model.addRow(new Object[]{ID, nama, helperClass.formatRupiah(harga), qty, helperClass.formatRupiah(total)});
                    int totalOrder = model.getRowCount();
                    int totalQty = 0;
                    for (int i = 0; i < totalOrder; i++) {
                        String tQty = model.getValueAt(i, 3).toString();
                        int totQty = Integer.parseInt(tQty);
                        totalQty += totQty;
                    }

                    String totalOrderQty = String.valueOf(totalQty);
                    orderQty.setText(totalOrderQty);

                    Double totalHarga = 0.00;
                    for (int i = 0; i < totalOrder; i++) {
                        String getHarga = model.getValueAt(i, 4).toString();
                        String sHarga = getHarga.substring(4, getHarga.length() - 3).replace(".", "");
                        Double totHarga = Double.parseDouble(sHarga);
                        totalHarga += totHarga;
                    }

                    String tHarga = helperClass.formatRupiah(totalHarga);
                    totalOrderPrice.setText(tHarga);
                }

                idField.setText("");
                namaField.setText("");
                hargaField.setText("");
                jumlahField.setText("");
                totalHarga.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(createOrder.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_addItemBtnMouseClicked

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

    private void viewOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("image/order1.png"));
        viewOrder.setIcon(b);
    }//GEN-LAST:event_viewOrderMouseEntered

    private void viewOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("image/order.png"));
        viewOrder.setIcon(b);
    }//GEN-LAST:event_viewOrderMouseExited

    private void viewOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("image/order.png"));
        viewOrder.setIcon(b);
    }//GEN-LAST:event_viewOrderMousePressed

    private void viewOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewOrderMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("image/order1.png"));
        viewOrder.setIcon(b);
    }//GEN-LAST:event_viewOrderMouseReleased

    private void backLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLinkMouseClicked
        new pelanggan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backLinkMouseClicked

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
            java.util.logging.Logger.getLogger(createOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new createOrder().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(createOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addItemBtn;
    private javax.swing.JLabel backLink;
    private javax.swing.JLabel bg;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel bottomPanelBG;
    private javax.swing.JLabel editBG;
    private javax.swing.JTable foodList;
    private javax.swing.JTextField hargaField;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jumlahAlert;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField namaField;
    private javax.swing.JLabel onsreen;
    private javax.swing.JLabel orderBtn;
    private javax.swing.JTable orderItemLists;
    private javax.swing.JLabel orderMsg;
    private javax.swing.JTextField orderQty;
    private javax.swing.JLabel resetBtn;
    private javax.swing.JScrollPane topPanel;
    private javax.swing.JTextField totalHarga;
    private javax.swing.JTextField totalOrderPrice;
    private javax.swing.JLabel viewOrder;
    // End of variables declaration//GEN-END:variables
}
