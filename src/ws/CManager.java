package ws;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import Beans.Book;
import Beans.Compte;



@WebService(name = "Service_Manager")
public class CManager {
	
	@WebMethod()
	 //******************   All Book  *******************
	
		public ArrayList<Book> AllBook(@WebParam(name = "requette")String sql) {
			ArrayList<Book> Books= new ArrayList<>();
			DBInteraction.connect();

			ResultSet rs = DBInteraction.Select(sql);
			
			try {
				while(rs.next())
				{
					
					Book b= new Book(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(1));
					Books.add(b);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			return Books ;
			
	
			
		}
	
	@WebMethod()
	public void AddBook(@WebParam(name = "FileName")String FileName ,@WebParam(name = "bookName")String bookName,@WebParam(name = "prix")String auteurName, int prix) {
		DBInteraction.connect();
		// int idmax = CManager.maxIdTable("book");
		 //int id = idmax +1 ;
		
		String sql="insert into book (name,auteur,prix) values('"+bookName+"','"+auteurName+"','"+prix+"')";
		DBInteraction.Update(sql);
		DBInteraction.deconnect();
	}
	
	@WebMethod()
	public Book FindBook(@WebParam(name = "nameBook")String nameBook){
		DBInteraction.connect();
        String sql="select * from book where name like lower('"+nameBook+"%')";
		Book c = null;
		ResultSet rs = DBInteraction.Select(sql);
		try {
			if(rs.next())
			{
				c = new Book();
				c.setId( rs.getInt(1));
				c.setBookName( rs.getString(2));
				c.setAuteurName( rs.getString(3));
				c.setPrix( rs.getInt(4));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return c ;
	}
	
	@WebMethod()
	public Book FindBookById(@WebParam(name = "id")int id){
		DBInteraction.connect();
        String sql="select * from book where id='"+id+"'";
		Book c = null;
		ResultSet rs = DBInteraction.Select(sql);
		try {
			if(rs.next())
			{
				c = new Book();
				c.setBookName( rs.getString(2));
				c.setAuteurName( rs.getString(3));
				c.setPrix( rs.getInt(4));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return c ;
	}


	
	//###############################  for compete ####################################
	@WebMethod()
	public void  Register(@WebParam(name = "name")String name, @WebParam(name = "last_name")String last_name, @WebParam(name = "tell")String tell, @WebParam(name = "email")String email, @WebParam(name = "password")String password) {
		DBInteraction.connect();
	
		String sql="insert into compte1 (name,last_name,tell,email,password) values('"+name+"','"+last_name+"','"+tell+"','"+email+"','"+password+"')";
		DBInteraction.Update(sql);
		DBInteraction.deconnect();
	}
	
	@WebMethod()
	public Compte Authentificate(@WebParam(name = "log")String log, @WebParam(name = "pass")String pass) {
		Compte c=null;
		DBInteraction.connect();
		String sql="select * from compte1 where email='"+log+"' and password='"+pass+"'";
		
		ResultSet rs = DBInteraction.Select(sql);
		try {
			if(rs.next())
			{
				c=new Compte();
				c.setName(rs.getString(2));
				c.setLast_name(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	@WebMethod()
	public String FindType(@WebParam(name = "log")String log, @WebParam(name = "pass")String pass) {
		DBInteraction.connect();
		String sql="select TypeUser from compte1 where email='"+log+"' and password='"+pass+"'";
		String c = "" ;
		ResultSet rs = DBInteraction.Select(sql);
		try {
			if(rs.next())
			{
				
				
				c = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	
 
	
	
  //############################# for Punier ############################
	@WebMethod()
	public void AddBookToPanier(@WebParam(name = "bookName")String bookName, @WebParam(name = "auteurName")String auteurName, @WebParam(name = "prix")int prix) {
		DBInteraction.connect();
		int nb=1;
		String sql="insert into panier (name,auteur,prix,compt) values('"+bookName+"','"+auteurName+"','"+prix+"','"+nb+"')";
		DBInteraction.Update(sql);
		DBInteraction.deconnect();
	}
	@WebMethod()
	public void DeletBookPanier(@WebParam(name = "id")int id) {
		DBInteraction.connect();
		
		String sql="delete from panier where id='"+id+"'";
		DBInteraction.Update(sql);
		DBInteraction.deconnect();
	}
	@WebMethod()
	public int TotalPrixe(){
		DBInteraction.connect();
        String sql="select SUM(prix) from panier";
		int c = 0;
		ResultSet rs = DBInteraction.Select(sql);
		try {
			if(rs.next())
			{
				
				c = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return c ;
	}

}
