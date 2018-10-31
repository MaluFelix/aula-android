package br.com.sevenestoque.sevenestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtUsuario = (TextView) findViewById(R.id.txtUsuario);
                TextView txtSenha = (TextView) findViewById(R.id.txtSenha);
                String usuario = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();
                if (usuario.equals("aluno")&& senha.equals("impacta")){
                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(it);
                }else{
                    alert("Login ou senha incorretos");
                }
            }
        });

    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
