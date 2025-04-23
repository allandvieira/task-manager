/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.models;

import com.mycompany.task.manager.models.enums.Prioridade;
/**
 *
 * @author alunolages
 */
public class TarefaSimples extends Tarefa {

    public TarefaSimples(String descricao) {
        super(descricao, Prioridade.Baixa);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId());
        System.out.println("Tarefa Simples: " + getDescricao());
        System.out.println("Data de Criação: " + getDataCriacao());
        System.out.println("Data de Edição: " + (getDataEdicao() != null ? getDataEdicao() : "Não editada"));
        System.out.println("Prioridade: Baixa");
        System.out.println("Status: " + (isConcluida() ? "Concluída" : "Pendente"));
    }

    public void setDescricao(String descricao) {
        super.setDescricao(descricao);
    }
    
    @Override
    public String getTipo() {
        return "Simples";
    }
}