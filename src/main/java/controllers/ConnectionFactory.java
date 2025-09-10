package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String url = "jdbc:sqlite:database.db";


    public static Connection getConnection(){
        try{
            System.out.println("Conectado com sucesso");
            return DriverManager.getConnection(url);
        }catch(Exception e){
            throw new RuntimeException("Erro na conexão: " + e);
        }
    }


}
