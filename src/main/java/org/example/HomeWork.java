package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        StepStruct st = new StepStruct(n);

        int q = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < q; ++i) {
            int index = scanner.nextInt();
            st.update(index - 1);

            int len = st.length();
            list.add(len);
            out.write(String.valueOf(len).getBytes());
            out.write(("\r\n").getBytes());
        }
    }
}
