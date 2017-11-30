package br.ufmg.mes.DisambiguationHeuristic.Model;
import java.util.Date;

/***
 *  Author is responsible for a commit 
 * @author Talita
 *
 */
public class Author {
	private String name;
	private String email;
	private Date dateIngress;

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
		this.dateIngress = null;
	}
	
	public Author(String name, String email, Date dateIngress) {
		this.name = name;
		this.email = email;
		this.dateIngress = dateIngress;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateIngress() {
		return dateIngress;
	}
	public void setDateIngress(Date dateIngress) {
		this.dateIngress = dateIngress;
	}
	
	public boolean equals(Object o) {	
	    Author author = (Author) o;
	    return this.name.equals(author.name) && this.email.equals(author.email);
	}
	
	public int hashCode() {
		return email.length()*name.length();
	}
}
