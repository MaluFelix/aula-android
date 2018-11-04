package br.com.sevenestoque.sevenestoque;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static br.com.sevenestoque.sevenestoque.ListaCliente.listacliente;

public class ClientesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        Bundle extra = getIntent().getExtras();
        String value = "";
        if(extra != null){
            value = extra.getString("nomeBotao");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(value);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        Button btnAddCliente = (Button) findViewById(R.id.btnAddCliente);
        btnAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), CadastroClienteActivity.class);
                it.putExtra("nomeBotao","Cadastro de Cliente");
                startActivity(it);
                finish();
            }
        });

        carregar();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView rcvClientes = (RecyclerView) findViewById(R.id.rcvClientes);
        rcvClientes.setLayoutManager(layoutManager);
        rcvClientes.setAdapter(new ClienteAdapter(listacliente));

    }

    public void carregar(){
        listacliente.add(new Cliente("12345678900", "Maria Luiza", "911112222","maria@gmail.com"));
        listacliente.add(new Cliente("11122233344", "Rita de Cassia", "933334444","rita@gmail.com"));
        listacliente.add(new Cliente("55566677788", "José Felix", "955556666","jose@gmail.com"));

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.itemTAtualizar:
                intent = new Intent(getBaseContext(),AtualizarActivity.class);
                startActivity(intent);
//                finish();
                return true;
            case R.id.itemTConfiguracoes:
                intent = new Intent(getBaseContext(),ItensHome.class);
                intent.putExtra("nomeBotao","Configuracoes");
//                finish();
                return true;
            case R.id.itemTSair:
                intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navHome:
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.navEstoque: {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Estoque");
                startActivity(it);
                finish();
                break;
            }
            case R.id.navPedidoVenda: {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Pedido de Venda");
                startActivity(it);
                finish();
                break;
            }
            case R.id.navProdutos: {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Produtos");
                startActivity(it);
                finish();
                break;
            }
            case R.id.navClientes: {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Clientes");
                startActivity(it);
                finish();
                break;
            }
            case R.id.navSair: {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                finish();
                break;
            }
            default: {
                Toast.makeText(this, "Menu Default", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}