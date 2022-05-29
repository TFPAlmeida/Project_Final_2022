package NoWarPolis;

import java.util.ArrayList;

public class Ways {

    private static int count = 0;
    private int Id;
    private String Tipo;
    private String Nome;
    private ArrayList<Etiqueta> myEtiqueta = new ArrayList<>();
    private Nodes node1;
    private Nodes node2;
    private ArrayList<Nodes> nodes = new ArrayList<>();

    /*---------------------------------------------------------------------------------------------------------------*/

    private static int countlig = 0;
    private int idl;
    private String node1L;
    private String node2L;
    private float distancia;
    private float tempo;

    public Ways(String node1, String node2, float distance, float tempo){
        this.idl = countlig++;
        this.node1L = node1;
        this.node2L = node2;
        this.distancia = distance;
        this.tempo = tempo;
    }

    public int getIdl() {return idl;}

    public void setIdl(int idl) {this.idl = idl;}

    public String getNode1L() {return node1L;}

    public void setNode1L(String node1L) {this.node1L = node1L;}

    public String getNode2L() {return node2L;}

    public void setNode2L(String node2L) {this.node2L = node2L;}

    public float getDistancia() {return distancia;}

    public void setDistancia(float distancia) {this.distancia = distancia;}

    public float getTempo() {return tempo;}

    public void setTempo(float tempo) {this.tempo = tempo;}

    /*---------------------------------------------------------------------------------------------------------------*/

    public Ways(String tipo, String nome, Nodes node1, Nodes node2) {
        //Rua
        Id = count++;
        Tipo = tipo;
        Nome = nome;
        this.node1 = node1;
        this.node2 = node2;
    }

    public Ways(String tipo, String nome, ArrayList<Nodes> nodes) {
        //Avenida
        Id = count++;
        Tipo = tipo;
        Nome = nome;
        this.nodes = nodes;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public static int getCount() {return count;}

    public static void setCount(int count) {Ways.count = count;}

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getTipo() {return Tipo;}

    public void setTipo(String tipo) {Tipo = tipo;}

    public String getNome() {return Nome;}

    public void setNome(String nome) {Nome = nome;}

    public ArrayList<Etiqueta> getMyEtiqueta() {return myEtiqueta;}

    public void setMyEtiqueta(ArrayList<Etiqueta> myEtiqueta) {this.myEtiqueta = myEtiqueta;}

    public Nodes getNode1() {return node1;}

    public void setNode1(Nodes node1) {this.node1 = node1;}

    public Nodes getNode2() {return node2;}

    public void setNode2(Nodes node2) {this.node2 = node2;}

    public ArrayList<Nodes> getNodes() {return nodes;}

    public void setNodes(ArrayList<Nodes> nodes) {this.nodes = nodes;}

    /*---------------------------------------------------------------------------------------------------------------*/

    public void tostring1() {
        System.out.println("Rua {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome() + ", Node1: " + node1.toString() + ", Node2: " + node2.toString() + ";\n" +
                "\n\tEtiquetas da Rua:");
        for (Etiqueta e : this.getMyEtiqueta()) {

            System.out.println("\t\t" + e.getId() + ", " + e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tRua nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }

    public void tostring2() {
        System.out.println("Avenida {\n" + "\tID=" + this.getId() + ", Nome: " + this.getNome() +
                "\n\tNodes da Avenida:");
        for(Nodes n : this.getNodes()){
            System.out.println("\t\t"+n.toString());
        }
        System.out.println("\n\tEtiquetas da Avenida:");
        for (Etiqueta e : this.getMyEtiqueta()) {

            System.out.println("\t\t" + e.getId() + ", " + e.getDescricao() + ";");
        }
        if (this.getMyEtiqueta().isEmpty()) {
            System.out.println("\t\tAvenida nao tem Etiquetas publicadas!");
            System.out.println("\n}\n");
            return;
        }
        System.out.println("\n}\n");
    }
}
