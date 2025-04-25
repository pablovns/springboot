package com.pablovns.primeirospassos;

import com.pablovns.primeirospassos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements CommandLineRunner {

    // Maneira apropriada de fazer uma injeção de dependência (injeta no construtor ao invés de injetar na variável)
    private final UsuarioRepository userRepository;

    @Autowired
    public StartApp(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando app...");
    }
}
