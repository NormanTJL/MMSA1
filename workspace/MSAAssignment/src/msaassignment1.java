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
		ArrayList listofid = new ArrayList();
		ArrayList listoftags = new ArrayList();
		
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
			for(int i=0;i<listofid.size();i++){

				if(listofid.get(i)==listofid.get(i+1)){
					for(int j=0;j<listofid.size();i++){
						if(listofid.get(j)==listofid.get(i))
							photoandtags2.put(listoftags.get(i), photoandtags1.get(listoftags.get(j), photoandtags1.get(listoftags.get(j))+1));
						}
					}

				}

			
			System.out.println(photoandtags1.get("explore"));
		
		}
		catch(Exception e){
			
		}
		finally{
			
		}
	}

	
}
