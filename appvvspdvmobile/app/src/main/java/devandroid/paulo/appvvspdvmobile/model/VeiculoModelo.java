package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;
public class VeiculoModelo {

    @SerializedName("codigo")
    private int codigo;
    @SerializedName("nome")
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
        return nome;
    }
}
