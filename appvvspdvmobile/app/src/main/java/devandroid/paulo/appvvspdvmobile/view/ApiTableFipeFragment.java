package devandroid.paulo.appvvspdvmobile.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import devandroid.paulo.appvvspdvmobile.model.VeiculoByFipe;
import devandroid.paulo.appvvspdvmobile.model.VeiculoModelo;
import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiTableFipeFragment extends Fragment {

    private View view;

    private Button btnTipoVeiculo;

    private TextView txtResultadoTipoVeiculo;

    private Spinner spinnerVeiculoMarca;
    private Spinner spinnerVeiculoModelo;
    private Spinner spinnerVeiculoModeloAno;

    private RadioGroup rgTipoVeiculo;

    private VeiculoMarcaService service;

    private VeiculoMarca veiculoMarcaByTipo;
    private VeiculoModelo veiculoMarcaByModelo;
    private VeiculoAno veiculoMarcaByModeloByAno;

    private String tipoVeiculo;

    private FipeApiClient fipeApiClient;

    private EditText editTextSearchMarca;
    private EditText editTextSearchModelo;


    public ApiTableFipeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fipeApiClient = new FipeApiClient();

        view = inflater.inflate(R.layout.fragment_api_tabela_fipe, container, false);


        btnTipoVeiculo = view.findViewById(R.id.btnTipoVeiculo);
        txtResultadoTipoVeiculo = view.findViewById(R.id.txtResultadoTipoVeiculo);
        spinnerVeiculoMarca = view.findViewById(R.id.spinnerVeiculoMarca);
        spinnerVeiculoModelo = view.findViewById(R.id.spinnerVeiculoModelo);
        spinnerVeiculoModeloAno = view.findViewById(R.id.spinnerVeiculoModeloAno);
        rgTipoVeiculo = view.findViewById(R.id.rgTipoVeiculo);
        editTextSearchMarca = view.findViewById(R.id.editTextSearchMarca);
        editTextSearchModelo = view.findViewById(R.id.editTextSearchModelo);


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
                    editTextSearchMarca.setText("");
                    editTextSearchModelo.setText("");

                    tipoVeiculo = radioButton.getText().toString();
                    Toast.makeText(getContext(), "Selecionado: " + tipoVeiculo, Toast.LENGTH_SHORT).show();

                    ArrayList<VeiculoMarca> listaTipoVeiculosMarcas = new ArrayList<>();

                    fipeApiClient.fetchTipoVeiculos(tipoVeiculo, new Callback<List<VeiculoMarca>>() {
                        @Override
                        public void onResponse(Call<List<VeiculoMarca>> call, Response<List<VeiculoMarca>> response) {
                            if (response.isSuccessful()) {
                                List<VeiculoMarca> veiculoMarca = response.body();
                                // Faça o que quiser com os dados do endereço aqui


                                for (VeiculoMarca v : veiculoMarca) {

                                    listaTipoVeiculosMarcas.add(v);

                                }

                                //ModeloArrayAdapter adapter = new ModeloArrayAdapter(getContext(), listaTipoVeiculosMarcas);

                                ArrayAdapter<VeiculoMarca> adapter = new ArrayAdapter<VeiculoMarca>(getActivity(), android.R.layout.simple_list_item_1,
                                        listaTipoVeiculosMarcas);
                                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                                spinnerVeiculoMarca.setAdapter(adapter);

                                // Configure a lógica de busca
                                editTextSearchMarca.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                       // adapter.getFilter().filter(s);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        adapter.getFilter().filter(s);
                                        veiculoMarcaByTipo = (VeiculoMarca) spinnerVeiculoMarca.getSelectedItem();
                                        Toast.makeText(getContext(), "Marca: " + veiculoMarcaByTipo, Toast.LENGTH_SHORT).show();
                                    }
                                });

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

                veiculoMarcaByTipo = (VeiculoMarca) spinnerVeiculoMarca.getSelectedItem();

                ArrayList<VeiculoModelo> listaVeiculosVeiculoModelos = new ArrayList<>();

                fipeApiClient.fetchModelosAndAnosByMarca(tipoVeiculo, Integer.toString(veiculoMarcaByTipo.getCodigo()), new Callback<ApiResponseVeiculoModelo>() {
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

                            editTextSearchModelo.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    adapter.getFilter().filter(s);
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    veiculoMarcaByModelo = (VeiculoModelo) spinnerVeiculoModelo.getSelectedItem();
                                    Toast.makeText(getContext(), "Modelo: " + veiculoMarcaByTipo, Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {
                            // Handle unsuccessful response
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseVeiculoModelo> call, Throwable t) {
                        // Handle network errors
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

        spinnerVeiculoModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getContext(), "Modelo: " + selectedItem, Toast.LENGTH_SHORT).show();

                veiculoMarcaByModelo = (VeiculoModelo) spinnerVeiculoModelo.getSelectedItem();

                ArrayList<VeiculoAno> listaVeiculosVeiculoModelosByAno = new ArrayList<>();

                fipeApiClient.fetchModelosAndAnosByModelo(tipoVeiculo,
                        Integer.toString(veiculoMarcaByTipo.getCodigo()),
                        Integer.toString(veiculoMarcaByModelo.getCodigo()),
                        new Callback<List<VeiculoAno>>() {
                            @Override
                            public void onResponse(Call<List<VeiculoAno>> call, Response<List<VeiculoAno>> response) {
                                if (response.isSuccessful()) {
                                    List<VeiculoAno> listVeiculoByMArcaByAno = response.body();
                                    // Access the list of modelos and anos from apiResponse object


                                    for (VeiculoAno m : listVeiculoByMArcaByAno) {
                                        listaVeiculosVeiculoModelosByAno.add(m);
                                    }

                                    ArrayAdapter<VeiculoAno> adapter = new ArrayAdapter<VeiculoAno>(getActivity(), android.R.layout.simple_list_item_1,
                                            listaVeiculosVeiculoModelosByAno);
                                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

                                    spinnerVeiculoModeloAno.setAdapter(adapter);




                                } else {
                                    Log.d(AppUtil.TAG, "ModeloVermelhoFragment: ELSE sem dados api fetchModelosAndAnosByModelo: ");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<VeiculoAno>> call, Throwable t) {
                                Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onFailure: nao conseguiu consumir api fetchModelosAndAnosByModelo: " + t.toString());
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });


        btnTipoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                veiculoMarcaByModeloByAno = (VeiculoAno) spinnerVeiculoModeloAno.getSelectedItem();

                fipeApiClient.fetchVeiculoByFipe(tipoVeiculo,
                        Integer.toString(veiculoMarcaByTipo.getCodigo()),
                        Integer.toString(veiculoMarcaByModelo.getCodigo()),
                        veiculoMarcaByModeloByAno.getCodigo(),
                        new Callback<VeiculoByFipe>() {
                            @Override
                            public void onResponse(Call<VeiculoByFipe> call, Response<VeiculoByFipe> response) {
                                if (response.isSuccessful()) {
                                    VeiculoByFipe veiculoByFipe = response.body();

                                    String veiculo = "";
                                    veiculo += "Preço Médio: " + veiculoByFipe.getValor() + "\n";
                                    veiculo += "Mês de referência: " + veiculoByFipe.getMesReferencia() + "\n";
                                    veiculo += "Código Fipe: " + veiculoByFipe.getCodigoFipe() + "\n";
                                    veiculo += "Marca: " + veiculoByFipe.getMarca() + "\n";
                                    veiculo += "Modelo: " + veiculoByFipe.getModelo() + "\n";
                                    veiculo += "Ano Modelo: " + veiculoByFipe.getAnoModelo() + "\n";

                                    txtResultadoTipoVeiculo.setText(veiculo);

                                } else {
                                    Log.d(AppUtil.TAG, "ModeloVermelhoFragment: nao conseguiu consumir api fetchVeiculoByFipe: ");
                                }
                            }

                            @Override
                            public void onFailure(Call<VeiculoByFipe> call, Throwable t) {
                                Log.d(AppUtil.TAG, "ModeloVermelhoFragment: onFailure: nao conseguiu consumir api fetchVeiculoByFipe: " + t.toString());
                            }
                        });


            }
        });


        return view;
    }


}
