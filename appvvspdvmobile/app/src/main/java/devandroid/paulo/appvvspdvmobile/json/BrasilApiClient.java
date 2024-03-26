package devandroid.paulo.appvvspdvmobile.json;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.interfaces.BrasilApiService;
import devandroid.paulo.appvvspdvmobile.model.DadosCnpj;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrasilApiClient {

    private static final String BASE_URL = "https://brasilapi.com.br/";

    private Retrofit retrofit;

    private BrasilApiService brasilApiService;

    public BrasilApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        brasilApiService = retrofit.create(BrasilApiService.class);
    }

    public void fetchCnpj(String cnpj, Callback<DadosCnpj> callback) {
        Call<DadosCnpj> call = brasilApiService.getDadosCnpj(cnpj);
        call.enqueue(callback);
    }

}
