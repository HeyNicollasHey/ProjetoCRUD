package dao;

import connection.ConnectionPostgreSQL;
import domain.Pessoa;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class PessoaDAO {
    private Connection connection;

    public PessoaDAO() {
        this.connection = ConnectionPostgreSQL.getConnection();
    }

    public void save(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa (nome, cpf) VALUES (?,?)";
        try {
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());

            stmt.execute();
            stmt.close();
            System.out.println("Operação realizada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Pessoa> getAll() {
        var pessoas = new ArrayList<Pessoa>();
        String sql = "SELECT * FROM PESSOA";
        try {
            var stmt = connection.prepareStatement(sql);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var pessoa = new Pessoa(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"));
                rs.close();
                pessoas.add(pessoa);
            }
            return pessoas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Pessoa findById(Long id) {
        var pessoa = new Pessoa();
        try {
            String sql = "SELECT * FROM PESSOA WHERE ID = ?";
            var stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setId(rs.getLong("id"));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pessoa;
    }

    public Pessoa findByCPF(String cpf) {
        var pessoa = new Pessoa();
        try {
            String sql = "SELECT * FROM PESSOA WHERE cpf = ?";
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setId(rs.getLong("id"));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pessoa;
    }

    public void update(Pessoa pessoa){
        try{
        String sql = "UPDATE PESSOA SET NOME = ?, CPF = ? WHERE ID = ?";
        var stmt = connection.prepareStatement(sql);
        stmt.setObject(1, pessoa.getNome());
            stmt.setObject(2, pessoa.getCpf());
            stmt.setObject(3, pessoa.getId());
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        try{
            String sql = "DELETE FROM PESSOA WHERE ID = ?";
            var stmt = connection.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.execute();
            stmt.close();
            System.out.println("Usuario removido com sucesso");
        } catch (SQLException ex){
            System.out.println("Erro aotentar deletar usuario " + ex.getMessage());
        }
    }
}
