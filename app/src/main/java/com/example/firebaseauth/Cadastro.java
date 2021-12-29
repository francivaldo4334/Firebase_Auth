package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {
    private EditText edt_email,edt_senha;
    private Button bt_cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edt_email = findViewById(R.id.edt_email_cadastro);
        edt_senha = findViewById(R.id.edt_senha_cadastro);
        bt_cadastrar = findViewById(R.id.bt_cadastrar_cadastro);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _Email,_Senha;
                _Email = edt_email.getText().toString();
                _Senha = edt_senha.getText().toString();
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(_Email, _Senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Cadastro.this, "cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Cadastro.this,Perfil.class));
                                    finish();
                                } else {
                                    String erro = task.getException().getMessage();
                                    Toast.makeText(Cadastro.this, "" + erro, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}