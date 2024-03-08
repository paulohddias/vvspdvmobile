package devandroid.paulo.appvvspdvmobile.datamodel;

public class AbastecimentoDataModel {
    //Nome da Tabela
    public static final String TABELA = "abastecimento";

    //Atributos da Tabela
    public static final String ID = "id"; //INTEGER
    public static final String PRECOGASOLINA = "precoGasolina"; //REAL
    public static final String PRECOETANOL = "precoEtanol"; //REAL
    public static final String QTDLITROS = "qtdLitros"; //REAL
    public static final String TOTALPAGAR = "totalPagar"; //REAL
    public static final String QTDLITROSCONSUMO = "qtdLitrosConsumo"; //REAL
    public static final String KMATUAL = "kmAtual"; //INTEGER
    public static final String KMANTIGO = "kmAntigo"; //INTEGER
    public static final String KMCONSUMO = "kmConsumo"; //INTEGER
    public static final String COMBUSTIVELSELECIONADO = "combustivelSelecionado"; //TEXT
    public static final String DATAABASTECIMENTO = "dataAbastecimento"; //TEXT
    public static final String TIPOCOMBUSTIVELANTERIOR = "tipoCombustivelAnterior"; //TEXT

    //Query para criar a tabela no banco de daods
    public static String queryCriarTabela = "";

    //Metodo para gerar o Script para criar a tabela
    public static String criarTabela() {
        //Concatenção de String

        queryCriarTabela += "CREATE TABLE " + TABELA + " (";
        queryCriarTabela += ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += PRECOGASOLINA + " REAL, ";
        queryCriarTabela += PRECOETANOL + " REAL, ";
        queryCriarTabela += QTDLITROS + " REAL, ";
        queryCriarTabela += TOTALPAGAR + " REAL, ";
        queryCriarTabela += QTDLITROSCONSUMO + " REAL, ";
        queryCriarTabela += KMATUAL + " INTEGER, ";
        queryCriarTabela += KMANTIGO + " INTEGER, ";
        queryCriarTabela += KMCONSUMO + " INTEGER, ";
        queryCriarTabela += COMBUSTIVELSELECIONADO + " TEXT, ";
        queryCriarTabela += TIPOCOMBUSTIVELANTERIOR + " TEXT, ";
        queryCriarTabela += DATAABASTECIMENTO + " TEXT ";
        queryCriarTabela += ")";
        return queryCriarTabela;
    }
}
