package com.example.restaurantproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    private void inicializar(){
        etUsuario = findViewById(R.id.login_et_usuario);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_et_login);
    }

    public void handlerBtnLogin(View view) {
        // Busqueda de usuario y password en la base de datos
        boolean loginCorrecto = true;

        if(loginCorrecto){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("usuario", "mesero@gmail.com");
            startActivity(intent);
        }else{
            Toast.makeText(this, "Nombre de usuario o contraseña incorrecto",
                    Toast.LENGTH_LONG).show();
        }

    }
}
