import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Queue;
/**
 * Clasa labirintului, Aici implementez functiile
 * pentru Task1 - getPath si Task2 - leeAlgorithm.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class Maze {

	/**
	 * Clasa interna pentru om. Am facut-o interna pentru
	 * ca nu am nevoie de ea decat aici. 
	 * @author Cristian Pavel - 324 CB
	 *
	 */
	private class Human {
		
		/**
		 * pozitia curenta a omului
		 */
		private Point p;
		
		/**
		 * directia inspre care este indreptat
		 */
		private char direction;
		
		/**
		 * pentru fiecare directie am un ArrayList in 
		 * care retin diferenta dintre celula curenta si
		 * celula din dreapta, din fata, din stanga si din spate;
		 * in ArrayList pozitiile 2*i si 2*i + 1 imi dau modul in
		 * care se schimba linia, respectiv coloana cand ma deplasez;
		 */
		private HashMap <Character, ArrayList<Integer>> correspondence;
		
		/**
		 * Constructor pentru un om. Aici construiesc si HashMap-ul.
		 * 
		 * @param x - linia
		 * @param y - coloana
		 * @param direction - directia
		 */
		public Human(int x, int y, char direction) {
			p = new Point(x,y);
			this.direction = direction;
			correspondence = new HashMap<Character, ArrayList<Integer>>();
			correspondence.put('N', new ArrayList<Integer>(Arrays.asList(0,1,-1,0,0,-1,1,0)));
			correspondence.put('E', new ArrayList<Integer>(Arrays.asList(1,0,0,1,-1,0,0,-1)));
			correspondence.put('S', new ArrayList<Integer>(Arrays.asList(0,-1,1,0,0,1,-1,0)));
			correspondence.put('W', new ArrayList<Integer>(Arrays.asList(-1,0,0,-1,1,0,0,1)));

			
			
		}
		
		/**
		 * Cum se schimba linia daca ma deplasez in dreapta
		 * 
		 * @return noua linie
		 */
		public int getNextRowRight() {
			
			return p.getX() + correspondence.get(direction).get(0);
			
		}
		
		/**
		 * Cum se schimba coloana daca ma deplasez in dreapta
		 * @return noua coloana
		 */
		public int getNextColumnRight() {
			
			return p.getY() + correspondence.get(direction).get(1);
		
		}
		
		/**
		 * Cum se schimba linia daca ma deplasez in fata
		 * @return noua linie
		 */
		public int getNextRowFront() {
			
			return p.getX() + correspondence.get(direction).get(2);
			
		}
		
		/**
		 * Cum se schimba coloana daca ma deplasez in fata
		 * @return noua coloana
		 */
		public int getNextColumnFront() {
			
			return p.getY() + correspondence.get(direction).get(3);
		
		}
		
		/**
		 * Cum se schimba linia daca ma deplasez in stanga
		 * @return noua linie
		 */
		public int getNextRowLeft() {
			
			return p.getX() + correspondence.get(direction).get(4);
			
		}
		
		/**
		 * Cum se schimba coloana daca ma deplasez in stanga
		 * @return noua coloana
		 */
		public int getNextColumnLeft() {
			
			return p.getY() + correspondence.get(direction).get(5);
		
		}
		
		/**
		 * Cum se schimba linia daca ma deplasez in spate
		 * @return noua linie
		 */
		public int getNextRowBack() {
			
			return p.getX() + correspondence.get(direction).get(6);
			
			
		}
		
		/**
		 * Cum se schimba coloana daca ma deplasez in spate
		 * @return noua coloana
		 */
		public int getNextColumnBack() {
			
			return p.getY() + correspondence.get(direction).get(7);
		
		}
		
		

	}
	
	/**
	 * inaltimea
	 */
	private int height;
	
	/**
	 * latimea
	 */
	private int width;
	
	/**
	 * matricea labirintului
	 */
	private Cell[][] map;
	
	/**
	 * pozitia de start, ca sa nu mai parcurg de 
	 * fiecare data matricea labirint
	 */
	private Point start;


	/**
	 * Parcurg matricea si o aduc la starea initiala.
	 */
	private void unvisitMap() {
		
		for (int i = 1; i <= height; i++)
			for (int j = 1; j <= width; j++)
				if (map[i][j] instanceof FreeCell)
					((FreeCell) map[i][j]).unvisit();
	}
	
	/**
	 * Copiez o matrice labirint.
	 * 
	 * @param map matricea ce trebuie copiate
	 * @param height inaltimea matricei
	 * @param width latimea matricei
	 * @return copie a matricei map
	 */
	private Cell[][] copyMap(Cell[][] map, int height, int width) {
		
		Cell[][] copy = MapFactory.getInstance().createMap(height, width);
		for (int i = 1; i <= height; i++)
			for (int j = 1; j <= width; j++)
				copy[i][j] = CellFactory.getInstance().createCell(map[i][j]);
				
		return copy;
	}
	
	/**
	 * Constuctor al clasei Maze.
	 * 
	 * @param height - inaltimea labirintului
	 * @param width - latimea labirintului
	 * @param map - harta labirintului
	 * @param startRow - linia pozitiei de inceput
	 * @param startColumn - coloana pozitiei de inceput
	 */
	public Maze(int height, int width, Cell[][] map,int startRow, int startColumn) {

		this.height = height;
		this.width = width;
		this.map = copyMap(map, height, width);
		start = new Point(startRow,startColumn);


	}
	
	/**
	 * Metoda ce construieste drumul inspre celula de iesire.
	 * Omul urmeaza ordinea RFLB, si tine cont de nr-ul
	 * de vizitari al fiecarei celule, alegand-o pe cea
	 * mai putin vizitata. In fiecare moment, adaug intr-o
	 * lista celulele vecine in ordinea RFLB, dupa care
	 * le sortez cu un comparator de tipul Move. Aleg celula
	 * de la capul listei, si mut omul in aceasta celula,
	 * shimbandu-i orientarea. O vizitez si daca ajung la 
	 * final (returnez -1 in visit) ma opresc.
	 * 
	 * @return - drumul spre celula de iesire
	 * @throws CannotMoveIntoWallsException
	 * @throws HeroOutOfGroundException
	 */
	public ArrayList<Point> getPath() throws CannotMoveIntoWallsException, HeroOutOfGroundException {
		
		/**
		 * om spawn-at la portalul de intrare
		 */
		Human h = new Human(start.getX(),start.getY(),'N');
	
		ArrayList<Point> path =  new ArrayList<Point>();
		path.add(h.p);

		Comparator<Cell> compare = new Move();
		Cell nextCell;
		do {
			
			
			ArrayList<Cell> toSort = new ArrayList<Cell>();
		
				
			toSort.add(map[h.getNextRowRight()][h.getNextColumnRight()]);
			toSort.add(map[h.getNextRowFront()][h.getNextColumnFront()]);
			toSort.add(map[h.getNextRowLeft()][h.getNextColumnLeft()]);
			toSort.add(map[h.getNextRowBack()][h.getNextColumnBack()]);
			
			toSort.sort(compare);
			
			
			nextCell = toSort.get(0);
			
			
			Point location = nextCell.getPoint();
			
			if (h.p.getX() == location.getX() && h.p.getY() < location.getY())
				h.direction = 'E';
			else if (h.p.getX() == location.getX())
				h.direction = 'W';
			else if (h.p.getX() < location.getX())
				h.direction = 'S';
			else
				h.direction = 'N';
		
			h.p = nextCell.getPoint();
			
			
			
	
			path.add(h.p);
		
		} while (nextCell.visit() != -1);
		
		unvisitMap();
		return path;
		
		
	}
	
	/**
	 * Metoda ce aplica algoritmul lui Lee ca 
	 * sa gasesc drumul minim de la portalul de
	 * intrare la portalul de iesire. Vectorii 
	 * movingInMap ma ajuta sa gasesc vecinii unei
	 * celule printr-un for. Am un HashMap in care
	 * retin celula prin care am ajuns la celula mea
	 * (moves.get(cell) = celula prin care am ajuns la
	 * celula cell). Am o coada in care pun constant 
	 * celule si vecini, pana ajung la portalul de iesire.
	 * La sfarsit construiesc drumul printr-o lista 
	 * ca sa pot adauga la inceput.
	 * 
	 * @return drumul cel mai scurt (+ ordinea RFLB)
	 */
	public LinkedList<Point> leeAlgorithm() {
		
		int[] movingInMapX = {0, -1, 0, 1};
		int[] movingInMapY = {1, 0, -1, 0};
		
		HashMap<Point,Point> moves = new HashMap<Point, Point>();
		Queue<Cell> toVisit = new LinkedList<Cell>();
		
		moves.put(start, null);
		toVisit.add(map[start.getX()][start.getY()]);
		Cell now = null;
		while (!toVisit.isEmpty()) {
			
			now = toVisit.poll();
		
			try {
				
				if (now.visit() == -1) {
					break;
				}
			} catch (HeroOutOfGroundException e) {
				moves.remove(now.getPoint());
				continue;
			
			} catch (CannotMoveIntoWallsException e) {
				moves.remove(now.getPoint());
				continue;
			
			}
			
			for (int i = 0; i < 4; i++) {
				
				Cell neighbour = map[now.getPoint().getX() + movingInMapX[i]][now.getPoint().getY() 
				                                                              + movingInMapY[i]];
				
				if (!moves.containsKey(neighbour.getPoint())) {
					
					toVisit.add(neighbour);	
					moves.put(neighbour.getPoint(), now.getPoint());
					
					
				}
				
			}
			
		
			
		}
	
		
		LinkedList<Point> path = new LinkedList<Point>();
		unvisitMap();
		
		Point theWayToGo = now.getPoint();
		while (theWayToGo != null) {
	
			path.addFirst(theWayToGo);
			theWayToGo = moves.get(theWayToGo);
			
		}
		
	
		return path;
	}
	
	
	


}
