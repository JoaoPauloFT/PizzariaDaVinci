package interfaces;

import classes.Cliente;
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

public class FrmCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmCliente
     */
    public FrmCliente() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTCliente.getModel();
        jTCliente.setRowSorter(new TableRowSorter(modelo));
        readJTable("cliente");
    }

    public FrmCliente(String nome, String endereco, String bairro, String cidade, String telefone){
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTCliente.getModel();
        jTCliente.setRowSorter(new TableRowSorter(modelo));
        readJTable("cliente");
        txtNome.setText(nome);
        txtEndereco.setText(endereco);
        txtBairro.setText(bairro);
        txtCidade.setText(cidade);
        txtTelefone.setText(telefone);
    }
    
    public void limpar() {
        txtNome.setText("");
        txtCPF.setText(null);
        txtEndereco.setText("");
        txtBairro.setText(null);
        txtTelefone.setText(null);
        txtPesquisa.setText("");
        txtCidade.setText("");
        txtComplemento.setText("");
    }

    public void readJTable(String nomeArquivo) {
        DefaultTableModel modelo = (DefaultTableModel) jTCliente.getModel();
        modelo.setRowCount(0);
        for (Cliente c : read(nomeArquivo)) {
            modelo.addRow(new Object[]{
                c.getNome(),
                c.getCPF(),
                c.getEndereco(),
                c.getComplemento(),
                c.getBairro(),
                c.getCidade(),
                c.getTelefone()
            });
        }
    }

    public List<Cliente> read(String nomeArquivo) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\" + nomeArquivo + ".txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                Cliente c = new Cliente();
                c.setNome(vetCampos[0]);
                c.setCPF(vetCampos[1]);
                c.setEndereco(vetCampos[2]);
                c.setComplemento(vetCampos[3]);
                c.setBairro(vetCampos[4]);
                c.setCidade(vetCampos[5]);
                c.setTelefone(vetCampos[6]);
                clientes.add(c);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + ex, "ERRO", ERROR_MESSAGE);
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar os dados! \nErro: " + erro, "ERRO", ERROR_MESSAGE);
        }
        return clientes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlPrincipal = new javax.swing.JPanel();
        PnlSuperior = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        PnlInferior = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Genriamento de Clientes");

        PnlPrincipal.setLayout(new java.awt.GridLayout(2, 1));

        PnlSuperior.setBackground(new java.awt.Color(255, 255, 255));
        PnlSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        PnlSuperior.setForeground(new java.awt.Color(240, 240, 240));
        PnlSuperior.setLayout(new java.awt.GridLayout(4, 6, 40, 80));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Nome*:");
        PnlSuperior.add(jLabel5);

        txtNome.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtNome);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("CPF:");
        PnlSuperior.add(jLabel9);

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtCPF);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Endereço:");
        PnlSuperior.add(jLabel12);

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtEndereco);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Complemento:");
        PnlSuperior.add(jLabel17);

        txtComplemento.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtComplemento);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Bairro:");
        PnlSuperior.add(jLabel8);

        txtBairro.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtBairro);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Cidade:");
        PnlSuperior.add(jLabel13);

        txtCidade.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtCidade);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Telefone*:");
        PnlSuperior.add(jLabel10);

        txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTelefone.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PnlSuperior.add(txtTelefone);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("jLabel6");
        PnlSuperior.add(jLabel6);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("jLabel14");
        PnlSuperior.add(jLabel14);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("jLabel16");
        PnlSuperior.add(jLabel16);

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("jLabel18");
        PnlSuperior.add(jLabel18);

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

        jTCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME:", "CPF:", "ENDEREÇO:", "COMPLEMENTO:", "BAIRRO:", "CIDADE:", "TELEFONE:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTCliente);

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
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("* Preenchimento obrigatório");
        PnlInferior.add(jLabel4, java.awt.BorderLayout.PAGE_START);

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
        if (!txtNome.getText().equals("") && !txtTelefone.getText().equals("")) {
            try {
                FileReader arquivo = new FileReader("c:\\txt\\cliente.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                boolean saber = true;
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if(txtTelefone.getText().equals(vetCampos[6])){
                        JOptionPane.showMessageDialog(null, "Já consta no sistema!", "Este número de telefone já está cadastrado!", PLAIN_MESSAGE);
                        saber = false;
                    }
                }
                if (saber) {
                    Cliente c = new Cliente();
                    c.setNome(txtNome.getText().toLowerCase());
                    c.setCPF(txtCPF.getText());
                    c.setEndereco(txtEndereco.getText());
                    c.setComplemento(txtComplemento.getText());
                    c.setBairro(txtBairro.getText());
                    c.setTelefone(txtTelefone.getText());
                    c.setCidade(txtCidade.getText());
                    c.cadastrar();
                    readJTable("cliente");
                    limpar();
                }
            } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preeencha o campo Nome ou adicione um telefone!", "Preencha os campos necessarios!", PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTCliente.getSelectedRow() != -1) {
            Cliente c = new Cliente();
            c.excluir(txtTelefone.getText());
            readJTable("cliente");
            limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTClienteMouseClicked
        if (jTCliente.getSelectedRow() != -1) {
            txtNome.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 0).toString());
            txtCPF.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 1).toString());
            txtEndereco.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 2).toString());
            txtComplemento.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 3).toString());
            txtBairro.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 4).toString());
            txtCidade.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 5).toString());
            txtTelefone.setText(jTCliente.getValueAt(jTCliente.getSelectedRow(), 6).toString());
            txtTelefone.disable();
        }
    }//GEN-LAST:event_jTClienteMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTCliente.getSelectedRow() != -1) {
            Cliente c = new Cliente();
            c.setNome(txtNome.getText().toLowerCase());
            c.setCPF(txtCPF.getText());
            c.setEndereco(txtEndereco.getText());
            c.setBairro(txtBairro.getText());
            c.setTelefone(txtTelefone.getText());
            c.setCidade(txtCidade.getText());
            c.setComplemento(txtComplemento.getText());
            c.alterar(txtTelefone.getText());
            readJTable("cliente");
            limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtPesquisa.getText().equals("")) {
            readJTable("cliente");
        } else {
            try {
                FileReader arquivo = new FileReader("c:\\txt\\cliente.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
                PrintWriter registroCliente = new PrintWriter(arquivoCliente);
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if (vetCampos[0].contains(txtPesquisa.getText().toLowerCase())) {
                        registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";" + vetCampos[3] + ";" + vetCampos[4] + ";"
                                + vetCampos[5] + ";" + vetCampos[6] + ";");
                    }
                }
                registroCliente.close();
                arquivoCliente.close();
                readJTable("arqTemp");
                limpar();
            } catch (IOException erro) {
                System.out.print("Erro ao consultar um cliente! \nErro: " + erro);
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCliente;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
