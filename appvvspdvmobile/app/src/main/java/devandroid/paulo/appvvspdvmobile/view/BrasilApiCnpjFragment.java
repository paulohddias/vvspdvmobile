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
import devandroid.paulo.appvvspdvmobile.api.MaskEditUtil;
import devandroid.paulo.appvvspdvmobile.json.BrasilApiClient;
import devandroid.paulo.appvvspdvmobile.model.DadosCnpj;
import devandroid.paulo.appvvspdvmobile.model.Qsa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrasilApiCnpjFragment extends Fragment {

    private View view;

    private Button btnBuscarCnpj;

    private EditText editCnpj;

    private TextView resultadoBuscaCnpj;

    private BrasilApiClient brasilApiClient;

    private DadosCnpj dadosCnpj;

    public BrasilApiCnpjFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_brasil_api_cnpj, container, false);

        brasilApiClient = new BrasilApiClient();

        resultadoBuscaCnpj = view.findViewById(R.id.resultadoBuscaCnpj);
        btnBuscarCnpj = view.findViewById(R.id.btnBuscarCnpj);
        editCnpj = view.findViewById(R.id.editCnpj);

        btnBuscarCnpj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cnpj = MaskEditUtil.unmask(editCnpj.getText().toString());

                brasilApiClient.fetchCnpj(cnpj, new Callback<DadosCnpj>() {
                    @Override
                    public void onResponse(Call<DadosCnpj> call, Response<DadosCnpj> response) {
                        if (response.isSuccessful()) {
                            // Access the cnpj from apiResponse object
                            dadosCnpj = response.body();

                            String resultado = "";

                            resultado += "CNPJ: " + dadosCnpj.getCnpj() + "\n";
                            resultado += "Razão Social: " + dadosCnpj.getRazaoSocial() + "\n";
                            resultado += "Fantasia: " + dadosCnpj.getNomeFantasia() + "\n";
                            resultado += "Endereço: " + dadosCnpj.getLogradouro() + "\n";
                            resultado += "Numero: " + dadosCnpj.getNumero() + "\n";
                            resultado += "Complemento: " + dadosCnpj.getComplemento() + "\n";
                            resultado += "Bairro: " + dadosCnpj.getBairro() + "\n";
                            resultado += "Municipio: " + dadosCnpj.getMunicipio() + "\n";
                            resultado += "Estado: " + dadosCnpj.getUf() + "\n";
                            resultado += "CEP: " + dadosCnpj.getCep() + "\n";
                            resultado += "COD IBGE: " + dadosCnpj.getCodigoMunicipioIbge() + "\n";
                            resultado += "Telefone: " + dadosCnpj.getDddTelefone_1() + "\n";
                            resultado += "Capital Social: " + dadosCnpj.getCapitalSocial() + "\n";
                            resultado += "COD CNAE: " + dadosCnpj.getCnaeFiscal() + "\n";
                            resultado += "CNAE: " + dadosCnpj.getCnaeFiscalDescricao() + "\n";
                            resultado += "Porte: " + dadosCnpj.getPorte() + "\n";
                            resultado += "Natureza Juridica: " + dadosCnpj.getNaturezaJuridica() + "\n";
                            resultado += "Status: " + dadosCnpj.getDescricaoSituacaoCadastral() + "\n";
                            resultado += "Data Inicio Atividade: " + dadosCnpj.getDataInicioAtividade() + "\n";
                            resultado += "Socios: " + "\n";
                            for (Qsa i : dadosCnpj.getQsa()) {
                                resultado += "Socio: " + i.getNomeSocio() + "\n";
                                resultado += "CNPJ ou CPF: " + i.getCnpjCpfDoSocio() + "\n";
                                resultado += "Qualificação: " + i.getQualificacaoSocio() + "\n";
                                resultado += "Data da Sociendade: " + i.getDataEntradaSociedade() + "\n";
                            }


                            resultadoBuscaCnpj.setText(resultado);


                        } else {
                            Log.d(AppUtil.TAG, "BrasilApiCnpjFragment: Else da busca API");
                        }
                    }

                    @Override
                    public void onFailure(Call<DadosCnpj> call, Throwable t) {

                    }
                });


            }
        });

        return view;
    }


}
