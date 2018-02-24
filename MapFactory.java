/**
 * Clasa de tip Singleton-Factory ce imi creeaza
 * o matrice bordata de tip Cell[][].
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class MapFactory {
	
	private static final MapFactory INSTANCE = new MapFactory();
	
	private MapFactory() {
		
	}

	public static MapFactory getInstance() {
		
		return INSTANCE;
	}
	
	public Cell[][] createMap(int height, int width) {
		
		Cell[][] map = new Cell[height + 2][width + 2];
		
		for (int i = 0; i <= height + 1; i++) {
			map[i][0] = new FrontierCell(i,0);
			map[i][width + 1] = new FrontierCell(i,width + 1);
		}
		
		for (int i = 0; i <= width + 1; i++) {
			map[0][i] = new FrontierCell(0,i);
			map[height + 1][i] = new FrontierCell(height + 1,i);
		}
		
		return map;
	
	}
	
}
