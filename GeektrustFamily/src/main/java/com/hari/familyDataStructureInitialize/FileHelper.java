package com.hari.familyDataStructureInitialize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import com.hari.family.*;
/**
 * 
 * @author Hariom Yadav | 04-Dec-2019
 *
 */
public class FileHelper {
	//read file
	//break and store each line in array - declare that array as public static
	//return that array
	
	/**
	 * Process input file
	 */
	public static void processInputFile() {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(
					"src/main/resources/input.txt"));
			String line = reader.readLine();
			while (line != null) {
				KingQueen.inputFromFile.add(line);
				//System.out.println(line);
				line = reader.readLine();// read next line
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
