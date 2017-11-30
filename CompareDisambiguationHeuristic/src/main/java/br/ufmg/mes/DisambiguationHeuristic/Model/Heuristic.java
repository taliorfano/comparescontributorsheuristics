package br.ufmg.mes.DisambiguationHeuristic.Model;

/***
 * Heuristic Interface
 * @author Talita
 *
 */
public interface Heuristic {
	public boolean Execute(String name1, String email1, String name2, String email2);
}
