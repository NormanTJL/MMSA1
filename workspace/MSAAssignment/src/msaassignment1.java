import java.io.*;
import java.util.*;

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
		int idofphoto=0;
		ArrayList<String> listofid = new ArrayList<String>();
		ArrayList<String> listoftags = new ArrayList<String>();
		
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
				listofid.add(tag[0]);
				listoftags.add(tag[1]);
			
			}
			int z=0;
			for(int i=0;i<listofid.size();i++){
					for(int j=0;j<listofid.size();j++){
						//System.out.println(photoandtags1.get(j));
						if(listofid.get(i)==listofid.get(j)){
							photoandtags2.get(listoftags.get(i)).put(listoftags.get(j), photoandtags2.get(listoftags.get(i)).get(listoftags.get(j))+1);
							//System.out.println("asd");
						}
					}

			}
			System.out.println(photoandtags2.get("explore").get("macro"));
		
		}
		catch(Exception e){
			
		}
		finally{
			
		}
	}

	
}
