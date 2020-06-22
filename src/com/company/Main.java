package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String filePath = "/Users/Bing/Documents/GitHub/BingnaYang.github.io/Quiz_System/src/com/company/questions.xml";
        File xmlFile = new File(filePath);
        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try{
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            List<Quiz> quizList = new ArrayList<>();
            NodeList nodeList = document.getElementsByTagName("Quiz");

            startMenu();
            int userInput = scanner.nextInt();
            switch (userInput){
                case 1:
                    System.out.println("Quiz Start");
                    for(int i=0; i<nodeList.getLength(); i++){
                        Node node = nodeList.item(i);
                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) node;
                            Integer id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                            String question = element.getElementsByTagName("question").item(0).getChildNodes().item(0).getNodeValue();
                            String optionA = element.getElementsByTagName("optionA").item(0).getChildNodes().item(0).getNodeValue();
                            String optionB = element.getElementsByTagName("optionB").item(0).getChildNodes().item(0).getNodeValue();
                            String optionC = element.getElementsByTagName("optionC").item(0).getChildNodes().item(0).getNodeValue();
                            String optionD = element.getElementsByTagName("optionD").item(0).getChildNodes().item(0).getNodeValue();
                            String answer =  element.getElementsByTagName("answer").item(0).getChildNodes().item(0).getNodeValue();
                            quizList.add(new Quiz(id,question,optionA,optionB,optionC,optionD,answer));
                        }
                    }
                    String answer = "";
                    int correctCount = 0;
                    for(Quiz quiz : quizList){
                        System.out.println("\n===================================\n"+
                                quiz.getId()+") "+quiz.getQuestion()+"\n"+
                                "A) "+quiz.getOptionA()+"\n"+
                                "B) "+quiz.getOptionB()+"\n"+
                                "C) "+quiz.getOptionC()+"\n"+
                                "D) "+quiz.getOptionD()+"\n");
                        System.out.println("Please enter your answer: ");
                        answer = scanner.next();

                        // Output For Testing
                        System.out.println("Your answer: "+answer);
                        System.out.println("Correct answer: "+quiz.getAnswer());

                        if(answer.toUpperCase().equals(quiz.getAnswer())){
                            correctCount++;
                        }
                    }
                    System.out.println("\nCorrect Answer: "+correctCount+" out of "+nodeList.getLength());
                    System.out.println("Your Java Quiz Score: ");

                    break;
                case 2:
                    System.out.println("Print All Quiz Questions");
                    for(int i=0; i<nodeList.getLength(); i++){
                        Node node1 = nodeList.item(i);
                        if(node1.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) node1;
                            Integer id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                            String question = element.getElementsByTagName("question").item(0).getChildNodes().item(0).getNodeValue();
                            quizList.add(new Quiz(id,question));
                        }
                    }
                    for(Quiz quiz : quizList){
                        System.out.println(quiz.toString());
                    }
                    break;
                case 3:
                    System.out.println("=====Warning=====");
                    System.out.println("Printing All Questions with Answer");
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Not an Option");
                    break;
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException | ParserConfigurationException e1){
            e1.printStackTrace();
        }
    }

    public static void startMenu(){
        System.out.println("============================");
        System.out.println("++++++Java Quiz System++++++");
        System.out.println("1) Start the Quiz");
        System.out.println("2) Print All Quiz Questions");
        System.out.println("3) Print Questions with Correct Answer");
        System.out.println("4) Quit");
        System.out.println("============================");
        System.out.println("Select One: ");
    }

}
