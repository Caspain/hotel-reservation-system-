package basic_classess;


import java.io.IOException;


import custom_exceptions.CharacterIndexException;

public class Main_Executor {

	public static void main(String[] args) throws IOException {
	
		Login_User login_User=new Login_User();
		
		try {
		
			
			login_User.Login_user();
			
		} catch (CharacterIndexException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
