package rest.resource.util;




public class JSON_response {

	private String status;
	private Object message;
	
	public JSON_response(Object message){
		
		if(message instanceof Exception){
			
			this.status = "Error";
			this.message = ((Exception)message).getMessage();
			
		}
		else if(message == null){
			
			this.status = "Error";
			this.message = "NULL";
			
		}
		else{
			
			this.status = "Succes";
			this.message = message;
			
		}
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @return the message
	 */
	public Object getMessage() {
		return message;
	}
	
	
}
