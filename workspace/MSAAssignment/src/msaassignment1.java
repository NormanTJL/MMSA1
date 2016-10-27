import java.io.*;
import java.util.*;

public class msaassignment1 {

	public static void main(String[] args){
		
		Set<String> phototag = new HashSet<String>();
		Map<String, Double> innermap = new HashMap<String, Double>();
		Map<String, Map<String, Double>> outermap = new HashMap<String, Map<String, Double>>();
		BufferedReader is = null;
		String s;
		String[] tag;
		ArrayList<String> listofid = new ArrayList<String>();
		ArrayList<String> listoftags = new ArrayList<String>();
		
		try{ 
			is = new BufferedReader(new FileReader("../csv/tags.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");		
				if(!phototag.contains(tag[0])){
					phototag.add(tag[0]);
					//innermap.put(tag[0], 0);
					//
				}		
			
			}
			for(String photoset: phototag){
				outermap.put(photoset, new HashMap<String, Double>());
			}
			for(String a: outermap.keySet()){
				for(String b: phototag){
					if(a.equals(b)){
						outermap.get(a).put(a, -1.0);
					}
					else{
						outermap.get(a).put(b, 0.0);
					}
				}
			}
			is = new BufferedReader(new FileReader("../csv/photos_tags.csv"));
			while((s=is.readLine())!= null){	

				tag = s.split(",");
				listofid.add(tag[0]);
				listoftags.add(tag[1]);
			
			}

		}
		catch(Exception e){
			
		}
		finally{
			for(int i=0;i<listofid.size();i++){
					for(int j=i+1;j<listofid.size();j++){
						//System.out.println(photoandtags1.get(j));
						if(listofid.get(i).toString().equals(listofid.get(j).toString())) {
							outermap.get(listoftags.get(i)).put(listoftags.get(j), outermap.get(listoftags.get(i)).get(listoftags.get(j))+1.0);
							outermap.get(listoftags.get(j)).put(listoftags.get(i), outermap.get(listoftags.get(j)).get(listoftags.get(i))+1.0);
							//System.out.println("asd");
						}
						else{
							break;
						}						
					}

			}
			PrintWriter pw = new PrintWriter(new File("coocurrence.csv"));
        	StringBuilder sb = new StringBuilder();
        	ArrayList<String> everyline = new ArrayList<String>();

        	
        	for(int z=0; z<outermap.size();z++){
        		String oneline="";
        		for(int x=0; x<outermap.get(z).size(); x++){

        			if(x+1 != outermap.get(z).size()){

        			oneline.concat(outermap.get(z).get(x));

        			oneline.concat(",");
        			}
        			else{
        			oneline.concat("\n");
        			}
        		}
        		everyline.add(oneline);
        	}

		}
	}

	
}
