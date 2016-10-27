import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class msaassignment1 {
		static Map<String, Double> innermap = new HashMap<String, Double>();
		static Map<String, Map<String, Double>> outermap = new HashMap<String, Map<String, Double>>();
	public static void main(String[] args){
		PrintWriter pw;
		Set<String> phototag = new HashSet<String>();
		
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
			try{	
				pw = new PrintWriter(new File("coocurrence.csv"));	
				StringBuilder sb = new StringBuilder();
        		ArrayList<String> everyline = new ArrayList<String>();
        		String firstLine = "\t";

        		for(String tag1:phototag){
        			firstLine = firstLine + tag1;
        			firstLine= firstLine +",";
        		
        		}
        		firstLine = firstLine.substring(0, firstLine.length()-1);
        		firstLine = firstLine+"\n";
        	
        		for(String key1: outermap.keySet()){
        			String oneline="";
        			oneline = oneline + key1+" ,";
        			for(String key2:outermap.get(key1).keySet()){
        				oneline = oneline + outermap.get(key1).get(key2).toString()+",";
        			}
        			oneline = oneline.substring(0, oneline.length()-1);

        			oneline = oneline + "\n";
        			everyline.add(oneline);
        		}
        		sb.append(firstLine);
        		for(String eachline:everyline){
        			sb.append(eachline);
        		}
        		pw.write(sb.toString());
        		pw.close();
			}
			catch(Exception e){ 

			}
			finally{

				printTop5("water");
				printTop5("people");
				printTop5("london");
        		printTop5IDF("water");
        		printTop5IDF("people");
        		printTop5IDF("london");
        	}

		}
		
	}
	public static Integer getN(){
		Set<String> phototag = new HashSet<String>();
		BufferedReader is = null;
		String s;
		String[] tag;
		try{
		is = new BufferedReader(new FileReader("../csv/photos.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");	
				if(!phototag.contains(tag[0])){
					phototag.add(tag[0]);
					//innermap.put(tag[0], 0);
					//
				}		
			
			}
		}
		catch(Exception e){

		}
		finally{

			return phototag.size();
		}
	}
	public static Integer getNT(String key){
		
		BufferedReader is = null;
		String s;
		String[] tag;
		try{
		is = new BufferedReader(new FileReader("../csv/tags.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");	
				if(tag[0].equals(key)){
					return Integer.parseInt(tag[1]);
					//innermap.put(tag[0], 0);
					//
				}		
			
			}
		}
		catch(Exception e){

		}
		return 0;
		
	}
	public static void printTop5IDF(String key){
		Map<String, Double>hmap = outermap.get(key);
		Map<String, Double>hmap1 = new HashMap<String, Double>();
		Double finalval;
		int count=0;
		for(String k2:hmap.keySet()){
			finalval = (Math.log10(getN()/getNT(k2))) * hmap.get(k2);
			hmap1.put(k2, finalval);
		}
		hmap1 = sortByValue(hmap1);
		System.out.println("Part 3 : ");
		System.out.print(key + " values : ");
		for(String k1:hmap1.keySet()){
			Double totalval=0.0;
			if(count < 5){
				
				totalval=hmap1.get(k1);

				System.out.print(k1+":");
				System.out.print(totalval+",\t");
			}
			count++;
		}
		System.out.print("\n");
	}
	public static void printTop5(String key){
		Map<String, Double>hmap = outermap.get(key);
		hmap = sortByValue(hmap);
		int count=0;

		System.out.println("Part 2 : ");
		System.out.print(key + " values : ");
		for(String k1:hmap.keySet()){
			Double totalval=0.0;
			if(count < 5){
				
				totalval=hmap.get(k1);

				System.out.print(k1+":");
				System.out.print(totalval+",\t");
			}
			count++;
		}
		System.out.print("\n");

	}
	/*public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
    return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
*/

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
	
}
