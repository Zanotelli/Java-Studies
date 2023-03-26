package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.UK);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("CADASTRO DE USUÁRIO");
        System.out.println("Nome do departamento: ");
        String departament = sc.nextLine();
        System.out.print("Dados do Funcionário: ");
        System.out.print("- Nome: ");
        String workerName = sc.nextLine();
        System.out.print("- Nível: ");
        String workerLevel = sc.nextLine();
        System.out.print("- Salário base: ");
        Double baseSalary = Double.valueOf(sc.nextLine());

        Worker worker = new Worker(
                workerName,
                WorkerLevel.valueOf(workerLevel),
                baseSalary,
                new Department(departament)
        );


        System.out.print("Quantos contratos tem o trabalhador ? ");
        int nContracts = sc.nextInt();


        for (int i = 0; i < nContracts; i++){
            System.out.println("Dados do contrato #" + (i+1) +": ");
            System.out.print("- Data(dd/MM/yyyy): ");
            Date dateContract = sdf.parse(sc.next());
            System.out.print("- Valor por hora: ");
            Double hValueContact = sc.nextDouble();
            System.out.print("- Duração (horas): ");
            Integer hoursContract = sc.nextInt();

            HourContract contract = new HourContract(
                    dateContract,
                    hValueContact,
                    hoursContract
            );
            worker.addContract(contract);
        }

        System.out.println();
        System.out.print("Entre com mês e ano para calcular o saslário (MM/yyyy);");
        String monthYear = sc.nextLine();
        int month = Integer.parseInt(monthYear.substring(0,2));
        int year = Integer.parseInt(monthYear.substring(3));

        System.out.println("Nome: " + worker.getName());
        System.out.println("Departamento: " + worker.getDepartment());
        System.out.println("Salário em "+ monthYear+ ": " + worker.income(year,month));

        sc.close();
    }
}
