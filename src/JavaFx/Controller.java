package JavaFx;

import NoWarPolis.*;

import edu.princeton.cs.algs4.DirectedEdge;
import NoWarPolis.Etiqueta;
import NoWarPolis.Log;
import NoWarPolis.Nodes;
import NoWarPolis.User;
import edu.princeton.cs.algs4.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static NoWarPolis.Base_Dados.*;

public class Controller {

    public Group graphGroup;
    public static SymbolDigraphLP grafo_tempos = new SymbolDigraphLP();
    public static SymbolDigraphLP grafo_distancias = new SymbolDigraphLP();
    private static final int radius = 20;
    private String basicUsersFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\basicUsersFX.txt");
    private String adminUsersFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\adminUsersFX.txt");
    private String nodesFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\nodesFX.txt");
    private String ligacoesFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\ligacoesnodes");
    private String etiquetasFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\etiquetasFX.txt");


    private static String UtilizadoreFX = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\UtilizadoreFX.txt");

    public MenuItem infoTxtMenu;
    public MenuItem carregarUTxtMenu;
    public MenuItem carregarNTxtMenu;
    public MenuItem carregarLTxtMenu;
    public MenuItem carregarETxtMenu;

    public MenuItem carregarUBinMenu;
    public MenuItem carregarNBinMenu;
    public MenuItem carregarLBinMenu;
    public MenuItem carregarEBinMenu;

    public MenuItem graphTxtMenu;
    public MenuItem graphBinMenu;
    public MenuItem infoSaveTxtMenu;
    public MenuItem infoSaveBinMenu;
    public MenuItem graphTxtSaveMenu;
    public MenuItem graphBinSaveMenu;

    public Pane graphPane;

    public TableColumn<User, Integer> idUtilizadores;
    public TableColumn<User, String> nomeUtilizadores;
    public TableColumn<User, String> tipoUtilizadores;
    public TableColumn<User, String> veiculoUtilizadores;
    public TextField nomeUtilizadoresTextField;
    public TextField tipoUtilizadoresTextField;
    public TextField veiculoUtilizadoresTextField;
    public Button adicionarUtilizadores;
    public Button removerUtilizadores;


    public TableColumn<Nodes, String> nomeNodes;
    public TableColumn<Nodes, String> tipoNodes;
    public TableColumn<Nodes, String> tipopoiNodes;
    public TableColumn<Nodes, Float> latitudeNodes;
    public TableColumn<Nodes, Float> longitudeNodes;
    public TableColumn<Nodes, String> veiculoNodes;
    public TextField idNodesTextField;
    public TextField nomeNodesTextField;
    public TextField tipoNodesTextField;
    public TextField tipopoiNodesTextField;
    public TextField xNodesTextField;
    public TextField yNodesTextField;
    public TextField veiculoNodesTextField;
    public Button adicionarNodes;
    public Button removerNodes;

    public TableColumn<Log, String> nodeLogs;
    public TableColumn<Log, String> dataLogs;
    public TableColumn<Log, String> infoLogs;
    public TableColumn<Log, String> mensagemLogs;
    public TableColumn<Log, String> userLogs;
    public TextField dataLogsTextField;
    public TextField infoLogsTextField;
    public TextField mensagemLogsTextField;
    public TextField nodeLogsTextField;
    public TextField userLogsTextField;
    public Button addLogs;
    public Button removeLogs;

    public TableColumn<Etiqueta, String> nomeEtiquetas;
    public TableColumn<Etiqueta, String> userEtiquetas;
    public TableColumn<Etiqueta, String> descricaoEtiquetas;
    public TableColumn<Etiqueta, Float> latitudeEtiquetas;
    public TableColumn<Etiqueta, Float> longitudeEtiquetas;
    public TextField nomeEtiquetasTextField;
    public TextField userEtiquetasTextField;
    public TextField descricaoEtiquetasTextField;
    public TextField xEtiquetasTextField;
    public TextField yEtiquetasTextField;
    public Button addEtiquetas;
    public Button removeEtiquetas;

    public TableColumn<Ways, Integer> idLigacoes;
    public TableColumn<Ways, String> node1Ligacoes;
    public TableColumn<Ways, String> node2Ligacoes;
    public TableColumn<Ways, Float> distanciaLigacoes;
    public TableColumn<Ways, Float> tempoLigacoes;
    public TextField node1TextField;
    public TextField node2TextField;
    public TextField distanciaTextField;
    public TextField tempoTextField;
    public Button adicionarLigacoes;
    public Button removerLigacoes;

    public TableView<Etiqueta> etiquetasTable;
    public TableView<Log> logsTable;
    public TableView<Nodes> nodesTable;
    public TableView<User> usersTable;
    public TableView<Ways> ligacoesTable;

    @FXML
    public void initialize() {
        //Tornar as Tables editáveis
        usersTable.setEditable(true);
        nodesTable.setEditable(true);
        logsTable.setEditable(true);
        etiquetasTable.setEditable(true);
        ligacoesTable.setEditable(true);

        //Tornar as Colunas não editáveis
        dataLogs.setEditable(false);

        //Users
        idUtilizadores.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nomeUtilizadores.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoUtilizadores.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        veiculoUtilizadores.setCellValueFactory(new PropertyValueFactory<>("veiculo"));

        //Edit Users
        idUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nomeUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn());
        veiculoUtilizadores.setCellFactory(TextFieldTableCell.forTableColumn());

        //Nodes
        nomeNodes.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tipoNodes.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tipopoiNodes.setCellValueFactory(new PropertyValueFactory<>("TipoPoi"));
        latitudeNodes.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeNodes.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        veiculoNodes.setCellValueFactory(new PropertyValueFactory<>("Veiculo"));

        //Editar Nodes
        nomeNodes.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoNodes.setCellFactory(TextFieldTableCell.forTableColumn());
        latitudeNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        longitudeNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        //Logs
        nodeLogs.setCellValueFactory(new PropertyValueFactory<>("Node"));
        dataLogs.setCellValueFactory(new PropertyValueFactory<>("data"));
        infoLogs.setCellValueFactory(new PropertyValueFactory<>("Info"));
        mensagemLogs.setCellValueFactory(new PropertyValueFactory<>("Mensagem"));
        userLogs.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //Editar Logs
        nodeLogs.setCellFactory(TextFieldTableCell.forTableColumn());
        dataLogs.setCellFactory(TextFieldTableCell.forTableColumn());
        infoLogs.setCellFactory(TextFieldTableCell.forTableColumn());
        mensagemLogs.setCellFactory(TextFieldTableCell.forTableColumn());

        //Etiquetas
        nomeEtiquetas.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        userEtiquetas.setCellValueFactory(new PropertyValueFactory<>("nomeCriador"));
        descricaoEtiquetas.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        latitudeEtiquetas.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeEtiquetas.setCellValueFactory(new PropertyValueFactory<>("longitude"));

        //Editar Etiquetas
        nomeEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());
        userEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());
        latitudeNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        longitudeNodes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        descricaoEtiquetas.setCellFactory(TextFieldTableCell.forTableColumn());

        //Ligacoes
        idLigacoes.setCellValueFactory(new PropertyValueFactory<>("idl"));
        node1Ligacoes.setCellValueFactory(new PropertyValueFactory<>("node1L"));
        node2Ligacoes.setCellValueFactory(new PropertyValueFactory<>("node2L"));
        distanciaLigacoes.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        tempoLigacoes.setCellValueFactory(new PropertyValueFactory<>("tempo"));

        //Editar Ligacoes
        node1Ligacoes.setCellFactory(TextFieldTableCell.forTableColumn());
        node2Ligacoes.setCellFactory(TextFieldTableCell.forTableColumn());
        distanciaLigacoes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        tempoLigacoes.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        readNodesFromFile(nodesFXtxt);
        //updateGraph();


    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public void handleCarregarInfoTXT(ActionEvent actionEvent) throws IOException {
        ArrayList<Basic> basicUsers = readBasicUsersFromFile(basicUsersFXtxt);
        ArrayList<Admin> adminUsers = readAdminUsersFromFile(adminUsersFXtxt);
        ArrayList<Etiqueta> etiquetasArrayList = readEtiquetasFromFile(etiquetasFXtxt);
        ArrayList<Ways> ligacoes = readLigacoesFromFile(ligacoesFXtxt);
        ArrayList<Nodes> nodesArrayList = new ArrayList<>();
        ArrayList<Log> LogsNode = new ArrayList<>();

        for (String s : nodes.keys())
            nodesArrayList.add(nodes.get(s));
        admins.get(5).visitarPoi(nodes.get("p1"), "fogo");
        for (Nodes c : nodesArrayList) {
            if (!c.getLogs().isEmpty())
                for (int i : c.getLogs().keys())
                    LogsNode.add(c.getLogs().get(i));
        }

        usersTable.getItems().addAll(basicUsers);
        usersTable.getItems().addAll(adminUsers);
        nodesTable.getItems().addAll(nodesArrayList);
        etiquetasTable.getItems().addAll(etiquetasArrayList);
        logsTable.getItems().addAll(LogsNode);
        ligacoesTable.getItems().addAll(ligacoes);
    }

    /**
     * Leitura de dados de um ficheiro de INPUT
     *
     * @param path Caminho para o ficheiro ler
     * @return retorna o arraylist com as utilizadores basics
     */
    private static ArrayList<Basic> readBasicUsersFromFile(String path) {
        ArrayList<Basic> basicUsers = new ArrayList<>();
        In in = new In(path);

        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Basic b = new Basic(fields[0], fields[1], fields[2]);

                basicUsers.add(b);
                basics.put(b.getId(), b);
            }
        }

        return basicUsers;
    }


    /**
     * Leitura de dados de um ficheiro de INPUT
     *
     * @param path Caminho para o ficheiro ler
     * @return retorna o arraylist com as utilizadores Admin
     */
    private static ArrayList<Admin> readAdminUsersFromFile(String path) {
        ArrayList<Admin> adminUsers = new ArrayList<>();
        In in = new In(path);

        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Admin a = new Admin(fields[0], fields[1], fields[2]);
                adminUsers.add(a);
                admins.put(a.getId(), a);
            }

        }
        return adminUsers;
    }

    public static void readNodesFromFile(String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Poi":
                        Nodes p = new Nodes(fields[0], fields[2], fields[1], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), fields[5]);
                        p.setLatitude(Float.parseFloat(fields[3]));
                        p.setLongitude(Float.parseFloat(fields[4]));
                        //grafo_distancias.addVertex(fields[3]);
                        //grafo_tempos.addVertex(fields[3]);
                        nodes.put(p.getNome(), p);
                        break;
                    case "Curva":
                        ArrayList<Point> GPS = new ArrayList<>();
                        Point p1 = new Point(Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                        GPS.add(p1);
                        Point p2 = new Point(Float.parseFloat(fields[4]), Float.parseFloat(fields[5]));
                        GPS.add(p2);
                        Point p3 = new Point(Float.parseFloat(fields[6]), Float.parseFloat(fields[7]));
                        GPS.add(p3);
                        Point p4 = new Point(Float.parseFloat(fields[8]), Float.parseFloat(fields[9]));
                        GPS.add(p4);
                        Nodes cu = new Nodes(fields[0], fields[1], GPS, fields[10]);
                        cu.setLatitude(Float.parseFloat(fields[2]));
                        cu.setLongitude(Float.parseFloat(fields[3]));
                        //grafo_distancias.addVertex(fields[2]);
                        //grafo_tempos.addVertex(fields[2]);
                        nodes.put(cu.getNome(), cu);
                        break;
                    case "Cruzamento":
                        Nodes cr = new Nodes(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[4]);
                        cr.setLatitude(Float.parseFloat(fields[2]));
                        cr.setLongitude(Float.parseFloat(fields[3]));
                        grafo_distancias.addVertex(fields[2]);
                        grafo_tempos.addVertex(fields[2]);
                        nodes.put(cr.getNome(), cr);
                        break;
                    case "Entroncamento":
                        Nodes e = new Nodes(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[4]);
                        e.setLatitude(Float.parseFloat(fields[2]));
                        e.setLongitude(Float.parseFloat(fields[3]));
                        grafo_distancias.addVertex(fields[2]);
                        grafo_tempos.addVertex(fields[2]);
                        nodes.put(e.getNome(), e);
                        break;
                }
            }
        }
    }

    public ArrayList<Ways> readLigacoesFromFile(String path) {
        ArrayList<Ways> Ligacoes = new ArrayList<>();
        In in = new In(path);
        String string1;
        String string2;
        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Ways w = new Ways(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                String field1 = fields[0];
                String field2 = fields[1];
                double distancia = Double.parseDouble(fields[2]);
                double tempo = Double.parseDouble(fields[3]);
                Nodes n1 = nodes.get(field1);
                Nodes n2 = nodes.get(field2);
                string1 = String.valueOf(n1.getId());
                string2 = String.valueOf(n2.getId());
                grafo_distancias.addEdge(string1, string2, distancia);
                grafo_tempos.addEdge(string1, string2, tempo);
                Ligacoes.add(w);
                ligacoes.put(w.getIdl(), w);

            }

        }

        return Ligacoes;
    }


    public static ArrayList<Etiqueta> readEtiquetasFromFile(String path) {
        ArrayList<Etiqueta> Etiquetas = new ArrayList<>();
        In in = new In(path);

        while (!in.isEmpty()) {
            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Etiqueta e = new Etiqueta(fields[0], fields[1], fields[2], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]));
                e.setLatitude(Float.parseFloat(fields[3]));
                e.setLongitude(Float.parseFloat(fields[4]));
                //e.tostring();
                //System.out.println(Float.parseFloat(fields[3]));
                Etiquetas.add(e);
                etiquetas.put(e.getNome(), e);
            }

        }
        return Etiquetas;
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------- POPULAR COMBOBOXS -------------------------------------------------*/






    /*---------------------------------------------------------------------------------------------------------------*/

    /*-------------------------------------- REMOVER ITEMS DAS TABELAS ----------------------------------------------*/

    public void handleRemoveUserAction(ActionEvent actionEvent) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        usersTable.getItems().remove(usersTable.getSelectionModel().getSelectedItem());
        removerUser(user);
    }

    public void handleRemoveNodesAction(ActionEvent actionEvent) throws IOException {
        Nodes node = nodesTable.getSelectionModel().getSelectedItem();
        nodesTable.getItems().remove(nodesTable.getSelectionModel().getSelectedItem());
        nodes.delete(node.getNome());
        nodesTable.refresh();
        //atualizarLogs();
    }

    public void handleRemoveEtiquetaAction(ActionEvent actionEvent) throws IOException {
        Etiqueta et = etiquetasTable.getSelectionModel().getSelectedItem();
        etiquetasTable.getItems().remove(etiquetasTable.getSelectionModel().getSelectedItem());
        etiquetas.delete(et.getNome());
        etiquetasTable.refresh();
    }

    public void handleRemoveLigacoesAction(ActionEvent actionEvent) throws IOException {
        Ways w = ligacoesTable.getSelectionModel().getSelectedItem();
        ligacoesTable.getItems().remove(ligacoesTable.getSelectionModel().getSelectedItem());
        ligacoes.delete(w.getIdl());
        ligacoesTable.refresh();
    }


    /*---------------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------- ADICIONAR ITEMS NAS TABELAS ------------------------------------------*/

    public void handleAdicionarUtilizador(ActionEvent actionEvent) {
        if (nomeUtilizadoresTextField.getText().isEmpty() || tipoUtilizadoresTextField.getText().isEmpty() || veiculoUtilizadoresTextField.getText().isEmpty()) {
            Alert("Utilizador", null, "Por favor preencha o campo que se encontra vazio!");
        } else {

            if (tipoUtilizadoresTextField.getText().equals("Basic")) {
                Basic b = new Basic(nomeUtilizadoresTextField.getText(), tipoUtilizadoresTextField.getText(), veiculoUtilizadoresTextField.getText());
                basics.put(b.getId(), b);
                usersTable.getItems().add(b);
            } else if (tipoUtilizadoresTextField.getText().equals("Admin")) {
                Admin a = new Admin(nomeUtilizadoresTextField.getText(), tipoUtilizadoresTextField.getText(), veiculoUtilizadoresTextField.getText());
                admins.put(a.getId(), a);
                usersTable.getItems().add(a);
            } else
                Alert("Utilizador", "(basic / admin)", "Tipo de Utilizador inválido");
            nomeUtilizadoresTextField.clear();
            tipoUtilizadoresTextField.clear();

        }
    }

    public void handleAdicionarNodes(ActionEvent actionEvent) {
        if (nomeNodesTextField.getText().isEmpty() || tipoNodesTextField.getText().isEmpty()
                || xNodesTextField.getText().isEmpty() || yNodesTextField.getText().isEmpty()) {
            Alert("Node", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
            if (tipoNodesTextField.getText().equals("Poi") && tipopoiNodesTextField.getText().isEmpty()) {
                Alert("Node", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
            }
        } else {
            if (!nodes.contains(nomeNodesTextField.getText())) {
                Nodes n;
                if (tipoNodesTextField.getText().equals("Poi")) {
                    n = new Nodes(tipoNodesTextField.getText(), tipopoiNodesTextField.getText(),
                            nomeNodesTextField.getText(),
                            Float.parseFloat(xNodesTextField.getText()),
                            Float.parseFloat(yNodesTextField.getText()),
                            veiculoNodesTextField.getText());
                    n.setLatitude(Float.parseFloat(xNodesTextField.getText()));
                    n.setLongitude(Float.parseFloat(yNodesTextField.getText()));
                } else {
                    n = new Nodes(tipoNodesTextField.getText(),
                            nomeNodesTextField.getText(),
                            Float.parseFloat(xNodesTextField.getText()),
                            Float.parseFloat(yNodesTextField.getText()),
                            veiculoNodesTextField.getText());
                    n.setLatitude(Float.parseFloat(xNodesTextField.getText()));
                    n.setLongitude(Float.parseFloat(yNodesTextField.getText()));
                }

                grafo_distancias.addVertex(String.valueOf(n.getId()));
                grafo_tempos.addVertex(String.valueOf(n.getId()));
                nodes.put(n.getNome(), n);
                nodesTable.getItems().add(n);
            } else Alert("Node", null, "Node introduzida já consta na DB!");
            //updateGraph();
            nomeNodesTextField.clear();
            tipoNodesTextField.clear();
            xNodesTextField.clear();
            yNodesTextField.clear();
            veiculoNodesTextField.clear();
            tipopoiNodesTextField.clear();
        }

    }

    public void handleAdicionarEtiqueta(ActionEvent actionEvent) {
        int i = 0;
        if (nomeEtiquetasTextField.getText().isEmpty() || userEtiquetasTextField.getText().isEmpty()
                || descricaoEtiquetasTextField.getText().isEmpty() || xEtiquetasTextField.getText().isEmpty() ||
                yEtiquetasTextField.getText().isEmpty()) {
            Alert("Etiqueta", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
        } else {
            for (Integer a : admins.keys()) {
                if (admins.get(a).getNome().equals(userEtiquetasTextField.getText())) {
                    if (!etiquetas.contains(nomeEtiquetasTextField.getText())) {
                        Etiqueta et = new Etiqueta(nomeEtiquetasTextField.getText(), userEtiquetasTextField.getText(),
                                descricaoEtiquetasTextField.getText(),
                                Float.parseFloat(xEtiquetasTextField.getText()), Float.parseFloat(yEtiquetasTextField.getText()));
                        et.setLatitude(Float.parseFloat(xEtiquetasTextField.getText()));
                        et.setLongitude(Float.parseFloat(yEtiquetasTextField.getText()));
                        etiquetas.put(et.getNome(), et);
                        etiquetasTable.getItems().add(et);
                        i = 1;
                    } else {
                        Alert("Etiqueta", null, "Etiqueta introduzida já consta na DB!");
                    }
                }
            }
            if (i == 0) {
                Alert("Etiqueta", null, "O utilizador não pode criar etiquetas!");
            }
            nomeEtiquetasTextField.clear();
            userEtiquetasTextField.clear();
            descricaoEtiquetasTextField.clear();
            xEtiquetasTextField.clear();
            yEtiquetasTextField.clear();
        }
    }

    public void handleAdicionarLogs(ActionEvent actionEvent) {
        if (dataLogsTextField.getText().isEmpty() || nodeLogsTextField.getText().isEmpty()
                || infoLogsTextField.getText().isEmpty() || mensagemLogsTextField.getText().isEmpty() || userLogsTextField.getText().isEmpty()) {
            Alert("Historico", null, "Por favor preencha o/s campo/s que se encontra/m vazio!");
        } else {
            String[] s = dataLogsTextField.getText().split("/");
            if (s.length == 3 && nodes.contains(nodeLogsTextField.getText())) {
                if (basics.contains(Integer.parseInt(s[1])) || admins.contains(Integer.parseInt(s[1]))) {
                    java.util.Date d = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                    Log log = new Log(d, infoLogs.getText(), mensagemLogsTextField.getText(), Integer.parseInt(userLogsTextField.getText()));
                    log.setNode(nodes.get(nodeLogsTextField.getText()).getNome());
                    logsTable.getItems().add(log);
                } else {
                    Alert("Utilizador", "(basic / admin)", "Não existe esse Id na DB!");
                }


            } else Alert("Historico", "(Ano, mes, dia)",
                    "Data de registo inválida!");

            dataLogsTextField.clear();
            nodeLogsTextField.clear();
            infoLogsTextField.clear();
            mensagemLogsTextField.clear();
            userLogsTextField.clear();
        }

    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------- FUNÇÔES AUXILIARES ------------------------------------------------*/

    public void Alert(String Title, String Info, String Header) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle(Title);
        a1.setContentText(Info);
        a1.setHeaderText(Header);
        a1.showAndWait();
    }


    /*---------------------------------------------------------------------------------------------------------------*/


    public void updateGraph() {
        graphGroup.getChildren().clear();

        SymbolDigraphLP grafo = grafo_distancias;
        // Cache.grafo_tempos = new SymbolDigraphLP();
        int[] xx = new int[grafo.getNvertices()];
        int[] yy = new int[grafo.getNvertices()];

        // adicionar vertices
        for (int i = 0; i < grafo.getNvertices(); i++) {
            int x = (int) (Math.random() * 650);
            int y = (int) (Math.random() * 400);
            int radius = 25;
            xx[i] = x;
            yy[i] = y;

            Circle c = new Circle(x, y, radius);
            System.out.println(nodes.get(grafo.nameOf(i)));
            if (nodes.get(grafo.nameOf(i)).getTipo().equals("Poi"))
                c.setFill(Color.GOLD);
            else
                c.setFill(Color.LIGHTSKYBLUE);

            StackPane stack = new StackPane();
            stack.setLayoutX(x - radius);
            stack.setLayoutY(y - radius);
            stack.getChildren().addAll(c, new Text(grafo.nameOf(i)));

            graphGroup.getChildren().add(stack);
        }

        // adicionar aresta
        for (int i = 0; i < grafo.getNvertices(); i++) {
            for (DirectedEdge e : grafo.digraph().adj(i)) {
                Line line = new Line(xx[e.from()], yy[e.from()], xx[e.to()], yy[e.to()]);
                line.setStroke(Color.LIGHTGRAY);
                graphGroup.getChildren().add(line);
            }
        }
    }

    /*private static void removeWays(Ways w) throws IOException {

        if (ways.contains(w.getNome())) {
            FileWriter wr = new FileWriter(waysRemovidastxt, true);
            wr.write(w.getId() + ", " + w.getNome() + ", " + w.getTipo() + ";\n");
            wr.write(w.getMyEtiqueta().size() + " etiquetas :\n");
            for (Etiqueta e : w.getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }

            wr.close();
            ways.delete(w.getNome());
        } else System.out.println("Erro, o Way nao existe nao DB!");
    }*/

    public void handlewriteUsersTXT() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
            escritaFicheiroTxtUtilizadores();

    }

    public void handlewriteNodesTXT() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        escritaFicheiroTxtNodes();

    }

    public void handlewriteLigacoesTXT() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        escritaFicheiroTxtligacoes();

    }

    public void escritaFicheiroTxtEtiquetasFX() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        escritaFicheiroTxtEtiquetas();
    }


    @FXML
    void handlewriteUsersBIN(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Binary File");
        escritaFicheiroBinUtilizadores();
    }

    @FXML
    void handlewriteNodesBIN(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Binary File");
        escritaFicheiroBinNodes();
    }

    @FXML
    void handlewriteLigacoesBIN(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Binary File");
        escritaFicheiroBinligacoes();
    }

    @FXML
    void handlewriteEtiquetasBIN(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Binary File");
        escritaFicheiroBinEtiqueta();
    }

}



