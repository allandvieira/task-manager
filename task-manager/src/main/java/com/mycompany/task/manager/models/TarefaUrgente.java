/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.models;

import com.mycompany.task.manager.models.enums.Prioridade;
import com.mycompany.task.manager.interfaces.Notificavel;
/**
 *
 * @author alunolages
 */
public class TarefaUrgente extends Tarefa implements Notificavel {

    public TarefaUrgente(String descricao) {
        super(descricao, Prioridade.Alta);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId());
        System.out.println("Tarefa Urgente: " + getDescricao());
        System.out.println("Data de Criação: " + getDataCriacao());
        System.out.println("Data de Edição: " + (getDataEdicao() != null ? getDataEdicao() : "Não editada"));
        System.out.println("Prioridade: Alta");
        System.out.println("Status: " + (isConcluida() ? "Concluída" : "Pendente"));
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação: Tarefa urgente " + getDescricao() + " precisa ser concluída!");
    }

    public void setDescricao(String descricao) {
        super.setDescricao(descricao);
    }
    
    @Override
    public String getTipo() {
        return "Urgente";
    }
}