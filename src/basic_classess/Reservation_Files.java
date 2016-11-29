package basic_classess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reservation_Files {

	String file_Type;
	
	public Reservation_Files()
	{
		this.file_Type = "C:\\OOP PROJECT FILES\\default.txt";
	}
		public Reservation_Files(String file_Type)
		{
			this.file_Type = file_Type;
		}
		
	
	public int Create_File(String new_file) throws IOException
	{
		boolean F = false;
		File File_Name1 =new  File(new_file);
		if(!File_Name1.exists())
		{
			try {
				if (File_Name1.createNewFile())
				{
					System.out.println("File is created");
					F=true;
				}else{
					System.out.println("Error File not created!");
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				throw e;
			}
			
		}
		else {
			{
				F =false;
			}
		}
		return (int) File_Name1.length();
	}
	
	
	
	String Get_File()
	{
		return this.file_Type;
	}
}

	
