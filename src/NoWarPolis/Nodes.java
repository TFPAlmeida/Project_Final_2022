package NoWarPolis;

import edu.princeton.cs.algs4.RedBlackBST;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Nodes implements Serializable{

    private static int count = 0;
    private int Id;
    private String Tipo;
    private String TipoPoi;
    private String Nome;
    private Point GPS;
    private ArrayList<Point> AGPS = new ArrayList<>();
    private String Veiculo;
    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();
    private RedBlackBST<Integer, Log> logs = new RedBlackBST<>();

    private Float latitude;

    private Float longitude;



    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }



    public Nodes(String tipo, String nome, float latitude, float longitude, String veiculo) {
        //Entroncamento ou Cruzamento
        Id = count++;
        Tipo = tipo;
        Nome = nome;
        this.GPS = new Point(latitude, longitude);
        Veiculo = veiculo;
    }

    public Nodes(String tipo, String tipoPoi, String nome, float latitude, float longitude, String veiculo) {
        //Poi
        Id = count++;
        Tipo = tipo;
        TipoPoi = tipoPoi;
        Nome = nome;
        this.GPS = new Point(latitude, longitude);
        Veiculo = veiculo;
    }

    public Nodes(String tipo, String nome, ArrayList<Point> AGPS, String veiculo) {
        //Curva
        Id = count++;
        Tipo = tipo;
        Nome = nome;
        this.AGPS = AGPS;
        Veiculo = veiculo;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public static int getCount() {return count;}

    public static void setCount(int count) {Nodes.count = count;}

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getTipo() {return Tipo;}

    public void setTipo(String tipo) {Tipo = tipo;}

    public String getTipoPoi() {return TipoPoi;}

    public void setTipoPoi(String tipoPoi) {TipoPoi = tipoPoi;}

    public String getNome() {return Nome;}

    public void setNome(String nome) {Nome = nome;}

    public Point getGPS() {return GPS;}

    public void setGPS(Point GPS) {this.GPS = GPS;}

    public ArrayList<Point> getAGPS() {return AGPS;}

    public void setAGPS(ArrayList<Point> AGPS) {this.AGPS = AGPS;}

    public String getVeiculo() {return Veiculo;}

    public void setVeiculo(String veiculo) {Veiculo = veiculo;}

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    public RedBlackBST<Integer, Log> getLogs() {return logs;}

    public void setLogs(RedBlackBST<Integer, Log> logs) {this.logs = logs;}

    @Override
    public String toString() {
        return "Node{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                '}';
    }

    public void tostring1() {
        //Entroncamento ou cruzamento
        System.out.println(Tipo +" {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Tipo: " + Tipo +
                ", GPS: " + GPS.getLatitude() + " | " + GPS.getLongitude() + "\n\tEtiquetas do Node:");
        for (Etiqueta e : this.getMyEtiqueta()) {

            System.out.println("\t\t" + e.getId() + ", " +e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tNode nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

    public void tostring2() {
        //Poi
        System.out.println(Tipo + " {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Tipo: " + Tipo +
                ", TipoPoi: " + TipoPoi + ", GPS : " + GPS.getLatitude() + " | " + GPS.getLongitude() + "\n\tEtiquetas do Node:");
        for (Etiqueta e : this.getMyEtiqueta()) {

            System.out.println("\t\t" + e.getId() + ", " +e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tNode nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

    public void tostring3() {
        //Curva
        int x = AGPS.size();
        System.out.println(Tipo +" {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome()  + ", Tipo: " + Tipo +
                ", TipoPoi: " + TipoPoi + "\n\tGPSI: " + AGPS.get(0).getLatitude() + " | " + AGPS.get(0).getLongitude() +
                "\n\tGPSF: " + AGPS.get(x - 1).getLatitude() + " | " + AGPS.get(x - 1).getLongitude() + "\n\tEtiquetas do Node: \n");
        for (Etiqueta e : this.getMyEtiqueta()) {
            System.out.println("\t\t" + e.getId() + ", " +e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tNode nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }
    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- METODOS ESPECIFICOS --
     */

    /**
     * Adiciona logs à RedBlackST de logs.
     *
     * @param log log que sera adicionado ao RedblackST
     */
    public void addLogs(Log log) {
        Integer id = log.getId();
        if (logs.contains(id)) {
            System.out.println("Erro, nao existe logs na cache");
            return;
        }
        logs.put(id, log);
    }

    /**
     * Edita/altera os valores do log.
     *
     * @param log      log a ser editado;
     * @param id       novo id;
     * @param date     nova data;
     * @param mensagem nova mensagem;
     */

    public void editarLog(Log log, int id, Date date, String mensagem) {
        Integer ID = log.getId();
        if (logs.contains(ID)) {
            logs.get(ID).setId(id);
            logs.get(ID).setDate(date);
            logs.get(ID).setMensagem(mensagem);
        } else System.out.println("ERRO! Nao existe o Log com o Id inserido!");
    }

    /**
     * Remove o log desejado.
     *
     * @param log       Log a ser removido.
     */

    public void removelogs(Log log) {
        if (logs.contains(log.getId())) {
            logs.delete(log.getId());
        } else System.out.println("Erro, nao existe o log");
    }

    /**
     * Lista todos os logs, no ecra, existentes na RedBlackST.
     */

    public void listarlogs() {
        System.out.println("Logs:");
        for (Integer log : logs.keys()) {
            System.out.println("Id:" + logs.get(log).getId() + " - " + logs.get(log).toString());
        }
    }

    /**
     * Lista todos os Etiquetas no ecra.
     */

    public void listarEtiquetas() {
        System.out.print("Etiquetas:  ");
        for (Etiqueta i : this.getMyEtiqueta()) {
            System.out.print(i.getDescricao() + "; ");
        }
        System.out.println("\n");
    }


}
