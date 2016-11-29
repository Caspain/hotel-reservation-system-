package reports;

import java.io.IOException;
import java.io.RandomAccessFile;

import rervation_types.Guest;
import rervation_types.Hotel_Room;
import rervation_types.Walk_In_Client;
import reservations.Reservation_Type;

@SuppressWarnings("unused")
public class Reservation_Reports extends Reservation_Type {
////////////////////////////////////////////////////////////////
	
		
		Hotel_Room room=new Hotel_Room();
		Guest guest=new Guest();
		Walk_In_Client client=new Walk_In_Client();
	
	
	
///////////////////////////////////////////////////////////////
	
	
	public void generateReports(){
		
		
		System.out.println("RESERVATION REPORTS");
		System.out.println("Active   = >[1]");
		System.out.println("Canceled = >[2]");
		System.out.println("Select:");
		try{
		reservation_Options_type_int_sub=Integer.parseInt(int_Data_input.nextLine().trim());
		}catch(Exception d)
		{
			System.err.println(d.getMessage());
		}
		
		
		
		 switch(reservation_Options_type_int_sub){
		 
		 case 1:
		 {
			 System.out.println("-:-You have chosen active reports:\nchoice:-:-");
			 
			System.out.println("Active Hotel =>[3]");
			System.out.println("Active Spa   =>[4]");
			System.out.println("Select:");
			try{
				reservation_Options_type_int_sub=Integer.parseInt(int_Data_input.nextLine().trim());
				}catch(Exception d)
				{
					System.err.println(d.getMessage());
				}
			
			switch(reservation_Options_type_int_sub){
			
			case 3:
				
			{
				
				try {
					room.active_Active_Hotel();
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}break;
			
			case 4:
			{
				
				 System.out.println("--You have chosen active spa--:");
				 
					System.out.println("Active Guest =>[5]");
					System.out.println("Active WalkInClient= >[6]");
					System.out.println("Select:");
					try{
						reservation_Options_type_int_sub=Integer.parseInt(int_Data_input.nextLine().trim());
						}catch(Exception d)
						{
							System.err.println(d.getMessage());
						}
					
					switch(reservation_Options_type_int_sub){
					case 5:
					{
						
						
						try {
							guest.Active_Guest();
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						
						
					}break;
					
					
					case 6:
					{
						
						try {
							client.Active_walkInClient();
							
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}break;
					
					
					default:
					 {
						 System.out.println("Invalid Selection.Option does not exist."); 
					 }break;
					
					}
				
			}break;
			
			 default:
			 {
				 System.out.println("Invalid Selection.option does not exist."); 
			 }break;
			
			}//inner switch
			 
			 
			 
			 
		 }break;
		 
		 
		 case 2:{
			 System.out.println("--You have chosen canceled reports--");
			 
				System.out.println("Canceled Hotel =>[7]");
				System.out.println("Canceled Spa= >[8]");
				System.out.println("Select:");
				try{
					reservation_Options_type_int_sub=Integer.parseInt(int_Data_input.nextLine().trim());
					}catch(Exception d)
					{
						System.err.println(d.getMessage());
					}
				switch(reservation_Options_type_int_sub){
				case 7:
				{
					
					try {
						room.DisplayCanceledHotelReservations();
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
				}break;
				
				
				case 8:
				{
					////////////////////////////////////////////////////////////////////////
					
					System.out.println("--You have chosen canceled spa reports--");
					 
					System.out.println("Canceled Guest =>[9]");
					System.out.println("Canceled WalkInClient= >[2]");
					System.out.println("Select:");
					try{
						reservation_Options_type_int_sub=Integer.parseInt(int_Data_input.nextLine().trim());
						}catch(Exception d)
						{
							System.err.println(d.getMessage());
						}
					
					switch(reservation_Options_type_int_sub){
					case 9:
					{
						try {
							guest.CanceledGuestReservation();
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}break;
					
					
					case 2:
					{
						try {
							client.CanceledWalkInClientsRESERVATION();
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						
					}break;
					
					
					default:
					 {
						 System.out.println("Invalid Selection.Option does not exist."); 
					 }break;
					
					}
					
					////////////////////////////////////////////////////////////////////////
				}break;
				
				
				default:
				 {
					 System.out.println("Invalid Selection.Option does not exist."); 
				 }break;
				}
				
				
				
				
		 }break;
		 
		 default:
		 {
			 System.out.println("Invalid Selection.SELECTED Option does not exist."); 
		 }break;
		 
		 }//swicth
		 
		 
		 
	}
	
	
	
	
	
	
	
	
}
