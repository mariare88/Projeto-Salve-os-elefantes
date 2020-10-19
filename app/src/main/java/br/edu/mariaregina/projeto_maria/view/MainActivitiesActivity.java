package br.edu.mariaregina.projeto_maria.view;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.mariaregina.projeto_maria.R;
import br.edu.mariaregina.projeto_maria.api.RetrofitService;
import br.edu.mariaregina.projeto_maria.dao.ElefanteDao;
import br.edu.mariaregina.projeto_maria.model.Elefante;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivitiesActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final int REQUEST_PERMISSION = 64;
    private static final String BASE_URL = "https://elephant-api.herokuapp.com/elephants/name/";

    private ElefanteDao elefanteDao;

    private Retrofit mRetrofit;

    private EditText nameEditText;
    private Button buscarButton;
    private Button consultarButton;
    private TextView nameTextView;
    private TextView affiliationTextView;
    private TextView speciesTextView;
    private TextView sexTextView;
    private TextView noteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activities);

       getLayoutElements();

        elefanteDao = new ElefanteDao(this);
    }

    private void getLayoutElements(){
        nameEditText = findViewById(R.id.edittext_name);
        nameTextView = findViewById(R.id.textview_name);
        affiliationTextView = findViewById(R.id.textview_affiation);
        speciesTextView = findViewById(R.id.textview_species);
        sexTextView = findViewById(R.id.textview_sex);
        noteTextView = findViewById(R.id.textview_note);
        buscarButton = findViewById(R.id.button_buscar);
        consultarButton = findViewById(R.id.button_consultar);
        consultarButton.setOnClickListener(this);
        buscarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buscarButton) {
            if (temPermissao()) {
                buscarElefante();
            } else {
                solicitaPermissao();
            }
        }else{
            consultar();
        }
    }

    private void consultar(){
        Intent intentConsulta = new Intent(this, ConsultaActivity.class);

        startActivity(intentConsulta);
    }

    private void buscarElefante(){
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        String nameString = nameEditText.getText().toString();

            RetrofitService mRetrofitService = mRetrofit.create(RetrofitService.class);

            Call<Elefante> call = mRetrofitService.getDados(nameString);

            call.enqueue(new Callback<Elefante>() {
                @Override
                public void onResponse(Call<Elefante> call, Response<Elefante> response) {
                    if(response.isSuccessful()){
                        Elefante elefante = response.body();
                        elefanteDao.adicionar(elefante);
                        updateUI(elefante);
                    }
                }

                @Override
                public void onFailure(Call<Elefante> call, Throwable t) {
                    Toast.makeText(MainActivitiesActivity.this, getString(R.string.erro_api), Toast.LENGTH_SHORT).show();
                }
            });
        }

    private boolean temPermissao(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void solicitaPermissao() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
            final Activity activity = this;
            new AlertDialog.Builder(this)
                    .setMessage(R.string.explicacao_permissao)
                    .setPositiveButton(R.string.botao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton(R.string.botao_nao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.INTERNET
                    },
                    REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {

                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    buscarElefante();
                }
            }
        }
    }

    private void updateUI(Elefante elefante){
        if(elefante != null){
            nameTextView.setText(elefante.getName());
            affiliationTextView.setText(elefante.getAffiliation());
            speciesTextView.setText(elefante.getSpecies());
            sexTextView.setText(elefante.getSex());
            noteTextView.setText(elefante.getNote());
        }
    }
}















