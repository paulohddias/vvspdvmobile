package devandroid.paulo.appvvspdvmobile.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import devandroid.paulo.appvvspdvmobile.R;
import devandroid.paulo.appvvspdvmobile.api.AppUtil;
import devandroid.paulo.appvvspdvmobile.interfaces.ViaCepService;
import devandroid.paulo.appvvspdvmobile.model.ViaCep;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ModeloAzulFragment extends Fragment {

    private View view;

    private Button btnBuscaCep;

    private EditText editCep;

    private TextView responseTextView;

    public ModeloAzulFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_azul, container, false);

        responseTextView = view.findViewById(R.id.responseTextView);
        btnBuscaCep = view.findViewById(R.id.btnbuscacep);
        editCep = view.findViewById(R.id.editcep);


        btnBuscaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cep = editCep.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://viacep.com.br/ws/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ViaCepService service = retrofit.create(ViaCepService.class);

                Call<ViaCep> call = service.buscarEnderecoPorCEP(cep);
                call.enqueue(new Callback<ViaCep>() {
                    @Override
                    public void onResponse(Call<ViaCep> call, Response<ViaCep> response) {
                        if (response.isSuccessful()) {
                            ViaCep viacep = response.body();
                            // Faça o que quiser com os dados do endereço aqui

                            String enderecoCompleto = "";
                            enderecoCompleto += "Endereço: " + viacep.getLogradouro() + "\n";
                            enderecoCompleto += "Complemento: " + viacep.getComplemento() + "\n";
                            enderecoCompleto += "Bairro: " + viacep.getBairro() + "\n";
                            enderecoCompleto += "Cidade: " + viacep.getLocalidade() + "\n";
                            enderecoCompleto += "Estado: " + viacep.getUf() + "\n";
                            enderecoCompleto += "Cod. IBGE: " + viacep.getIbge() + "\n";

                            responseTextView.setText(enderecoCompleto);


                        } else {
                            Log.d(AppUtil.TAG, "ModeloAzulFragment: onResponse: nao conseguiu consumir api");
                        }
                    }

                    @Override
                    public void onFailure(Call<ViaCep> call, Throwable t) {
                        Log.d(AppUtil.TAG, "ModeloAzulFragment: onFailure: nao conseguiu consumir api");
                    }
                });

            }
        });

        return view;
    }


}
