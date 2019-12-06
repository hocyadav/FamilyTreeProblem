package com.hari.familyDataStructure;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Hariom Yadav | 01-Dec-2019
 *
 */
public class Node {

    public String name;
    public Gender gender;
    public String spouse;
    public Node parent;
    public List<Node> children;

    public Node(String name, Gender sex, String spouse, Node parent, List<Node> children) {
        this.name = name;
        this.gender = sex;
        this.spouse = spouse;
        this.parent = parent;
        if(children!=null){
            this.children = children;
        }else{
            this.children= new ArrayList<Node>();
        }
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void print(){
        System.out.print("  name: "+ this.name);
        System.out.print("  gender: "+ this.gender);
        System.out.println("  spouse: "+ this.spouse);
    }

}
