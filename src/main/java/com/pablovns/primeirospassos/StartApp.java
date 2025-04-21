package com.pablovns.primeirospassos;

import com.pablovns.primeirospassos.model.Usuario;
import com.pablovns.primeirospassos.repository.UsuarioRepository;
import com.pablovns.primeirospassos.util.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StartApp implements CommandLineRunner {

    private final UsuarioRepository userRepository;

    @Autowired
    public StartApp(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();

            final String nomeGerado = EmailGenerator.generateRandomUsername();
            usuario.setNome(nomeGerado);
            usuario.setEmail(EmailGenerator.generateRandomEmail(nomeGerado));
            usuario.setSenha(nomeGerado + "12345");
            usuario.setCreatedAt(LocalDateTime.now());
            Thread.sleep(100);
//            userRepository.save(usuario);
        }

        for (Usuario u : userRepository.findAll()) {
            System.out.println(u);
        }
    }
}
