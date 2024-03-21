package devandroid.paulo.appvvspdvmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseVeiculoModelo {

    @SerializedName("modelos")
    private List<VeiculoModelo> veiculoModelos;
    @SerializedName("anos")
    private List<VeiculoAno> veiculoAnos;

    public List<VeiculoModelo> getModelos() {
        return veiculoModelos;
    }

    public void setModelos(List<VeiculoModelo> veiculoModelos) {
        this.veiculoModelos = veiculoModelos;
    }

    public List<VeiculoAno> getAnos() {
        return veiculoAnos;
    }

    public void setAnos(List<VeiculoAno> veiculoAnos) {
        this.veiculoAnos = veiculoAnos;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "modelos=" + veiculoModelos +
                ", anos=" + veiculoAnos +
                '}';
    }
}
