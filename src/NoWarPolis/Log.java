package NoWarPolis;


import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable{

    private static int count = 0;
    private int id;

    private String Node;

    private Date date;

    private String Info;

    private String Mensagem;

    private Integer userId;

    /*---------------------------------------------------------------------------------------------------------------*/

    public Log(Date date, String info, String mensagem, Integer userId) {
        id = count++;
        this.date = date;
        this.Info = info;
        Mensagem = mensagem;
        this.userId = userId;
    }

    /*---------------------------------------------------------------------------------------------------------------*/

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public String getInfo() {return Info;}

    public void setInfo(String info) {Info = info;}

    public String getMensagem() {return Mensagem;}

    public void setMensagem(String mensagem) {Mensagem = mensagem;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public String getNode() {return Node;}

    public void setNode(String node) {Node = node;}

    public String getData() {
        return date.getYear() + ", " + date.getMonth() + ", " + date.getDate() ;
    }

    public String getNodes() {
        for(String s : Base_Dados.nodes.keys())
            if(Base_Dados.nodes.get(s).getLogs().contains(this.getId()))
                return Base_Dados.nodes.get(s).getNome();

        return null;
    }

    @Override
    public String toString() {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + ", " +
                 this.Info +
                ", " + Mensagem + "\n";
    }
}