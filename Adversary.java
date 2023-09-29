public class Adversary {
    private String nome;
    private int pv;
    private int dano;
    private int defesa;
    private int agilidade;
    private boolean defesaDobrada;

    public Adversary(String nome, int pv, int dano, int defesa, int agilidade) {
        this.nome = nome;
        this.pv = pv;
        this.dano = dano;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.defesaDobrada = false; // Por padrão, a defesa dobrada está desativada.
    }

    public String getNome() {
        return nome;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getDano() {
        return dano;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public boolean isDefesaDobrada() {
        return defesaDobrada;
    }

    public void setDefesaDobrada(boolean defesaDobrada) {
        this.defesaDobrada = defesaDobrada;
    }

    public int getPV() {
        return 0;
    }

    public void setPV(int i) {
    }
}
