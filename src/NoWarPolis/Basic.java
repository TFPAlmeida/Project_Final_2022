package NoWarPolis;

import edu.princeton.cs.algs4.RedBlackBST;

public class Basic extends Admin {
    public Basic(String tipo, String nome, String veiculo) {
        super(tipo, nome, veiculo);
    }





    /*---------------------------------------------------------------------------------------------------------------*/

    /**
     * -- METODOS ESPECIFICOS --
     */

    /**
     * Utilizador básico não pode criar Etiquetas, então esta função vai sobrepor a mesma da superclass.
     */

    @Override
    public void createEtiquetasNodes(RedBlackBST<String, Etiqueta> Etiquetas, Nodes n, String descricao, String nome){
        System.out.println("ERRO! { \n\tUtilizadores Basic não podem criar Etiquetas!\n\t" +
                this.getId() + ", " + this.getNome());
    }

    @Override
    public void createEtiquetasWays(RedBlackBST<String, Etiqueta> Etiquetas, Ways w, String descricao, String nome){
        System.out.println("ERRO! { \n\tUtilizadores Basic não podem criar Etiquetas!\n\t" +
                this.getId() + ", " + this.getNome());
    }




}