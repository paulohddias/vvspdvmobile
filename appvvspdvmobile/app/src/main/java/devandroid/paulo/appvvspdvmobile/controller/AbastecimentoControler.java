package devandroid.paulo.appvvspdvmobile.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.api.AppUtil;
import devandroid.paulo.appvvspdvmobile.datamodel.AbastecimentoDataModel;
import devandroid.paulo.appvvspdvmobile.datasource.AppDataBase;
import devandroid.paulo.appvvspdvmobile.interfaces.ICrud;
import devandroid.paulo.appvvspdvmobile.model.Abastecimento;


public class AbastecimentoControler extends AppDataBase implements ICrud<Abastecimento> {

    public static final String NOME_PREFERENCES = "pref_abastecimento";

    ContentValues dadoDoObjeto;


    public AbastecimentoControler(Context context) {
        super(context);
        Log.d(AppUtil.TAG, "AbastecimentoControler: Conectado");
    }


    public Double calculoCombustivel(double valorCombustivel, double totalPagar) {
        Double qtdLitros = 0.0;
        qtdLitros = totalPagar / valorCombustivel;
        return qtdLitros;
    }

    public Double calculoConsumoPorLitro(int quantKm, Double litrosAbastecimento) {
        Double kmL;
        kmL = quantKm / litrosAbastecimento;
        return AppUtil.doubleDuasCasasDecimais(kmL);
    }


    @Override
    public boolean incluir(Abastecimento obj) {
        dadoDoObjeto = new ContentValues();

        dadoDoObjeto.put(AbastecimentoDataModel.PRECOGASOLINA, obj.getPrecoGasolina());
        dadoDoObjeto.put(AbastecimentoDataModel.PRECOETANOL, obj.getPrecoEtanol());
        dadoDoObjeto.put(AbastecimentoDataModel.QTDLITROS, obj.getQtdLitros());
        dadoDoObjeto.put(AbastecimentoDataModel.TOTALPAGAR, obj.getTotalPagar());
        dadoDoObjeto.put(AbastecimentoDataModel.QTDLITROSCONSUMO, obj.getQtdLitrosConsumo());
        dadoDoObjeto.put(AbastecimentoDataModel.KMATUAL, obj.getKmAtual());
        dadoDoObjeto.put(AbastecimentoDataModel.KMANTIGO, obj.getKmAntigo());
        dadoDoObjeto.put(AbastecimentoDataModel.KMCONSUMO, obj.getKmConsumo());
        dadoDoObjeto.put(AbastecimentoDataModel.COMBUSTIVELSELECIONADO, obj.getCombustivelSelecionado());
        dadoDoObjeto.put(AbastecimentoDataModel.DATAABASTECIMENTO, obj.getDataAbastecimento());
        dadoDoObjeto.put(AbastecimentoDataModel.TIPOCOMBUSTIVELANTERIOR, obj.getTipoCombustivelAnterior());

        return insert(AbastecimentoDataModel.TABELA, dadoDoObjeto);
    }

    @Override
    public boolean alterar(Abastecimento obj) {
        dadoDoObjeto = new ContentValues();

        dadoDoObjeto.put(AbastecimentoDataModel.ID, obj.getIdAbastecimento());
        dadoDoObjeto.put(AbastecimentoDataModel.PRECOGASOLINA, obj.getPrecoGasolina());
        dadoDoObjeto.put(AbastecimentoDataModel.PRECOETANOL, obj.getPrecoEtanol());
        dadoDoObjeto.put(AbastecimentoDataModel.QTDLITROS, obj.getQtdLitros());
        dadoDoObjeto.put(AbastecimentoDataModel.TOTALPAGAR, obj.getTotalPagar());
        dadoDoObjeto.put(AbastecimentoDataModel.QTDLITROSCONSUMO, obj.getQtdLitrosConsumo());
        dadoDoObjeto.put(AbastecimentoDataModel.KMATUAL, obj.getKmAtual());
        dadoDoObjeto.put(AbastecimentoDataModel.KMANTIGO, obj.getKmAntigo());
        dadoDoObjeto.put(AbastecimentoDataModel.KMCONSUMO, obj.getKmConsumo());
        dadoDoObjeto.put(AbastecimentoDataModel.COMBUSTIVELSELECIONADO, obj.getCombustivelSelecionado());
        dadoDoObjeto.put(AbastecimentoDataModel.DATAABASTECIMENTO, obj.getDataAbastecimento());
        dadoDoObjeto.put(AbastecimentoDataModel.TIPOCOMBUSTIVELANTERIOR, obj.getTipoCombustivelAnterior());
        return update(AbastecimentoDataModel.TABELA, dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {
        return deleteByID(AbastecimentoDataModel.TABELA, id);
    }

    @Override
    public List<Abastecimento> listar() {
        return null;
    }

    public List<Abastecimento> getListaDados() {
        return listarDados();
    }

    public Abastecimento getUltimoRegistro() {
        return ultimoRegistro();
    }


}
