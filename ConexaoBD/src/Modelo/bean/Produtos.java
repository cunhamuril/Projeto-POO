package Modelo.bean;

/**
 *
 * @author muril
 */
public class Produtos {

    private int id;
    private String nome;
    private String qtd;
    private String fab;
    private String lote;
    private String obs;

    public Produtos(int id,
            String nome,
            String qtd,
            String fab,
            String lote,
            String obs) {
        setId(id);
        setNome(nome);
        setQtd(qtd);
        setFab(fab);
        setLote(lote);
        setObs(obs);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
