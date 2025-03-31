package com.mccayl;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Paths;

public class DomParserExample {
    public static void main(String[] args) throws Exception {
        // Получаем путь к папке ресурсов
        String resourcesPath = Paths.get("lab4","src", "main", "resources").toString();
        File inputFile = new File(resourcesPath, "students.xsd");
        File outputFile = new File(resourcesPath, "new_students.xml");

        // Создание фабрики и построителя документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Читаем XML-файл и создаем DOM-дерево
        Document document = builder.parse(inputFile);

        // Получаем список всех студентов
        NodeList nodeList = document.getElementsByTagName("Student");

        // Проходим по каждому студенту и выводим его имя
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element student = (Element) nodeList.item(i);
            String name = student.getElementsByTagName("Name").item(0).getTextContent();
            System.out.println("Student: " + name);
        }

        // Создание нового XML-документа
        Document newDoc = builder.newDocument();
        Element root = newDoc.createElement("Students");
        newDoc.appendChild(root);

        // Создание нового студента
        Element student = newDoc.createElement("Student");
        root.appendChild(student);

        // Добавляем имя студенту
        Element name = newDoc.createElement("Name");
        name.setTextContent("Some Student");
        student.appendChild(name);

        // Создаем объект для преобразования DOM в XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Записываем XML-файл в ресурсы
        transformer.transform(new DOMSource(newDoc), new StreamResult(outputFile));

        System.out.println("Файл успешно сохранен в " + outputFile.getAbsolutePath());
    }
}