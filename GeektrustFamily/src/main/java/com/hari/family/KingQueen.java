package com.hari.family;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hari.familyDataStructure.Gender;
import com.hari.familyDataStructure.Node;
import com.hari.familyDataStructureImplementation.NodeImpl;
import com.hari.familyDataStructureInitialize.FileHelper;
import com.hari.familyDataStructureInitialize.NodeDummyData;
/**
 * 
 * @author Hariom Yadav | 01-Dec-2019
 *
 */

public class KingQueen {
	public static Node rootNode = NodeDummyData.initializeDummyData();
	public static NodeImpl nodeImpl = new NodeImpl(rootNode);
	public static LinkedList<String> inputFromFile = new LinkedList<>();

    public static void main(String[] args) {
  
        //System.out.println("old "+inputFromFile);
    	FileHelper.processInputFile();//update inputFromFile variable
    	//System.out.println("new " +inputFromFile);
        
        for(String inputLine : inputFromFile) {
        	//System.out.println(inputLine);
        	
        	String[] sarr = inputLine.split(" ");
        	switchCase(sarr);
        	
        	//System.out.println();
        }
    }
    
    public static void switchCase(String[] inputStr) {
    	
    	if(inputStr.length == 1) {
    		System.out.println("INVALID_INPUT_FORMAT");
    		return;
    	}
    	
    	if(inputStr.length < 3) {
    		
    		if(inputStr.length>0 && inputStr[0].equals("ADD_CHILD"))
    			System.out.println("CHILD_ADDITION_FAILED");
    		
    		else if(inputStr.length>0 && inputStr[0].equals("GET_RELATIONSHIP"))
    			System.out.println("NONE");
    		
    		return;
    		
    	}else {
    		String cs = inputStr[0];
    		
    		switch (cs) {
    		
    		case "ADD_CHILD": //Sample : ADD_CHILD Chitra Aria Female
    				
    				String motherName = inputStr[1];
    				String childName = inputStr[2];
    				Gender gender;
    				
    				if(inputStr[3].equals("Female")) {
    					gender = Gender.FEMALE;
    				}else if(inputStr[3].equals("Male")){
    					gender = Gender.MALE;
    				}else {
    					System.out.println("CHILD_ADDITION_FAILED");
    					break;
    				}
    				//System.out.println(motherName + " "+ childName);
    				
    				if( nodeImpl.addChildMother(motherName, childName, gender).equals("CHILD_ADDITION_SUCCEEDED") )
    					System.out.println("CHILD_ADDITION_SUCCEEDED");
    				
    				else if( nodeImpl.addChildMother(motherName, childName, gender).equals("PERSON_NOT_FOUND") )
    					System.out.println("PERSON_NOT_FOUND");
    			
    				break;
    				
    		case "GET_RELATIONSHIP"://Sample : GET_RELATIONSHIP Aria Siblings
    			
    			List<String> rel = nodeImpl.getRelation(inputStr[1], inputStr[2]);
    			if(rel.size() == 1 && rel.get(0).equals("PERSON_NOT_FOUND")) {
    				System.out.println("PERSON_NOT_FOUND");
    				break;
    			}
    			
    			if(rel == null || rel.size() == 0) {
    				System.out.println("NONE");
    				break;
    				
    			}else {
    				Iterator<String> it = rel.iterator();
    				
    				while(it.hasNext())
    					System.out.print(it.next()+" ");
    				
    				System.out.println();
    				break;
    			}
    		default:
    			break;
    		}
    	}
        
    }
}