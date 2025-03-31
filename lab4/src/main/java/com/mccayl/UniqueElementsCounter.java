package com.mccayl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class UniqueElementsCounter {
    public static void main(String[] args) throws Exception {
        // Создание фабрики и построителя документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Читаем XML-файл и создаем DOM-дерево
        String resourcesPath = Paths.get("lab4","src", "main", "resources").toString();
        Document document = builder.parse(new File(resourcesPath,"students.xsd"));

        // Set для хранения уникальных имен элементов
        Set<String> uniqueElements = new HashSet<>();
        NodeList nodeList = document.getElementsByTagName("*");

        // Проходим по всем узлам и добавляем их локальные имена в Set
        for (int i = 0; i < nodeList.getLength(); i++) {
            uniqueElements.add(nodeList.item(i).getNodeName());
        }

        // Вывод количества уникальных имен
        System.out.println("Количество уникальных элементов: " + uniqueElements.size());
    }
}
