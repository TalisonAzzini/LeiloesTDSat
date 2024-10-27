import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?,?,?)";
        try {
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, "A venda");
            prep.execute();
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar.");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

