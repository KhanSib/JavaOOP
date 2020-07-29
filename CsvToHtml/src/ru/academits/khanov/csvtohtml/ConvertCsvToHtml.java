package ru.academits.khanov.csvtohtml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConvertCsvToHtml {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.csv"));//new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter("output.html")) {

            boolean isTableRowOpen = false;
            boolean isTableDetailOpen = false;
            boolean isTableDetailShielded = false;
            int doubleQuotesCount = 0;

            writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"" +
                    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>!DOCTYPE</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!isTableRowOpen) {
                    writer.println("<tr>");
                    isTableRowOpen = true;
                }

                if (!isTableDetailOpen) {
                    writer.print("<td>");
                    isTableDetailOpen = true;
                }

                int i = 0;

                while (i < line.length()) {
                    if (!isTableDetailOpen) {
                        writer.print("<td>");
                        isTableDetailOpen = true;
                    }

                    if (line.charAt(i) == '<') {
                        writer.print("&lt;");
                        i++;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&gt;");
                        i++;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp;");
                        i++;
                    }

                    if (line.charAt(i) == ',') {
                        if (isTableDetailShielded && doubleQuotesCount % 2 == 0) {
                            writer.print("</td>");
                            isTableDetailOpen = false;
                            isTableDetailShielded = false;
                            doubleQuotesCount = 0;
                            i++;
                            continue;
                        }

                        if (!isTableDetailShielded) {
                            writer.print("</td>");
                            isTableDetailOpen = false;
                            i++;
                            continue;
                        }

                        writer.print(",");
                        i++;
                        continue;
                    }

                    if (line.charAt(i) == '"') {
                        if (!isTableDetailShielded) {
                            doubleQuotesCount++;
                            isTableDetailShielded = true;
                            i++;
                            continue;
                        }

                        doubleQuotesCount++;

                        if (i + 1 < line.length() - 1) {
                            if (line.charAt(i + 1) == '"') {
                                writer.print("\"");
                                doubleQuotesCount++;
                                i += 2;
                                continue;
                            }

                            if (line.charAt(i + 1) == ',' && doubleQuotesCount % 2 != 0) {
                                writer.print(",");
                                i += 2;
                                continue;
                            }

                            writer.print("</td>");
                            isTableDetailOpen = false;
                            isTableDetailShielded = false;
                            doubleQuotesCount = 0;
                            i += 2;
                            continue;
                        }

                        i++;
                        continue;
                    }

                    writer.print(line.charAt(i));
                    i++;
                }

                if (isTableDetailShielded && doubleQuotesCount % 2 == 0) {
                    writer.print("</td>");
                    isTableDetailOpen = false;

                    if (isTableRowOpen = true) {
                        writer.println();
                        writer.print("</tr>");
                        isTableRowOpen = false;
                    }

                    isTableDetailShielded = false;
                    doubleQuotesCount = 0;

                    continue;
                }

                if (isTableDetailOpen) {
                    if (!isTableDetailShielded) {
                        writer.print("</td>");
                        isTableDetailOpen = false;
                    } else {
                        writer.print("<br/>");
                    }
                }

                if (!isTableDetailShielded) {
                    writer.println();
                    writer.println("</tr>");
                    isTableRowOpen = false;
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}