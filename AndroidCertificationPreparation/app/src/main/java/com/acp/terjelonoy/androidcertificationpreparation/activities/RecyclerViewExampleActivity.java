package com.acp.terjelonoy.androidcertificationpreparation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.acp.terjelonoy.androidcertificationpreparation.R;
import com.acp.terjelonoy.androidcertificationpreparation.adapters.RecyclerViewAdapter;
import com.acp.terjelonoy.androidcertificationpreparation.objects.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by terjelonoy on 2/26/17.
 */

public class RecyclerViewExampleActivity extends Activity {
    private List<Pokemon> pokemonList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_example);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        listAdapter = new RecyclerViewAdapter(pokemonList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

        preparePokemonData();
    }

    private void preparePokemonData() {
        Pokemon pokemon = new Pokemon("Bulbasaur", "001");
        pokemonList.add(pokemon);

        pokemon = new Pokemon("Ivysaur", "002");
        pokemonList.add(pokemon);

        pokemon = new Pokemon("Venosaur", "003");
        pokemonList.add(pokemon);

        listAdapter.notifyDataSetChanged();
    }
}
