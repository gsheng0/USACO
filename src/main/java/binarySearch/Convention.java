package binarySearch;

import template.Template;

public class Convention extends Template<Integer> {
    public Convention(){
        super.setClass(Convention.class);
    }
    public Integer run(){
        return 4;
    }
    public Integer test() {
        return 4;
    }
    public static void main(String[] args) throws Exception {
        Convention app = new Convention();
        app.testSample();
    }
}
