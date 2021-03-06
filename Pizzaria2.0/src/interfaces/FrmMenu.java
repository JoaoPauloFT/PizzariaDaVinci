/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.net.URL;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 *
 * @author joaop
 */
public class FrmMenu extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenu2
     */
    public FrmMenu() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        URL url = this.getClass().getResource("/img/icon.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo finalizar o programa?", "Deseja finalizar?", WARNING_MESSAGE);
                if (resp == 0) {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(FrmMenu.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DplPrincipal = new javax.swing.JDesktopPane();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCliente = new javax.swing.JMenu();
        MenuGerenCliente = new javax.swing.JMenuItem();
        MenuAlimento = new javax.swing.JMenu();
        MenuPizza = new javax.swing.JMenuItem();
        MenuCalzone = new javax.swing.JMenuItem();
        MenuBebida = new javax.swing.JMenuItem();
        MenuBorda = new javax.swing.JMenuItem();
        MenuPedido = new javax.swing.JMenu();
        menuFazerPedido = new javax.swing.JMenuItem();
        MenuSobre = new javax.swing.JMenu();
        MenuSobreDesenvolvedor = new javax.swing.JMenuItem();
        Separador = new javax.swing.JPopupMenu.Separator();
        MenuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizzaria Da Vinci");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        DplPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        DplPrincipal.setName("Pizzaria Da Vinci"); // NOI18N

        javax.swing.GroupLayout DplPrincipalLayout = new javax.swing.GroupLayout(DplPrincipal);
        DplPrincipal.setLayout(DplPrincipalLayout);
        DplPrincipalLayout.setHorizontalGroup(
            DplPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        DplPrincipalLayout.setVerticalGroup(
            DplPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        getContentPane().add(DplPrincipal);

        MenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        MenuCliente.setText("Cliente");
        MenuCliente.setToolTipText("");
        MenuCliente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        MenuGerenCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        MenuGerenCliente.setText("Gerenciar Clientes");
        MenuGerenCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuGerenClienteActionPerformed(evt);
            }
        });
        MenuCliente.add(MenuGerenCliente);

        MenuPrincipal.add(MenuCliente);

        MenuAlimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alimento.png"))); // NOI18N
        MenuAlimento.setText("Alimento");
        MenuAlimento.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        MenuPizza.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        MenuPizza.setText("Pizza");
        MenuPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPizzaActionPerformed(evt);
            }
        });
        MenuAlimento.add(MenuPizza);

        MenuCalzone.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        MenuCalzone.setText("Calzone");
        MenuCalzone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCalzoneActionPerformed(evt);
            }
        });
        MenuAlimento.add(MenuCalzone);

        MenuBebida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        MenuBebida.setText("Bebida");
        MenuBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBebidaActionPerformed(evt);
            }
        });
        MenuAlimento.add(MenuBebida);

        MenuBorda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        MenuBorda.setText("Borda");
        MenuBorda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBordaActionPerformed(evt);
            }
        });
        MenuAlimento.add(MenuBorda);

        MenuPrincipal.add(MenuAlimento);

        MenuPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pedido.png"))); // NOI18N
        MenuPedido.setText("Pedido");
        MenuPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        menuFazerPedido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        menuFazerPedido.setText("Fazer Pedido");
        menuFazerPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFazerPedidoActionPerformed(evt);
            }
        });
        MenuPedido.add(menuFazerPedido);

        MenuPrincipal.add(MenuPedido);

        MenuSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sobre.png"))); // NOI18N
        MenuSobre.setText("Sobre");
        MenuSobre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        MenuSobreDesenvolvedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, java.awt.event.InputEvent.CTRL_MASK));
        MenuSobreDesenvolvedor.setText("Sobre o Desenvolvedor");
        MenuSobreDesenvolvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSobreDesenvolvedorActionPerformed(evt);
            }
        });
        MenuSobre.add(MenuSobreDesenvolvedor);
        MenuSobre.add(Separador);

        MenuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        MenuSair.setText("Sair");
        MenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSairActionPerformed(evt);
            }
        });
        MenuSobre.add(MenuSair);

        MenuPrincipal.add(MenuSobre);

        setJMenuBar(MenuPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuGerenClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGerenClienteActionPerformed
        FrmCliente meuForm = new FrmCliente();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuGerenClienteActionPerformed

    private void MenuPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPizzaActionPerformed
        FrmPizza meuForm = new FrmPizza();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuPizzaActionPerformed

    private void MenuCalzoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCalzoneActionPerformed
        FrmCalzone meuForm = new FrmCalzone();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuCalzoneActionPerformed

    private void MenuBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBebidaActionPerformed
        FrmBebida meuForm = new FrmBebida();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuBebidaActionPerformed

    private void MenuBordaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBordaActionPerformed
        FrmBorda meuForm = new FrmBorda();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuBordaActionPerformed

    private void menuFazerPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFazerPedidoActionPerformed
        FrmPedido meuForm = new FrmPedido();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_menuFazerPedidoActionPerformed

    private void MenuSobreDesenvolvedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSobreDesenvolvedorActionPerformed
        FrmSobre meuForm = new FrmSobre();
        DplPrincipal.add(meuForm);
        try {
            meuForm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar com a tela cheia! \nErro: " + ex);
        }
        meuForm.setVisible(true);
    }//GEN-LAST:event_MenuSobreDesenvolvedorActionPerformed

    private void MenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSairActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo finalizar o programa?", "Deseja finalizar?", WARNING_MESSAGE);
        if (resp == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_MenuSairActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DplPrincipal;
    private javax.swing.JMenu MenuAlimento;
    private javax.swing.JMenuItem MenuBebida;
    private javax.swing.JMenuItem MenuBorda;
    private javax.swing.JMenuItem MenuCalzone;
    private javax.swing.JMenu MenuCliente;
    private javax.swing.JMenuItem MenuGerenCliente;
    private javax.swing.JMenu MenuPedido;
    private javax.swing.JMenuItem MenuPizza;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem MenuSair;
    private javax.swing.JMenu MenuSobre;
    private javax.swing.JMenuItem MenuSobreDesenvolvedor;
    private javax.swing.JPopupMenu.Separator Separador;
    private javax.swing.JMenuItem menuFazerPedido;
    // End of variables declaration//GEN-END:variables
}
