/**
 * Clasa ce extinde clasa Cell si 
 * implementeaza metodele getOrder
 * si visit. Corespunde caracterului
 * '#' din fisierul de intrare si 
 * reprezinta un perete.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class WallCell extends Cell {

	/**
	 * Constructor al unui perete.
	 * 
	 * @param x - linia celulei
	 * @param y - coloana celulei
	 */
	public WallCell(int x, int y) {
		super(x, y);
		
	}

	/**
	 * Nr de ordine are valoare foarte mare
	 * pentru ca aceasta celula sa fie mai mica
	 * oricand decat o celula libera (sa nu fie 
	 * aleasa).
	 * 
	 */
	@Override
	public int getOrder() {
		
		return Integer.MAX_VALUE - 1;
	}

	/**
	 * Daca dorim sa o vizita intoarcem exceptie.
	 */
	@Override
	public int visit() throws CannotMoveIntoWallsException {
		throw new CannotMoveIntoWallsException();
	}

	
}
