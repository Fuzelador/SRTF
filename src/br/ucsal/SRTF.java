package br.ucsal;
import java.util.Scanner;

class WorkingCrew {
    String nome;
    int arrivalTime;
    int executionTime;
    int tempoRestante;

    public WorkingCrew(String nome, int arrivalTime, int executionTime) {
        this.nome = nome;
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
        this.tempoRestante = executionTime;
    }
}

public class SRTF {
    public static void main(String[] args) {
        int arrivalTime, executionTime, tempoAtual = 0, menorTempoRestante = Integer.MAX_VALUE;;
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o número de processos: ");
        int process = sc.nextInt();
        WorkingCrew[] Crew = new WorkingCrew[process];

        for (int i = 0; i < process; i++) {
            String nome = "P" + (i + 1);

            System.out.print(nome + ": " + "Tempo de chegada: ");
            arrivalTime = sc.nextInt();

            System.out.print("Tempo de execução: ");
            executionTime = sc.nextInt();

            Crew[i] = new WorkingCrew(nome, arrivalTime, executionTime);
            sc.nextLine();
        }

        sc.close();

        for (int i = 0; i < process - 1; i++) {
            for (int j = 0; j < process - i - 1; j++) {
                if (Crew[j].arrivalTime > Crew[j + 1].arrivalTime) {
                    WorkingCrew temp = Crew[j];
                    Crew[j] = Crew[j + 1];
                    Crew[j + 1] = temp;
                }
            }
        }

        System.out.println("\n\nExecução SRTF:");
        int processesCompleted = 0;
        WorkingCrew processoAtual = null;
        
        while (processesCompleted < process) {
            WorkingCrew processoMenorTempoRestante = null;

            for (int i = 0; i < process; i++) {
                WorkingCrew processo = Crew[i];

                if (processo.arrivalTime <= tempoAtual && processo.tempoRestante < menorTempoRestante && processo.tempoRestante > 0) {
                    menorTempoRestante = processo.tempoRestante;
                    processoMenorTempoRestante = processo;
                }
            }

            if (processoMenorTempoRestante == null) {
                tempoAtual++;
            } else {
                if (processoAtual != processoMenorTempoRestante) {
                    System.out.println(processoMenorTempoRestante.nome + " Tempo Atual: " + tempoAtual);
                    processoAtual = processoMenorTempoRestante;
                }

                processoMenorTempoRestante.tempoRestante--;

                if (processoMenorTempoRestante.tempoRestante == 0) {
                    processesCompleted++;
                    System.out.println(processoMenorTempoRestante.nome + " concluído.");
                    menorTempoRestante = Integer.MAX_VALUE;
                }

                tempoAtual++;
            }
        }
    }
}
