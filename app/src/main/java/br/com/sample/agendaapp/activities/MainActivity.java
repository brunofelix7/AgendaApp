package br.com.sample.agendaapp.activities;

import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.sample.agendaapp.R;
import br.com.sample.agendaapp.adapters.MyAdapter;
import br.com.sample.agendaapp.core.ControllerProfileActivity;
import br.com.sample.agendaapp.interfaces.ItemClickCallback;
import br.com.sample.agendaapp.models.Contact;
import br.com.sample.agendaapp.models.MyContacts;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickCallback {

    private RecyclerView rv_list;
    private MyAdapter adapter;
    private ArrayList listData;
    private TextView tv_perfil;

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

        //  Recupera o header do NavegationView
        View headerView = navigationView.getHeaderView(0);
        tv_perfil = (TextView) headerView.findViewById(R.id.tv_perfil);

        //  Recupero meus dados da numa lista
        listData = (ArrayList) MyContacts.getListData();

        //  Recupera o RecyclerView
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        //  Define o tipo do layout do RecyclerView, pode ser GridLayoutManager ou StaggeredGridLayoutManager também
        rv_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //  Meu adapter recebe minha lista de dados nesse contexto
        adapter = new MyAdapter(MyContacts.getListData(), MainActivity.this);

        //  Seto meu listener nesse contexto
        adapter.setItemClickCallback(MainActivity.this);

        //  Adiciona meu adapter no RecyclerView
        rv_list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  Sempre verifica se o valor que vou pegar é != de null
        //  Caso contrário, ao iniciar a 1ª eu recebo NullPointerException
        if (ControllerProfileActivity.getInstance().getValue() != null) {
            tv_perfil.setText(ControllerProfileActivity.getInstance().getValue());
        }
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

        if (id == R.id.nav_settings) {
            //  Vai pra tela de configurações
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_web) {
            //  Vai pra tela de WebView
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            //  Sai da aplicação
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onIconClick(int p) {
        //  Toast.makeText(MainActivity.this, "Get position: " + p, Toast.LENGTH_SHORT).show();

        //  Recupera a posição do item no RecyclerView
        Contact contact = (Contact) listData.get(p);

        //  Realiza uma discagem pegando o telefone, com base na posição da lista
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
        startActivity(intent);

        //  Executa alguma ação ou evento no meu adapter
        adapter.notifyDataSetChanged();
    }

    /**
     * Vai pra tela de edição de perfil
     * @param view
     */
    public void goToProfile(View view){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}
