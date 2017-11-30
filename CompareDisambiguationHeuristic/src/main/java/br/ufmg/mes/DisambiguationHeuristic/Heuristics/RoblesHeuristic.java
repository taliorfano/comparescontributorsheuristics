package br.ufmg.mes.DisambiguationHeuristic.Heuristics;
import java.util.ArrayList;

import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/**
 * Robles Heuristic
 * @author Talita
 *
 */
public class RoblesHeuristic implements Heuristic{
	
	/***
	 * Execute Robles heuristic, comparing two authors (name and e-mail)
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
		
		if(emailAuthor1.equals(emailAuthor2)){
			return true;
		}
		
		if(nameAuthor1.equals(nameAuthor2)){
			return true;
		}
		
		if(VerifyPrefix(nameAuthor1, nameAuthor2, emailAuthor1, emailAuthor2))
		{
			return true;
		}
		return false;
	}
	
	/***
	 * Create list of probable e-mail prefixes from the first and last name
	 * @return
	 */
	public ArrayList<String> LikelyPrefix(String label){
		String arrayLabel[] = label.split(" ");
		ArrayList<String> combinationPrefix = new ArrayList<String>();
		
		// tratar casos em que o nome é unico, length = 1
		int tam = arrayLabel.length;
		
		// Lista com combinacoes de possiveis prefixos atraves das partes do nome:
		// firstnamelastname, firstname.lastname, firstname_lastname, nsurname@example.com, firstnameinitiallast
		// lastnamefirstname, lastname.firstname, lastname_firstname, initiallastfirstname, lastnameinitialfistname
				
		if(tam > 1){
			combinationPrefix.add(arrayLabel[0].concat(arrayLabel[tam-1]));
			combinationPrefix.add(arrayLabel[0].concat(".").concat(arrayLabel[tam-1]));
			combinationPrefix.add(arrayLabel[0].concat("_").concat(arrayLabel[tam-1]));
			String initialFirstName = arrayLabel[0].substring(0, 1);
			String initialLastName = arrayLabel[tam-1].substring(0, 1);
			combinationPrefix.add(initialFirstName.concat(arrayLabel[tam-1]));
			combinationPrefix.add(arrayLabel[0].concat(initialLastName));
			
			combinationPrefix.add(arrayLabel[tam-1].concat(arrayLabel[0]));
			combinationPrefix.add(arrayLabel[tam-1].concat(".").concat(arrayLabel[0]));
			combinationPrefix.add(arrayLabel[tam-1].concat("_").concat(arrayLabel[0]));
			combinationPrefix.add(initialLastName.concat(arrayLabel[0]));
			combinationPrefix.add(arrayLabel[tam-1].concat(initialFirstName));
		}
		else{
			combinationPrefix.add(arrayLabel[0]);
		}
		
		return combinationPrefix;
	}
	
	/**
	 *  Create list of probable e-mail prefixes from the name, so compare with the prefix of author2, the inverse too
	 * @param nameAuthor1
	 * @param nameAuthor2
	 * @param emailAuthor1
	 * @param emailAuthor2
	 * @return
	 */
	public boolean VerifyPrefix(String nameAuthor1, String nameAuthor2, String emailAuthor1, String emailAuthor2){ 
		ArrayList<String> likelyPrefix = LikelyPrefix(nameAuthor1);
		
		for(String prefix:likelyPrefix){
			if(prefix.equals(emailAuthor2)){
				return true;
			}
		}
		
		ArrayList<String> likelyPrefix2 = LikelyPrefix(nameAuthor2);
		for(String prefix:likelyPrefix2){
			if(prefix.equals(emailAuthor1)){
				return true;
			}
		}
		
		return false;
	}
	
	/***
	 * Normalize label
	 * @param label
	 * @return label normalized
	 */
	public String Normalize(String label){
		label = label.replaceAll("[ÂÀÁÄÃ]", "A");
	    label = label.replaceAll("[âãàáä]", "a");
	    label = label.replaceAll("[ÊÈÉË]", "E");
	    label = label.replaceAll("[êèéë]", "e");
	    label = label.replaceAll("ÎÍÌÏ", "I");
	    label = label.replaceAll("îíìï", "i");
	    label = label.replaceAll("[ÔÕÒÓÖ]", "O");
	    label = label.replaceAll("[ôõòóö]", "o");
	    label = label.replaceAll("[ÛÙÚÜ]", "U");
	    label = label.replaceAll("[ûúùü]", "u");
	    label = label.replaceAll("[ýÿ]", "y");
	    label = label.replaceAll("Ý", "Y");
	    label = label.replaceAll("ñ", "n");
	    label = label.replaceAll("Ñ", "N");
		
	    label = label.replaceAll("\\s+", " ");
		label = label.trim();
		label = label.toLowerCase();
		
		return label;
	}
}
