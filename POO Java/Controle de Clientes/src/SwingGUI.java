import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public void iniciar() {
        frame = new JFrame("Sistema de Cadastro e Controle de Cobrança");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(criarTelaResponsavel(), "TelaResponsavel");
        cardPanel.add(criarTelaCrianca(), "TelaCrianca");
        cardPanel.add(criarTelaEstadia(), "TelaEstadia");

        frame.add(cardPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cardLayout.show(cardPanel, "TelaResponsavel");
    }

    private JPanel criarTelaResponsavel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Etapa 1 de 3 - Dados do Responsável", SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(8, 2));
        JTextField nomeField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField enderecoField = new JTextField(20);
        JTextField idadeField = new JTextField(20);
        JButton nextButton = new JButton("Avançar");

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(cpfField);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(telefoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Endereço:"));
        formPanel.add(enderecoField);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(idadeField);
        formPanel.add(new JLabel());
        formPanel.add(nextButton);

        panel.add(formPanel, BorderLayout.CENTER);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    String cpf = cpfField.getText();
                    String telefone = telefoneField.getText();
                    String email = emailField.getText();
                    String endereco = enderecoField.getText();
                    int idade = Integer.parseInt(idadeField.getText());

                    Responsavel responsavel = new Responsavel(nome, cpf, telefone, email, endereco, idade);
                    Funcoes.setResponsavel(responsavel);
                    cardLayout.show(cardPanel, "TelaCrianca");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar responsável: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel criarTelaCrianca() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Etapa 2 de 3 - Dados da Criança", SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField nomeField = new JTextField(20);
        JTextField idadeField = new JTextField(20);
        JTextField sexoField = new JTextField(20);
        JButton nextButton = new JButton("Avançar");

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(idadeField);
        formPanel.add(new JLabel("Sexo:"));
        formPanel.add(sexoField);
        formPanel.add(new JLabel());
        formPanel.add(nextButton);

        panel.add(formPanel, BorderLayout.CENTER);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText();
                    int idade = Integer.parseInt(idadeField.getText());
                    String sexo = sexoField.getText();

                    Crianca crianca = new Crianca(Funcoes.getResponsavel(), nome, idade, sexo);
                    Funcoes.setCrianca(crianca);
                    cardLayout.show(cardPanel, "TelaEstadia");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar criança: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel criarTelaEstadia() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Etapa 3 de 3 - Dados da Estadia", SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField tempoUtilizadoField = new JTextField(20);
        JButton calcularButton = new JButton("Calcular e Mostrar Resumo");

        formPanel.add(new JLabel("Tempo Utilizado (minutos):"));
        formPanel.add(tempoUtilizadoField);
        formPanel.add(calcularButton);
        formPanel.add(new JLabel()); // Adicione um espaço para manter o layout

        panel.add(formPanel, BorderLayout.CENTER);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tempoUtilizado = Integer.parseInt(tempoUtilizadoField.getText());
                    double valorTotal = Funcoes.calcularValor(tempoUtilizado);
                    Responsavel responsavel = Funcoes.getResponsavel();
                    Crianca crianca = Funcoes.getCrianca();

                    String mensagem = String.format(
                            "<html><b>Dados da Estadia</b><br>" +
                                    "Nome Responsável: %s<br>" +
                                    "CPF Responsável: %s<br>" +
                                    "Telefone Responsável: %s<br>" +
                                    "Email Responsável: %s<br>" +
                                    "Endereço Responsável: %s<br>" +
                                    "Idade Responsável: %d<br>" +
                                    "Nome Criança: %s<br>" +
                                    "Idade Criança: %d<br>" +
                                    "Sexo Criança: %s<br>" +
                                    "Tempo no Brinquedo: %d minutos<br>" +
                                    "Valor a Pagar: R$ %.2f</html>",
                            responsavel.getNome(), responsavel.getCpf(), responsavel.getTelefone(), responsavel.getEmail(),
                            responsavel.getEndereco(), responsavel.getIdade(),
                            crianca.getNome(), crianca.getIdade(), crianca.getSexo(),
                            tempoUtilizado, valorTotal
                    );

                    JOptionPane.showMessageDialog(frame, mensagem, "Resumo da Estadia", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao calcular estadia: " + ex.getMessage());
                }
            }
        });

        return panel;
    }
}