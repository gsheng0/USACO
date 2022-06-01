package misc;

import template.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Apartments extends Template<Integer> {
    public Apartments() { super.setClass(Apartments.class); }
    public static class Applicant implements Comparable<Applicant>{
        static int maxDiff = 0;
        int area;
        ArrayList<Integer> apartmentIndices = new ArrayList<>();

        public Applicant(int area){
            this.area = area;
        }
        public void add(int value, int index){
            if(Math.abs(value - area) <= maxDiff){
                apartmentIndices.add(index);
            }
        }

        public int compareTo(Applicant other){
            if(other.apartmentIndices.size() < apartmentIndices.size()){
                return 1;
            }
            else if(other.apartmentIndices.size() == apartmentIndices.size()){
                return 0;
            }
            return -1;
        }
        public String toString(){
            String out = "" + area;
            for(int i = 0; i < apartmentIndices.size(); i++){
                out += "\n\t" + apartmentIndices.get(i);
            }
            return out;
        }

    }
    public Integer run(){

        Template.InputReader r = super.getInputReader();
        int numApplicants = r.nextInt();
        int numApartments = r.nextInt();
        Applicant.maxDiff = r.nextInt();
        Applicant[] applicants = new Applicant[numApplicants];
        int[] apartments = new int[numApartments];
        for(int i = 0; i < numApplicants; i++){
            applicants[i] = new Applicant(r.nextInt());
        }
        for(int i = 0; i < numApartments; i++){
            apartments[i] = r.nextInt();
            for(int x = 0; x < numApplicants; x++){
                applicants[x].add(apartments[i], i);
            }
        }

        for(int i = 0; i < applicants.length; i++){
            System.out.println(applicants[i]);
        }
        int counter = 0;
        List<Applicant> list = Arrays.asList(applicants);
        Collections.sort(list);
        int[] tracker = new int[numApartments];
        for(int i = 0; i < list.size(); i++){
            Applicant applicant = list.get(i);
            for(int index : applicant.apartmentIndices){
                if(tracker[index] == 0){
                    tracker[index] = 1;
                    counter++;
                    break;
                }
            }
        }
        return counter;

    }
    public Integer test(){
        return 2;
    }

    public static void main(String[] args) throws Exception{
        Apartments apartments = new Apartments();
        apartments.testSample();
    }
}
