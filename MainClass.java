import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * Clasa Main. Aici fac citirea si afisarea.
 * Fac un maze si apelez in functie de
 * nrTask o metoda din acesta. Afisez
 * la args[2].
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class MainClass {
	
	/**
	 * Functia main. Citesc inaltimea si latimea.
	 * Fac o matrice labirint cu ajutorul factory-ului.
	 * Dupa care citesc matricea de la intrare si
	 * in creez celule cu CellFactory. Retin pozitia
	 * portalului de intrare.
	 * 
	 * @param args - <nrTask> <input> <output>
	 */
	public static void main(String[] args) {
		
		int nrTask = Integer.parseInt(args[0]);
		String input = args[1];
		String output = args[2];
		BufferedReader bf = null;
		BufferedWriter of = null;			
		
		try {
			
			bf = new BufferedReader(new FileReader(input));
			String line = bf.readLine();
			
			int height = Integer.parseInt(line.split("[ ]+")[0]);
			int width = Integer.parseInt(line.split("[ ]+")[1]);
			
			Cell[][] map = MapFactory.getInstance().createMap(height, width);
			
			int row = 1;
			int column = 1;
			
			int startRow = 0, startColumn = 0;
			
			line = bf.readLine();
			while (line != null) {
				

				while (column <= width) { 
					if (line.charAt(column - 1) == 'I') {
						startRow = row;
						startColumn = column;
					}
					
					map[row][column] = CellFactory.getInstance().createCell(line.charAt(column - 1),row,column);
					column++;
				}
				
				row++;
				column = 1;
				line = bf.readLine();
				
			}
			
			Maze lab = new Maze(height,width,map,startRow,startColumn);
			List<Point> path =null;
		
			if (nrTask == 1) {
				path = lab.getPath();
				
				
			} else {
				path = lab.leeAlgorithm();
			
			}
			
			of = new BufferedWriter(new FileWriter(output));
			
			of.write(path.size() + "");
			of.newLine();
			
			for (int i = 0; i < path.size(); i++) {
				of.write(path.get(i).inBordedMatrix());
				of.newLine();
			}
			
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		} finally {
			
			try {
				
				bf.close();
				of.close();
			
			} catch (IOException e) {
			
				e.printStackTrace();
			
			}	
		}
			
	}

}
