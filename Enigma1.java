package model;

import java.util.Scanner;

public class Enigma1
{	
	private static String[][] rotator1 =
	{{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" },
	 { "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" }};

	private static String[][] rotator2 =
	{{ "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" },
	 { "O", "Q", "S", "U", "W", "Y", "A", "C", "E", "G", "I", "K", "M", "N", "P", "R", "T", "V", "X", "Z", "B", "D", "F", "H", "J", "L" }};

	private static String[] reflector1 =
	 { "C", "F", "I", "L", "O", "R", "U", "X", "A", "D", "G", "J", "M", "P", "S", "V", "Y", "B", "E", "H", "K", "N", "Q", "T", "W", "Z" };
	
	private boolean isReversed = false;
	
	private String[] encryptedMsg;
	
	private void inputSplitter(String message)
	{
		String[] messageParts = new String[message.length()];
		for(int i = 0; i < message.length(); i++)
		{
			messageParts[i] = message.substring(i, i+1);
		}		
		encrypt(messageParts);
		System.out.println();
		decrypt(getEncryptedMsg());
	}
	
	
	private void encrypt(String[] messageParts)
	{
		String[] holder = new String[messageParts.length];
		for(int i = 0; i < messageParts.length; i++)
		{
			setReversed(false);
			holder[i] = firstRotator(messageParts[i]);
			holder[i] = secondRotator(holder[i]);
			holder[i] = reflector1(holder[i]);
			
			setReversed(true);
			holder[i] = secondRotator(holder[i]);
			holder[i] = firstRotator(holder[i]);
			System.out.print(holder[i]);
			
		}		
		setEncryptedMsg(holder);
	}
	
	private void decrypt(String[] messageParts)
	{
		String[] holder = new String[messageParts.length];
		for(int i = 0; i < messageParts.length; i++)
		{
			setReversed(true);
			holder[i] = firstRotator(messageParts[i]);
			holder[i] = secondRotator(holder[i]);
			holder[i] = reflectorIn(holder[i]);
			
			setReversed(false);			
			holder[i] = secondRotator(holder[i]);
			holder[i] = firstRotator(holder[i]);
			System.out.print(holder[i]);
		}		
	}

	private String firstRotator(String string)
	{
		int row = 0;
		if(isReversed())
		{
			row = 1;
		}
		
		//have to find the place of the string that is called in the array
		for(int i = 0; i < rotator1[row].length; i++)
		{
			if(string.equalsIgnoreCase(rotator1[row][i]))
			{
				string = rotator1[Math.abs(row-1)][i];
				break;
			}
		}
		return string;
	}


	private String secondRotator(String string)
	{
		int row = 0;
		if(isReversed())
		{
			row = 1;
		}
		//have to find the place of the string that is called in the array
		for(int i = 0; i < rotator2[row].length; i++)
		{
			if(string.equalsIgnoreCase(rotator2[row][i]))
			{
				string = rotator2[Math.abs(row-1)][i];
				break;
			}
		}				
		return string;
	}

	
	private String reflector1(String string)
	{
		//have to find the place of the string that is called in the array
		for(int i = 0; i < rotator1[1].length; i++)
		{
			if(string.equalsIgnoreCase(rotator2[1][i]))
			{
				string = reflector1[i];
				break;
			}
		}	
		
		return string;
	}
	
	private String reflectorIn(String string)
	{
		//have to find the place of the string that is called in the array
		for(int i = 0; i < rotator2[1].length; i++)
		{
			if(string.equalsIgnoreCase(rotator1[1][i]))
			{
				string = reflector1[i];
				break;
			}
		}	
		
		return string;
	}


	public boolean isReversed()
	{
		return isReversed;
	}


	public void setReversed(boolean isReversed)
	{
		this.isReversed = isReversed;
	}

	public String[] getEncryptedMsg()
	{
		return encryptedMsg;
	}


	public void setEncryptedMsg(String[] encryptedMsg)
	{
		this.encryptedMsg = encryptedMsg;
	}
	
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String message = keyboard.nextLine();
		Enigma1 en = new Enigma1();
		en.inputSplitter(message.toUpperCase());
		keyboard.close();
	}
}
