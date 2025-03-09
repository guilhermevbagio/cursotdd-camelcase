package cursotdd.camelcase;

import java.util.ArrayList;
import java.util.List;

public class App {

    public List<String> quebraCamelCase(String frase) {
        if(isNumber(frase.charAt(0) )) throw new StartsWithNumberException("ops");
      
        List<String> out = breakStrings(frase);


        if(out.isEmpty()) 
            out.add(handleCase(frase));
        var casedOut = out.stream().map(this::handleCase).toList();
        return joinUpperCase(casedOut);
    }

    public List<String> breakStrings(String frase) {
        List<String> out = new ArrayList<String>();
        int palavraAnterior = 0;
        for (int i = 0; i < frase.length(); i++) {
            if(i > 1 && isUpperCaseOrNumber(frase.charAt(i))) {
                out.add(frase.substring(palavraAnterior, i));
                palavraAnterior = i;
                continue;
            }
        }
        if(palavraAnterior < frase.length()) 
            out.add(frase.substring(palavraAnterior));
        return out;
    }

    public boolean isUpperCaseOrNumber(char c) {
        if(isSpecialChar(c)) throw new SpecialCharException("String must not contain special chars");
        return c >= 'A' && c <= 'Z' || isNumber(c);
    }

    public boolean isNumber(char c){
        if(isSpecialChar(c)) throw new SpecialCharException("String must not contain special chars");
        return  c >= 48 && c <= 57;
    }

    public boolean isSpecialChar(char c){
        return String.valueOf(c).matches("[^a-zA-Z0-9]");
    }

    public String handleCase(String s) {
        if(s.length() <= 1) return s;
        return isUpperCaseOrNumber(s.charAt(1)) ? s.toUpperCase() : s.toLowerCase();
    }

    public List<String> joinUpperCase(List<String> strings) {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            if (isUpperCaseOrNumber(strings.get(i).charAt(0))) {
                int j = findEndOfUppercase(strings, i);
                out.add(mergeStrings(strings.subList(i, j)));
                i = j - 1;
            } else out.add(strings.get(i));
        }
        return out;
    }
    
    private int findEndOfUppercase(List<String> strings, int i) {
        int j = i;
        while (j < strings.size() && isUpperCaseOrNumber(strings.get(j).charAt(0))) j++;
        return j;
    }

    public String mergeStrings(List<String> strings) {
        return strings.stream()
                      .reduce((s1, s2) -> s1 + s2)
                      .orElse("");
    }
    
}
