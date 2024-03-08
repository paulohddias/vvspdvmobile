package devandroid.paulo.appvvspdvmobile.model;

public class Combustivel {

    private int idCombustivel;
    private String nomeCombustivel;
    private double precoCombustivel;
    private String recomendacao;

    public int getIdCombustivel() {
        return idCombustivel;
    }

    public void setIdCombustivel(int idCombustivel) {
        this.idCombustivel = idCombustivel;
    }

    public String getNomeCombustivel() {
        return nomeCombustivel;
    }

    public void setNomeCombustivel(String nomeCombustivel) {
        this.nomeCombustivel = nomeCombustivel;
    }

    public double getPrecoCombustivel() {
        return precoCombustivel;
    }

    public void setPrecoCombustivel(double precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    @Override
    public String toString() {
        return "Combustivel{" +
                "idCombustivel=" + idCombustivel +
                ", nomeCombustivel='" + nomeCombustivel + '\'' +
                ", precoCombustivel=" + precoCombustivel +
                ", recomendacao='" + recomendacao + '\'' +
                '}';
    }
}
