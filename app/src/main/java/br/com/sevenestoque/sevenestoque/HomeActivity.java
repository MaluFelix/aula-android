package br.com.sevenestoque.sevenestoque;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnProdutos = (Button) findViewById(R.id.btnProdutos);
        btnProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Produtos");
                startActivity(it);

            }
        });

        Button btnEstoque = (Button) findViewById(R.id.btnEstoque);
        btnEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Estoque");
                startActivity(it);
              }
        });

        Button btnClientes = (Button) findViewById(R.id.btnClientes);
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), ItensHome.class);
                it.putExtra("nomeBotao","Clientes");
                startActivity(it);
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
//                finish();
                return true;
            case R.id.itemTConfiguracoes:
                intent = new Intent(getBaseContext(),ConfiguracoesActivity.class);
                startActivity(intent);
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
}

