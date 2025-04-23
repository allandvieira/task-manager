/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.task.manager.models.Tarefa;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alunolages
 */
public class PersistenciaDeTarefas {
    private static final String ARQUIVO = "tarefas.json";

    public static List<Tarefa> carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Tarefa.class, new TarefaTypeAdapter())
                    .create();

            Type tarefaListType = new TypeToken<List<Tarefa>>() {}.getType();
            return gson.fromJson(reader, tarefaListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void salvar(List<Tarefa> tarefas) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<JsonObject> jsonList = new ArrayList<>();

            for (Tarefa tarefa : tarefas) {
                JsonObject obj = gson.toJsonTree(tarefa).getAsJsonObject();
                obj.addProperty("tipo", tarefa.getTipo());
                jsonList.add(obj);
            }

            gson.toJson(jsonList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}