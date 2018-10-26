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
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class AtualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recupera o Button e o ProgressBar do XML
        Button btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        final ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progBarAtualizar);

        //Evento de click do botão
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quando clica no botão torna visível o ProgressBar
                mProgressBar.setVisibility(View.VISIBLE);


                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Depois que passa os 10s "esconde" o ProgressBar
                                mProgressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }, 10000);//milisegundos
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
