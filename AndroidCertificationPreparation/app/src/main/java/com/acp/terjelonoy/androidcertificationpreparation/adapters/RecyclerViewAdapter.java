package com.acp.terjelonoy.androidcertificationpreparation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acp.terjelonoy.androidcertificationpreparation.R;
import com.acp.terjelonoy.androidcertificationpreparation.objects.Pokemon;

import java.util.List;

/**
 * Created by terjelonoy on 2/26/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExampleViewHolder> {

    private List<Pokemon> pokemonList;

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;

        public ExampleViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
        }
    }

    public RecyclerViewAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_pokemon, parent, false);

        return new ExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.name.setText(pokemon.getName());
        holder.number.setText(pokemon.getNumber());
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
