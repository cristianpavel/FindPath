/**
 * Clasa ce reprezinta un punct din matricea
 * labirint. 
 * @author Cristian Pavel - 324 CB
 *
 */
public class Point {
	
	/**
	 * linia din matrice
	 */
	private int x;
	
	/**
	 * coloana din matrice
	 */
	private int y;
	
	/**
	 * constructor
	 * 
	 * @param x - linia
	 * @param y - coloana
	 */
	public Point(int x, int y) {

		this.y = y;
		this.x = x;
	}
	
	/**
	 * getter pentru coloana
	 * @return - coloana
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * setter pentru coloana
	 * @param y - coloana noua
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getter pentru linie
	 * @return - linia
	 */
	public int getX() {
		return x;
	}

	/**
	 * setter pentru linie
	 * @param x - linia noua
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Metoda ce verifica daca 2 puncte sunt egale. 
	 * Suprascriem metoda din Object.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (!(o instanceof Point)) return false;
		
		Point oPoint = (Point)o;
		
		
		return (this.getX() == oPoint.getX()) && (this.getY() == oPoint.getY());
	}
	
	/**
	 * Suprascriem pentru a putea folosi HashMap (pentru
	 * ca am suprascris deja metoda equals).
	 */
	@Override
	public int hashCode() {
		
		return x ^ y;
		
	}
	
	/**
	 * Returneaza punctul daca acesta s-ar
	 * afla intr-o matrice bordata.
	 * @return - linia si coloana
	 */
	public String inBordedMatrix() {
		return (x - 1 ) + " " + (y - 1);
	}
	
}
