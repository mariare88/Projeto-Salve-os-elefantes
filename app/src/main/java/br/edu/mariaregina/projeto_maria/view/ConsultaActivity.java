package br.edu.mariaregina.projeto_maria.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

import br.edu.mariaregina.projeto_maria.R;
import br.edu.mariaregina.projeto_maria.dao.ElefanteDao;
import br.edu.mariaregina.projeto_maria.model.Elefante;

public class ConsultaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<Elefante> elefanteList;
    private ElefanteDao elefanteDao;

    private ElefanteAdapter ElefanteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        mRecyclerView = findViewById(R.id.recylerview_elefante);

        elefanteDao = new ElefanteDao(this);
        elefanteList = elefanteDao.recuperaTodos();

        ElefanteAdapter = new ElefanteAdapter(elefanteList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(ElefanteAdapter);

        updateDataSet();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDataSet(){
        elefanteList.clear();
        elefanteList.addAll(elefanteDao.recuperaTodos());
    }
}