package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button bt_cadastro,bt_login;
    private EditText edt_Email,edt_Senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_cadastro = findViewById(R.id.bt_cadastrar);
        bt_login = findViewById(R.id.bt_logar);
        edt_Email = findViewById(R.id.edt_email);
        edt_Senha = findViewById(R.id.edt_senha);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _email,_senha;
                _email = edt_Email.getText().toString();
                _senha = edt_Senha.getText().toString();
                if(!TextUtils.isEmpty(_email) || !TextUtils.isEmpty(_senha)) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(_email, _senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this, Perfil.class);
                                startActivity(intent);
                            } else {
                                String erro = task.getException().getMessage();
                                Toast.makeText(MainActivity.this, "" + erro, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        bt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Cadastro.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser != null){
            Intent intent = new Intent(MainActivity.this,Perfil.class);
            startActivity(intent);
            finish();
        }
    }
}