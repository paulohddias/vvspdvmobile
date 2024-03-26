package devandroid.paulo.appvvspdvmobile.json;

import devandroid.paulo.appvvspdvmobile.interfaces.ViaCepService;
import devandroid.paulo.appvvspdvmobile.model.ViaCep;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CepApiClient {
    private static final String BASE_URL = "https://viacep.com.br/ws/";

    private Retrofit retrofit;
    private ViaCepService viaCepService;

    public CepApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        viaCepService = retrofit.create(ViaCepService.class);
    }

    public void fetchCep(String cep, Callback<ViaCep> callback) {
        Call<ViaCep> call = viaCepService.getEnderecoPorCEP(cep);
        call.enqueue(callback);
    }
}
