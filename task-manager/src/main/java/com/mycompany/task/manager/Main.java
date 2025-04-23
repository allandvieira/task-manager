/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.task.manager;

import com.mycompany.task.manager.controller.GerenciadorDeTarefas;
import com.mycompany.task.manager.models.TarefaSimples;
import com.mycompany.task.manager.models.TarefaUrgente;
import com.mycompany.task.manager.models.TarefaRecorrente;
import com.mycompany.task.manager.exceptions.TarefaJaConcluidaException;
import com.mycompany.task.manager.exceptions.TarefaNaoEncontradaException;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author alunolages
 */
public class Main {

    public static void main(String[] args) {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;

        while (rodando) {
            System.out.println("\nTask-Manager:");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Editar tarefa");
            System.out.println("3 - Concluir tarefa");
            System.out.println("4 - Visualizar tarefas");
            System.out.println("5 - Excluir tarefa");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = -1;

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Descrição da tarefa: ");
                    String descricao = scanner.nextLine();

                    int tipo = -1;
                    while (tipo < 1 || tipo > 3) {
                        try {
                            System.out.println("Tipo de tarefa (1 - Simples, 2 - Urgente, 3 - Recorrente): ");
                            tipo = scanner.nextInt();
                            scanner.nextLine();

                            if (tipo == 1) {
                                gerenciador.adicionarTarefa(new TarefaSimples(descricao));
                            } else if (tipo == 2) {
                                gerenciador.adicionarTarefa(new TarefaUrgente(descricao));
                            } else if (tipo == 3) {
                                System.out.println("Repetir a cada quantos dias? ");
                                int dias = scanner.nextInt();
                                scanner.nextLine();
                                gerenciador.adicionarTarefa(new TarefaRecorrente(descricao, dias));
                            } else {
                                System.out.println("Tipo inválido. Tarefa não criada.");
                                tipo = -1;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, digite um número válido para o tipo.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case 2:
                    if (gerenciador.estaVazio()) {
                        System.out.println("Não há tarefas para editar.");
                        break;
                    }
                    gerenciador.exibirTarefas();
                    System.out.print("Digite o ID da tarefa para editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        gerenciador.editarTarefa(idEditar);
                    } catch (TarefaNaoEncontradaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    if (gerenciador.semTarefasParaConcluir()) {
                        System.out.println("Não há tarefas pendentes para concluir.");
                        break;
                    }
                    gerenciador.exibirTarefasPendentes();
                    System.out.print("Digite o ID da tarefa para concluir: ");
                    int idConcluir = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        gerenciador.concluirTarefa(idConcluir);
                    } catch (TarefaNaoEncontradaException | TarefaJaConcluidaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    gerenciador.exibirTarefas();
                    break;

                case 5:
                    if (gerenciador.estaVazio()) {
                        System.out.println("Não há tarefas para excluir.");
                        break;
                    }
                    gerenciador.exibirTarefas();
                    System.out.print("Digite o ID da tarefa para excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        gerenciador.excluirTarefa(idExcluir);
                    } catch (TarefaNaoEncontradaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 6:
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}