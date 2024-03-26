package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;

public class Qsa {

    @SerializedName("nome_socio")
    private String NomeSocio;

    @SerializedName("cnpj_cpf_do_socio")
    private String CnpjCpfDoSocio;

    @SerializedName("qualificacao_socio")
    private String qualificacaoSocio;

    @SerializedName("data_entrada_sociedade")
    private String dataEntradaSociedade;

    public String getNomeSocio() {
        return NomeSocio;
    }

    public void setNomeSocio(String nomeSocio) {
        NomeSocio = nomeSocio;
    }

    public String getCnpjCpfDoSocio() {
        return CnpjCpfDoSocio;
    }

    public void setCnpjCpfDoSocio(String cnpjCpfDoSocio) {
        CnpjCpfDoSocio = cnpjCpfDoSocio;
    }

    public String getQualificacaoSocio() {
        return qualificacaoSocio;
    }

    public void setQualificacaoSocio(String qualificacaoSocio) {
        this.qualificacaoSocio = qualificacaoSocio;
    }

    public String getDataEntradaSociedade() {
        return dataEntradaSociedade;
    }

    public void setDataEntradaSociedade(String dataEntradaSociedade) {
        this.dataEntradaSociedade = dataEntradaSociedade;
    }

    @Override
    public String toString() {
        return "Qsa{" +
                "NomeSocio='" + NomeSocio + '\'' +
                ", CnpjCpfDoSocio='" + CnpjCpfDoSocio + '\'' +
                ", qualificacaoSocio='" + qualificacaoSocio + '\'' +
                ", dataEntradaSociedade='" + dataEntradaSociedade + '\'' +
                '}';
    }
}
