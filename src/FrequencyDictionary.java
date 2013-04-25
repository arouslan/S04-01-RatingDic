import java.io.*;
import java.util.*;

/**
 * Author: Abushaev Ruslan
 * e-mail: rouslan@inbox.ru
 * Date: 23.04.13
 * Time: 13:41
 */
public class FrequencyDictionary {
    private List<String> f_fileList;
    private Reader f_in;
    private PrintWriter f_out;
    private HashMap<String, Double> f_Dictionary;

    public FrequencyDictionary() {
        f_Dictionary = new HashMap<String, Double>();
        f_fileList = new ArrayList<String>(2);
    }

    private void OpenReader(String fileName) throws IOException {
        if (f_in != null) {
            f_in.close();
        } else {
            f_in = new InputStreamReader(new BufferedInputStream(new FileInputStream(fileName)));
        }
    }

    public void addFileToDictionary(String fileName) throws IOException {
        OpenReader(fileName);
        if (addDictionarySet()) {
            f_in.close();
            addFileNameToList(fileName);
        }

    }

    private void addFileNameToList(String fileName) {
        f_fileList.add(fileName);
    }

    public int getCounterAddFiles() {
        return f_fileList.size();
    }

    public List<String> getAddFilesListName() {
        return f_fileList;
    }

    private void addWordToDictionary(String word) {
        if (f_Dictionary.containsKey(word)) {
            f_Dictionary.put(word, f_Dictionary.get(word) + 1);
        } else {
            f_Dictionary.put(word, Double.valueOf(1));
        }
    }

    private boolean addDictionarySet() {
        int data;
        char ch;
        boolean isAdded = false;
        StringBuilder sb = new StringBuilder();


        assert f_in != null;

        try {
            while ((data = f_in.read()) > 0) {
                ch = (char) data;
                if (Character.isLetterOrDigit(ch)) {
                    sb.append(ch);
                } else {
                    if ( sb.length()>0) {
                        addWordToDictionary(sb.toString().toLowerCase());
                        if (!isAdded) isAdded = true;
                    }
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            isAdded = false;
        }

        return isAdded;
    }

    public void sortDictionarySave(String fileName) {

        // отсортируем словарь
        List<Map.Entry<String, Double>> entryList = new ArrayList(f_Dictionary.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> me1, Map.Entry<String, Double> me2) {
                int i;
                Comparable c1 = (Comparable) me1.getValue();
                Comparable c2 = (Comparable) me2.getValue();
                i = c2.compareTo(c1);
                if (i == 0) {
                    Comparable c3 = (Comparable) me1.getKey();
                    Comparable c4 = (Comparable) me2.getKey();
                    i = c4.compareTo(c3);
                }
                return i;
            }
        });

        // запишем в файл
        int count = entryList.size();
        try {
            f_out = new PrintWriter(fileName);
            f_out.println();
            for (Map.Entry<String, Double> ent : entryList) {
                f_out.println(ent.getKey() + ", " + ent.getValue() + ", " + ent.getValue() / count * 100);
            }
            f_out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
