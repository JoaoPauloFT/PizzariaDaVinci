package interfaces;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JSpinner;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
        
public class FrmPedido extends javax.swing.JInternalFrame {

    public FrmPedido() {
        initComponents();
        lblData.setText(pegandoData());
        preenchendoCBO("pizza");
        preenchendoCBO("calzone");
        preenchendoCBO("bebida");
        preenchendoCBO("borda");
        lblPedido.setText(String.valueOf(numPedido()));
        txtTroco.disable();
        txtCliente.disable();
        txtEndereco.disable();
        txtBairro.disable();
        txtCidade.disable();
        cboPizza2.setVisible(false);
        cboPizza3.setVisible(false);
        cboPizza4.setVisible(false);
        txtCod2.setVisible(false);
        txtCod3.setVisible(false);
        txtCod6.setVisible(false);
    }

    public int numPedido() {
        int numPedido = 0;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pedido.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\pedido.txt", true);
            PrintWriter registroCliente = new PrintWriter(arquivoCliente);
            String reg;
            while ((reg = registro.readLine()) != null) {
                numPedido = Integer.parseInt(reg);
            }
            numPedido++;
            registroCliente.println(numPedido);
            registroCliente.close();
            arquivoCliente.close();
            registro.close();
            arquivo.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao achar o arquivo pedido.txt! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
        }
        return numPedido;
    }

    public String totalPizza() {
        double valorzin = 0;
        for (int linha = 0; linha < jTPizza.getRowCount(); linha++) {
            double valorColuna = Double.parseDouble(jTPizza.getModel().getValueAt(linha, 6).toString());
            valorzin += valorColuna;
        }
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(valorzin);
        return oi;
    }

    public String conteudo() {
        String data = lblData.getText();
        String pedido = lblPedido.getText();
        String empresa = "Pizzaria Da Vinci";
        String cliente = txtCliente.getText();
        String endereco = txtEndereco.getText();
        String bairro = txtBairro.getText();
        String telefone = txtTelefone.getText();
        String formaPagamento = cboPagamento.getSelectedItem().toString();
        String[][] pizza = new String[10][9];
        int contador = 0;
        int numLinhas = jTPizza.getRowCount();
        numLinhas--;
        while (numLinhas >= contador) {
            for (int i = 0; i < 8; i++) {
                pizza[contador][i] = jTPizza.getValueAt(contador, i).toString();
            }
            contador++;
        }
        String[][] bebida = new String[10][6];
        int contador1 = 0;
        int numLinhas1 = jTBebida.getRowCount();
        numLinhas1--;
        while (numLinhas1 >= contador1) {
            for (int i = 0; i < 5; i++) {
                bebida[contador1][i] = jTBebida.getValueAt(contador1, i).toString();
            }
            contador1++;
        }
        String[][] calzone = new String[10][6];
        int contador2 = 0;
        int numLinhas2 = jTCalzone.getRowCount();
        numLinhas2--;
        while (numLinhas2 >= contador2) {
            for (int i = 0; i < 5; i++) {
                calzone[contador2][i] = jTCalzone.getValueAt(contador2, i).toString();
            }
            contador2++;
        }
        double troco1;
        String troco = "";
        String total = lblTotalTotal.getText();
        if (!txtTroco.getText().equals("")) {
            troco1 = Double.parseDouble(txtTroco.getText()) - Double.parseDouble(total);
            DecimalFormat formato = new DecimalFormat("0.00");
            troco = formato.format(troco1);
        }
        String tudo;
        if (formaPagamento.equals("Dinheiro")) {
            tudo = ""
                    + empresa + "\n"
                    + "Pedido: " + pedido
                    + "\n" + data
                    + "\n---------------------------------\n"
                    + "Sobre do Cliente \n"
                    + "---------------------------------\n"
                    + "Nome: " + cliente
                    + "\nEndereco: " + endereco + " " + bairro
                    + "\nTelefone: " + telefone
                    + "\nForma de Pagamento:"
                    + "\n" + formaPagamento
                    + "\nTroco: " + troco + "\n"
                    + "---------------------------------\n"
                    + "Num  Qtd  Descrição         Preço\n"
                    + "---------------------------------\n";
        } else {
            tudo = ""
                    + empresa + "\n"
                    + "Pedido: " + pedido
                    + "\n" + data
                    + "\n--------------------------------------\n"
                    + "Sobre do Cliente \n"
                    + "----------------------------------------\n"
                    + "Nome: " + cliente
                    + "\nEndereco: " + endereco
                    + "\nTelefone: " + telefone
                    + "\nForma de Pagamento:"
                    + "\n" + formaPagamento
                    + "\n----------------------------------------\n"
                    + "Num  Qtd  Descrição               Preço\n"
                    + "----------------------------------------\n";
        }
        contador = 0;
        while (numLinhas >= contador) {
            if (pizza[contador][1].contains("/")) {
                String[] vetPizza = pizza[contador][1].split("/");
                int contadorAleatorio = contador + 1;
                String contadorSeiLa = String.format("%03d", contadorAleatorio);
                String seiLa = String.format("%03d", Integer.parseInt(pizza[contador][4]));
                int tamanhoString = vetPizza[0].length();
                for (int i = tamanhoString; i <= 15; i++) {
                    vetPizza[0] += " ";
                }
                tamanhoString = vetPizza[1].length();
                for (int i = tamanhoString; i <= 15; i++) {
                    vetPizza[1] += " ";
                }
                if (vetPizza[2] != null) {
                    tamanhoString = vetPizza[2].length();
                    for (int i = tamanhoString; i <= 15; i++) {
                        vetPizza[2] += " ";
                    }
                }
                if (vetPizza[3] != null) {
                    tamanhoString = vetPizza[3].length();
                    for (int i = tamanhoString; i <= 15; i++) {
                        vetPizza[3] += " ";
                    }
                }
                if (vetPizza[3] != null) {
                    tudo += contadorSeiLa + "  " + seiLa + "  " + vetPizza[0] + "  " + pizza[contador][6] + "\n"
                            + "          " + vetPizza[1] + "\n"
                            + "          " + vetPizza[2] + "\n"
                            + "          " + vetPizza[3] + "\n"
                            + "          " + pizza[contador][3] + "\n"
                            + "          " + pizza[contador][2] + "\n"
                            + "OBS: " + pizza[contador][7] + "\n";
                } else if (vetPizza[2] != null) {
                    tudo += contadorSeiLa + "  " + seiLa + "  " + vetPizza[0] + "  " + pizza[contador][6] + "\n"
                            + "          " + vetPizza[1] + "\n"
                            + "          " + vetPizza[2] + "\n"
                            + "          " + pizza[contador][3] + "\n"
                            + "          " + pizza[contador][2] + "\n"
                            + "OBS: " + pizza[contador][7] + "\n";
                } else {
                    tudo += contadorSeiLa + "  " + seiLa + "  " + vetPizza[0] + "  " + pizza[contador][6] + "\n"
                            + "          " + vetPizza[1] + "\n"
                            + "          " + pizza[contador][3] + "\n"
                            + "          " + pizza[contador][2] + "\n"
                            + "OBS: " + pizza[contador][7] + "\n";
                }
            } else {
                int contadorAleatorio = contador + 1;
                String contadorSeiLa = String.format("%03d", contadorAleatorio);
                String seiLa = String.format("%03d", Integer.parseInt(pizza[contador][4]));
                int tamanhoString = pizza[contador][1].length();
                for (int i = tamanhoString; i <= 15; i++) {
                    pizza[contador][1] += " ";
                }
                tudo += "\n" + contadorSeiLa + "  " + seiLa + "  " + pizza[contador][1] + "  " + pizza[contador][6] + "\n"
                        + "          " + pizza[contador][3] + "\n"
                        + "          " + pizza[contador][2] + "\n"
                        + "OBS: " + pizza[contador][7] + "\n";
            }
            contador++;
        }
        contador1 = 0;
        while (numLinhas1 >= contador1) {
            int contadorAleatorio = contador + 1;
            String contadorSeiLa = String.format("%03d", contadorAleatorio);
            String seiLa = String.format("%03d", Integer.parseInt(bebida[contador1][2]));
            int tamanhoString = bebida[contador1][1].length();
            for (int i = tamanhoString; i <= 15; i++) {
                bebida[contador1][1] += " ";
            }
            tudo += "\n" + contadorSeiLa + "  " + seiLa + "  " + bebida[contador1][1] + "  " + bebida[contador1][4] + "\n";
            contador1++;
            contador++;
        }
        contador2 = 0;
        while (numLinhas2 >= contador2) {
            int contadorAleatorio = contador + 1;
            String contadorSeiLa = String.format("%03d", contadorAleatorio);
            String seiLa = String.format("%03d", Integer.parseInt(calzone[contador2][2]));
            int tamanhoString = calzone[contador2][1].length();
            for (int i = tamanhoString; i <= 15; i++) {
                calzone[contador2][1] += " ";
            }
            tudo += "\n" + contadorSeiLa + "  " + seiLa + "  " + calzone[contador2][1] + "  " + calzone[contador2][4] + "\n";
            contador2++;
            contador++;
        }
        String entrega = txtDesconto.getText();
        String desconto = txtDesconto.getText();
        tudo += "\n----------------------------------------"
                + "\nEntrega:                     R$ " + entrega
                + "\nDesconto:                    R$ " + desconto
                + "\nTOTAL:                       R$ " + total;
        System.out.println(tudo);
        return tudo;
    }

    public void gerarPDF(String pedido) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\txt\\pedido\\" + pedido + ".pdf"));
            document.open();
            // adicionando um parágrafo no documento
            document.add(new Paragraph(conteudo()));
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
    
//    public void imprimir(String pTexto) {
//        try {
//            InputStream prin = new ByteArrayInputStream(pTexto.getBytes());
//            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//            SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
//            PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
//            PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
//            printerAttributes.add(new JobName("Impressao", null));
//            printerAttributes.add(OrientationRequested.PORTRAIT);
//            printerAttributes.add(MediaSizeName.ISO_A4);
//            DocPrintJob printJob = impressora.createPrintJob();
//            try {
//                printJob.print(documentoTexto, (PrintRequestAttributeSet) printerAttributes);
//            } catch (PrintException ex) {
//                JOptionPane.showMessageDialog(null, "Erro ao fazer a impressão! \nErro: " + ex, "ERRO!!!", JOptionPane.ERROR_MESSAGE);
//            }
//            prin.close();
//        } catch (Exception ex) {
//
//        }
//    }

    public String totalBebida() {
        double valorzin = 0;
        for (int linha = 0; linha < jTBebida.getRowCount(); linha++) {
            double valorColuna = Double.parseDouble(jTBebida.getModel().getValueAt(linha, 4).toString());
            valorzin += valorColuna;
        }
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(valorzin);
        return oi;
    }

    public String totalCalzone() {
        double valorzin = 0;
        for (int linha = 0; linha < jTCalzone.getRowCount(); linha++) {
            double valorColuna = Double.parseDouble(jTCalzone.getModel().getValueAt(linha, 4).toString());
            valorzin += valorColuna;
        }
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(valorzin);
        return oi;
    }

    public void totalTotal() {
        double valorzin = 0;
        double pizza = Double.parseDouble(lblTotalPizza.getText());
        double bebida = Double.parseDouble(lblTotalBebida.getText());
        double calzone = Double.parseDouble(lblTotalCalzone.getText());
        double desconto;
        double entrega;
        if (txtDesconto.getText().equals("")) {
            desconto = 0.0;
        } else {
            String descontox = txtDesconto.getText();
            if (descontox.contains(",")) {
                descontox = trocaVirgula(descontox);
            }
            desconto = Double.parseDouble(descontox);
        }
        if (txtEntrega.getText().equals("")) {
            entrega = 0.0;
        } else {
            String entregax = txtEntrega.getText();
            if (entregax.contains(",")) {
                entregax = trocaVirgula(entregax);
            }
            entrega = Double.parseDouble(entregax);
        }
        valorzin = pizza + bebida + calzone + entrega - desconto;
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(valorzin);
        lblTotalTotal.setText(trocaVirgula(oi));
    }

    public void limparPizza() {
        cboQtdSabor.setSelectedIndex(0);
        txtCod1.setText("");
        txtCod2.setText("");
        cboPizza1.setSelectedIndex(0);
        cboPizza2.setSelectedIndex(0);
        cboTamanho.setSelectedIndex(0);
        cboBorda.setSelectedIndex(0);
        txtQuantidade.setValue(0);
        lblUnitario.setText("");
        lblTotal.setText("");
        txtObs.setText("");
    }

    public String trocaVirgula(String trocar) {
        String[] trocando = trocar.split(",");
        String trocado = trocando[0] + "." + trocando[1];
        return trocado;
    }

    public String trocaPonto(String trocar) {
        JOptionPane.showMessageDialog(null, trocar);
        String[] trocando = trocar.split(".");
        String trocado = trocando[0] + "," + trocando[1];
        return trocado;
    }

    public final void calcularValorTotal() {
        double total = (int) txtQuantidade.getValue() * Double.parseDouble(lblUnitario.getText());
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(total);
        lblTotal.setText(trocaVirgula(oi));
    }

    public final void calcularValorTotalBebida() {
        double total = (int) txtQuantidade1.getValue() * Double.parseDouble(lblUnitario1.getText());
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(total);
        lblTotal1.setText(trocaVirgula(oi));
    }

    public final void calcularValorTotalCalzone() {
        double total = (int) txtQuantidade2.getValue() * Double.parseDouble(lblUnitario2.getText());
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(total);
        lblTotal2.setText(trocaVirgula(oi));
    }

    public void limparBebida() {
        txtCod4.setText("");
        cboBebida.setSelectedIndex(0);
        txtQuantidade1.setValue(0);
        lblUnitario1.setText("");
        lblTotal1.setText("");
    }

    public void limparCalzone() {
        txtCod5.setText("");
        cboCalzone.setSelectedIndex(0);
        txtQuantidade2.setValue(0);
        lblUnitario2.setText("");
        lblTotal2.setText("");
    }

    private void calcularValor() {
        double preco = 0;
        double valorPizza1 = 0;
        double valorPizza2 = 0;
        double valorPizza3 = 0;
        double valorPizza4 = 0;
        int qtdSabor = Integer.parseInt(cboQtdSabor.getSelectedItem().toString());
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (cboTamanho.getSelectedItem().toString().equals("Grande")) {
                    if (qtdSabor == 1) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[3]);
                        }
                    } else if (qtdSabor == 2) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[3]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[3]);
                        }
                    }
                } else if (cboTamanho.getSelectedItem().toString().equals("Família")) {
                    if (qtdSabor == 1) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[4]);
                        }
                    } else if (qtdSabor == 2) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[4]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[4]);
                        }
                    } else if (qtdSabor == 3) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[4]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[4]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza3.getSelectedItem().toString())) {
                            valorPizza3 = Double.parseDouble(vetCampos[4]);
                        }
                    }
                } else if (cboTamanho.getSelectedItem().toString().equals("Gigante")) {
                    if (qtdSabor == 1) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[5]);
                        }
                    } else if (qtdSabor == 2) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[5]);
                        }
                    } else if (qtdSabor == 3) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza3.getSelectedItem().toString())) {
                            valorPizza3 = Double.parseDouble(vetCampos[5]);
                        }
                    } else if (qtdSabor == 4) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza3.getSelectedItem().toString())) {
                            valorPizza3 = Double.parseDouble(vetCampos[5]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza4.getSelectedItem().toString())) {
                            valorPizza4 = Double.parseDouble(vetCampos[5]);
                        }
                    }
                } else if (cboTamanho.getSelectedItem().toString().equals("Broto")) {
                    if (qtdSabor == 1) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[6]);
                        }
                    } else if (qtdSabor == 2) {
                        if (vetCampos[1].equalsIgnoreCase(cboPizza1.getSelectedItem().toString())) {
                            valorPizza1 = Double.parseDouble(vetCampos[6]);
                        } else if (vetCampos[1].equalsIgnoreCase(cboPizza2.getSelectedItem().toString())) {
                            valorPizza2 = Double.parseDouble(vetCampos[6]);
                        }
                    }
                }
            }
            registro.close();
            arquivo.close();
            if (qtdSabor == 1) {
                preco += valorPizza1;
            } else if (qtdSabor == 2) {
                preco += (valorPizza1 + valorPizza2) / 2;
            } else if (qtdSabor == 3) {
                preco += (valorPizza1 + valorPizza2 + valorPizza3) / 3;
            } else {
                preco += (valorPizza1 + valorPizza2 + valorPizza3 + valorPizza4) / 4;
            }
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pegar preço! \nErro: " + erro, "Erro", ERROR_MESSAGE);
        }
        //calcula o valor da borda
        String borda = cboBorda.getSelectedItem().toString();
        preco += pegarPreco(borda, "borda");
        DecimalFormat formato = new DecimalFormat("0.00");
        String oi = formato.format(preco);
        lblUnitario.setText(trocaVirgula(oi));
    }

    public final String pegandoData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public final double pegarPreco(String pesq, String arquivo1) {
        double valor = 0;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\" + arquivo1 + ".txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equalsIgnoreCase(pesq)) {
                    valor = Double.parseDouble(vetCampos[2]);
                }
            }
            registro.close();
            arquivo.close();
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pegar preço! \nErro: " + erro, "Erro", ERROR_MESSAGE);
        }
        return valor;
    }

    public final void preenchendoCBO(String classe) {
        try {
            FileReader arquivo = new FileReader("c:\\txt\\" + classe + ".txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (classe.equals("pizza")) {
                    cboPizza1.addItem(vetCampos[1]);
                    cboPizza2.addItem(vetCampos[1]);
                    cboPizza3.addItem(vetCampos[1]);
                    cboPizza4.addItem(vetCampos[1]);
                } else if (classe.equals("calzone")) {
                    cboCalzone.addItem(vetCampos[1]);
                } else if (classe.equals("bebida")) {
                    cboBebida.addItem(vetCampos[1]);
                } else if (classe.equals("borda")) {
                    cboBorda.addItem(vetCampos[1]);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
    }

    public void qntdSabor() {
        if (cboTamanho.getSelectedItem().toString().equals("Família")) {
            cboPizza3.setVisible(false);
            txtCod3.setVisible(false);
            cboPizza2.setVisible(false);
            txtCod2.setVisible(false);
            cboQtdSabor.removeAllItems();
            cboQtdSabor.addItem("1");
            cboQtdSabor.addItem("2");
            cboQtdSabor.addItem("3");
        } else if (cboTamanho.getSelectedItem().toString().equals("Gigante")) {
            cboPizza4.setVisible(false);
            cboPizza3.setVisible(false);
            txtCod3.setVisible(false);
            txtCod6.setVisible(false);
            cboPizza2.setVisible(false);
            txtCod2.setVisible(false);
            cboQtdSabor.removeAllItems();
            cboQtdSabor.addItem("1");
            cboQtdSabor.addItem("2");
            cboQtdSabor.addItem("3");
            cboQtdSabor.addItem("4");
        } else {
            cboPizza2.setVisible(false);
            txtCod2.setVisible(false);
            cboPizza4.setVisible(false);
            cboPizza3.setVisible(false);
            txtCod3.setVisible(false);
            txtCod6.setVisible(false);
            cboQtdSabor.removeAllItems();
            cboQtdSabor.addItem("1");
            cboQtdSabor.addItem("2");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlPrincipal = new javax.swing.JPanel();
        PnlCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPedido = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cboPagamento = new javax.swing.JComboBox<String>();
        lblTroco = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtTroco = new javax.swing.JFormattedTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cboTamanho = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        cboQtdSabor = new javax.swing.JComboBox<String>();
        txtCod1 = new javax.swing.JTextField();
        txtCod2 = new javax.swing.JTextField();
        txtCod3 = new javax.swing.JTextField();
        cboPizza1 = new javax.swing.JComboBox<String>();
        cboPizza2 = new javax.swing.JComboBox<String>();
        cboPizza3 = new javax.swing.JComboBox<String>();
        txtCod6 = new javax.swing.JTextField();
        cboPizza4 = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        cboBorda = new javax.swing.JComboBox<String>();
        txtQuantidade = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblUnitario = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtObs = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPizza = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtCod4 = new javax.swing.JTextField();
        cboBebida = new javax.swing.JComboBox<String>();
        txtQuantidade1 = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblUnitario1 = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        btnAlterar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBebida = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtCod5 = new javax.swing.JTextField();
        cboCalzone = new javax.swing.JComboBox<String>();
        txtQuantidade2 = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblUnitario2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblTotal2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTCalzone = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btnAdd3 = new javax.swing.JButton();
        btnExcluir3 = new javax.swing.JButton();
        btnAlterar3 = new javax.swing.JButton();
        PnlCalzone = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalPizza = new javax.swing.JLabel();
        lblTotalBebida = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTotalCalzone = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblTotalTotal = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JFormattedTextField();
        txtEntrega = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Fazendo Pedido");

        PnlPrincipal.setLayout(new java.awt.BorderLayout());

        PnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Cliente", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Endereco:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Bairro:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Cidade:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Pedido:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Data:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Telefone:");

        lblPedido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPedido.setText("jLabel28");

        lblData.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblData.setText("jLabel28");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel28.setText("Forma de Pagamento:");

        cboPagamento.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartão de Crédito", "Dinheiro" }));
        cboPagamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPagamentoFocusLost(evt);
            }
        });

        lblTroco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTroco.setText("Troco para:");

        txtBairro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTelefone.setEditable(true);
        txtTelefone.setForeground(new java.awt.Color(255, 0, 0));
        txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtTelefone.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelefoneFocusLost(evt);
            }
        });

        txtTroco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));

        javax.swing.GroupLayout PnlClienteLayout = new javax.swing.GroupLayout(PnlCliente);
        PnlCliente.setLayout(PnlClienteLayout);
        PnlClienteLayout.setHorizontalGroup(
            PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEndereco)
                    .addComponent(txtCliente))
                .addGap(151, 151, 151)
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefone))))
                .addGap(18, 18, 18)
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addComponent(lblTroco)
                        .addGap(18, 18, 18)
                        .addComponent(txtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28)
                    .addComponent(cboPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        PnlClienteLayout.setVerticalGroup(
            PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlClienteLayout.createSequentialGroup()
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlClienteLayout.createSequentialGroup()
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(lblPedido))
                                .addGap(20, 20, 20)
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(lblData)))
                        .addGap(18, 18, 18)
                        .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(lblTroco)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addGap(19, 19, 19)
                        .addComponent(cboPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PnlPrincipal.add(PnlCliente, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tamanho:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));

        cboTamanho.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboTamanho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grande", "Família", "Gigante", "Broto" }));
        cboTamanho.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboTamanhoFocusLost(evt);
            }
        });
        jPanel3.add(cboTamanho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Sabores:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        cboQtdSabor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboQtdSabor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        cboQtdSabor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboQtdSaborFocusLost(evt);
            }
        });
        jPanel3.add(cboQtdSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 50, -1));

        txtCod1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod1FocusLost(evt);
            }
        });
        jPanel3.add(txtCod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 30, -1));

        txtCod2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod2FocusLost(evt);
            }
        });
        jPanel3.add(txtCod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 30, -1));

        txtCod3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod3FocusLost(evt);
            }
        });
        jPanel3.add(txtCod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 30, -1));

        cboPizza1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboPizza1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPizza1FocusLost(evt);
            }
        });
        jPanel3.add(cboPizza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 260, -1));

        cboPizza2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboPizza2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPizza2FocusLost(evt);
            }
        });
        jPanel3.add(cboPizza2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 260, -1));

        cboPizza3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboPizza3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPizza3FocusLost(evt);
            }
        });
        jPanel3.add(cboPizza3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 260, -1));

        txtCod6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod6FocusLost(evt);
            }
        });
        jPanel3.add(txtCod6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 30, -1));

        cboPizza4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboPizza4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboPizza4FocusLost(evt);
            }
        });
        jPanel3.add(cboPizza4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 260, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Borda:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        cboBorda.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboBorda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sem" }));
        cboBorda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboBordaFocusLost(evt);
            }
        });
        jPanel3.add(cboBorda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 110, -1));

        txtQuantidade.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusLost(evt);
            }
        });
        ((JSpinner.DefaultEditor)txtQuantidade.getEditor()).getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                calcularValorTotal();
            }
        });
        jPanel3.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 60, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Quantidade:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Unitário:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, -1, -1));

        lblUnitario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblUnitario.setText("00.00");
        jPanel3.add(lblUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 60, -1));

        lblTotal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotal.setText("000.00");
        jPanel3.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 40, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Total:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        txtObs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtObs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObsFocusLost(evt);
            }
        });
        jPanel3.add(txtObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 280, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Observações:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, -1, -1));

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 30, 30, 30));

        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 90, 30, 30));

        btnExcluir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel3.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 150, 30, 30));

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 210, 30, 30));

        jTPizza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PIZZA", "TAMANHO", "BORDA", "QUANTIDADE", "UNITÁRIO", "TOTAL", "OBSERVAÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTPizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPizzaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTPizza);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1240, 200));

        jTabbedPane1.addTab("Pizza", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCod4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod4FocusLost(evt);
            }
        });
        jPanel4.add(txtCod4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 30, -1));

        cboBebida.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboBebida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboBebidaFocusLost(evt);
            }
        });
        jPanel4.add(cboBebida, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 260, -1));

        txtQuantidade1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel4.add(txtQuantidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 70, -1));
        ((JSpinner.DefaultEditor)txtQuantidade1.getEditor()).getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                calcularValorTotalBebida();
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Quantidade:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Unitário:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        lblUnitario1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblUnitario1.setText("00.00");
        jPanel4.add(lblUnitario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 40, -1));

        lblTotal1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotal1.setText("00.00");
        jPanel4.add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 40, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Total:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, 30, 30));

        btnAdd1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 90, 30, 30));

        btnExcluir1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 160, 30, 30));

        btnAlterar1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        btnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnAlterar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 220, 30, 30));

        jTBebida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "BEBIDA", "QUANTIDADE", "UNITÁRIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBebida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBebidaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTBebida);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1260, 250));

        jTabbedPane1.addTab("Bebida", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCod5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCod5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCod5FocusLost(evt);
            }
        });
        txtCod5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod5ActionPerformed(evt);
            }
        });
        jPanel5.add(txtCod5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 30, -1));

        cboCalzone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboCalzone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboCalzoneFocusLost(evt);
            }
        });
        jPanel5.add(cboCalzone, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 260, -1));

        txtQuantidade2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel5.add(txtQuantidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 60, -1));
        ((JSpinner.DefaultEditor)txtQuantidade2.getEditor()).getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent evt){
                calcularValorTotalCalzone();
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel25.setText("Quantidade:");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel29.setText("Unitário:");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        lblUnitario2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblUnitario2.setText("00.00");
        jPanel5.add(lblUnitario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 40, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("Total:");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        lblTotal2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotal2.setText("00.00");
        jPanel5.add(lblTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 40, -1));

        jTCalzone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "CALZONE", "QUANTIDADE", "UNITÁRIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane4.setViewportView(jTCalzone);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1260, 250));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpar.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 40, 30, 30));

        btnAdd3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAdd3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        btnAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd3ActionPerformed(evt);
            }
        });
        jPanel5.add(btnAdd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 100, 30, 30));

        btnExcluir3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnExcluir3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnExcluir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir3ActionPerformed(evt);
            }
        });
        jPanel5.add(btnExcluir3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 160, 30, 30));

        btnAlterar3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAlterar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        btnAlterar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar3ActionPerformed(evt);
            }
        });
        jPanel5.add(btnAlterar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 220, 30, 30));

        jTabbedPane1.addTab("Calzone", jPanel5);

        PnlPrincipal.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        PnlCalzone.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Calzone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N
        PnlCalzone.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Total de Pizzas:");
        PnlCalzone.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, -1));

        lblTotalPizza.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotalPizza.setText("000.00");
        PnlCalzone.add(lblTotalPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 40, -1));

        lblTotalBebida.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotalBebida.setText("000.00");
        PnlCalzone.add(lblTotalBebida, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 40, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Total de Bebidas:");
        PnlCalzone.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Total de Calzones:");
        PnlCalzone.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        lblTotalCalzone.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTotalCalzone.setText("000.00");
        PnlCalzone.add(lblTotalCalzone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 40, -1));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("Entrega:");
        PnlCalzone.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 70, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setText("Desconto:");
        PnlCalzone.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 70, -1));

        lblTotalTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTotalTotal.setText("000.00");
        PnlCalzone.add(lblTotalTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("TOTAL:");
        PnlCalzone.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        btnFinalizar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        PnlCalzone.add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 100, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        PnlCalzone.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 10));

        txtDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        txtDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescontoFocusLost(evt);
            }
        });
        PnlCalzone.add(txtDesconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 60, -1));

        txtEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        txtEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEntregaFocusLost(evt);
            }
        });
        PnlCalzone.add(txtEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 60, -1));

        PnlPrincipal.add(PnlCalzone, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboPagamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPagamentoFocusLost
        if (!cboPagamento.getSelectedItem().toString().equals("Dinheiro")) {
            txtTroco.disable();
        } else {
            txtTroco.enable();
        }
    }//GEN-LAST:event_cboPagamentoFocusLost

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        totalTotal();
        int resp = JOptionPane.showConfirmDialog(null, "Deseja mesmo finalizar o pedido?", "Finalização o pedido", WARNING_MESSAGE);
        if (resp == 0) {
            gerarPDF(lblPedido.getText());
            try {
                Desktop.getDesktop().print(new File("C:\\txt\\pedido\\" + lblPedido.getText() + ".pdf"));
                JOptionPane.showMessageDialog(null, "Pedido impresso!", "Pedido Finalizado", WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelefoneFocusLost
        String telefone = txtTelefone.getText();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\cliente.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            boolean saber = false;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[6].equals(telefone)) {
                    txtCliente.setText(vetCampos[0]);
                    txtEndereco.setText(vetCampos[2] + ", " + vetCampos[3]);
                    txtBairro.setText(vetCampos[4]);
                    txtCidade.setText(vetCampos[5]);
                    txtCliente.enable();
                    txtEndereco.enable();
                    txtBairro.enable();
                    txtCidade.enable();
                    saber = false;
                    break;
                } else {
                    saber = true;
                }
            }
            if (saber && !telefone.equals("")) {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado!", "Sem Cadastro", PLAIN_MESSAGE);
                txtCliente.enable();
                txtEndereco.enable();
                txtBairro.enable();
                txtCidade.enable();
            }
        } catch (IOException erro) {
            System.out.print("Erro ao consultar um cliente! \nErro: " + erro);
        }
    }//GEN-LAST:event_txtTelefoneFocusLost

    private void cboTamanhoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboTamanhoFocusLost
        calcularValor();
        calcularValorTotal();
        qntdSabor();
    }//GEN-LAST:event_cboTamanhoFocusLost

    private void txtCod1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod1FocusLost
        String cod = txtCod1.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboPizza1.setSelectedItem(vetCampos[1]);
                    verificar = true;
                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(txtCod1.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Pizza não Encontrada!", "Erro!", PLAIN_MESSAGE);
                txtCod1.setText("");
                cboPizza1.setSelectedIndex(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtCod1FocusLost

    private void txtCod2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod2FocusLost
        String cod = txtCod2.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboPizza2.setSelectedItem(vetCampos[1]);
                    verificar = true;

                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(txtCod2.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Pizza não Encontrada!", "Erro!", PLAIN_MESSAGE);
                txtCod2.setText("");
                cboPizza2.setSelectedIndex(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtCod2FocusLost

    private void txtCod3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod3FocusLost
        String cod = txtCod3.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboPizza3.setSelectedItem(vetCampos[1]);
                    verificar = true;

                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(txtCod3.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Pizza não Encontrada!", "Erro!", PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
            txtCod3.setText("");
            cboPizza3.setSelectedIndex(1);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtCod3FocusLost

    private void cboPizza1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPizza1FocusLost
        String cod = cboPizza1.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod1.setText(vetCampos[0]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboPizza1FocusLost

    private void cboPizza2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPizza2FocusLost
        String cod = cboPizza2.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod2.setText(vetCampos[0]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboPizza2FocusLost

    private void cboPizza3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPizza3FocusLost
        String cod = cboPizza3.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod3.setText(vetCampos[0]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboPizza3FocusLost

    private void txtCod6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod6FocusLost
        String cod = txtCod6.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboPizza4.setSelectedItem(vetCampos[1]);
                    verificar = true;

                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(txtCod6.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Pizza não Encontrada!", "Erro!", PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
            txtCod6.setText("");
            cboPizza4.setSelectedIndex(1);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtCod6FocusLost

    private void cboPizza4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboPizza4FocusLost
        String cod = cboPizza4.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\pizza.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod6.setText(vetCampos[0]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboPizza4FocusLost

    private void cboBordaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboBordaFocusLost
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboBordaFocusLost

    private void txtQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusLost
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtQuantidadeFocusLost

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparPizza();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtQuantidade.getValue().equals("0")) {
            JOptionPane.showMessageDialog(null, "Selecione a quantidade de pizzas!", "Preencha os campos necessários!", PLAIN_MESSAGE);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) jTPizza.getModel();
            String sabor;
            String cod;
            if (cboQtdSabor.getSelectedItem() == "1") {
                cod = txtCod1.getText();
                sabor = cboPizza1.getSelectedItem().toString();
            } else if (cboQtdSabor.getSelectedItem() == "2") {
                cod = txtCod1.getText() + "/" + txtCod2.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString();
            } else if (cboQtdSabor.getSelectedItem() == "3") {
                cod = txtCod1.getText() + "/" + txtCod2.getText() + "/" + txtCod3.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString() + "/" + cboPizza3.getSelectedItem().toString();
            } else {
                cod = txtCod1.getText() + "/" + txtCod2.getText() + "/" + txtCod3.getText() + "/" + txtCod6.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString() + "/" + cboPizza3.getSelectedItem().toString() + "/" + cboPizza4.getSelectedItem().toString();
            }
            modelo.addRow(new Object[]{
                cod,
                sabor,
                cboTamanho.getSelectedItem().toString(),
                cboBorda.getSelectedItem().toString(),
                txtQuantidade.getValue(),
                lblUnitario.getText(),
                lblTotal.getText(),
                txtObs.getText()
            });
            limparPizza();
            lblTotalPizza.setText(trocaVirgula(totalPizza()));
            totalTotal();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTPizza.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTPizza.getModel();
            model.removeRow(jTPizza.getSelectedRow());
            limparPizza();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
        lblTotalPizza.setText(trocaVirgula(totalPizza()));
        totalTotal();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTPizza.getSelectedRow() != -1) {
            String sabor;
            String cod;
            if (cboQtdSabor.getSelectedItem() == "1") {
                cod = txtCod1.getText();
                sabor = cboPizza1.getSelectedItem().toString();
            } else if (cboQtdSabor.getSelectedItem() == "2") {
                cod = txtCod1.getText() + "/" + txtCod2.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString();
            } else if (cboQtdSabor.getSelectedItem() == "3") {
                cod = txtCod1.getText() + "/" + txtCod2.getText() + "/" + txtCod3.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString() + "/" + cboPizza3.getSelectedItem().toString();
            } else {
                cod = txtCod1.getText() + "/" + txtCod2.getText() + "/" + txtCod3.getText() + "/" + txtCod6.getText();
                sabor = cboPizza1.getSelectedItem().toString() + "/" + cboPizza2.getSelectedItem().toString() + "/" + cboPizza3.getSelectedItem().toString() + "/" + cboPizza4.getSelectedItem().toString();
            }
            AbstractTableModel modelo = (AbstractTableModel) jTPizza.getModel();
            modelo.setValueAt(cod, jTPizza.getSelectedRow(), 0);
            modelo.setValueAt(sabor, jTPizza.getSelectedRow(), 1);
            modelo.setValueAt(cboTamanho.getSelectedItem().toString(), jTPizza.getSelectedRow(), 2);
            modelo.setValueAt(cboBorda.getSelectedItem().toString(), jTPizza.getSelectedRow(), 3);
            modelo.setValueAt(txtQuantidade.getValue(), jTPizza.getSelectedRow(), 4);
            modelo.setValueAt(lblUnitario.getText(), jTPizza.getSelectedRow(), 5);
            modelo.setValueAt(lblTotal.getText(), jTPizza.getSelectedRow(), 6);
            modelo.setValueAt(txtObs.getText(), jTPizza.getSelectedRow(), 7);
        }
        lblTotalPizza.setText(trocaVirgula(totalPizza()));
        totalTotal();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void jTPizzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPizzaMouseClicked
        if (jTPizza.getSelectedRow() != -1) {
            cboTamanho.setSelectedItem(jTPizza.getValueAt(jTPizza.getSelectedRow(), 2).toString());
            qntdSabor();
            String cod = jTPizza.getValueAt(jTPizza.getSelectedRow(), 0).toString();
            if (cod.contains("/")) {
                String[] vetCod = cod.split("/");
                txtCod1.setText(vetCod[0]);
                txtCod2.setText(vetCod[1]);
                cboQtdSabor.setSelectedIndex(1);
                if (vetCod[2] != null) {
                    txtCod3.setText(vetCod[2]);
                    cboQtdSabor.setSelectedIndex(2);
                }
                if (vetCod[3] != null) {
                    txtCod6.setText(vetCod[3]);
                    cboQtdSabor.setSelectedIndex(3);
                }
            } else {
                txtCod1.setText(cod);
                txtCod2.setText("");
                txtCod3.setText("");
                txtCod6.setText("");
                cboQtdSabor.setSelectedIndex(0);
            }
            String pizza = jTPizza.getValueAt(jTPizza.getSelectedRow(), 1).toString();
            if (pizza.contains("/")) {
                String[] vetCod = pizza.split("/");
                cboPizza1.setSelectedItem(vetCod[0]);
                cboPizza2.setSelectedItem(vetCod[1]);
                if (vetCod[2] != null) {
                    cboPizza3.setSelectedItem(vetCod[2]);
                }
                if (vetCod[3] != null) {
                    cboPizza4.setSelectedItem(vetCod[3]);
                }
            } else {
                cboPizza1.setSelectedItem(cod);
                cboPizza2.setSelectedIndex(0);
                cboPizza3.setSelectedIndex(0);
                cboPizza4.setSelectedIndex(0);
            }
            cboBorda.setSelectedItem(jTPizza.getValueAt(jTPizza.getSelectedRow(), 3).toString());
            int valor = (int) jTPizza.getValueAt(jTPizza.getSelectedRow(), 4);
            txtQuantidade.setValue(valor);
            txtObs.setText(jTPizza.getValueAt(jTPizza.getSelectedRow(), 7).toString());
            calcularValor();
            calcularValorTotal();
        }
    }//GEN-LAST:event_jTPizzaMouseClicked

    private void txtCod4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod4FocusLost
        String cod = txtCod4.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\bebida.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboBebida.setSelectedItem(vetCampos[1]);
                    verificar = true;
                    lblUnitario1.setText(vetCampos[2]);
                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(cod.equals(""))) {
                JOptionPane.showMessageDialog(null, "Bebida não Encontrada!", "Erro!", PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValorTotalBebida();
    }//GEN-LAST:event_txtCod4FocusLost

    private void cboBebidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboBebidaFocusLost
        String cod = cboBebida.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\bebida.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod4.setText(vetCampos[0]);
                    lblUnitario1.setText(vetCampos[2]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValorTotalBebida();
    }//GEN-LAST:event_cboBebidaFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limparBebida();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) jTBebida.getModel();
        modelo.addRow(new Object[]{
            txtCod4.getText(),
            cboBebida.getSelectedItem().toString(),
            txtQuantidade1.getValue(),
            lblUnitario1.getText(),
            lblTotal1.getText()
        });
        limparBebida();
        lblTotalBebida.setText(trocaVirgula(totalBebida()));
        totalTotal();
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        if (jTBebida.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTBebida.getModel();
            model.removeRow(jTBebida.getSelectedRow());
            limparBebida();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
        lblTotalBebida.setText(trocaVirgula(totalBebida()));
        totalTotal();
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void btnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar1ActionPerformed
        if (jTBebida.getSelectedRow() != -1) {
            AbstractTableModel modelo = (AbstractTableModel) jTBebida.getModel();
            modelo.setValueAt(txtCod4.getText(), jTBebida.getSelectedRow(), 0);
            modelo.setValueAt(cboBebida.getSelectedItem().toString(), jTBebida.getSelectedRow(), 1);
            modelo.setValueAt(txtQuantidade1.getValue(), jTBebida.getSelectedRow(), 2);
            modelo.setValueAt(lblUnitario1.getText(), jTBebida.getSelectedRow(), 3);
            modelo.setValueAt(lblTotal1.getText(), jTBebida.getSelectedRow(), 4);
            limparBebida();
        }
        lblTotalBebida.setText(trocaVirgula(totalBebida()));
        totalTotal();
    }//GEN-LAST:event_btnAlterar1ActionPerformed

    private void jTBebidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBebidaMouseClicked
        if (jTBebida.getSelectedRow() != -1) {
            txtCod4.setText(jTBebida.getValueAt(jTBebida.getSelectedRow(), 0).toString());
            cboBebida.setSelectedItem(jTBebida.getValueAt(jTBebida.getSelectedRow(), 1).toString());
            int valor = (int) jTBebida.getValueAt(jTBebida.getSelectedRow(), 2);
            txtQuantidade1.setValue(valor);
            try {
                FileReader arquivo = new FileReader("c:\\txt\\bebida.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if (vetCampos[1].equals(cboBebida.getSelectedItem().toString())) {
                        lblUnitario1.setText(vetCampos[2]);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Deu Erro");
            }
            calcularValorTotalBebida();
        }
    }//GEN-LAST:event_jTBebidaMouseClicked

    private void txtCod5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCod5FocusLost
        String cod = txtCod5.getText();
        boolean verificar = true;
        try {
            FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[0].equals(cod)) {
                    cboCalzone.setSelectedItem(vetCampos[1]);
                    verificar = true;
                    lblUnitario2.setText(vetCampos[2]);
                } else {
                    verificar = false;
                }
                if (verificar) {
                    break;
                }
            }
            if (verificar == false && !(cod.equals(""))) {
                JOptionPane.showMessageDialog(null, "Calzone não Encontrado!", "Erro!", PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValorTotalCalzone();
    }//GEN-LAST:event_txtCod5FocusLost

    private void txtCod5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod5ActionPerformed

    private void cboCalzoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboCalzoneFocusLost
        String cod = cboCalzone.getSelectedItem().toString();
        try {
            FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (vetCampos[1].equals(cod)) {
                    txtCod5.setText(vetCampos[0]);
                    lblUnitario2.setText(vetCampos[2]);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher o campo com o codigo! \nERRO: " + ex, "ERRO", ERROR_MESSAGE);
        }
        calcularValorTotalCalzone();
    }//GEN-LAST:event_cboCalzoneFocusLost

    private void jTCalzoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCalzoneMouseClicked
        if (jTCalzone.getSelectedRow() != -1) {
            txtCod5.setText(jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 0).toString());
            cboCalzone.setSelectedItem(jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 1).toString());
            int valor = (int) jTCalzone.getValueAt(jTCalzone.getSelectedRow(), 2);
            txtQuantidade2.setValue(valor);
            try {
                FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
                BufferedReader registro = new BufferedReader(arquivo);
                String reg;
                while ((reg = registro.readLine()) != null) {
                    String vetCampos[] = reg.split(";");
                    if (vetCampos[1].equals(cboCalzone.getSelectedItem().toString())) {
                        lblUnitario2.setText(vetCampos[2]);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Deu Erro");
            }
            calcularValorTotalCalzone();
        }
    }//GEN-LAST:event_jTCalzoneMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limparCalzone();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) jTCalzone.getModel();
        modelo.addRow(new Object[]{
            txtCod5.getText(),
            cboCalzone.getSelectedItem().toString(),
            txtQuantidade2.getValue(),
            lblUnitario2.getText(),
            lblTotal2.getText()
        });
        limparCalzone();
        lblTotalCalzone.setText(trocaVirgula(totalCalzone()));
        totalTotal();
    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void btnExcluir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir3ActionPerformed
        if (jTCalzone.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) jTCalzone.getModel();
            model.removeRow(jTCalzone.getSelectedRow());
            limparCalzone();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione algum registro na tabela!", "Atenção!", WARNING_MESSAGE);
        }
        lblTotalCalzone.setText(trocaVirgula(totalCalzone()));
        totalTotal();
    }//GEN-LAST:event_btnExcluir3ActionPerformed

    private void btnAlterar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar3ActionPerformed
        if (jTCalzone.getSelectedRow() != -1) {
            AbstractTableModel modelo = (AbstractTableModel) jTCalzone.getModel();
            modelo.setValueAt(txtCod5.getText(), jTCalzone.getSelectedRow(), 0);
            modelo.setValueAt(cboCalzone.getSelectedItem().toString(), jTCalzone.getSelectedRow(), 1);
            modelo.setValueAt(txtQuantidade2.getValue(), jTCalzone.getSelectedRow(), 2);
            modelo.setValueAt(lblUnitario2.getText(), jTCalzone.getSelectedRow(), 3);
            modelo.setValueAt(lblTotal2.getText(), jTCalzone.getSelectedRow(), 4);
            limparCalzone();
        }
        lblTotalCalzone.setText(trocaVirgula(totalCalzone()));
        totalTotal();
    }//GEN-LAST:event_btnAlterar3ActionPerformed

    private void cboQtdSaborFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboQtdSaborFocusLost
        String qtdSabor = cboQtdSabor.getSelectedItem().toString();
        if (qtdSabor.equals("1")) {
            txtCod1.setVisible(true);
            cboPizza1.setVisible(true);
            txtCod2.setVisible(false);
            cboPizza2.setVisible(false);
            txtCod3.setVisible(false);
            cboPizza3.setVisible(false);
            txtCod6.setVisible(false);
            cboPizza4.setVisible(false);
        } else if (qtdSabor.equals("2")) {
            txtCod1.setVisible(true);
            cboPizza1.setVisible(true);
            txtCod2.setVisible(true);
            cboPizza2.setVisible(true);
            txtCod3.setVisible(false);
            cboPizza3.setVisible(false);
            txtCod6.setVisible(false);
            cboPizza4.setVisible(false);
        } else if (qtdSabor.equals("3")) {
            txtCod1.setVisible(true);
            cboPizza1.setVisible(true);
            txtCod2.setVisible(true);
            cboPizza2.setVisible(true);
            txtCod3.setVisible(true);
            cboPizza3.setVisible(true);
            txtCod6.setVisible(false);
            cboPizza4.setVisible(false);
        } else {
            txtCod1.setVisible(true);
            cboPizza1.setVisible(true);
            txtCod2.setVisible(true);
            cboPizza2.setVisible(true);
            txtCod3.setVisible(true);
            cboPizza3.setVisible(true);
            txtCod6.setVisible(true);
            cboPizza4.setVisible(true);
        }
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_cboQtdSaborFocusLost

    private void txtObsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObsFocusLost
        calcularValor();
        calcularValorTotal();
    }//GEN-LAST:event_txtObsFocusLost

    private void txtEntregaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEntregaFocusLost
        totalTotal();
    }//GEN-LAST:event_txtEntregaFocusLost

    private void txtDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescontoFocusLost
        totalTotal();
    }//GEN-LAST:event_txtDescontoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlCalzone;
    private javax.swing.JPanel PnlCliente;
    private javax.swing.JPanel PnlPrincipal;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd3;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnAlterar3;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnExcluir3;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cboBebida;
    private javax.swing.JComboBox<String> cboBorda;
    private javax.swing.JComboBox<String> cboCalzone;
    private javax.swing.JComboBox<String> cboPagamento;
    private javax.swing.JComboBox<String> cboPizza1;
    private javax.swing.JComboBox<String> cboPizza2;
    private javax.swing.JComboBox<String> cboPizza3;
    private javax.swing.JComboBox<String> cboPizza4;
    private javax.swing.JComboBox<String> cboQtdSabor;
    private javax.swing.JComboBox<String> cboTamanho;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTBebida;
    private javax.swing.JTable jTCalzone;
    private javax.swing.JTable jTPizza;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblPedido;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblTotal2;
    private javax.swing.JLabel lblTotalBebida;
    private javax.swing.JLabel lblTotalCalzone;
    private javax.swing.JLabel lblTotalPizza;
    private javax.swing.JLabel lblTotalTotal;
    private javax.swing.JLabel lblTroco;
    private javax.swing.JLabel lblUnitario;
    private javax.swing.JLabel lblUnitario1;
    private javax.swing.JLabel lblUnitario2;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCod1;
    private javax.swing.JTextField txtCod2;
    private javax.swing.JTextField txtCod3;
    private javax.swing.JTextField txtCod4;
    private javax.swing.JTextField txtCod5;
    private javax.swing.JTextField txtCod6;
    private javax.swing.JFormattedTextField txtDesconto;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JFormattedTextField txtEntrega;
    private javax.swing.JTextField txtObs;
    private javax.swing.JSpinner txtQuantidade;
    private javax.swing.JSpinner txtQuantidade1;
    private javax.swing.JSpinner txtQuantidade2;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JFormattedTextField txtTroco;
    // End of variables declaration//GEN-END:variables
}
