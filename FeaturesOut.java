import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;



public class FeaturesOut{


	public static void main(String[] args)

	{


		String filePath = "Features.txt";
		String filePath2 = "FeaturesToDelete.txt";
		BufferedReader br,br2;
		String line = "";

		HashMap<String,String> numbers = new HashMap<String,String>();
		try {

			br = new BufferedReader(new FileReader(filePath));
			try {
				while((line = br.readLine()) != null)
				{

					//Splits single line into words
					String[] words = line.split(",",-1);//adress empty col with -1
					String UDP = words[0];
					String a = words[1];
					String b = words[2];
					String c = words[3];
					String rest = a+","+b+","+c;

					numbers.put(UDP, rest);

				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br = new BufferedReader(new FileReader(filePath2));
			try {
				while((line = br.readLine()) != null)
				{

					if (numbers.containsKey(line)){numbers.remove(line);}else{



					}


				}
				if (numbers.containsKey("10713959"))
					System.out.println("success");

				System.out.println(numbers.size());
				// System.out.println(names.size());

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

					// increment the record count once we have printed to the file
					//count++;
				}
				// lastly, close the file and end
				out.close();

				br.close();


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
