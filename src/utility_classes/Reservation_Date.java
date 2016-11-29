package utility_classes;

public class Reservation_Date {

	public int Day;
	public int Month;
	public int Year;
	
	public  Reservation_Date(){
						this.Day = 0;
						this.Month=0;
						this.Year=0;
		
	}
	
	public  Reservation_Date(int Day,int Month,int Year){
		this.Day = Day;
		this.Month=Month;
		this.Year=Year;


}
	
	
	public  void Set_Reservation_Date(int Day,int Month,int Year){
		this.Day = Day;
		this.Month=Month;
		this.Year=Year;


}
	public  String Get_Reservation_Date(){
		return String.format("Day :%d / Month : %d/year: %d", this.Day,this.Month,this.Year);


}
}
