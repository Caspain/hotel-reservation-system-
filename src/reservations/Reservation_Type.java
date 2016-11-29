package reservations;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import rervation_types.Hotel_Room;
import rervation_types.Spa_Appointment;
import utility_classes.*;

import java.util.GregorianCalendar;

import basic_classess.Menu_Options;
import basic_classess.Reservation_Files;
public class Reservation_Type {
	
	protected String Spa_Requested;
	protected String Spa_Appointment_Type;
	protected int Room_Number;
	protected Random random = new Random();
	protected boolean found =true;//make in super class
	protected Calendar calendar = new GregorianCalendar();
	protected String tempFirstNameString = " ";
	protected String tempLastNameString= " ";
	protected  Scanner int_Data_input = new Scanner(System.in);
	protected Scanner String_Data_input = new Scanner(System.in);
	protected  int Area_Code;
	protected int Exchange;
	protected int Line;
	protected  String First_Name;
	protected String Last_Name;
	protected int Day;
	protected int Month;
	protected int Year;
	protected int Minute;
	protected int Hour;
	protected char try_Again = ' ';
	protected long File_Pointer = 0;
	protected  int size_Of_Records =0;
	protected final static int number_of_records = 1000;
	protected long File_Length = 0;
	protected int String_Length_Difference = 0;
	protected int Duration_Of_Stay = 0;
	protected int Occupancy = 0;
	protected int ID;
	
public String reservation_Appointment_Type;
public int reservation_Options_type_int_sub = 0;
public	Contact contact = new Contact();
public	FullName fullname = new FullName();
public	Reservation_Date reservation_date = new Reservation_Date();
public	Reservation_Time reservation_Time = new Reservation_Time();
protected int customer_IdentificationString = 0;


	public Reservation_Type(String reservation_Appointment_Type) {
		this.reservation_Appointment_Type = reservation_Appointment_Type;
		
	}

	public Reservation_Type() {
		this.reservation_Appointment_Type = " ";
	}


	public Reservation_Type(int Area_Code, int Exchange, int Line,String First_Name, String Last_Name, int Day, int Month, int Year,int Minute, int Hour) {
		contact.Set_Contact(Area_Code, Exchange, Line);
		fullname.Set_FullName(First_Name, Last_Name);
		reservation_date.Set_Reservation_Date(Day, Month, Year);
		reservation_Time.Set_Reservation_Time(Minute, Hour);
	}


	
public void hotel_read_in(int cus_,RandomAccessFile accessFile,int Size) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile.seek(File_Pointer * Size);
	customer_IdentificationString = accessFile.readInt();
	Area_Code = accessFile.readInt();
	Exchange = accessFile.readInt();
	Line = accessFile.readInt();
	First_Name = accessFile.readUTF();
	Last_Name = accessFile.readUTF();
	 Duration_Of_Stay = accessFile.readInt();
	Occupancy = accessFile.readInt();
	Hour = accessFile.readInt();
	Minute = accessFile.readInt();
	Month = accessFile.readInt();
	Day = accessFile.readInt();
	Year = accessFile.readInt();
	}


public void Hotel_Write_out(int cus_,RandomAccessFile accessFile,int Size,int Area_Code, int Exchange, int Line,String First_Name, String Last_Name, int Day, int Month, int Year,int Minute, int Hour,int dur,int occ) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile.seek(File_Pointer * Size);
	accessFile.writeInt(cus_);
	
	accessFile.writeInt(Area_Code);
	
	accessFile.writeInt(Exchange);
	
	accessFile.writeInt(Line);
	

	accessFile.writeUTF(First_Name);
	

	accessFile.writeUTF(Last_Name);
	
	accessFile.writeInt(dur);
	
	accessFile.writeInt(occ);
	
	accessFile.writeInt(Hour);
	
	accessFile.writeInt(Minute);

	accessFile.writeInt(Month);
	
	accessFile.writeInt(Day);
	
	accessFile.writeInt(Year);
	
}


////////////////////////////////////////////////////////////////////////////////////////////
public void guest_read_in(int cus_,RandomAccessFile accessFile3,int Size) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile3.seek(File_Pointer * Size);
	
	
	
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
	
	}


/////////////////////////////////////////////////////////////////////////////////////////////////


public void Guest_Write_out(int cus_,RandomAccessFile accessFile3,int Size,int Room_Number,String First_Name, String Last_Name, int Day, int Month, int Year,int Minute, int Hour,String Spa_Requested) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile3.seek(File_Pointer * Size);
	
	accessFile3.writeInt(cus_);
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
	
	//accessFile3.close();
	
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


public void walkINClient_read_in(int cus_,RandomAccessFile accessFile2,int Size) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile2.seek(File_Pointer * Size);
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
	
	
	}



//////////////////////////////////////////////////////////////////////////////////////////////////////



public void walkInClient_Write_out(int cus_,RandomAccessFile accessFile2,int Size,int ID,int Line,int Area_Code,int Exchange ,String First_Name, String Last_Name, int Day, int Month, int Year,int Minute, int Hour,String Spa_Requested) throws IOException{
	File_Pointer = cus_ - 1;
	accessFile2.seek(File_Pointer * Size);
	
	
	accessFile2.writeInt(cus_);
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
	//accessFile2.close();
	
	}
//////////////////////////////////////////////////////////////////////////////////

	


}
