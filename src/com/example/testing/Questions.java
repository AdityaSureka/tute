package com.example.testing;

public class Questions {
	//private variables
		int _id;
		String _question ,_ans,_opta,_optb,_optc,_optd;
		
		
		// Empty constructor
		public Questions(){
			
		}
		// constructor
		public Questions(int id, String questions ,String ans ,String opta ,String optb ,String optc ,String optd){
			this._id = id;
			this._question = questions;
			this._ans = ans;
			this._opta = opta;
			this._optb = optb;
			this._optc = optc;
			this._optd = optd;			
		}
		// constructor
		public Questions(String questions ,String ans ,String opta ,String optb ,String optc ,String optd){
			this._question = questions;
			this._ans = ans;
			this._opta = opta;
			this._optb = optb;
			this._optc = optc;
			this._optd = optd;	
		}
		// getting ID
		public int getID(){
			return this._id;
		}
		
		// setting id
		public void setID(int id){
			this._id = id;
		}
		// getting message
		public String getQuestions(){
			return this._question;
		}
		
		// setting name
		public void setQuestions(String questions){
			this._question = questions;
		}
		
		public String getans(){
			return this._ans;
		}
		
		// setting name
		public void setans(String ans){
			this._ans = ans;
		}
		
		// setting name
		public void setopta(String opta){
			this._opta = opta;
		}
		
		public String getopta(){
			return this._opta;
		}
		
		public void setoptb(String optb){
			this._optb = optb;
		}
		
		public String getoptb(){
			return this._optb;
		}
		
		public void setoptc(String optc){
			this._optc = optc;
		}
		
		public String getoptc(){
			return this._optc;
		}
		
		public void setoptd(String optd){
			this._optd = optd;
		}
		
		public String getoptd(){
			return this._optd;
		}		
}
