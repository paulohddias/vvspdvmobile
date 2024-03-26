package devandroid.paulo.appvvspdvmobile.interfaces;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.model.DadosCnpj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BrasilApiService {

    @GET("api/cnpj/v1/{cnpj}")
    Call<DadosCnpj> getDadosCnpj(@Path("cnpj") String cnpj);
}
