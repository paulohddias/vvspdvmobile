package devandroid.paulo.appvvspdvmobile.interfaces;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.model.ApiResponseVeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoAno;
import devandroid.paulo.appvvspdvmobile.model.VeiculoByFipe;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VeiculoMarcaService {

    @GET("{tipoVeiculo}/marcas")
    Call<List<VeiculoMarca>> getcarVeiculoMarcas(@Path("tipoVeiculo") String tipoVeiculo);

    @GET("{tipoVeiculo}/marcas/{codMarcaVeiculo}/modelos")
    Call<ApiResponseVeiculoModelo> getModelosAndAnos(@Path("tipoVeiculo") String tipoVeiculo,
                                                     @Path("codMarcaVeiculo") String codMarcaVeiculo
    );

    @GET("{tipoVeiculo}/marcas/{codMarcaVeiculo}/modelos/{codVeiculoModelo}/anos")
    Call<List<VeiculoAno>> getModelosAndAnos(@Path("tipoVeiculo") String tipoVeiculo,
                                       @Path("codMarcaVeiculo") String codMarcaVeiculo,
                                       @Path("codVeiculoModelo") String codVeiculoModelo
    );

    @GET("{tipoVeiculo}/marcas/{codMarcaVeiculo}/modelos/{codVeiculoModelo}/anos/{codVeiculoAno}")
    Call<VeiculoByFipe> getVeiculoByFipe(@Path("tipoVeiculo") String tipoVeiculo,
                                               @Path("codMarcaVeiculo") String codMarcaVeiculo,
                                               @Path("codVeiculoModelo") String codVeiculoModelo,
                                               @Path("codVeiculoAno") String codVeiculoAno
    );


}
