package br.com.fiap.bean;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValidacaoViaCep {
    public static Endereco buscarEnderecoPorCEP(String cep) throws Exception {
        URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(conexao.getInputStream());
        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(reader, Endereco.class);
        return endereco;
    }
}
