package ru.testexample.console.input;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleStepInput implements StepInput {

    private BufferedReader reader;

    public ConsoleStepInput(InputStreamReader inputStreamReader){
        this.reader = new BufferedReader(inputStreamReader);
    }

    public int inputStep() {
        String field = null;
        try {
            Pattern p = Pattern.compile("^\\d$");

            while (true){
                field = reader.readLine();
                Matcher m = p.matcher(field);
                if (field.equals("q")){
                    return -1;}
                else if(m.matches()){
                    int val = Integer.valueOf(field);
                    if (val >= 1 & val <=9){
                        return val-1;
                    }
                } else {
                    System.out.println("Введите номер поля 1-9. Для выхода нажмите \"q\"");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("INPUT FAILED: полученный символ: " + field);
        return -1;
    }
}
