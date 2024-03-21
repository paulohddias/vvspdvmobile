package devandroid.paulo.appvvspdvmobile.model;

public class VeiculoMarca {

    private int codigo;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "VeiculoMarca{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
    }

}
