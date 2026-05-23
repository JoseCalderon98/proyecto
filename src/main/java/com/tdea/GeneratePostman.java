package com.tdea;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class GeneratePostman {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode collection = mapper.createObjectNode();
        
        ObjectNode info = mapper.createObjectNode();
        info.put("name", "Banking API - Hexagonal & DDD (Completo)");
        info.put("schema", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        collection.set("info", info);

        ArrayNode variables = mapper.createArrayNode();
        ObjectNode var = mapper.createObjectNode();
        var.put("key", "baseUrl");
        var.put("value", "http://localhost:8080");
        variables.add(var);
        collection.set("variable", variables);

        ArrayNode items = mapper.createArrayNode();
        
        File baseDir = new File("postman/collections/Banking API - Hexagonal & DDD (Completo)");
        if (!baseDir.exists()) {
            System.out.println("Base directory does not exist!");
            return;
        }

        File[] folders = baseDir.listFiles(File::isDirectory);
        if (folders != null) {
            Arrays.sort(folders, Comparator.comparing(File::getName));
            for (File folder : folders) {
                if (folder.getName().startsWith(".")) continue;
                
                ObjectNode folderNode = mapper.createObjectNode();
                folderNode.put("name", folder.getName());
                ArrayNode folderItems = mapper.createArrayNode();
                
                File[] requests = folder.listFiles((d, name) -> name.endsWith(".request.yaml"));
                if (requests != null) {
                    Arrays.sort(requests, Comparator.comparing(File::getName));
                    for (File requestFile : requests) {
                        String name = requestFile.getName().replace(".request.yaml", "");
                        ObjectNode requestNode = parseYamlRequest(requestFile.toPath(), name, mapper);
                        if (requestNode != null) {
                            folderItems.add(requestNode);
                        }
                    }
                }
                folderNode.set("item", folderItems);
                items.add(folderNode);
            }
        }
        
        collection.set("item", items);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("banking_api_postman_collection.json"), collection);
        System.out.println("Collection successfully generated at banking_api_postman_collection.json");
    }

    private static ObjectNode parseYamlRequest(Path path, String name, ObjectMapper mapper) throws Exception {
        String content = Files.readString(path);
        
        String url = extractValue(content, "url: ");
        if (url == null) url = "";
        if (url.startsWith("\"") && url.endsWith("\"")) url = url.substring(1, url.length() - 1);
        
        String method = extractValue(content, "method: ");
        if (method == null) method = "GET";
        
        String description = extractValue(content, "description: ");
        
        ObjectNode itemNode = mapper.createObjectNode();
        itemNode.put("name", name);
        
        ObjectNode requestNode = mapper.createObjectNode();
        requestNode.put("method", method);
        
        ArrayNode headers = mapper.createArrayNode();
        if (content.contains("Content-Type: application/json")) {
            ObjectNode h = mapper.createObjectNode();
            h.put("key", "Content-Type");
            h.put("value", "application/json");
            h.put("type", "text");
            headers.add(h);
        }
        requestNode.set("header", headers);
        
        ObjectNode urlNode = mapper.createObjectNode();
        urlNode.put("raw", url);
        ArrayNode host = mapper.createArrayNode();
        host.add("{{baseUrl}}");
        urlNode.set("host", host);
        
        String pathStr = url.replace("{{baseUrl}}/", "");
        if (!pathStr.isEmpty()) {
            ArrayNode pathArr = mapper.createArrayNode();
            for (String p : pathStr.split("/")) {
                pathArr.add(p);
            }
            urlNode.set("path", pathArr);
        }
        requestNode.set("url", urlNode);
        
        if (description != null) {
            requestNode.put("description", description);
        }

        int bodyIdx = content.indexOf("content: |-");
        if (bodyIdx != -1) {
            String bodyContent = content.substring(bodyIdx + "content: |-".length());
            // Remove the leading newlines and indentation
            String[] lines = bodyContent.split("\n");
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                if (line.trim().isEmpty() && sb.length() == 0) continue;
                if (line.startsWith("    ")) {
                    sb.append(line.substring(4)).append("\n");
                } else {
                    sb.append(line).append("\n");
                }
            }
            
            ObjectNode bodyNode = mapper.createObjectNode();
            bodyNode.put("mode", "raw");
            bodyNode.put("raw", sb.toString().trim());
            ObjectNode options = mapper.createObjectNode();
            ObjectNode rawOpt = mapper.createObjectNode();
            rawOpt.put("language", "json");
            options.set("raw", rawOpt);
            bodyNode.set("options", options);
            
            requestNode.set("body", bodyNode);
        }
        
        itemNode.set("request", requestNode);
        itemNode.set("response", mapper.createArrayNode());
        
        return itemNode;
    }

    private static String extractValue(String content, String prefix) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                return line.substring(prefix.length()).trim();
            }
        }
        return null;
    }
}
