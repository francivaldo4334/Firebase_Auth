package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private Button bt_deslogar,bt_apagar_conta;
    private FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        bt_deslogar = findViewById(R.id.bt_deslogar);
        bt_apagar_conta = findViewById(R.id.bt_apagar_conta);
        currentuser = FirebaseAuth.getInstance().getCurrentUser();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent= new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_apagar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Perfil.this, "sua conta foi excluida com sucesso!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Perfil.this,MainActivity.class));
                            finish();
                        } else {

                            Toast.makeText(Perfil.this, "erro ao excluir sua conta",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}