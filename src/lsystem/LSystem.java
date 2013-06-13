package lsystem;

/**
 * @author Alex Pinkney
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LSystem {

    public static void main(String[] args) {
        new LSystem();
    }
    private ArrayList<LSystemRuleset> rulesets;

    public LSystem() {
        rulesets = new ArrayList<LSystemRuleset>();

        File file = new File(".");
        File[] files = file.listFiles();

        for (File f : files) {
            String fname = f.toString();
            if (fname.endsWith(".l")) {
                parseFile(new File(fname));
            }
        }

        Collections.sort(rulesets);

        LSystemSettings settingsWindow = new LSystemSettings(rulesets);
        settingsWindow.setVisible(true);

        settingsWindow.setRulesets(rulesets);
    }

    private void parseFile(File file) {
        try {
            Scanner s = new Scanner(file);
            LSystemRuleset rules = null;
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                //System.out.println(line);
                if (line.isEmpty()) { //Skip blank lines
                    continue;
                }
                if (rules == null) {
                    if (line.startsWith(";") || line.equals("")) { //Ignore comments outside rulesets
                        continue;
                    }
                    String[] bits = line.split("\\{", 2);
                    rules = new LSystemRuleset(bits[0]);
                    if (!bits[1].trim().isEmpty()) {
                        rules.addComment("; " + bits[1].trim());
                    }
                } else {
                    if (line.startsWith("}")) {
                        rulesets.add(rules);
                        rules = null;
                        continue;
                    }
                    String[] bits = line.split(";", 2);
                    if (bits.length == 2 && !bits[1].trim().isEmpty()) {
                        rules.addComment("; " + bits[1].trim());
                    }
                    if (bits[0].length() > 6) { // Might have angle/axiom
                        String kw = bits[0].substring(0, 5);
                        if (kw.equalsIgnoreCase("angle")) {
                            rules.setAngle(Double.parseDouble(bits[0].substring(6).trim()));
                            continue;
                        } else if (kw.equalsIgnoreCase("axiom")) {
                            rules.setAxiom(bits[0].substring(6).trim());
                            continue;
                        }
                    }
                    bits[0] = bits[0].trim();
                    if (!bits[0].isEmpty()) {
                        rules.addRule(bits[0].charAt(0), bits[0].substring(2));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
