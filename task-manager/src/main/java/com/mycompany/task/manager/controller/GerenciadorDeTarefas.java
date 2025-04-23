/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager.controller;

import com.mycompany.task.manager.exceptions.TarefaJaConcluidaException;
import com.mycompany.task.manager.storage.PersistenciaDeTarefas;
import com.mycompany.task.manager.exceptions.TarefaNaoEncontradaException;
import com.mycompany.task.manager.models.Tarefa;
import com.mycompany.task.manager.models.TarefaSimples;
import com.mycompany.task.manager.models.TarefaUrgente;
import com.mycompany.task.manager.models.TarefaRecorrente;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alunolages
 */
public class GerenciadorDeTarefas {
    private final List<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        this.tarefas = PersistenciaDeTarefas.carregar();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        PersistenciaDeTarefas.salvar(tarefas);
    }

    public void exibirTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
            return;
        }
        for (Tarefa tarefa : tarefas) {
            tarefa.exibirDetalhes();
            System.out.println();
        }
    }

    public Tarefa buscarTarefaPorId(int id) throws TarefaNaoEncontradaException {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada!");
    }

    public void editarTarefa(int id) throws TarefaNaoEncontradaException {
        Tarefa tarefa = buscarTarefaPorId(id);
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Novo nome (descrição) para a tarefa (deixe em branco para não editar): ");
        String novaDescricao = scanner.nextLine();
        if (!novaDescricao.isEmpty()) {
            tarefa.setDescricao(novaDescricao);
        }

        tarefa.setDataEdicao(new Date());

        System.out.println("Escolha o tipo de tarefa:");
        System.out.println("1 - Simples");
        System.out.println("2 - Urgente");
        System.out.println("3 - Recorrente");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        if (tipo == 1) {
            tarefa = new TarefaSimples(tarefa.getDescricao());
        } else if (tipo == 2) {
            tarefa = new TarefaUrgente(tarefa.getDescricao());
        } else if (tipo == 3) {
            System.out.print("Quantos dias a tarefa se repetirá? ");
            int dias = scanner.nextInt();
            scanner.nextLine();
            tarefa = new TarefaRecorrente(tarefa.getDescricao(), dias);
        } else {
            System.out.println("Opção inválida! Tarefa não editada.");
            return;
        }

        tarefas.set(tarefas.indexOf(tarefa), tarefa);
        PersistenciaDeTarefas.salvar(tarefas);
        System.out.println("Tarefa editada com sucesso!");
    }

    public void concluirTarefa(int id) throws TarefaNaoEncontradaException, TarefaJaConcluidaException {
        Tarefa tarefa = buscarTarefaPorId(id);
        if (tarefa.isConcluida()) {
            throw new TarefaJaConcluidaException("A tarefa já está concluída.");
        }
        tarefa.concluirTarefa();
        PersistenciaDeTarefas.salvar(tarefas);
        System.out.println("Tarefa concluída com sucesso!");
    }

    public void excluirTarefa(int id) throws TarefaNaoEncontradaException {
        Tarefa tarefa = buscarTarefaPorId(id);
        tarefas.remove(tarefa);
        PersistenciaDeTarefas.salvar(tarefas);
        System.out.println("Tarefa excluída com sucesso!");
    }
    
    public boolean estaVazio() {
        return tarefas.isEmpty();
    }

    public boolean semTarefasParaConcluir() {
        return tarefas.stream().noneMatch(tarefa -> !tarefa.isConcluida());
    }

    public void exibirTarefasPendentes() {
        boolean encontrou = false;
        for (Tarefa tarefa : tarefas) {
            if (!tarefa.isConcluida()) {
                tarefa.exibirDetalhes();
                System.out.println();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Não há tarefas pendentes.");
        }
    }
}