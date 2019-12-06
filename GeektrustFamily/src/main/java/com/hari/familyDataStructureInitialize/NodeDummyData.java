package com.hari.familyDataStructureInitialize;

import java.util.ArrayList;

import com.hari.familyDataStructure.Gender;
import com.hari.familyDataStructure.Node;
/**
 * 
 * @author Hariom Yadav | 01-Dec-2019
 *
 */

public class NodeDummyData {

	public static Node initializeDummyData(){

        //-------king-----------
        Node rootNode = new Node("King Shan", Gender.MALE, "Queen anga", null, null);

        //------kings child level 1--------
        Node node_c1 = new Node("Chit", Gender.MALE, "Amba", rootNode, null);
        Node node_c2 = new Node("Ish", Gender.MALE, null, rootNode, null);
        Node node_c3 = new Node("Vich", Gender.MALE, "Lika", rootNode, null);
        Node node_c4 = new Node("Aras", Gender.MALE, "Chitra", rootNode, null);
        Node node_c5 = new Node("Satya", Gender.FEMALE, "Vyan", rootNode, null);

        rootNode.setChildren(new ArrayList<Node>());
        rootNode.children.add(node_c1);
        rootNode.children.add(node_c2);
        rootNode.children.add(node_c3);
        rootNode.children.add(node_c4);
        rootNode.children.add(node_c5);

        //--------- node_c1 child-----------
        Node node_c1_c1 = new Node("Dritha", Gender.FEMALE, "Jaya", node_c1, null);
        Node node_c1_c2 = new Node("Tritha", Gender.FEMALE, null, node_c1, null);
        Node node_c1_c3 = new Node("Vritha", Gender.MALE, null, node_c1, null);

        node_c1.setChildren(new ArrayList<Node>());
        node_c1.children.add(node_c1_c1);
        node_c1.children.add(node_c1_c2);
        node_c1.children.add(node_c1_c3);


        //------- node_c3 child ------
        Node node_c3_c1 = new Node("Vila", Gender.FEMALE, null, node_c3, null);
        Node node_c3_c2 = new Node("Chika", Gender.FEMALE, null, node_c3, null);

        node_c3.setChildren(new ArrayList<Node>());
        node_c3.children.add(node_c3_c1);
        node_c3.children.add(node_c3_c2);

        //-------child node_c4 child -----
        Node node_c4_c1 = new Node("Jnki", Gender.FEMALE, "Arit", node_c4, null);
        Node node_c4_c2 = new Node("Ahit", Gender.MALE, null, node_c4, null);

        node_c4.setChildren(new ArrayList<Node>());
        node_c4.children.add(node_c4_c1);
        node_c4.children.add(node_c4_c2);

        //------child node_c5 child-----
        Node node_c5_c1 = new Node("Asva", Gender.MALE, "Satvy", node_c5, null);
        Node node_c5_c2 = new Node("Vyas", Gender.MALE, "Krpi", node_c5, null);
        Node node_c5_c3 = new Node("Atya", Gender.FEMALE, null, node_c5, null);

        node_c5.setChildren(new ArrayList<Node>());
        node_c5.children.add(node_c5_c1);
        node_c5.children.add(node_c5_c2);
        node_c5.children.add(node_c5_c3);

        // ********* ---level 2 start--- *********

        // node_c1_c1 child
        Node node_c1_c1_c1 = new Node("Yodhan", Gender.MALE, null, node_c1_c1, null);
        node_c1_c1.setChildren(new ArrayList<Node>());
        node_c1_c1.children.add(node_c1_c1_c1);

        // node_c4_c1 (jnki) child
        Node node_c4_c1_c1 = new Node("Laki", Gender.MALE, null, node_c4_c1, null);
        Node node_c4_c1_c2 = new Node("Lavnya", Gender.FEMALE, null, node_c4_c1, null);

        node_c4_c1.setChildren(new ArrayList<Node>());
        node_c4_c1.children.add(node_c4_c1_c1);
        node_c4_c1.children.add(node_c4_c1_c2);


        // node_c5_c1 child
        Node node_c5_c1_c1 = new Node("Vasa", Gender.MALE, null, node_c5_c1, null);
        node_c5_c1_c1.setChildren(new ArrayList<Node>());
        node_c5_c1.children.add(node_c5_c1_c1);

        // node_c5_c2 child
        Node node_c5_c2_c1 = new Node("Kriya", Gender.MALE, null, node_c5_c2, null);
        Node node_c5_c2_c2 = new Node("Krithi", Gender.FEMALE, null, node_c5_c2, null);

        node_c5_c2.setChildren(new ArrayList<Node>());
        node_c5_c2.children.add(node_c5_c2_c1);
        node_c5_c2.children.add(node_c5_c2_c2);

        return rootNode;
    }
	
	
}
