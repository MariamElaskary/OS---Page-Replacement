
package os;
import java.io.*;
import java.util.StringTokenizer;

public class OPTIMAL 
{
  public void Optimal(int fn, int []pagelist,int pn) throws FileNotFoundException, IOException
  {
     int hit=0,PageFault=0,pointer=0;
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
                    int repitems[] = new int[fn];// array of size fn
                    boolean repitems_flag[] = new boolean[fn];
                    for(int j = i + 1; j < pagelist.length; j++) 
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
                    }//find max position of j
                    int findMax = repitems[0]; 
                    pointer = 0; //frame number of max item
                   
                    for(int b = 0; b < framelist.length; b++)
                    {
                        if(repitems[b] == 0) //item not found in future reference
                        {
                            repitems[b] =99;
                        }

                        if(repitems[b] > findMax)
                        {
                            findMax = repitems[b];
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
