package rest.service.util;

public class Constants {
	
	public class User{

		public static final String checkPasswordByID = "SELECT password = PASSWORD(?) FROM User WHERE id_user = ?";
		
		public static final String getAll = "SELECT * FROM User";
		public static final String getByID = "SELECT * FROM User WHERE id_user = ?";
		
		public static final String post = "INSERT INTO User(pseudo, password, email) VALUES(?, PASSWORD(?), ?)";

		public static final String putPasswordByID = "UPDATE User SET password = PASSWORD(?) WHERE id_user = ?";
		public static final String putEmailByID = "UPDATE User SET email = ? WHERE id_user = ?";

		public static final String deleteByID = "DELETE FROM User WHERE id_user = ?";

	}
	
	public class Rate{
		
		public static final String getAll = "SELECT * FROM Rate WHERE id_user = ?";
		public static final String getByID = "SELECT * FROM Rate WHERE (id_rate = ? AND id_user = ?)";
		
		public static final String post = "INSERT INTO Rate(value, id_user, id_multimedia) VALUES(?, ?, ?)";
		
		public static final String putByID = "UPDATE Rate SET value = ? WHERE (id_rate = ? AND id_user = ?)";

		public static final String deleteByID = "DELETE FROM Rate WHERE (id_rate = ? AND id_user = ?)";

	}
	
	public class Comment{
		
		public static final String getAll = "SELECT * FROM Comment WHERE id_user = ?";
		public static final String getByID = "SELECT * FROM Comment WHERE (id_comment = ? AND id_user = ?)";
		
		public static final String post = "INSERT INTO Comment(value, id_user, id_multimedia) VALUES(?, ?, ?)";
		
		public static final String putByID = "UPDATE Comment SET value = ? WHERE (id_comment = ? AND id_user = ?)";

		public static final String deleteByID = "DELETE FROM Comment WHERE (id_comment = ? AND id_user = ?)";

	}
	

	public class Multimedia{
		public static final String getByID = "SELECT * FROM Multimedia WHERE ID_multimedia = ?";
		public static final String getTypeByID = "SELECT category FROM Multimedia WHERE ID_multimedia = ?";
		public static final String getBookChildID = "SELECT id_book FROM book WHERE ID_multimedia = ?";
		public static final String getFilmChildID = "SELECT id_film FROM film WHERE ID_multimedia = ?";
		public static final String getVideoGameChildID = "SELECT id_videoGame FROM videoGame WHERE ID_multimedia = ?";
		
		public static final String post = "INSERT INTO multimedia (title, language, genre, category, status, ID_uploader, description, date_release) VALUES(?,?,?,?,?,?,?,?)";
	}
	

	public class Film{
		public static final String getAll = "SELECT * FROM Film_v";
		public static final String getByID = "SELECT * FROM Film_v WHERE (id_film = ?)";
		
		public static final String post ="INSERT INTO Film (director, productor, mainCast, duration, ID_multimedia) VALUES(?,?,?,?,?)";
		
		public static final String deleteByID = "DELETE FROM Film WHERE id_film = ?";
	}


	public class Book{
		public static final String getAll = "SELECT * FROM Book_v";
		public static final String getByID = "SELECT * FROM Book_v WHERE (id_book = ?)";
		
		public static final String deleteByID = "DELETE FROM Book WHERE id_book = ?";
		public static final String putByID = "UPDATE Book SET value = ? WHERE (id_comment = ? AND id_user = ?)";
		public static final String post = "INSERT INTO `book` (author, publisher, id_multimedia) VALUES(?,?,?)";
	}


	public class VideoGame{
		public static final String getAll = "SELECT * FROM VideoGame_v";
		public static final String getByID = "SELECT * FROM VideoGame_v WHERE id_videoGame = ?";
		
		public static final String deleteByID = "DELETE FROM VideoGame WHERE id_videoGame = ?";
		
		public static final String post ="INSERT INTO VideoGame (developer, publisher, ID_multimedia) VALUES(?,?,?)";
	}
	
}
