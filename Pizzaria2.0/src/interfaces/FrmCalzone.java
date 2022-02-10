package interfaces;

import classes.Calzone;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FrmCalzone extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmCliente
     */
    public FrmCalzone() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTCalzone.getModel();
        jTCalzone.setRowSorter(new TableRowSorter(modelo));
        readJTable("calzone");
    }

    public void limpar() {
        txtNome.setText("");
        txtNumero.setText(null);
        txtValor.setText(null);
        txtPesquisa.setText("");
    }
    
    public String trocaVirgula(String trocar) {
        String trocado;
        if(trocar.contains(",")){
            String[] trocando = trocar.split(",");
            trocado = trocando[0] + "." + trocando[1];
        } else {
            trocado = trocar;
        }
        return trocado;
    }

    public void readJTable(String nomeArquivo) {
        DefaultTableModel modelo = (DefaultTableModel) jTCalzone.getModel();
        modelo.setRowCount(0);
        for (Calzone c : read(nomeArquivo)) {
            modelo.addRow(new Object[]{
                c.getNumero(),
                c.getNome(),
                c.getPreco()
            });
        }
    }

    public List<Calzone> read(String nomeArquivo) {
        List<Calzone> calzones = new ArrayList<>();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\" + nomeArquivo + ".txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                Calzone c = new Calzone();
                c.setNumero(vetCampos[0]);
                c.setNome(vetCampos[1]);
                c.setPreco(vetCampos[2]);
                calzones.add(c);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + ex, "ERRO", ERROR_MESSAGE);
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar os dados! \nErro: " + erro, "ERRO", ERROR_MESSAGE);
        }
        return calzones;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlPrincipal = new javax.swing.JPanel();
        PnlSuperior = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValor = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        PnlInferior = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCalzone = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Gerenciamento de Calzones");

        PnlPrincipal.setLayout(new java.awt.GridLayout(2, 1));

        PnlSuperior.setBackground(new java.awt.Color(255, 255, 255));
        PnlSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        PnlSuperior.setForeground(new java.awt.Color(240, 240, 240));
        PnlSuperior.setLayout(new java.awt.GridLayout(4, 6, 40, 80));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Número*:");
        PnlSuperior.add(jLabel5);

        txtNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        PnlSuperior.add(txtNumero);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Nome*:");
        PnlSuperior.add(jLabel6);

        txtNome.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtNome);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Valor*:");
        PnlSuperior.add(jLabel7);

        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        PnlSuperior.add(txtValor);
        PnlSuperior.add(jLabel18);
        PnlSuperior.add(jLabel16);
        PnlSuperior.add(jLabel14);
        PnlSuperior.add(jLabel8);
        PnlSuperior.add(jLabel9);
        PnlSuperior.add(jLabel13);

        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnAdd.setText("Adicionar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        PnlSuperior.add(btnAdd);

        btnExcluir.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        PnlSuperior.add(btnExcluir);

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        PnlSuperior.add(btnAlterar);
        PnlSuperior.add(jLabel10);
        PnlSuperior.add(jLabel12);
        PnlSuperior.add(jLabel17);
        PnlSuperior.add(jLabel11);
        PnlSuperior.add(jLabel20);
        PnlSuperior.add(jLabel19);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Pesquisar:");
        PnlSuperior.add(jLabel15);

        txtPesquisa.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtPesquisa);

        btnBuscar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        PnlSuperior.add(btnBuscar);

        PnlPrincipal.add(PnlSuperior);

        PnlInferior.setBackground(new java.awt.Color(255, 255, 255));
        PnlInferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        PnlInferior.setLayout(new java.awt.BorderLayout());

        jTCalzone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTCalzone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NÚMERO:", "NOME:", "VALOR:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTCalzone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTCalzoneMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTCalzone);

        PnlInferior.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");
        PnlInferior.add(jLabel1, java.awt.BorderLayout.LINE_END);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");
        PnlInferior.add(jLabel2, java.awt.BorderLayout.PAGE_END);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel3");
        PnlInferior.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel4");
        PnlInferior.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel21.setText("* Preenchimento obrigatório");
        PnlInferior.add(jLabel21, java.awt.BorderLayout.PAGE_START);

        PnlPrincipal.add(PnlInferior);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preeencha o campo Nome!", "Preencha os campos necessarios!", PLAIN_MESSAGE);
        } else if (txtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preeencha o campo Número!", "Preencha os campos necessarios!", PLAIN_MESSAGE);
        } else if (txtValor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preeencha o campo Valor!", "Preencha os campos necessarios!", PLAIN_MESSAGE);
        } else {
            try {
                FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                boolean saber = true;
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if(txtNumero.getText().equals(vetCampos[0])){
                        JOptionPane.showMessageDialog(null, "Já consta no sistema!", "Este calzone já está cadastrado!", PLAIN_MESSAGE);
                        saber = false;
                    }
                }
                if (saber) {
                    Calzone c = new Calzone();
                    c.setNome(txtNome.getText().toLowerCase());
                    c.setNumero(txtNumero.getText());
                    c.setPreco(trocaVirgula(txtValor.getText()));
                    c.cadastrar();
                    readJTable("calzone");
                    limpar();
                }
            } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
            }   
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTCalzone.getSelectedRow() != -1) {
            Calzone c = new Calzone();
            c.excluir(txtNumero.getText());
            readJTable("calzone");
            limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTCalzoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCalzoneMouseClicked
        if (jTCalzone.getSelectedRow() != -1) {
            txtNumero.setText(jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 0).toString());
            txtNome.setText(jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 1).toString());
            txtValor.setText(jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 2).toString());
            txtNumero.disable();
        }
    }//GEN-LAST:event_jTCalzoneMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTCalzone.getSelectedRow() != -1) {
            Calzone c = new Calzone();
            c.setNumero(txtNumero.getText());
            c.setNome(txtNome.getText().toLowerCase());
            c.setPreco(trocaVirgula(txtValor.getText()));
            c.alterar(txtNumero.getText());
            readJTable("calzone");
            limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtPesquisa.getText().equals("")) {
            readJTable("calzone");
        } else {
            try {
                FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
                PrintWriter registroCliente = new PrintWriter(arquivoCliente);
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if (vetCampos[1].contains(txtPesquisa.getText().toLowerCase())) {
                        registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";");
                    }
                }
                registroCliente.close();
                arquivoCliente.close();
                readJTable("arqTemp");
                limpar();
            } catch (IOException erro) {
                System.out.print("Erro ao consultar um calzone! \nErro: " + erro);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlInferior;
    private javax.swing.JPanel PnlPrincipal;
    private javax.swing.JPanel PnlSuperior;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCalzone;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtNumero;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JFormattedTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
