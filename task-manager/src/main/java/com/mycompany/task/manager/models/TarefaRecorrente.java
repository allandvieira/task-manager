/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.models;

import com.mycompany.task.manager.interfaces.Notificavel;
import com.mycompany.task.manager.models.enums.Prioridade;
/**
 *
 * @author alunolages
 */
public class TarefaRecorrente extends Tarefa implements Notificavel {

    private int diasIntervalo;

    public TarefaRecorrente(String descricao, int diasIntervalo) {
        super(descricao, Prioridade.Media);
        this.diasIntervalo = diasIntervalo;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId());
        System.out.println("Tarefa Recorrente: " + getDescricao());
        System.out.println("Data de Criação: " + getDataCriacao());
        System.out.println("Data de Edição: " + (getDataEdicao() != null ? getDataEdicao() : "Não editada"));
        System.out.println("Repetição a cada: " + diasIntervalo + " dias.");
        System.out.println("Status: Sempre Ativa");
    }

    @Override
    public void concluirTarefa() {
        System.out.println("Tarefas recorrentes não podem ser concluídas.");
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação: A tarefa recorrente \"" + getDescricao() + 
                "\" ocorre a cada " + diasIntervalo + " dias.");
    }

    public int getDiasIntervalo() {
        return diasIntervalo;
    }

    public void setDiasIntervalo(int diasIntervalo) {
        this.diasIntervalo = diasIntervalo;
    }

    public void setDescricao(String descricao) {
        super.setDescricao(descricao);
    }
    
    @Override
    public String getTipo() {
        return "Recorrente";
    }
}