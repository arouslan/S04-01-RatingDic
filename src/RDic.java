import java.io.IOException;
import java.util.*;

/**
 * Author: Abushaev Ruslan
 * e-mail: rouslan@inbox.ru
 * Date: 20.04.13
 * Time: 10:41
 */
public class RDic {
    public static void main(String[] args) {

        if (args.length > 0) {
            FrequencyDictionary fd = new FrequencyDictionary();
            for (String arg : args){
                try {
                    fd.addFileToDictionary(arg);

                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            fd.sortDictionarySave("c:\\fdic.txt");
        } else {
            System.out.print("Необходимо в командной строке указать имя файла(ов)!");
        }



    }
}
