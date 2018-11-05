package br.com.sevenestoque.sevenestoque;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference estoqueRef = db.collection("users");
//    DocumentReference usersRef = db.document("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Boolean valorLembrar = Prefs.INSTANCE.getBoolean("Lembrar");
        String valorUsuario = Prefs.INSTANCE.getString("lembrarNome");
        String valorSenha = Prefs.INSTANCE.getString("lembrarSenha");

        TextView txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtUsuario.setText(valorUsuario);

        TextView txtSenha = (TextView) findViewById(R.id.txtSenha);
        txtSenha.setText(valorSenha);

        CheckBox ckbButton = (CheckBox) findViewById(R.id.ckbLembrar);
        ckbButton.setChecked(valorLembrar);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView txtUsuario = (TextView) findViewById(R.id.txtUsuario);
                TextView txtSenha = (TextView) findViewById(R.id.txtSenha);
                CheckBox valorLembrar = (CheckBox) findViewById(R.id.ckbLembrar);

                String usuario = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();



                Prefs.INSTANCE.setBoolean("Lembrar",valorLembrar.isChecked());
                if (valorLembrar.isChecked() == true){
                    Prefs.INSTANCE.setString("lembrarNome", usuario);
                    Prefs.INSTANCE.setString("lembrarSenha", senha);
                }
                else{

                    Prefs.INSTANCE.setString("lembrarNome", "");
                    Prefs.INSTANCE.setString("lembrarSenha", "");
                }


//                var oi = estoqueRef.whereEqualTo("user", "aluno").get();
//                Query query = estoqueRef.whereEqualTo("user", "aluno");
//                Log.d("query","Deu certo!");


                estoqueRef.whereEqualTo("user", txtUsuario.getText().toString() ).whereEqualTo("password", txtSenha.getText().toString() ).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    boolean usuarioValido = false;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Collection<Object> valores = document.getData().values();
                                        usuarioValido = true;

                                        for (Object val : valores ) {
                                            Log.d("kwl", val.toString());
                                        }
                                    }

                                    if (usuarioValido)
                                    {
//                                        Toast.makeText(v.getContext(), "Usuario ok",Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else {
                                        alert("Login ou senha incorretos");
                                    }
                                }
                                else {
                                    Toast.makeText(v.getContext(), "Ocorreu um erro ao validar",Toast.LENGTH_LONG).show();
                                    //Log.d(TAG, "Error getting documents: ", task.getException());
                                    //alert("Usuario invalido");
                                }
                            }
                        });

//                String usuario = txtUsuario.getText().toString();
//                String senha = txtSenha.getText().toString();
//
//                if (usuario.equals("aluno")&& senha.equals("impacta")){
//                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(it);
//                }else{
//                    alert("Login ou senha incorretos");
//                }
            }
        });



    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }


}
