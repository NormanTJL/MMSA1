import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class msaassignment1 {

	public static void main(String[] args){
		
		Set<String> phototag = new HashSet<String>();
		Map<String, Integer> photoandtags1 = new HashMap<String, Integer>();
		Map<String, Map<String, Integer>> photoandtags2 = new HashMap<String, Map<String, Integer>>();
		BufferedReader is = null;
		String s;
		String[] tag;
		int a=0;
		int[] values1, values2;
		try{ 
			is = new BufferedReader(new FileReader("../csv/tags.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");		
				if(!phototag.contains(tag[0])){
					phototag.add(tag[0]);
					photoandtags1.put(tag[0], 0);
					photoandtags2.put(tag[0], photoandtags1);
				}		
				
			}
			is = new BufferedReader(new FileReader("../csv/photos_tags.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");
				
			}
			System.out.println(photoandtags1.get("explore"));
		
		}
		catch(Exception e){
			
		}
		finally{
			
		}
	}

	
}
