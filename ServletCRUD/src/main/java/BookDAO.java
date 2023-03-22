
import java.sql.*;
import java.util.*;

public class BookDAO {
	 private String jdbcURL;
	    private String jdbcUsername;
	    private String jdbcPassword;
	    private Connection jdbcConnection;
	    
	    public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
	    	this.jdbcURL = jdbcURL;
	        this.jdbcUsername = jdbcUsername;
	        this.jdbcPassword = jdbcPassword;
	    }
	public void connect() throws SQLException {
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
			}catch(ClassNotFoundException e){
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
		}
	}
	public void disconnect() throws SQLException {
		 if (jdbcConnection != null && !jdbcConnection.isClosed()) {
	            jdbcConnection.close();
	        }
	}
	public boolean insertbook(Book book) throws SQLException {
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        System.out.println(sql);
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
	}
	public boolean updatebook(Book book)throws SQLException {
		String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;  
	}
	public boolean deletebook(int id) throws SQLException {
		 String sql = "DELETE FROM book where id = ?";
         
	        connect();
	         
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setInt(1, id);
	         
	        boolean rowDeleted = statement.executeUpdate() > 0;
	        statement.close();
	        disconnect();
	        return rowDeleted; 	
	}
	public  List<Book> listAllBook()throws SQLException {
		 List<Book> listBook = new ArrayList<>();
         
	        String sql = "SELECT * FROM book";

	        
	        connect();
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	           
	            Book book = new Book(id, title, author, price);
	            listBook.add(book);
	          
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        disconnect();
	         
	        return listBook;
	}
	public List<Book> searchBook(int id)throws SQLException{
		List<Book> listBook = new ArrayList<>();
		Book book = null;
		String sql = "SELECT * FROM book WHERE id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String title = resultSet.getString("title");
//            String author = resultSet.getString("author");
//            float price = resultSet.getFloat("price");
//           
//            Book book = new Book(id, title, author, price);
//            listBook.add(book);
//          
//        }
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            System.out.println(id+","+title+","+author);
            book = new Book(id, title, author, price);
            listBook.add(book);
        }
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
	}
	public Book selectbook(int id) throws SQLException {
		Book book = null;
        String sql = "SELECT * FROM book WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            System.out.println(id+","+title+","+author);
            book = new Book(id, title, author, price);
        }
         
        resultSet.close();
        statement.close();
         
        return book;		
	}
}
