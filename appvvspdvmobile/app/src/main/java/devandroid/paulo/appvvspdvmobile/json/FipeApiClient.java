package devandroid.paulo.appvvspdvmobile.json;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.interfaces.VeiculoMarcaService;
import devandroid.paulo.appvvspdvmobile.model.ApiResponseVeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FipeApiClient {
    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";

    private Retrofit retrofit;
    private VeiculoMarcaService apiService;

    public FipeApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(VeiculoMarcaService.class);
    }

    public void fetchTipoVeiculos(String tipoVeiculo, Callback<List<VeiculoMarca>> callback) {
        Call<List<VeiculoMarca>> call = apiService.buscarVeiculoMarcas(tipoVeiculo);
        call.enqueue(callback);
    }


    public void fetchModelosAndAnos(String tipoVeiculo, String codMarcaVeiculo, Callback<ApiResponseVeiculoModelo> callback) {
        Call<ApiResponseVeiculoModelo> call = apiService.getModelosAndAnos(tipoVeiculo, codMarcaVeiculo);
        call.enqueue(callback);
    }


}
