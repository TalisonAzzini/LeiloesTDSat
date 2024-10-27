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
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar.");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try {
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            return listagem;
        }
        catch(Exception e) {
            return null;
        }
    }
    
    public void venderProduto(int id) {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status=? WHERE id=?";
        try {
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, "VendidO");
            prep.setInt(2, id);
            int linhaAfetada = prep.executeUpdate();
            if(linhaAfetada>0) {
                JOptionPane.showMessageDialog(null, "Produto vendido.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto.");
        }
    }

}