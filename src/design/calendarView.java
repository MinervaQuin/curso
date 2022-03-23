/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package design;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.*;

public class calendarView extends javax.swing.JFrame {
    
    private int actualMonth;
    private int actualYear;
    private calendar actualCalendar;
    
    // Constructor
    public calendarView() {
        initComponents();
        test();
        Calendar cl = new GregorianCalendar();
        actualMonth = cl.get(Calendar.MONTH);
        actualYear = cl.get(Calendar.YEAR);
        updateTasks();
    }
    
    // Test para añadir tareas al calendario
    public void test() {
        calendar c = new calendar("prueba");
        task t = new task("Esto es una prueba");
        String date = "2022-03-23";
        c.addTask(date, t);
        t = new task("Esto es una prueba1");
        c.addTask(date, t);
        t = new task("Esto es una prueba2");
        c.addTask(date, t);
        t = new task("Esto es una prueba3");
        c.addTask(date, t);
        date = "2022-03-24";
        t = new task("Esto es una prueba1");
        c.addTask(date, t);
        date = "2022-03-23";
        //List l = c.getTasks(date);
        date = "2022-03-23";
        for (int i = 0; i < 7; i++) {
            date = "2022-02-2" + i;
            t = new task("En el mes actual");
            c.addTask(date, t);
            c.addTask(date, t);
            c.addTask(date, t);
        }
        //l = c.getTasks(date);
        this.actualCalendar = c;
        task t1 = new task("Cambiado");
        c.setTask(date, 1, t1);
    }
    
    // Método para formatear la tabla y actualizar las tareas
    public void updateTasks() {
        jTable1.setRowHeight(80);
        Object mesAux [][] = calculateDate(actualMonth, actualYear);
        for (int i = 0; i<mesAux.length;i++) {
            for (int j = 0; j<mesAux[0].length;j++) {
                if (mesAux[i][j] == null) {
                    mesAux[i][j] = new String[]{""};
                } else {
                    int day = (Integer)mesAux[i][j];
                    String date = "";
                    if (actualMonth <= 9) {
                        date = actualYear + "-0" + actualMonth;
                    } else {
                        date = actualYear + "-" + actualMonth;
                    }
                    if (day <= 9) {
                        date += "-0" + day;
                    } else {
                        date += "-" + day;
                    }
                    List<task> tasks = actualCalendar.getTasks(date);
                    String[] content = new String[tasks.size()+1];
                    content[0] = Integer.toString(day);
                    for (int k = 1; k < content.length; k++) {
                        content[k] = tasks.get(k-1).getName();
                    }
                    mesAux[i][j] = content;
                }
            }
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            mesAux,
            new String [] {
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }));
       MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
       jTable1.setDefaultRenderer(String[].class, renderer);
       for (int i = 0; i <7; i++) {
           jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
       }
    }
    
    // Método para calcular la fecha en la que se encuentra el usuario actualmente
    private Object[][] calculateDate (int month, int year) {
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar c1 = new GregorianCalendar(year, month, 1);
        Object mesAux [][] = getMonth(c1.get(Calendar.DAY_OF_MONTH), c1.get(Calendar.DAY_OF_WEEK), 
                c1.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.actualMonth = month;
        this.actualYear = year;
        jLabel1.setText(months[month] + " " + year);
        return mesAux;
    }
    
    // Método para conseguir el mes entero de la fecha
    private Object [][] getMonth(int dayM, int dayOfWeek, int maxDayOfMonth) {
        Object mes[][] = {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            };
        dayOfWeek = dayOfWeek-2;
        if (dayOfWeek == -1) {
            dayOfWeek = 6;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = dayOfWeek; j < 7; j++) {
                if (dayM > maxDayOfMonth) {
                    break;
                }
                mes[i][j] = dayM;
                dayM++;
            }
            dayOfWeek = 0;
        }
        return mes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Prev");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Botón de avanzar al siguiente mes
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.actualMonth + 1 == 12) {
            this.actualMonth = 0;
            this.actualYear += 1;
        } else {
            this.actualMonth += 1;
        }
        updateTasks();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Botón de retroceder al anterior mes
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (this.actualMonth - 1 == -1) {
            this.actualMonth = 11;
            this.actualYear -= 1;
        } else {
            this.actualMonth -= 1;
        }
        updateTasks();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calendarView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
