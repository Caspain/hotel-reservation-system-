package rervation_types;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basic_classess.Reservation_Files;
import ckeckStatus.Customer_Checkin;
import randomAccessFiles.RandomAccessFiles;
import reservation_Options.Manage_Reservations;
import reservations.*;

public class Guest extends Spa_Appointment implements Manage_Reservations,
		RandomAccessFiles {

	int hotelRoomNumber;
	
	RandomAccessFile accessFile3 = null;
	final String Spa_File_source = "C:\\OOP PROJECT FILES\\Spa.txt";
	final static int size_Of_Records =84;

	public Guest() {
		this.Room_Number = 0;
		hotelRoomNumber = 0;
	}

	public Guest(int Room_Number, int hotelRoomNumber) {
		this.Room_Number = Room_Number;
		this.hotelRoomNumber = hotelRoomNumber;
	}

	@Override
	public void Random_Access_File_Writer() throws IOException, EOFException {
		accessFile3 = new RandomAccessFile(Spa_File_source, "rw");

		for (int j = 0; j < 1; j++) {
			File_Pointer = customer_IdentificationString - 1;
			File_Length = (int) accessFile3.length();
			accessFile3.seek(File_Pointer * size_Of_Records);

			accessFile3.writeInt(customer_IdentificationString);
			accessFile3.writeInt(Room_Number);
			accessFile3.writeInt(Month);
			accessFile3.writeInt(Day);
			accessFile3.writeInt(Year);
			accessFile3.writeInt(Hour);
			accessFile3.writeInt(Minute);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeUTF(First_Name);
			accessFile3.writeUTF(Last_Name);
			accessFile3.writeUTF(Spa_Requested);
			
			

		}

		accessFile3.close();

	}

	@Override
	public void Add_Reservation() {

		try {

			System.out.print("Enter Room Number: ");
			Room_Number = Integer.parseInt(int_Data_input.nextLine().trim());

			System.out.print("Enter First Name: ");
			First_Name = String_Data_input.nextLine();

			System.out.print("Enter Last Name: ");
			Last_Name = String_Data_input.nextLine();

			System.out.print("Type of Spa: ");
			Spa_Requested = String_Data_input.nextLine();

			Hour = calendar.get(Calendar.HOUR_OF_DAY);

			System.out.print("Day Selection  > Present|Later : ");
			{
				try_Again = String_Data_input.nextLine().toUpperCase().charAt(0);
				if (try_Again == 'P') {
					Day = calendar.get(Calendar.DAY_OF_WEEK);
				} else if (try_Again == 'L') {

					System.out.print("Day:");
					Day = Integer.parseInt(int_Data_input.nextLine().trim());
				} else {
					System.out.print("Not a Valid option.");
					do{
						System.out.println("do you want to try again[y/n]:");
						
						try_Again = String_Data_input.nextLine().toUpperCase().charAt(0);
						if (try_Again == 'N') {
							System.out.println("Good Bye");
							True_Break =false;
							break;
						}
						if (try_Again == 'Y') {
							System.out.print("Day Selection  > Present|Later : ");
							try_Again = String_Data_input.nextLine().toUpperCase().charAt(0);
							if (try_Again == 'P') {
								Day = calendar.get(Calendar.DAY_OF_WEEK);
								True_Break =true;
								break;
							} else if (try_Again == 'L') {

								System.out.print("Day:");
								Day = Integer.parseInt(int_Data_input.nextLine().trim());
								True_Break =true;
								break;
							}else{
								continue;
							}
							
							
						}
					
					}while(try_Again != 'N');
				}

			}

			Minute = calendar.get(Calendar.MINUTE);
			Month = calendar.get(Calendar.MONTH);
			Year = calendar.get(Calendar.YEAR);
			

		} catch (Exception e) {
			System.out.print("Not a Valid option.");
		}
		
		try {
			if(True_Break!=false){
				customer_IdentificationString = 2001 + (random.nextInt(3000-2000));
				System.out.println("Reservation Successfuly added : Here is your CIS: "+ customer_IdentificationString);
				Reservation_Type new_res = new Reservation_Type(0, 0, 0, First_Name,Last_Name, Day, Month, Year, Minute, Hour);
				
			Random_Access_File_Writer();
			
			}
		} 
		catch (IOException e) {
			System.out.print(e.getMessage());
		}

	}

	@Override
	public void Delete_Reservation() throws IOException {
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine().trim());//potential problem use trim
		File_Pointer = tempc - 1;
		accessFile3 = new RandomAccessFile(Spa_File_source, "rw");
		for (int d = 0; d < number_of_records && accessFile3.length() != -1; d++) { // update
			

			File_Length = (int) accessFile3.length();

			accessFile3.seek(File_Pointer * size_Of_Records);
			
			customer_IdentificationString = accessFile3.readInt();
			
			if (tempc == customer_IdentificationString) {
				found = true;
			} else {
				found = false;
			}

		}
		if(found ==false)
			System.out.println("No Such Record exist.");
		else{

		// --------------------------------------------------------------
			accessFile3.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString =0;
			accessFile3.writeInt(customer_IdentificationString);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeInt(0);
			accessFile3.writeUTF("1234567890123456");
			accessFile3.writeUTF("1234567890123456");
			accessFile3.writeUTF("1234567890123456");
			

		// --------------------------------------------------------------
		
		System.out.println("Record as been successfully deleted.");
		}
		accessFile3.close();

	}

	@Override
	public void Update_Reservation() throws IOException {

		int tempc = 0;

		accessFile3 = new RandomAccessFile(Spa_File_source, "rw");
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine().trim());
		File_Pointer = tempc - 1;
		int menu_answer = 0;

		// ------------------------------------------------------------

		for (int d = 0; d < number_of_records; d++) { // update number of
														// records to 1000
			

			File_Length = (int) accessFile3.length();
			accessFile3.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile3.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
				break;

			} else {
				found = false;
			}

		}
		// -------------------------------------------------

		if (found == false) {
			System.out.println("Cant Update,File Not Found");
		} else {
			do{
			try{
			System.out.println("File/Record Founded: ");
			System.out.println("Which Record do you want to update ? ");
			System.out.println("Room Number      =>[1]");
			System.out.println("First_Name       =>[2]");
			System.out.println("Last_Name        =>[3]");
			System.out.println("Spa request		 =>[4]");
			System.out.println("Day 			 =>[5]");
			System.out.println("Choice: ");
			
			
	
			menu_answer = Integer.parseInt(int_Data_input.nextLine().trim());

			switch (menu_answer) {
			case 1: {
				
				guest_read_in(tempc, accessFile3, size_Of_Records);
				System.out.print("Enter New room number: ");
				int tRoom_Number = Integer.parseInt(int_Data_input.nextLine().trim());
				Guest_Write_out(customer_IdentificationString, accessFile3, size_Of_Records, tRoom_Number, First_Name, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
				
				
			}
				break;

			case 2: {
				
				
				
				
				
				guest_read_in(tempc, accessFile3, size_Of_Records);
				System.out.print("Enter New  First_Name : ");
				String	tFirst_Name = String_Data_input.nextLine();
				
				if (tFirst_Name.length() > 16) {
					tempFirstNameString = tFirst_Name.substring(0, 16);

				} else if (tFirst_Name.length() < 16) {

					String_Length_Difference = 16 - tFirst_Name.length();
					for (int c = 0; c < String_Length_Difference; c++) {

						tempFirstNameString = tFirst_Name += " ";
					}

				} else {

					tempFirstNameString = tFirst_Name;
				}
				
				Guest_Write_out(customer_IdentificationString, accessFile3, size_Of_Records, Room_Number, tempFirstNameString, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
			
				
			}
				break;

				
				
				
			case 3: {
				
				guest_read_in(tempc, accessFile3, size_Of_Records);
				System.out.print("Enter New  Last_Name : ");
				String	tLast_Name = String_Data_input.nextLine();
				
				if (tLast_Name.length() > 16) {
					tempLastNameString = tLast_Name.substring(0, 16);

				} else if (tLast_Name.length() < 16) {

					String_Length_Difference = 16 - tLast_Name.length();
					for (int c = 0; c < String_Length_Difference; c++) {

						tempLastNameString = tLast_Name += " ";
					}

				} else {

					tempLastNameString = tLast_Name;
				}
//				Guest_Write_out(customer_IdentificationString, accessFile3, size_Of_Records, Room_Number, First_Name, tempLastNameString, Day, Month, Year, Minute, Hour, Spa_Requested);
				
			}
				break;

		

			case 4: {
				guest_read_in(tempc, accessFile3, size_Of_Records);
				System.out.print("Enter New  spa request : ");
				String tSpa_Requested = String_Data_input.nextLine();
			Guest_Write_out(customer_IdentificationString, accessFile3, size_Of_Records, Room_Number, First_Name, Last_Name, Day, Month, Year, Minute, Hour, tSpa_Requested);
				
			}
				break;

			case 5: {
				
				
				guest_read_in(tempc, accessFile3, size_Of_Records);
				System.out.print("Enter New Day : ");
				int tDay = Integer.parseInt(int_Data_input.nextLine().trim());
				Guest_Write_out(customer_IdentificationString, accessFile3, size_Of_Records, Room_Number, First_Name, Last_Name, tDay, Month, Year, Minute, Hour, Spa_Requested);
			}
				break;
			default: {
				System.out.print("Invalid Selection!");
				break;
			}
			}// end switch

			
		}catch(Exception a){
			System.err.println(a.getMessage());
		}
			System.out.println("Do you wish to continue updating[y|n]:");
			try_Again = int_Data_input.nextLine().toUpperCase().charAt(0);
			if (try_Again=='Y') {
				break;
			}else if(try_Again=='N'){
				continue;
				}
			
		}while(try_Again!='Y');
	}

		// ---------------------------------------------------------------
		accessFile3.close();

	}

	@Override
	public void view_Reservation() throws IOException {
		
		
		
		accessFile3 = new RandomAccessFile(Spa_File_source, "r");

		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine());

		for (int d = 0; d < number_of_records ; d++) {

			File_Pointer = tempc - 1;
			accessFile3.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile3.readInt();

			if (tempc == customer_IdentificationString) {
				found = true;
				Room_Number = accessFile3.readInt();
				Month = accessFile3.readInt();
				Day = accessFile3.readInt();
				Year = accessFile3.readInt();
				Hour = accessFile3.readInt();
				Minute=	accessFile3.readInt();
				accessFile3.readInt();
				accessFile3.readInt();
				accessFile3.readInt();
				First_Name = accessFile3.readUTF();
				Last_Name = accessFile3.readUTF();
				Spa_Requested = accessFile3.readUTF();
				
				
				
				
				
	
				break;

			} else {

				found = false;
				
			}
		}

		// ----------------------------------------------------------------------
		if (found == false) {
			System.out.println("Record Not Found");

		} else {
			JFrame frame = new JFrame("| G U E S T | R E S E R V A T I O N [S]");
			DefaultTableModel Tmodel = new DefaultTableModel();
			JTable table=new JTable(Tmodel);
			frame.setVisible(true);
			frame.pack();
			frame.setLocationRelativeTo(table);
			Tmodel.addColumn("CIS");
			Tmodel.addColumn("ROOM NUMBER");
			Tmodel.addColumn("MONTH");
			Tmodel.addColumn("DAY");
			Tmodel.addColumn("YEAR");
			Tmodel.addColumn("HOUR");
			Tmodel.addColumn("MINUTE");
			Tmodel.addColumn("FIRST NAME");
			Tmodel.addColumn("LAST NAME");
			Tmodel.addColumn("SPA TYPE");
			table.setAutoCreateRowSorter(true);
			table.setCellSelectionEnabled(true);
			table.setSelectionBackground(Color.green);
			table.setSelectionForeground(Color.PINK);
			frame.add(new JScrollPane(table));
			
				Tmodel.addRow(new Object[] {customer_IdentificationString,Room_Number,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		accessFile3.close();
		
	}
///////////////////////////////////////////////////////////////|||||////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	public boolean Search(int tempc) throws IOException {

		accessFile3 = new RandomAccessFile(Spa_File_source, "r");

		for (int d = 0; d < number_of_records && accessFile3.length() != -1; d++) {
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile3.length();

			accessFile3.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile3.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
			} else {
				found = false;
			}

		}// end for
		accessFile3.close();
		return found;

	}
	
	
	//-------------------------------------------------------------------------------------------||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	
	
	
	
	public int Modified_Search( int Room_Number,String Spa_Requested, int temp_Day, String temp_FirstName, String temp_LastName,int customer_IdentificationString) throws IOException {


		accessFile3 = new RandomAccessFile(Spa_File_source, "r");
		int Rval=0;
		for (int d = 0; d < number_of_records; d++) { 
																			
			File_Pointer = customer_IdentificationString - 1;

			File_Length = (int) accessFile3.length();

			accessFile3.seek(File_Pointer * size_Of_Records);
			
			this.customer_IdentificationString = accessFile3.readInt();//check
			
			if (this.customer_IdentificationString == customer_IdentificationString) {
				
				this.Room_Number = accessFile3.readInt();
				Month = accessFile3.readInt();
				Day = accessFile3.readInt();
				Year = accessFile3.readInt();
				Hour = accessFile3.readInt();
				Minute=	accessFile3.readInt();
				accessFile3.readInt();
				accessFile3.readInt();
				accessFile3.readInt();
				First_Name = accessFile3.readUTF();
				Last_Name = accessFile3.readUTF();
				this.Spa_Requested = accessFile3.readUTF();
				found = true;
				break;
				
				
				
				
				
				
			} else {
				found = false;
			}

		}
		if (found == true) {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if ((this.customer_IdentificationString == customer_IdentificationString)  && temp_FirstName.equalsIgnoreCase(this.First_Name.trim()) && (temp_LastName.equalsIgnoreCase(this.Last_Name.trim()) && (this.Room_Number == Room_Number ) && (this.Spa_Requested.equalsIgnoreCase(Spa_Requested)) && (Day == temp_Day)))  
			{
				
				
				Rval=1;

			}else{
				Rval= -1;
			}
		} 
		
		return Rval;

	}
	
	
	
	
	
	
	
	
////////////////////////////////////////////////////////////////////|||||/////////////////////////////////////////////////////////////////////////////////

public void Active_Guest() throws IOException{

	int extract_Data = 0;
	accessFile3 = new RandomAccessFile(Spa_File_source, "r");
	
	int counter = 0;
	@SuppressWarnings("resource")
	Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\activeSpaMembers.txt"));
	Reservation_Files gfFiles=new Reservation_Files();
	int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\activeSpaMembers.txt");
	
	if(res >0){
	
	int[] arr=new int[1000];
	
	
	while (FileScanner.hasNext() ){
		
		extract_Data=FileScanner.nextInt();
		arr[counter]=extract_Data;
		counter++;
	}
	
	for (int i = 0; i <counter; i++) {
		 File_Pointer = arr[i] -1;
		accessFile3.seek(File_Pointer*size_Of_Records);
		
		customer_IdentificationString = accessFile3.readInt();
		Room_Number = accessFile3.readInt();
		Month = accessFile3.readInt();
		Day = accessFile3.readInt();
		Year = accessFile3.readInt();
		Hour = accessFile3.readInt();
		Minute=	accessFile3.readInt();
		accessFile3.readInt();
		accessFile3.readInt();
		accessFile3.readInt();
		First_Name = accessFile3.readUTF();
		Last_Name = accessFile3.readUTF();
		Spa_Requested = accessFile3.readUTF();
		//------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////////
		
JFrame frame = new JFrame("A C T I V E| GUEST | R E S E R V A T I O N [S]");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("ROOM NUMBER");
Tmodel.addColumn("MONTH");
Tmodel.addColumn("DAY");
Tmodel.addColumn("YEAR");
Tmodel.addColumn("HOUR");
Tmodel.addColumn("MINUTE");
Tmodel.addColumn("FIRST NAME");
Tmodel.addColumn("LAST NAME");
Tmodel.addColumn("SPA TYPE");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.green);
table.setSelectionForeground(Color.PINK);
frame.add(new JScrollPane(table));


////////////////////////////////////////////////////////////////////////////////
		Tmodel.addRow(new Object[]{customer_IdentificationString,Room_Number,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
		
		
	}
	
		}
	
	
	
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void CanceledGuestReservation() throws IOException{
	
	
	int extract_Data = 0;
	accessFile3 = new RandomAccessFile(Spa_File_source, "r");
	
	int counter = 0;
	@SuppressWarnings("resource")
	Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\CanceledSpaReservations.txt"));
	Reservation_Files gfFiles=new Reservation_Files();
	int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\CanceledSpaReservations.txt");
	
	if(res >0){
		
	
	int[] arr=new int[1000];
	
	
	while (FileScanner.hasNext() ){
		
		extract_Data=FileScanner.nextInt();
		if(extract_Data>=2000 && extract_Data<=3000)
		arr[counter]=extract_Data;
		counter++;
	}
	if(extract_Data>=2000 && extract_Data<=3000){
	for (int i = 0; i <counter; i++) {
		 File_Pointer = arr[i] -1;
		accessFile3.seek(File_Pointer*size_Of_Records);
		
		customer_IdentificationString = accessFile3.readInt();
		Room_Number = accessFile3.readInt();
		Month = accessFile3.readInt();
		Day = accessFile3.readInt();
		Year = accessFile3.readInt();
		Hour = accessFile3.readInt();
		Minute=	accessFile3.readInt();
		accessFile3.readInt();
		accessFile3.readInt();
		accessFile3.readInt();
		First_Name = accessFile3.readUTF();
		Last_Name = accessFile3.readUTF();
		Spa_Requested = accessFile3.readUTF();
		//------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////////
		
JFrame frame = new JFrame("C A N C E L E D | GUEST | R E S E R V A T I O N [S]");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("ROOM NUMBER");
Tmodel.addColumn("MONTH");
Tmodel.addColumn("DAY");
Tmodel.addColumn("YEAR");
Tmodel.addColumn("HOUR");
Tmodel.addColumn("MINUTE");
Tmodel.addColumn("FIRST NAME");
Tmodel.addColumn("LAST NAME");
Tmodel.addColumn("SPA TYPE");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.MAGENTA);
frame.add(new JScrollPane(table));


////////////////////////////////////////////////////////////////////////////////

		Tmodel.addRow(new Object[]{customer_IdentificationString,Room_Number,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
		
		
	}
	
 }
}else{
	
	System.out.printf("There are zero records to procesed." + " Error " + " Empty File");
	}
	
		}
}
