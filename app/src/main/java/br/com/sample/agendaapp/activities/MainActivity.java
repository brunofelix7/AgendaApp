package br.com.sample.agendaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.sample.agendaapp.R;
import br.com.sample.agendaapp.adapters.MyAdapter;
import br.com.sample.agendaapp.interfaces.ItemClickCallback;
import br.com.sample.agendaapp.models.ListItem;
import br.com.sample.agendaapp.models.MyContacts;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickCallback {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "TITLE";
    private static final String EXTRA_SUBTITLE = "SUBTITLE";
    private RecyclerView rv_list;
    private MyAdapter adapter;
    private ArrayList listData;
    private ListItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listData = (ArrayList) MyContacts.getListData();

        //  RECUPERA O RecyclerView
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        //  DEFINE O TIPO DE LAYOUT DO RecyclerView
        //  Or GridLayoutManager or StaggeredGridLayoutManager
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        //  MEU ADAPTER RECEBE A LISTA DE DADOS AQUI
        adapter = new MyAdapter(MyContacts.getListData(), this);
        adapter.setItemClickCallback(this);

        //  ADICIONA MEU ADAPTER
        rv_list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int p) {
        Toast.makeText(this, "Click on item " + p, Toast.LENGTH_SHORT).show();

        //  Vou passar pra minha activity de listagem o que eu cliquei
        item = (ListItem) listData.get(p);
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, item.getTitle());
        extras.putString(EXTRA_SUBTITLE, item.getSubTitle());
        intent.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(intent);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        Toast.makeText(this, "Click on secondary item " + p, Toast.LENGTH_SHORT).show();

        //  VOCÊ PODE CHAMAR ALGUM MÉTODO LÁ DO MyAdapter e executar alguma ação como excluir a ocorrência por exemplo
        ListItem item = (ListItem) listData.get(p);
        //update our data
        if (item.isFavourite()){
            item.setFavourite(false);
        } else {
            item.setFavourite(true);
        }
        adapter.notifyDataSetChanged();
    }
}
