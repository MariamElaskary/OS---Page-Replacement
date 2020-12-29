
package os;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class FIFO 
{
  static void FIFO(int NumOfFrames, int [] list) throws FileNotFoundException, IOException {
        boolean pagenotfound;
        int PageFault = 0, FirstItem = 0,i=0; //refrence string
        int FrameList[] = new int[NumOfFrames];
        for (int z = 0; z < NumOfFrames; z++) 
        {
            FrameList[z] = -1; //Initializing the frame list(empty)
        }
        FileReader reader = new FileReader("D:\\test1.txt");
        BufferedReader buffer = new BufferedReader(reader);
        StringTokenizer T = new StringTokenizer(buffer.readLine()," ");

        while(T.hasMoreTokens() && i<list.length)
        {
            System.out.print("(");
            pagenotfound = true; // 5 3 8 4 6
            for (int j = 0; j < FrameList.length; j++) {

                if (list[i] == FrameList[j]) { // -1 -1 -1 -1
                    pagenotfound = false; //page already exists
                    PageFault--;
                }
                System.out.print(FrameList[j] + " ");
            }
            System.out.print(")");
            System.out.println(" ");

            PageFault++;
            
            if (pagenotfound) //if page doesn't exist and frame list is full
               
            {
                FrameList[FirstItem] = list[i];
                FirstItem = (PageFault) % NumOfFrames;
            }
             
            i++;
            
        }
        buffer.close();
        System.out.print("(");
        for (int w = 0; w < NumOfFrames; w++) {
            System.out.print(FrameList[w] + " ");
        }
        System.out.print(")");
        System.out.println("Page Fault:" + " " + PageFault);   
 }
}//class
