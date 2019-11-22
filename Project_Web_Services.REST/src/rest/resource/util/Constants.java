package rest.resource.util;

public class Constants {
	
	public class User{

		public static final String checkPasswordByID = "SELECT password = PASSWORD(?) FROM User WHERE id_user = ?";
		
		public static final String getAll = "SELECT * FROM User";
		public static final String getByID = "SELECT * FROM User WHERE id_user = ?";
		
		public static final String post = "INSERT INTO User(pseudo, password, email) VALUES(?, PASSWORD(?), ?)";
		
		public static final String putByID = "UPDATE User SET password = PASSWORD(?), email = ? WHERE id_user = ?";

		public static final String deleteByID = "DELETE FROM User WHERE id_user = ?";

	}
	
	public class Rate{
		
		public static final String getAll = "SELECT * FROM Rate WHERE id_user = ?";
		public static final String getByID = "SELECT * FROM Rate WHERE (id_rate = ? AND id_user = ?)";
		
		public static final String post = "INSERT INTO Rate(value, id_user, id_multimedia) VALUES(?, ?, ?)";
		
		public static final String putByID = "UPDATE Rate SET value = ? WHERE (id_rate = ? AND id_user = ?)";

		public static final String deleteByID = "DELETE FROM Rate WHERE (id_rate = ? AND id_user = ?)";

	}
	

	public class Film{
		public static final String getAll = "SELECT * FROM film";
		public static final String post ="INSERT INTO `film` (director, productor, mainCast, duration, ID_multimedia) VALUES(?,?,?,?,?)";
	}


	public class Book{

		
		public static final String getAll = "SELECT * FROM Book";
		public static final String getByID = "SELECT * FROM Book WHERE id_book = ?";
		public static final String deleteByID = "DELETE FROM Book WHERE id_book = ?";
	
		
		
		
	}
	

	public class Multimedia{
		public static final String getByID = "SELECT * FROM Multimedia WHERE ID_multimedia = ? ";
		public static final String post = "INSERT INTO multimedia (title, language, genre, category, status, ID_uploader, description) VALUES(?,?,?,?,?,?,?)";
	}
	
}
