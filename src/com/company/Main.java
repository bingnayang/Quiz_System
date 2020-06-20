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

public class Main {

    public static void main(String[] args) {
        String filePath = "/Users/Bing/Documents/GitHub/BingnaYang.github.io/Quiz_System/src/com/company/questions.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try{
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            System.out.println(document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("Quiz");

            // Now XML is loaded as Document in memory
            // Convert it to Object List
            List<Quiz> quizList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                quizList.add(getQuestion(nodeList.item(i)));
            }
            // Print Quiz questions list information
            for (Quiz question : quizList) {
                System.out.println(question.toString());
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException | ParserConfigurationException e1){
            e1.printStackTrace();
        }
    }

    private static Quiz getQuestion(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Quiz quizQuestion = new Quiz();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            quizQuestion.setId(Integer.parseInt(getTagValue("id",element)));
            quizQuestion.setQuestion(getTagValue("question",element));
            quizQuestion.setOptionA(getTagValue("optionA",element));
            quizQuestion.setOptionB(getTagValue("optionB",element));
            quizQuestion.setOptionC(getTagValue("optionC",element));
            quizQuestion.setOptionD(getTagValue("optionD",element));
            quizQuestion.setAnswer(getTagValue("answer",element));
        }
        return quizQuestion;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
