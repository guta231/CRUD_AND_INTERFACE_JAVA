package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Jogo;

public class JogoDAO {

    public JogoDAO() {}

    public void save(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO jogo (NOME, GENERO, STATUS) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getGenero());
            stmt.setString(3, jogo.getStatus());

            int rowAffected = stmt.executeUpdate();
            if (rowAffected == 0) {
                throw new SQLException("Nenhuma alteração feita");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    jogo.setId(rs.getInt(1));
                    System.out.println("Jogo " + jogo.getNome() + " inserido com sucesso!");
                }
            }

            if (!con.getAutoCommit()) {
                con.commit();
            }
        }
    }

    public List<Jogo> readAll() throws SQLException {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogo";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jogo j = new Jogo(rs.getString("NOME"), rs.getString("GENERO"), rs.getString("STATUS"));
                j.setId(rs.getInt("ID"));
                jogos.add(j);
            }
        }
        return jogos;
    }

    public List<Jogo> readById(int id) throws SQLException {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogo WHERE ID = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogo j = new Jogo(rs.getString("NOME"), rs.getString("GENERO"), rs.getString("STATUS"));
                    j.setId(rs.getInt("ID"));
                    jogos.add(j);
                }
            }
        }
        return jogos;
    }

    public void update(int id, String newStatus) throws SQLException {
        String sql = "UPDATE jogo SET STATUS = ? WHERE ID = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " linhas alteradas com sucesso!");

            if (!con.getAutoCommit()) {
                con.commit();
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM jogo WHERE ID = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " linhas deletadas com sucesso!");

            if (!con.getAutoCommit()) {
                con.commit();
            }
        }
    }

    public int selectMaxId() throws SQLException {
        String sql = "SELECT MAX(ID) AS max_id FROM jogo";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("max_id");
            }
        }
        return 0;
    }

    public int selectMinId() throws SQLException {
        String sql = "SELECT MIN(ID) AS min_id FROM jogo";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("min_id");
            }
        }
        return 0;
    }
}
