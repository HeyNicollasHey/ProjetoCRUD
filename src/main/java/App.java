import connection.ConnectionPostgreSQL;
import dao.PessoaDAO;
import domain.Pessoa;

import java.sql.SQLException;

public class App {
    public static void main(String[] args){
        ConnectionPostgreSQL.getConnection();

        //var pessoa = new PessoaDAO();
        //var nicollas = new Pessoa(rs.getLong("id"), "Nicollas","12332145699");

        //pessoa.save(nicollas);

        //var maria = new Pessoa("Maria","12343325678");
        //var gustavo = new Pessoa("Gustavo","12345676511");

        //pessoa.getAll().forEach(System.out::println);

        //pessoa.save(maria);
        //pessoa.save(gustavo);
        //System.out.println(pessoa.findByCPF("12345676511"));

        var pessoaDAO = new PessoaDAO();

        var pessoa = pessoaDAO.findById(1l);
        pessoa.setNome("Nicollaaass");
        pessoa.setCpf("12345678998");

        pessoaDAO.update(pessoa);

        //pessoaDAO.delete(4l);


    }
}
