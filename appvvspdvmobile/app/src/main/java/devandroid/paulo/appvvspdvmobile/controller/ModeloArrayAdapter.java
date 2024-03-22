package devandroid.paulo.appvvspdvmobile.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import devandroid.paulo.appvvspdvmobile.model.VeiculoMarca;

public class ModeloArrayAdapter extends ArrayAdapter<VeiculoMarca> {

    public ModeloArrayAdapter(Context context, List<VeiculoMarca> modelos) {
        super(context, android.R.layout.simple_spinner_item, modelos);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VeiculoMarca modelo = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        // Lookup view for data population
        TextView tvName = convertView.findViewById(android.R.id.text1);

        // Populate the data into the template view using the data object
        tvName.setText(modelo.getNome());

        // Return the completed view to render on screen
        return convertView;
    }
}
