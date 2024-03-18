package devandroid.paulo.appvvspdvmobile.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;

import devandroid.paulo.appvvspdvmobile.R;
import devandroid.paulo.appvvspdvmobile.json.ApiClient;
import devandroid.paulo.appvvspdvmobile.model.Cep;

import com.google.gson.Gson;


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

        view =  inflater.inflate(R.layout.fragment_azul, container, false);

        responseTextView = view.findViewById(R.id.responseTextView);
        btnBuscaCep = view.findViewById(R.id.btnbuscacep);
        editCep = view.findViewById(R.id.editcep);


        btnBuscaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cep = editCep.getText().toString();

                new ApiTask().execute("https://viacep.com.br/ws/"+cep+"/json/");

            }
        });

        return view;
    }

    private class ApiTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return ApiClient.getApiResponse(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new Gson();
            Cep cep = gson.fromJson(result, Cep.class);
            responseTextView.setText(cep.getLogradouro().toString());
        }
    }


}
