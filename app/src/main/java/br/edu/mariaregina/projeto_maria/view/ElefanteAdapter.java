package br.edu.mariaregina.projeto_maria.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.mariaregina.projeto_maria.R;
import br.edu.mariaregina.projeto_maria.model.Elefante;

public class ElefanteAdapter extends RecyclerView.Adapter<ElefanteAdapter.ElefanteViewHolder>{
    private List<Elefante> elefanteList;

    public ElefanteAdapter(@NonNull List<Elefante> elefantes){this.elefanteList = elefantes;}

    @NonNull
    @Override
    public ElefanteAdapter.ElefanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elefante_recyclerview, parent, false);
       ElefanteViewHolder viewHolder = new ElefanteViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ElefanteAdapter.ElefanteViewHolder holder, int position) {
        holder.elefanteTextView.setText(elefanteList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return elefanteList.size();
    }

    public class ElefanteViewHolder extends RecyclerView.ViewHolder{
        private TextView elefanteTextView;

        public ElefanteViewHolder(@NonNull View itemView) {
            super(itemView);
            elefanteTextView =itemView.findViewById(R.id.textview_elefante);
        }
    }

}



