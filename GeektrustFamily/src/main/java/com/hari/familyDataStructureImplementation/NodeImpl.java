package com.hari.familyDataStructureImplementation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hari.familyDataStructure.Gender;
import com.hari.familyDataStructure.Node;
/**
 * 
 * @author Hariom Yadav | 02-Dec-2019
 *
 */

public class NodeImpl {
	
    Node root;

    /**
     * Node constructor
     * @param root
     */
    public NodeImpl(Node root){
        this.root = root;
    }

    /**
     * Add child to mother
     * @param motherName : mother name
     * @param childName : child name
     * @param gender : child gender name Male/Female
     * @return
     */
    public String addChildMother(String motherName, String childName, Gender gender) {
        //System.out.println("child name : "+childName);
    	if(motherName==null || childName==null || gender==null){
            //System.out.println("Invalid data");
            return "";
        }
        //Algo
        //1. search node and get address
        //2. check husband is present or not --> checking for mother
        //3. add child to childlist 
        
        Node motherNode = getNode(root, motherName);
        if(motherNode == null) {
        	//System.out.println("No Mother found - " + motherName +"- so not adding child");
        	return "PERSON_NOT_FOUND";
        }
        if(motherNode.spouse == null) {
        	//System.out.println("No husband Not a mother - no spouse");
        	return "CHILD_ADDITION_FAILED";
        }
        
        Node childParent = getChildParentNode(motherNode);//it may be father node or mother node
        Node newChild = new Node(childName, gender, null, childParent, null);//Parent ?? mother or father node - solved
        motherNode.children.add(newChild);
        
        return "CHILD_ADDITION_SUCCEEDED";
    }

    /**
     * 
     * @param name : Person name
     * @param relationName : Relationship name
     * @return list of person name
     */
    public List<String> getRelation(String name, String relationName){
    	
    	if(relationName.equals("Sister-In-Law") ) {
    		
    		return getSisterInLaw(name);
    		
    	}else if(relationName.equals("Brother-In-Law") ) {
    		
    		return getBrotherInLaw(name);
    		
    	}else if(relationName.equals("Paternal-Uncle") ) {
    		
    		return getPaternalUncle(name);
    		
    	}else if(relationName.equals("Paternal-Aunt") ) {
    		
    		return getPaternalAunt(name);
    		
    	}else if(relationName.equals("Maternal-Uncle") ) {
    		
    		return getMaternalUncle(name);
    		
    	}else if(relationName.equals("Maternal-Aunt") ) {
    		
    		return getMaternalAunt(name);
    		
    	}else if(relationName.equals("Son") ) {
    		
    		return getSon(name);
    		
    	}else if(relationName.equals("Daughter") ) {
    		
    		return getDaughter(name);
    		
    	}else if(relationName.equals("Siblings") ) {
    		
    		List<Node> siblingNode = getSiblingNodes(name);
    		
    		List<String> rel = new LinkedList<>();
    		
    		Iterator<Node> it = siblingNode.iterator();
    		while(it.hasNext()) {
    			rel.add(it.next().name);
    		}
    		return rel;
    	}
    	return null;
    }
    
    /**
     * 
     * @param motherNode : mother name of a child
     * @return return parent node that is connected with grand parent
     */
    public Node getChildParentNode(Node motherNode){
		//get both parent node, motherNode
    	//check for parent of both mother and dad 
    	
    	if(motherNode.parent != null) {//grand parent present i.e. link to parent to parent present
    		return motherNode;
    	}else {
    		return getNode(root, motherNode.spouse);
    	}
    }
    
    

    //create utility package and add below all methods
    
    /**
     * Search a node in family hierarchy with name or spouse_name
     * 
     * @param root : family root node (King, Queen node)
     * @param name : person name
     * @return return person node address
     */
    public Node getNode(Node root, String name) {
        if (root == null) return null;
        if (name.equalsIgnoreCase(root.name) || name.equalsIgnoreCase(root.spouse)) return root;
        
        Iterator itr = root.children.iterator();
        Node child;
        
        while (itr.hasNext()) {
            child = getNode((Node) itr.next(), name);
            if (child != null) {
                return child;
            }
        }
        return null;
    }

    /**
     * Brothers of father
     * 
     * @param name : Person name
     * @return : return list of brothers of father
     * 
     */
    public List<String> getPaternalUncle(String name) {
    	//System.out.print("get Paternal Uncle "+name+" : ");
        Node node = getNode(root, name);
        List<String> paternalUncles = new ArrayList<String>();
        
        if(node == null) {
        	paternalUncles.add("PERSON_NOT_FOUND");
        	return paternalUncles;
        }
        
        // parent should be male
        if (node != null && node.parent != null && node.parent.gender.equals(Gender.MALE)
                && node.parent.parent != null && node.parent.parent.children.size() > 1) {

            List<Node> parentSiblings = node.parent.parent.children;
            
            for (Node n : parentSiblings) {
                //exclude father in paternal uncles
                if (n.gender.equals(Gender.MALE) && !n.name.equalsIgnoreCase(node.parent.name)) {
                    //System.out.print(n.name+" ");
                    paternalUncles.add(n.name);
                }
            }
        }
        //System.out.println();
        return paternalUncles;
    }
    //done

    /**
     * Brothers of mother
     * @param name : person name
     * @return : return list of brothers of mother 
     */
    public List<String> getMaternalUncle(String name) {
    	//System.out.print("get Maternal Uncle "+name+" : ");
        Node node = getNode(root, name);
        List<String> maternalUncles = new ArrayList<String>();

        // parent should be female
        if (node != null && node.parent != null && node.parent.gender.equals(Gender.FEMALE)
                && node.parent.parent != null && node.parent.parent.children.size() > 1) {

            List<Node> parentSiblings = node.parent.parent.children;

            for (Node n : parentSiblings) {
                if (n.gender.equals(Gender.MALE)){
                    //System.out.println(n.name);
                    maternalUncles.add(n.name);
                }
            }
            
        }
        //System.out.println();
        return maternalUncles;
    }
    //done

    /**
     * Sisters of Father
     * 
     * @param name : person name
     * @return : return list of sisters of Father for a given person name
     */
    public List<String> getPaternalAunt(String name) {
    	//System.out.print("get Paternal Aunt "+name+" : ");
        Node node = getNode(root, name);
        List<String> paternalAunt = new ArrayList<String>();
        
        // parent should be male
        if (node != null && node.parent != null && node.parent.gender.equals(Gender.MALE)
                && node.parent.parent != null && node.parent.parent.children.size() > 1) {
            List<Node> parentSiblings = node.parent.parent.children;
            
            for (Node n : parentSiblings) {
                if (n.gender.equals(Gender.FEMALE)){
                    //System.out.print(n.name+" ");
                    paternalAunt.add(n.name);
                }
            }
            
        }
        //System.out.println();
        return paternalAunt;
    }
    //done

    /**
     * Sisters of mother
     * 
     * @param name : person name
     * @return : return list of sisters of Mother for a given person name
     */
    public List<String> getMaternalAunt(String name) {
    	//System.out.print("get Maternal Aunt "+name+" : ");
        Node node = getNode(root, name);
        List<String> maternalAunt = new ArrayList<String>();
        
        // parent should be male
        if (node != null && node.parent != null && node.parent.gender.equals(Gender.FEMALE)
                && node.parent.parent != null && node.parent.parent.children.size() > 1) {
            List<Node> parentSiblings = node.parent.parent.children;
            
            for (Node n : parentSiblings) {
                if (n.gender.equals(Gender.FEMALE) && !node.parent.name.equalsIgnoreCase(n.name)) {
                    //System.out.print(n.name+" ");
                    maternalAunt.add(n.name);
                }
            }
            
        }
        //System.out.println();
        return maternalAunt;
    }


    /**
     * Brothers and Sisters
     * 
     * @param name : person name
     * @return : return list of Brothers and Sisters for a given person name
     */
    public List<Node> getSiblingNodes(String name) {
    	
    	//System.out.print("get Sibling Nodes "+name+" : ");
    	
        Node node = getNode(root, name);
        List<Node> siblings = new ArrayList<Node>();
        
        if (node != null && node.parent != null && node.parent.children.size() > 1) {
            List<Node> siblingNodes = node.parent.children;
            
            for (Node n : siblingNodes) {
            	//System.out.print(n.name+" ");
                if (!name.equalsIgnoreCase(n.name)) siblings.add(n);
            }
            
        }
        //System.out.println();
        return siblings;
    }

    // Brother of spouse* and husband of sisters
    //Spouse’s brothers (1st get spouse name --> )
    		// for male(hari) --> get wife --> get wife brother // ankit
    		//for female(neha) --> get husband --> get husband brother//op, chandan
    //Husbands of siblings (1st FEMALE sibling --> get FEMALE sibling husband)
    		//for female --> get FEMALE sibling --> get FEMALE sibling husband
    		//for male --> get FEMALE sibling --> get FEMALE sibling husband
    /**
     * Spouse’s brothers and Husbands of siblings both
     * @param name : person name
     * @return : return list of Spouse’s brothers OR list contain Husbands of siblings
     */
    public List<String> getBrotherInLaw(String name) {
    	//System.out.print("get Brother InLaw "+name+" :");
        Node node = getNode(root, name);
        List<String> brotherInLaws = new ArrayList<String>();

        if(node == null) {
        	brotherInLaws.add("PERSON_NOT_FOUND");
        	return brotherInLaws;
        }
        
        if (node != null && node.parent != null && node.parent.children.size() > 1) {

            //if self, husband of sisters
            if (node.name.equalsIgnoreCase(name)) {
                //filter sister's husbands
                List<Node> siblings = node.parent.children;
                
                for (Node sibling : siblings) {
                    if (sibling.gender.equals(Gender.FEMALE) && sibling.spouse != null && !sibling.name.equalsIgnoreCase(name)) {
                    	//System.out.print(sibling.spouse+" ");
                    	brotherInLaws.add(sibling.spouse);
                    	
                    }
                }
                
            }
            //If as spouse, brothers of spouse
            else {
                List<Node> spouseSiblings = node.parent.children;

                for (Node spouseSibling : spouseSiblings) {
                    if (spouseSibling.gender.equals(Gender.MALE) && !spouseSibling.name.equalsIgnoreCase(node.name)) {
                    	//System.out.print(spouseSibling.name+" ");
                    	brotherInLaws.add(spouseSibling.name);
                    }
                }
            }
        }
        //System.out.println();
        return brotherInLaws;
    }

    // Sister of spouse* and wife of brothers
    //Spouse’s sisters : (1st find spouse --> then sister)
    		//for female --> get husband --> get husband sister
    		//for male --> get wife --> get wife sister
    
    //Wives of siblings : (1st find all MALE sibling  --> then all sibling wife)
    		//for male --> get MALE sibling --> get MALE sibling wife
    		//for female ->  get MALE sibling --> get MALE sibling wife
    
    /**
     * Spouse’s sisters and Wives of siblings 
     * @param name : person name
     * @return : return list of Spouse’s sisters OR list contain Wives of siblings
     * 
     */
    public List<String> getSisterInLaw(String name) {
    	//System.out.print("get Sister InLaw "+name+" :");
        Node node = getNode(root, name);
        List<String> sisterInLaws = new ArrayList<String>();

        if(node == null) {
        	sisterInLaws.add("PERSON_NOT_FOUND");
        	return sisterInLaws;
        }
        
        
        if (node != null && node.parent != null && node.parent.children.size() > 1) {

            //if self, wife of brothers
            if (node.name.equalsIgnoreCase(name)) {
                //filter sister's husbands
                List<Node> siblings = node.parent.children;
                
                for (Node sibling : siblings) {
                    if (sibling.gender.equals(Gender.MALE) && sibling.spouse != null && !sibling.name.equalsIgnoreCase(name)) {
                    	//System.out.print(sibling.spouse+" ");
                    	sisterInLaws.add(sibling.spouse);
                    }
                }
                
            }
            //If as spouse, Sister of spouse
            else {
                List<Node> spouseSiblings = node.parent.children;

                for (Node spouseSibling : spouseSiblings) {
                    if (spouseSibling.gender.equals(Gender.FEMALE) && !spouseSibling.name.equalsIgnoreCase(node.name)) {
                    	//System.out.print(spouseSibling.name+" ");
                    	sisterInLaws.add(spouseSibling.name);
                    }
                }
                
            }
        }
        //System.out.println();
        return sisterInLaws;
    }


    //TODO : person not found comment
    /**
     * List of sons
     * @param name : person name
     * @return : return list of sons, if no sons then return list with 1 entry as PERSON_NOT_FOUND
     */
    public List<String> getSon(String name) {
    	//System.out.print("get son "+name+" : ");
        Node node = getNode(root, name);
        //TODO : node = null --> PERSON_NOT_FOUND
        //System.out.println("node name "+node.name);
        List<String> sons = new ArrayList<String>();
        
        if(node == null) {
        	sons.add("PERSON_NOT_FOUND");
        	return sons;
        }
        if (node != null && node.spouse != null && !node.children.isEmpty()) {
            List<Node> children = node.children;
            
            for (Node child : children) {
                if (child.gender.equals(Gender.MALE)){
                    //System.out.print(child.name+" ");
                    sons.add(child.name);
                }
            }
            
        }
        //System.out.println();
        return sons;
    }

    //
    /**
     * List of Daughters
     * @param name : person name
     * @return : return list of daughter
     */
    public List<String> getDaughter(String name) {
    	//System.out.print("get Daughter "+name+" : ");
        Node node = getNode(root, name);
        List<String> daughters = new ArrayList<String>();
        if (node != null && node.spouse != null && !node.children.isEmpty()) {
            List<Node> children = node.children;
            
            for (Node child : children) {
                if (child.gender.equals(Gender.FEMALE)){
                    //System.out.print(child.name+" ");
                    daughters.add(child.name);
                }
            }
            
        }
        //System.out.println();
        return daughters;
    }

    /**
     * print - pre order - checking for all node
     * @param root
     */
    public void printTree(Node root) {
        if (root == null) return;
        root.print();
        
        if (root.children != null && !root.children.isEmpty()) {
            for (Node node : root.children) {
                printTree(node);
            }
        }
        
    }


}
