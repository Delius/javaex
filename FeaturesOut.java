import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class FeaturesOut{
	


	public static void main(String[] args)
	{
		//Display Console with the Delete button
		final JLabel label;
		 
		   JButton button;

		   
		      label = new JLabel("Console application that deletes the \ngiven features and saves the results into a new file");
		     
		      button = new JButton("Delete records");
		      JFrame frame = new JFrame("Console application");
		      frame.setLayout(new FlowLayout());
		      frame.setSize(600,150);
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      button.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		            performDelete();
		         }
		      });
		      
		      frame.add(label);
		      frame.add(button);
		      
		      frame.setVisible(false);//make it true
	}
		   
	
	//Method to delete given records from dataset
		private static void performDelete (){

		String filePath = "Features.txt";
		String filePath2 = "FeaturesToDelete.txt";
		BufferedReader br,br2;
		String line = "";
		//HashMap as a fastest data structure for the big data files
		HashMap<String,String> numbers = new HashMap<String,String>();
		
		try {

			br = new BufferedReader(new FileReader(filePath));
			br2 = new BufferedReader(new FileReader(filePath2));
			try {
				while((line = br.readLine()) != null)
				{
					//Splits single line into words to retrieve UDP for search and deletion 
					String[] words = line.split(",",-1);//adress empty col with -1
					String UDP = words[0];
					String a = words[1];
					String b = words[2];
					String c = words[3];
					String rest = a+","+b+","+c;

					numbers.put(UDP, rest);

				}
				//check how many records were added to the HashMap
				System.out.println("Before Delete: "+ numbers.size());
				
				while((line = br2.readLine()) != null)
				{

					if (numbers.containsKey(line)){
						numbers.remove(line);
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*Write numbers to file */
			//Check how many records remains in the Hashmap and 
			//indicates the file where those records are stored
			System.out.println("After Delete: "+ numbers.size());
			System.out.println("File named 'WithoutToDelete.txt' "
					+ "\nwith remaining data is in the project root");

			FileWriter fstream;
			BufferedWriter out;

			// create your filewriter and bufferedreader
			fstream = new FileWriter("WithoutToDelete.txt");
			out = new BufferedWriter(fstream);

			// initialize the record count
			//int count = 0;

			// create your iterator for your map
			Iterator<Entry<String, String>> it = numbers.entrySet().iterator();

			// then use the iterator to loop through the map, stopping when we reach the
			// last record in the map
			while (it.hasNext()) {

				// the key/value pair is stored here in pairs
				Map.Entry<String, String> pairs = it.next();
				//System.out.println(pairs.getKey() + pairs.getValue());

				// since you only want the value, we only care about pairs.getValue(), which is written to out
				out.write(pairs.getKey()+","+pairs.getValue() + "\n");

			}
			// lastly, close the file and end
			out.close();
			br.close();
			br2.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
