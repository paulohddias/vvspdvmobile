package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;

public class VeiculoByFipe {

    @SerializedName("TipoVeiculo")
    private String tipoVeiculo;

    @SerializedName("Valor")
    private String valor;

    @SerializedName("Marca")
    private String marca;

    @SerializedName("Modelo")
    private String modelo;

    @SerializedName("AnoModelo")
    private String anoModelo;

    @SerializedName("Combustivel")
    private String combustivel;

    @SerializedName("CodigoFipe")
    private String codigoFipe;

    @SerializedName("MesReferencia")
    private String mesReferencia;

    @SerializedName("SiglaCombustivel")
    private String siglaCombustivel;

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        this.codigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        this.siglaCombustivel = siglaCombustivel;
    }

    @Override
    public String toString() {
        return "VeiculoByFipe{" +
                "tipoVeiculo=" + tipoVeiculo +
                ", valor=" + valor +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoModelo='" + anoModelo + '\'' +
                ", Combustivel='" + combustivel + '\'' +
                ", codigoFipe='" + codigoFipe + '\'' +
                ", mesReferencia='" + mesReferencia + '\'' +
                ", siglaCombustivel='" + siglaCombustivel + '\'' +
                '}';
    }
}
