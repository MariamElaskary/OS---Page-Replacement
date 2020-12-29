
package os;

import java.util.*;
import java.io.*;  

public class OS {

    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int NumOfFrames, NumOfPages;
        System.out.println("Enter number of pages:");
        NumOfPages = sc.nextInt();
        System.out.println("Enter value of each page:");
        int list[] = new int[NumOfPages];
        char [] LRUList = new char [NumOfPages];
        for (int k = 0; k < NumOfPages; k++)
        {
            list[k] = sc.nextInt();
        }

        display(list);
        write(NumOfPages, list);

        System.out.println("Enter number of frames:");
        NumOfFrames = sc.nextInt();
       
        menu (NumOfFrames, list, NumOfPages, LRUList);
    }
    
    static void menu(int NumOfFrames, int [] list, int NumOfPages, char [] LRUList) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int ch;
        
        System.out.println("1)" + " " + "FIFO");
        System.out.println("2)" + " " + "LRU");
        System.out.println("3)" + " " + "Optimal");         
        System.out.print("Choose an Algorithm: ");
        ch = sc.nextInt();
        
        int FrameList[] = new int[NumOfFrames];
        
        switch(ch) 
        {
            case 1:
            FIFO.FIFO(NumOfFrames, list);
            break;
            
            case 2:
            System.out.println("Please re-enter your reference list: ");
            for (int k = 0; k < NumOfPages; k++)
           {
            LRUList[k] = sc.next().charAt(0);
           }
            LRU lru = new LRU ();
            lru.LRU(NumOfFrames, LRUList, NumOfPages);
            break;
            
            case 3:
            OPTIMAL optimal = new OPTIMAL();
            optimal.Optimal(NumOfFrames, list, NumOfPages);
            break;
        }
    }

    static void display(int[] list) {

        System.out.print("Pages:" + " " + "{");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("}");
    }

    public static void write(int n, int [] RS) throws Exception {
        FileWriter writer = new FileWriter("D:\\test1.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write("Refrence String:" + " ");
        for (int i = 0; i < RS.length; i++) 
        {
            buffer.write(RS[i] + " ");
        }
        buffer.close();

    }

}


