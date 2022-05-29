package NoWarPolis;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public abstract class User {

    private static int countUser = 0;

    private int Id;

    private String Tipo;

    private String Nome;

    private String veiculo;

    private Date date;

    private ArrayList<Nodes> nodesVisitados = new ArrayList<>();

    private ArrayList<Etiqueta> Etiquetas = new ArrayList<>();


    /*---------------------------------------------------------------------------------------------------------------*/

    public User(String tipo, String nome, String veiculo) {
        this.Id = countUser++;
        this.Tipo = tipo;
        Nome = nome;
        this.veiculo = veiculo;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public ArrayList<Nodes> getNodesVisitados() {return nodesVisitados;}

    public void setNodesVisitados(ArrayList<Nodes> poisVisitados) {
        this.nodesVisitados = poisVisitados;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return Etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        Etiquetas = etiquetas;
    }


    public void tostring() {
        System.out.println("Utilizador {\n" + "\tID=" + Id + ", Nome: " + Nome + ";\n" +
                "\tEtiquetas do Utilizador:");
        for (Etiqueta e : this.getEtiquetas()) {

            System.out.println("\t\t" + e.getId() + ", " + e.getDescricao() + ";");
        }
        if (this.getEtiquetas().isEmpty()) {
            System.out.println("\t\tUtilizador nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- MANIPULAÇAO REDBLACK ETIQUETAS --
     */

    public void createEtiquetasNodes(RedBlackBST<String, Etiqueta> etiquetas, Nodes n, String descricao, String nome) {
        if (n.getTipo().equals("Poi")) {
            Etiqueta ET = new Etiqueta(nome, this.getNome(), descricao, n.getGPS().getLatitude(),
                    n.getGPS().getLongitude());
            if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
                System.out.println("Erro, Etiqueta ja existe na DB!");
                return;
            }
            n.getMyEtiqueta().add(ET);
            etiquetas.put(nome, ET);
        }
    }

    public void createEtiquetasWays(RedBlackBST<String, Etiqueta> etiquetas, Ways w, String descricao, String nome) {
        Etiqueta ET = new Etiqueta(nome, this.getNome(), descricao);
        if (etiquetas.contains(nome) || this.getEtiquetas().contains(ET)) {
            System.out.println("Erro, Etiqueta ja existe na DB!");
            return;
        }
        w.getMyEtiqueta().add(ET);
        etiquetas.put(nome, ET);

    }

    /*---------------------------------------------------------------------------------------------------------------*/


    /**
     * -- METODOS ESPECIFICOS --
     */

    /**
     * Simula a Visita de uma Cache por parte de um utilizador, como tal é possivel a troca
     * de Items com a Cache, a escrita de uma mensagem no Log criado ao ser visitada a Cache
     *
     * @param n        Objeto do tipo Nodes a visitar/interagir pelo Utilizador;
     * @param Mensagem Mensagem que será escrita no log (null se não quiser deixar mensagem);
     */

    public void visitarPoi(Nodes n, String Mensagem) {

        if (n.getVeiculo().equals(this.getVeiculo()) || n.getVeiculo().equals("todos") || this.getVeiculo().equals("todos")) {
            if (n.getTipo().equals("Poi")) {
                this.getNodesVisitados().add(n);
                LocalDateTime date = LocalDateTime.now();
                Date dateAtual = new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
                this.date = dateAtual;
                String info = this.Nome;
                Integer id = this.getId();
                Log log = new Log(this.date, info, Mensagem,id);
                log.setNode(n.getNome());
                n.getLogs().put(log.getId(), log);
            } else {
                System.out.println("O Node selecionado não é um Poi!\n");
            }
        } else {
            System.out.println("Não pode visitar o Poi com o veiculo : " + this.getVeiculo());
        }


    }

    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- PESQUISAS --
     */

    /**
     * Listar todas as Caches visitadas por um determinado utilizador;
     *
     * @param Data Data restringe a os Pois a serem imprimidas
     */

    public void listarPoisVisitados(Date Data) {
        if (this.getNodesVisitados().isEmpty()) {
            System.out.println("Utilizador nao visitou um Poi!");
        }
        if (Data != null) {
            for (Nodes n : this.getNodesVisitados()) {
                    if (this.date.equals(Data)) {
                        System.out.println(n.getId() + ", " + n.getNome() + ", " + n.getTipo() + ", " + n.getTipoPoi() + ", " + n.getGPS().getLatitude() + ", " +
                                n.getGPS().getLongitude() + ", " + this.getNome());
                    }
                }

        }
    }

    /**
     * Listar todas as Caches nao visitadas por um determinado utilizador;
     *
     * @param data Data restringe a os Pois a serem imprimidas
     */

    public void listarPoisNaoVisitados(Date data, SeparateChainingHashST<String, Nodes> pois) {
        for (String s : pois.keys()) {
            if (!this.getNodesVisitados().contains(pois.get(s))) {
                if (data != null) {
                        if (this.date.equals(data)) {
                            System.out.println(pois.get(s).getId() + ", " + pois.get(s).getTipo() + ", " +
                                    pois.get(s).getGPS().getLatitude() + ", " + pois.get(s).getGPS().getLongitude());
                        }

                }
            }
        }
    }
}