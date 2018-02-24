/**
 * Clasa ce extinde clasa Cell si 
 * implementeaza metodele getOrder
 * si visit. Corespunde caracterului
 * '.' din fisierul de intrare si 
 * reprezinta o celula libera.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */

public class FreeCell extends Cell {
	
	/**
	 * nr de vizitari al unei celule
	 */
	private int nrVisits;
	
	/**
	 * Constructor al unei celule libere.
	 * @param x - linia celulei
	 * @param y - coloana celulei
	 */
	public FreeCell(int x, int y) {
		super(x, y);
		nrVisits = 0;
	}
	
	
	/**
	 * Ordinea este data de nr de vizitari.
	 */
	@Override
	public int getOrder() {
		return nrVisits;
	}
	
	/**
	 * Cand vizitam o celula libera crestem
	 * nr de vizitari si returnam codul 1.
	 */
	@Override
	public int visit() {
		nrVisits++;
		return 1;
	}
	
	/**
	 * Metoda ce imi modifica nr de vizitari
	 * ca sa corespunda momentului initial. Utila
	 * daca dorim gasirea drumului de mai multe ori, ca
	 * sa avem acelasi rezultat.
	 * 
	 */
	public void unvisit() {
		nrVisits = 0;
	}
}
