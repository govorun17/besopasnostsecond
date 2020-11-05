import java.io.IOException;

public class Vigener {
    private final String RU = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ ";
    private final String EN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    public String encrypt(String text, String key) throws IOException {
        text = text.toUpperCase();
        key = key.toUpperCase();
        String alphabet = chooseAlphabet(text);
        validateKey(key, alphabet);

        StringBuilder result = new StringBuilder();

        int key_index = 0;

        for (char c : text.toCharArray()) {

            if (c == ' ') {
                result.append(c);
                continue;
            }

            int i = (alphabet.indexOf(c) + alphabet.indexOf(key.charAt(key_index))) % alphabet.length();
            result.append(alphabet.charAt(i));

            if (++key_index == key.length()) {
                key_index = 0;
            }
        }

        return result.toString();
    }

    public String decrypt(String text, String key) throws IOException {
        text = text.toUpperCase();
        key = key.toUpperCase();
        String alphabet = chooseAlphabet(text);
        validateKey(key, alphabet);

        StringBuilder result = new StringBuilder();

        int key_index = 0;

        for (char c : text.toCharArray()) {

            if (c == ' ') {
                result.append(c);
                continue;
            }

            int i = (alphabet.indexOf(c) + alphabet.length() - alphabet.indexOf(key.charAt(key_index))) % alphabet.length();
            result.append(alphabet.charAt(i));

            if (++key_index == key.length()) {
                key_index = 0;
            }
        }

        return result.toString();
    }

    private String chooseAlphabet(String text) throws IOException {
        String result = null;
        for (char c : text.toCharArray()) {
            if (RU.indexOf(c) == -1) {
                result = null;
                break;
            } else {
                result = RU;
            }
        }
        if (result != null) {
            return result;
        }
        for (char c : text.toCharArray()) {
            if (EN.indexOf(c) == -1) {
                result = null;
                break;
            } else {
                result = EN;
            }
        }
        if (result != null) {
            return result;
        }
        else {
            throw new IOException("В тексте используется комбинация букв, не найденная в алфавите системы");
        }
    }
    private void validateKey(String key, String alphabet) throws IOException {
        for (char c : key.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (alphabet.indexOf(c) == -1) {
                throw new IOException("Алфавит ключа и текста не совпадают");
            }
        }
    }
}
