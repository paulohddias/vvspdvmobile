package devandroid.paulo.appvvspdvmobile.interfaces;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.model.ApiResponseVeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VeiculoMarcaService {

    @GET("{tipoVeiculo}/marcas")
    Call<List<VeiculoMarca>> buscarVeiculoMarcas(@Path("tipoVeiculo") String tipoVeiculo);

    @GET("{tipoVeiculo}/marcas/{codMarcaVeiculo}/modelos")
    Call<List<VeiculoMarca>> buscarVeiculoMarcasByTipo(
            @Path("tipoVeiculo") String tipoVeiculo,
            @Path("codMarcaVeiculo") int codMarcaVeiculo
    );

    @GET("{tipoVeiculo}/marcas/{codMarcaVeiculo}/modelos")
    Call<ApiResponseVeiculoModelo> getModelosAndAnos(@Path("tipoVeiculo") String tipoVeiculo, @Path("codMarcaVeiculo") String codMarcaVeiculo);

}
