

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	 BookDAO bookDAO ;
		public void init() throws ServletException {
			  String jdbcURL = "jdbc:mysql://localhost:3306/book_db";
			     String jdbcUsername="root";
			     String jdbcPassword="";
			 bookDAO = new BookDAO(jdbcURL, jdbcUsername,jdbcPassword);
			 
		}
	
		 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
			}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//   Book book;
//try {
//	book = bookDAO.selectbook(1);
//	PrintWriter pw = response.getWriter(); 
//	pw.print(book.toString());
//	 
//	 
//} catch (SQLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//  

		String action =req.getServletPath();
		try {
			switch (action) {
			case "/search":
				searchBook(req, resp);
				break;
			case "/insert":
				insertBook(req,resp);
				break;
			case "/delete":
				deleteBook(req,resp);
				break;
			case "/edit":
				showEditForm(req,resp);
				break;
			case "/update":
				updateBook(req,resp);
				break;
			default:
				listBook(req,resp);
				break;
		} 
			}catch (SQLException ex) {
			throw new ServletException (ex) ;
		}
		
	}

	private void listBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> listBook = bookDAO.listAllBook();
		req.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher =req.getRequestDispatcher("listpage.jsp");
		dispatcher.forward(req, resp);
		
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.selectbook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editpage.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
 
    }
	private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));
 
        Book book = new Book(id, title, author, price);
        bookDAO.updatebook(book);
        resp.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		bookDAO.deletebook(id);
		resp.sendRedirect("list");
	}

	private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));
 
        Book newBook = new Book(title, author, price);
        bookDAO.insertbook(newBook);
        resp.sendRedirect("list");
	}

	private void searchBook(HttpServletRequest req, HttpServletResponse resp)throws IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
        Book existingBook = bookDAO.selectbook(id);
        List<Book> listBook = bookDAO.searchBook(id);
		req.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listpage.jsp");
        req.setAttribute("book", existingBook);
        dispatcher.forward(req, resp);
 
	}


}
