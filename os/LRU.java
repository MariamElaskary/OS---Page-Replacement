
package os;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LRU {
        public void LRU(int fn, int []pagelist,int pn) throws FileNotFoundException, IOException{
     int hit=0, PageFault=0, pointer=0;
     boolean fullframe = false;
      int framelist[] = new int[fn]; //framelist
      
      //intialize empty frame list
        for(int f = 0; f < fn; f++)
        {
            framelist[f] = -1;
        }
        
        FileReader reader = new FileReader("D:\\test1.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StringTokenizer T = new StringTokenizer(buffer.readLine()," ");
      
      for(int i = 0; i < pagelist.length; i++)
        {
            boolean pagenotfound = true; //no similar items
            for(int c = 0; c < framelist.length; c++)
            {
                if(framelist[c] == pagelist[i]) // page item already in frame
                {
                    hit++;
                    pagenotfound = false;
                    PageFault--;
                    break;
                }
            }
            PageFault++;
            if(pagenotfound)
            {
                if(fullframe) //frame list is full
                {
                    int j, firstitem=pagelist[0];
                    int repitems[] = new int[fn];// array of size fn
                    boolean repitems_flag[] = new boolean[fn];
                    for( j= firstitem; j < i+1; j++) //read array in inverse
                    {
                        for(int v = 0; v < framelist.length; v++)
                        {
                            //if frame item is found in future reference
                            if((pagelist[j] == framelist[v]) && (repitems_flag[v] == false))
                            {
                                repitems[v] = j;//add pos of j in new array
                                repitems_flag[v] = true;
                                break;
                            }
                        }
                        
                    }//find min position of j
                    pointer = 0;
                    int findMin = repitems[0];
                     //frame number of max item
                   
                    for(int b = 0; b < fn; b++)
                    {
                           if(repitems[b]== 0)//item not found in future reference
                        {
                            repitems[b] =99;
                        }

                        if(repitems[b] <= findMin)
                        {
                            findMin = repitems[b];
                            pointer = b;
                           
                        }
                    }
                } 
            framelist[pointer] = pagelist[i];
            
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

            for (int g=0;g<fn;g++){
            System.out.print(framelist[g]+" ");
            
            }System.out.println(" ");
        } //i loop
             
      System.out.print("No. of hits = "+hit+" ");
      System.out.print ("No. of misses = "+ (pn - hit)+ " " ); 
      System.out.println("Number of page fault = "+PageFault);
    }//function
}
