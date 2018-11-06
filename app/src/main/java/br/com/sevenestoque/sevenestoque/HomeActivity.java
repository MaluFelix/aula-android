package br.com.sevenestoque.sevenestoque;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        Button btnEstoque = (Button) findViewById(R.id.btnEstoque);
        btnEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Estoque");
                startActivity(it);
                finish();
            }
        });

        Button btnVenda = (Button) findViewById(R.id.btnVenda);
        btnVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Pedido de Venda");
                startActivity(it);
                finish();
            }
        });

        Button btnProdutos = (Button) findViewById(R.id.btnProdutos);
        btnProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Produtos");
                startActivity(it);
                finish();
            }
        });

        Button btnClientes = (Button) findViewById(R.id.btnClientes);
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ClientesActivity.class);
                it.putExtra("nomeBotao","Clientes");
                startActivity(it);
                finish();
            }
        });
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
                finish();
                return true;
            case R.id.itemTConfiguracoes:
                intent = new Intent(getBaseContext(),ItensHome.class);
                intent.putExtra("nomeBotao","Configuracoes");
                finish();
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
                intent.putExtra("nomeBotao","Estoque");
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
                Intent it = new Intent(getBaseContext(), ClientesActivity.class);
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

