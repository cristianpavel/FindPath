/**
 * Clasa abstracta de tip celula. Are un 
 * atribut de tip Point in care retin 
 * pozitia celulei in cadrul Labirintului 
 * (o celula nu are sens decat daca face 
 * parte dintr-un labirint). Metodele getOrder
 * si visit trebuie implementate in functie 
 * de tipul celulei.
 * 
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
abstract public class Cell {

	/*
	 * pozitia celulei in labirint
	 */
	private Point p;
	
	/**
	 * Metoda care ma ajuta sa sortez
	 * celulele. Valoare de return difera
	 * in functie de tipul celulei.
	 * 
	 * @return - un nr de ordine
	 */
	abstract public int getOrder();

	/**
	 * Metoda apelata in momentul in care
	 * visitez o celula.
	 * 
	 * @return - cod ce imi spune tipul celulei
	 * @throws CannotMoveIntoWallsException
	 * @throws HeroOutOfGroundException
	 */
	abstract public int visit() throws CannotMoveIntoWallsException, HeroOutOfGroundException;
	
	
	/**
	 * Constructor al unei Celule.
	 * @param x - linia celulei in labirint
	 * @param y - coloana celulei in labirint
	 */
	public Cell(int x, int y) {
		p = new Point(x,y);
	
	}

	/**
	 * Prin metoda am acces la pozitia celulei.
	 * @return - coordonatele celulei
	 */
	public Point getPoint() {
		return p;
	}
	
}
