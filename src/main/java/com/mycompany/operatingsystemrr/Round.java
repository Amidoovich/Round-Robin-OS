/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.operatingsystemrr;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
/**
 *
 * @author ahmed
 */
public class Round {

        public static void main(String[] args) {
              Scanner scanner = new Scanner(System.in);
              
       
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        Process[] processes = new Process[numProcesses];

        
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter details for Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        
        System.out.print("Enter time quantum for Round Robin: ");
        int timeQuantum = scanner.nextInt();

        scanner.close();

        
        roundRobin(processes, timeQuantum);

        
        displayResults(processes);
    }

    public static void roundRobin(Process[] processes, int timeQuantum) {
        int numProcesses = processes.length;
        int[] remainingTime = new int[numProcesses];

        Process temp;
        for(int i=0;i<numProcesses;i++){
          for(int j=i+1;j<numProcesses;j++){
            if(processes[i].arrivalTime> processes[j].arrivalTime){
             temp=processes[i];
             processes[i]=processes[j];
             processes[j]=temp;
            }
          }  
        }
        int currentTime =processes[0].arrivalTime;
        for (int i = 0; i < numProcesses; i++) {
            remainingTime[i] = processes[i].burstTime;
        }
        int c=0;
        while (true) {
            boolean allFinished = true;

            for (int i = 0; i < numProcesses; i++) {
                if (remainingTime[i] > 0) {
                    allFinished = false;

                    if (remainingTime[i] > timeQuantum) {
                        if(c<numProcesses)
                            {
                            processes[i].responseTime=currentTime-processes[i].arrivalTime;
                            c++;
                            }                        
                        currentTime += timeQuantum;
                        remainingTime[i] -= timeQuantum;
                    } else {
                        if(c<numProcesses)
                           {
                            processes[i].responseTime=currentTime-processes[i].arrivalTime;
                            c++;
                           }
                        currentTime += remainingTime[i];
                        processes[i].waitingTime = currentTime - processes[i].arrivalTime - processes[i].burstTime;
                        remainingTime[i] = 0;
                        processes[i].turnaroundTime = currentTime - processes[i].arrivalTime;
                    }
                }
            }

            if (allFinished) {
                break;
            }
        }
    }

    public static void displayResults(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;

        System.out.println("Process\t\tWaiting Time\t\tTurnaround Time\t\tResponse Time");

        for (Process process : processes) {
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
            totalResponseTime += process.responseTime;
            System.out.println(process.id + "\t\t" + process.waitingTime + "\t\t\t" + process.turnaroundTime + "\t\t\t" + process.responseTime);
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.length;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.length;
        double avgResponseTime = (double) totalResponseTime / processes.length;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Response Time: " + avgResponseTime);
    }
}
