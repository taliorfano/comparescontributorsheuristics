package br.ufmg.mes.DisambiguationHeuristic.Controller;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.mes.DisambiguationHeuristic.Model.Author;
import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/**
 * Explore list of authors to find aliases
 * @author Talita
 *
 */
public class SearchAliasHeuristics {
	// Lista de alias encontrados
	private ArrayList<String> alias;
	
	// Lista de autores, separados por alias
	private ArrayList<List<String>> listAuthorsWithAlias;
	
	/***
	 * Constructor
	 */
	public SearchAliasHeuristics() {
		this.alias = new ArrayList<String>();
		this.listAuthorsWithAlias = new ArrayList<List<String>>();
	}

	/***
	 * 	Explore list of authors to find aliases
	 * @return 
	 * @return List with aliases
	 * @throws Exception 
	 */
	public ArrayList<List<String>> ExploreAuthors(ArrayList<Author> authorsList, Heuristic heuristic) throws Exception{	
		Author[] authors = authorsList.toArray(new Author[0]);
    	boolean hasAlias = false;
    	
    	for(int i = 0; i < authors.length; i++){
    		for(int j = i +1; j < authors.length; j++){
    			String email1 = authors[i].getEmail();
    			String email2 = authors[j].getEmail();
				String name1 = authors[i].getName();
				String name2 = authors[j].getName(); 
				hasAlias = heuristic.Execute(name1, email1, name2, email2);
    			if(hasAlias && !email1.equals(email2)){
    				this.AddAuthorsToList(email1, email2);
    			}
    		}
    	}
    	
    	// Add na lista aqueles nao tem alias
    	for(Author author : authors){
    		if(!this.alias.contains(author.getEmail())){
    			List<String> email = new ArrayList<String>();
    			email.add(author.getEmail());
    			this.listAuthorsWithAlias.add(email);
    		}
    	}
    	
    	return this.listAuthorsWithAlias;
	}
	
	/**
	 * 	Adds authors to the alias list when they are similar
	 * @param a1 Author 1
	 * @param a2 Author 2
	 * @param authorsAliasList
	 * @throws Exception 
	 */
	public void AddAuthorsToList(String email1, String email2) throws Exception{
		boolean sucess = false;
		
		if(this.alias.contains(email1)){
			if(this.alias.contains(email2)){
				sucess = this.MergeEmailAlias(email1, email2);
			}
			else{
				sucess = this.AddAlias(email1, email2);
				this.alias.add(email2);
			}
		}
		
		else if(this.alias.contains(email2)){
			sucess = this.AddAlias(email2, email1);		
			this.alias.add(email1);
		}
		else{
			List<String> newList = new ArrayList<String>();
			newList.add(email1);
			newList.add(email2);
			
			sucess = this.listAuthorsWithAlias.add(newList);
			this.alias.add(email1);
			this.alias.add(email2);
		}
		
		//if(!sucess){
			//throw new Exception("Error in add alias operation."); 
		//}
	}
	
	/**
	 * Verify if email is a alias
	 * @param email1 existe na lista
	 * @param email2 nao existe na lista
	 * @return
	 */
	public boolean AddAlias(String email1, String email2) {
		for (List<String> list: this.listAuthorsWithAlias) {    		
    		if (list.contains(email1)) {
	        	list.add(email2);
	        	return true;
	        }
	    }
		return false;
	}
	
	/**
	 *  Se ambos emails ja existem na lista de alias, fazer merge dessas listas
	 * @param email1
	 * @param email2
	 */
	public boolean MergeEmailAlias(String email1, String email2) {
	    int i = 0, j = 0;
    	        
    	for (List<String> list: this.listAuthorsWithAlias) {
    		if (list.contains(email1)) {
    			i = this.listAuthorsWithAlias.lastIndexOf(list);
	        	j = list.lastIndexOf(email1);
	        	break;
	        }
	    }
    	
    	for (List<String> list: this.listAuthorsWithAlias) {
    		if (list.contains(email2)) {
    			if(i == this.listAuthorsWithAlias.lastIndexOf(list)){
    				return true;
    			}
    			
	        	list.addAll(this.listAuthorsWithAlias.get(i));
	        	this.listAuthorsWithAlias.remove(i);	        	
	        	return true;
	        }
	    }
    	return false;
	}
}
