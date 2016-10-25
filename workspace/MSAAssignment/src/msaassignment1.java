import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class msaassignment1 {

	public static void main(String[] args){
		
		Set<String> phototag = new HashSet<String>();
		BufferedReader is = null;
		String s;
		String[] tag;
		int a=0;
		try{ 
			is = new BufferedReader(new FileReader("../csv/tags.csv"));
			while((s=is.readLine())!= null){
				tag = s.split(",");
				
				if(phototag.contains(tag[0])){
					
				}
				else{
					phototag.add(tag[0]);
				}
				
				
			}
			System.out.println(phototag.size());
		
		}
		catch(Exception e){
			
		}
		finally{
			
		}
	}

	
}
