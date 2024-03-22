package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;

public class VeiculoAno {

    @SerializedName("codigo")
    private String codigo;
    @SerializedName("nome")
    private String nome;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
