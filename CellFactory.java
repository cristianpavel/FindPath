/**
 * Clasa de tip Singleton-Factory care imi creeaza
 * instante de celule.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class CellFactory {
	
	private static final CellFactory INSTANCE = new CellFactory();
	
	private CellFactory() {
		
	}

	public static CellFactory getInstance() {
		
		return INSTANCE;
	}
	
	/**
	 * Functie ce imi creeaza o celula ponrind de la un
	 * caracter.
	 *  
	 * @param cell - caracterul celulei
	 * @param x - linia celulei
	 * @param y - coloana celulei
	 * @return - instanta de celula
	 */
	public Cell createCell(char cell,int x, int y) {
		
		switch(cell) {
		
		case '.': return new FreeCell(x, y);
		case 'I': return new InCell(x, y);
		case '#': return new WallCell(x,y);
		case 'O': return new OutCell(x,y);
		
		
		}
		return null;
		
	}
	
	/**
	 * Functie ce imi creeaza o celula de acelasi tip
	 * cu alta celula primita ca parametru.
	 * 
	 * @param cell - celula
	 * @return - noua celula
	 */
	public Cell createCell(Cell cell) {
		
		int x = cell.getPoint().getX();
		int y = cell.getPoint().getY();
		
		if (cell instanceof InCell)
			return new InCell(x,y);
		if (cell instanceof WallCell)
			return new WallCell(x,y);
		if (cell instanceof OutCell)
			return new OutCell(x,y);
		if (cell instanceof FreeCell)
			return new FreeCell(x,y);
		
		return null;
		
	}
}
