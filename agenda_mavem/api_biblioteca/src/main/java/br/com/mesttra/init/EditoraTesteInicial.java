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

         // Recupera o ID gerado para a editora salva
         int idGerado = editora.getId();
        
        System.out.println("Editora salva com o ID: " + idGerado);

         // Limpa a referência para garantir que estamos buscando do banco
         // Objeto é reaproveitar a variavel editora para armazenar o resultado da busca
         editora = null;

         // Busca a editora pelo ID
         editora = editoraDao.buscarPorId(idGerado);
         System.out.println("\nEditora encontrado por ID: " + editora);

         // Busca a editora por nome, retornando uma lista (pode haver mais de uma editora com o mesmo nome)
         List<EditoraModel> editoras = editoraDao.buscarPorNome("Editora"); 
         System.out.println("\nEditora encontrada por nome: " + editoras);

        // Realiza a atualização da editora
        editora.setEditora("Editora Code & Syntax Ltda.");
        editora.setTelefone("(11) 3344-5566");
        editoraDao.atualizar(editora.getId(), editora);

        //Busca a editora atualizada para verificar as mudanças
        editora = editoraDao.buscarPorId(editora.getId());
        System.out.println("\nEditora atualizada verifique os campos: " + editora);

        // Deleta a editora do banco de dados
        boolean deletado = editoraDao.deletar(editora.getId());
        System.out.println("\nEditora deletada: " + deletado);
    }
}