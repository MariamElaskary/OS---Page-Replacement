
package os;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LRU 
{
 public void LRU(int fn, char[ ] pagelist,int pn) throws FileNotFoundException, IOException{
     int hit=0,PageFault=0,pointer=0;
     boolean fullframe = false;
      ArrayList<Character> stack = new ArrayList<Character>();
      char framelist[] = new char[fn]; //framelist

      //intialize empty frame list
        for(int f = 0; f < fn; f++)
        {
            framelist[f] = '*';

        }
        
        FileReader reader = new FileReader("D:\\test1.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StringTokenizer T = new StringTokenizer(buffer.readLine()," ");
        
        for(int i = 0; i < pn; i++){
            if(stack.contains(pagelist[i])){
                stack.remove(stack.indexOf(pagelist[i])); 
            }
            stack.add(pagelist[i]);//char array that has pagelist charecter once
            boolean pagenotfound=true;
            for(int j = 0; j < fn; j++){
                if(framelist[j] == pagelist[i]){
                    pagenotfound=false;
                    PageFault--;
                    break;
                }
            }
            PageFault++;
            if(pagenotfound){
                //frame list is complete, start lru
                if(fullframe){
                    int findMin = pn;
                    for(int x = 0; x < fn; x++){     
                        if(stack.contains(framelist[x])){ //4 7 6
                            int temp = stack.indexOf(framelist[x]);// stack of frequency
                            if(temp < findMin){
                                findMin = temp;
                                pointer = x;
                            }
                        }
                    }
                }
                framelist[pointer] = pagelist[i];
   
                //frame list is not complete
                if(!fullframe)
                {
                    pointer++;
                    if(pointer == fn)
                    {
                        pointer = 0;
                        fullframe = true;
                    }
                }
            }
            for(int w = 0; w < fn; w++){
                        System.out.printf("%3c ",framelist[w]);}
                         System.out.println(" ");
        }
        System.out.println("Total number of Faults: " + PageFault);
      }       
}
