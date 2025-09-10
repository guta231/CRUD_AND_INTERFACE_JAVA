
import controllers.JogoDAO;
import models.Jogo;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    private static void refreshTable(JTable table) throws SQLException {

        JogoDAO dao = new JogoDAO();
        List<Jogo> jogos = dao.readAll();

        String[] columns = {"ID", "NOME", "GENERO", "STATUS"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Jogo j : jogos){
            model.addRow(new Object[]{
                    j.getId(),
                    j.getNome(),
                    j.getGenero(),
                    j.getStatus()
            });
        }
        table.setModel(model);

    }

    public static void main(String[] args) throws SQLException {



        JFrame frame = new JFrame("Biblioteca de jogos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(20, 2));

        inputPanel.add(new JLabel("Nome: "));
        JTextField nomeText = new JTextField();
        inputPanel.add(nomeText);
        inputPanel.add(new JLabel("Genero: "));
        JTextField generoText = new JTextField();
        inputPanel.add(generoText);
        inputPanel.add(new JLabel("Status: "));
        JTextField statusText = new JTextField();
        inputPanel.add(statusText);

        inputPanel.setPreferredSize(new Dimension(500, 800));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        System.out.println(buttonPanel.getPreferredSize());
        JButton addButton = new JButton("Adicionar");
        JButton selectAllButton = new JButton("Selecionar todos");
        JButton selectByIdButton = new JButton("Selecionar por ID");
        JButton deleteButton = new JButton("Excluir");

        buttonPanel.add(addButton);
        buttonPanel.add(selectAllButton);
        buttonPanel.add(selectByIdButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(buttonPanel);
        frame.add(inputPanel);

        String[] columns = {"ID", "Nome", "Genero", "Status"};
        Object[][] data = {};
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1280, 800));

        frame.add(scrollPane);

        frame.setVisible(true);

        selectAllButton.addActionListener(event ->{
            try{
                refreshTable(table);
            }catch(SQLException e){
                throw new RuntimeException("Erro ao atualizar tabela" + e.getMessage());
            }
        });

        addButton.addActionListener(event -> {

            JogoDAO dao = new JogoDAO();

            String nome = nomeText.getText();
            String genero = generoText.getText();
            String status = statusText.getText();


            Jogo jogo = new Jogo(nome, genero, status);

            try{
                dao.save(jogo);
            }catch(SQLException e){
                throw new RuntimeException("Erro ao adicionar jogo" + e.getMessage());
            }

            try {
                refreshTable(table);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar tabela" + e.getMessage());
            }
        });

        selectByIdButton.addActionListener(event -> {

            JogoDAO dao = new JogoDAO();

            JFrame frameId = new JFrame();
            frameId.setSize(400, 200);
            frameId.setVisible(true);

            JPanel idPanel = new JPanel();
            JSpinner idSpinner = new JSpinner();
            JButton idButton = new JButton("Ok");
            idPanel.add(idSpinner);
            idPanel.add(idButton);
            frameId.add(idPanel);

            idButton.addActionListener(ev ->{
                try{

                    List<Jogo> jogos = dao.readById((Integer)idSpinner.getValue());
                    DefaultTableModel model = new DefaultTableModel(columns, 0);
                    for(Jogo j : jogos){
                        model.addRow(new Object[]{
                                j.getId(),
                                j.getNome(),
                                j.getGenero(),
                                j.getStatus()
                        });
                    }
                    table.setModel(model);
                    frameId.dispose();
                }catch(SQLException e){
                    throw new RuntimeException("Erro ao buscar ID" + e.getMessage());
                }
            });



        });

        deleteButton.addActionListener(event -> {
            JogoDAO dao = new JogoDAO();
            int[] rows = table.getSelectedRows();
            for (int row : rows){
                try{
                    row = (int) table.getValueAt(row, 0);
                    dao.delete(row);
                }catch(SQLException e){
                    throw new RuntimeException("Erro ao excluir Jogo" + e.getMessage());
                }
            }
            try {
                refreshTable(table);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
