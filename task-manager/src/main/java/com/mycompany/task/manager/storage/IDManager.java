/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.storage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author alunolages
 */
public class IDManager {
    private static final String ID_FILE_PATH = "id.json";
    
    public static int carregarUltimoId() {
        try (FileReader reader = new FileReader(ID_FILE_PATH)) {
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            return jsonObject.has("ultimoId") ? jsonObject.get("ultimoId").getAsInt() : 0;
        } catch (IOException e) {
            return 0;
        }
    }

    public static void salvarUltimoId(int ultimoId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ultimoId", ultimoId);
        
        try (FileWriter writer = new FileWriter(ID_FILE_PATH)) {
            new Gson().toJson(jsonObject, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o ID: " + e.getMessage());
        }
    }
}