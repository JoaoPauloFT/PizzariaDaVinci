package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Calzone {

    private String numero;
    private String nome;
    private String preco;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void cadastrar() {
        try {
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\calzone.txt", true);
            PrintWriter registro = new PrintWriter(arquivoCliente);
            registro.println(numero + ";" + nome + ";" + preco + ";");
            arquivoCliente.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", INFORMATION_MESSAGE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o calzone! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
        }
    }

    public void excluir(String NUMpesquisa) {
        try {
            FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
            PrintWriter registroCliente = new PrintWriter(arquivoCliente);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (!vetCampos[0].equals(NUMpesquisa)) {
                    registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";");
                }
            }
            registroCliente.close();
            arquivoCliente.close();
            registro.close();
            arquivo.close();

            FileReader arquivo1 = new FileReader("c:\\txt\\arqTemp.txt");
            BufferedReader registro1 = new BufferedReader(arquivo1);
            FileWriter arquivoCliente1 = new FileWriter("c:\\txt\\calzone.txt", false);
            PrintWriter registroCliente1 = new PrintWriter(arquivoCliente1);
            String reg1;
            while ((reg1 = registro1.readLine()) != null) {
                String vetCampos[] = reg1.split(";");
                registroCliente1.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";");
            }
            registroCliente1.close();
            arquivoCliente1.close();
            registro1.close();
            arquivo1.close();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Sucesso!", INFORMATION_MESSAGE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
        }
    }

    public void alterar(String NUMpesquisa) {
        try {
            FileReader arquivo = new FileReader("c:\\txt\\calzone.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
            PrintWriter registroCliente = new PrintWriter(arquivoCliente);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (!vetCampos[0].equals(NUMpesquisa)) {
                    registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";");
                } else {
                    registroCliente.println(numero + ";" + nome + ";" + preco + ";");
                }
            }
            registroCliente.close();
            arquivoCliente.close();
            registro.close();
            arquivo.close();

            FileReader arquivo1 = new FileReader("c:\\txt\\arqTemp.txt");
            BufferedReader registro1 = new BufferedReader(arquivo1);
            FileWriter arquivoCliente1 = new FileWriter("c:\\txt\\calzone.txt", false);
            PrintWriter registroCliente1 = new PrintWriter(arquivoCliente1);
            String reg1;
            while ((reg1 = registro1.readLine()) != null) {
                String vetCampos[] = reg1.split(";");
                registroCliente1.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";");
            }
            registroCliente1.close();
            arquivoCliente1.close();
            registro1.close();
            arquivo1.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso!", INFORMATION_MESSAGE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o arquivo! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
        }
    }
}
