package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Cliente {

    private String nome;
    private String CPF;
    private String endereco;
    private String numero;
    private String bairro;
    private String telefone;
    private String cidade;
    private String complemento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void cadastrar() {
        try {
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\cliente.txt", true);
            PrintWriter registro = new PrintWriter(arquivoCliente);
            registro.println(nome + ";" + CPF + ";" + endereco + ";" + complemento + ";"
                    + bairro + ";" + cidade + ";" + telefone + ";");
            arquivoCliente.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso!", INFORMATION_MESSAGE);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente! \nErro: " + erro, "Erro!", ERROR_MESSAGE);
        }
    }

    public void excluir(String CPFpesquisa) {
        try {
            FileReader arquivo = new FileReader("c:\\txt\\cliente.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
            PrintWriter registroCliente = new PrintWriter(arquivoCliente);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (!vetCampos[6].equals(CPFpesquisa)) {
                    registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";" + vetCampos[3] + ";" + vetCampos[4] + ";"
                            + vetCampos[5] + ";" + vetCampos[6] + ";");
                }
            }
            registroCliente.close();
            arquivoCliente.close();
            registro.close();
            arquivo.close();

            FileReader arquivo1 = new FileReader("c:\\txt\\arqTemp.txt");
            BufferedReader registro1 = new BufferedReader(arquivo1);
            FileWriter arquivoCliente1 = new FileWriter("c:\\txt\\cliente.txt", false);
            PrintWriter registroCliente1 = new PrintWriter(arquivoCliente1);
            String reg1;
            while ((reg1 = registro1.readLine()) != null) {
                String vetCampos[] = reg1.split(";");
                registroCliente1.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";" + vetCampos[3] + ";" + vetCampos[4] + ";"
                        + vetCampos[5] + ";" + vetCampos[6] + ";");
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

    public void alterar(String CPFpesquisa) {
        try {
            FileReader arquivo = new FileReader("c:\\txt\\cliente.txt");
            BufferedReader registro = new BufferedReader(arquivo);
            FileWriter arquivoCliente = new FileWriter("c:\\txt\\arqTemp.txt", false);
            PrintWriter registroCliente = new PrintWriter(arquivoCliente);
            String reg;
            while ((reg = registro.readLine()) != null) {
                String vetCampos[] = reg.split(";");
                if (!vetCampos[6].equals(CPFpesquisa)) {
                    registroCliente.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";" + vetCampos[3] + ";" + vetCampos[4] + ";"
                            + vetCampos[5] + ";" + vetCampos[6] + ";");
                } else {
                    registroCliente.println(nome + ";" + CPF + ";" + endereco + ";" + complemento + ";"
                            + bairro + ";" + cidade + ";" + telefone + ";");
                }
            }
            registroCliente.close();
            arquivoCliente.close();
            registro.close();
            arquivo.close();

            FileReader arquivo1 = new FileReader("c:\\txt\\arqTemp.txt");
            BufferedReader registro1 = new BufferedReader(arquivo1);
            FileWriter arquivoCliente1 = new FileWriter("c:\\txt\\cliente.txt", false);
            PrintWriter registroCliente1 = new PrintWriter(arquivoCliente1);
            String reg1;
            while ((reg1 = registro1.readLine()) != null) {
                String vetCampos[] = reg1.split(";");
                registroCliente1.println(vetCampos[0] + ";" + vetCampos[1] + ";" + vetCampos[2] + ";" + vetCampos[3] + ";" + vetCampos[4] + ";"
                        + vetCampos[5] + ";" + vetCampos[6] + ";");
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
