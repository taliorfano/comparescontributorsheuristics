package br.ufmg.mes.DisambiguationHeuristic.Heuristics;
import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/***
 *  Bird Heuristic
 *  @author Talita 
 */

public class BirdHeuristic implements Heuristic{
	private double constLevenshtein;
	
	public BirdHeuristic(){
		this.constLevenshtein = 0.93;
	}
	
	public BirdHeuristic(double constLevenshtein){
		this.constLevenshtein = constLevenshtein;
	}
	
	/***
	 * Execute Bird heuristic, comparing two authors (name and e-mail)
	 * @param name1  Author name 1
	 * @param email1 Author email 1
	 * @param name2  Author name 2
	 * @param email2 Author email 2
	 * @return True - If are equals
	 */
	public boolean Execute(String nameAuthor1, String email1, String nameAuthor2, String email2) {		
		String emailAuthor1 = Util.PrefixEmail(email1);
		String emailAuthor2 = Util.PrefixEmail(email2);
		
		nameAuthor1 = Normalize(nameAuthor1); 
		nameAuthor2 = Normalize(nameAuthor2);
		emailAuthor1 = Normalize(emailAuthor1);
		emailAuthor2 = Normalize(emailAuthor2);
		
		// As long as the two bases at least 2 caracteres long
		if(nameAuthor1.length() < 2 || nameAuthor2.length() < 2){
			return false;
		}
		
		if(compareFullName(nameAuthor1, nameAuthor2) >= constLevenshtein){
			return true;
		}
		
		if(compareFirstName(nameAuthor1, nameAuthor2) >= constLevenshtein && 
		   compareLastName(nameAuthor1, nameAuthor2) >= constLevenshtein){
			return true;
		}
		
		// As long as the two bases at least 3 caracteres long
		if(emailAuthor1.length() < 3 || emailAuthor2.length() < 3){
			return false;
		}
		
		if(comparePrefixContainsFirstAndLastName(emailAuthor1, nameAuthor2)){
			return true;
		}
		
		if(comparePrefixContainsFirstAndLastName(emailAuthor2, nameAuthor1)){
			return true;
		}
				
		if(comparePrefixWithFirstAndInitialLastName(emailAuthor1, nameAuthor2)){
			return true;
		}
		
		if(comparePrefixWithInitialFirstAndLastName(emailAuthor1, nameAuthor2)){
			return true;
		}
		
		if(comparePrefixWithFirstAndInitialLastName(emailAuthor2, nameAuthor1)){
			return true;
		}
		
		if(comparePrefixWithInitialFirstAndLastName(emailAuthor2, nameAuthor1)){
			return true;
		}
		
		if(comparePrefixes(emailAuthor1, emailAuthor2) >= constLevenshtein){
			return true;
		}
		return false;
	}
	
	/***
	 * Compare full name
	 * @param name1
	 * @param name2
	 * @return
	 */
	public double compareFullName(String name1, String name2){	
		return Util.DistanceLevenshtein(name1, name2);
	}
	
	/***
	 * Compare first name
	 * @param name1
	 * @param name2
	 * @return
	 */
	public double compareFirstName(String name1, String name2){	
		return Util.DistanceLevenshtein(Util.FirstName(name1), Util.FirstName(name2));
	}
	
	/**
	 * Compare last name
	 * @param name1
	 * @param name2
	 * @return
	 */
	public double compareLastName(String name1, String name2){	
		return Util.DistanceLevenshtein(Util.LastName(name1), Util.LastName(name2));
	}
	
	/**
	 * 	Return true if email prefix contains first and last name
	 * @param prefixEmail
	 * @param name
	 * @return
	 */
	public boolean comparePrefixContainsFirstAndLastName(String prefixEmail, String name){	
		return prefixEmail.contains(Util.FirstName(name)) && prefixEmail.contains(Util.LastName(name));
	}
	
	/***
	 * 	Return true if email prefix contains first name and the initial of last name
	 * @param prefixEmail
	 * @param name
	 * @return
	 */
	public boolean comparePrefixWithFirstAndInitialLastName(String prefixEmail, String name){
		String prefix =  Util.FirstName(name) + Util.LastName(name).charAt(0);
		return prefixEmail.contains(prefix);
	}
	
	/***
	 * 	Returns true if email prefix contains the initial of the first name and last name
	 * @param prefixEmail
	 * @param name
	 * @return 
	 */
	public boolean comparePrefixWithInitialFirstAndLastName(String prefixEmail, String name){	
		String prefix =  Util.FirstName(name).charAt(0) + Util.LastName(name);
		return prefixEmail.contains(prefix);
	}
	
	/**
	 * 	Comparing prefixes using Levenshtein Distance
	 * @param prefix1	Email prefix 1
	 * @param prefix2	Email prefix 2
	 * @return
	 */
	public double comparePrefixes(String prefix1, String prefix2){	
		return Util.DistanceLevenshtein(Util.PrefixEmail(prefix1), Util.PrefixEmail(prefix2));
	}
	
	/***
	 * Normalize label
	 * @param label
	 * @return label normalized
	 */
	public String Normalize(String label){
		label = label.replace(".", " ");
		label = label.replace("_", " ");
		label = label.replace("-", " ");
		label = label.replace(",", " ");
		label = label.replace(" jr ", "");
		label = label.replace("admin", "");
		label = label.replace("support", "");
		label = label.replaceAll("\\s+", " ");	
		label = label.trim();
		return label;
	}
}
