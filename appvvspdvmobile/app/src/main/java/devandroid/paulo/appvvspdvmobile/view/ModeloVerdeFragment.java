package devandroid.paulo.appvvspdvmobile.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import devandroid.paulo.appvvspdvmobile.R;
import devandroid.paulo.appvvspdvmobile.api.AppUtil;
import devandroid.paulo.appvvspdvmobile.api.MoneyTextWatcher;
import devandroid.paulo.appvvspdvmobile.api.UtilData;
import devandroid.paulo.appvvspdvmobile.controller.AbastecimentoControler;
import devandroid.paulo.appvvspdvmobile.model.Abastecimento;


public class ModeloVerdeFragment extends Fragment {

    View view;


    //Variaveis layout
    EditText editGasolina, editEtanol, editQtdLitros, editKmAtual, editTotalPagar;
    TextInputLayout txtGasolina, txtEtanol, txtQtdLitors, txtKmAtual, txtTotalPgar;
    Button btnLimpar, btnSalvar, btnFinalizar, btnCalcular, btnCalacularTipo;
    TextView textViewResultado;
    TableLayout tabHistorico;
    RadioGroup rbGrupo;
    RadioButton rbGasolina, rbEtanol;
    LinearLayout llHistorico;

    //Variaveis classes
    Abastecimento abastecimento;
    AbastecimentoControler abastecimentoControler;
    List<Abastecimento> dados;
    Date dataAtual;

    public ModeloVerdeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_verde, container, false);


        abastecimentoControler = new AbastecimentoControler(view.getContext());

        //Pegando o ultimo registro
        abastecimento = abastecimentoControler.getUltimoRegistro();

        //Atualizando o kmAntigo com base no ultimo abastecimento
        int kmAntigo = abastecimento.getKmAtual();
        abastecimento.setKmAntigo(kmAntigo);
        String tipoCombAnt = abastecimento.getCombustivelSelecionado();
        abastecimento.setTipoCombustivelAnterior(tipoCombAnt);

        dados = abastecimentoControler.getListaDados();

        editGasolina = view.findViewById(R.id.editGasolina);
        editEtanol = view.findViewById(R.id.editEtanol);
        editQtdLitros = view.findViewById(R.id.editQtdLitros);
        editKmAtual = view.findViewById(R.id.editKmAtual);
        editTotalPagar = view.findViewById(R.id.editTotalPagar);

        txtGasolina = view.findViewById(R.id.txtGasolina);
        txtEtanol = view.findViewById(R.id.txtEtanol);
        txtQtdLitors = view.findViewById(R.id.txtQtdLitros);
        txtKmAtual = view.findViewById(R.id.txtKmAtual);
        txtTotalPgar = view.findViewById(R.id.txtTotalPagar);
        btnCalcular = view.findViewById(R.id.btnCalcular);
        btnCalacularTipo = view.findViewById(R.id.btnCalculatTipo);

        textViewResultado = view.findViewById(R.id.textResultado);

        //tabHistorico = findViewById(R.id.tabHistorico);

        rbGrupo = view.findViewById(R.id.rbGrupo);

        rbGasolina = view.findViewById(R.id.rbGasolina);
        rbEtanol = view.findViewById(R.id.rbEtanol);

        llHistorico = view.findViewById(R.id.llHistorico);

        Locale mLocale = new Locale("pt", "BR");
        editGasolina.addTextChangedListener(new MoneyTextWatcher(editGasolina, mLocale));
        editEtanol.addTextChangedListener(new MoneyTextWatcher(editEtanol, mLocale));
        editTotalPagar.addTextChangedListener(new MoneyTextWatcher(editTotalPagar, mLocale));


        //Populado tabela de historico e ultimo dado
        Boolean vazio = dados.isEmpty();
        if (vazio == false) {
            editGasolina.setText(abastecimento.getPrecoGasolina().toString());
            editEtanol.setText(abastecimento.getPrecoEtanol().toString());
            String abastecimentoAterior;
            for (int i = 0; i < dados.size(); i++) {
                //dadosTabela(tabHistorico, dados.get(i), i);
                abastecimentoAterior = dados.get(i).getCombustivelSelecionado();
                criarHistoricoAbastecimento(llHistorico, dados.get(i));
            }
        }


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double gasolina, etanol, totalPagar, qtdLitros = 0.0;
                int kmAtual;

                if (validadeForm() == true) {
                    gasolina = MoneyTextWatcher.stringMonetarioToDouble(editGasolina.getText().toString());
                    etanol = MoneyTextWatcher.stringMonetarioToDouble(editEtanol.getText().toString());
                    totalPagar = MoneyTextWatcher.stringMonetarioToDouble(editTotalPagar.getText().toString());
                    kmAtual = Integer.parseInt(editKmAtual.getText().toString());

                    abastecimento.setPrecoGasolina(gasolina);
                    abastecimento.setPrecoEtanol(etanol);
                    abastecimento.setKmAtual(kmAtual);
                    abastecimento.setTotalPagar(totalPagar);

                    if (abastecimento.getKmAtual() < abastecimento.getKmAntigo()) {
                        Toast.makeText(view.getContext(), "KM Atual não pode ser menor que KM Antigo " + abastecimento.getKmAntigo() + " !!!", Toast.LENGTH_LONG).show();
                        txtKmAtual.setErrorEnabled(true);
                        editKmAtual.requestFocus();
                    } else {
                        txtKmAtual.setErrorEnabled(false);
                        abastecimento.setKmAtual(kmAtual);
                        int consumoKM = 0;
                        if (abastecimento.getKmAntigo() == 0) {
                            consumoKM = 0;
                        } else {
                            consumoKM = abastecimento.getKmAtual() - abastecimento.getKmAntigo();
                        }

                        abastecimento.setKmConsumo(consumoKM);
                        //abastecimento.setKmAntigo(kmAtual);
                        if (rbGasolina.isChecked()) {
                            abastecimento.setCombustivelSelecionado("Gasolina");
                            qtdLitros = abastecimentoControler.calculoCombustivel(gasolina, totalPagar);
                            qtdLitros = AppUtil.doubleDuasCasasDecimais(qtdLitros);

                        } else if (rbEtanol.isChecked()) {
                            abastecimento.setCombustivelSelecionado("Etanol");
                            qtdLitros = abastecimentoControler.calculoCombustivel(etanol, totalPagar);
                            qtdLitros = AppUtil.doubleDuasCasasDecimais(qtdLitros);
                        }

                        editQtdLitros.setText(AppUtil.doubleToString(qtdLitros));
                        abastecimento.setQtdLitros(qtdLitros);
                        abastecimento.setQtdLitrosConsumo(abastecimentoControler.calculoConsumoPorLitro(abastecimento.getKmConsumo(), abastecimento.getQtdLitros()));

                        dataAtual = new Date();
                        abastecimento.setDataAbastecimento(UtilData.stringToDataBD(UtilData.dateToString(dataAtual)));
                        abastecimentoControler.incluir(abastecimento);

                        //dadosTabela(tabHistorico, abastecimento, dados.size());
                        criarHistoricoAbastecimento(llHistorico, abastecimento);
                    }

                }

            }
        });

        btnCalacularTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double gasolina, etanol;

                if (validFormCalcularTipo() == true) {
                    gasolina = MoneyTextWatcher.stringMonetarioToDouble(editGasolina.getText().toString());
                    etanol = MoneyTextWatcher.stringMonetarioToDouble(editEtanol.getText().toString());

                    String resultado = AppUtil.calcularMelhorOpcao(gasolina, etanol);
                    textViewResultado.setText(resultado);
                }

            }
        });


        return view;
    }

    //meus metodos

    public void limpar() {
        editGasolina.setText("0");
        editEtanol.setText("0");
        editQtdLitros.setText("0");
        editKmAtual.setText("0");
        editTotalPagar.setText("0");
        textViewResultado.setText("Resultado");
    }

    public boolean validadeForm() {
        if (rbGasolina.isChecked() || rbEtanol.isChecked()) {
            if (editGasolina.getText().toString().isEmpty() || editGasolina.getText().toString().equals("R$ 0,00")) {
                txtGasolina.setErrorEnabled(true);
                txtGasolina.setError("Preencher o valor da gasolina!!!");
                editGasolina.requestFocus();
            } else if (editEtanol.getText().toString().isEmpty() || editEtanol.getText().toString().equals("R$ 0,00")) {
                txtEtanol.setErrorEnabled(true);
                txtEtanol.setError("Preencher o valor do etanol!!!");
                editEtanol.requestFocus();
            } else if (editKmAtual.getText().toString().isEmpty() || editKmAtual.getText().toString().equals("0")) {
                txtKmAtual.setErrorEnabled(true);
                txtKmAtual.setError("Preencher o KM atual!!!");
                editKmAtual.requestFocus();
            } else if (editTotalPagar.getText().toString().isEmpty() || editTotalPagar.getText().toString().equals("R$ 0,00")) {
                txtTotalPgar.setErrorEnabled(true);
                txtTotalPgar.setError("Preencher o valor a pagar!!!");
                editTotalPagar.requestFocus();
            } else {
                txtGasolina.setErrorEnabled(false);
                txtEtanol.setErrorEnabled(false);
                txtQtdLitors.setErrorEnabled(false);
                txtKmAtual.setErrorEnabled(false);
                return true;
            }
        } else {
            Toast.makeText(view.getContext(), "Favor Selecionar o tipo de Combustivel!!!", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public boolean validFormCalcularTipo() {
        if (editGasolina.getText().toString().isEmpty() || editGasolina.getText().toString().equals("R$ 0,00")) {
            txtGasolina.setErrorEnabled(true);
            txtGasolina.setError("Preencher o valor da gasolina!!!");
            editGasolina.requestFocus();
        } else if (editEtanol.getText().toString().isEmpty() || editEtanol.getText().toString().equals("R$ 0,00")) {
            txtEtanol.setErrorEnabled(true);
            txtEtanol.setError("Preencher o valor do etanol!!!");
            editEtanol.requestFocus();
        } else {
            txtGasolina.setErrorEnabled(false);
            txtEtanol.setErrorEnabled(false);

            return true;
        }
        return false;
    }

    public void dadosTabela(TableLayout tableLayout, Abastecimento abastecimento, int i) {

        TableRow tr_head = new TableRow(view.getContext());

        TextView labelData = new TextView(view.getContext());
        labelData = linhaTabela(labelData, i);
        labelData.setText(abastecimento.getDataAbastecimento());
        tr_head.addView(labelData);

        TextView labelCombustivel = new TextView(view.getContext());
        labelCombustivel = linhaTabela(labelCombustivel, i);
        labelCombustivel.setText(abastecimento.getCombustivelSelecionado());
        tr_head.addView(labelCombustivel);

        TextView labelPreco = new TextView(view.getContext());
        labelPreco = linhaTabela(labelPreco, i);
        if (abastecimento.getCombustivelSelecionado().equals("Gasolina")) {
            labelPreco.setText(AppUtil.doubleParaReal(abastecimento.getPrecoGasolina()));
        } else {
            labelPreco.setText(AppUtil.doubleParaReal(abastecimento.getPrecoEtanol()));
        }
        tr_head.addView(labelPreco);

        TextView labelLitros = new TextView(view.getContext());
        labelLitros = linhaTabela(labelLitros, i);
        labelLitros.setText(abastecimento.getQtdLitros().toString());
        tr_head.addView(labelLitros);

        TextView labelKmAtual = new TextView(view.getContext());
        labelKmAtual = linhaTabela(labelKmAtual, i);
        labelKmAtual.setText(Integer.toString(abastecimento.getKmAtual()));
        tr_head.addView(labelKmAtual);

        TextView labelTotalPagar = new TextView(view.getContext());
        labelTotalPagar = linhaTabela(labelTotalPagar, i);
        labelTotalPagar.setText(AppUtil.doubleParaReal(abastecimento.getTotalPagar()));
        tr_head.addView(labelTotalPagar);

        TextView labelConsumo = new TextView(view.getContext());
        labelConsumo = linhaTabela(labelConsumo, i);
        labelConsumo.setText(Integer.toString(abastecimento.getKmConsumo()));
        tr_head.addView(labelConsumo);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        criarLinhaTabela(tableLayout);

    }

    public void criarLinhaTabela(TableLayout tableLayout) {
        View vLinha = new View(view.getContext());
        vLinha.setBackgroundColor(Color.parseColor("#2c3e50"));
        vLinha.setMinimumHeight(5);
        tableLayout.addView(vLinha);
    }

    public TextView linhaTabela(TextView textView, int i) {
        //Verifica se par ou impar
        if (i % 2 == 0) {
            textView.setBackgroundResource(R.color.tabela_linha_branca);
        } else {
            textView.setBackgroundResource(R.color.tabela_linha_clara);
        }
        textView.setTextAppearance(R.style.tabl_font);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }

    public void criarHistoricoAbastecimento(LinearLayout llHistorico, Abastecimento abastecimento) {

        //Cria layout inteira como uma linha onde sera dividido
        LinearLayout llLinha = new LinearLayout(view.getContext());
        llLinha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        llLinha.setOrientation(LinearLayout.HORIZONTAL);
        llLinha.setId(abastecimento.getIdAbastecimento());

        llHistorico.addView(llLinha);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;

        //Cria a primeira coluna de data
        LinearLayout llData = new LinearLayout(view.getContext());
        llData.setLayoutParams(lp);
        llData.setOrientation(LinearLayout.VERTICAL);
        llData.setGravity(Gravity.CENTER);
        llLinha.addView(llData);

        TextView data = new TextView(view.getContext());
        data.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
        data.setText(UtilData.stringToDataBrMes(abastecimento.getDataAbastecimento()));
        data.setGravity(Gravity.CENTER);
        data.setBackgroundResource(R.drawable.ic_calendario);
        data.setTypeface(null, Typeface.BOLD);
        data.setPadding(0, 50, 0, 0);
        llData.addView(data);


        //Cria a Segunada coluna de dados
        LinearLayout llDados = new LinearLayout(view.getContext());
        llDados.setLayoutParams(lp);
        llDados.setOrientation(LinearLayout.VERTICAL);
        llLinha.addView(llDados);

        TextView comb = new TextView(view.getContext());
        comb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        comb.setText("Abasteceu com " + abastecimento.getCombustivelSelecionado() + "      " + AppUtil.doubleToString(abastecimento.getQtdLitros()) + " L");
        comb.setTypeface(null, Typeface.BOLD);
        comb.setTextColor(Color.parseColor("#6D214F"));
        llDados.addView(comb);

        TextView valorComb = new TextView(view.getContext());
        valorComb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (abastecimento.getCombustivelSelecionado().equals("Gasolina")) {
            valorComb.setText("Preço R$  " + AppUtil.doubleToString(abastecimento.getPrecoGasolina()));
        } else {
            valorComb.setText("Preço R$  " + AppUtil.doubleToString(abastecimento.getPrecoEtanol()));
        }
        llDados.addView(valorComb);

        TextView valorTotal = new TextView(view.getContext());
        valorTotal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        valorTotal.setText("Total R$  " + AppUtil.doubleToString(abastecimento.getTotalPagar()));
        llDados.addView(valorTotal);

        TextView consumo = new TextView(view.getContext());
        consumo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        consumo.setText("Andou: " + abastecimento.getKmConsumo() + " km com " + abastecimento.getTipoCombustivelAnterior());
        llDados.addView(consumo);

        TextView litros = new TextView(view.getContext());
        litros.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        litros.setText("Consumo: " + AppUtil.doubleToString(abastecimento.getQtdLitrosConsumo()) + " km/l");
        llDados.addView(litros);

        TextView kmAtual = new TextView(view.getContext());
        kmAtual.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        kmAtual.setText("KM: " + abastecimento.getKmAtual());
        llDados.addView(kmAtual);


        View vLinha = new View(view.getContext());
        vLinha.setBackgroundColor(Color.parseColor("#2c3e50"));
        vLinha.setMinimumHeight(5);
        llHistorico.addView(vLinha);

    }


}
