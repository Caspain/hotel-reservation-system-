package basic_classess;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.lang.System;


import custom_exceptions.CharacterIndexException;
import basic_classess.Reservation_Files;

public class Login_User extends Simple_Files {
	public String date_time = " ";
	String dateStr;
	String User_name;
	String user_Password;
	boolean checker = false;
	public char next_answer;
	public char try_agin = ' ';
	String test_name = " ";
	String test_pass = " ";
	Menu_Options options; 
	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	String File_Source = "C:\\OOPPROJECFILES\\login_file.txt";
	String TempPassword = " ";
	String TempUser = " ";
	Scanner FileScanner;
	Scanner input = new Scanner(System.in);
	Scanner try_Again_input = new Scanner(System.in);
	void Login_user() throws IOException, CharacterIndexException {

		do {
		
			
			
			String answer = " ";
			System.out.println("\n\n");
			System.out.println("WELCOME TO LOGIN SCREEN");

			System.out.println("New User: = > [N]");
			System.out.println("ExistingUser: = > [E]");
			System.out.println("Quit: = > [Q]");

			System.out.print("User Type :");
try{
			answer = input.nextLine().toUpperCase();
}catch(Exception e)
{
	System.out.println(e.getMessage());
}
			next_answer = answer.charAt(0);
			if (next_answer == 'Q') {
				System.out.print("Have a good day...good bye!");
				System.exit(0);
			}

			else if (next_answer == 'N') {
				System.out.println("Account Creation");
				Get_Info();

			} else if (next_answer == 'E') {

				System.out.println("Please enter the following");

				System.out.print("Enter password :");
				test_pass = scanner.nextLine();
				System.out.print("Enter username :");
				test_name = scanner2.nextLine();
				// File_Reader("C:\\OOP PROJECT FILES\\login_file.txt");

				try {
					FileScanner = new Scanner(new File(File_Source));
					while (FileScanner.hasNext() ) {
						TempPassword = FileScanner.next();
						TempUser = FileScanner.next();
						// date_time = FileScanner.nextLine();
						if (test_name.equals(TempUser)&& test_pass.equals(TempPassword)) {

							System.out.println("Login Successful!");
							checker =true;

							break;
						} 



						else {
							checker = false; // this part needs to be fixed

						}
					}
					if (checker == false) {
						do {
							System.out.println("Record not Found : do you want to try again[y/n]: => ");
							try_agin = try_Again_input.nextLine().toUpperCase().charAt(0);
							if (try_agin == 'N') {
								System.out.println("Good Bye");
								return;
							}
							if (try_agin == 'Y') {

								System.out.print("Please Select Your Choice:");
								break;
							}
						} while (try_agin != 'N');
					}else{

						options	= new Menu_Options();
						options.Choices();
					}
					FileScanner.close();

				} catch (Exception e) {
					System.err.println(e.getMessage());

				}

			} 

			else {
				System.out.println("Invalid Selection!");
				do {
					System.out.print("Do you want to try again:[y/n]:");
						try{
					try_agin = try_Again_input.nextLine().toUpperCase().charAt(0);
						}catch(Exception h){
							System.out.println(h.getMessage());
						}
					if (try_agin == 'N') {
						System.out.println("Good Bye");
						break;
					}
					if (try_agin == 'Y') {
						System.out.print("Please Select Your Choice:");
						break;
					}

				} while (try_agin != 'N');
			}
		
			
		} while (next_answer != 'Q' || try_agin != 'N');
		
		
	}// end login
	
	public Login_User() {
		User_name = "Clerk_Superve";
		user_Password = "ranch321789";

	}

	public Login_User(String User_name, String user_Password) {
		this.User_name = User_name;
		this.user_Password = user_Password;
	}

	void Get_Info() throws IOException {
		Scanner input2 = new Scanner(System.in);
		Scanner input3 = new Scanner(System.in);
		System.out.print("\n");
		System.out.print("Enter password :");
		this.user_Password = input3.nextLine();
		System.out.print("Enter UserName :");
		this.User_name = input3.nextLine();

		Reservation_Files reservation_Files = new Reservation_Files(
				File_Source);
		try {
			reservation_Files
			.Create_File(File_Source);
		} catch (IOException e) {

			System.err.println(e.getMessage());
			
		}catch(Exception k){
			System.err.println(k.getMessage());
		}

		// ///////////////////////////

		File_Writer();

		// //////////////////////////
		
	}

	void set_login() {

	}

	@Override
	void File_Writer() {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		dateStr = dateFormat.format(calendar.getTime());

		BufferedWriter outputWriter = null;
		try {
			System.out.println("Writing to file");
			outputWriter = new BufferedWriter(new FileWriter(
					File_Source , true));
			outputWriter.write(user_Password + "\t");

			outputWriter.write(User_name + "\t");

			// outputWriter.write(dateStr + "\t");
			outputWriter.write(System.getProperty("line.separator"));

		} catch (IOException | NullPointerException e) {
			System.err.println(e.getMessage());

		}

		finally {

			try {
				File_Reader(File_Source );
				outputWriter.flush();
				outputWriter.close();
				System.out.print("Redirecting");
				sleeper();
				System.out.print(".");
				sleeper();
				System.out.print(".");
				sleeper();
				System.out.print(".");
				sleeper();
			} catch (IOException e) {
				System.err.println(e.getMessage());

			}
		}
	}

	public void display() {
		System.out.println(this.user_Password + " " + this.User_name + " "+ this.date_time);
	}




}
