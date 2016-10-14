/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbprak2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author imamsantosa
 */
public class BFS {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Node stationS = new Node("S", null, null);
        Node stationT = new Node("T", null, stationS);
        Node stationU = new Node("U", null, stationT);
        Node stationV = new Node("V", null, stationU);
        Node stationA = new Node("A", null, stationV);
        Node stationR = new Node("R", stationS, null);
        Node stationX = new Node("X", stationR, null);
        Node stationQ = new Node("Q", stationX, null);
        Node stationI = new Node("I", stationQ, null);
        Node stationJ = new Node("J", stationI, null);
        Node stationK = new Node("K", stationJ, null);
        Node stationB = new Node("B", null, null);
        Node stationC = new Node("C", stationB, null);
        Node stationD = new Node("D", stationC, null);
        Node stationE = new Node("E", stationD, null);
        Node stationF = new Node("F", stationE, null);
        Node stationG = new Node("G", stationF, null);
        Node stationH = new Node("H", stationG, null);
        Node stationM = new Node("M", null, null);
        Node stationL = new Node("L", null, null);
        Node stationN = new Node("N", null, null);
        Node stationP = new Node("P", null, stationN);
        Node stationO = new Node("O", stationN, null);
        
        stationS.rightChild = stationR;
        stationS.leftChild = stationT;
        
        stationT.leftChild = stationU;
        stationT.middleChild = stationV;
        
        stationU.leftChild = stationV;
        
        stationV.leftChild = stationA;
        stationV.middleChild = stationT;
        
        stationA.leftChild = stationX;
        stationA.middleChild = stationB;
        stationA.middleChild2 = stationI;
        
        stationR.rightChild = stationX;
        
        stationX.rightChild = stationQ;
        stationX.middleChild = stationA;
        
        stationQ.rightChild = stationI;
        
        stationI.rightChild = stationJ;
        stationI.middleChild = stationA;
        
        stationJ.rightChild = stationK;
        
        stationK.rightChild = stationD;
        
        stationB.leftChild = stationA;
        stationB.rightChild = stationC;
        
        stationC.rightChild = stationD;
        
        stationD.rightChild = stationE;
        stationD.middleChild = stationK;
        
        stationE.rightChild = stationF;
        stationE.middleChild = stationM;
        stationE.middleChild2 = stationL;
        stationE.middleChild3 = stationN;
        
        stationF.rightChild = stationG;
        
        stationG.rightChild = stationH;
        
        stationM.middleChild = stationE;
        
        stationL.middleChild = stationE;
        
        stationN.middleChild = stationE;
        stationN.leftChild = stationP;
        stationN.rightChild = stationO;
        BFSearch bfs = new BFSearch(stationX, stationI);
        //BreadthFirstSearch bfs = new BreadthFirstSearch(stationX, stationQ);
        //System.out.println(bfs.hitungGoal(stationI));
        ArrayList<String> jalur = bfs.compute();
        int [] totalCost = new int [jalur.size()];
        System.out.println("");
        System.out.println("DENGAN PERHITUNGAN COST :");
        for (int i = 0; i < jalur.size(); i++) {
            System.out.print(jalur.get(i)+" : ");
            String [] split = jalur.get(i).split("-");
            int tmpTotal = 0;
            for (int j = 0; j < split.length-1; j++) {
                tmpTotal += bfs.hitungCost(split[j], split[j+1]);
            }
            totalCost[i] = tmpTotal;
            System.out.println(totalCost[i]);
        }
        int costTerkecil = 10000;
        for (int i = 0; i < totalCost.length; i++) {
            if (costTerkecil>totalCost[i]) {
                costTerkecil = totalCost[i];
            }
        }
        System.out.println("");
        System.out.println("JALAN DENGAN COST TERPENDEK ADALAH :");
        for (int i = 0; i < jalur.size(); i++) {
            if (costTerkecil == totalCost[i]) {
                System.out.println(jalur.get(i)+" : "+totalCost[i]);
            }
        }
    }

    
}

class Node {

    //    A Unique Identifier for our node
    public String stationName;
    //    An arraylist containing a list of Nodes that
//    This node is directly connected to - It's child nodes.
    Node leftChild;
    Node rightChild;
    Node middleChild;
    Node middleChild2;
    Node middleChild3;

    public Node(String stationName, Node firstChild, Node secondChild){
        this.stationName = stationName;
        this.leftChild = firstChild;
        this.rightChild = secondChild;
        
    }

    public ArrayList<Node> getChildren(){
        ArrayList<Node> childNodes = new ArrayList<>();
        if(this.leftChild != null)
        {
            childNodes.add(leftChild);
        }
        if(this.rightChild != null) {
            childNodes.add(rightChild);
        }
        if(this.middleChild != null)
        {
            childNodes.add(middleChild);
        }
        if(this.middleChild2 != null) {
            childNodes.add(middleChild2);
        }
        if(this.middleChild3 != null) {
            childNodes.add(middleChild3);
        }
        return childNodes;
    }

    public boolean removeChild(Node n){
        return false;
    }
}

class BFSearch {

    Node startNode;
    Node goalNode;

    public BFSearch(Node start, Node goalNode){
        this.startNode = start;
        this.goalNode = goalNode;
    }

    public ArrayList<String> compute(){
        ArrayList<String> jalur = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        Node current = queue.remove();
            if(current.equals(this.goalNode)) {
                //System.out.println(explored);
                return jalur;
            }
            else{
                if(current.getChildren().isEmpty())
                    return jalur;
                else
                    queue.addAll(current.getChildren());
            }
        //explored.add(startNode);
        System.out.println("TITIK START '"+startNode.stationName +"' MENUJU '"+goalNode.stationName+"'");
        while(!queue.isEmpty()){
            current = queue.remove();
            String tmp = startNode.stationName+"-";
            System.out.println("jika melewati : "+current.stationName);
            tmp += getWay(current);
            System.out.println("maka jalurnya : "+tmp);
            jalur.add(tmp);
        }
        return jalur;
    }
    
    public String getWay(Node start){
        Queue<Node> queue = new LinkedList<>();
        String way = "";
        if (start.equals(goalNode)) {
            return goalNode.stationName;
        }else{
            queue.addAll(start.getChildren());
            Node nearNode = getNearNode(queue);
            way += start.stationName;
            way += "-"+getWay(nearNode);
            return way;
        }
    }
    
    public Node getNearNode(Queue<Node> queue){
        Queue<Node> tmpQueue = new LinkedList<>();
        int count1 = 10000;
        for (Node temp : queue) {
            int count2 = hitungWay(temp);
            if(count1>count2){
                count1 = count2;
            }
	}
        for (Node temp : queue) {
            if(count1 == hitungWay(temp)){
                return temp;
            }
	}
        return null;
    }
    
    public int hitungWay(Node start){
        int i=0;
        if(start.equals(goalNode)){
            return i;
        }

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        queue.add(start);
        //explored.add(startNode);
        while(!queue.isEmpty()){
            Node current = queue.remove();
            //System.out.println("-"+i+"-");
            if(current.equals(this.goalNode)) {
                return i;
            }
            else{
                if(current.getChildren().isEmpty())
                    return i;
                else
                    queue.addAll(current.getChildren());
            }
            explored.add(current);
            i++;
        }
        return i;
    }
    
    public int hitungCost(String start,String goal){
        String pointer = start+goal;
        if(pointer.equals("UT")||pointer.equals("TU"))
            return 75;
        else if(pointer.equals("UV")||pointer.equals("VU"))
            return 85;
        else if(pointer.equals("TV")||pointer.equals("VT"))
            return 25;
        else if(pointer.equals("TS")||pointer.equals("ST"))
            return 122;
        else if(pointer.equals("VA")||pointer.equals("AV"))
            return 167;
        else if(pointer.equals("SR")||pointer.equals("RS"))
            return 93;
        else if(pointer.equals("RX")||pointer.equals("XR"))
            return 57;
        else if(pointer.equals("XA")||pointer.equals("AX"))
            return 55;
        else if(pointer.equals("XQ")||pointer.equals("QX"))
            return 30;
        else if(pointer.equals("AB")||pointer.equals("BA"))
            return 145;
        else if(pointer.equals("AI")||pointer.equals("IA"))
            return 148;
        else if(pointer.equals("IQ")||pointer.equals("QI"))
            return 25;
        else if(pointer.equals("IJ")||pointer.equals("JI"))
            return 75;
        else if(pointer.equals("BC")||pointer.equals("CB"))
            return 98;
        else if(pointer.equals("CD")||pointer.equals("DC"))
            return 212;
        else if(pointer.equals("JK")||pointer.equals("KJ"))
            return 95;
        else if(pointer.equals("KD")||pointer.equals("DK"))
            return 102;
        else if(pointer.equals("DE")||pointer.equals("ED"))
            return 102;
        else if(pointer.equals("EM")||pointer.equals("ME"))
            return 73;
        else if(pointer.equals("EL")||pointer.equals("LE"))
            return 95;
        else if(pointer.equals("EN")||pointer.equals("NE"))
            return 97;
        else if(pointer.equals("EF")||pointer.equals("FE"))
            return 152;
        else if(pointer.equals("NP")||pointer.equals("PN"))
            return 65;
        else if(pointer.equals("NO")||pointer.equals("ON"))
            return 25;
        else if(pointer.equals("FG")||pointer.equals("GF"))
            return 83;
        else if(pointer.equals("GH")||pointer.equals("HG"))
            return 75;       
        
        return 0;
    }
}


