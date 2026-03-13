package br.com.mesttra.dao;

import br.com.mesttra.config.DatabaseConnection;
import br.com.mesttra.model.EditoraModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EditoraDAO {
    
    private final DatabaseConnection gerenciadorBancoDados;

    public EditoraDAO(DatabaseConnection gerenciadorBancoDados) {
        this.gerenciadorBancoDados = gerenciadorBancoDados;
    }

    // BUSCAR TODOS
    public List<EditoraModel> buscarTodos() {
        List<EditoraModel> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";
        
        try (Connection conexao = gerenciadorBancoDados.obterConexao();
             Statement instrucao = conexao.createStatement();
             ResultSet resultado = instrucao.executeQuery(sql)) {
                
            while (resultado.next()){
                EditoraModel editora = mapearResultSetParaEditoraModel(resultado);
                editoras.add(editora);
            }
        } catch(SQLException erro) {
            erro.printStackTrace();
        }
        
        return editoras;
    }

    // BUSCAR POR ID
    public EditoraModel buscarPorId(Integer id) {
        String sql = "SELECT * FROM editora WHERE id = ?";

        try (Connection conexao = gerenciadorBancoDados.obterConexao();
             PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            
                instrucao.setInt(1, id);
                try (ResultSet resultado = instrucao.executeQuery()) {
                    if (resultado.next()) {
                        return mapearResultSetParaEditoraModel(resultado);
                    }
                }

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return null;
    }

    // SALVAR (INSERT)
    private EditoraModel salvar(EditoraModel editora) {
        String sql = "INSERT INTO editora (editora, cnpj, email, telefone, cep, estado, bairro, endereco, nacionalidade, endereco_web" +            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = gerenciadorBancoDados.obterConexao();
             PreparedStatement instrucao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
                instrucao.setString(1, editora.getEditora());
                instrucao.setString(2, editora.getCnpj());
                instrucao.setString(3, editora.getEmail());
                instrucao.setString(4, editora.getTelefone());
                instrucao.setString(5, editora.getCep());
                instrucao.setString(6, editora.getEstado());
                instrucao.setString(8, editora.getBairro());
                instrucao.setString(9, editora.getEndereco());
                instrucao.setString(10, editora.getNacionalidade());
                instrucao.setString(11, editora.getEndereco_web());
                
                instrucao.executeUpdate();

                try (ResultSet chavesGeradas = instrucao.getGeneratedKeys()) {
                    if (chavesGeradas.next()) {
                        editora.setId(chavesGeradas.getInt(1));
                    }
                }
        
                return editora;
            } catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        }
    }

    // ATUALIZAR
    public EditoraModel atualizar(Integer id, EditoraModel editora){
        String sql = "UPDATE editora SET editora = ?, cnpj = ?, email = ?, telefone = ?, cep = ?" + 
                     "estado = ?, bairro = ?, endereco = ?, nacionalidade = ?, endereco_web = ? WHERE id = ?";

        try (Connection conexao = gerenciadorBancoDados.obterConexao(); 
             PreparedStatement instrucao = conexao.prepareStatement(sql)) {
            
                instrucao.setString(1, editora.getEditora());
                instrucao.setString(2, editora.getCnpj());
                instrucao.setString(3, editora.getEmail());
                instrucao.setString(4, editora.getTelefone());
                instrucao.setString(5, editora.getCep());
                instrucao.setString(6, editora.getEstado());
                instrucao.setString(7, editora.getBairro());
                instrucao.setString(8, editora.getEndereco());
                instrucao.setString(9, editora.getNacionalidade());
                instrucao.setString(10, editora.getEndereco_web());
                instrucao.setInt(11, id);

                int linhasAfetadas = instrucao.executeUpdate();

                if (linhasAfetadas > 0) {
                    editora.setId(id);
                    return editora;
                }

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return null;
    }

    // DELETAR (DELETE)
    public boolean deletar(Integer id) {
        String sql = "DELETE FROM editora WHERE id = ?";

        try (Connection conexao = gerenciadorBancoDados.obterConexao();
             PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                
                instrucao.setInt(1, id);
                int linhasAfetadas = instrucao.executeUpdate();

                return linhasAfetadas > 0;

        } catch (SQLException erro) {
            // Se for violação da integridade, fornecemos mensagem mais amigável
            if (erro instanceof java.sql.SQLIntegrityConstraintViolationException) {
                throw new RuntimeException("Não é possível deletar o aluno: existem registros relacionados");
            }
            throw new RuntimeException(erro);
        }
    }

    // Método auxiliar para mapear Resultset para EditoraModel
    private EditoraModel mapearResultSetParaEditoraModel(ResultSet resultado) throws SQLException {
        return new EditoraModel(
                resultado.getInt("id"),
                resultado.getString("editora"),
                resultado.getString("cnpj"),
                resultado.getString("email"),
                resultado.getString("telefone"),
                resultado.getString("cep"),
                resultado.getString("estado"),
                resultado.getString("bairro"),
                resultado.getString("endereco"),
                resultado.getString("nacionalidade"),
                resultado.getString("endereco_web")
        );
    }

    public List<EditoraModel> buscarPorNome(String editoraParte) {
        List<EditoraModel> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editoras WHERE nome LIKE ?";

        try (Connection conexao = gerenciadorBancoDados.obterConexao();
             PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                
                instrucao.setString(1, "%" + editoraParte + "%");
                try (ResultSet resultado = instrucao.executeQuery()) { 
                    while (resultado.next()) {
                        EditoraModel editora = mapearResultSetParaEditoraModel(resultado);
                        editoras.add(editora);
                    }
                }

            } catch(SQLException erro){
                erro.printStackTrace();
            }

        return editoras;
    }
}
