package ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DadosCâmara;
import model.Evaporadoras;
import model.Item;
import model.UnidadeCondensadoras;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PainelMaterialFX extends SplitPane {

    // === CAMPOS ===
    private boolean listaVisivel = true;
    private TableView<Item> tabela;
    private Label lblCusto;
    private Label lblSugerido;
    private ObservableList<Item> observableItensTabela = FXCollections.observableArrayList();
    private double custoTotalObra;



    // === CONSTRUTOR ===
    public PainelMaterialFX() {
        // --- Tabela de materiais ---
        configurarTabelaMateriais();
        tabela.setItems(observableItensTabela);

        // --- Painel lateral (Custo, Venda) ---
        VBox painelLateral = criarPainelLateral();

        // --- Painel principal (Tabela + botão "Valores") ---
        VBox painelTabela = criarPainelTabela();

        // --- Montagem do SplitPane ---
        this.getItems().addAll(painelTabela, painelLateral);
        this.setDividerPositions(0.7);

    }

    // === MÉTODOS DE CONSTRUÇÃO DE UI ===

    public void initialize() {
        tabela.setItems(observableItensTabela);
        // configure colunas normalmente
    }

    private void configurarTabelaMateriais() {

        tabela = new TableView<>();

        TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Item, String> colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaModelo.setPrefWidth(180);

        TableColumn<Item, String> colunaUnidade = new TableColumn<>("Unidade");
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colunaUnidade.setPrefWidth(68);

        TableColumn<Item, Integer> colunaQuantidade = new TableColumn<>("Quantidade");
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaQuantidade.setPrefWidth(88);

        TableColumn<Item, Double> colunaValorUnitario = new TableColumn<>("Valor Unitário");
        colunaValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValorUnitario.setPrefWidth(110);
        colunaValorUnitario.setCellFactory(col -> new TableCell<Item, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(formatoBR.format(valor));
                }
            }
        });

        TableColumn<Item, Double> colunaValorTotal = new TableColumn<>("Valor Total");
        colunaValorTotal.setPrefWidth(110);
        colunaValorTotal.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getQuantidade() * cellData.getValue().getValor())
        );
        colunaValorTotal.setCellFactory(col -> new TableCell<Item, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(formatoBR.format(valor));
                }
            }
        });

        tabela.getColumns().addAll(
                colunaNome,
                colunaModelo,
                colunaUnidade,
                colunaQuantidade,
                colunaValorUnitario,
                colunaValorTotal
        );
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
            -fx-background-color: linear-gradient(from 0% 100% to 0% 0%, #e3f4ff, white);
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
            -fx-background-color: linear-gradient(from 0% 100% to 0% 0%, #e3f4ff, white);
            -fx-border-color: #cccccc;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);

        Label lblCustoTitulo = new Label("Custo da câmara");
        lblCustoTitulo.setFont(Font.font("System", FontWeight.BOLD, 13));
        lblCusto = new Label(formatoBR.format(0));

        Label lblSugeridoTitulo = new Label("Valor de venda sugerido");
        lblSugeridoTitulo.setFont(Font.font("System", FontWeight.BOLD, 13));
        lblSugerido = new Label(formatoBR.format(0) + " (30% de margem)");


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

    public void adicionarPainel(String local, String modelo, String unidade, int quantidade, double valorIndividual) {
        observableItensTabela.add(new Item(local, modelo, unidade, quantidade, valorIndividual));
        atualizarCusto();
    }

    private double calcularCustoTotal() {
        return observableItensTabela.stream()
                .mapToDouble(item -> item.getValor() * item.getQuantidade())
                .sum();
    }

    private void atualizarCusto() {
        double custoBase = calcularCustoTotal();
        lblCusto.setText(formatoBR.format(custoBase));
        double vendaSugerida = custoBase * 1.3;
        lblSugerido.setText(formatoBR.format(vendaSugerida) + " (30% de margem)");
    }

    public void adicionarRecomendadosNaTabela() {
        observableItensTabela.removeIf(item ->
                item.getNome().equals("Motor") || item.getNome().equals("Evaporadora")
        );
        UnidadeCondensadoras motor = DadosCâmara.getMotorRecomendado();
        Evaporadoras evap = DadosCâmara.getEvaporadoraRecomendada();

        if (motor != null) {
            observableItensTabela.add(new Item("Motor", motor.getModelo(), "un", 1, motor.getPreco()));
        }
        if (evap != null) {
            observableItensTabela.add(new Item("Evaporadora", evap.getModelo(), "un", 1, evap.getPreco()));
        }
        atualizarCusto();
    }

    private static final NumberFormat formatoBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public void limparMateriaisPainel() {
        observableItensTabela.removeIf(item ->
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

        VBox container = new VBox(16);
        container.setPadding(new Insets(15));
        container.setStyle("-fx-background-color: white;");

        TableView<Item> tabelaEspelho = new TableView<>();
        tabelaEspelho.setEditable(true);
        tabelaEspelho.setItems(observableItensTabela); // <- Use a MESMA lista!        tabelaEspelho.setMinWidth(300);

        TableColumn<Item, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Item, String> colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Item, Double> colunaValor = new TableColumn<>("Valor");
        colunaValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty().asObject());
        colunaValor.setCellFactory(TextFieldTableCell.<Item, Double>forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        colunaValor.setOnEditCommit(event -> {
            event.getRowValue().setValor(event.getNewValue());
        });

        tabelaEspelho.getColumns().addAll(colunaNome, colunaModelo, colunaValor);

        // Exemplo de preencher com alguns itens (troque pelo seu ObservableList):
        // tabelaEspelho.setItems(listaItens);

        Button btnSalvar = new Button("Salvar Alterações");
        btnSalvar.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white;");
        btnSalvar.setMaxWidth(Double.MAX_VALUE);

        btnSalvar.setOnAction(e -> {
            // Aqui você pode salvar ou atualizar os valores conforme sua lógica
            for (Item item : tabelaEspelho.getItems()) {
                System.out.println(item.getNome() + ": " + item.getValor());
            }
            popup.close();
        });

        container.getChildren().addAll(tabelaEspelho, btnSalvar);

        Scene scene = new Scene(container, 400, 400);
        popup.setScene(scene);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }

    // Janela de custos operacionais
    private void abrirJanelaCustos2() {

        //this.custoTotalObra = total;
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
            try {
                int tempo = Integer.parseInt(campoTempo.getText().trim());
                double custoEquipe = Double.parseDouble(campoEquipe.getText().replace(",", ".").trim());
                double bonificacao = campoBonificacao.getText().isBlank() ? 0
                        : Double.parseDouble(campoBonificacao.getText().replace(",", ".").trim());

                double total = tempo * custoEquipe + bonificacao;

                System.out.println("Tempo da obra: " + tempo);
                System.out.println("Custo equipe: R$" + custoEquipe);
                System.out.println("Bonificação: R$" + bonificacao);
                System.out.println("Total final = R$" + total);

                // Use a variável 'total' conforme sua aplicação (ex: atualize o valor da câmara)
                popup.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Preencha os campos corretamente!", ButtonType.OK);
                alert.showAndWait();
            }
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
        observableItensTabela.removeIf(item -> item.getNome().equals("Porta") && item.getModelo().equals(tipoPorta));

        double valor = 2500.0; // Você pode mudar para um valor fixo de acordo com o tipo se quiser
        observableItensTabela.add(new Item("Porta", tipoPorta, "un", quantidade, valor));
        atualizarCusto();
    }
    public void adicionarItensComplementares(List<Item> itensComplementares) {
        observableItensTabela.addAll(itensComplementares);
    }
}
