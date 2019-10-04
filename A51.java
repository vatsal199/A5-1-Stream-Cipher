/*
Author: Dhameliya Vatsalkumar
Email: vatsal17137@gmail.com
Credit: https://www.youtube.com/watch?v=LgZAI3DdUA4
*/

import java.util.*;
import java.util.concurrent.TimeUnit;
public class A51{
	public static char[] rotate(char lfsr[]){
		for(int i=lfsr.length-1;i>0;i--){
			lfsr[i]=lfsr[i-1];
		}			
		lfsr[0]='0';
		return lfsr;
	}
	public static char[] clocking(char lfsr[],int lsfrTap[],String key){
		char resultXorLFSR;
		for(int i=0;i<key.length();i++){
			if(lsfrTap.length == 4)
				resultXorLFSR=XOR(lfsr[lsfrTap[0]],XOR(lfsr[lsfrTap[1]],XOR(lfsr[lsfrTap[2]],lfsr[lsfrTap[3]])));
			else
				resultXorLFSR=XOR(lfsr[lsfrTap[0]],lfsr[lsfrTap[1]]);
			
			char resultFinal = XOR(key.charAt(i),resultXorLFSR);
			lfsr=rotate(lfsr);
			lfsr[0]=resultFinal;	
		}
		
		return lfsr;
	}
	public static char XOR(char a,char b){
		char result;
		if(a==b)
			result = '0';
		else
			result = '1';
		return result;
	}
	public static char[] Initialize(String lsfr){
		//char result[] = new char[25];
		String temp;
		if(lsfr.equals("lsfr1")){
			temp="0000000000000000000";
			char result[] = new char[temp.length()];
			result=temp.toCharArray();
			return result;
		}
		else if(lsfr.equals("lsfr2")){
			temp="0000000000000000000000";
			char result[] = new char[temp.length()];
			result=temp.toCharArray();
			return result;
		}
		else{
			temp="00000000000000000000000";
			char result[] = new char[temp.length()];
			result=temp.toCharArray();
			return result;
		}
		//return result;
	}
	public static void main(String args[]) throws Exception{		
		System.out.println();
		System.out.println("Initiating LFSR.....");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		
		char lfsr1[]=Initialize("lsfr1");
		char lfsr2[]=Initialize("lsfr2");
		char lfsr3[]=Initialize("lsfr3");
		
		System.out.print("LFSR1: ");
		System.out.println(lfsr1);
		System.out.print("LFSR2: ");
		System.out.println(lfsr2);
		System.out.print("LFSR3: ");
		System.out.println(lfsr3);
		System.out.println();
		
		System.out.println("Initiating Clocking Bit.....");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		int clockingBit[]={8,10,10};
		System.out.print("Clocking Bits: 8 10 10");
		System.out.println();
		System.out.println();
		
		System.out.println("Initiating Tapped Bit.....");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		
		int lsfr1Tap[] = {13,16,17,18};
		int lsfr2Tap[] = {20,21};
		int lsfr3Tap[] = {7,20,21,22};
		
		System.out.print("LFSR1: ");
		System.out.println("13 16 17 18");
		System.out.print("LFSR2: ");
		System.out.println("20 21");
		System.out.print("LFSR3: ");
		System.out.println("7 20 21 22");
		System.out.println();
		
		System.out.println();
		System.out.print("Step 1\n");
		System.out.println("-------\n");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Initiating Session Key.....");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		
		//sessionKey = "0100 1110 0010 1111 0100 1101 0111 1100 0001 1110 1011 1000 1000 1011 0011 1010"
		String sessionKey = "0100111000101111010011010111110000011110101110001000101100111010";
		//String sessionKey =   "0001001000100011010001010110011110001001101010111100110111101111";
		System.out.print("Session Key: ");
		System.out.println(sessionKey);
		System.out.println();
		
		
		System.out.println("XOR-ing between session key and LFSR with tapped bit");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		
		lfsr1=clocking(lfsr1,lsfr1Tap,sessionKey);
		lfsr2=clocking(lfsr2,lsfr2Tap,sessionKey);
		lfsr3=clocking(lfsr3,lsfr3Tap,sessionKey);
		
		System.out.print("LFSR1: ");
		System.out.println(lfsr1);
		System.out.print("LFSR2: ");
		System.out.println(lfsr2);
		System.out.print("LFSR3: ");
		System.out.println(lfsr3);
		
		System.out.println();
		System.out.print("Step 2\n");
		System.out.println("-------\n");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Initiating Frame Counter.....");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		String frameCounter = "1110101011001111001011";
		//String frameCounter =   "0000000000000100110100";
		System.out.print("Frame Counter Created: ");
		System.out.println(frameCounter);
		System.out.println();
		
		
		System.out.println("XOR-ing between frame counter and LFSR with tapped bit");
		TimeUnit.SECONDS.sleep(1);
		System.out.println();
		
		lfsr1=clocking(lfsr1,lsfr1Tap,frameCounter);
		lfsr2=clocking(lfsr2,lsfr2Tap,frameCounter);
		lfsr3=clocking(lfsr3,lsfr3Tap,frameCounter);
		
		System.out.print("LFSR1: ");
		System.out.println(lfsr1);
		System.out.print("LFSR2: ");
		System.out.println(lfsr2);
		System.out.print("LFSR3: ");
		System.out.println(lfsr3);
		System.out.println();
		
		System.out.print("Step 3\n");
		System.out.println("-------\n");
		TimeUnit.SECONDS.sleep(3);
		
		char majority='0';
		String majorityKey="0";
		int counterZero = 0;
		int counterOne = 0;
		
		System.out.println("Irregular clocking of LFSR with majority bit\n");
		TimeUnit.SECONDS.sleep(1);
		
		for(int i=0;i<100;i++){
			counterZero = 0;
			counterOne = 0;
			
			if(lfsr1[clockingBit[0]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			if(lfsr2[clockingBit[1]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			if(lfsr3[clockingBit[2]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			
			if(counterZero>counterOne){
				if(lfsr1[clockingBit[0]] == majority)
					lfsr1=clocking(lfsr1,lsfr1Tap,majorityKey);
				if(lfsr2[clockingBit[1]] == majority)
					lfsr2=clocking(lfsr2,lsfr2Tap,majorityKey);
				if(lfsr3[clockingBit[2]] == majority)
					lfsr3=clocking(lfsr3,lsfr3Tap,majorityKey);
			}
			else{
				if(lfsr1[clockingBit[0]] != majority)
					lfsr1=clocking(lfsr1,lsfr1Tap,majorityKey);
				if(lfsr2[clockingBit[1]] != majority)
					lfsr2=clocking(lfsr2,lsfr2Tap,majorityKey);
				if(lfsr3[clockingBit[2]] != majority)
					lfsr3=clocking(lfsr3,lsfr3Tap,majorityKey);
			}
			
		}
		
		System.out.print("Irregular clocking result on LFSR1: ");
		System.out.println(lfsr1);
		System.out.print("Irregular clocking result on LFSR2: ");
		System.out.println(lfsr2);
		System.out.print("Irregular clocking result on LFSR3: ");
		System.out.println(lfsr3);
		System.out.println();
		
		System.out.print("Step 4\n");
		System.out.print("-------\n");
		TimeUnit.SECONDS.sleep(3);
		System.out.println();
		System.out.println("Generating Key Stream...... ");
		TimeUnit.SECONDS.sleep(1);
		
		char keyStream[] = new char[228];
		int keyStreamIndex = 0;
		
		System.out.println();
		
		
		for(int i=0;i<228;i++){
			counterZero = 0;
			counterOne = 0;
			
			if(lfsr1[clockingBit[0]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			if(lfsr2[clockingBit[1]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			if(lfsr3[clockingBit[2]] == majority)
				counterZero += 1;
			else
				counterOne += 1;
			
			char temp = XOR(lfsr1[lfsr1.length-1],XOR(lfsr2[lfsr2.length-1],lfsr3[lfsr3.length-1]));
			keyStream[keyStreamIndex++] = temp;
			
			if(counterZero>counterOne){
				if(lfsr1[clockingBit[0]] == majority)
					lfsr1=clocking(lfsr1,lsfr1Tap,majorityKey);
				if(lfsr2[clockingBit[1]] == majority)
					lfsr2=clocking(lfsr2,lsfr2Tap,majorityKey);
				if(lfsr3[clockingBit[2]] == majority)
					lfsr3=clocking(lfsr3,lsfr3Tap,majorityKey);
			}
			else{
				if(lfsr1[clockingBit[0]] != majority)
					lfsr1=clocking(lfsr1,lsfr1Tap,majorityKey);
				if(lfsr2[clockingBit[1]] != majority)
					lfsr2=clocking(lfsr2,lsfr2Tap,majorityKey);
				if(lfsr3[clockingBit[2]] != majority)
					lfsr3=clocking(lfsr3,lsfr3Tap,majorityKey);
			}
			
		}
		System.out.print("KeyStream: "+new String(keyStream));
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println();
		
		//----------------------------------------------------------------------------------------
		
		System.out.print("Enter plain text: ");
		Scanner scn = new Scanner(System.in);
		String plaintext = scn.nextLine();
		System.out.print("You entered :"+plaintext);
		
		System.out.println();
		char cipherText[] = generateCipherText(binaryArray(plaintext),keyStream);
		System.out.println();
		
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		System.out.print(". ");
		TimeUnit.SECONDS.sleep(1);
		System.out.print(". ");
		TimeUnit.SECONDS.sleep(1);
		System.out.print(". ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println();
		System.out.println();
		
		System.out.println("Cipher Text: ");
		System.out.println(new String(cipherText));
		System.out.println();
	}
	public static char[] binaryArray(String plain){
		char temp[]=plain.toCharArray();
		String temp1=new String();
		for(int i=0;i<temp.length;i++){
			temp1 = temp1.concat(Integer.toBinaryString(temp[i]));
		}
		System.out.print("\nPlainText: "+temp1);
		return temp1.toCharArray();
	}
	public static char[] generateCipherText(char plaintext[],char keyStream[]){
		
		if(plaintext.length > keyStream.length){
				char cipherText[] = new char[plaintext.length];
				int lowerIndex = plaintext.length - keyStream.length;
				for(int i=0;i<lowerIndex;i++)
					cipherText[i]=plaintext[i];
				for(int i=lowerIndex;i<plaintext.length;i++){
					cipherText[i] = XOR(plaintext[i],keyStream[i-lowerIndex]);
				}
				return cipherText;
				
		}
		else if(plaintext.length < keyStream.length){
				char cipherText[] = new char[keyStream.length];
				int lowerIndex = keyStream.length - plaintext.length;
				for(int i=0;i<lowerIndex;i++)
					cipherText[i]=keyStream[i];
				for(int i=lowerIndex;i<keyStream.length;i++){
					cipherText[i] = XOR(keyStream[i],plaintext[i-lowerIndex]);
				}
				return cipherText;
				
		}
		else{
			char cipherText[] = new char[keyStream.length];
			for(int i=0;i<keyStream.length;i++){
				cipherText[i] = XOR(keyStream[i],plaintext[i]);
			}
			return cipherText;
		}
	}
}