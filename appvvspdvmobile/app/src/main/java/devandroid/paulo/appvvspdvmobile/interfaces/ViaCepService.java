package devandroid.paulo.appvvspdvmobile.interfaces;

import devandroid.paulo.appvvspdvmobile.model.ViaCep;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("{cep}/json/")
    Call<ViaCep> buscarEnderecoPorCEP(@Path("cep") String cep);
}
