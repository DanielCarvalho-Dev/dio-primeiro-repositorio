class Responsavel {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private int idade;

    public Responsavel(String nome, String cpf, String telefone, String email, String endereco, int idade) {
        if (idade < 18) {
            throw new IllegalArgumentException("O responsável deve ser maior de idade.");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.idade = idade;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public int getIdade() { return idade; }
}

class Crianca {
    private Responsavel responsavel;
    private String nome;
    private int idade;
    private String sexo;

    public Crianca(Responsavel responsavel, String nome, int idade, String sexo) {
        if (idade > 10) {
            throw new IllegalArgumentException("A criança deve ter 10 anos ou menos.");
        }
        this.responsavel = responsavel;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    // Getters
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }
}

class Estadia {
    private Responsavel responsavel;
    private Crianca crianca;
    private int tempoUtilizado; // em minutos

    public Estadia(Responsavel responsavel, Crianca crianca, int tempoUtilizado) {
        this.responsavel = responsavel;
        this.crianca = crianca;
        this.tempoUtilizado = tempoUtilizado;
    }

    public double calcularValor() {
        double valorPorMinuto = 1.50;
        double valorTotal = tempoUtilizado * valorPorMinuto;

        if (tempoUtilizado > 60) {
            valorTotal *= 0.85; // 15% de desconto
        } else if (tempoUtilizado > 40) {
            valorTotal *= 0.90; // 10% de desconto
        } else if (tempoUtilizado > 20) {
            valorTotal *= 0.95; // 5% de desconto
        }

        return valorTotal;
    }

    // Getters
    public Responsavel getResponsavel() { return responsavel; }
    public Crianca getCrianca() { return crianca; }
    public int getTempoUtilizado() { return tempoUtilizado; }
}

public class Funcoes {
    private static Responsavel responsavel;
    private static Crianca crianca;

    public static void setResponsavel(Responsavel r) {
        responsavel = r;
    }

    public static Responsavel getResponsavel() {
        return responsavel;
    }

    public static void setCrianca(Crianca c) {
        crianca = c;
    }

    public static Crianca getCrianca() {
        return crianca;
    }

    public static double calcularValor(int tempoUtilizado) {
        double valorPorMinuto = 1.50;
        double valorTotal = tempoUtilizado * valorPorMinuto;

        if (tempoUtilizado > 60) {
            valorTotal *= 0.85; // 15% de desconto
        } else if (tempoUtilizado > 40) {
            valorTotal *= 0.90; // 10% de desconto
        } else if (tempoUtilizado > 20) {
            valorTotal *= 0.95; // 5% de desconto
        }

        return valorTotal;
    }
}