package com.collabera.mediator;

interface IATCMediator { 

	public void registerRunway(Runway runway); 

	public void registerFlight(Flight flight); 

	public boolean isLandingOk(String name); 

	public void setLandingStatus(boolean status); 
} 

class ATCMediator implements IATCMediator { 
	
	private Flight flight; 
	private Runway runway; 
	public boolean land; 
	private String FName;

	public void registerRunway(Runway runway) {
		
		this.runway = runway; 
	} 

	public void registerFlight(Flight flight) {
		
		this.flight = flight; 
	} 

	public boolean isLandingOk(String FlightName) { 
		FName = FlightName;
		if(!land)
			System.out.println("ATC: Don't land yet " + FlightName + "\n");
		
		return land; 
	} 

	@Override
	public void setLandingStatus(boolean status) { 
		land = status; 
		if(status)
			System.out.println("ATC: Ok "  + FName + ", you're good to land\n"); 
	} 
} 

interface Command { 
	
	void land(); 
} 



class Flight implements Command { 
	
	private IATCMediator atcMediator;
	private String FlightName; 

	public Flight(IATCMediator atcMediator, String FlightName){ 
		this.atcMediator = atcMediator; 
		this.FlightName = FlightName;
	} 

	public void land() { 
		if (atcMediator.isLandingOk(FlightName)){ 
			
			System.out.println(FlightName + ": Ok, landing now.\n"); 
			atcMediator.setLandingStatus(false); 
		} 
		else
			System.out.println(FlightName + ": Ok, waiting for permission to land.\n"); 
	} 

	public void getReady() { 
		
		System.out.println(FlightName + ": Ready for landing.\n"); 
	} 

} 

class Runway implements Command { 
	
	private IATCMediator atcMediator; 

	public Runway(IATCMediator atcMediator) { 
		
		this.atcMediator = atcMediator; 
	 
	} 

	@Override
	public void land() { 
		
		
		atcMediator.setLandingStatus(true); 
	} 

} 

class MediatorDesignPattern { 
	
	public static void main(String args[]) { 
		

		IATCMediator atc = new ATCMediator();  
		Flight Meteor = new Flight(atc, "Meteor"); 
		Flight FireFalcon = new Flight(atc, "FireFalcon");
		Runway mainRunway = new Runway(atc); 
		 
		atc.registerFlight(Meteor); 
		atc.registerFlight(FireFalcon); 
		atc.registerRunway(mainRunway); 
		
		
		FireFalcon.getReady(); 
		FireFalcon.land(); 
		mainRunway.land(); 
		FireFalcon.land();
		
		System.out.println("\n");
		
		Meteor.getReady(); 
		Meteor.land(); 
		mainRunway.land(); 
		Meteor.land();
		
	} 
} 
