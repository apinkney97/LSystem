/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author azp
 */
public class LSystemRuleset implements Comparable<LSystemRuleset> {

    private double angle;
    private HashMap<Character, String> rules;
    private HashMap<Integer, String> expansions;
    public final String NAME;
    public ArrayList<String> comments;
    private String ruletext;
    private int lastviewedit;

//    public LSystemRuleset(String name, double angle, String start, HashMap<Character,String> rules, ArrayList<String> comments) {
//        NAME = name;
//        this.angle = angle;
//        this.rules = rules;
//
//        expansions = new HashMap<Integer,String>();
//        expansions.put(0, start);
//
//        //printRules();
//
//    }
    public LSystemRuleset(String name) {
        NAME = name;
        rules = new HashMap<Character, String>();
        comments = new ArrayList<String>();
        expansions = new HashMap<Integer, String>();
        lastviewedit = 0;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setAxiom(String axiom) {
        expansions.put(0, axiom);
    }

    public void addRule(char c, String s) {
        c = Character.toUpperCase(c);
        if (rules.containsKey(c)) {
            rules.put(c, rules.get(c) + s);
        } else {
            rules.put(c, s);
        }
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public String getString(int iterations) {
        if (!expansions.containsKey(iterations)) {
            expansions.put(iterations, expand(getString(iterations - 1)));
        }
        return expansions.get(iterations);
    }

    public double getAngle() {
        return angle;
    }

    public int getLastViewedIt() {
        return lastviewedit;
    }

    public void setLastViewedIt(int n) {
        lastviewedit = n;
    }

    private String expand(String s) {
        String oldstring = s;
        StringBuilder newstring;

        newstring = new StringBuilder(""); //clear the new string
        for (int j = 0; j < oldstring.length(); j++) { //iterate over each character in the old string
            char c = oldstring.charAt(j);
            newstring.append(applyRule(c));
        }
        return newstring.toString();

    }

    public String getRuleText() {
        if (ruletext == null) {
            StringBuilder sb = new StringBuilder("");

            for (String c : comments) {
                sb.append(c);
                sb.append('\n');
            }

            sb.append('\n');
            sb.append("Angle: ");
            sb.append(angle);
            sb.append('\n');
            sb.append("Start: ");
            sb.append(expansions.get(0));
            sb.append('\n');
            sb.append("Rules:");
            sb.append('\n');

            Set<Character> keys = rules.keySet();
            for (Character k : keys) {
                sb.append(k);
                sb.append(" -> ");
                sb.append(rules.get(k));
                sb.append('\n');
            }


            ruletext = sb.toString();
        }

        StringBuilder sb = new StringBuilder(ruletext);
        sb.append("\n\norder\tchars\n");
        for (int i = 0; i < expansions.size(); i++) {
            sb.append(i);
            sb.append("\t");
            sb.append(expansions.get(i).length());
            sb.append('\n');
        }

        return sb.toString();
    }

    private String applyRule(char c) {
        c = Character.toUpperCase(c);
        if (rules.containsKey(c)) {
            return rules.get(c);
        } else {
            return String.valueOf(c);
        }
    }

    public int compareTo(LSystemRuleset rs) {
        int val = this.NAME.compareTo(rs.NAME);
        //if (val != 0)
            return val;
        
    }
}
