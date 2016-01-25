package com.example.lordzabrilon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
    Button btnMagic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMagic = (Button) findViewById(R.id.btnMagic);
        //LEL tienes que agregar los objetos de la vista a variables de su tipo y ponerles los listener
        btnMagic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new HttpRequestTask().execute();
            }

        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    //esta madre es la peticion, la que queria cambiar, para evitar poner un tipo de objeto como respuesta,
    //y manipular el string JSON, haber que mafufada se me ocurria
    private class HttpRequestTask extends AsyncTask<Void, Void, ObjResponse> {
        //tengo entendido que esto es lo que hace cuando das .execute();
        @Override
        protected ObjResponse doInBackground(Void... params) {
            try {
                final String url = "http://rest-service.guides.spring.io/greeting";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                //peticion a la respuesta y tipo de dato para mapear el JSON
                ObjResponse greeting = restTemplate.getForObject(url, ObjResponse.class);
                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        //y a como veo este es el callback de la peticion, no se si se ejecute siempre, o solo cuando tenga una respuesta positiva
        @Override
        protected void onPostExecute(ObjResponse greeting) {
            TextView greetingIdText = (TextView) findViewById(R.id.textView);
            greetingIdText.setText(greeting.getContent());
        }

    }
}
