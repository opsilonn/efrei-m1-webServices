package rest.resources.util;

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
}
