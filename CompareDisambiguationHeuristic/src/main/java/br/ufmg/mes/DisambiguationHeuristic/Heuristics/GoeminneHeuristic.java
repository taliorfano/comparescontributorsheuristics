package br.ufmg.mes.DisambiguationHeuristic.Heuristics;
import java.util.ArrayList;

import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/**
 * 	Goeminne Heuristic
 * @author Talita
 *
 */
public class GoeminneHeuristic implements Heuristic{
	private double threshold;
	private int minLen;
	
	public GoeminneHeuristic(){
		this.threshold = 0.87;
		this.minLen = 8;
	}
	
	public GoeminneHeuristic(double threshold, int minLen){
		this.threshold = threshold;
		this.minLen = minLen;
	}
	
	/***
	 * Execute Goeminne heuristic, comparing two authors (name and e-mail)
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
		
		if(compareNameWithPrefix(nameAuthor1, emailAuthor2)){
			return true;
		}
		
		if(compareNameWithPrefix(nameAuthor2, emailAuthor1)){
			return true;
		}
		
		if(PrefixContainsName(nameAuthor2, emailAuthor1)){
			return true;
		}
		
		if(PrefixContainsName(nameAuthor1, emailAuthor2)){
			return true;
		}
		
		if(nameAuthor1.length() >= 3 && nameAuthor2.length() >= 3 &&
				compareFullName(nameAuthor1, nameAuthor2)){
			return true;
		}
		
		if(emailAuthor1.length() >= 3 && emailAuthor2.length() >=3 &&
				comparePrefixes(emailAuthor1, emailAuthor2)){
			return true;
		}
		
		if(VerifyPrefix(nameAuthor1, nameAuthor2, emailAuthor1, emailAuthor2)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 	Compare full name with prefix email using Levenshtein Distance 
	 * @param name Author 1
	 * @param prefix Author 2
	 * @return
	 */
	public boolean compareNameWithPrefix(String name, String prefix){	
		return Util.DistanceLevenshtein(name, prefix) >= threshold;
	}
	
	/**
	 * 	Compare full names using Levenshtein Distance 
	 * @param name1 Author 1
	 * @param name2 Author 2
	 * @return
	 */
	public boolean compareFullName(String name1, String name2){	
		return Util.DistanceLevenshtein(name1, name2) >= threshold;
	}
	
	/**
	 * 	Compare email prefixes using Levenshtein Distance 
	 * @param prefix1
	 * @param prefix2
	 * @return
	 */
	public boolean comparePrefixes(String prefix1, String prefix2){	
		return Util.DistanceLevenshtein(Util.PrefixEmail(prefix1), Util.PrefixEmail(prefix2)) >= threshold;
	}
	
	/**
	 * 	Create list of probable e-mail prefixes from the full name, so compare with the prefix of author2, the inverse too
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
	 * Create list of probable e-mail prefixes from the full name
	 * TODO Completar comentarios
	 * @return
	 */
	public ArrayList<String> LikelyPrefix(String label){
		String arrayLabel[] = label.split(" ");
		ArrayList<String> combinationPrefix = new ArrayList<String>();
		
		// tratar casos em que o nome é unico, length = 1
		int tam = arrayLabel.length;
		
		// List with combinations of possible prefixes through the parts of the name:
		// firstnamelastname, firstname.lastname, firstname_lastname, nsurname@example.com, firstnameinitiallast, firstname-lastname
		// lastnamefirstname, lastname.firstname, lastname_firstname, initiallastfirstname, lastnameinitialfistname, lastname_firstname 
				
		if(tam == 1){
			combinationPrefix.add(arrayLabel[tam-1]);
			return combinationPrefix;
		}
		
		String word1 = null;
		String word2 = null;
		String word3 = null;
		String word4 = null;
		String word5 = null;
		String word6 = null;
		
		// Five combinations (e.g. name middle surname)
		// namemiddlesurname, middlesurnamename, surnamenamemiddle, name_middle_surname
		// name.middle.surname( middle.surname.name, surname.name.middle)
		
		for(int i = 0; i < tam; i++){
			if(arrayLabel[i].length() < 1 ||  arrayLabel[i].charAt(0) == (' ') || arrayLabel[i].equals("")){
				continue;
			}
			word1 = arrayLabel[i];
			word2 = arrayLabel[i];
			word3 = arrayLabel[i];
			word4 = arrayLabel[i].substring(0, 1);
			word5 = arrayLabel[i];
			word6 = arrayLabel[i];
			
			// combinacoes do word do nome para frente
			for(int j = i+1; j < tam; j++){
				if(arrayLabel[j].length() < 1 ||  arrayLabel[j].charAt(0) == (' ') || arrayLabel[j].equals("")){
					continue;
				}
				
				word1 = word1.concat(arrayLabel[j]);
				word2 = word2.concat(".").concat(arrayLabel[j]);
				word3 = word3.concat("_").concat(arrayLabel[j]);
				word4 = word4.concat(arrayLabel[j]);
				word5 = word5.concat(arrayLabel[j].substring(0, 1));
				word6 = word3.concat("-").concat(arrayLabel[j]);
				
				combinationPrefix.add(word1);
				combinationPrefix.add(word2);
				combinationPrefix.add(word3);
				combinationPrefix.add(word4);
				combinationPrefix.add(word5);
				combinationPrefix.add(word6);
			}
						
			// combinacoes do inicio do nome ate o word
			for(int j = 0; j < i; j++){
				if(arrayLabel[j].length() < 1 ||  arrayLabel[j].charAt(0) == (' ') || arrayLabel[j].equals("")){
					continue;
				}
				
				word1 = word1.concat(arrayLabel[j]);
				word2 = word2.concat(".").concat(arrayLabel[j]);
				word3 = word3.concat("_").concat(arrayLabel[j]);
				word4 = word4.concat(arrayLabel[j]);
				word5 = word5.concat(arrayLabel[j].substring(0, 1));
				word6 = word6.concat("-").concat(arrayLabel[j]);
				
				combinationPrefix.add(word1);
				combinationPrefix.add(word2);
				combinationPrefix.add(word3);
				combinationPrefix.add(word4);
				combinationPrefix.add(word5);
				combinationPrefix.add(word6);
			}
		}
		
		return combinationPrefix;
	}
	
	/***
	 * 	Return true if email prefix contain part of the name
	 * @param nameA
	 * @param emailB
	 * @return
	 */
	public boolean PrefixContainsName(String nameA, String emailB){
		String name[] = Util.PartsName(nameA);
		
		// The name just only one part
		if(name == null){
			return (emailB.contains(nameA));
		}
		
		for(int i = 0; i < name.length; i++){
			if(name[i].length() > minLen){
				if(emailB.contains(name[i])){
					return true;
				}
			}
		}
		return false;
	}
	

	/***
	 * Normalize label
	 * @param label
	 * @return label normalized
	 */
	// TODO Conferir se estao todas palavras presentes no Apendice do artigo
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
		
		label = label.replace("mr.", "");
		label = label.replace("mrs.", "");
		label = label.replace("miss", "");
		label = label.replace("ms.", "");
		label = label.replace("prof.", "");
		label = label.replace("pr.", "");
		label = label.replace("dr.", "");
		label = label.replace("ir.", "");
		label = label.replace("rev.", "");
		label = label.replace("ing.", "");
		label = label.replace("jr.", "");
		label = label.replace("d.d.s.", "");
		label = label.replace("ph.d.", "");
		label = label.replace("capt.", "");
		label = label.replace("lt.", "");
		
		label = label.replace("administrator", "");
		label = label.replace("admin.", "");
		label = label.replace("support", "");
		label = label.replace("development", "");
		label = label.replace("dev.", "");
		label = label.replace("developer", "");
		label = label.replace("maint.", "");
		label = label.replace("maintainer", "");
		label = label.replace("i18n", "");
		
		label = label.replace("spam", "");
		label = label.replace("bugs", "");
		label = label.replace("bugs", "");
		label = label.replace("root", "");
		label = label.replace("mailing", "");
		label = label.replace("list", "");
		label = label.replace("contact", "");
		label = label.replace("project", "");
		label = label.replace("evince", "");
		label = label.replace("brasero", "");
		label = label.replace("bugzilla", "");
		label = label.replace("gnome", "");
		label = label.replace("linux", "");
		
		return label;
	}

}
