package problems2011;

import template.Template;

public class Pageant extends Template<Integer> {
    public Pageant(){
        super.setFilename("Pageant");
    }

    @Override
    public Integer run() {
        return null;
    }

    @Override
    public Integer test() {
        return null;
    }
    public static void main(String[] args) throws Exception {
        Pageant app = new Pageant();
        app.testSample();
    }
}
