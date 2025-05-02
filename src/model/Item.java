package model;

import javafx.beans.property.*;

public class Item {
    private final SimpleStringProperty nome;
    private final SimpleStringProperty modelo;
    private final SimpleStringProperty unidade;
    private final SimpleIntegerProperty quantidade;
    private final SimpleDoubleProperty valor; // unitário

    public Item(String nome, String modelo, String unidade, int quantidade, double valor) {
        this.nome = new SimpleStringProperty(nome);
        this.modelo = new SimpleStringProperty(modelo);
        this.unidade = new SimpleStringProperty(unidade);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.valor = new SimpleDoubleProperty(valor);
    }

    // nome
    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }
    public StringProperty nomeProperty() { return nome; }

    // modelo
    public String getModelo() { return modelo.get(); }
    public void setModelo(String modelo) { this.modelo.set(modelo); }
    public StringProperty modeloProperty() { return modelo; }

    // unidade
    public String getUnidade() { return unidade.get(); }
    public void setUnidade(String unidade) { this.unidade.set(unidade); }
    public StringProperty unidadeProperty() { return unidade; }

    // quantidade
    public int getQuantidade() { return quantidade.get(); }
    public void setQuantidade(int quantidade) { this.quantidade.set(quantidade); }
    public IntegerProperty quantidadeProperty() { return quantidade; }

    // valor
    public double getValor() { return valor.get(); }
    public void setValor(double valor) { this.valor.set(valor); }
    public DoubleProperty valorProperty() { return valor; }

    public double getValorTotal() {
        return getQuantidade() * getValor();
    }
}