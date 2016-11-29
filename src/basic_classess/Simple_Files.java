package basic_classess;

import java.io.IOException;

public abstract class Simple_Files {

	abstract void File_Writer();

	void File_Reader(String File_Source) {

	}
	final static int sleeper_number = 1000;
	public void sleeper()
	{
		try {
			Thread.sleep(sleeper_number);
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
			
		}
	}
	
}
