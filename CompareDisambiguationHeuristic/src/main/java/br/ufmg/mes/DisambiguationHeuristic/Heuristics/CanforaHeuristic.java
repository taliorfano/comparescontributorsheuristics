package br.ufmg.mes.DisambiguationHeuristic.Heuristics;

import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/***
 * Canfora Heuristic
 * 
 * @author Talita
 *
 */
public class CanforaHeuristic implements Heuristic {

	/***
	 * Execute Canfora heuristic, comparing two authors (name and e-mail)
	 * 
	 * @param name1
	 *            Author name 1
	 * @param email1
	 *            Author email 1
	 * @param name2
	 *            Author name 2
	 * @param email2
	 *            Author email 2
	 * @return True - If are equals
	 */
	public boolean Execute(String nameAuthor1, String email1, String nameAuthor2, String email2) {
		String emailAuthor1 = Util.PrefixEmail(email1);
		String emailAuthor2 = Util.PrefixEmail(email2);

		nameAuthor1 = Normalize(nameAuthor1);
		nameAuthor2 = Normalize(nameAuthor2);
		emailAuthor1 = Normalize(emailAuthor1);
		emailAuthor2 = Normalize(emailAuthor2);

		if (CompareName(nameAuthor1, nameAuthor2)) {
			return true;
		}

		if (CompareEmail(nameAuthor1, nameAuthor2, emailAuthor1, emailAuthor2)) {
			return true;
		}

		return false;
	}

	/***
	 * Compare names
	 * 
	 * @param nameAuthor1
	 * @param nameAuthor2
	 * @return
	 */
	public boolean CompareName(String nameAuthor1, String nameAuthor2) {
		if (nameAuthor1.equals(nameAuthor2)) {
			return true;
		}

		if (CompareNameIgnoringMiddleName(nameAuthor1, nameAuthor2)) {
			return true;
		}

		if (CompareFirstNameWithInitials(nameAuthor1, nameAuthor2)) {
			return true;
		}

		if (CompareLastName(nameAuthor1, nameAuthor2)) {
			return true;
		}

		if (CompareInitialsName(nameAuthor1, nameAuthor2)) {
			return true;
		}
		return false;
	}

	/***
	 * Compare names and emails
	 * 
	 * @param name1
	 *            Name Author 1
	 * @param name2
	 *            Name Author 2
	 * @param emailAuthor1
	 *            Email Author 1
	 * @param emailAuthor2
	 *            Email Author 2
	 * @return
	 */
	public boolean CompareEmail(String name1, String name2, String emailAuthor1, String emailAuthor2) {
		if (emailAuthor1.equalsIgnoreCase(emailAuthor2)) {
			return true;
		}

		if (CompareNameWithEmail(name1, name2, emailAuthor1, emailAuthor2)) {
			return true;
		}

		return false;
	}

	/**
	 * Compare names ignoring the middle names
	 * 
	 * @param name1
	 *            Name 1
	 * @param name2
	 *            Name 2
	 * @return
	 */
	public boolean CompareNameIgnoringMiddleName(String name1, String name2) {
		String arrayName1[] = name1.split(" ");
		String arrayName2[] = name2.split(" ");

		// John Michael Smith = John Smith, and the reverse
		if ((arrayName1.length > 2 && arrayName2.length == 2) || (arrayName1.length == 2 && arrayName2.length > 2)
				|| (arrayName1.length > 2 && arrayName2.length > 2)) {
			if (arrayName1[0].equalsIgnoreCase(arrayName2[0])
					&& arrayName1[arrayName1.length - 1].equalsIgnoreCase(arrayName2[arrayName2.length - 1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compare first name with initials name
	 * 
	 * @param name1
	 * @param name2
	 * @return
	 */
	public boolean CompareFirstNameWithInitials(String name1, String name2) {
		String arrayName1[] = name1.split(" ");
		String arrayName2[] = name2.split(" ");

		if (((arrayName1.length >= 2 && arrayName2.length >= 2) && (arrayName1[0].length() == 1))
				&& (arrayName1.length >= 2 && arrayName2.length >= 2) && (arrayName2[0].length() == 1)) {
			if ((arrayName1[0].charAt(0) == arrayName2[0].charAt(0))
					&& arrayName1[arrayName1.length - 1].equalsIgnoreCase(arrayName2[arrayName2.length - 1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compare last name
	 * 
	 * @param name1
	 * @param name2
	 * @return
	 */
	public boolean CompareLastName(String name1, String name2) {
		String arrayName1[] = name1.split(" ");
		String arrayName2[] = name2.split(" ");

		if ((arrayName1.length == 1 && arrayName2.length >= 2) || (arrayName2.length == 1 && arrayName1.length >= 2)) {
			if (arrayName1[arrayName1.length - 1].equalsIgnoreCase(arrayName2[arrayName2.length - 1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compare initials name, e.g. John Smith == j s
	 * 
	 * @param name1
	 * @param name2
	 * @return
	 */
	public boolean CompareInitialsName(String name1, String name2) {
		String arrayName1[] = name1.split(" ");
		String arrayName2[] = name2.split(" ");

		boolean hasInitial = true;

		// Um dos dois nomes precisa ter apenas caracteres iniciais
		for (String n : arrayName1) {
			if (!JustInitial(n)) {
				hasInitial = false;
			}
		}
		if (!hasInitial) {
			for (String n : arrayName2) {
				if (!JustInitial(n)) {
					return false;
				}
			}
		}

		// Compare the first character of each string of name
		if (arrayName1.length == arrayName2.length && arrayName2.length > 1) {
			for (int i = 0; i < arrayName1.length; i++) {
				if(arrayName1[i].charAt(0) == ' ' || (arrayName2[i].equals("")) || (arrayName2[i].equals(" "))){
					return false;
				}
				
				if (arrayName1[i].charAt(0) != arrayName2[i].charAt(0)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/***
	 * Compare name author1 with email author2, and the reverse
	 * 
	 * @param name1
	 * @param name2
	 * @param emailAuthor1
	 * @param emailAuthor2
	 * @return
	 */
	public boolean CompareNameWithEmail(String name1, String name2, String emailAuthor1, String emailAuthor2) {
		String extractName1 = ExtractName(emailAuthor1);
		String extractName2 = ExtractName(emailAuthor2);
		
		if (extractName1.equalsIgnoreCase(name2)) {
			return true;
		}

		if (extractName2.equalsIgnoreCase(name1)) {
			return true;
		}
		
		// Name 1
		if (CompareNameIgnoringMiddleName(extractName1, name2)) {
			return true;
		}

		if (CompareFirstNameWithInitials(extractName1, name2)) {
			return true;
		}

		if (CompareLastName(extractName1, name2)) {
			return true;
		}

		if (CompareInitialsName(extractName1, name2)) {
			return true;
		}

		// Name 2
		if (CompareNameIgnoringMiddleName(extractName2, name1)) {
			return true;
		}

		if (CompareFirstNameWithInitials(extractName2, name1)) {
			return true;
		}

		if (CompareLastName(extractName2, name1)) {
			return true;
		}

		if (CompareInitialsName(extractName2, name1)) {
			return true;
		}

		return false;
	}

	/**
	 * Extract full name from email prefix
	 * 
	 * @param prefix
	 * @return
	 */
	public String ExtractName(String prefix) {
		if (prefix.contains(".")) {
			prefix = prefix.replace(".", " ");
			return prefix;
		}
		if (prefix.contains("_")) {
			prefix = prefix.replace("_", " ");
			return prefix;
		}
		if (prefix.contains("-")) {
			prefix = prefix.replace("-", " ");
			return prefix;
		}
		return prefix;
	}

	/**
	 * Verify if name contains only the initial
	 * 
	 * @param name
	 * @return
	 */
	public static boolean JustInitial(String name) {
		if (name.length() == 1) {
			return true;
		}
		return false;
	}

	/***
	 * Normalize label
	 * 
	 * @param label
	 * @return label normalized
	 */
	public String Normalize(String label) {
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
		label = label.replaceAll("Ç", "C");
		label = label.replaceAll("ç", "c");
		label = label.replaceAll("[ýÿ]", "y");
		label = label.replaceAll("Ý", "Y");
		label = label.replaceAll("ñ", "n");
		label = label.replaceAll("Ñ", "N");
		label = label.replaceAll("[-+=*&;%$#@!_]", "");
		label = label.replaceAll("['\"]", "");
		label = label.replaceAll("[<>()\\{\\}]", "");
		label = label.replaceAll("['\\\\.,()|/]", "");
		label = label.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		label = label.trim();
		label = label.toLowerCase();

		return label;
	}
}
