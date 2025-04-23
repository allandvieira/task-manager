/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.storage;

import com.google.gson.*;
import com.mycompany.task.manager.models.Tarefa;
import com.mycompany.task.manager.models.TarefaSimples;
import com.mycompany.task.manager.models.TarefaUrgente;
import com.mycompany.task.manager.models.TarefaRecorrente;
import java.lang.reflect.Type;
/**
 *
 * @author alunolages
 */
public class TarefaTypeAdapter implements JsonDeserializer<Tarefa> {

    @Override
    public Tarefa deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String tipo = jsonObject.get("tipo").getAsString();  // Assumindo que o JSON tem um campo "tipo" para distinguir as tarefas
        
        switch (tipo) {
            case "Simples":
                return context.deserialize(jsonObject, TarefaSimples.class);
            case "Urgente":
                return context.deserialize(jsonObject, TarefaUrgente.class);
            case "Recorrente":
                return context.deserialize(jsonObject, TarefaRecorrente.class);
            default:
                throw new JsonParseException("Tipo de tarefa desconhecido: " + tipo);
        }
    }
}
