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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroCliActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Button btnSalvarCliente;

    private EditText edtCpf;
    private EditText edtNome;
    private EditText edtContato;
    private EditText edtEmail;

    private FirebaseFirestore rFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cli);
        Bundle extra = getIntent().getExtras();
        String value = "";
        if(extra != null){
            value = extra.getString("nomeBotao");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        rFireStore = FirebaseFirestore.getInstance();

        btnSalvarCliente = (Button) findViewById(R.id.btnSalvarCliente);
        edtCpf = (EditText) findViewById(R.id.edtCpf);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtContato = (EditText) findViewById(R.id.edtContato);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CPF = edtCpf.getText().toString();
                String nome = edtNome.getText().toString();
                String contato = edtContato.getText().toString();
                String email = edtEmail.getText().toString();

                Map<String,String> clienteMap = new HashMap<>();
                clienteMap.put("CPF", CPF);
                clienteMap.put("Nome", nome);
                clienteMap.put("Contato", contato);
                clienteMap.put("Email", email);

                rFireStore.collection("clientes").document().set(clienteMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CadastroCliActivity.this, "Cliente cadastrado!", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getBaseContext(),ClientesActivity.class);
                        startActivity(it);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CadastroCliActivity.this, "Falha ao cadastrar!", Toast.LENGTH_SHORT).show();
                    }
                });
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