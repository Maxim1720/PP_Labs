package Lab_1;
import Lab_1.DOM.XmlDocCorrector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("source xml path:");
        String sourcePath = in.next();
        //C:\\Users\\Kruppov07\\Desktop\\University\\xml\\groups.xml

        System.out.println("target xml path:");
        String targetPath = in.next();
        //C:\\Users\\Kruppov07\\Desktop\\University\\xml\\correctedGroups.xml

        XmlDocCorrector docCorrector = new XmlDocCorrector(sourcePath, targetPath);
        docCorrector.correct();
        docCorrector.saveChangesToTargetFile();
    }
}
