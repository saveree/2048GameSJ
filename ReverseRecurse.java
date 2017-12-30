/*  
 * Author: Saveree Joshipura
 * ID: A13349228 cs8bwaph
 * FileName: ReverseRecurse.java
 */
import java.util.*;
public class ReverseRecurse {

   /** initializes array 
    * @return int[]  
    **/
	public int[] initArray()
	{
		Scanner in = new Scanner(System.in);
		System.out.println(PSA7Strings.MAX_NUM);
		int size = 0; 
 		int count = 0; 
 		if(in.hasNextInt())
 		{
 			size = in.nextInt();
 		} 
 		else
 		{
 			System.exit(1);
 		}

 		while(size <=0)
		{
			System.out.println(PSA7Strings.TOO_SMALL);
			if(in.hasNextInt())
 			{
 				size = in.nextInt();
 			} 
 			else
 			{
 				System.exit(1);
 			}
		}

		int[] intArray = new int[size];
		System.out.println(PSA7Strings.ENTER_INTS);
		while(count < size)
		{
			int x = in.nextInt();
			if(x < 0)
			{
				System.out.println(PSA7Strings.TOO_SMALL);
				System.out.println(PSA7Strings.ENTER_INTS);
			}
			else
			{
				intArray[count] = x;
				count++;
			}
		}
		return intArray;
	}

   /** prints array 
    * @param int[] array 
    **/
	public void printArray(int[] array)
	{
		if(array.length == 0 || array == null)
		{
			System.out.println(PSA7Strings.EMPTY);
			return;
		}
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");	
		}
		System.out.println();
	}

   /** reverses array, modifies original array  
    * @param int[] originalArray, int low, int high 
    **/
	public void reverse(int[] originalArray, int low, int high)
	{
		if(originalArray == null)
		{
			return;
		}
		if(low < high) 
		{
			int temp = originalArray[low];
			originalArray[low] = originalArray[high];
			originalArray[high] = temp;
			this.reverse(originalArray, ++low, --high);
		}
		else
		{
			return;
		}
	}

   /** reverses array, preserves original array 
    * @param int[] original Array
    * @return int[]
    **/
	public int[] reverse(int[] originalArray)
	{
		if(originalArray == null)
		{
			return new int[0];
		}
		int length = originalArray.length;
		int low = 0; 
		int high = length-1; 
		int[] newArray = new int[length];

		
		if(length == 0) 
		{
			return originalArray;
		}
		if(length <=1)
		{
			return originalArray;
		}
		
		//switches highest and lowest elements
		newArray[low] = originalArray[high];
		newArray[high] = originalArray[low];
		low = low + 1; high = high - 1; length = length - 2;

		//creates array of middle elements 
		int[] midArray = new int[length];
		System.arraycopy(originalArray, low, midArray, 0, length);
		this.reverse(midArray);

		for(int i = 0; i < midArray.length; i++)
		{
			newArray[low+i] = midArray[i];
		}

		return newArray;
	}
}