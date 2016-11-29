package ckeckStatus;



public class Customer_CheckOut extends Customer_Checkin{

	@Override
	public void find_Customer_Reservation() {
		System.out.println("C U S T O M E R|C H E C K O U T ");
		
		System.out.println("\n---Type of Reservation--\n");
		System.out.println("Spa Appointment:[1]");
		System.out.println("Hotel Rooms:[2]");
		System.out.println("Choose:");
try{
		this.Next_Answer = this.menuu_input.nextLine().toUpperCase().charAt(0);
}catch(Exception f){
	System.out.println("Error please again.");
}
		if (Next_Answer == '2') {
			
			
			try{
				System.out
				.println("Please enter customer_Identification number: ");
				customer_IdentificationString = Integer.parseInt(menuu_input.nextLine().trim());
				super.found_customer = hotel_Room.Search(customer_IdentificationString);
				if (found_customer == true) {
					
					super.Customer_Checkin_Method();
					super.modified_answer = super.hotel_Room.Modified_Search(
							temp_Area_Code, temp_Line, temp_Exchange,
							temp_Day, temp_FirstName, temp_LastName,
							temp_DurationOfStay, temp_Ocuupancy, customer_IdentificationString);
					
					
					
					if(super.modified_answer==1){
					System.out.println("Thank you,you've been successfully checked out.");
					n =charge_Customer();
					System.out.println("Charge $:" + n);
						}
					
				}else{
					System.out.println("Invalid entry of data, can't check out.");
				}
				
				
				
				
			}catch(Exception e){
				
				System.err.print(e.getMessage());
				
			}
		
		
		} else if (Next_Answer == '1') {

			// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			String Spa_Appointment_Type = null;
			System.out.println("Type of Spa Appointment:");
			System.out.println("Guest Appointment: [=>G]:");
			System.out.println("Walk_In_Client Appointment:[=>W]:");
			try{
			 Spa_Appointment_Type = Str_Data_input.nextLine();
			}catch(Exception f)
			{
				System.out.println(f.getLocalizedMessage());
			}
			switch (Spa_Appointment_Type.toUpperCase().charAt(0)) {

			case 'G': {

				System.out
						.println("Searching database for all spa based records.......");
				try {
					System.out
							.println("Please enter customer_Identification number: ");
					customer_IdentificationString = Integer
							.parseInt(menuu_input.nextLine().trim());

					super.found_customer = super.newGuest.Search(customer_IdentificationString);
					if (found_customer == true) {
						System.out.println("Record Located.");
						super.Customer_Checkin_Method2();
						super.modified_answer = super.newGuest.Modified_Search(
								Room_Number, Spa_Requested, temp_Day,
								temp_FirstName, temp_LastName,
								customer_IdentificationString);

						if (modified_answer == 1) {
							System.out.println("Thank you,you've been successfully checked out.");
							n =charge_Customer_Spa();
							System.out.println("Charge $:" + n);
							
							
						} else if (modified_answer == -1) {
							System.out
									.println("Incorrect information submitted");
							
						}
							
					}

					else {
						System.out
								.println("Incorrect information submitted first");
					}

				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}

			}
				break;

			case 'W': {

				
				try {

					System.out.println("Please enter customer_Identification number: ");
					customer_IdentificationString = Integer.parseInt(menuu_input.nextLine().trim());

					super.found_customer = super.wclClient
							.Search(customer_IdentificationString);
					if (found_customer == true) {
						System.out.println("Record Located.");
						super.Customer_Checkin_Method3();
						super.modified_answer = super.wclClient.Modified_Search(ID,
								customer_IdentificationString,
								temp_Area_Code, temp_Exchange, temp_Line,
								temp_FirstName, temp_LastName,
								Spa_Requested, temp_Day);

						// ----------------------------------
						if (modified_answer == 1) {
						
							
							System.out.println("Thank you,you've been successfully checked out.");
							n =charge_Customer_Spa();
							System.out.println("Charge $:" + n);
							
						} else if (modified_answer == -1) {
							System.out.println("Incorrect information submitted.");
							
						}

					} else {

						System.out.println("Incorrect information submitted.");
					}

				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());

				}

			}
				break;

			default: {

				System.out.println("NOT A VALID OPTION");
			}
				break;
			}
		
		}
		
	}

	

	
	
	
	float charge_Customer(){
		int hotel_room_price = 300;
		float tax = 3210.89f;
	float charge = (float) ((float)(hotel_room_price * super.temp_Ocuupancy)*(super.temp_DurationOfStay)+tax);
	return charge;
		
	}
	float charge_Customer_Spa(){
		int spa_price = 30;
		float tax = 3210.89f;
	float charge = (float) ((float)(spa_price )*(tax));
	return charge;
		
	}
	
}
