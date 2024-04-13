package com.example.semana02.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.semana02.entity.User;
import com.example.semana02.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        }

        final User user = getItem(position);

        TextView txtId = convertView.findViewById(R.id.idItemUserId);
        TextView txtName = convertView.findViewById(R.id.idItemName);
        TextView txtEmail = convertView.findViewById(R.id.idItemEmail);
        TextView txtPhone = convertView.findViewById(R.id.idItemPhone);
        TextView txtWebsite = convertView.findViewById(R.id.idItemWebsite);

        txtId.setText(String.valueOf(user.getId()));
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtWebsite.setText(user.getWebsite());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressDialog(user);
            }
        });

        return convertView;
    }

    private void showAddressDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Dirección de " + user.getName());

        String address = "Calle: " + user.getAddress().getStreet() +
                "\nSuite: " + user.getAddress().getSuite() +
                "\nCiudad: " + user.getAddress().getCity() +
                "\nCódigo Postal: " + user.getAddress().getZipcode();

        builder.setMessage(address);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    // Agregar mensajeAlert sobrecargado
    public void mensajeAlert(String titulo, String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(msg);
        alertDialog.setTitle(titulo);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}