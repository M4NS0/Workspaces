package com.example.aula_19.service;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HttpInserir extends AsyncTask<Void,Void,String> {

    String nome;
    String telefone;

    public HttpInserir(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    @Override
    protected String doInBackground(Void... voids) {

        StringBuilder stringBuilder = new StringBuilder();

        try {

            URL url = new URL("http://www.ferdinandiz.com.br/inserir_aula12.php?nome="+this.nome+"&tel="+this.telefone);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()){

                stringBuilder.append(sc.next());

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
