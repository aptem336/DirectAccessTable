package tables;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Algorithm {

    //S1 G1 A1 A2 S12 B4 A3 X3 X16
    private static String[][] headsTable;
    private static String[][] listsTable;
    private static String error = "";

    public static void add(String idString) {
        String[] IDs = idString.trim().split(" ");
        if (!isCorrectString(IDs)) {
            JOptionPane.showMessageDialog(null, "Ошибка получения идентификаторов из строки:\n'" + idString + "'\n" + error);
            return;
        }
        String[][] mem = getListsTable().clone();
        listsTable = new String[mem.length + IDs.length][3];
        System.arraycopy(mem, 0, getListsTable(), 0, mem.length);
        for (int i = mem.length; i < getListsTable().length; i++) {
            String ID = IDs[i - mem.length].trim();
            String A = "A" + ID;
            int hash = hash(ID);
            if (headsTable[hash][1].equals("-")) {
                headsTable[hash][1] = A;
            } else {
                String head = headsTable[hash][1];
                String[] head_row = null;
                do {
                    for (String[] list_row : listsTable) {
                        if (list_row[0].equals(head)) {
                            head_row = list_row;
                            head = list_row[2];
                            break;
                        }
                    }
                } while (!head.equals("-"));
                head_row[2] = A;
            }
            listsTable[i][0] = A;
            listsTable[i][1] = ID;
            listsTable[i][2] = "-";
        }
    }

    private static int hash(String ID) {
        return (int) ID.toLowerCase().charAt(0) - 97;
    }

    public static void clear() {
        headsTable = new String[26][2];
        for (int i = 0; i < headsTable.length; i++) {
            headsTable[i][0] = i + "";
            headsTable[i][1] = "-";
        }
        listsTable = new String[][]{};
    }

    private static boolean isCorrectString(String[] idString) {
        for (String ID : idString) {
            if (!isCorrectID(ID)) {
                error = "Некорректный идентификатор '" + ID + "'!";
                return false;
            }
            if (contains(ID)) {
                error = "Идентификатор '" + ID + "' уже присутствует в таблице!";
                return false;
            }
        }
        return true;
    }

    private static boolean contains(String ID) {
        for (String[] list_row : listsTable) {
            if (list_row[1].equals(ID)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCorrectID(String ID) {
        return Pattern.compile("^[a-zA-Z]+([a-zA-Z0-9])*$").matcher(ID).matches();
    }

    public static String[][] getHeadsTable() {
        return headsTable;
    }

    public static String[][] getListsTable() {
        return listsTable;
    }
}
