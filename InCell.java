/**
 * Clasa ce extine clasa FreeCell.
 * Metodele abstracte ale clase Cell vor
 * fi cele de la FreeCell. Clasa InCell devine
 * celula libera dupa teleportare, deci am considerat
 * ca este o celula libera speciala. Corespunde
 * caracterului 'I' in fisierul de intrare si 
 * reprezinta portalul de intrare.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class InCell extends FreeCell {

	/**
	 * Constructor pentru InCell. Odata
	 * cu construirea o si vizitez o data.
	 * 
	 * @param x - linia celulei
	 * @param y - coloana celulei
	 */
	public InCell(int x, int y) {
		super(x, y);
		this.visit();
		
	}

	
}
