package basic_classess;

import custom_exceptions.*;
import reports.Reservation_Reports;
import rervation_types.Hotel_Room;
import rervation_types.Spa_Appointment;



import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;


import ckeckStatus.Customer_CheckOut;
import ckeckStatus.Customer_Checkin;

public class Menu_Options  {
	final String Hotel_File_source = "C:\\OOP PROJECT FILES\\NewRandom.txt";
	final String Spa_File_source = "C:\\OOP PROJECT FILES\\Spa.txt";
	public char test_answer;
	public char Next_Answer = ' ';
	public char answer = ' ';
	protected int n = 0;
	public Scanner menuu_input = new Scanner(System.in);
	Hotel_Room hr = new Hotel_Room();
	Spa_Appointment spa_Appointment = new Spa_Appointment();
	Scanner scanner = new Scanner(System.in);
	
	
	public char Choices(){
	//PER LOOPING HERE
		
	try{
		
		System.out.println("\n");
		System.out.println("---MENU OPTIONS---");
		System.out.println("\n");
		System.out.println("Initialize File     ->  [I]");
		System.out.println("Add Reservation     ->  [A]");
		System.out.println("Delete Reservation  ->  [D]");
		System.out.println("View Reservation    ->  [V]");
		System.out.println("Update Reservation  ->  [U]");
		System.out.println("Log Out			     >  [M]");
		System.out.println("Check In Customer   ->  [C]");
		System.out.println("Check Out Customer  ->  [O]");
		System.out.println("Reservation Reports ->  [R]");
		System.out.println("Choice: => ");

		answer = scanner.nextLine().trim().toUpperCase().charAt(0);
	}catch(Exception e){
		System.err.println(e.getMessage());
	}
		
			return answer;
		
	}
				
	
	{
		do{
			
					test_answer =Choices();
	
				switch(test_answer){
				
				
				case 'A':
					try {
						n = Confirm_Selection();
					if(n ==2)
					{
						
						hr.Add_Reservation();
						
					}else if(n == 1)
					{
						
						spa_Appointment.spa_decider('A');
						
					}
					
						
					} 
					
					catch (InputMismatchException e) {
						
						System.err.println(e.getMessage());
						
						
					}
					break;//-----------------------------------------------------------------
					
				case 'D':
					try {
						n = Confirm_Selection();
						if(n ==2)
						{
							
							hr.Delete_Reservation();
							
						}else if(n ==1)
						{
							spa_Appointment.spa_decider('D');
						}
						
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;//----------------------------------------------------------------------

				case 'V':
					try {
						n = Confirm_Selection();
					
						if(n ==2)
						{
							
							hr.view_Reservation();
							
						}else if(n ==1)
						{
							spa_Appointment.spa_decider('V');
						}
						
						
						
					} catch (Exception e) {
						System.err.println("Error record does not exist");
					}
					break;//-----------------------------------------------------------------------

				case 'U':
					try {
						n = Confirm_Selection();
						if(n ==2)
						{
							
							hr.Update_Reservation();
							
						}else if(n ==1)
						{
							spa_Appointment.spa_decider('U');
						}
					} catch (Exception e) {
						System.err.println("Error record does not exist");
					}
					break;//------------------------------------------------------------------------
					
				case 'M':
					try {
						Login_User log=new Login_User();
						log.Login_user();
					} catch (CharacterIndexException e) {
						System.err.println(e.getMessage());
					} catch ( Exception e) {
						System.out.println("Error 7.9");
					}
					break;//----------------------------------------------------------------------------
					
					
					
					
				case 'I':
					try {
						System.out.print("Getting the file ready,please wait.");
						RandomAccessFile accessFile = null;

						accessFile = new RandomAccessFile(Hotel_File_source, "rw");
						
						for (int i = 0; i < 1000; i++) {
							accessFile.seek(accessFile.length());
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeUTF("oooooooooooooooo");//dummy data
								accessFile.writeUTF("oooooooooooooooo");
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);
								accessFile.writeInt(0);

						}

						accessFile.close();
						
						RandomAccessFile accessFile2 = null;
						accessFile2 = new RandomAccessFile(Spa_File_source, "rw");
						
						for (int i = 0; i < 1000; i++) {
							accessFile2.seek(accessFile2.length());
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
								accessFile2.writeInt(0);
							
							accessFile2.writeUTF("1234567890123456");//dummy data
							accessFile2.writeUTF("1234567890123456");
							accessFile2.writeUTF("1234567890123456");

						}
						accessFile2.close();
					} 
					
					catch (IOException e) {
						
						System.err.println(e.getMessage());
						
						
					}
					System.out.print("File sucessfuly initialized.");
					
					break;//-------------------------------------------------------------------------------------------------------------
					
					
				case 'C':
				{
					Customer_Checkin Cuscheckin=new Customer_Checkin();
					Cuscheckin.find_Customer_Reservation();
				}break;
						
				case 'O':
				{
					Customer_CheckOut checkOut=new Customer_CheckOut();
					checkOut.find_Customer_Reservation();
				}break;
				case 'R':
				{
					Reservation_Reports reports=new Reservation_Reports();
					reports.generateReports();
				}break;
				
				default:
				{
					System.out.println("Sorry Invalid selction " + " -ERROR 1.3VR-");
				}
					
					}//end switch
			
		}while(test_answer != 'M');
	}//end inner block

	
	int Confirm_Selection(){
		int N =0;
		try{
			
			
		System.out.println("\t\tPlease Select Reservation Type:\n");
	
		System.out.println("Spa Appointment:[1]");
		System.out.println("Hotel Rooms:[2]");
		System.out.println("Choose: =>");
		N = Integer.parseInt(menuu_input.nextLine().trim());
		
		if(N ==1)
		{
			N=1;
		}
		else if (N ==2){
			
			N=2;
		}else{
			System.out.println("Not a valid choice.");
			N = -1;
		}
		
		}catch(Exception r){
			System.err.println(r.getMessage());
		}
		
		return N;
		
	}
	
		
	
}


