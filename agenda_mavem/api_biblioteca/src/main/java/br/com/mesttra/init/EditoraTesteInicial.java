package br.com.mesttra.init;
import br.com.mesttra.model.EditoraModel;
import br.com.mesttra.dao.EditoraDAO;

import java.util.List;

// O QUE ESSAS CLASSES DO SPRINGBOOT FAZ?
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EditoraTesteInicial implements CommandLineRunner {

    @Autowired
    private EditoraDAO editoraDao;

    @Override public void run(String... args) throws Exception {
         // Realizar os testes em todos os metodos DAO

        System.out.println("\n===== TESTE EDITORA =====");

         // Constroi uma editora
        EditoraModel editora = new EditoraModel();

        editora.setEditora("Editora Code & Syntax Ltda.");
        editora.setCnpj("12.345.678/0001-90");
        editora.setEmail("contato@codesyntax.com.br");
        editora.setTelefone("(11) 3344-5566");
        editora.setCep("01310-100");
        editora.setEstado("São Paulo");
        editora.setBairro("Bela Vista");
        editora.setEndereco("Avenida Paulista, 1000 - Conjunto 42");
        editora.setNacionalidade("Brasileira");
        editora.setEndereco_web("www.codesyntax.com.br");

         // Salva a editora no banco de dados
        editoraDao.salvar(editora);
    }
}