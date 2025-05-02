package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DadosCâmara;
import model.Evaporadoras;
import model.Item;
import model.UnidadeCondensadoras;

public class PainelMaterialFX extends SplitPane {

    // === CAMPOS ===
    private boolean listaVisivel = true;
    private TableView<Item> tabela;
    private Label lblCusto;
    private Label lblSugerido;

    // === CONSTRUTOR ===
    public PainelMaterialFX() {
        // --- Tabela de materiais ---
        configurarTabelaMateriais();

        // --- Painel lateral (Custo, Venda) ---
        VBox painelLateral = criarPainelLateral();

        // --- Painel principal (Tabela + botão "Valores") ---
        VBox painelTabela = criarPainelTabela();

        // --- Montagem do SplitPane ---
        this.getItems().addAll(painelTabela, painelLateral);
        this.setDividerPositions(0.7);
    }

    // === MÉTODOS DE CONSTRUÇÃO DE UI ===

    private void configurarTabelaMateriais() {
        tabela = new TableView<>();

        TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Item, String> colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaModelo.setPrefWidth(130);

        TableColumn<Item, Integer> colunaQuantidade = new TableColumn<>("Quantidade");
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        TableColumn<Item, Double> colunaValor = new TableColumn<>("Valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setCellFactory(col -> new TableCell<Item, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(String.format("R$ %.2f", valor));
                }
            }
        });

        tabela.getColumns().addAll(colunaNome, colunaModelo, colunaQuantidade, colunaValor);
    }

    private VBox criarPainelTabela() {
        // Barra de título com botão de esconder/exibir lista
        Label titulo = new Label("Lista de materiais");
        titulo.setFont(Font.font("System", FontWeight.BOLD, 14));
        Button btnToggle = new Button("▲");
        btnToggle.setOnAction(e -> {
            listaVisivel = !listaVisivel;
            tabela.setVisible(listaVisivel);
            tabela.setManaged(listaVisivel);
            btnToggle.setText(listaVisivel ? "▲" : "▼");
        });

        HBox barraTopo = new HBox(titulo, new Region(), btnToggle);
        HBox.setHgrow(barraTopo.getChildren().get(1), Priority.ALWAYS);
        barraTopo.setAlignment(Pos.CENTER_LEFT);
        barraTopo.setPadding(new Insets(5,10,5,10));
        barraTopo.setSpacing(10);
        barraTopo.setStyle("-fx-background-color: #eeeeee; -fx-border-radius: 8 8 0 0; -fx-background-radius: 8 8 0 0;");

        ScrollPane scrollPane = new ScrollPane(tabela);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        // Rodapé com botão "Valores"
        Button btnValores = new Button("Valores");
        btnValores.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnValores.setPadding(new Insets(6,12,6,12));
        btnValores.setOnAction(e -> abrirJanelaItens1());

        HBox rodape = new HBox(new Region(), btnValores);
        HBox.setHgrow(rodape.getChildren().get(0), Priority.ALWAYS);
        rodape.setPadding(new Insets(0,0,0,0));

        VBox painelTabela = new VBox(barraTopo, scrollPane, rodape);
        painelTabela.setPadding(new Insets(10,10,5,10));
        painelTabela.setSpacing(10);
        painelTabela.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #cccccc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        return painelTabela;
    }

    private VBox criarPainelLateral() {
        VBox painelLateral = new VBox(10);
        painelLateral.setPadding(new Insets(10));
        painelLateral.setStyle("""
            -fx-background-color: #f9f9f9;
            -fx-border-color: #cccccc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);

        Label lblCustoTitulo = new Label("Custo da câmara");
        lblCustoTitulo.setFont(Font.font("System", FontWeight.BOLD, 13));
        lblCusto = new Label("R$ 0.00");

        Label lblSugeridoTitulo = new Label("Valor de venda sugerido");
        lblSugeridoTitulo.setFont(Font.font("System", FontWeight.BOLD, 13));
        lblSugerido = new Label("R$ 0.00 (30% de margem)");

        Label lblVendaTitulo = new Label("Valor da venda");
        lblVendaTitulo.setFont(Font.font("System", FontWeight.BOLD, 13));
        TextField campoVenda = new TextField();
        campoVenda.setPromptText("Digite o valor de venda");

        Button btnCalcular = new Button("Calcular");
        btnCalcular.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnCalcular.setMaxWidth(Double.MAX_VALUE);

        Label lblResultado = new Label();
        btnCalcular.setOnAction(e -> {
            try {
                double venda = Double.parseDouble(campoVenda.getText());
                double custoBaseAtual = calcularCustoTotal();
                double lucro = venda - custoBaseAtual;
                double porcentagem = custoBaseAtual > 0 ? (lucro / custoBaseAtual) * 100 : 0;
                lblResultado.setText(String.format("Lucro: R$ %.2f\nMargem: %.1f%%", lucro, porcentagem));
            } catch (Exception ex) {
                lblResultado.setText("Valor inválido.");
            }
        });

        // Botão "Custos operacionais"
        Button btnCustos = new Button("Custos operacionais");
        btnCustos.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px;");
        btnCustos.setOnAction(e -> abrirJanelaCustos2());

        HBox barraInferior2 = new HBox();
        barraInferior2.setPadding(new Insets(10, 10, 10, 10));
        barraInferior2.setAlignment(Pos.BOTTOM_RIGHT);
        barraInferior2.getChildren().add(btnCustos);

        painelLateral.getChildren().addAll(
                lblCustoTitulo, lblCusto,
                lblSugeridoTitulo, lblSugerido,
                new Separator(),
                lblVendaTitulo, campoVenda,
                btnCalcular, lblResultado,
                barraInferior2
        );
        return painelLateral;
    }

    // === LÓGICA DE NEGÓCIO / SUPORTE À UI ===

    public void adicionarPainel(String local, String modelo, int quantidade, double valorIndividual) {
        tabela.getItems().add(new Item(local, modelo, quantidade, valorIndividual * quantidade));
        atualizarCusto();
    }

    private double calcularCustoTotal() {
        return tabela.getItems().stream().mapToDouble(Item::getValor).sum();
    }

    private void atualizarCusto() {
        double custoBase = calcularCustoTotal();
        lblCusto.setText(String.format("R$ %.2f", custoBase));
        double vendaSugerida = custoBase * 1.3;
        lblSugerido.setText(String.format("R$ %.2f (30%% de margem)", vendaSugerida));
    }

    public void adicionarRecomendadosNaTabela() {
        tabela.getItems().removeIf(item ->
                item.getNome().equals("Motor") || item.getNome().equals("Evaporadora")
        );
        UnidadeCondensadoras motor = DadosCâmara.getMotorRecomendado();
        Evaporadoras evap = DadosCâmara.getEvaporadoraRecomendada();

        if (motor != null) {
            tabela.getItems().add(new Item("Motor", motor.getModelo(), 1, motor.getPreco()));
        }
        if (evap != null) {
            tabela.getItems().add(new Item("Evaporadora", evap.getModelo(), 1, evap.getPreco()));
        }
        atualizarCusto();
    }

    public void limparMateriaisPainel() {
        tabela.getItems().removeIf(item ->
                item.getNome().equals("Painel Parede")
                        || item.getNome().equals("Painel Teto")
                        || item.getNome().equals("Painel Piso")
        );
        atualizarCusto();
    }

    // === POPUPS / JANELAS AUXILIARES ===

    // Valores detalhados dos itens
    private void abrirJanelaItens1() {
        Stage popup = new Stage();
        popup.setTitle("Itens Câmera Fria");

        HBox conteudo = new HBox(10);
        conteudo.setPadding(new Insets(15));
        conteudo.setStyle("-fx-background-color: white;");

        TableView<Item> tabelaEspelho = new TableView<>();
        tabelaEspelho.setMinWidth(300);

        TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Item, String> colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Item, Double> colunaValor = new TableColumn<>("Valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setCellFactory(col -> new TableCell<Item, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) setText(null);
                else setText(String.format("R$ %.2f", valor));
            }
        });

        tabelaEspelho.getColumns().addAll(colunaNome, colunaModelo, colunaValor);

        tabelaEspelho.getItems().addAll(tabela.getItems());

        VBox boxBotao = new VBox();
        boxBotao.setAlignment(Pos.TOP_CENTER);
        Button btnTrocaValores = new Button("Troca valores");
        btnTrocaValores.setStyle("-fx-background-color: #FFB300; -fx-text-fill: #222;");
        btnTrocaValores.setOnAction(ev -> {
            new Alert(Alert.AlertType.INFORMATION, "Funcionalidade ainda não implementada!").showAndWait();
        });
        boxBotao.getChildren().add(btnTrocaValores);

        conteudo.getChildren().addAll(tabelaEspelho, boxBotao);

        Scene scene = new Scene(conteudo, 520, 300);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }

    // Janela de custos operacionais
    private void abrirJanelaCustos2() {
        Stage popup = new Stage();
        popup.setTitle("Custos Operacionais");

        VBox conteudo = new VBox(10);
        conteudo.setPadding(new Insets(15));
        conteudo.setStyle("-fx-background-color: white;");

        TextField campoTempo = new TextField();
        campoTempo.setPromptText("Tempo da obra (dias)");
        TextField campoEquipe = new TextField();
        campoEquipe.setPromptText("Custo da equipe (R$)");
        TextField campoBonificacao = new TextField();
        campoBonificacao.setPromptText("Bonificação (R$)");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnSalvar.setMaxWidth(Double.MAX_VALUE);
        btnSalvar.setOnAction(ev -> {
            // Aqui você pode armazenar os dados em variáveis ou passar para outra classe
            System.out.println("Tempo da obra: " + campoTempo.getText());
            System.out.println("Custo equipe: " + campoEquipe.getText());
            System.out.println("Bonificação: " + campoBonificacao.getText());
            popup.close();
        });

        conteudo.getChildren().addAll(
                new Label("Tempo da obra (dias)"), campoTempo,
                new Label("Custo da equipe"), campoEquipe,
                new Label("Bonificação"), campoBonificacao,
                btnSalvar
        );

        Scene scene = new Scene(conteudo, 300, 280);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }
    public void adicionarItemPorta(String tipoPorta, int quantidade) {
        // Remove portas anteriores desse tipo para evitar duplicidade
        tabela.getItems().removeIf(item -> item.getNome().equals("Porta") && item.getModelo().equals(tipoPorta));

        double valor = 2500.0; // Você pode mudar para um valor fixo de acordo com o tipo se quiser
        tabela.getItems().add(new Item("Porta", tipoPorta, quantidade, valor));
        atualizarCusto();
    }

    // === CLASSE INTERNA ITEM ===
    public static class Item {
        private final String nome;
        private final String modelo;
        private final int quantidade;
        private final double valor;

        public Item(String nome, String modelo, int quantidade, double valor) {
            this.nome = nome;
            this.modelo = modelo;
            this.quantidade = quantidade;
            this.valor = valor;
        }
        public String getNome() { return nome; }
        public String getModelo() { return modelo; }
        public int getQuantidade() { return quantidade; }
        public double getValor() { return valor; }
    }
}
