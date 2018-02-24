/**
 * lasa ce extinde clasa Cell si 
 * implementeaza metodele getOrder
 * si visit. Corespunde caracterului
 * 'O' in fisierul de intrare si
 * reprezinta portalul de iesire. 
 * 
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class OutCell extends Cell {

	/**
	 * Constructor pentru OutCell
	 * 
	 * @param x - linia celulei
	 * @param y - coloana celulei
	 */
	public OutCell(int x, int y) {
		super(x, y);
	}

	/**
	 * Ordinea este minima, pentru ca
	 * dorim sa alegem mereu portalul 
	 * de iesire in detrimentul unei
	 * celule libere.
	 * 
	 */
	@Override
	public int getOrder() {
	
		return -1;
	}

	/**
	 * Returneaza codul -1.
	 */
	@Override
	public int visit() {
		
		return -1;
	}

}
