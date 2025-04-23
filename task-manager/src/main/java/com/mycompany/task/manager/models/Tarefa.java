/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.models;

import java.util.Date;
import com.mycompany.task.manager.storage.IDManager;
import com.mycompany.task.manager.models.enums.Prioridade;
/**
 *
 * @author alunolages
 */
public abstract class Tarefa {
    private static int contadorId = IDManager.carregarUltimoId();
    
    private final int id;
    private String descricao;
    private Date dataCriacao;
    private Date dataEdicao;
    private boolean concluida;
    private Prioridade prioridade;

    // Construtor da classe Tarefa
    public Tarefa(String descricao, Prioridade prioridade) {
        this.id = ++contadorId;
        this.descricao = descricao;
        this.dataCriacao = new Date();
        this.dataEdicao = null;
        this.concluida = false;
        this.prioridade = prioridade;

        IDManager.salvarUltimoId(contadorId);
    }

    // Métodos Getter e Setter
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Date dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluirTarefa() {
        this.concluida = true;
    }

    public abstract void exibirDetalhes();
    
    public abstract String getTipo();
}