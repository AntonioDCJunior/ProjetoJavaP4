package br.unipe.mlp.banco.conta.dados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlServer {

    private String host;
    private String user;
    private String pass;
    private String database;
    private String porta;
    
    public Connection connect;
    
    public SqlServer ( String host, String database, String user, String pass, String porta ) {
        this.pass = pass;
        this.user = user;
        this.host = host;
        this.database = database;
        this.porta = porta;
    }
   
    public boolean conectar() {
        boolean isConnected = false;
        
        String url;
        String userName   = this.user;
        String passName   = this.pass;
        url = "jdbc:sqlserver://"+ this.host+":" +this.porta + ";databaseName=" +this.database;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            this.connect = DriverManager.getConnection(url,userName, passName);
            isConnected = true;
        } catch( SQLException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( InstantiationException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        }
       
        return isConnected;
    }
   
    public boolean desconectar() {
        boolean isConnected = false;
       
        String url;
        String portNumber = "1433";
        String userName   = this.user;
        String passName   = this.pass;
        url = "jdbc:sqlserver://"+ this.host+":" +portNumber + ";databaseName=" +this.database;
        	
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            this.connect = DriverManager.getConnection(url,userName, passName);
            this.connect.close();
            isConnected = true;
        } catch( SQLException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( ClassNotFoundException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( InstantiationException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( IllegalAccessException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
       
        return isConnected;
    }

    public ResultSet executar( String query ) {
        Statement st;
        ResultSet rs;
       
        try {
            st = this.connect.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
       
        return null;
    }
   
    public int inserir( String query ) {
        Statement st;
        int result = -1;
       
        try {
            st = this.connect.createStatement();
            result = st.executeUpdate(query);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
       
        return result;
    }
    
}

