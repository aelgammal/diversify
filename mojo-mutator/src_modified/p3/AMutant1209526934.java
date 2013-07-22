package p3;


public class AMutant1209526934 implements java.awt.event.KeyListener {
    public AMutant1209526934() {
        k = this;
    }
    
    public static void main(java.lang.String[] args) {
        new p3.AMutant1209526934().foo();
    }
    
    java.util.List<java.lang.Object>  cache = new java.util.ArrayList<java.lang.Object> ();
    
    public void foo() {
        new java.lang.Thread(new java.lang.Runnable() {
            public void run() {
                while (true)
                    ;
            }
            
        }).start();
        cache.add(new byte[1000000]);
        int j = 0;
        for (int i = 0 ; i < 100 ; i++) {
            j++;
        }
        java.lang.System.err.println(j);
    }
    
    public void foo1() {
        int j = 0;
        while (true) {
            for (int i = 0 ; i < 100 ; i = i + 2) {
                j++;
            }
            java.lang.System.err.println(j);
        }
    }
    
    java.awt.event.KeyListener k;
    
    public void keyPressed(java.awt.event.KeyEvent e) {
    }
    
    public void keyReleased(java.awt.event.KeyEvent e) {
    }
    
    public void keyTyped(java.awt.event.KeyEvent e) {
    }
    
}

