package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DadosCnpj {

    @SerializedName("uf")
    private String uf;

    @SerializedName("cep")
    private String cep;

    @SerializedName("qsa")
    private List<Qsa>qsa;

    @SerializedName("cnpj")
    private String cnpj;

    @SerializedName("porte")
    private String porte;

    @SerializedName("bairro")
    private String bairro;

    @SerializedName("numero")
    private String numero;

    @SerializedName("municipio")
    private String municipio;

    @SerializedName("logradouro")
    private String logradouro;

    @SerializedName("cnae_fiscal")
    private String cnaeFiscal;

    @SerializedName("complemento")
    private String complemento;

    @SerializedName("razao_social")
    private String razaoSocial;

    @SerializedName("nome_fantasia")
    private String nomeFantasia;

    @SerializedName("capital_social")
    private String capitalSocial;

    @SerializedName("ddd_telefone_1")
    private String dddTelefone_1;

    @SerializedName("natureza_juridica")
    private String naturezaJuridica;

    @SerializedName("cnae_fiscal_descricao")
    private String cnaeFiscalDescricao;

    @SerializedName("codigo_municipio_ibge")
    private String codigoMunicipioIbge;

    @SerializedName("data_inicio_atividade")
    private String dataInicioAtividade;

    @SerializedName("descricao_situacao_cadastral")
    private String descricaoSituacaoCadastral;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Qsa> getQsa() {
        return qsa;
    }

    public void setQsa(List<Qsa> qsa) {
        this.qsa = qsa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCnaeFiscal() {
        return cnaeFiscal;
    }

    public void setCnaeFiscal(String cnaeFiscal) {
        this.cnaeFiscal = cnaeFiscal;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(String capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public String getDddTelefone_1() {
        return dddTelefone_1;
    }

    public void setDddTelefone_1(String dddTelefone_1) {
        this.dddTelefone_1 = dddTelefone_1;
    }

    public String getNaturezaJuridica() {
        return naturezaJuridica;
    }

    public void setNaturezaJuridica(String naturezaJuridica) {
        this.naturezaJuridica = naturezaJuridica;
    }

    public String getCnaeFiscalDescricao() {
        return cnaeFiscalDescricao;
    }

    public void setCnaeFiscalDescricao(String cnaeFiscalDescricao) {
        this.cnaeFiscalDescricao = cnaeFiscalDescricao;
    }

    public String getCodigoMunicipioIbge() {
        return codigoMunicipioIbge;
    }

    public void setCodigoMunicipioIbge(String codigoMunicipioIbge) {
        this.codigoMunicipioIbge = codigoMunicipioIbge;
    }

    public String getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(String dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public String getDescricaoSituacaoCadastral() {
        return descricaoSituacaoCadastral;
    }

    public void setDescricaoSituacaoCadastral(String descricaoSituacaoCadastral) {
        this.descricaoSituacaoCadastral = descricaoSituacaoCadastral;
    }

    @Override
    public String toString() {
        return "DadosCnpj{" +
                "sp='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", qsa=" + qsa +
                ", cnpj='" + cnpj + '\'' +
                ", porte='" + porte + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", municipio='" + municipio + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cnaeFiscal='" + cnaeFiscal + '\'' +
                ", complemento='" + complemento + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", capitalSocial='" + capitalSocial + '\'' +
                ", dddTelefone_1='" + dddTelefone_1 + '\'' +
                ", naturezaJuridica='" + naturezaJuridica + '\'' +
                ", cnaeFiscalDescricao='" + cnaeFiscalDescricao + '\'' +
                ", codigoMunicipioIbge='" + codigoMunicipioIbge + '\'' +
                ", dataInicioAtividade='" + dataInicioAtividade + '\'' +
                ", descricaoSituacaoCadastral='" + descricaoSituacaoCadastral + '\'' +
                '}';
    }
}
