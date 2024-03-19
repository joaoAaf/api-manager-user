package apiManagerUser.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import apiManagerUser.domain.User;
import apiManagerUser.dto.Email;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
public class EmailService {

    public Email emailContent(User user) {
        return new Email(
            "Cadastro de Usuário", 
            user.getEmail(), 
            "Bem Vindo, "+user.getName()+"!", 
            "Olá, "+user.getName()+", seu cadastro foi realizado com sucesso!",
            "API Manager User");    
    }
    
    public void sendEmail(Email email) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"name\":\"" + email.name() + "\",\"to\":\"" + email.to()
                + "\",\"subject\":\"" + email.subject() + "\",\"text\":\"" + email.text() + "\",\"category\":\""
                + email.category() + "\"}", mediaType);
        Request request = new Request.Builder()
                .url("http://localhost:8080/email")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).execute().close();
    }

}
