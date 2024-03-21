package devandroid.paulo.appvvspdvmobile.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import devandroid.paulo.appvvspdvmobile.R;
import devandroid.paulo.appvvspdvmobile.api.AppUtil;
import devandroid.paulo.appvvspdvmobile.interfaces.VeiculoMarcaService;
import devandroid.paulo.appvvspdvmobile.json.FipeApiClient;
import devandroid.paulo.appvvspdvmobile.model.VeiculoAno;
import devandroid.paulo.appvvspdvmobile.model.ApiResponseVeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ModeloVermelhoFragment extends Fragment {

    private View view;

    private Button btnTipoVeiculo;

    private TextView txtResultadoTipoVeiculo;

    private Spinner spinnerVeiculoMarca;
    private Spinner spinnerVeiculoModelo;

    private RadioGroup rgTipoVeiculo;

    private Retrofit retrofit;

    private VeiculoMarcaService service;

    private String tipoVeiculo;

    private FipeApiClient fipeApiClient;

    public ModeloVermelhoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fipeApiClient = new FipeApiClient();

        view = inflater.inflate(R.layout.fragment_vermelho, container, false);


        btnTipoVeiculo = view.findViewById(R.id.btnTipoVeiculo);
        txtResultadoTipoVeiculo = view.findViewById(R.id.txtResultadoTipoVeiculo);
        spinnerVeiculoMarca = view.findViewById(R.id.spinnerVeiculoMarca);
        spinnerVeiculoModelo = view.findViewById(R.id.spinnerVeiculoModelo);
        rgTipoVeiculo = view.findViewById(R.id.rgTipoVeiculo);

        //estanciando a api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(VeiculoMarcaService.class);

        //Metodo para pegar o click
        rgTipoVeiculo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // Encontra qual botão de rádio foi selecionado
                RadioButton radioButton = view.findViewById(i);

                if (radioButton != null) {
                    tipoVeiculo = radioButton.getText().toString();
                    Toast.makeText(getContext(), "Selecionado: " + tipoVeiculo, Toast.LENGTH_SHORT).show();

                    ArrayList<VeiculoMarca> listaTipoVeiculosMarcas = new ArrayList<>();

                    fipeApiClient.fetchTipoVeiculos(tipoVeiculo, new Callback<List<VeiculoMarca>>() {
                        @Override
                        public void onResponse(Call<List<VeiculoMarca>> call, Response<List<VeiculoMarca>> response) {
                            if (response.isSuccessful()) {
                                List<VeiculoMarca> veiculoMarca = response.body();
                                // Faça o que quiser com os dados do endereço aqui


                                String marca = "";
                                for (VeiculoMarca v : veiculoMarca) {

                                    listaTipoVeiculosMarcas.add(v);

                                    marca += "Codigo: " + v.getCodigo() + "\n";
                                    marca += "Nome: " + v.getNome() + "\n";

                                }

                                ArrayAdapter<VeiculoMarca> adapter = new ArrayAdapter<VeiculoMarca>(getActivity(), android.R.layout.simple_list_item_1,
                                        listaTipoVeiculosMarcas);
                                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                                spinnerVeiculoMarca.setAdapter(adapter);

                                //txtResultadoTipoVeiculo.setText(marca);

                            } else {
                                Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onResponse: nao conseguiu consumir api");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<VeiculoMarca>> call, Throwable t) {
                            Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onFailure: nao conseguiu consumir api: " + t.toString());
                        }
                    });

                }

            }
        });

        spinnerVeiculoMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "MARCA: " + selectedItem, Toast.LENGTH_SHORT).show();

                VeiculoMarca v = new VeiculoMarca();
                v = (VeiculoMarca) spinnerVeiculoMarca.getSelectedItem();
                String codMarcaVeiculo = Integer.toString(v.getCodigo());

                ArrayList<VeiculoModelo> listaVeiculosVeiculoModelos = new ArrayList<>();

                fipeApiClient.fetchModelosAndAnos(tipoVeiculo, codMarcaVeiculo, new Callback<ApiResponseVeiculoModelo>() {
                    @Override
                    public void onResponse(Call<ApiResponseVeiculoModelo> call, Response<ApiResponseVeiculoModelo> response) {
                        if (response.isSuccessful()) {
                            ApiResponseVeiculoModelo apiResponseVeiculoModelo = response.body();
                            // Access the list of modelos and anos from apiResponse object
                            List<VeiculoModelo> veiculoModelos = apiResponseVeiculoModelo.getModelos();
                            List<VeiculoAno> veiculoAnos = apiResponseVeiculoModelo.getAnos();

                            for (VeiculoModelo m : veiculoModelos) {
                                listaVeiculosVeiculoModelos.add(m);
                            }

                            ArrayAdapter<VeiculoModelo> adapter = new ArrayAdapter<VeiculoModelo>(getActivity(), android.R.layout.simple_list_item_1,
                                    listaVeiculosVeiculoModelos);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                            spinnerVeiculoModelo.setAdapter(adapter);


                        } else {
                            // Handle unsuccessful response
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseVeiculoModelo> call, Throwable t) {
                        // Handle network errors
                    }
                });


                //Call<List<VeiculoMarca>> call = service.buscarVeiculoMarcasByTipo(tipoVeiculo, codMarcaVeiculo);

                /*
                call.enqueue(new Callback<List<VeiculoMarca>>() {
                    @Override
                    public void onResponse(Call<List<VeiculoMarca>> call, Response<List<VeiculoMarca>> response) {
                        if (response.isSuccessful()) {
                            List<VeiculoMarca> veiculoModelo = response.body();
                            // Faça o que quiser com os dados do endereço aqui


                            String marca = "";
                            for (VeiculoMarca v : veiculoModelo) {

                                listaVeiculosModelos.add(v);

                                marca += "Codigo: " + v.getCodigo() + "\n";
                                marca += "Nome: " + v.getNome() + "\n";

                            }

                            ArrayAdapter<VeiculoMarca> adapter = new ArrayAdapter<VeiculoMarca>(getActivity(), android.R.layout.simple_list_item_1,
                                    listaVeiculosModelos);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                            spinnerVeiculoModelo.setAdapter(adapter);

                            //txtResultadoTipoVeiculo.setText(marca);

                        } else {
                            Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onResponse: nao conseguiu consumir api");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<VeiculoMarca>> call, Throwable t) {
                        Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onFailure: nao conseguiu consumir api Modelos: " + t.toString());
                    }
                });
                    */

                // Do something with the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });


        btnTipoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(AppUtil.TAG, "ModeloVermelhoFragment: btnTipoVeiculo.setOnClickListener: " + spinnerVeiculoMarca.getSelectedItem());
            }
        });


        return view;
    }


}
