package utility_classes;

public class Contact {
	
	public int Area_Code;
	public int Exchange;
	public int Line;

	public  Contact (){
		
		this.Area_Code =0;
		this.Exchange =0;
		this.Line =0;
	}
public  Contact (int Area_Code,int Exchange,int Line){
		
		this.Area_Code= Area_Code;
		this.Exchange= Exchange;
		this.Line =Line;
	}
		
public  void Set_Contact (int Area_Code,int Exchange,int Line){
	
	this.Area_Code= Area_Code;
	this.Exchange= Exchange;
	this.Line =Line;
}
public  String Get_Contact (){
	
	return String.format("Area_Code : %d , Exchange : %s, Line : %s", this.Area_Code,this.Exchange,this.Line);
	
}

}
