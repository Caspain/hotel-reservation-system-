package rervation_types;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
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
import reservations.Reservation_Type;

public class Walk_In_Client extends Spa_Appointment implements
		Manage_Reservations, RandomAccessFiles {

	RandomAccessFile accessFile2 = null;
	final String Spa_File_source = "C:\\OOP PROJECT FILES\\Spa.txt";
	
	final static int size_Of_Records = 84;

	public Walk_In_Client(int ID) {

		this.ID = ID;
	}

	public Walk_In_Client() {
		this.ID = 0;
	}

	@Override
	public void Random_Access_File_Writer() throws IOException, EOFException {

		accessFile2 = new RandomAccessFile(Spa_File_source, "rw");

		File_Length = (int) accessFile2.length();

		for (int j = 0; j < 1; j++) {
			File_Pointer = customer_IdentificationString - 1;
			File_Length = (int) accessFile2.length();
			accessFile2.seek(File_Pointer * size_Of_Records);

			accessFile2.writeInt(customer_IdentificationString);
			accessFile2.writeInt(ID);
			accessFile2.writeInt(Area_Code);
			accessFile2.writeInt(Exchange);
			accessFile2.writeInt(Line);
			accessFile2.writeInt(Month);
			accessFile2.writeInt(Day);
			accessFile2.writeInt(Year);
			accessFile2.writeInt(Hour);
			accessFile2.writeInt(Minute);
			accessFile2.writeUTF(First_Name);
			accessFile2.writeUTF(Last_Name);
			accessFile2.writeUTF(Spa_Requested);
			// ////////////////////////////////////

		}

		accessFile2.close();

	}

	@Override
	public void Add_Reservation() {
		System.out.println("Welcome to the Reservation Adder!");
		try {

			System.out.print("Enter ID: ");
			ID = Integer.parseInt(int_Data_input.nextLine().trim());

			System.out.print("Enter First Name: ");
			First_Name = String_Data_input.nextLine();

			System.out.print("Enter Last Name: ");
			Last_Name = String_Data_input.nextLine();

			System.out.print("Enter Area code: ");
			Area_Code = Integer.parseInt(int_Data_input.nextLine().trim());

			System.out.print("Enter  Exchange: ");
			Exchange = Integer.parseInt(int_Data_input.nextLine().trim());

			System.out.print("Enter Line Number: ");
			Line = Integer.parseInt(int_Data_input.nextLine().trim());

			Hour = calendar.get(Calendar.HOUR_OF_DAY);

			System.out.print("Day Selection  > Present|Later : ");
			{
				try_Again = String_Data_input.nextLine().toUpperCase()
						.charAt(0);
				if (try_Again == 'P') {
					Day = calendar.get(Calendar.DAY_OF_WEEK);
					True_Break = true;
				} else if (try_Again == 'L') {

					System.out.print("Day:");
					Day = Integer.parseInt(int_Data_input.nextLine().trim());

					True_Break = true;
				} else {
					System.out.print("Not a Valid option.");
					do {
						System.out.println("do you want to try again[y/n]:");

						try_Again = String_Data_input.nextLine().toUpperCase()
								.charAt(0);
						if (try_Again == 'N') {
							System.out.println("Good Bye");
							True_Break = false;
							break;
						}
						if (try_Again == 'Y') {
							System.out.print("Day Selection  > Present|Later : ");
							try_Again = String_Data_input.nextLine()
									.toUpperCase().charAt(0);
							if (try_Again == 'P') {
								Day = calendar.get(Calendar.DAY_OF_WEEK);
								True_Break = true;
								break;
							} else if (try_Again == 'L') {

								System.out.print("Day:");
								Day = Integer.parseInt(int_Data_input
										.nextLine().trim());
								True_Break = true;
								break;
							} else {
								continue;
							}

						}

					} while (try_Again != 'N');
				}

			}

			Minute = calendar.get(Calendar.MINUTE);
			Month = calendar.get(Calendar.MONTH);
			Year = calendar.get(Calendar.YEAR);

			System.out.print("Type of Spa: ");
			Spa_Requested = String_Data_input.nextLine();

			try {
				if (True_Break) {
					customer_IdentificationString = (3000 + (random.nextInt(4000-3000)));
					System.out.println("Reservation Successfuly added : Here is your CIS: "+ customer_IdentificationString);
					Reservation_Type new_res = new Reservation_Type(Area_Code, Exchange,Line, First_Name, Last_Name, Day, Month, Year, Minute, Hour);
					
					Random_Access_File_Writer();
					
				}
			} catch (IOException e) {
				System.err.println(" Error" + e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		

	}

	@Override
	public void Delete_Reservation() throws IOException {
		accessFile2 = new RandomAccessFile(Spa_File_source, "rw");
		
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine().trim());

		
		File_Pointer = tempc - 1;
		for (int d = 0; d < number_of_records ; d++) { // update
			

			File_Length = (int) accessFile2.length();

			accessFile2.seek(File_Pointer * size_Of_Records);

			customer_IdentificationString = accessFile2.readInt();

			if (tempc == customer_IdentificationString) {
				found = true;
			} else {
				found = false;
			}

		}
		if (found == false)
			System.out.println("No Such Record exist.");
		else {
			// -----------------------------------------------------------
			accessFile2.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = 0;
			accessFile2.writeInt(customer_IdentificationString);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeInt(0);
			accessFile2.writeUTF("1234567890123456");
			accessFile2.writeUTF("1234567890123456");
			accessFile2.writeUTF("1234567890123456");

			// -----------------------------------------------------------
			System.out.println("Record as been successfully deleted.");
		}

		accessFile2.close();

	}

	@Override
	public void Update_Reservation() throws IOException {
		accessFile2 = new RandomAccessFile(Spa_File_source, "rw");
		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine());
		File_Pointer = tempc - 1;

		// ------------------------------------------------------------

		for (int d = 0; d < number_of_records; d++) { // update number of
														// records to 1000
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile2.length();
			accessFile2.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile2.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
				break;

			} else {
				found = false;
			}

		}
		// ------------------------------------------------------------------

		if (found == false) {
			System.out.println("Cant Update,record Not Found " + " ERROR 2.4");
		} else {
			do{
			try{
			System.out.println("File/Record Founded: ");
			System.out.println("Which Record do you want to update ? ");
			System.out.println("Area_Code        =>[1]");
			System.out.println("Exchange         =>[2]");
			System.out.println("Line             =>[3]");
			System.out.println("First_Name       =>[4]");
			System.out.println("Last_Name        =>[5]");
			System.out.println("ID 				 =>[6]");
			System.out.println("Spa Requested    =>[7]");
			System.out.println("Day 			 =>[8]");
			System.out.println("Choice: ");
			menu_answer = Integer.parseInt(int_Data_input.nextLine());

			switch (menu_answer) {
			case 1: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter New Area code: ");
				int tArea_Code = Integer.parseInt(int_Data_input.nextLine().trim());
				walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, tArea_Code, Exchange,First_Name, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
				
				
			}
				break;

			case 2: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter New Exchange  code: ");
			int	tExchange = Integer.parseInt(int_Data_input.nextLine().trim());
			walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, Area_Code, tExchange,First_Name, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
				
			}
				break;

			case 3: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter New Line   code: ");
			int	 tLine = Integer.parseInt(int_Data_input.nextLine().trim());
			walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, tLine, Area_Code, Exchange,First_Name, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
			
			}
				break;

			case 4: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				
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
				
				walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, Area_Code, Exchange,tempFirstNameString, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
				// ----------------------------------------------------------

			}
				break;

			case 5: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				
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
				
				walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, Area_Code, Exchange,First_Name, tempLastNameString, Day, Month, Year, Minute, Hour, Spa_Requested);

			}
				break;

			case 6: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter ID: ");
				int tID = Integer.parseInt(int_Data_input.nextLine().trim());
				
				walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, tID, Line, Area_Code, Exchange,First_Name, Last_Name, Day, Month, Year, Minute, Hour, Spa_Requested);
				
			}
				break;

			case 7: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter New Spa request : ");
				String tSpa_Requested = String_Data_input.nextLine();
				walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, Area_Code, Exchange,First_Name, Last_Name, Day, Month, Year, Minute, Hour, tSpa_Requested);
				
			}
				break;

			case 8: {
				walkINClient_read_in(customer_IdentificationString, accessFile2, size_Of_Records);
				System.out.print("Enter New Day : ");
			int	tDay = Integer.parseInt(int_Data_input.nextLine().trim());
			walkInClient_Write_out(customer_IdentificationString, accessFile2, size_Of_Records, ID, Line, Area_Code, Exchange,First_Name, Last_Name, tDay, Month, Year, Minute, Hour, Spa_Requested);
			}
				break;
			default: {
				System.out.print("Invalid Selection!");
				break;
			}
			}// end switch
			
		}catch(Exception s){
			System.err.println(s.getLocalizedMessage());
			}
			
			
			System.out.println("Do you wish to continue updating[y|n]:");
			try_Again = int_Data_input.nextLine().toUpperCase().charAt(0);
			if (try_Again=='Y') {
				break;
			}else if(try_Again=='N'){
				continue;
				}
			
		}while(try_Again!='Y');
		}// end else

		// ---------------------------------------------------------------
		accessFile2.close();

	}

	@Override
	public void view_Reservation() throws IOException {
		
		accessFile2 = new RandomAccessFile(Spa_File_source, "r");

		System.out.println("Please enter customer_Identification number: ");
		tempc = Integer.parseInt(int_Data_input.nextLine());

		for (int d = 0; d < number_of_records && accessFile2.length() != -1; d++) {
			File_Pointer = tempc - 1;
			accessFile2.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile2.readInt();

			if (tempc == customer_IdentificationString) {
				
				
				ID = accessFile2.readInt();
				Area_Code = accessFile2.readInt();
				Exchange = accessFile2.readInt();
				Line = accessFile2.readInt();
				Month = accessFile2.readInt();
				Day = accessFile2.readInt();
				Year = accessFile2.readInt();
				Hour = accessFile2.readInt();
				Minute = accessFile2.readInt();
				First_Name = accessFile2.readUTF();
				Last_Name = accessFile2.readUTF();
				Spa_Requested = accessFile2.readUTF();
				found = true;
				break;

			} else {
				found = false;

			}

		}

		if (found == false) {
			System.out.println("File Not Found");

		} else {
//////////////////////////////////////////////////////////////////
			
JFrame frame = new JFrame("W A L K I N - C L I E N T || R E S E R V A T I O N (S)");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("ID");
Tmodel.addColumn("AREA CODE");
Tmodel.addColumn("EXCHANGE");
Tmodel.addColumn("LINE");
Tmodel.addColumn("MONTH");
Tmodel.addColumn("DAY");
Tmodel.addColumn("YEAR");
Tmodel.addColumn("Hour");
Tmodel.addColumn("Minute");
Tmodel.addColumn("FIRST NAME");
Tmodel.addColumn("LAST NAME");
Tmodel.addColumn("SPA TYPE");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.gray);
frame.add(new JScrollPane(table));



//////////////////////////////////////////////////////////////
			Tmodel.addRow( new Object[]{ customer_IdentificationString,ID,Area_Code,Exchange,Line,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
		
		}
		accessFile2.close();
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean Search(int tempc) throws IOException {

		accessFile2 = new RandomAccessFile(Spa_File_source, "r");

		for (int d = 0; d < number_of_records; d++) {
			File_Pointer = tempc - 1;

			File_Length = (int) accessFile2.length();

			accessFile2.seek(File_Pointer * size_Of_Records);
			customer_IdentificationString = accessFile2.readInt();
			if (tempc == customer_IdentificationString) {
				found = true;
			} else {
				found = false;
			}

		}// end for
		accessFile2.close();
		return found;

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int Modified_Search(int ID, int customer_IdentificationString,
			int temp_Area_Code, int temp_Exchange, int temp_Line,
			String temp_FirstName, String temp_LastName, String Spa_Requested,
			int temp_Day) throws IOException {

		accessFile2 = new RandomAccessFile(Spa_File_source, "r");
		int Rval = 0;
		for (int d = 0; d < number_of_records && accessFile2.length() != -1; d++) {

			File_Pointer = customer_IdentificationString - 1;

			File_Length = (int) accessFile2.length();

			accessFile2.seek(File_Pointer * size_Of_Records);

			this.customer_IdentificationString = accessFile2.readInt();

			if (this.customer_IdentificationString == customer_IdentificationString) {
				this.ID = accessFile2.readInt();
				Area_Code = accessFile2.readInt();
				Exchange = accessFile2.readInt();
				Line = accessFile2.readInt();
				Month = accessFile2.readInt();
				Day = accessFile2.readInt();
				Year = accessFile2.readInt();
				Hour = accessFile2.readInt();
				Minute = accessFile2.readInt();
				First_Name = accessFile2.readUTF();
				Last_Name = accessFile2.readUTF();
				this.Spa_Requested = accessFile2.readUTF();
				found = true;
				break;
			} else {
				found = false;
			}

		}
		if (found == true) {
			// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// ///////////////////////////////////////////////////////////////////////////////////////////////////////////
			if ((this.customer_IdentificationString == customer_IdentificationString)
					&& (Area_Code == temp_Area_Code)
					&& (Exchange == temp_Exchange) && (Line == temp_Line)
					&& (temp_LastName.equalsIgnoreCase(this.Last_Name.trim())
					&& (temp_FirstName.equalsIgnoreCase(this.First_Name.trim()))
					&& (this.ID == ID)
					&& (this.Spa_Requested.equalsIgnoreCase(Spa_Requested)) && (Day == temp_Day))) {
			

				Rval = 1;

			} else {
				Rval = -1;
			}
		}

		return Rval;

	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	public void Active_walkInClient() throws IOException{
		
		int extract_Data = 0;
		accessFile2 = new RandomAccessFile(Spa_File_source, "r");
		
		int counter = 0;
		@SuppressWarnings("resource")
		Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\activeSpaMembers.txt"));
		Reservation_Files gfFiles=new Reservation_Files();
		int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\activeSpaMembers.txt");
		if(res>0)
		{
		int[] arr=new int[1000];
		
		
		while (FileScanner.hasNext() ){
			
			extract_Data=FileScanner.nextInt();
			arr[counter]=extract_Data;
			counter++;
		}
	
		for (int i = 0; i <counter; i++) {
			 File_Pointer = arr[i] -1;
			accessFile2.seek(File_Pointer*size_Of_Records);
			
			customer_IdentificationString = accessFile2.readInt();
			ID = accessFile2.readInt();
			Area_Code = accessFile2.readInt();
			Exchange = accessFile2.readInt();
			Line = accessFile2.readInt();
			Month = accessFile2.readInt();
			Day = accessFile2.readInt();
			Year = accessFile2.readInt();
			Hour = accessFile2.readInt();
			Minute = accessFile2.readInt();
			First_Name = accessFile2.readUTF();
			Last_Name = accessFile2.readUTF();
			Spa_Requested = accessFile2.readUTF();
			//-------------------------------------------------------------------------
//////////////////////////////////////////////////////////////////////////
JFrame frame = new JFrame("A C T I V E|| W A L K I N - C L I E N T || R E S E R V A T I O N (S)");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("ID");
Tmodel.addColumn("AREA CODE");
Tmodel.addColumn("EXCHANGE");
Tmodel.addColumn("LINE");
Tmodel.addColumn("MONTH");
Tmodel.addColumn("DAY");
Tmodel.addColumn("YEAR");
Tmodel.addColumn("Hour");
Tmodel.addColumn("Minute");
Tmodel.addColumn("FIRST NAME");
Tmodel.addColumn("LAST NAME");
Tmodel.addColumn("SPA TYPE");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.gray);
frame.add(new JScrollPane(table));




/////////////////////////////////////////////////////////////////////////
			Tmodel.addRow( new Object[]{ customer_IdentificationString,ID,Area_Code,Exchange,Line,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
			
			
		}
		}else{
			
			System.out.printf("There are zero records to procesed." + " Error " + " Empty File");
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////
	
	public void CanceledWalkInClientsRESERVATION() throws IOException{
		
		
		
		
		int extract_Data = 0;
		accessFile2 = new RandomAccessFile(Spa_File_source, "r");
		
		int counter = 0;
		@SuppressWarnings("resource")
		Scanner	FileScanner = new Scanner(new File("C:\\OOP PROJECT FILES\\CanceledSpaReservations.txt"));
		Reservation_Files gfFiles=new Reservation_Files();
		int res = gfFiles.Create_File("C:\\OOP PROJECT FILES\\CanceledSpaReservations.txt");
		
		if(res>0){
	
		
		int[] arr=new int[1000];
		
		
		while (FileScanner.hasNext() ){
			
			extract_Data=FileScanner.nextInt();
			if(extract_Data>=3000 && extract_Data<=4000)
			arr[counter]=extract_Data;
			counter++;
		}
		if(extract_Data>=3000 && extract_Data<=4000)
		{
			
			for (int i = 0; i <counter; i++) {
				 File_Pointer = arr[i] -1;
				accessFile2.seek(File_Pointer*size_Of_Records);
				
				customer_IdentificationString = accessFile2.readInt();
				ID = accessFile2.readInt();
				Area_Code = accessFile2.readInt();
				Exchange = accessFile2.readInt();
				Line = accessFile2.readInt();
				Month = accessFile2.readInt();
				Day = accessFile2.readInt();
				Year = accessFile2.readInt();
				Hour = accessFile2.readInt();
				Minute = accessFile2.readInt();
				First_Name = accessFile2.readUTF();
				Last_Name = accessFile2.readUTF();
				Spa_Requested = accessFile2.readUTF();
				//-------------------------------------------------------------------------
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
JFrame frame = new JFrame("C A N C E L E D || W A L K I N - C L I E N T || R E S E R V A T I O N (S)");
DefaultTableModel Tmodel = new DefaultTableModel();
JTable table=new JTable(Tmodel);
frame.setVisible(true);
frame.pack();
frame.setLocationRelativeTo(table);
Tmodel.addColumn("CIS");
Tmodel.addColumn("ID");
Tmodel.addColumn("AREA CODE");
Tmodel.addColumn("EXCHANGE");
Tmodel.addColumn("LINE");
Tmodel.addColumn("MONTH");
Tmodel.addColumn("DAY");
Tmodel.addColumn("YEAR");
Tmodel.addColumn("Hour");
Tmodel.addColumn("Minute");
Tmodel.addColumn("FIRST NAME");
Tmodel.addColumn("LAST NAME");
Tmodel.addColumn("SPA TYPE");
table.setAutoCreateRowSorter(true);
table.setCellSelectionEnabled(true);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.gray);
frame.add(new JScrollPane(table));




////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Tmodel.addRow( new Object[]{ customer_IdentificationString,ID,Area_Code,Exchange,Line,Month,Day,Year,Hour,Minute,First_Name,Last_Name,Spa_Requested});
				
				
			}
			
		}
			}else{
				
				System.out.printf("There are zero records to procesed." + " Error " + " Empty File");
			}
	}
}
