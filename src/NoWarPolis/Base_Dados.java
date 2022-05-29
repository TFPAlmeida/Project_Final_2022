package NoWarPolis;

import JavaFx.SymbolDigraphLP;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Base_Dados {

    private static String inputNodestxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\inputNodes");
    private static String inputWaystxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\inputWays");
    private static String utilizadorestxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\utilizadorestxt");
    private static String nodestxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\nodes.txt");
    private static String etiquetastxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\etiquetas.txt");
    private static String waystxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\ways.txt");


    private static String UtilizadoresFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\UtilizadoresFXtxt");
    private static String NodesFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\NodesFXtxt");
    private static String LigacoesFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\LigacoesFXtxt");
    private static String etiquetasFXtxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXtxt\\EtiquetasFXtxt");

    private static String UtilizadoresFXbin = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXbin\\UtilizadoresFXbin");
    private static String NodesFXbin = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXbin\\NodesFXbin");
    private static String LigacoesFXbin = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXbin\\LigacoesFXbin");
    private static String EtiquetasFXbin = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\FXbin\\EtiquetasFXbin");


    private static String utilizadoresRemovidostxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\utilizadoresRemovidos.txt");
    private static String nodesRemovidastxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\nodesRemovidas.txt");
    private static String waysRemovidastxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\waysRemovidas.txt");
    private static String etiquetasRemovidostxt = ("C:\\Users\\tiago\\IdeaProjects\\Project_Final_2022\\data\\txt\\etiquetasRemovidos.txt");

    public static SeparateChainingHashST<Integer, Basic> basics = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<Integer, Admin> admins = new SeparateChainingHashST<>();

    public static SeparateChainingHashST<String, Nodes> nodes = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Ways> ways = new SeparateChainingHashST<>();

    public static RedBlackBST<String, Etiqueta> etiquetas = new RedBlackBST<>();

    public static SeparateChainingHashST<Integer, Ways> ligacoes = new SeparateChainingHashST<>();


    /**
     * Pesquisa por um objeto do tipo Etiqueta no Arraylist de myEtiqueta dos Nodes recebida por parametro,
     * por nome ou descrição;
     *
     * @param n      Objeto do tipo Node;
     * @param string Nome/descrição da Etiqueta a procurar;
     * @return Retorna o Objeto do tipo Etiqueta caso seja encontrado nos Nodes;
     */
    public static Etiqueta findEtiquetaInNode(Nodes n, String string) {
        for (Etiqueta e : n.getMyEtiqueta()) {
            if (e.getDescricao().equals(string) || e.getNome().equals(string)) {
                return e;
            }
        }
        System.out.println("ERRO! { \n\tNenhuma etiqueta com a descrição ou nome: '" + string +
                "', encontrado nas etiquetas dos nodes!\n}\n");
        return null;
    }

    public static Etiqueta findEtiquetaInWay(Ways w, String string) {
        for (Etiqueta e : w.getMyEtiqueta()) {
            if (e.getDescricao().equals(string) || e.getNome().equals(string)) {
                return e;
            }
        }
        System.out.println("ERRO! { \n\tNenhuma etiqueta com a descrição ou nome: '" + string +
                "', encontrado nas etiquetas das ways!\n}\n");
        return null;
    }

    /**
     * Pesquisa por um objeto do tipo Etiqueta no Arraylist de Etiquetas do User recebido por parametro,
     * por nome ou descrição;
     *
     * @param User   Objeto do tipo User;
     * @param string Nome/descrição da Etiqueta a procurar;
     * @return Retorna o Objeto do tipo Etiqueta caso seja encontrado no User;
     */
    public static Etiqueta findEtiquetaInUsers(Object User, String string) {
        if (User instanceof Basic) {
            System.out.println("Erro! \nOs Utilizadores Basic não podem ter etiquetas!\n");
        }
        if (User instanceof Admin) {
            for (Etiqueta e : ((Admin) User).getEtiquetas()) {
                if (e.getDescricao().equals(string) || e.getNome().equals(string)) {
                    return e;
                }
            }
            System.out.println("ERRO! { \n\tNenhuma etiqueta com a descrição: '" + string +
                    "', encontrado nas etiquetas dos nodes!\n}\n");
        }
        return null;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * ADD / EDITAR / REMOVER / LISTAR --CACHES / LOGS--
     */


    /**
     * Adicionar objetos do tipo Cache na ST de Caches global;
     *
     * @param n Objeto do tipo Cache a add;
     */
    public static void addNode(Nodes n) {
        if (nodes.contains(n.getNome())) {
            System.out.println("Erro, node ja existe na DB!");
            return;
        }
        nodes.put(n.getNome(), n);
    }

    private static void removeNode(Nodes n) throws IOException {
        if (nodes.contains(n.getNome())) {
            FileWriter wr = new FileWriter(nodesRemovidastxt, true);
            if (n.getTipo().equals("Poi")) {
                wr.write(n.getNome() + ", " + n.getTipo() + ", " + n.getTipoPoi() +
                        ", " + n.getGPS().getLatitude() + ", " + n.getGPS().getLongitude() + ";\n");
            } else {
                wr.write(n.getNome() + ", " + n.getTipo() + ", " + n.getTipo() +
                        ", " + n.getGPS().getLatitude() + ", " + n.getGPS().getLongitude() + ";\n");
            }
            wr.write(n.getMyEtiqueta().size() + " items :\n");
            for (Etiqueta e : n.getMyEtiqueta()) {
                wr.write(", " + e.getDescricao());
            }
            wr.write("\n" + n.getLogs().size() + " Logs :\n");
            for (int i : n.getLogs().keys()) {
                wr.write(n.getLogs().get(i).toString());
            }
            wr.close();
            nodes.delete(n.getNome());
        } else System.out.println("Erro, a cache nao existe nao DB!");
    }

    /**
     * Editar objetos do tipo Node que estão ST de Nodes global;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param n         Objeto do tipo Node a editar;
     * @param nome      Novo nome do Node (String)
     * @param latitude  Nova latitude do Node (Float)
     * @param longitude Nova longitude do Node (Float)
     */
    public static void editarNode(Nodes n, String nome, float latitude, float longitude, String veiculo) {
        String name = n.getNome();
        if (!nodes.contains(name)) {
            System.out.println("Erro Node nao registada na DB!");
            return;
        }
        nodes.get(name).setNome(nome);
        nodes.get(name).getGPS().setLatitude(latitude);
        nodes.get(name).getGPS().setLatitude(longitude);
    }



    public static Nodes getNodes(String nome) {
        for (String s : nodes.keys()) {
            if (s.equals(nome)) {
                return nodes.get(s);
            }
        }
        return null;
    }

    /**
     * Lista todas os objetos do tipo Cache que foram inseridas na ST caches;
     */
    public static void listarNodes() {
        System.out.println("Nodes: \n");
        for (String node : nodes.keys()) {
            if (nodes.get(node).getTipo().equals("Poi")) {
                    nodes.get(node).tostring2();
            } else if(nodes.get(node).getTipo().equals("Curva")) {
                nodes.get(node).tostring3();
            }else{
                nodes.get(node).tostring1();
            }
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * ADD / EDITAR / REMOVER / LISTAR --Ways / LOGS--
     */


    /**
     * Adicionar objetos do tipo Ways na ST de Node global;
     *
     * @param w Objeto do tipo Ways a add;
     */
    public static void addWays(Ways w) {
        if (ways.contains(w.getNome())) {
            System.out.println("Erro, Way ja existe na DB!");
            return;
        }
        ways.put(w.getNome(), w);
    }

    private static void removeWays(Ways w) throws IOException {

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
    }

    /**
     * Editar objetos do tipo Ways que estão ST de Ways global;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param w       Objeto do tipo Ways a editar;
     * @param nome       Novo nome do Ways (String)
     */
    public static void editarWays(Ways w, String nome) {
            String name = w.getNome();
            if (!ways.contains(name)) {
                System.out.println("Erro Way nao registado na DB!");
                return;
            }
            ways.get(name).setNome(nome);
    }

    public static void listarWays() {
        System.out.println("Ways: \n");
        for (String way : ways.keys()) {
            if(ways.get(way).getTipo().equals("Rua")){
                ways.get(way).tostring1();
            }else {
                ways.get(way).tostring2();
            }
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /*  ADD / EDITAR / REMOVER / LISTAR --USERS--  */

    /**
     * Adicionar objetos do tipo User na ST basics ou Admins;
     *
     * @param User Objeto do tipo User a add;
     */
    public static void addUser(Object User) {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (basics.contains(id)) {
                System.out.println("Erro, Utilizador Basic com ID:" + id + " já existe na DB!");
                return;
            }
            basics.put(id, ((Basic) User));
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (admins.contains(id)) {
                System.out.println("Erro, Utilizador Admin com ID:" + id + " já existe na DB!");
                return;
            }
            admins.put(id, ((Admin) User));
        }
    }

    /**
     * Editar objetos do tipo User que estão ST basics;
     * Caso o objeto nao esteja inserido na ST, uma mensagem
     * de erro é enviada para o terminal;
     *
     * @param User Objeto do tipo User a editar;
     * @param nome Novo nome do Admin ou Basic(String);
     */
    public static void editarUser(Object User, String nome) {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (!basics.contains(id)) {
                System.out.println("Erro, Utilizador Basic com ID:" + id + " já existe na DB!");
                return;
            }
            basics.get(id).setNome(nome);
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (admins.contains(id)) {
                System.out.println("Erro, Utilizador Admin com ID:" + id + " já existe na DB!");
                return;
            }
            basics.get(id).setNome(nome);
        }
    }

    /**
     * Remover objetos do tipo User na ST basics;
     *
     * @param User Objeto do tipo User a remover;
     */
    public static void removerUser(Object User) throws IOException {
        if (User instanceof Basic) {
            int id = ((Basic) User).getId();
            if (!basics.contains(id)) {
                System.out.println("Erro Utilizador nao registado na DB!");
                return;
            }
            FileWriter wr = new FileWriter(utilizadoresRemovidostxt, true);
            wr.write(((Basic) User).getId() + ", " + ((Basic) User).getNome() + "\n");
            wr.write(((Basic) User).getEtiquetas().size() + " etiqueta");
            for (Etiqueta e : ((Basic) User).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.close();
            basics.delete(id);
        } else if (User instanceof Admin) {
            int id = ((Admin) User).getId();
            if (!admins.contains(id)) {
                System.out.println("Erro Utilizador nao registado na DB!");
                return;
            }
            FileWriter wr = new FileWriter(utilizadoresRemovidostxt, true);
            wr.write(((Admin) User).getId() + ", " + ((Admin) User).getNome() + "\n");
            wr.write(((Admin) User).getEtiquetas().size() + " etiqueta");
            for (Etiqueta e : ((Admin) User).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.close();
            admins.delete(id);
        }
    }

    /**
     * Lista todas os Utilizadores do tipo Basic que foram inseridas na ST basics;
     */
    public static void listarBasicUsers() {
        System.out.println("Basic Users: \n");
        for (Integer i : basics.keys()) {
            basics.get(i).tostring();
        }
    }

    /**
     * Lista todas os Utilizadores do tipo Admin que foram inseridas na ST admins;
     */
    public static void listarAdminUsers() {
        System.out.println("Admin Users: \n");
        for (Integer i : admins.keys()) {
            admins.get(i).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * Le o ficheiro de nodes
     */
    public static void leituraFicheiroNodesTxt() {
        In in = new In(inputNodestxt);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Poi":
                        Nodes p = new Nodes(fields[0], fields[2], fields[1], Float.parseFloat(fields[3]), Float.parseFloat(fields[4]), fields[5]);
                        addNode(p);
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
                        addNode(cu);
                        break;
                    case "Cruzamento":
                        Nodes cr = new Nodes(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[4]);
                        addNode(cr);
                        break;
                    case "Entroncamento":
                        Nodes e = new Nodes(fields[0], fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]), fields[4]);
                        addNode(e);
                        break;
                }
            }
        }
    }


    public static void leituraFicheiroWaysTxt() {
        In in = new In(inputWaystxt);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Rua":
                        for(String s1: nodes.keys()){
                            if(nodes.get(s1).getNome().equals(fields[2])){
                                for(String s2: nodes.keys()){
                                    if(nodes.get(s2).getNome().equals(fields[3])){
                                        Ways r = new Ways(fields[0], fields[1], nodes.get(s1), nodes.get(s2));
                                        addWays(r);
                                    }
                                }
                            }
                        }
                        break;
                    case "Avenida":
                        ArrayList<Nodes> Nodes = new ArrayList<>();
                        Nodes.add(nodes.get(fields[2]));
                        Nodes.add(nodes.get(fields[3]));
                        Nodes.add(nodes.get(fields[4]));
                        Nodes.add(nodes.get(fields[5]));
                        Nodes.add(nodes.get(fields[6]));
                        Ways a = new Ways(fields[0], fields[1], Nodes);
                        addWays(a);
                        break;
                }
            }
        }
    }

    public static void leituraFicheiroUtilizadoresTxt() {
        In in = new In(utilizadorestxt);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Admin":
                        Admin a = new Admin(fields[0], fields[1], fields[2]);
                        addUser(a);
                        break;
                    case "Basic":
                        Basic b = new Basic(fields[0], fields[1], fields[2]);
                        addUser(b);
                        break;
                }
            }
        }
    }

    /**
     * Encontrar de todas as ST's, o objeto que tem o nome igual ao que é passado por parametros;
     *
     * @param nome nome a procurar nas ST's;
     */
    public static Object findUser(String nome) {
        for (int i : basics.keys()) {
            if (basics.get(i).getNome().equals(nome))
                return basics.get(i);
        }
        for (int i : admins.keys()) {
            if (admins.get(i).getNome().equals(nome))
                return admins.get(i);
        }
        System.out.println("User não está registado na DB!");
        return null;
    }

    /**
     * Escrita de toda a informação presente nas ST's Basics e Admins;
     */
    public static void escritaFicheiroTxtUtilizadores() throws IOException {
        FileWriter wr = new FileWriter(UtilizadoresFXtxt, true);
        int nUtilizadores = basics.size() + admins.size();
        wr.write(String.valueOf(nUtilizadores) + "\n");
        for (Integer i : basics.keys()) {
            wr.write(basics.get(i).getId() + ", " + basics.get(i).getNome() + ", " + basics.get(i).getTipo() + ", " +
                    basics.get(i).getEtiquetas().size());
            for (Etiqueta e : basics.get(i).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n");
        }
        for (Integer i : admins.keys()) {
            wr.write(admins.get(i).getId() + ", " + admins.get(i).getNome() + ", " + admins.get(i).getTipo() + ", " +
                    admins.get(i).getEtiquetas().size());
            for (Etiqueta e : admins.get(i).getEtiquetas()) {
                wr.write(e.getId() + ", " + e.getDescricao());
            }
            wr.write("\n");
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Pois;
     */
    public static void escritaFicheiroTxtNodes() throws IOException {

        FileWriter wr = new FileWriter(NodesFXtxt, true);
        int nNodes = nodes.size();
        wr.write(nNodes + "\n");
        for (String s : nodes.keys()) {
            if (nodes.get(s).getTipo().equals("Poi")) {
                wr.write(nodes.get(s).getNome() + ", " + nodes.get(s).getTipo() + ", " + nodes.get(s).getTipoPoi() +
                        ", " + nodes.get(s).getGPS().getLatitude() + ", " + nodes.get(s).getGPS().getLongitude() + ";\n");
            } else if(nodes.get(s).getTipo().equals("Entroncamento") || nodes.get(s).getTipo().equals("Cruzamento")){
                wr.write(nodes.get(s).getNome() + ", " + nodes.get(s).getTipo() + ", " + nodes.get(s).getTipo() +
                        ", " + nodes.get(s).getGPS().getLatitude() + ", " + nodes.get(s).getGPS().getLongitude() + ";\n");
            }else{
                wr.write(nodes.get(s).getNome() + ", " + nodes.get(s).getTipo() + ", " + nodes.get(s).getTipo() +
                        ", " + nodes.get(s).getAGPS().size() + ": " + ";\n");
                for(int i = 0; i < nodes.get(s).getAGPS().size(); i++){
                    wr.write("\t\t"+nodes.get(s).getAGPS().get(i).getLatitude() + " | " + nodes.get(s).getAGPS().get(i).getLongitude() + ";\n");
                }
            }

            wr.write("\n\t NLogs: " + nodes.get(s).getLogs().size() + "\n");
            for (int i : nodes.get(s).getLogs().keys()) {
                wr.write("\t\t " +nodes.get(s).getLogs().get(i).toString());
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST Ruas;
     */
    public static void escritaFicheiroTxtWays() throws IOException {

        FileWriter wr = new FileWriter(waystxt, true);
        int wNodes = ways.size();
        wr.write(wNodes + "\n");
        for (String s : ways.keys()) {
            if(ways.get(s).getTipo().equals("Rua")){
                wr.write(ways.get(s).getId() + ", " + ways.get(s).getNome() + ", " +
                        ways.get(s).getNode1().toString() + ", " +
                        ways.get(s).getNode2().toString() + ";\n");
                wr.write("\n\t NEtiquetas: " + ways.get(s).getMyEtiqueta().size() + ":\n");
            }else {
                wr.write(ways.get(s).getId() + ", " + ways.get(s).getNome() + ";\n ");
                wr.write("\n\t NNodes: " + ways.get(s).getNodes().size() + ":\n");
                for(Nodes n : ways.get(s).getNodes()){
                    wr.write(n.toString());
                }
                wr.write("\n\t NNodes: " + ways.get(s).getNodes().size() + ":\n");
            }
            for (Etiqueta e : ways.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao() + ";\n ");
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas ST ligacoes;
     */
    public static void escritaFicheiroTxtligacoes() throws IOException {

        FileWriter wr = new FileWriter(LigacoesFXtxt, true);
        int wNodes = ligacoes.size();
        wr.write(wNodes + "\n");
        for (Integer s : ligacoes.keys()) {

                wr.write(ligacoes.get(s).getIdl() + ", " + ligacoes.get(s).getNode1L() + ", " +
                        ligacoes.get(s).getNode2L() + ";\n");
                wr.write("\n\t NEtiquetas: " + ligacoes.get(s).getMyEtiqueta().size() + ":\n");

            for (Etiqueta e : ligacoes.get(s).getMyEtiqueta()) {
                wr.write(e.getId() + ", " + e.getDescricao() + ";\n ");
            }
        }
        wr.close();
    }

    /**
     * Escrita de toda a informação presente nas RedBlack Etiquetas
     */
    public static void escritaFicheiroTxtEtiquetas() throws IOException {
        FileWriter wr = new FileWriter(etiquetasFXtxt, true);
        wr.write(etiquetas.size() + "\n");
        for (String nome : etiquetas.keys()) {
            wr.write(etiquetas.get(nome).getId() + ", " + etiquetas.get(nome).getNome() + ", " +
                    etiquetas.get(nome).getNomeCriador() + ", " + etiquetas.get(nome).getDescricao() + ", " +
                    etiquetas.get(nome).getGPS().getLatitude() + ", " + etiquetas.get(nome).getGPS().getLongitude() + "\n");
        }
        wr.close();
    }

    public static void escritaFicheiroBinUtilizadores() throws IOException {

        FileOutputStream file = new FileOutputStream(UtilizadoresFXbin);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(file));

        dos.writeInt(basics.size() + admins.size());
        for(Integer i : basics.keys()){
            dos.writeUTF(basics.get(i).getId() + ", " + basics.get(i).getNome() + ", " + basics.get(i).getTipo() + ", " +
                    basics.get(i).getEtiquetas().size());
            for(Etiqueta e : basics.get(i).getEtiquetas()){
                dos.writeUTF(e.getId() + ", " + e.getDescricao());
            }
        }
        for(Integer i : admins.keys()) {
            dos.writeUTF(admins.get(i).getId() + ", " + admins.get(i).getNome() + ", " + admins.get(i).getTipo() + ", " +
                    admins.get(i).getEtiquetas().size());
            for (Etiqueta e : admins.get(i).getEtiquetas()) {
                dos.writeUTF(e.getId() + ", " + e.getDescricao());
            }
        }
        dos.close();
        file.close();
    }

    public static void escritaFicheiroBinNodes() throws IOException {

        FileOutputStream file = new FileOutputStream(NodesFXbin);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(file));

        dos.writeInt(nodes.size());
        for(String n: nodes.keys()){
           if(nodes.get(n).getTipo().equals("Poi")){
               dos.writeUTF(nodes.get(n).getNome() + ", " + nodes.get(n).getTipo() + ", " + nodes.get(n).getTipoPoi() +
                       ", " + nodes.get(n).getGPS().getLatitude() + ", " + nodes.get(n).getGPS().getLongitude() + ";\n");
           }else if(nodes.get(n).getTipo().equals("Entroncamento") || nodes.get(n).getTipo().equals("Cruzamento")){
               dos.writeUTF(nodes.get(n).getNome() + ", " + nodes.get(n).getTipo() + ", " + nodes.get(n).getTipo() +
                       ", " + nodes.get(n).getGPS().getLatitude() + ", " + nodes.get(n).getGPS().getLongitude() + ";\n");
           } else {
               dos.writeUTF(nodes.get(n).getNome() + ", " + nodes.get(n).getTipo() + ", " + nodes.get(n).getTipo() +
                       ", " + nodes.get(n).getAGPS().size() + ": " + ";\n");
               for(int i = 0; i < nodes.get(n).getAGPS().size(); i++){
                   dos.writeUTF("\t\t"+nodes.get(n).getAGPS().get(i).getLatitude() + " | " + nodes.get(n).getAGPS().get(i).getLongitude() + ";\n");
               }
               dos.writeUTF("\n\t NLogs: " + nodes.get(n).getLogs().size() + "\n");
               for (int i : nodes.get(n).getLogs().keys()) {
                   dos.writeUTF("\t\t " +nodes.get(n).getLogs().get(i).toString());
               }
           }
        }


        dos.close();
        file.close();
    }

    public static void escritaFicheiroBinligacoes() throws IOException {

        FileOutputStream file = new FileOutputStream(LigacoesFXbin);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(file));

        dos.writeInt(ligacoes.size());
        for (Integer s : ligacoes.keys()) {

            dos.writeUTF(ligacoes.get(s).getIdl() + ", " + ligacoes.get(s).getNode1L() + ", " +
                    ligacoes.get(s).getNode2L() + ";\n");
            dos.writeUTF("\n\t NEtiquetas: " + ligacoes.get(s).getMyEtiqueta().size() + ":\n");

            for (Etiqueta e : ligacoes.get(s).getMyEtiqueta()) {
                dos.writeUTF(e.getId() + ", " + e.getDescricao() + ";\n ");
            }
        }
        dos.close();
        file.close();
    }

    public static void escritaFicheiroBinEtiqueta() throws IOException {
        FileOutputStream file = new FileOutputStream(EtiquetasFXbin);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(file));

        dos.writeInt(etiquetas.size());
        for (String nome : etiquetas.keys()) {
            dos.writeUTF(etiquetas.get(nome).getId() + ", " + etiquetas.get(nome).getNome() + ", " +
                    etiquetas.get(nome).getNomeCriador() + ", " + etiquetas.get(nome).getDescricao() + ", " +
                    etiquetas.get(nome).getGPS().getLatitude() + ", " + etiquetas.get(nome).getGPS().getLongitude() + "\n");
        }
        dos.close();
        file.close();
    }


    /**
     * Função percorre todas os objetos do tipo Nodes presentes na ST de Nodes, encontrando as
     * nodes que têm a mesma data que a pedida por parametro, guarda-a no ArrayList de Nodes
     * retornando-o no final;
     *
     * @param data data condicional para obter os Nodes presentes na mesma;
     * @return ArrayList com os Objetos Node presentes na data;
     */
    public static ArrayList<Nodes> getNodesInData(Date data) {
        ArrayList<Nodes> nodesInData = new ArrayList<>();
        for (String s : nodes.keys()) {
            for (Integer log : nodes.get(s).getLogs().keys()) {
                if (nodes.get(s).getLogs().get(log).getDate().equals(data)) {
                    nodesInData.add(nodes.get(s));
                }
            }
        }
        return nodesInData;
    }



    /*---------------------------------------------------------------------------------------------------------------*/

    /*  --- PESQUISAS ---  */

    /**
     * Lista todas os Utilizadores que visitaram uma determinado Poi;
     *
     * @param nodeVisitado Objeto do tipo Poi;
     */
    public static void listarUtilizadoresPoi(Nodes nodeVisitado) {
        int k = 0;
        System.out.println("Utilizadores que visitaram o Poi ( " + nodeVisitado.getNome() + " ):");
        for (int i : basics.keys()) {
            if (basics.get(i).getNodesVisitados().contains(nodeVisitado)) {
                basics.get(i).tostring();
                k++;
            }
        }
        for (int i : admins.keys()) {
            if (admins.get(i).getNodesVisitados().contains(nodeVisitado)) {
                admins.get(i).tostring();
                k++;
            }
        }
        if (k == 0) System.out.println("Poi ainda não foi visitada por nenhum Utilizador!");
    }

    /**
     * Listar o Top 5 de utilizadores que visitaram mais Caches num determinada intervalo de tempo;
     *
     * @param dataInicio Objeto do tipo Date;
     * @param dataFim    Objeto do tipo Date;
     */
    public static void topUtilizadores(Date dataInicio, Date dataFim) {
        int count = 0;
        Object[] UserId = new Object[5];
        Integer[] UserCount = new Integer[5];
        System.out.println("Top Utilizadores com mais Nodes Visitados de '" +
                dataInicio.getYear() + "/" + dataInicio.getMonth() + "/" + dataInicio.getDate() +
                "' a '" + dataFim.getYear() + "/" + dataFim.getMonth() + "/" + dataFim.getDate() + "' : ");
        for (int i : basics.keys()) {
            for (Nodes j : basics.get(i).getNodesVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (basics.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) >= 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) <= 0) {
                        count++;
                    }
                }
            }
            int pos = 0;
            for (int k = 0; k < UserCount.length; k++) {
                if (UserCount[k] == null && UserId[k] == null) {
                    UserId[k] = basics.get(i);
                    UserCount[k] = count;
                    break;
                }
                if (UserCount[k] < count && UserCount[k] != null) {
                    pos = k;
                } else if ((k + 1 == UserCount.length)) {
                    UserCount[pos] = count;
                    UserId[pos] = basics.get(i);
                    break;
                }
            }
            count = 0;
        }
        for (int i : admins.keys()) {
            for (Nodes j : admins.get(i).getNodesVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (admins.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) >= 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) <= 0) {
                        count++;
                    }
                }
            }
            int pos = 0;
            for (int k = 0; k < UserCount.length; k++) {
                if (UserCount[k] == null && UserId[k] == null) {
                    UserId[k] = admins.get(i);
                    UserCount[k] = count;
                    break;
                }
                if (UserCount[k] < count && UserCount[k] != null) pos = k;
                else if ((k + 1 == UserCount.length)) {
                    UserCount[pos] = count;
                    UserId[pos] = admins.get(i);
                    break;
                }
            }
            count = 0;
        }
        int b = 1;
        for (int i : basics.keys()) {
            for (Nodes j : basics.get(i).getNodesVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (basics.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) >= 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) <= 0 && b ==1) {
                                    System.out.println(basics.get(i).getId() + ", " + basics.get(i).getNome());
                                    b = 0;
                        }
                    }
                }
            b++;
            }

        int a = 1;
        for (int i : admins.keys()) {
            for (Nodes j : admins.get(i).getNodesVisitados()) {
                for (Integer log : j.getLogs().keys()) {
                    if (admins.get(i).getId() == j.getLogs().get(log).getUserId()
                            && j.getLogs().get(log).getDate().compareTo(dataInicio) >= 0
                            && j.getLogs().get(log).getDate().compareTo(dataFim) <= 0 && a == 1) {
                            System.out.println(admins.get(i).getId() + ", " + admins.get(i).getNome());
                            a = 0;
                    }
                }
            }
            a++;
            }
    }

    /*---------------------------------------------------------------------------------------------------------------*/



    /*  ADD / EDITAR / REMOVER / LISTAR --Etiquetas--  */

    /**
     * Remover um Etiquetas da DB
     */
    public static void removerEtiquetas(Etiqueta et) throws IOException {
        if (etiquetas.contains(et.getNome())) {
            FileWriter wr = new FileWriter(etiquetasRemovidostxt, true);
            wr.write(et.getId() + ", " + et.getNome() + ", " + et.getNomeCriador() + ", " +
                    et.getDescricao() + ", " + et.getGPS().getLatitude() + ", " + et.getGPS().getLongitude() + "\n");

            wr.close();
            etiquetas.delete(et.getNome());
        } else {
            System.out.println("Erro Etiqueta nao resgistada na DB!");
        }
    }

    public static void listarEtiquetas() {
        System.out.println("Etiquetas: \n");
        for (String s : etiquetas.keys()) {
            etiquetas.get(s).tostring();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException, ParseException {
        leituraFicheiroNodesTxt();
        leituraFicheiroWaysTxt();
        leituraFicheiroUtilizadoresTxt();

        /*------------------------------------------------------------------------------------------------------------*/
        //listarNodes();
        //listarWays();
        //listarAdminUsers();

        /*------------------------------------------------------------------------------------------------------------*/
        /*basics.get(0).createEtiquetasNodes(etiquetas,nodes.get("p11"), "incendio","e3");
        basics.get(1).createEtiquetasNodes(etiquetas,nodes.get("p11"), "incendio","e4");
        admins.get(2).createEtiquetasNodes(etiquetas,nodes.get("p1"), "incendio","e1");
        basics.get(3).createEtiquetasNodes(etiquetas,nodes.get("p11"), "incendio","e5");
        basics.get(4).createEtiquetasNodes(etiquetas,nodes.get("p11"), "incendio","e6");
        admins.get(5).createEtiquetasNodes(etiquetas,nodes.get("p11"), "incendio","e2");*/

        //listarEtiquetas();
        /*------------------------------------------------------------------------------------------------------------*/
        //Os utilizadores só podem visitar Pois
        //Date D = new Date(2022,05,25);
        //basics.get(0).visitarPoi(nodes.get("p1"), null);
        //basics.get(0).listarPoisVisitados(D);
        //basics.get(1).visitarPoi(nodes.get("p1"), null);
        //basics.get(1).listarPoisVisitados(D);
        //basics.get(3).visitarPoi(nodes.get("p1"), null);
       // basics.get(3).visitarPoi(nodes.get("p2"), null);
        //basics.get(3).visitarPoi(nodes.get("p3"), null);
        //basics.get(3).visitarPoi(nodes.get("p4"), null);
        //basics.get(3).visitarPoi(nodes.get("p5"), null);
        //basics.get(3).listarPoisVisitados(D);
        //basics.get(4).visitarPoi(nodes.get("p1"), null);
        //basics.get(4).listarPoisVisitados(D);
        //basics.get(4).listarPoisNaoVisitados(D,nodes);

        /*------------------------------------------------------------------------------------------------------------*/

        //Date DI = new Date(2022,05,24);
        //Date DF = new Date(2022,05,30);
       // listarUtilizadoresPoi(nodes.get("p1"));
            //topUtilizadores(DI,DF);
        //nodes.get("p1").listarlogs();
        //nodes.get("p1").listarlogs();
        /*------------------------------------------------------------------------------------------------------------*/

        //b1.visitarPoi(p1, null);
        //.visitarPoi(p8, null);
        //String date_string = "08-05-2022";
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Date date = formatter.parse(date_string);
        //System.out.println("Date value: "+date);
        //b1.listarPoisVisitados(date);
        //listarAdminUsers();
        //listarEtiquetas();

    }
}
