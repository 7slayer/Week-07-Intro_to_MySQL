package projects.exception;

//Show this in my video presentation after this I will need to show my DbConnection Class

@SuppressWarnings("serial")
//Class that we will use to catch a SQLException
public class DbException extends RuntimeException {
	//Constructors for the class that calls the constructors of the super parent class
	public DbException(String message) {
		super(message);
	}
	
	public DbException(Throwable cause) {
		super(cause);
	}
	
	public DbException(String message, Throwable cause) {
		super(message, cause);
	}
}
