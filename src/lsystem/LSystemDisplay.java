package lsystem;

/**
 * @author Alex Pinkney
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LSystemDisplay extends JFrame {

    LSystemRuleset ruleset;
    private double length = 5;
    private int iterations;

    public void setRuleset(LSystemRuleset rs) {
        this.ruleset = rs;
        repaint();
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
        repaint();
    }

    public LSystemDisplay(LSystemRuleset r, int iterations) {
        //lsysgen = new LSystemGenerator(filename, iterations);
        ruleset = r;
        this.iterations = iterations;
        setVisible(true);
        setSize(1100, 900);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);




    }
    public JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            int colour = 0;

            g.setColor(getColour(colour));
            this.setBackground(Color.BLACK);

            double minx = 0;
            double maxx = 0;
            double miny = 0;
            double maxy = 0;

            double angle = ruleset.getAngle();

            double x;
            double y;
            double t;
            double t2; //t2 is used for D and M commands

            double newx;
            double newy;

            double size = length;

            int reverse;

            Stack<GState> gstack = new Stack<GState>();
            String string = ruleset.getString(iterations);


            for (int j = 0; j < 2; j++) {


                x = Math.abs(minx);
                y = Math.abs(miny);
                t = 90;
                t2 = 90;
                reverse = 1;


                if (j == 1) { //ie if we're actually drawing, sort out the scaling
                    double xs = (double) getWidth() / (maxx - minx);
                    double ys = (double) getHeight() / (maxy - miny);
                    double scale;
                    double xpad = 0;
                    double ypad = 0;

                    if (xs < ys) {
                        scale = xs;
                        ypad = (getHeight() - scale * (maxy - miny)) / 2;
                    } else {
                        scale = ys;
                        xpad = (getWidth() - scale * (maxx - minx)) / 2;
                    }
                    x = x * scale + xpad;
                    y = y * scale + ypad;
                    //System.out.println(xpad+" "+ypad);
                    size = length * scale;
                }

                for (int i = 0; i < string.length(); i++) {
                    //System.out.println(i+" " + string.charAt(i));
                    Tuple tup;
                    switch (string.charAt(i)) {
                        case 'D':
                        case 'd':
                        case 'M':
                        case 'm':
                            newx = x + size * Math.sin(Math.toRadians(t2));
                            newy = y + size * Math.cos(Math.toRadians(t2));
                            if (j == 0) {
                                if (newx > maxx) {
                                    maxx = newx;
                                }
                                if (newx < minx) {
                                    minx = newx;
                                }
                                if (newy > maxy) {
                                    maxy = newy;
                                }
                                if (newy < miny) {
                                    miny = newy;
                                }
                            } else {
                                if (string.charAt(i) == 'D' || string.charAt(i) == 'd') {
                                    g.setColor(getColour(colour));
                                    g.drawLine((int) x, (int) y, (int) newx, (int) newy);
                                }
                            }
                            x = newx;
                            y = newy;
                            break;
                        case 'F':
                        case 'f':
                        case 'G':
                        case 'g':
                            newx = x + size * Math.sin(Math.toRadians(t));
                            newy = y + size * Math.cos(Math.toRadians(t));
                            if (j == 0) {
                                if (newx > maxx) {
                                    maxx = newx;
                                }
                                if (newx < minx) {
                                    minx = newx;
                                }
                                if (newy > maxy) {
                                    maxy = newy;
                                }
                                if (newy < miny) {
                                    miny = newy;
                                }
                            } else {
                                if (string.charAt(i) == 'F' || string.charAt(i) == 'f') {
                                    g.setColor(getColour(colour));
                                    g.drawLine((int) x, (int) y, (int) newx, (int) newy);
                                }
                            }
                            x = newx;
                            y = newy;
                            break;
                        case '-':
                            t -= reverse * 360.0 / angle;
                            break;
                        case '+':
                            t += reverse * 360.0 / angle;
                            break;
                        case '[':
                            gstack.push(new GState(x, y, t, t2, size, reverse, colour));
                            break;
                        case ']':
                            if (gstack.empty()) {
                                break;
                            }
                            GState state = gstack.pop();
                            x = state.x;
                            y = state.y;
                            t = state.t;
                            t2 = state.t2;
                            size = state.length;
                            reverse = state.reverse;
                            colour = state.colour;
                            break;
                        case '!':
                            reverse *= -1;
                            break;
                        case '|':
                            t += (360.0 / angle) * (((int) angle) / 2);
                            break;
                        case '\\':
                            tup = parseStringNumeric(string, i + 1);
                            t2 += tup.value * reverse;
                            i = tup.index;
                            break;
                        case '/':
                            tup = parseStringNumeric(string, i + 1);
                            t2 -= tup.value * reverse;
                            i = tup.index;
                            break;
                        case '<':
                            tup = parseStringNumeric(string, i + 1);
                            colour += (int) tup.value;
                            i = tup.index;
                            break;
                        case '>':
                            tup = parseStringNumeric(string, i + 1);
                            colour -= (int) tup.value;
                            i = tup.index;
                            break;
                        case 'C':
                        case 'c':
                            tup = parseStringNumeric(string, i + 1);
                            colour = (int) tup.value;
                            i = tup.index;
                            break;
                        case '@':
                            tup = parseStringNumeric(string, i + 1);
                            size *= tup.value;
                            i = tup.index;
                            break;
                        default:
                            break;
                    }//end switch
                }//end inner for
            }//end outer for
        }//end paintComponent
    };

    private Tuple parseStringNumeric(String s, int start) {

        double d;
        int j;

        boolean q = false;// Q or q encountered
        boolean i = false;// I or i encountered
        //square root and inversion appear to be commutative, so ordering doesn't matter here

        boolean n = false;// Looking only for numeric chars

        String number = "";

        //Loop through string, starting at index start. Look for Qs and Is first
        //then numeric digits. Stop when non-numeric digit encountered.
        for (j = start; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!n) {
                if (c == '.' || (c >= 48 && c <= 57)) {
                    n = true;
                    number += c;
                } else {
                    if (c == 'q' || c == 'Q') {
                        q = true;
                    } else if (c == 'i' || c == 'I') {
                        i = true;
                    }
                }
            } else {
                if (c == '.' || (c >= 48 && c <= 57)) {
                    number += c;
                } else {
                    break;
                }
            }
        }

        d = Double.parseDouble(number);
        if (q) {
            d = Math.sqrt(d);
        }
        if (i) {
            d = 1.0 / d;
        }

        return new Tuple(d, j - 1);
    }

    private Color getColour(int i) {
        //System.out.print(i+ " ");
        int numcolours = 12;
        i = (i % numcolours + numcolours) % numcolours;
        //System.out.println(i);
        switch (i) {
            case 1:
                return Color.BLUE;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.DARK_GRAY;
            case 4:
                return Color.GRAY;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.LIGHT_GRAY;
            case 7:
                return Color.MAGENTA;
            case 8:
                return Color.ORANGE;
            case 9:
                return Color.PINK;
            case 10:
                return Color.RED;
            case 11:
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }

    }

    private class Tuple {

        public double value;
        public int index;

        public Tuple(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private class GState { //Holds a graphics state for the graphics stack

        public double x;
        public double y;
        public double t; //theta, bitches
        public double t2;
        public int reverse;
        public double length;
        public int colour;

        public GState(double x, double y, double t, double t2, double length, int reverse, int colour) {

            this.x = x;
            this.y = y;
            this.t = t;
            this.t2 = t2;
            this.reverse = reverse;
            this.length = length;
            this.colour = colour;
        }
    }
}
