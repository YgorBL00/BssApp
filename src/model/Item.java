package model;

public class Item {
    private final String nome;
    private final String modelo;
    private final int quantidade;
    private final double valor;
    private final String outros; // NOVO!


    public Item(String nome, String modelo, int quantidade, double valor, String outros) {
        this.nome = nome;
        this.modelo = modelo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.outros = outros;
    }

    public String getNome() { return nome; }
    public String getModelo() { return modelo; }
    public int getQuantidade() { return quantidade; }
    public double getValor() { return valor; }
    public String getOutros() { return outros; }

}